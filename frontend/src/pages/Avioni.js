import React from "react";
import { Row, ListGroup, ListGroupItem, Col, Container } from "react-bootstrap";
import { connect } from "react-redux";
import * as actions from "../store/actions/index";
import { api_axios } from "../api/api";
import Avion from "../components/Avion";
import Header from "../components/Header";
import AddAvionModal from "../components/AddAvionModal";
import { FaPlus, FaArrowLeft, FaTicketAlt } from "react-icons/fa";
import { toast } from "react-toastify";
import { Link } from "react-router-dom";

class Avioni extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            modal: false,
        };
    }

    toggle = () => this.setModal(!this.state.modal);

    setModal = (modal) => {
        this.setState({
            modal,
        });
    };

    getAvioni = async () => {
        try {
            const response = await api_axios(
                "get",
                `/avion`,
                "/letovi-servis",
                null
            );
            const avioni = response.data.content;
            this.props.setAvioni(avioni);
            // console.log(avioni);
        } catch (error) {
            console.log(error);
        }
    };

    deleteAvion = async (id) => {
        try {
            await api_axios("delete", `/avion/${id}`, "/letovi-servis", null);
            this.props.deleteAvion(id);
            toast.success("Uspešno obrisan avion.");
        } catch (error) {
            console.log(error.response.data.error_message);
        }
    };

    componentDidMount() {
        this.getAvioni();
    }

    render() {
        return (
            <>
                <div>
                    <AddAvionModal
                        modal={this.state.modal}
                        toggle={this.toggle}
                    />
                    <Container
                        fluid
                        style={{ paddingLeft: 0, paddingRight: 0 }}
                    >
                        <Row>
                            <Col lg="12" className="px-0">
                                <Header />
                                {this.props.user.loggedUser.role ===
                                "ROLE_ADMIN" ? (
                                    <>
                                        <ListGroup className="additions">
                                            <ListGroupItem
                                                style={{
                                                    backgroundColor: "#4186a6",
                                                    color: "whitesmoke",
                                                }}
                                            >
                                                <span>
                                                    <Link
                                                        to="/home-user/"
                                                        className="navbar__link"
                                                        style={{
                                                            marginRight: "20px",
                                                        }}
                                                    >
                                                        <span>
                                                            <FaArrowLeft />
                                                        </span>
                                                        Nazad na letove
                                                    </Link>
                                                    <button
                                                        className="navbar__link"
                                                        style={{
                                                            backgroundColor:
                                                                "white",
                                                            marginRight: "20px",
                                                        }}
                                                        onClick={this.toggle}
                                                    >
                                                        <span>
                                                            <FaPlus />
                                                        </span>{" "}
                                                        Dodaj avion
                                                    </button>
                                                    Trenutno ponuđeni avioni:
                                                </span>
                                            </ListGroupItem>
                                            {this.props.avioni.map((avion) => {
                                                return (
                                                    <Avion
                                                        key={avion.id}
                                                        avion={avion}
                                                        deleteAvion={
                                                            this.deleteAvion
                                                        }
                                                    />
                                                );
                                            })}
                                        </ListGroup>
                                    </>
                                ) : (
                                    <>
                                        <ListGroup className="additions">
                                            <ListGroupItem
                                                style={{
                                                    backgroundColor: "#4186a6",
                                                    color: "whitesmoke",
                                                }}
                                            >
                                                <span>
                                                    <Link
                                                        to="/home-user/karte"
                                                        className="navbar__link"
                                                        style={{
                                                            marginRight: "20px",
                                                        }}
                                                    >
                                                        <span>
                                                            <FaTicketAlt />
                                                        </span>
                                                        Pregled karata
                                                    </Link>
                                                    <Link
                                                        to="/home-user/"
                                                        className="navbar__link"
                                                        style={{
                                                            marginRight: "20px",
                                                        }}
                                                    >
                                                        <span>
                                                            <FaArrowLeft />
                                                        </span>
                                                        Nazad na letove
                                                    </Link>
                                                    <button
                                                        className="navbar__link"
                                                        style={{
                                                            backgroundColor:
                                                                "white",
                                                            marginRight: "20px",
                                                        }}
                                                        onClick={this.toggle}
                                                    >
                                                        <span>
                                                            <FaPlus />
                                                        </span>{" "}
                                                        Dodaj avion
                                                    </button>
                                                    Trenutno ponuđeni avioni:
                                                </span>
                                            </ListGroupItem>
                                            {this.props.avioni.map((avion) => {
                                                return (
                                                    <Avion
                                                        key={avion.id}
                                                        avion={avion}
                                                        deleteAvion={
                                                            this.deleteAvion
                                                        }
                                                    />
                                                );
                                            })}
                                        </ListGroup>
                                    </>
                                )}
                            </Col>
                        </Row>
                    </Container>
                </div>
            </>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        avioni: state.avioni,
        user: state.user,
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
        setAvioni: (letovi) => dispatch(actions.setAvioni(letovi)),
        deleteAvion: (id) => dispatch(actions.deleteAvion(id)),
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(Avioni);
