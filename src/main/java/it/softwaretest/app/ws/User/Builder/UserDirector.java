package it.softwaretest.app.ws.User.Builder;

public class UserDirector {

    private GetUserBuilder userBuilder;

    public void setBuilder(GetUserBuilder userBuilder) {
        this.userBuilder = userBuilder;
    }

    public void buildUser(String id) {
        this.userBuilder.getUserDataAndCopyDtoToUserModel(id);
    }

}
