import React, { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../AuthContext";
import "./Register.css";
import { jwtDecode } from "jwt-decode";

function Register() {
  const navigate = useNavigate();
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password1, setPassword1] = useState("");
  const [password2, setPassword2] = useState("");
  const { user, setUser } = useContext(AuthContext);

  // Проверка дали корисникот е најавен, ако е најавен да се пренасочи на почетната страна
  useEffect(() => {
    if (user) navigate("/");
  }, [user]);

  // Метод за регистрација на корисник со емаил и лозинка
  async function registerWithEmailAndPassword(
    name,
    email,
    password1,
    password2
  ) {
    // Проверка дали лозинките се совпаѓаат
    if (password1 !== password2) {
      alert("Лозинките не се совпаѓаат!");
      return;
    }

    // Проверка дали сите полиња се пополнети
    if (
      name.trim().length === 0 ||
      email.trim().length === 0 ||
      password1.trim().length === 0 ||
      password2.trim().length === 0
    ) {
      alert("Ве молиме пополнете ги сите полиња!");
      return;
    }

    // Испраќање на POST барање до серверот за регистрација на корисник
    try {
      const response = await fetch("/api/auth/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          firstName: name,
          email: email,
          password: password1,
        }),
      });

      // Доколку барањето е успешно, извести го корисникот и го пренасочи на страната за најава
      if (response.ok) {
        alert("Успешно се регистриравте!");
        const data = await response.json();
        setUser(jwtDecode(data.token)); // Сетирај го корисникот во контекстот
        localStorage.setItem("accessToken", data.token); // Зачувај го токенот во localStorage
        navigate("/login");
      } else {
        alert("Неуспешна регистрација! Обидете се повторно."); // Во спротивно, извести го корисникот за неуспешна регистрација
      }
    } catch (error) {
      console.error("Грешка при регистрација: ", error); // Испечати ја грешката
    }
  }

  // Метод за проверка на притискање на копче
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

  return (
    <div className="register-wrapper">
        <h1>Регистрација</h1>
        <div id="inputFields">
          <input
            id="regNameInput"
            placeholder="Име"
            type="text"
            value={name}
            onKeyUp={(e) => handleKeyUp(e, "Next", "regEmailInput")}
            onChange={(e) => setName(e.target.value)}
          />
          <input
            id="regEmailInput"
            placeholder="Емаил"
            type="email"
            value={email}
            onKeyUp={(e) => handleKeyUp(e, "Next", "regPassword1")}
            onChange={(e) => setEmail(e.target.value)}
          />

          <input
            id="regPassword1"
            placeholder="Лозинка"
            type="password"
            value={password1}
            onKeyUp={(e) => handleKeyUp(e, "Next", "regPassword2")}
            onChange={(e) => setPassword1(e.target.value)}
          />
          <input
            id="regPassword2"
            placeholder="Потврди лозинка"
            type="password"
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
  );
}

export default Register;