import { SET_KARTE, UPDATE_KARTA, ADD_KARTA, DELETE_KARTA } from "./types";

export const setKarte = (karte) => {
    return {
        type: SET_KARTE,
        payload: karte,
    };
};

export const updateKarta = (karta) => {
    return {
        type: UPDATE_KARTA,
        payload: karta,
    };
};

export const addKarta = (karta) => {
    return {
        type: ADD_KARTA,
        payload: karta,
    };
};

export const deleteKarta = (id) => {
    return {
        type: DELETE_KARTA,
        payload: id,
    };
};
