export class CreateSiteElementsModel {

    private _siteId: string;

    constructor(siteId: string) {
        this._siteId = siteId;
    }

    get siteId(): string {
        return this._siteId;
    }

    set siteId(value: string) {
        this._siteId = value;
    }

    toJSON() {
        return {
            siteId: this._siteId
        }
    }
}
