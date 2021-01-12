import {
    SET_KARTICE,
    UPDATE_KARTICA,
    ADD_KARTICA,
    DELETE_KARTICA,
} from "./types";

export const setKartice = (kartice) => {
    return {
        type: SET_KARTICE,
        payload: kartice,
    };
};

export const updateKartica = (kartica) => {
    return {
        type: UPDATE_KARTICA,
        payload: kartica,
    };
};

export const addKartica = (kartica) => {
    return {
        type: ADD_KARTICA,
        payload: kartica,
    };
};

export const deleteKartica = (id) => {
    return {
        type: DELETE_KARTICA,
        payload: id,
    };
};
