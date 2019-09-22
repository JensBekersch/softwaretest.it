import * as actionTypes from './ActionTypes';
import {ActionCreator, Dispatch} from 'redux';
import AuthenticationApi from '../../BaseTemplateBundle/Api/impl/AuthenticationApi';
import {AuthenticationModel} from "../../BaseTemplateBundle/Model/AuthenticationModel";

export const authStart = () => {
    return {
        type: actionTypes.AUTH_START
    };
};

export const authSuccess = (token: string, id: string) => {
    return {
        type: actionTypes.AUTH_SUCCESS,
        token: token,
        id: id
    };
};

export const authFail = (error: any) => {
    return {
        type: actionTypes.AUTH_FAIL,
        error: error
    };
};

export const authLogout = () => {
    return {
        type: actionTypes.AUTH_LOGOUT
    }
}

export const auth: ActionCreator<any> = (email: string, password: string) => {
    return (dispatch: Dispatch) => {
        dispatch(authStart());

        let authenticationModel = new AuthenticationModel(email, password);
        let authentication = new AuthenticationApi();

        authentication.post(authenticationModel)
            .then((data: any) => {
                dispatch(authSuccess(data.token, data.id));
            }).catch(error => {
                dispatch(authFail(error));
            })
    };
};

export const logout: ActionCreator<any> = () => {
    return (dispatch: Dispatch) => {
        dispatch(authLogout());
    }
};