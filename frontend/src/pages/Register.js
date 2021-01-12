import React, { Component } from "react";
import { Col, Form, FormGroup, Label, Input } from "reactstrap";
import { Link } from "react-router-dom";
import { api_axios } from "../api/api";

export default class HomeUser extends Component {
    constructor() {
        super();
        this.state = {
            email: "",
            ime: "",
            prezime: "",
            sifra: "",
            brPasosa: "",
        };
    }

    handleChange = (event) => {
        const { name, value } = event.target;

        this.setState({
            [name]: value,
        });
    };

    onSubmit = () => {
        try {
            const headers = {
                "Content-Type": "application/json",
            };
            api_axios(
                "post",
                "/korisnik",
                "/korisnicki-servis",
                this.state,
                headers
            );
            this.props.history.push("/");
        } catch (error) {
            console.log(error);
        }
    };

    render() {
        return (
            <div style={{ marginTop: "50px" }}>
                <h1
                    style={{
                        marginBottom: "30px",
                    }}
                >
                    Registracija korisnika
                </h1>
                <Form>
                    <FormGroup row style={{ width: "100%" }}>
                        <Label for="ime" sm={4}>
                            Ime:
                        </Label>
                        <Col sm={8}>
                            <Input
                                type="text"
                                name="ime"
                                id="ime"
                                value={this.state.ime}
                                onChange={(e) => {
                                    this.handleChange(e);
                                }}
                            />
                        </Col>
                    </FormGroup>
                    <FormGroup row style={{ width: "100%" }}>
                        <Label for="prezime" sm={4}>
                            Prezime:
                        </Label>
                        <Col sm={8}>
                            <Input
                                type="text"
                                name="prezime"
                                id="prezime"
                                value={this.state.prezime}
                                onChange={(e) => {
                                    this.handleChange(e);
                                }}
                            />
                        </Col>
                    </FormGroup>
                    <FormGroup row style={{ width: "100%" }}>
                        <Label for="email" sm={4}>
                            Email:
                        </Label>
                        <Col sm={8}>
                            <Input
                                type="text"
                                name="email"
                                id="email"
                                value={this.state.email}
                                onChange={(e) => {
                                    this.handleChange(e);
                                }}
                            />
                        </Col>
                    </FormGroup>
                    <FormGroup row style={{ width: "100%" }}>
                        <Label for="brPasosa" sm={4}>
                            Broj pasosa:
                        </Label>
                        <Col sm={8}>
                            <Input
                                type="text"
                                name="brPasosa"
                                id="brPasosa"
                                value={this.state.brPasosa}
                                onChange={(e) => {
                                    this.handleChange(e);
                                }}
                            />
                        </Col>
                    </FormGroup>
                    <FormGroup row style={{ width: "100%" }}>
                        <Label for="sifra" sm={4}>
                            Sifra:
                        </Label>
                        <Col sm={8}>
                            <Input
                                type="text"
                                name="sifra"
                                id="sifra"
                                value={this.state.sifra}
                                onChange={(e) => {
                                    this.handleChange(e);
                                }}
                            />
                        </Col>
                    </FormGroup>
                </Form>

                <button
                    className="btn-primary"
                    type="submit"
                    style={{ marginRight: "20px" }}
                    onClick={this.onSubmit}
                >
                    Potvrdi
                </button>
                <Link to="/">Nazad na login</Link>
            </div>
        );
    }
}
