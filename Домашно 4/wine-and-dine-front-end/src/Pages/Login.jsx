import { useContext, useEffect, useState } from "react";
import { AuthContext } from "../AuthContext";
import { useNavigate } from "react-router-dom";
import "./Login.css";

function Login() {
  const {user, setUser} = useContext(AuthContext);
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  // Проверка дали корисникот е најавен, ако е најавен да се пренасочи на почетната страна
  useEffect(() => {
    if (user) {
      navigate("/profile");
    }
  }, [user]);

  // Метод за проверка на притискање на копче
  function handleKeyUp(event, action) {
    if (event.key === "Enter") {
      if (action === "Confirm") {
        logInWithEmailAndPassword(email, password);
      }
      if (action === "Next") {
        document.getElementById("loginPasswordInput").focus();
      }
    }
  }

  // Испраќање на POST барање до серверот за најава на корисник
  async function logInWithEmailAndPassword(email, password) {
    try {
      const response = await fetch('/api/auth/login', {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ email, password }),
      });

      // Доколку барањето е успешно, извести го корисникот и го пренасочи на страната за профил
      if (response.ok) {
        const data = await response.json();
        const token = data.token;
        localStorage.setItem("accessToken", token); // Зачувај го токенот во localStorage
        setUser(token); // Сетирај го корисникот во контекстот
        alert("Успешно се најавивте!");
        navigate("/profile");
      } else {
        alert("Неуспешна најава! Обидете се повторно."); // Во спротивно, извести го корисникот за неуспешна најава
      }
    } catch (error) {
      console.error("Грешка при најава: ", error); // Испечати ја грешката
    }
  }

  return (
    <div className="loginWrapper">
      <div id="login" className="login">
        <h2>Најава</h2>
        <div id="inputFields">
          <input
            placeholder="Емаил"
            type="email"
            value={email}
            onKeyUp={(e) => handleKeyUp(e, "Next")}
            onChange={(e) => setEmail(e.target.value)}
          />
          <input
            id="loginPasswordInput"
            placeholder="Лозинка"
            type="password"
            value={password}
            onKeyUp={(e) => handleKeyUp(e, "Confirm")}
            onChange={(e) => setPassword(e.target.value)}
          />
          <button
            className="login-button"
            onClick={() => logInWithEmailAndPassword(email, password)}
          >
            Најава
          </button>
        </div>
        <div>
          <a href="/register" className="register-button">
            Регистрирај се
          </a>
        </div>
      </div>
    </div>
  );
}

export default Login;
