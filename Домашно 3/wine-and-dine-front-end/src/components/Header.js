import Navigation from "./Navigation";
import './Header.css';

const Header = () => {
  return (
    <div className="header">
        <span className="title">Wine & Dine</span>
        <Navigation />
    </div>
  );
}

export default Header;