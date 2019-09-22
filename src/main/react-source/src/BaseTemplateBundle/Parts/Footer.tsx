import React, {Component} from 'react';
import {connect} from 'react-redux';

class Footer extends Component<any> {

    constructor(props: any) {
        super(props);
    }

    render() {
        return (
            <div>
                {!this.props.isAuthenticated && (
                    <footer className="footer">
                        <div className="container-fluid d-flex flex-row-reverse bd-highlight">
                            <a className="btn btn-outline-primary btn-sm" href="/user">Registrieren</a>
                        </div>
                    </footer>
                )}
            </div>
        )
    }
}

const mapStateToProps = state => {
    return {
        isAuthenticated: state.authentication.isAuthenticated
    }
};

export default connect(mapStateToProps)(Footer);