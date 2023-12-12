import React, { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../AuthContext";
import "./Register.css";
import "./Login.css";

function Register() {
  const navigate = useNavigate();
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password1, setPassword1] = useState("");
  const [password2, setPassword2] = useState("");
  const { user } = useContext(AuthContext);

  useEffect(() => {
    if (user) navigate("/homepage");
  }, [user]);

  function handleKeyUp(event, action, elementId) {
    if (event.key === "Enter") {
      if (action === "Confirm") {
        registerWithEmailAndPassword(name, email, password1, password2);
      }
      if (action === "Next") {
        document.getElementById(elementId).focus();
      }
    }
  }

  async function registerWithEmailAndPassword(
    name,
    email,
    password1,
    password2
  ) {
    if (password1 !== password2) {
      alert("Passwords don't match");
      return;
    }
    alert("Not implemented yet");
  }

  return (
    <div className={"loginWrapper"}>
      <div id={"Register"} className={"login"}>
        <h1>Регистрација</h1>
        <div id={"inputFields"}>
          <input
            id={"regNameInput"}
            placeholder={"Name"}
            type={"text"}
            value={name}
            onKeyUp={(e) => handleKeyUp(e, "Next", "regEmailInput")}
            onChange={(e) => setName(e.target.value)}
          />
          <input
            id={"regEmailInput"}
            placeholder={"Email"}
            type={"email"}
            value={email}
            onKeyUp={(e) => handleKeyUp(e, "Next", "regPassword1")}
            onChange={(e) => setEmail(e.target.value)}
          />

          <input
            id={"regPassword1"}
            placeholder={"Password"}
            type={"password"}
            value={password1}
            onKeyUp={(e) => handleKeyUp(e, "Next", "regPassword2")}
            onChange={(e) => setPassword1(e.target.value)}
          />
          <input
            id={"regPassword2"}
            placeholder={"Repeat password"}
            type={"password"}
            value={password2}
            onKeyUp={(e) => handleKeyUp(e, "Confirm")}
            onChange={(e) => setPassword2(e.target.value)}
          />
          <button
            className="rec_button accent"
            onClick={() =>
              registerWithEmailAndPassword(name, email, password1, password2)
            }
          >
            Регистрација
          </button>
        </div>
      </div>
    </div>
  );
}

export default Register;
