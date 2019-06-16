package it.softwaretest.app.ws.service.impl;

import it.softwaretest.app.ws.exceptions.AuthenticationException;
import it.softwaretest.app.ws.io.dao.Dao;
import it.softwaretest.app.ws.io.dao.impl.MySqlDao;
import it.softwaretest.app.ws.service.AuthenticationService;
import it.softwaretest.app.ws.service.UsersService;
import it.softwaretest.app.ws.shared.dto.UserDto;
import it.softwaretest.app.ws.ui.model.response.ErrorMessages;
import it.softwaretest.app.ws.utils.UserProfileUtils;

import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthenticationServiceImpl implements AuthenticationService {

    Dao database;

    @Override
    public UserDto authenticate(String userName, String password) throws AuthenticationException {
        UsersService usersService = new UsersServiceImpl();
        String encryptedPassword = null;
        boolean authenticated = false;

        UserDto storedUser = usersService.getUserByUserName(userName);

        if (storedUser == null)
            throw new AuthenticationException(ErrorMessages.AUTHENTICATION_FAILED.getErrorMessage());


        encryptedPassword = new UserProfileUtils().generateSecurePassword(password, storedUser.getSalt());

        if (encryptedPassword != null && encryptedPassword.equalsIgnoreCase(storedUser.getEncryptedPassword())) {
            if (userName != null && userName.equalsIgnoreCase(storedUser.getEmail())) {
                authenticated = true;
            }
        }

        if (!authenticated)
            throw new AuthenticationException(ErrorMessages.AUTHENTICATION_FAILED.getErrorMessage());

        return storedUser;
    }

    @Override
    public String issueAccessToken(UserDto userProfile) throws AuthenticationException {
        String returnValue = null;
        byte[] encryptedAccessToken = null;

        String newSaltAsPostfix = userProfile.getSalt();
        String accessTokenMaterial = userProfile.getUserId() + newSaltAsPostfix;

        try {
            encryptedAccessToken = new UserProfileUtils().encrypt(userProfile.getEncryptedPassword(), accessTokenMaterial);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(AuthenticationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new AuthenticationException("Failed to issue secure access token");
        }

        String encryptedAccessTokenBase64Encoded = Base64.getEncoder().encodeToString(encryptedAccessToken);

        int tokenLength = encryptedAccessTokenBase64Encoded.length();

        String tokenToSaveToDatabase = encryptedAccessTokenBase64Encoded.substring(0, tokenLength / 2);
        returnValue = encryptedAccessTokenBase64Encoded.substring(tokenLength / 2, tokenLength);

        userProfile.setToken(tokenToSaveToDatabase);
        updateUserProfile(userProfile);

        return returnValue;
    }

    @Override
    public void resetSecurityCredentials(String password, UserDto userProfile) {
        UserProfileUtils userProfileUtils = new UserProfileUtils();

        String salt = userProfileUtils.getSalt(30);
        String securePassword = userProfileUtils.generateSecurePassword(password, salt);

        userProfile.setSalt(salt);
        userProfile.setEncryptedPassword(securePassword);

        updateUserProfile(userProfile);
    }

    private void updateUserProfile(UserDto userProfile) {
        this.database = new MySqlDao();
        try {
            this.database.openConnection();
            this.database.updateUser(userProfile);
        } finally {
            this.database.closeConnection();
        }
    }




}
