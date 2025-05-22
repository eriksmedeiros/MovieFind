import { useState } from 'react';
import authService from '../../services/authService';
import { useNavigate } from 'react-router-dom';

const Register = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleRegister = async (e) => {
    e.preventDefault();
    try {
      await authService.register(email, password);
      navigate('/login');
    } catch (err) {
      alert('Erro ao registrar. Tente novamente.' + err.message);
    }
  };

  return (
    <form onSubmit={handleRegister}>
      <h2>Registrar</h2>
      <input type="email" placeholder="E-mail" onChange={(e) => setEmail(e.target.value)} required />
      <input type="password" placeholder="Senha" onChange={(e) => setPassword(e.target.value)} required />
      <button type="submit">Cadastrar</button>
    </form>
  );
};

export default Register;