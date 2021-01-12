import React from "react";
import {
    Modal,
    ModalBody,
    ModalFooter,
    ModalHeader,
    Button,
    Col,
    Form,
    FormGroup,
    Label,
    Input,
} from "reactstrap";

import { connect } from "react-redux";
import { Component } from "react";
import { api_axios } from "../api/api";
import * as actions from "../store/actions/index";
import { toast } from "react-toastify";

class AddVehicleModal extends Component {
    constructor(props) {
        super(props);
        this.state = {
            email: this.props.user.loggedUser.email,
            ime: this.props.user.loggedUser.ime,
            prezime: this.props.user.loggedUser.prezime,
            sifra: "",
            brPasosa: this.props.user.loggedUser.brPasosa,
        };
    }

    resetState = () => {
        this.setState({
            email: this.props.user.loggedUser.email,
            ime: this.props.user.loggedUser.ime,
            prezime: this.props.user.loggedUser.prezime,
            sifra: "",
            brPasosa: this.props.user.loggedUser.brPasosa,
        });
    };

    handleChange = (e) => {
        const { name, value } = e.target;

        this.setState({
            [name]: value,
        });
    };

    onSubmit = async () => {
        try {
            console.log(atob(localStorage.token));
            console.log(this.state);
            const response = await api_axios(
                "put",
                `/korisnik/${this.props.user.loggedUser.id}/update`,
                `/korisnicki-servis`,
                this.state
            );
            this.props.updateUser(response.data);
            toast.success("Uspešno izmenje korisnik.");
            this.resetState();
            this.props.toggle();
        } catch (error) {
            console.log(error);
        }
    };

    render() {
        return (
            <>
                <Modal
                    isOpen={this.props.modal}
                    toggle={() => this.props.toggle()}
                    backdrop="static"
                    scrollable={true}
                    size="xl"
                >
                    <ModalHeader toggle={() => this.props.toggle()}>
                        Dodavanje leta
                    </ModalHeader>
                    <ModalBody>
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
                    </ModalBody>
                    <ModalFooter className="modal-footer">
                        <Button
                            className="btn-primary btn-small btn-green"
                            onClick={this.onSubmit}
                            color="success"
                        >
                            Dodaj
                        </Button>{" "}
                        <Button
                            className="btn-primary btn-small btn-red"
                            onClick={() => {
                                this.resetState();
                                this.props.toggle();
                            }}
                            color="danger"
                        >
                            Odustani
                        </Button>
                    </ModalFooter>
                </Modal>
            </>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        user: state.user,
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
        updateUser: (user) => dispatch(actions.updateUser(user)),
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(AddVehicleModal);
