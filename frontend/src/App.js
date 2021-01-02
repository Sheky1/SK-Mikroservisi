import "./App.css";
import React from "react";

import Login from "./pages/Login";
import HomeUser from "./pages/HomeUser";
import Register from "./pages/Register";

import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

import { Route, Switch, BrowserRouter } from "react-router-dom";

function App() {
    return (
        <div className="App">
            <BrowserRouter>
                <Switch>
                    <Route exact path="/home-user/" component={HomeUser} />

                    <Route exact path="/register/" component={Register} />

                    <Route exact path="/" component={Login} />
                </Switch>
                <ToastContainer autoClose={3000} hideProgressBar />
            </BrowserRouter>
        </div>
    );
}

export default App;
