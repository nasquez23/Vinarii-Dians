import "./Map.css";
import { MapContainer, TileLayer, Marker, Popup, useMap } from "react-leaflet";
import "leaflet/dist/leaflet.css";
import React, { useContext, useEffect, useState } from "react";
import { MapContext } from "../MapContext";
import L, { Icon } from "leaflet";
import "leaflet-routing-machine";
import "lrm-graphhopper";

// Бришење на стандардните икони за маркери и поставување на сопствени икони
delete L.Icon.Default.prototype._getIconUrl;
L.Icon.Default.mergeOptions({
  iconRetinaUrl: require("leaflet/dist/images/marker-icon-2x.png"),
  iconUrl: require("leaflet/dist/images/marker-icon.png"),
  shadowUrl: require("leaflet/dist/images/marker-shadow.png"),
});

// Функција за фокусирање на мапата на одредена локација
function Highlight({ mark, map }) {
  if (map === null) return;

  if (mark === null) map.setView([41.609, 21.745], 8);
  else map.setView([mark.latitude, mark.longitude], 13);
}

// Функција за контрола на рутирањето на мапата
function RoutingMachineControl({ waypoints }) {
  const map = useMap();

  useEffect(() => {
    if (!map) return;

    // Креирање и додавање на контрола за рутирање
    const routingControl = L.Routing.control({
      waypoints,
      router: L.Routing.graphHopper("455050f4-c992-4611-94f6-aef40dbe32b8"),
      lineOptions: {
        styles: [
          {
            color: "#3873e0",
          },
        ],
      },
    });
    routingControl.addTo(map);

    return () => {
      map.removeControl(routingControl);
    };
  }, [map, waypoints]);

  return null;
}

// Функција за прикажување маркер на локацијата на корисникот
function LocationMarker() {
  const { position, setPosition } = useContext(MapContext);

  const map = useMap();

  // Логика за приказ на локацијата и поставување на истата во контекстот
  useEffect(() => {
    map.locate().on("locationfound", function (e) {
      setPosition(e.latlng);
      map.flyTo(e.latlng, map.getZoom());
      const radius = e.accuracy;
      const circle = L.circle(e.latlng, radius);
      circle.addTo(map);
    });
  }, [map, setPosition]);

  return position === null ? null : (
    <Marker
      position={position}
      icon={
        new Icon({
          iconUrl: require("../images/marker.png"),
          iconAnchor: [24, 48],
        })
      }
    >
      <Popup>Your location.</Popup>
    </Marker>
  );
}

export default function Map() {
  const { position } = useContext(MapContext);
  const { wineries } = useContext(MapContext);
  const { highlighted, setHighlighted } = useContext(MapContext);
  const { displayRoute } = useContext(MapContext);

  const [map, setMap] = useState(null);

  useEffect(() => {
    // Ако има само една винарија, постави ја како фокус
    if (wineries.length === 1) {
      setHighlighted(wineries[0]);
    } else {
      setHighlighted(null);
    }
  }, [wineries, setHighlighted]);

  return (
    <MapContainer center={[41.609, 21.745]} zoom={8} ref={setMap}>
      <TileLayer
        attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a>'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      />
      {highlighted != null && displayRoute && (
        <RoutingMachineControl
          waypoints={[
            L.latLng(position),
            L.latLng(highlighted.latitude, highlighted.longitude),
          ]}
        />
      )}
      {wineries.map((mar) => (
        <Marker position={[mar.latitude, mar.longitude]} key={mar.id}>
          <Popup>
            <h3>{mar.name}</h3>
            <div>{mar.phone}</div>
            <div>
              {mar.openHours} - {mar.closeHours}
            </div>
          </Popup>
        </Marker>
      ))}

      <Highlight mark={highlighted} map={map} />
      <LocationMarker />
    </MapContainer>
  );
}
