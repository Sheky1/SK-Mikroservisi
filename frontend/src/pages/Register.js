import React, { Component } from "react";
import { Col, Form, FormGroup, Label, Input } from "reactstrap";
import { Link } from "react-router-dom";

export default class HomeUser extends Component {
    constructor() {
        super();
        this.state = {
            email: "",
            ime: "",
            prezime: "",
            sifra: "",
            pasos: "",
        };
    }

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
                                    this.state.handleChange(e);
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
                                type="number"
                                name="prezime"
                                id="prezime"
                                value={this.state.prezime}
                                onChange={(e) => {
                                    this.state.handleChange(e);
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
                                type="number"
                                name="email"
                                id="email"
                                value={this.state.email}
                                onChange={(e) => {
                                    this.state.handleChange(e);
                                }}
                            />
                        </Col>
                    </FormGroup>
                    <FormGroup row style={{ width: "100%" }}>
                        <Label for="pasos" sm={4}>
                            Broj pasosa:
                        </Label>
                        <Col sm={8}>
                            <Input
                                type="number"
                                name="pasos"
                                id="pasos"
                                value={this.state.pasos}
                                onChange={(e) => {
                                    this.state.handleChange(e);
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
                                type="number"
                                name="sifra"
                                id="sifra"
                                value={this.state.sifra}
                                onChange={(e) => {
                                    this.state.handleChange(e);
                                }}
                            />
                        </Col>
                    </FormGroup>
                </Form>
                <Link to="/">Nazad na login</Link>
            </div>
        );
    }
}
