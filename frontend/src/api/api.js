import axios from "axios";

const headers_default = () => {
    return {
        "Content-Type": "application/json",
        Authorization: `Bearer ${atob(localStorage.token)}`,
        Accept: "application/json",
        "Access-Control-Allow-Origin": "Mahmood",
    };
};

export const api_axios = (
    method,
    query,
    service,
    data,
    headers = headers_default()
) => {
    // console.log(query);
    // console.log(headers)
    return axios({
        method: `${method}`,
        url: `http://localhost:8083${service}/api${query}`,
        data: data,
        headers: headers,
    });
};

export const params_axios = (
    query,
    service,
    params,
    headers = headers_default()
) => {
    // console.log(query);
    return axios.get(`http://localhost:8083${service}/api${query}`, {
        params,
        headers,
    });
};
