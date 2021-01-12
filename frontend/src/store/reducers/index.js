import { combineReducers } from "redux";

import loginReducer from "./loginReducer";
import avioniReducer from "./avioniReducer";
import letoviReducer from "./letoviReducer";
import karteReducer from "./karteReducer";
import karticeReducer from "./karticeReducer";

const rootReducer = combineReducers({
    user: loginReducer,
    avioni: avioniReducer,
    letovi: letoviReducer,
    karte: karteReducer,
    kartice: karticeReducer,
});

export default rootReducer;
