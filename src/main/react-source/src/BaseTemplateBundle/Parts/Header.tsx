import React, {Component} from 'react';
import {connect} from 'react-redux';
import * as actions from '../../Store/Actions/index';
import {AnyAction, Dispatch} from "redux";

class Header extends Component<any> {

    private inputEmail = React.createRef<HTMLInputElement>();
    private inputPassword= React.createRef<HTMLInputElement>();

    constructor(props: any) {
        super(props);

        this.submitHandler = this.submitHandler.bind(this);
        this.logout = this.logout.bind(this);
    }

    submitHandler(event: any) {
        event.preventDefault();

        this.props.onAuth(this.inputEmail.current.value, this.inputPassword.current.value);
    }

    logout() {
        this.props.logout();
    }

    render() {
        return (
            <div className="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
                <h5 className="my-0 mr-md-auto font-weight-normal">
                    <a href="/" className="heading">
                        Html2Test
                    </a>
                </h5>
                <div className="d-flex flex-row-reverse bd-highlight">
                    {!this.props.isAuthenticated && (
                    <form onSubmit={this.submitHandler}>
                        <div className="form-row">
                            <div className="col">
                                <input type="text" ref={this.inputEmail} id="email" className="form-control form-control-sm" placeholder="Email" required />
                            </div>
                            <div className="col">
                                <input type="text" ref={this.inputPassword} id="password" className="form-control form-control-sm" placeholder="Passwort" required />
                            </div>
                            <div className="col">
                                <button type="submit" className="btn btn-primary btn-sm btn-block">Login</button>
                            </div>
                        </div>
                    </form>
                    )}
                    {this.props.isAuthenticated && (
                        <button
                            type="submit"
                            className="btn btn-primary btn-sm btn-block"
                            onClick={this.logout}
                        >Logout</button>
                    )}
                </div>
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

const mapDispatchToProps = (dispatch: Dispatch<AnyAction>) => {
    return {
        onAuth: (email: string, password: string) => dispatch(actions.auth(email, password)),
        logout: () => dispatch(actions.logout())
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(Header);
