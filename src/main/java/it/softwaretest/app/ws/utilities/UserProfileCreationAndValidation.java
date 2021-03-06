package it.softwaretest.app.ws.utilities;

import it.softwaretest.app.ws.exceptions.MissingRequiredFieldException;
import it.softwaretest.app.ws.shared.dto.impl.UserDto;
import it.softwaretest.app.ws.ui.model.response.impl.ErrorMessageDefinitions;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

public class UserProfileCreationAndValidation {

    private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOBQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final int ITERATIONS = 10000;
    private final int KEY_LENGTH = 256;

    public String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    private String generateRandomString(int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for(int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(returnValue);
    }

    public String generateUserId(int length) {
        return generateRandomString(length);
    }

    public void validateRequiredFields(UserDto userDto) throws MissingRequiredFieldException {
        if (
            userDto.getFirstName() == null ||
            userDto.getFirstName().isEmpty() ||
            userDto.getLastName() == null ||
            userDto.getLastName().isEmpty() ||
            userDto.getEmail() == null ||
            userDto.getEmail().isEmpty() ||
            userDto.getPassword() == null ||
            userDto.getPassword().isEmpty()
        ) {
            throw new MissingRequiredFieldException(
                    ErrorMessageDefinitions.MISSING_REQUIRED_FIELD.getErrorMessage()
            );
        }
    }

    public String getSalt(int length) {
        return generateRandomString(length);
    }

    public String generateSecurePassword(String password, String salt) {
        String securedPassword;

        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());

        securedPassword = Base64.getEncoder().encodeToString(securePassword);

        return securedPassword;
    }

    private byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return secretKeyFactory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new AssertionError("Error while hashing a password" + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    public byte[] encrypt(String securePassword, String accessTokenMaterial) throws InvalidKeySpecException {
        return hash(securePassword.toCharArray(), accessTokenMaterial.getBytes());
    }

}
