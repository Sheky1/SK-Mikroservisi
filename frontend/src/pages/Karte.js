import React from "react";
import { Row, ListGroup, ListGroupItem, Col, Container } from "react-bootstrap";
import { connect } from "react-redux";
import * as actions from "../store/actions/index";
import { api_axios } from "../api/api";
import Karta from "../components/Karta";
import Header from "../components/Header";
import AddKartaModal from "../components/AddKartaModal";
import { FaTicketAlt, FaArrowLeft } from "react-icons/fa";
import { toast } from "react-toastify";
import { Link } from "react-router-dom";

class Karte extends React.Component {
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

    getKarte = async () => {
        try {
            const response = await api_axios(
                "get",
                `/karte/korisnik/${this.props.user.loggedUser.id}?sort=datumKreiranja,desc`,
                "/karte-servis",
                null
            );
            const karte = response.data.content;
            this.props.setKarte(karte);
        } catch (error) {
            console.log(error);
        }
    };

    deleteKarta = async (id) => {
        try {
            await api_axios("delete", `/karte/${id}`, "/karte-servis", null);
            this.props.deleteKarta(id);
            toast.success("Uspešno obrisana karta.");
        } catch (error) {
            console.log(error.response);
        }
    };

    componentDidMount() {
        this.getKarte();
    }

    render() {
        return (
            <>
                <div>
                    <AddKartaModal
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
                                                style={{ marginRight: "20px" }}
                                            >
                                                <span>
                                                    <FaArrowLeft />
                                                </span>
                                                Nazad na letove
                                            </Link>
                                            <button
                                                className="navbar__link"
                                                style={{
                                                    backgroundColor: "white",
                                                    marginRight: "20px",
                                                }}
                                                onClick={this.toggle}
                                            >
                                                <span>
                                                    <FaTicketAlt />
                                                </span>{" "}
                                                Rezervisi kartu
                                            </button>
                                            Pregled karata:
                                        </span>
                                    </ListGroupItem>
                                    {this.props.karte.map((karta) => {
                                        return (
                                            <Karta
                                                key={karta.id}
                                                karta={karta}
                                                deleteKarta={this.deleteKarta}
                                            />
                                        );
                                    })}
                                </ListGroup>
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
        karte: state.karte,
        user: state.user,
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
        setKarte: (karte) => dispatch(actions.setKarte(karte)),
        deleteKarta: (id) => dispatch(actions.deleteKarta(id)),
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(Karte);
