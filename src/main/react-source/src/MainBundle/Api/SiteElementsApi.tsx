import {AbstractApi} from "../../BaseTemplateBundle/Api/impl/AbstractApi";
import {SiteModel} from "../Model/SiteModel";
import {CreateSiteElementsModel} from "../Model/CreateSiteElementsModel";

export class SiteElementsApi extends AbstractApi {

    post(siteId: CreateSiteElementsModel, userId: string, token: string) {
        return this.fetchJson("./api/elements/" + userId, {
            method: 'POST',
            body: JSON.stringify(siteId),
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            }
        });
    }

    get(siteId: string, userId: string, token: string) {
        return this.fetchJson("./api/elements/" + userId + "/" + siteId, {
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