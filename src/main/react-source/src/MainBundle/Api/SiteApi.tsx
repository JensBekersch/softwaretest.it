import {AbstractApi} from "../../BaseTemplateBundle/Api/impl/AbstractApi";
import {SiteModel} from "../Model/SiteModel";

export class SiteApi extends AbstractApi {

    post(site: SiteModel, userId: string, token: string) {
        return this.fetchJson("./api/sites/" + userId, {
            method: 'POST',
            body: JSON.stringify(site),
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            }
        });
    }

    get(list: boolean, projectId: string, userId: string, token: string) {
        return this.fetchJson("./api/sites/" + userId + "/" + projectId + "/" + list, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            }
        }).then(response => {
            return response;
        });
    }
}