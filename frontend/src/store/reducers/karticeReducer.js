import * as actionTypes from "../actions/types";

const initialState = [];

const karteReducer = (state = initialState, action) => {
    switch (action.type) {
        case actionTypes.SET_KARTICE:
            return action.payload;
        case actionTypes.UPDATE_KARTICA:
            return [
                ...state.filter((kartica) => kartica.id !== action.payload.id),
                action.payload,
            ];
        case actionTypes.ADD_KARTICA:
            return [...state, action.payload];
        case actionTypes.DELETE_KARTICA:
            return [
                ...state.filter((kartica) => kartica.id !== action.payload),
            ];
        default:
            return state;
    }
};

export default karteReducer;
