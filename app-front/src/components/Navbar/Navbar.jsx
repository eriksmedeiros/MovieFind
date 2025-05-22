import { Link } from 'react-router-dom'
import { BiCameraMovie, BiSearchAlt2 } from 'react-icons/bi';
import { useContext } from 'react';
import { AuthContext } from '../../context/AuthContext';

import './Navbar.css'

const Navbar = () => {
  const { isAuthenticated, logout } = useContext(AuthContext);

  return (
    <nav className="navbar">
      <h2>
        <Link to="/">
          <BiCameraMovie /> MovieFind
        </Link>
      </h2>
      <form>
        <input type="text" placeholder="Busque um filme..." />
        <button type="submit">
          <BiSearchAlt2 />
        </button>
      </form>
      <div className="auth-links">
        {isAuthenticated ? (
          <button onClick={logout}>Sair</button>
        ) : (
          <Link to="/login">Entrar</Link>
        )}
      </div>
    </nav>
  );
};

export default Navbar;