export class  SiteViewModel {

    private _id: string;
    private _name: string;
    private _url: string;
    private _project_id: string;


    get id(): string {
        return this._id;
    }

    set id(value: string) {
        this._id = value;
    }

    get name(): string {
        return this._name;
    }

    set name(value: string) {
        this._name = value;
    }

    get url(): string {
        return this._url;
    }

    set url(value: string) {
        this._url = value;
    }

    get project_id(): string {
        return this._project_id;
    }

    set project_id(value: string) {
        this._project_id = value;
    }

    toJSON() {
        return {
            id: this._id,
            name: this._name,
            url: this._url,
            project_id: this._project_id
        }
    }
}