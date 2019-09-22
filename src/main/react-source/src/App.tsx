import React, { Component } from 'react';
import { Route, Switch } from 'react-router-dom';

import Main from './MainBundle/Main/impl/Main';
import User from './UserBundle/User/impl/User';


export default class App extends Component {
    render () {
        return (
            <div>
                <Switch>
                    <Route exact path="/" component={Main} />
                    <Route exact path="/user" component={User} />
                </Switch>
            </div>
        );
    }
}
