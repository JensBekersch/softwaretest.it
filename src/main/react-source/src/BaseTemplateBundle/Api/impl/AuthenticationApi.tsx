import {AbstractApi} from "./AbstractApi";
import {AuthenticationModel} from "../../Model/AuthenticationModel";

export default class AuthenticationApi extends AbstractApi {

    post(authentication: AuthenticationModel) {
        return this.fetchJson("./api/authentication", {
            method: 'POST',
            body: JSON.stringify(authentication),
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }

}