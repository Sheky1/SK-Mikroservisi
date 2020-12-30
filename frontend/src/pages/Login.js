import React from "react";
import { Row, Col, Container } from "react-bootstrap";

class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username: "",
            password: "",
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
        this.props.history.push("/home/");
        // this.props.initiateLogin({
        //     username: this.state.username,
        //     password: this.state.password,
        //     history: this.props.history,
        // });
        // setTimeout(() => {
        //     if (this.props.user.isLogged === false) this.toggleError();
        // }, 1200);
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
                                        name="username"
                                        placeholder="Unesite korisnicko ime"
                                        onChange={this.handleChange}
                                        required
                                    />

                                    <input
                                        type="password"
                                        name="password"
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
                                </form>
                            </Col>
                        </Row>
                    </Container>
                </div>
            </>
        );
    }
}

// const mapStateToProps = (state) => {
//     return {
//         user: state.user,
//     };
// };

// const mapDispatchToProps = (dispatch) => {
//     return {
//         initiateLogin: (user) => dispatch(actions.initiateLogin(user)),
//     };
// };

export default Login;
