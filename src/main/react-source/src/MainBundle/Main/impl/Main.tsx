import React, {Component} from 'react';
import {MainInterface} from '../MainInterface';
import Header from "../../../BaseTemplateBundle/Parts/Header";
import Footer from "../../../BaseTemplateBundle/Parts/Footer";
import {connect} from "react-redux";
import {ProjectModel} from "../../Model/ProjectModel";
import ProjectApi from "../../Api/ProjectApi";
import {ProjectViewModel} from "../../Model/ProjectViewModel";
import {SiteModel} from "../../Model/SiteModel";
import {SiteApi} from "../../Api/SiteApi";
import {SiteViewModel} from "../../Model/SiteViewModel";
import {SiteElementsApi} from "../../Api/SiteElementsApi";
import {CreateSiteElementsModel} from "../../Model/CreateSiteElementsModel";
import {SiteElementsModel} from "../../Model/SiteElementsModel";

class Main extends Component<any> implements MainInterface {

    private inputProject = React.createRef<HTMLInputElement>();
    private inputSiteName = React.createRef<HTMLInputElement>();
    private inputSiteUrl = React.createRef<HTMLInputElement>();
    private buttonFetchElements = React.createRef<HTMLButtonElement>();

    private projectApi: any;
    private siteApi: any;
    private siteElementsApi: any;

    public state: {
        loggedIn: boolean,
        projects: ProjectViewModel[],
        showSites: boolean,
        projectId: string,
        sites: SiteViewModel[],
        selectedSiteId: string,
        fetchButtonDisabled: boolean,
        siteElements: SiteElementsModel[]
    } = {
        loggedIn: false,
        projects: [],
        showSites: false,
        projectId: '',
        sites: [],
        selectedSiteId: '',
        fetchButtonDisabled: false,
        siteElements: []
    };

    constructor(props:any) {
        super(props);

        this.getProjects = this.getProjects.bind(this);
        this.getSites = this.getSites.bind(this);
        this.projectChanged = this.projectChanged.bind(this);
        this.submitHandlerNewSite = this.submitHandlerNewSite.bind(this);
        this.submitHandlerNewProject = this.submitHandlerNewProject.bind(this);
        this.siteChanged = this.siteChanged.bind(this);
        this.submitFetchSite = this.submitFetchSite.bind(this);
        this.showSites = this.showSites.bind(this);

        this.projectApi = new ProjectApi();
        this.siteApi = new SiteApi();
        this.siteElementsApi = new SiteElementsApi();
    }

    componentDidMount() {
        document.title = 'Html2Test';
    }

    getProjects() {
        this.projectApi.get(this.props.userId, this.props.token)
            .then(result => {
                this.setState({
                    projects: result
                });
            });
    }

    getSites(projectId: string) {
        this.siteApi.get(true, projectId, this.props.userId, this.props.token)
            .then(result => {
                this.setState({
                    sites: result
                })
            });
    }

    projectChanged(value) {
        if (value !== "choose" && value !== "") {
            this.setState({
                showSites: true,
                projectId: value
            })
        } else if (value === "choose") {
            this.setState({
                showSites: false,
                projectId: null
            })
        }
        this.getSites(value);
    }

    submitHandlerNewProject(event) {
        event.preventDefault();

        let project = new ProjectModel(this.inputProject.current.value);

        this.projectApi.post(project, this.props.userId, this.props.token)
            .then(() => {
                this.inputProject.current.value = "";
                this.getProjects();
            }).catch(() => {
                console.log("error");
            });
    }

    submitHandlerNewSite(event) {
        event.preventDefault();

        if(this.validURL(this.inputSiteUrl.current.value)) {
            let site = new SiteModel(this.inputSiteName.current.value, this.inputSiteUrl.current.value, this.state.projectId);

            this.siteApi.post(site, this.props.userId, this.props.token)
                .then(() => {
                    this.inputSiteName.current.value = "";
                    this.inputSiteUrl.current.value = "";
                    this.getSites(this.state.projectId);
                }).catch(() => {
                    console.log("error");
                });
        } else {
            console.log("Url not valid!");
        }
    }

    siteChanged(value: string) {
        this.setState({
            selectedSiteId: value
        });
    }

    submitFetchSite() {
        this.setState({
            fetchButtonDisabled: true
        })
        let siteId = new CreateSiteElementsModel(this.state.selectedSiteId);
        this.siteElementsApi.post(siteId, this.props.userId, this.props.token)
            .then(() => {
                this.setState({
                    fetchButtonDisabled: false
                })
            });
    }

    showSites() {
        this.siteElementsApi.get(this.state.selectedSiteId, this.props.userId, this.props.token)
            .then((data) => {
                this.setState({
                    siteElements: data.siteElements.singleDomElements
                });
            });
    }

    /**
     * Quelle: https://stackoverflow.com/questions/5717093/check-if-a-javascript-string-is-a-url
     */
    validURL(str) {
        let pattern = new RegExp('^(https?:\\/\\/)?'+ // protocol
            '((([a-z\\d]([a-z\\d-]*[a-z\\d])*)\\.?)+[a-z]{2,}|'+ // domain name
            '((\\d{1,3}\\.){3}\\d{1,3}))'+ // OR ip (v4) address
            '(\\:\\d+)?(\\/[-a-z\\d%_.~+]*)*'+ // port and path
            '(\\?[;&a-z\\d%_.~+=-]*)?'+ // query string
            '(\\#[-a-z\\d_]*)?$','i'); // fragment locator
        return pattern.test(str);
    }

    render() {
        if (this.state.loggedIn === false && this.props.isAuthenticated === true) {
            this.setState({
                loggedIn: true
            });
            this.getProjects();
        }
        return (
            <div>
                <Header />
                <div className="container-fluid">
                    {!this.props.isAuthenticated && (
                        <h3>Bitte melden Sie sich an!</h3>
                    )}
                    {this.props.isAuthenticated && (
                        <div className="row">
                            <div className="col-2">
                                    <form onSubmit={this.submitHandlerNewProject}>
                                        <div className="form-group">
                                            <label htmlFor="newProject">Neues Projekt</label>
                                                <input ref={this.inputProject} type="text" id="newProject" className="form-control form-control-sm" placeholder="Projektname" required />
                                                <button className="btn btn-sm btn-primary btn-block" type="submit">anlegen</button>
                                        </div>
                                    </form>
                                    <select className="form-control form-control-sm" onChange={event => this.projectChanged(event.target.value)}>
                                        <option value="choose">Bitte wählen</option>
                                        {this.state.projects.map((value) =>
                                            <option value={value.id}>{value.name}</option>
                                        )}
                                    </select>
                                {this.state.showSites && (
                                    <div>
                                        <form onSubmit={this.submitHandlerNewSite}>
                                            <div className="form-group mt-4">
                                                <label htmlFor="newSite">Neue Seite anlegen</label>
                                                <input ref={this.inputSiteName} type="text" id="newSite" className="form-control form-control-sm" placeholder="Seite" required />
                                                <input ref={this.inputSiteUrl} type="text" id="newSiteUrl" className="form-control form-control-sm" placeholder="Url" required />
                                                <button className="btn btn-sm btn-primary btn-block" type="submit">anlegen</button>
                                            </div>
                                        </form>
                                        <select className="form-control form-control-sm" onChange={event => this.siteChanged(event.target.value)}>
                                            <option value="choose">Bitte wählen</option>
                                            {this.state.sites.map(value =>
                                                <option value={value.id}>{value.name}</option>
                                            )}
                                        </select>
                                        <button
                                            className="btn btn-sm btn-primary btn-block"
                                            type="button"
                                            onClick={this.submitFetchSite}
                                            disabled={this.state.fetchButtonDisabled}
                                        >Seitenelemente abrufen</button>
                                        <button
                                            className="btn btn-sm btn-primary btn-block"
                                            type="button"
                                            onClick={this.showSites}
                                        >Seitenelemente anzeigen</button>
                                    </div>
                                )}
                            </div>
                            <div className="col-10">
                                <table className="table table-striped">
                                    <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Element Typ</th>
                                            <th scope="col">Code</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    {this.state.siteElements.map( value => {
                                        let seleniumString: string;
                                        if (value.elementId !== '') {
                                            seleniumString = 'driver.find_element_by_id("'+value.elementId+'")';
                                        } else if (value.elementId === '' && value.elementXPath !== '') {
                                            seleniumString = 'driver.find_element_by_xpath("'+value.elementXPath+'")';
                                        } else if (value.elementId === '' && value.elementXPath === '' && value.elementClass !== '') {
                                            seleniumString = 'driver.find_element_by_css_selector("'+value.elementClass+'")';
                                        } else {
                                            seleniumString = 'Element besitzt keinen gültigen Selektor.';
                                        }
                                        return (
                                            <tr>
                                                <th scope="row">{value.id}</th>
                                                <td>{value.elementType}</td>
                                                <td>{seleniumString}</td>
                                            </tr>
                                        )
                                    })}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    )}
                </div>
                <Footer />
            </div>
        )
    }

}

const mapStateToProps = state => {
    return {
        token: state.authentication.token,
        userId: state.authentication.userId,
        isAuthenticated: state.authentication.isAuthenticated
    }
};

export default connect(mapStateToProps)(Main);