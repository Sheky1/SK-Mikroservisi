import {
    LOGIN_USER,
    LOGOUT_USER,
    INITIATE_LOGIN,
    GET_USER,
    UPDATE_USER,
} from "./types";

export const loginUser = (user) => {
    return {
        type: LOGIN_USER,
        payload: user,
    };
};

export const initiateLogin = (user) => {
    return {
        type: INITIATE_LOGIN,
        payload: user,
    };
};

export const updateUser = (user) => {
    return {
        type: UPDATE_USER,
        payload: user,
    };
};

export const getUserData = () => {
    return {
        type: GET_USER,
    };
};

export const logout = () => {
    return {
        type: LOGOUT_USER,
    };
};
