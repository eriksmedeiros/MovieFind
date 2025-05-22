import { useState, useContext } from 'react';
import authService from '../../services/authService';
import { AuthContext } from '../../context/AuthContext';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const { login } = useContext(AuthContext);

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const token = await authService.login(email, password);
      login(token);
    } catch (err) {
      alert('Falha no login. Verifique suas credenciais.' + err.message);
    }
  };

  return (
    <form onSubmit={handleLogin}>
      <h2>Login</h2>
      <input type="email" placeholder="E-mail" onChange={(e) => setEmail(e.target.value)} required />
      <input type="password" placeholder="Senha" onChange={(e) => setPassword(e.target.value)} required />
      <button type="submit">Entrar</button>
    </form>
  );
};

export default Login;