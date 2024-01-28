import "./Review.css";
import {useEffect, useState} from "react";

export default function Review(props) {
  const [createdBy, setCreatedBy] = useState(null);
  const timestamp = new Date(props.review.timestamp);
  async function fetchUserDto(id) {
    const response = await fetch(
        "http://localhost:3000/api/user/" + id
    );
    const data = await response.json();
    setCreatedBy(data);
    console.log(data)
  }

  useEffect(() => {
    fetchUserDto(props.review.userId);
  }, [props]);

  return (
    // Приказ на оцена и името на корисникот кој ја оценил
    <div id="Review">
      <div>
        <div
          className="horizontal-flex"
          style={{ justifyContent: "space-between" }}
        >
          <h1 style={{ fontWeight: "lighter" }}>
            <b>{props.review.score}</b>/5
          </h1>
          <span>
            {createdBy !== undefined && createdBy !== null &&
              createdBy.name}
          </span>
        </div>

        <h4>
          {timestamp.toLocaleDateString("mk-MK", {
            weekday: "long",
            year: "numeric",
            month: "short",
            day: "numeric",
          })}
        </h4>
      </div>
      <p className="description">{props.review.description}</p>
    </div>
  );
}
