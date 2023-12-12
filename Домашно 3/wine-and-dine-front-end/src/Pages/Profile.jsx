import "./Profile.css";
import { Navigate } from "react-router-dom";
import { useContext } from "react";
import { AuthContext } from "../AuthContext";

const Profile = () => {
  const { user } = useContext(AuthContext);

  return (
    <div>{user === null ? <Navigate to={"/login"} /> : <div>Profil</div>}</div>
  );
};

export default Profile;
