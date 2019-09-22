export class ProjectModel {

    private _name: string;

    constructor(name: string) {
        this._name = name;
    }

    get name(): string {
        return this._name;
    }

    set name(value: string) {
        this._name = value;
    }

    toJSON() {
        return {
            name: this._name
        }
    }
}