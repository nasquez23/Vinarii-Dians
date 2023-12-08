import './App.css';
import { Route, Routes } from 'react-router-dom';
import Home from './Pages/Home';
import About from './Pages/About';
import Contact from './Pages/Contact';
import Header from './components/Header';
import Footer from './components/Footer';
import Profile from './Pages/Profile';
import {DataProvider} from "./MapContext";
import {AuthProvider} from "./AuthContext";
import Login from "./Pages/Login";
import Register from "./Pages/Register";


function App() {
  return (
    <div>
        <AuthProvider>
            <Header />
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/about" element={<About />} />
                <Route path="/contact" element={<Contact />} />
                <Route path="/profile" element={<Profile />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
                <Route path="/*" element={<Home/>}/>
            </Routes>
            <Footer />

        </AuthProvider>
    </div>
  );
}

export default App;
