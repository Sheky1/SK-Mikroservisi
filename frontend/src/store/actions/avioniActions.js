import { SET_AVIONI, UPDATE_AVION, ADD_AVION, DELETE_AVION } from "./types";

export const setAvioni = (avioni) => {
    return {
        type: SET_AVIONI,
        payload: avioni,
    };
};

export const updateAvion = (avion) => {
    return {
        type: UPDATE_AVION,
        payload: avion,
    };
};

export const addAvion = (avion) => {
    return {
        type: ADD_AVION,
        payload: avion,
    };
};

export const deleteAvion = (id) => {
    return {
        type: DELETE_AVION,
        payload: id,
    };
};
