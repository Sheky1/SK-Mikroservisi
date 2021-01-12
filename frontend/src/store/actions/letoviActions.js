import { SET_LETOVI, UPDATE_LET, ADD_LET, DELETE_LET } from "./types";

export const setLetovi = (letovi) => {
    return {
        type: SET_LETOVI,
        payload: letovi,
    };
};

export const updateLet = (jedanLet) => {
    return {
        type: UPDATE_LET,
        payload: jedanLet,
    };
};

export const addLet = (jedanLet) => {
    return {
        type: ADD_LET,
        payload: jedanLet,
    };
};

export const deleteLet = (id) => {
    return {
        type: DELETE_LET,
        payload: id,
    };
};
