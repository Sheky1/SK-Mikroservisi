import React from "react";
import { Row, ListGroup, ListGroupItem, Col, Container } from "react-bootstrap";
import { connect } from "react-redux";
import * as actions from "../store/actions/index";
import { api_axios } from "../api/api";
import Let from "../components/Let";
import AddLetModal from "../components/AddLetModal";
import AddKarticaModal from "../components/AddKarticaModal";
import FilterLetoviModal from "../components/FilterLetoviModal";
import { FaPlus, FaPlane, FaTicketAlt, FaCreditCard } from "react-icons/fa";
import { toast } from "react-toastify";
import { Link } from "react-router-dom";
import Header from "../components/Header";

class HomeUser extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            modal: false,
            modalCard: false,
            modalFilter: false,
        };
    }

    toggleFilter = () => this.setModalFilter(!this.state.modalFilter);

    setModalFilter = (modalFilter) => {
        this.setState({
            modalFilter,
        });
    };

    toggle = () => this.setModal(!this.state.modal);

    setModal = (modal) => {
        this.setState({
            modal,
        });
    };

    toggleCard = () => this.setModalCard(!this.state.modalCard);

    setModalCard = (modalCard) => {
        this.setState({
            modalCard,
        });
    };

    getLetovi = async () => {
        try {
            if (this.props.user.loggedUser.role === "ROLE_ADMIN") {
                const response = await api_axios(
                    "get",
                    `/letovi/admin`,
                    "/letovi-servis",
                    null
                );
                const letovi = response.data.content;
                this.props.setLetovi(letovi);
            } else {
                const response = await api_axios(
                    "get",
                    `/letovi`,
                    "/letovi-servis",
                    null
                );
                const letovi = response.data.content;
                this.props.setLetovi(letovi);
            }
        } catch (error) {
            console.log(error);
        }
    };

    deleteLet = async (id) => {
        try {
            await api_axios("delete", `/letovi/${id}`, "/letovi-servis", null);
            this.props.deleteLet(id);
            toast.success("Uspešno obrisan let.");
        } catch (error) {
            console.log(error);
        }
    };

    componentDidMount() {
        this.getLetovi();
    }

    render() {
        return (
            <>
                <div>
                    <AddLetModal
                        modal={this.state.modal}
                        toggle={this.toggle}
                    />
                    <AddKarticaModal
                        modal={this.state.modalCard}
                        toggle={this.toggleCard}
                    />
                    <FilterLetoviModal
                        modal={this.state.modalFilter}
                        toggle={this.toggleFilter}
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
                                                    <button
                                                        className="navbar__link"
                                                        style={{
                                                            backgroundColor:
                                                                "white",
                                                            marginRight: "20px",
                                                        }}
                                                        onClick={
                                                            this.toggleFilter
                                                        }
                                                    >
                                                        <span>
                                                            <FaPlus />
                                                        </span>{" "}
                                                        Filtriraj letove
                                                    </button>
                                                    <Link
                                                        to="/home-user/avioni"
                                                        className="navbar__link"
                                                        style={{
                                                            marginRight: "20px",
                                                        }}
                                                    >
                                                        <span>
                                                            <FaPlane />
                                                        </span>
                                                        Pregled aviona
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
                                                        Dodaj let
                                                    </button>
                                                    Trenutno ponuđeni letovi:
                                                </span>
                                            </ListGroupItem>
                                            {this.props.letovi.map(
                                                (jedanLet) => {
                                                    return (
                                                        <Let
                                                            key={jedanLet.id}
                                                            jedanLet={jedanLet}
                                                            deleteLet={
                                                                this.deleteLet
                                                            }
                                                        />
                                                    );
                                                }
                                            )}
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
                                                    <button
                                                        className="navbar__link"
                                                        style={{
                                                            backgroundColor:
                                                                "white",
                                                            marginRight: "20px",
                                                        }}
                                                        onClick={
                                                            this.toggleFilter
                                                        }
                                                    >
                                                        <span>
                                                            <FaPlus />
                                                        </span>{" "}
                                                        Filtriraj letove
                                                    </button>
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
                                                    <button
                                                        className="navbar__link"
                                                        style={{
                                                            backgroundColor:
                                                                "white",
                                                            marginRight: "20px",
                                                        }}
                                                        onClick={
                                                            this.toggleCard
                                                        }
                                                    >
                                                        <span>
                                                            <FaCreditCard />
                                                        </span>{" "}
                                                        Dodaj karticu
                                                    </button>
                                                    Trenutno ponuđeni letovi:
                                                </span>
                                            </ListGroupItem>
                                            {this.props.letovi.map(
                                                (jedanLet) => {
                                                    // console.log(jedanLet);
                                                    return (
                                                        <Let
                                                            key={jedanLet.id}
                                                            jedanLet={jedanLet}
                                                            deleteLet={
                                                                this.deleteLet
                                                            }
                                                        />
                                                    );
                                                }
                                            )}
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
        letovi: state.letovi,
        user: state.user,
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
        setLetovi: (letovi) => dispatch(actions.setLetovi(letovi)),
        deleteLet: (id) => dispatch(actions.deleteLet(id)),
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(HomeUser);
