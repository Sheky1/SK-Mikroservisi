import { put } from "redux-saga/effects";
import { api_axios } from "../../api/api";
import jwt_decode from "jwt-decode";
import * as actions from "../actions/index";

export function* login(action) {
    const headers = {
        "Content-Type": "application/json",
    };
    try {
        const apiUser = {
            email: action.payload.email,
            sifra: action.payload.sifra,
        };
        const response = yield api_axios(
            "post",
            "/korisnik/login",
            "/korisnicki-servis",
            apiUser,
            headers
        );
        const token = btoa(response.data.token);
        localStorage.setItem("token", token);

        const decoded = jwt_decode(atob(localStorage.token));

        yield put(
            actions.loginUser({
                isLogged: true,
                loggedUser: {
                    ime: decoded.ime,
                    prezime: decoded.prezime,
                    milje: decoded.milje,
                    rank: decoded.rank,
                    role: decoded.role,
                    id: decoded.id,
                    kartice: decoded.kartice,
                    email: decoded.email,
                    brPasosa: decoded.brPasosa,
                },
            })
        );

        // if (response.data.user.role.name === "korisnik") {
        //     action.payload.history.push("/home-korisnik/");
        // } else action.payload.history.push("/home-admin/");
        action.payload.history.push("/home-user/");
    } catch (error) {
        console.log(error);
    }
}

export function* getUser() {
    try {
        const response = yield api_axios("get", "/user", null);
        const loggedUser = response.data.data;
        yield put(
            actions.loginUser({
                isLogged: true,
                loggedUser,
            })
        );
    } catch (error) {
        localStorage.removeItem("token");
        window.location.reload();
    }
}

export function* logout() {
    try {
        yield put(
            actions.loginUser({
                isLogged: false,
                loggedUser: {},
            })
        );
    } catch (error) {
        console.log(error.response);
    }
}
