import React from "react";
import { Row, Col, Container } from "react-bootstrap";
import { connect } from "react-redux";
import * as actions from "../store/actions/index";

class HomeUser extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username: "",
            password: "",
        };
    }

    render() {
        return (
            <>
                <div>
                    <Container
                        fluid
                        style={{ paddingLeft: 0, paddingRight: 0 }}
                    >
                        <Row>
                            <Col lg="12" className="px-0">
                                <h1>Korisnik</h1>
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

export default connect(mapStateToProps, mapDispatchToProps)(HomeUser);
