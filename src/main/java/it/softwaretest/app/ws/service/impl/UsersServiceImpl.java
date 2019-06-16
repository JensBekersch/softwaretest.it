package it.softwaretest.app.ws.service.impl;

import it.softwaretest.app.ws.exceptions.CouldNotCreateRecordException;
import it.softwaretest.app.ws.exceptions.CouldNotDeleteRecordException;
import it.softwaretest.app.ws.exceptions.CouldNotUpdateRecordException;
import it.softwaretest.app.ws.exceptions.NoRecordFoundException;
import it.softwaretest.app.ws.io.dao.Dao;
import it.softwaretest.app.ws.io.dao.impl.MySqlDao;
import it.softwaretest.app.ws.service.UsersService;
import it.softwaretest.app.ws.shared.dto.UserDto;
import it.softwaretest.app.ws.ui.model.response.ErrorMessages;
import it.softwaretest.app.ws.utils.UserProfileUtils;

public class UsersServiceImpl implements UsersService {

    private Dao database;

    UserProfileUtils userProfileUtils = new UserProfileUtils();

    public UsersServiceImpl() {
        this.database = new MySqlDao();
    }

    @Override
    public UserDto createUser(UserDto user) {
        UserDto returnValue = null;

        userProfileUtils.validateRequiredFields(user);

        UserDto existingUser = this.getUserByUserName(user.getEmail());
        if (existingUser != null)
            throw new CouldNotCreateRecordException(ErrorMessages.RECORD_ALREADY_EXISITS.name());

        String userId = userProfileUtils.generateUserId(30);
        user.setUserId(userId);

        String salt = userProfileUtils.getSalt(30);

        String encryptedPassword = userProfileUtils.generateSecurePassword(user.getPassword(), salt);
        user.setSalt(salt);
        user.setEncryptedPassword(encryptedPassword);

        returnValue = this.saveUser(user);

        return returnValue;
    }

    public UserDto getUser(String id) {
        UserDto returnValue = null;

        try {
            this.database.openConnection();
            returnValue = this.database.getUser(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new NoRecordFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        } finally {
            this.database.closeConnection();
        }

        return returnValue;
    }

    @Override
    public UserDto getUserByUserName(String userName) {
        UserDto userDto = null;

        if (userName == null || userName.isEmpty())
            return null;

        try {
            this.database.openConnection();
            userDto = this.database.getUserByUserName(userName);
        } finally {
            this.database.closeConnection();
        }

        return userDto;
    }

    @Override
    public void updateUserDetails(UserDto userDetails) {
        try {
            this.database.openConnection();
            this.database.updateUser(userDetails);
        } catch (Exception ex) {
            throw new CouldNotUpdateRecordException(ex.getMessage());
        } finally {
            this.database.closeConnection();
        }
    }

    @Override
    public void deleteUser(UserDto userDto) {
        try {
            this.database.openConnection();
            this.database.deleteUser(userDto);
        } catch (Exception ex) {
            throw new CouldNotDeleteRecordException(ex.getMessage());
        } finally {
            this.database.closeConnection();;
        }

        try {
            userDto = getUser(userDto.getUserId());
        } catch (NoRecordFoundException ex) {
            userDto = null;
        }

        if (userDto != null)
            throw new CouldNotDeleteRecordException(ErrorMessages.COULD_NOT_DELETE_RECORD.getErrorMessage());
    }

    private UserDto saveUser(UserDto user) {
        UserDto returnValue = null;

        try {
            this.database.openConnection();
            returnValue = this.database.saveUser(user);
        } finally {
            this.database.closeConnection();
        }

        return returnValue;
    }
}
