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

class AddAvionModal extends Component {
    constructor(props) {
        super(props);
        this.state = {
            naziv: "",
            kapacitetPutnika: 0,
        };
    }

    resetState = () => {
        this.setState({
            naziv: "",
            kapacitetPutnika: 0,
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
            let { naziv, kapacitetPutnika } = this.state;

            kapacitetPutnika = parseInt(kapacitetPutnika);

            const noviAvion = {
                naziv,
                kapacitetPutnika,
            };

            const response = await api_axios(
                "post",
                `/avion`,
                `/letovi-servis`,
                noviAvion
            );
            this.props.addAvion(response.data);
            toast.success("Uspešno dodat nov avion.");
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
                        Dodavanje Aviona
                    </ModalHeader>
                    <ModalBody>
                        <Form>
                            <FormGroup row style={{ width: "100%" }}>
                                <Label for="naziv" sm={2}>
                                    Naziv aviona
                                </Label>
                                <Col sm={10}>
                                    <Input
                                        type="text"
                                        name="naziv"
                                        id="naziv"
                                        value={this.state.naziv}
                                        onChange={this.handleChange}
                                    />
                                </Col>
                            </FormGroup>
                            <FormGroup row style={{ width: "100%" }}>
                                <Label for="kapacitetPutnika" sm={2}>
                                    Kapacitet Putnika
                                </Label>
                                <Col sm={10}>
                                    <Input
                                        type="number"
                                        name="kapacitetPutnika"
                                        id="kapacitetPutnika"
                                        value={this.state.kapacitetPutnika}
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

const mapDispatchToProps = (dispatch) => {
    return {
        addAvion: (avion) => dispatch(actions.addAvion(avion)),
    };
};

export default connect(null, mapDispatchToProps)(AddAvionModal);
