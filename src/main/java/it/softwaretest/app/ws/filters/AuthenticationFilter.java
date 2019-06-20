package it.softwaretest.app.ws.filters;

import it.softwaretest.app.ws.annotations.Secured;
import it.softwaretest.app.ws.service.impl.UsersService;
import it.softwaretest.app.ws.shared.dto.impl.UserDto;
import it.softwaretest.app.ws.utilities.UserProfileCreationAndValidation;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Priority;
import javax.security.sasl.AuthenticationException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @Autowired
    UsersService usersService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        this.checkIfAuthorizationHeaderExists(authorizationHeader);
        this.checkIfTokenIstValid(authorizationHeader, requestContext);
    }

    private void checkIfAuthorizationHeaderExists(String authorizationHeader) throws IOException {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer")) {
            throw new AuthenticationException("Authorization header must be provided");
        }
    }

    private void checkIfTokenIstValid(String authorizationHeader, ContainerRequestContext requestContext) throws IOException  {
        String token = authorizationHeader.substring("Bearer".length()).trim();
        String userId = requestContext.getUriInfo().getPathParameters().getFirst("id");

        this.validateToken(token, userId);
    }

    private void validateToken(String token, String userId) throws IOException {
        UserDto userProfile = usersService.getUser(userId);
        String completeToken = userProfile.getToken() + token;
        String salt = userProfile.getSalt();

        String securePassword = userProfile.getEncryptedPassword();
        String accessTokenMaterial = userId + salt;

        byte[] encryptedAccessToken = this.encryptAccessToken(securePassword, accessTokenMaterial);

        this.authenticateOrThrowErrorMessage(completeToken, encryptedAccessToken);
    }

    private byte[] encryptAccessToken(String securePassword, String accessTokenMaterial) throws AuthenticationException {
        byte[] encryptedAccessToken;

        try {
            encryptedAccessToken = new UserProfileCreationAndValidation().encrypt(securePassword, accessTokenMaterial);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(AuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
            throw new AuthenticationException("Failed to issue secure access token");
        }

        return encryptedAccessToken;
    }

    private void authenticateOrThrowErrorMessage(String completeToken, byte[] encryptedAccessToken) throws IOException {
        String encryptedAccessTokenBase64Encoded = Base64.getEncoder().encodeToString(encryptedAccessToken);

        if (!encryptedAccessTokenBase64Encoded.equalsIgnoreCase(completeToken))
            throw new AuthenticationException("Authorization token did not match");
    }

}
