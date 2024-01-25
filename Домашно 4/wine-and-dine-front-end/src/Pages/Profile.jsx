import "./Profile.css";
import { useNavigate } from "react-router-dom";
import { useContext, useEffect, useState } from "react";
import { AuthContext } from "../AuthContext";
import { jwtDecode } from "jwt-decode";

const Profile = () => {
  const { user, setUser } = useContext(AuthContext);
  const navigate = useNavigate();
  const [userData, setUserData] = useState(null);

  // Метод за одјава на корисникот
  const logout = () => {
    localStorage.removeItem("accessToken"); // Избриши го токенот
    setUser(null); // Сетирај го корисникот на null
  };

  // Проверка дали корисникот е најавен, ако не е го пренасочува на страницата за најава
  useEffect(() => {
    if (!user) navigate("/login");
    else {
      setUser(localStorage.getItem("accessToken")); // Сетирај го корисникот со податоците од токенот
      setUserData(jwtDecode(user)); // Сетирај ги податоците за корисникот
    }
  }, [user]);

  return (
    <div className="profile horizontal-flex">
      <div className={"vertical-flex"} style={{ textAlign: "center" }}>
        <h3>Профил</h3>
        <div className="profile-info">
          <h1>{userData && userData.firstName}</h1>
          <p>Емаил: {userData && userData.sub}</p>
        </div>
        <button onClick={logout}>Одјава</button>
      </div>
    </div>
  );
};

export default Profile;
