import "./Profile.css";
import { Navigate } from "react-router-dom";
import { useContext } from "react";
import { AuthContext } from "../AuthContext";

const Profile = () => {
  const { user } = useContext(AuthContext);
  console.log(user);

  return (
    <div>
      {user === null ? (
        <Navigate to={"/login"} />
      ) : (
        <div className="profile">
          <h2>Профил</h2>
          <div className="profile-info">
            <p>Име: </p>
            <p>Емаил: {user}</p>
          </div>
        </div>
      )}
    </div>
  );
};

export default Profile;
