import * as actionTypes from "../actions/types";

const initialState = [];

const karteReducer = (state = initialState, action) => {
    switch (action.type) {
        case actionTypes.SET_KARTE:
            return action.payload;
        case actionTypes.UPDATE_KARTA:
            return [
                ...state.filter((karta) => karta.id !== action.payload.id),
                action.payload,
            ];
        case actionTypes.ADD_KARTA:
            return [...state, action.payload];
        case actionTypes.DELETE_KARTA:
            return [...state.filter((karta) => karta.id !== action.payload)];
        default:
            return state;
    }
};

export default karteReducer;
