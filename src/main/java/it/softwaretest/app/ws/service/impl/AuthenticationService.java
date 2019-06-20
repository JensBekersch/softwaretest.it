package it.softwaretest.app.ws.service.impl;

import it.softwaretest.app.ws.exceptions.AuthenticationException;

import it.softwaretest.app.ws.io.repository.impl.UserRepository;
import it.softwaretest.app.ws.service.AuthenticationServiceInterface;
import it.softwaretest.app.ws.shared.dto.impl.UserDto;
import it.softwaretest.app.ws.ui.model.response.impl.ErrorMessageDefinitions;
import it.softwaretest.app.ws.utilities.UserProfileCreationAndValidation;

import javax.inject.Inject;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthenticationService implements AuthenticationServiceInterface {

    private UsersService userService;
    private UserProfileCreationAndValidation userProfileCreationAndValidation;
    private UserRepository userRepository;

    @Inject
    public AuthenticationService(UsersService userService, UserProfileCreationAndValidation userProfileCreationAndValidation, UserRepository userRepository) {
        this.userService = userService;
        this.userProfileCreationAndValidation = userProfileCreationAndValidation;
        this.userRepository = userRepository;
    }

    @Override
    public UserDto authenticate(String userName, String password) throws AuthenticationException {
        UserDto userDto = this.checkIfUserNameExists(userName);

        boolean authenticated = this.authenticateUserOrThrowException(userName, password, userDto);

        if (!authenticated)
            throw new AuthenticationException(ErrorMessageDefinitions.AUTHENTICATION_FAILED.getErrorMessage());

        return userDto;
    }

    private UserDto checkIfUserNameExists(String userName) {
        UserDto userDto = this.userService.getUserByUserName(userName);

        if (userDto == null)
            throw new AuthenticationException(ErrorMessageDefinitions.AUTHENTICATION_FAILED.getErrorMessage());

        return userDto;
    }

    private boolean authenticateUserOrThrowException(String userName, String password, UserDto userDto) {
        String encryptedPassword = new UserProfileCreationAndValidation().generateSecurePassword(password, userDto.getSalt());

        if (encryptedPassword != null && encryptedPassword.equalsIgnoreCase(userDto.getEncryptedPassword())) {
            return this.checkIfUserNameEqualsUserDto(userName, userDto);
        }

        return false;
    }

    private boolean checkIfUserNameEqualsUserDto(String userName, UserDto userDto) {
        if (userName != null && userName.equalsIgnoreCase(userDto.getEmail())) {
            return true;
        }

        return false;
    }

    @Override
    public String issueAccessToken(UserDto userDto) throws AuthenticationException {
        String newSaltAsPostfix = userDto.getSalt();
        String accessTokenMaterial = userDto.getUserId() + newSaltAsPostfix;

        byte[] encryptedAccessToken = createEncryptedAccessToken(userDto, accessTokenMaterial);

        String encryptedAccessTokenBase64Encoded = Base64.getEncoder().encodeToString(encryptedAccessToken);

        int tokenLength = encryptedAccessTokenBase64Encoded.length();

        String tokenToSaveToDatabase = encryptedAccessTokenBase64Encoded.substring(0, tokenLength / 2);

        String returnValue = encryptedAccessTokenBase64Encoded.substring(tokenLength / 2, tokenLength);

        userDto.setToken(tokenToSaveToDatabase);

        updateUserProfile(userDto);

        return returnValue;
    }

    private byte[] createEncryptedAccessToken(UserDto userDto, String accessTokenMaterial) throws AuthenticationException {
        byte[] encryptedAccessToken;

        try {
            encryptedAccessToken = this.userProfileCreationAndValidation.encrypt(userDto.getEncryptedPassword(), accessTokenMaterial);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(AuthenticationService.class.getName()).log(Level.SEVERE, null, ex);
            throw new AuthenticationException("Failed to issue secure access token");
        }

        return encryptedAccessToken;
    }

    @Override
    public void resetSecurityCredentials(String password, UserDto userProfile) {
        UserProfileCreationAndValidation userProfileUtils = new UserProfileCreationAndValidation();

        String salt = userProfileUtils.getSalt(30);
        String securePassword = userProfileUtils.generateSecurePassword(password, salt);

        userProfile.setSalt(salt);
        userProfile.setEncryptedPassword(securePassword);

        updateUserProfile(userProfile);
    }

    private void updateUserProfile(UserDto userProfile) {
        try {
            this.userRepository.openConnection();
            this.userRepository.updateUser(userProfile);
        } finally {
            this.userRepository.closeConnection();
        }
    }

}
