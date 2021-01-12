import * as actionTypes from "../actions/types";

const initialState = [];

const letoviReducer = (state = initialState, action) => {
    switch (action.type) {
        case actionTypes.SET_LETOVI:
            return action.payload;
        case actionTypes.UPDATE_LET:
            return [
                ...state.filter(
                    (jedanLet) => jedanLet.id !== action.payload.id
                ),
                action.payload,
            ];
        case actionTypes.ADD_LET:
            return [...state, action.payload];
        case actionTypes.DELETE_LET:
            return [
                ...state.filter((jedanLet) => jedanLet.id !== action.payload),
            ];
        default:
            return state;
    }
};

export default letoviReducer;
