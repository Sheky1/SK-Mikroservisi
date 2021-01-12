import * as actionTypes from "../actions/types";

const initialState = [];

const avioniReducer = (state = initialState, action) => {
    switch (action.type) {
        case actionTypes.SET_AVIONI:
            return action.payload;
        case actionTypes.UPDATE_AVION:
            return [
                ...state.filter((avion) => avion.id !== action.payload.id),
                action.payload,
            ];
        case actionTypes.ADD_AVION:
            return [...state, action.payload];
        case actionTypes.DELETE_AVION:
            return [...state.filter((avion) => avion.id !== action.payload)];
        default:
            return state;
    }
};

export default avioniReducer;
