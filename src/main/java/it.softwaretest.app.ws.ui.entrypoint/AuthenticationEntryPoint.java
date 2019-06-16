package it.softwaretest.app.ws.ui.entrypoint;

import it.softwaretest.app.ws.service.AuthenticationService;
import it.softwaretest.app.ws.service.impl.AuthenticationServiceImpl;
import it.softwaretest.app.ws.shared.dto.UserDto;
import it.softwaretest.app.ws.ui.model.request.LoginCredentials;
import it.softwaretest.app.ws.ui.model.response.AuthenticationDetails;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/authentication")
public class AuthenticationEntryPoint {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AuthenticationDetails userLoggin(LoginCredentials loginCredentials) {
        AuthenticationDetails returnValue = new AuthenticationDetails();

        AuthenticationService authenticationService = new AuthenticationServiceImpl();
        UserDto authenticatedUser = authenticationService.authenticate(loginCredentials.getUserName(), loginCredentials.getUserPassword());

        authenticationService.resetSecurityCredentials(loginCredentials.getUserPassword(), authenticatedUser);

        String accessToken = authenticationService.issueAccessToken(authenticatedUser);

        returnValue.setId(authenticatedUser.getUserId());
        returnValue.setToken(accessToken);

        return returnValue;
    }
}
