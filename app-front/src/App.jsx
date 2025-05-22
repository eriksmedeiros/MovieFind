import './App.css'
import Navbar from './components/Navbar/Navbar';
import { Outlet } from 'react-router-dom';
import { AuthProvider } from './context/AuthContext.jsx'

function App() {

  return (
      <div className="App">
        <AuthProvider>
          <Navbar />
          <Outlet />
        </AuthProvider>
      </div>
  );
}

export default App