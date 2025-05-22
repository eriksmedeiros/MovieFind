const API_URL = import.meta.env.VITE_API_URL;

const login = async (email, password) => {
  const response = await fetch(`${API_URL}/auth/login`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ email, password }),
  });
  if (!response.ok) throw new Error('Falha no login');

  const data = await response.json();
  return data.token;
};

const register = async (email, password) => {
  const response = await fetch(`${API_URL}/auth/register`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ email, password }),
  });
  if (!response.ok) throw new Error('Falha no registro');
};

const logout = () => {
  localStorage.removeItem('token');
};

const authService = { login, register, logout };
export default authService;