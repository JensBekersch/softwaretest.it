import React, {Component} from 'react';

import {UserInterface} from '../UserInterface';
import {UserModel} from "../../Model/UserModel";
import {RegisterUserApi} from "../../Api/impl/RegisterUserApi";

import Header from "../../../BaseTemplateBundle/Parts/Header";
import Footer from "../../../BaseTemplateBundle/Parts/Footer";

import '../style/User.css';

export default class User extends Component implements UserInterface {

    private registerUserApi: any;

    private inputFirstName = React.createRef<HTMLInputElement>();
    private inputLastName = React.createRef<HTMLInputElement>();
    private inputEmail = React.createRef<HTMLInputElement>();
    private inputPassword = React.createRef<HTMLInputElement>();

    public state: {
        submitButtonDisabled: boolean,
        submitSuccess: boolean,
        submitError: boolean
    } = {
        submitButtonDisabled: false,
        submitSuccess: false,
        submitError: false
    }

    constructor(props:any) {
        super(props);

        this.handleFormSubmit = this.handleFormSubmit.bind(this);

        this.registerUserApi = new RegisterUserApi();
    }

    componentDidMount() {
        document.title = 'Html2Test - Benutzer registrieren';
    }

    handleFormSubmit(event: any) {
        event.preventDefault();

        this.setState({
            submitButtonDisabled: true
        });

        let firstName = this.inputFirstName.current.value;
        let lastName = this.inputLastName.current.value;
        let email = this.inputEmail.current.value;
        let password = this.inputPassword.current.value;

        let user = new UserModel(firstName, lastName, email, password);

        this.registerUserApi.post(user)
            .then(() => {
                this.setState({
                    submitButtonDisabled: false,
                    submitSuccess: true,
                    submitError: false
                });
            }).catch(() => {
                this.setState({
                    submitButtonDisabled: false,
                    submitError: true,
                    submitSuccess: false
                });
            })
    }

    render() {
        return (
            <div>
                <div className="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
                    <h5 className="my-0 mr-md-auto font-weight-normal">
                        <a href="/" className="heading">
                            Html2Test
                        </a>
                    </h5>
                </div>
                { this.state.submitSuccess && (
                    <div className="alert alert-success" role="alert">
                        Der Account wurde erfolgreich angelegt. Bitte melden Sie sich mit Ihren Zugangsdaten an!
                    </div>
                )}
                { this.state.submitError && (
                    <div className="alert alert-danger" role="alert">
                        Es ist ein Fehler aufgetreten. Bitte wenden Sie sich an den Administrator!
                    </div>
                )}
                <form onSubmit={this.handleFormSubmit} className="form-signin" autoComplete="off">
                    <h1 className="h3 mb-3 font-weight-normal">Als Benutzer registrieren</h1>
                        <label htmlFor="inputFirstName" className="sr-only">Vorname</label>
                            <input ref={this.inputFirstName} type="text" id="inputFirstName" className="form-control" placeholder="Vorname" required autoFocus />
                         <label htmlFor="inputLastName" className="sr-only">Nachname</label>
                            <input ref={this.inputLastName} type="text" id="inputLastName" className="form-control" placeholder="Nachname" required />
                        <label htmlFor="inputEmail" className="sr-only">Email Adresse</label>
                            <input ref={this.inputEmail} type="email" id="inputEmail" className="form-control" placeholder="Email Adresse" required />
                        <label htmlFor="inputPassword" className="sr-only">Password</label>
                            <input ref={this.inputPassword} type="password" id="inputPassword" className="form-control" placeholder="Passwort" required />
                        <button
                            className="btn btn-lg btn-primary btn-block"
                            type="submit"
                            disabled={this.state.submitButtonDisabled}
                        >Jetzt registrieren</button>
                </form>
            </div>
        )
    }

}