export class SiteElementsModel {

    private _id: number;
    private _elementType: string;
    private _elementId: string;
    private _elementXPath: string;
    private _elementClass: String;

    get id(): number {
        return this._id;
    }

    set id(value: number) {
        this._id = value;
    }

    get elementType(): string {
        return this._elementType;
    }

    set elementType(value: string) {
        this._elementType = value;
    }

    get elementId(): string {
        return this._elementId;
    }

    set elementId(value: string) {
        this._elementId = value;
    }

    get elementXPath(): string {
        return this._elementXPath;
    }

    set elementXPath(value: string) {
        this._elementXPath = value;
    }

    get elementClass(): String {
        return this._elementClass;
    }

    set elementClass(value: String) {
        this._elementClass = value;
    }
}