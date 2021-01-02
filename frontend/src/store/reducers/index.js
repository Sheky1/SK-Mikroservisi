import { combineReducers } from "redux";

import loginReducer from "./loginReducer";
import vehicleSizesReducer from "./vehicleSizesReducer";

const rootReducer = combineReducers({
    user: loginReducer,
    vehicleSizes: vehicleSizesReducer,
});

export default rootReducer;
