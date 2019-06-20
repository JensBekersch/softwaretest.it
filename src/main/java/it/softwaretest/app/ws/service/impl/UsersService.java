package it.softwaretest.app.ws.service.impl;

import it.softwaretest.app.ws.exceptions.CouldNotCreateRecordException;
import it.softwaretest.app.ws.exceptions.CouldNotDeleteRecordException;
import it.softwaretest.app.ws.exceptions.CouldNotUpdateRecordException;
import it.softwaretest.app.ws.exceptions.NoRecordFoundException;
import it.softwaretest.app.ws.io.repository.impl.UserRepository;
import it.softwaretest.app.ws.service.UsersServiceInterface;
import it.softwaretest.app.ws.shared.dto.impl.UserDto;
import it.softwaretest.app.ws.ui.model.response.impl.ErrorMessageDefinitions;
import it.softwaretest.app.ws.utilities.UserProfileCreationAndValidation;


public class UsersService implements UsersServiceInterface {

    private UserRepository userRepository;
    private UserProfileCreationAndValidation userProfileCreationAndValidation;

    public UsersService(UserRepository userRepository, UserProfileCreationAndValidation userProfileCreationAndValidation) {
        this.userRepository = userRepository;
        this.userProfileCreationAndValidation = userProfileCreationAndValidation;
    }

    @Override
    public UserDto createUser(UserDto user) {
        this.userProfileCreationAndValidation.validateRequiredFields(user);

        this.checkIfUserExists(user.getEmail());

        user.setUserId(this.createUserId());

        String salt = this.createSalt();

        String encryptedPassword = this.userProfileCreationAndValidation.generateSecurePassword(user.getPassword(), salt);

        user.setSalt(salt);
        user.setEncryptedPassword(encryptedPassword);

        this.saveUser(user);

        return user;
    }

    private void checkIfUserExists(String userEmail) {
        if (this.getUserByUserName(userEmail) != null)
            throw new CouldNotCreateRecordException(ErrorMessageDefinitions.RECORD_ALREADY_EXISITS.name());
    }

    private String createUserId() {
        return this.userProfileCreationAndValidation.generateUserId(30);
    }

    private String createSalt() {
        return this.userProfileCreationAndValidation.getSalt(30);
    }

    public UserDto getUser(String id) {
        UserDto userDto;

        try {
            this.userRepository.openConnection();
            userDto = this.userRepository.getUser(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new NoRecordFoundException(ErrorMessageDefinitions.NO_RECORD_FOUND.getErrorMessage());
        } finally {
            this.userRepository.closeConnection();
        }

        return userDto;
    }

    @Override
    public UserDto getUserByUserName(String userName) {
        UserDto userDto;

        if (userName == null || userName.isEmpty())
            return null;

        try {
            this.userRepository.openConnection();
            userDto = this.userRepository.getUserByUserName(userName);
        } finally {
            this.userRepository.closeConnection();
        }

        return userDto;
    }

    @Override
    public void updateUserDetails(UserDto userDetails) {
        try {
            this.userRepository.openConnection();
            this.userRepository.updateUser(userDetails);
        } catch (Exception ex) {
            throw new CouldNotUpdateRecordException(ex.getMessage());
        } finally {
            this.userRepository.closeConnection();
        }
    }

    @Override
    public void deleteUser(UserDto userDto) {
        try {
            this.userRepository.openConnection();
            this.userRepository.deleteUser(userDto);
        } catch (Exception ex) {
            throw new CouldNotDeleteRecordException(ex.getMessage());
        } finally {
            this.userRepository.closeConnection();;
        }

        try {
            userDto = getUser(userDto.getUserId());
        } catch (NoRecordFoundException ex) {
            userDto = null;
        }

        if (userDto != null)
            throw new CouldNotDeleteRecordException(ErrorMessageDefinitions.COULD_NOT_DELETE_RECORD.getErrorMessage());
    }

    private UserDto saveUser(UserDto user) {
        UserDto returnValue;

        try {
            this.userRepository.openConnection();
            returnValue = this.userRepository.saveUser(user);
        } finally {
            this.userRepository.closeConnection();
        }

        return returnValue;
    }
}
