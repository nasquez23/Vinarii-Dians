import "./Review.css";
import { useContext, useEffect, useState } from "react";

export default function Review(props) {

    const timestamp = new Date(props.review.timestamp);


    return (
        <div id={"Review"}>
            <div>
                <h1>{props.review.score}/5</h1>
                <h4>{timestamp.toLocaleDateString("mk-MK", { weekday: 'long', year: 'numeric', month: 'short', day: 'numeric' })}</h4>

            </div>
            <p className={'description'}>{props.review.description}</p>
        </div>
    );
}
