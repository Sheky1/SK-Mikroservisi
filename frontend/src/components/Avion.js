import React from "react";
import { Button, ListGroupItem } from "reactstrap";

export default function City({ avion, deleteAvion }) {
    return (
        <>
            <ListGroupItem className="additions-group">
                <span style={{ fontWeight: "bold", marginRight: "10px" }}>
                    Naziv aviona: {avion.naziv} ---
                </span>
                <span style={{ fontWeight: "bold", marginRight: "10px" }}>
                    {" "}
                    Kapacitet putnika: {avion.kapacitetPutnika} ---
                </span>
                <Button
                    color="danger"
                    onClick={() => {
                        deleteAvion(avion.id);
                    }}
                    className="btn-primary btn-small"
                >
                    Obriši avion
                </Button>
            </ListGroupItem>
        </>
    );
}
