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
            idAviona: 0,
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

    getAvioni = async () => {
        try {
            let response;

            if (this.props.avioni.length === 0) {
                response = await api_axios(
                    "get",
                    `/avion`,
                    "/letovi-servis",
                    null
                );
                this.props.setAvioni(response.data.content);
            }
            if (this.props.avioni.length !== 0) {
                this.setState({
                    idAviona: this.props.avioni[0].id,
                });
            }
        } catch (error) {
            console.log(error.response);
        }
    };

    onSubmit = async () => {
        try {
            const avion = this.props.avioni.find(
                (avion) => avion.id === parseInt(this.state.idAviona)
            );
            let {
                pocetnaDestinacija,
                krajnjaDestinacija,
                cena,
                duzinaLeta,
            } = this.state;

            cena = parseInt(cena);
            duzinaLeta = parseInt(duzinaLeta);

            const noviLet = {
                pocetnaDestinacija,
                krajnjaDestinacija,
                cena,
                duzinaLeta,
                idAviona: avion.id,
            };

            const response = await api_axios(
                "post",
                `/letovi`,
                `/letovi-servis`,
                noviLet
            );
            this.props.addLet(response.data);
            toast.success("Uspešno dodat nov let.");
            this.resetState();
            this.props.toggle();
        } catch (error) {
            console.log(error);
        }
    };

    componentDidMount() {
        this.getAvioni();
    }

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
                            <FormGroup row style={{ width: "100%" }}>
                                <Label for="idAviona" sm={2}>
                                    Id aviona
                                </Label>
                                {this.props.avioni.length !== 0 ? (
                                    <Col sm={10}>
                                        <Input
                                            type="select"
                                            name="idAviona"
                                            id="idAviona"
                                            value={this.state.idAviona}
                                            onChange={this.handleChange}
                                        >
                                            {this.props.avioni.map((avion) => {
                                                return (
                                                    <option key={avion.id}>
                                                        {avion.id}
                                                    </option>
                                                );
                                            })}
                                        </Input>
                                    </Col>
                                ) : (
                                    <Col sm={10}>
                                        <div>
                                            Trenutno ne postoji nijedan avion.
                                        </div>
                                    </Col>
                                )}
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
        addLet: (jedanLet) => dispatch(actions.addLet(jedanLet)),
        setAvioni: (avioni) => dispatch(actions.setAvioni(avioni)),
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(AddVehicleModal);
