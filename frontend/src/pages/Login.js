import React from "react";
import { Row, Col, Container } from "react-bootstrap";
import { connect } from "react-redux";
import * as actions from "../store/actions/index";
import { Link } from "react-router-dom";

class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            email: "",
            sifra: "",
        };
    }

    handleChange = (event) => {
        const { name, value } = event.target;

        this.setState({
            [name]: value,
        });
    };

    onLogin = (event) => {
        event.preventDefault();
        this.props.initiateLogin({
            email: this.state.email,
            sifra: this.state.sifra,
            history: this.props.history,
        });
        setTimeout(() => {
            if (this.props.user.isLogged === false)
                console.log("NIJE ULOGOVAN");
        }, 1200);
    };

    render() {
        return (
            <>
                <div className="login">
                    <Container
                        fluid
                        style={{ paddingLeft: 0, paddingRight: 0 }}
                    >
                        <Row className="width-100">
                            <Col lg="12" className="px-0">
                                <form
                                    action=""
                                    className="login-form"
                                    onSubmit={(event) => this.onLogin(event)}
                                >
                                    <h1>Login korisnika</h1>
                                    <div className="login-line"></div>
                                    <h4>Dobrodosli!</h4>

                                    <input
                                        type="text"
                                        name="email"
                                        placeholder="Unesite email"
                                        onChange={this.handleChange}
                                        required
                                    />

                                    <input
                                        type="password"
                                        name="sifra"
                                        placeholder="Unesite sifru"
                                        onChange={this.handleChange}
                                        required
                                    />

                                    <button
                                        className="btn-primary"
                                        type="submit"
                                    >
                                        Potvrdi
                                    </button>
                                    <Link
                                        className="btn-primary"
                                        to="/register/"
                                    >
                                        Registruj se
                                    </Link>
                                </form>
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
        user: state.user,
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
        initiateLogin: (user) => dispatch(actions.initiateLogin(user)),
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(Login);
