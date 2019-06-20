package it.softwaretest.app.ws.ui.entrypoint;

import it.softwaretest.app.ws.service.AuthenticationServiceInterface;
import it.softwaretest.app.ws.service.impl.AuthenticationService;
import it.softwaretest.app.ws.shared.dto.impl.UserDto;
import it.softwaretest.app.ws.ui.model.request.impl.LoginCredentials;
import it.softwaretest.app.ws.ui.model.response.impl.AuthenticationDetails;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/authentication")
public class AuthenticationEntryPoint {

    @Autowired
    AuthenticationService authenticationService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AuthenticationDetails userLogin(LoginCredentials loginCredentials) {
        AuthenticationDetails returnValue = new AuthenticationDetails();

        UserDto authenticatedUser = authenticationService.authenticate(loginCredentials.getUserName(), loginCredentials.getUserPassword());

        authenticationService.resetSecurityCredentials(loginCredentials.getUserPassword(), authenticatedUser);

        String accessToken = authenticationService.issueAccessToken(authenticatedUser);

        returnValue.setId(authenticatedUser.getUserId());

        returnValue.setToken(accessToken);

        return returnValue;
    }
}
