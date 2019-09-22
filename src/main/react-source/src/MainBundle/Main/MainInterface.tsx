import {ProjectViewModel} from "../Model/ProjectViewModel";
import {SiteViewModel} from "../Model/SiteViewModel";
import {SiteElementsModel} from "../Model/SiteElementsModel";

export interface MainInterface {
    state: {
        loggedIn: boolean,
        projects: ProjectViewModel[],
        showSites: boolean,
        projectId: string,
        sites: SiteViewModel[],
        selectedSiteId: string,
        fetchButtonDisabled: boolean,
        siteElements: SiteElementsModel[]
    }
}