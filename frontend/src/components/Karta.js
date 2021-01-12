import React from "react";
import { ListGroupItem } from "reactstrap";

export default function City({ karta, deleteKarta }) {
    return (
        <>
            <ListGroupItem className="additions-group">
                <span style={{ fontWeight: "bold", marginRight: "10px" }}>
                    Id leta: {karta.idLeta} ---
                </span>
                <span style={{ fontWeight: "bold", marginRight: "10px" }}>
                    Cena: {karta.cena}
                </span>
            </ListGroupItem>
        </>
    );
}
