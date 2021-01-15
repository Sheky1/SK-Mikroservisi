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
import { FaCreditCard } from "react-icons/fa";
import AddKarticaModal from "./AddKarticaModal";

class AddKartaModal extends Component {
    constructor(props) {
        super(props);
        this.state = {
            idUsera: 0,
            idLeta: 0,
            idKartice: 0,
            modalCard: false,
        };
    }

    resetState = () => {
        this.setState({
            idUsera: 0,
            idLeta: 0,
            idKartice: 0,
        });
        this.getData();
    };

    handleChange = (e) => {
        const { name, value } = e.target;

        this.setState({
            [name]: value,
        });
    };

    toggleCard = () => this.setModalCard(!this.state.modalCard);

    setModalCard = (modalCard) => {
        this.setState({
            modalCard,
        });
    };

    getData = async () => {
        try {
            let response;

            if (this.props.letovi.length === 0) {
                response = await api_axios(
                    "get",
                    `/letovi`,
                    "/letovi-servis",
                    null
                );
                this.props.setLetovi(response.data.content);
            }
            if (this.props.letovi.length !== 0) {
                this.setState({
                    idLeta: this.props.letovi[0].id,
                });
            }

            response = await api_axios(
                "get",
                `/kartice/${this.props.user.loggedUser.id}`,
                "/korisnicki-servis",
                null
            );
            this.props.setKartice(response.data);
            if (this.props.kartice.length !== 0) {
                this.setState({
                    idKartice: this.props.kartice[0].id,
                });
            }
        } catch (error) {
            console.log(error.response);
        }
    };

    onSubmit = async () => {
        try {
            // console.log(this.props.letovi);
            // console.log(this.state);
            // const avion = this.props.letovi.find(
            //     (avion) => avion.id === parseInt(this.state.idLeta)
            // );
            // let {
            //     pocetnaDestinacija,
            //     krajnjaDestinacija,
            //     cena,
            //     duzinaLeta,
            // } = this.state;

            // duzinaLeta = parseInt(duzinaLeta);

            const novaKarta = {
                idUsera: this.props.user.loggedUser.id,
                idLeta: this.state.idLeta,
                idKartice: this.state.idKartice,
            };

            const response = await api_axios(
                "post",
                `/karte`,
                `/karte-servis`,
                novaKarta
            );
            this.props.addKarta(response.data);
            toast.success("Uspešno rezervisana karta.");
            this.resetState();
            this.props.toggle();
        } catch (error) {
            console.log(error.response.data.error_message);
        }
    };

    componentDidMount() {
        this.getData();
    }

    render() {
        return (
            <>
                <AddKarticaModal
                    modal={this.state.modalCard}
                    toggle={this.toggleCard}
                />
                <Modal
                    isOpen={this.props.modal}
                    toggle={() => this.props.toggle()}
                    backdrop="static"
                    scrollable={true}
                    size="xl"
                >
                    <ModalHeader toggle={() => this.props.toggle()}>
                        Rezervisanje karte
                    </ModalHeader>
                    <ModalBody>
                        <Form>
                            <FormGroup row style={{ width: "100%" }}>
                                <Label for="idLeta" sm={2}>
                                    Id Leta
                                </Label>
                                {this.props.letovi.length !== 0 ? (
                                    <Col sm={10}>
                                        <Input
                                            type="select"
                                            name="idLeta"
                                            id="idLeta"
                                            value={this.state.idLeta}
                                            onChange={this.handleChange}
                                        >
                                            {this.props.letovi.map(
                                                (jedanLet) => {
                                                    return (
                                                        <option
                                                            key={jedanLet.id}
                                                        >
                                                            {jedanLet.id}
                                                        </option>
                                                    );
                                                }
                                            )}
                                        </Input>
                                    </Col>
                                ) : (
                                    <Col sm={10}>
                                        <div>
                                            Trenutno ne postoji nijedan let.
                                        </div>
                                    </Col>
                                )}
                            </FormGroup>
                            <FormGroup row style={{ width: "100%" }}>
                                <Label for="idKartice" sm={2}>
                                    Id Kartice
                                </Label>
                                {this.props.kartice.length !== 0 ? (
                                    <Col sm={10}>
                                        <Input
                                            type="select"
                                            name="idKartice"
                                            id="idKartice"
                                            value={this.state.idKartice}
                                            onChange={this.handleChange}
                                        >
                                            {this.props.kartice.map(
                                                (kartica) => {
                                                    return (
                                                        <option
                                                            key={kartica.id}
                                                        >
                                                            {kartica.id}
                                                        </option>
                                                    );
                                                }
                                            )}
                                        </Input>
                                    </Col>
                                ) : (
                                    <Col sm={10}>
                                        <div>
                                            Trenutno ne postoji nijedna kartica.
                                            <button
                                                className="navbar__link"
                                                style={{
                                                    backgroundColor:
                                                        "white",
                                                    marginRight: "20px",
                                                }}
                                                onClick={(e) => {
                                                    e.preventDefault()
                                                    this.toggleCard()
                                                }

                                                    
                                                }
                                            >
                                                <span>
                                                    <FaCreditCard />
                                                </span>{" "}
                                                Dodaj karticu
                                            </button>
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
        letovi: state.letovi,
        kartice: state.kartice,
        user: state.user,
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
        addKarta: (karta) => dispatch(actions.addKarta(karta)),
        setLetovi: (letovi) => dispatch(actions.setLetovi(letovi)),
        setKartice: (kartice) => dispatch(actions.setKartice(kartice)),
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(AddKartaModal);
