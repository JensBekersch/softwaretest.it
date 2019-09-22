import {ApiInterface} from '../ApiInterface';

export abstract class AbstractApi implements ApiInterface {

    fetchJson(url: string, options?: any) {
        return fetch(url, Object.assign({
            credentials: 'same-origin'
        }, options))
            .then(this.checkStatus)
            .then((response:any) => {
                return response.text()
                    .then((text:any) => text ? JSON.parse(text) : '')
            });
    }

    private checkStatus(response:any) {
        if(response.status >= 200 && response.status < 400)
            return response;

        const error = new Error(response.statusText);
        error.message = response;

        throw error;
    }

}
