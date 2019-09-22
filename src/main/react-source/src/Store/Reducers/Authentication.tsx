import * as actionTypes from '../Actions/ActionTypes';
import {updateObject} from "../utility";

const initialState = {
    token: null,
    userId: null,
    isAuthenticated: false
};

const authSuccess = (state, action) => {
    return updateObject(state, {
        token: action.token,
        userId: action.id,
        isAuthenticated: true
    })
};

const authFail = (state, action) => {
    return updateObject(state, {
        token: null,
        userId: null,
        isAuthenticated: false
    })
};

const authLogout = (state, action) => {
   return updateObject(state, {
       token: null,
       userId: null,
       isAuthenticated: false
   })
};

const reducer = (state = initialState, action) => {
    switch (action.type) {
        case actionTypes.AUTH_SUCCESS:
            return authSuccess(state, action);
        case actionTypes.AUTH_FAIL:
            return authFail(state, action);
        case actionTypes.AUTH_LOGOUT:
            return authLogout(state, action);
        default:
            return state;
    }
};

export default reducer;