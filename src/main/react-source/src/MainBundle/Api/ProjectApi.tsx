import {AbstractApi} from "../../BaseTemplateBundle/Api/impl/AbstractApi";
import {ProjectModel} from "../Model/ProjectModel";

export default class ProjectApi extends AbstractApi {

    post(project: ProjectModel, userId: string, token: string) {
        return this.fetchJson("./api/projects/" + userId, {
            method: 'POST',
            body: JSON.stringify(project),
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            }
        });
    }

    get(userId: string, token: string) {
        return this.fetchJson("./api/projects/" + userId, {
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