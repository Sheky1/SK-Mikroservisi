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
            pocetnaDestinacija: "",
            krajnjaDestinacija: "",
            cena: 0,
            duzinaLeta: 0,
        };
    }

    resetState = () => {
        this.setState({
            pocetnaDestinacija: "",
            krajnjaDestinacija: "",
            cena: 0,
            duzinaLeta: 0,
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
                pocetnaDestinacija,
                krajnjaDestinacija,
                cena,
                duzinaLeta,
            } = this.state;

            cena = parseInt(cena);
            duzinaLeta = parseInt(duzinaLeta);

            const filter = {
                pocetnaDestinacija,
                krajnjaDestinacija,
                cena,
                duzinaLeta,
            };

            console.log(filter);

            const response = await api_axios(
                "post",
                `/letovi/params`,
                `/letovi-servis`,
                filter
            );
            console.log(response.data.content);
            this.props.setLetovi(response.data.content);
            toast.success("Letovi isfiltrirani.");
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
                        Filtriranje leta
                    </ModalHeader>
                    <ModalBody>
                        <Form>
                            <FormGroup row style={{ width: "100%" }}>
                                <Label for="pocetnaDestinacija" sm={2}>
                                    Pocetna Destinacija
                                </Label>
                                <Col sm={10}>
                                    <Input
                                        type="text"
                                        name="pocetnaDestinacija"
                                        id="pocetnaDestinacija"
                                        value={this.state.pocetnaDestinacija}
                                        onChange={this.handleChange}
                                    />
                                </Col>
                            </FormGroup>
                            <FormGroup row style={{ width: "100%" }}>
                                <Label for="krajnjaDestinacija" sm={2}>
                                    Krajnja Destinacija
                                </Label>
                                <Col sm={10}>
                                    <Input
                                        type="text"
                                        name="krajnjaDestinacija"
                                        id="krajnjaDestinacija"
                                        value={this.state.krajnjaDestinacija}
                                        onChange={this.handleChange}
                                    />
                                </Col>
                            </FormGroup>
                            <FormGroup row style={{ width: "100%" }}>
                                <Label for="cena" sm={2}>
                                    Cena leta
                                </Label>
                                <Col sm={10}>
                                    <Input
                                        type="number"
                                        name="cena"
                                        id="cena"
                                        value={this.state.cena}
                                        onChange={this.handleChange}
                                    />
                                </Col>
                            </FormGroup>
                            <FormGroup row style={{ width: "100%" }}>
                                <Label for="duzinaLeta" sm={2}>
                                    Duzina leta
                                </Label>
                                <Col sm={10}>
                                    <Input
                                        type="number"
                                        name="duzinaLeta"
                                        id="duzinaLeta"
                                        value={this.state.duzinaLeta}
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
        avioni: state.avioni,
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
        setLetovi: (letovi) => dispatch(actions.setLetovi(letovi)),
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(AddVehicleModal);
