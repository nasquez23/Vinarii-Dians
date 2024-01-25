import "./SearchResults.css";
import { useContext, useEffect, useState } from "react";
import { MapContext } from "../MapContext";

function Result(props) {
  const name = props.winery.name;
  const address = props.winery.address;
  const phone = props.winery.phone;
  const site = props.winery.site;
  const openHours = props.winery.openHours;
  const closeHours = props.winery.closeHours;
  const { highlighted, setHighlighted } = useContext(MapContext);
  const [bgColor, setBgColor] = useState("aliceblue");

  // При клик на одредена винарија, се прикажуваат нејзините податоци
  const highlight = () => {
    if (highlighted === props.winery) {
      setHighlighted(null);
    } else setHighlighted(props.winery);
  };

  // При клик на одредена винарија, се менува бојата на нејзиниот приказ
  useEffect(() => {
    if (highlighted === props.winery) {
      setBgColor("#d7cd80");
    } else setBgColor("aliceblue");
  }, [highlighted, props.winery]);

  return (
    <div id={"result"} onClick={highlight} style={{ backgroundColor: bgColor }}>
      <h4>{name}</h4>
      <div>{address}</div>
      <div>
        {phone}
        <a href={String(site)}> {site}</a>
      </div>
      <div>
        {openHours} - {closeHours}
      </div>
    </div>
  );
}

export default function SearchResults() {
  const { wineries } = useContext(MapContext);

  return (
    <div id={"resultsContainer"}>
      {wineries.length === 0
        ? "No results" // Доколку нема резултати, прикажи соодветна порака
        : wineries.map((marker, index) => (
            <Result key={index} winery={marker} />
          ))}
    </div>
  );
}
