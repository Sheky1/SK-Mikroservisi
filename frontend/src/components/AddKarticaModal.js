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

class AddKarticaModal extends Component {
    constructor(props) {
        super(props);
        this.state = {
            imeVlasnika: "",
            prezimeVlasnika: "",
            brKartice: "",
            sigurnosniBroj: "",
        };
    }

    resetState = () => {
        this.setState({
            imeVlasnika: "",
            prezimeVlasnika: "",
            brKartice: "",
            sigurnosniBroj: "",
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
            let {
                prezimeVlasnika,
                imeVlasnika,
                brKartice,
                sigurnosniBroj,
            } = this.state;

            const novaKartica = {
                idUsera: this.props.user.loggedUser.id,
                prezimeVlasnika,
                imeVlasnika,
                brKartice,
                sigurnosniBroj,
            };

            const response = await api_axios(
                "post",
                `/kartice`,
                `/korisnicki-servis`,
                novaKartica
            );
            console.log(response.data);
            this.props.addKartica(response.data);
            toast.success("Uspešno dodata nova kartica.");
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
                        Dodavanje kartice
                    </ModalHeader>
                    <ModalBody>
                        <Form>
                            <FormGroup row style={{ width: "100%" }}>
                                <Label for="imeVlasnika" sm={2}>
                                    Ime vlasnika
                                </Label>
                                <Col sm={10}>
                                    <Input
                                        type="text"
                                        name="imeVlasnika"
                                        id="imeVlasnika"
                                        value={this.state.imeVlasnika}
                                        onChange={this.handleChange}
                                    />
                                </Col>
                            </FormGroup>
                            <FormGroup row style={{ width: "100%" }}>
                                <Label for="prezimeVlasnika" sm={2}>
                                    Prezime vlasnika
                                </Label>
                                <Col sm={10}>
                                    <Input
                                        type="text"
                                        name="prezimeVlasnika"
                                        id="prezimeVlasnika"
                                        value={this.state.prezimeVlasnika}
                                        onChange={this.handleChange}
                                    />
                                </Col>
                            </FormGroup>
                            <FormGroup row style={{ width: "100%" }}>
                                <Label for="brKartice" sm={2}>
                                    Broj kartice
                                </Label>
                                <Col sm={10}>
                                    <Input
                                        type="text"
                                        name="brKartice"
                                        id="brKartice"
                                        value={this.state.brKartice}
                                        onChange={this.handleChange}
                                    />
                                </Col>
                            </FormGroup>
                            <FormGroup row style={{ width: "100%" }}>
                                <Label for="sigurnosniBroj" sm={2}>
                                    Sigurnosni broj
                                </Label>
                                <Col sm={10}>
                                    <Input
                                        type="text"
                                        name="sigurnosniBroj"
                                        id="sigurnosniBroj"
                                        value={this.state.sigurnosniBroj}
                                        onChange={this.handleChange}
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
        addKartica: (kartica) => dispatch(actions.addKartica(kartica)),
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(AddKarticaModal);
