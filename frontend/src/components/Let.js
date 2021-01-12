import React from "react";
import { Button, ListGroupItem } from "reactstrap";
import { useSelector } from "react-redux";

export default function City({ jedanLet, deleteLet }) {
    const user = useSelector((state) => state.user);
    return (
        <>
            <ListGroupItem className="additions-group">
                <span style={{ fontWeight: "bold", marginRight: "10px" }}>
                    Pocetna destinacija: {jedanLet.pocetnaDestinacija} ---
                </span>
                <span style={{ fontWeight: "bold", marginRight: "10px" }}>
                    Krajnja destinacija: {jedanLet.krajnjaDestinacija} ---
                </span>
                <span style={{ fontWeight: "bold", marginRight: "10px" }}>
                    {" "}
                    Duzina leta: {jedanLet.duzinaLeta} ---
                </span>
                <span style={{ fontWeight: "bold", marginRight: "10px" }}>
                    Cena: {jedanLet.cena}
                </span>

                {user.loggedUser.role === "ROLE_ADMIN" ? (
                    <>
                        <Button
                            color="danger"
                            onClick={() => {
                                deleteLet(jedanLet.id);
                            }}
                            className="btn-primary btn-small"
                        >
                            Obriši let
                        </Button>
                    </>
                ) : (
                    <></>
                )}
            </ListGroupItem>
        </>
    );
}
