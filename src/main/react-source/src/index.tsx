import * as React from 'react';
import {render} from 'react-dom';
import {Route, BrowserRouter as Router} from 'react-router-dom';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware, compose, combineReducers } from 'redux';
import thunk from 'redux-thunk';

import App from './App';
import authReducer from './Store/Reducers/Authentication';

import 'bootstrap/dist/css/bootstrap.min.css';
import './style.scss';

declare global {
    interface Window {
        __REDUX_DEVTOOLS_EXTENSION_COMPOSE__?: typeof compose;
    }
}

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

const rootReducer = combineReducers({
    authentication: authReducer
});

const store = createStore(rootReducer, composeEnhancers(
    applyMiddleware(thunk)
));

const routing = (
    <Provider store={store}>
      <Router>
          <div>
              <App />
          </div>
      </Router>
    </Provider>
);

render(
    routing,
    document.getElementById('app')
);