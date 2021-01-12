import React, { Component } from "react";
import { Container, Col, Row } from "reactstrap";
import { withRouter } from "react-router-dom";
import { FaSignOutAlt } from "react-icons/fa";
import * as actions from "../store/actions/index";
import EditUserModal from "./EditUserModal";

import { connect } from "react-redux";

class Header extends Component {
    constructor(props) {
        super(props);
        this.state = {
            modal: false,
        };
    }

    logout = () => {
        this.props.logout();
        localStorage.removeItem("token");
        this.props.history.push("/");
    };

    toggle = () => this.setModal(!this.state.modal);

    setModal = (modal) => {
        this.setState({
            modal,
        });
    };

    // componentDidMount() {
    //     if (!localStorage.token) {
    //         return <Redirect to="/" />;
    //     } else {
    //         this.props.getUserData();
    //     }
    // }

    render() {
        return (
            <>
                <header className="header">
                    <EditUserModal
                        modal={this.state.modal}
                        toggle={this.toggle}
                    />
                    <Container fluid>
                        <Row style={{ width: "100%" }}>
                            {this.props.user.loggedUser.role ===
                            "ROLE_ADMIN" ? (
                                <>
                                    <Col xs="6">
                                        <div className="header-right full-height">
                                            Ulogovani ste kao admin
                                        </div>
                                    </Col>
                                    <Col xs="6">
                                        <div className="header-right full-height">
                                            <button
                                                className="btn-primary btn-small"
                                                onClick={this.logout}
                                            >
                                                <span>
                                                    <FaSignOutAlt />
                                                </span>{" "}
                                                Izloguj se
                                            </button>
                                        </div>
                                    </Col>
                                </>
                            ) : (
                                <>
                                    <Col xs="6">
                                        <div className="header-right full-height">
                                            <p>
                                                Korisnik:{" "}
                                                {this.props.user.loggedUser.ime}{" "}
                                                {
                                                    this.props.user.loggedUser
                                                        .prezime
                                                }
                                            </p>
                                            <span>
                                                Rank:{" "}
                                                {
                                                    this.props.user.loggedUser
                                                        .rank
                                                }
                                            </span>
                                            <span>
                                                Milje:{" "}
                                                {
                                                    this.props.user.loggedUser
                                                        .milje
                                                }
                                            </span>
                                        </div>
                                    </Col>
                                    <Col xs="6">
                                        <div className="header-right full-height">
                                            <button
                                                className="btn-primary btn-small"
                                                onClick={this.toggle}
                                            >
                                                <span>
                                                    <FaSignOutAlt />
                                                </span>{" "}
                                                Izmeni nalog
                                            </button>
                                            <button
                                                className="btn-primary btn-small"
                                                onClick={this.logout}
                                            >
                                                <span>
                                                    <FaSignOutAlt />
                                                </span>{" "}
                                                Izloguj se
                                            </button>
                                        </div>
                                    </Col>
                                </>
                            )}
                        </Row>
                    </Container>
                </header>
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
        logout: () => dispatch(actions.logout()),
        getUserData: () => dispatch(actions.getUserData()),
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(withRouter(Header));
