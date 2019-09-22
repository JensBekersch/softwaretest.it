import {UserModel} from "../../Model/UserModel";
import {AbstractApi} from "../../../BaseTemplateBundle/Api/impl/AbstractApi";

export class RegisterUserApi extends AbstractApi  {

    post(user: UserModel) {
        return this.fetchJson("./api/users", {
            method: 'POST',
            body: JSON.stringify(user),
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }

}
