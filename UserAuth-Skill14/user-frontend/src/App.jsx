import { BrowserRouter as Router, Routes, Route, Navigate, Link } from "react-router-dom";
import Register from "./components/Register";
import Login from "./components/Login";
import Home from "./components/Home";
import Profile from "./components/Profile";
import "./App.css";

const getStoredUser = () => {
  const rawUser = localStorage.getItem("authUser");
  if (!rawUser) {
    return null;
  }

  try {
    return JSON.parse(rawUser);
  } catch {
    return null;
  }
};

const ProtectedRoute = ({ children }) => {
  const storedUser = getStoredUser();
  return storedUser ? children : <Navigate to="/login" replace />;
};

function App() {
  const user = getStoredUser();

  return (
    <Router>
      {user && (
        <nav className="top-nav">
          <div className="nav-links">
            <Link to="/home">Home</Link>
            <Link to="/profile">Profile</Link>
          </div>
        </nav>
      )}
      <Routes>
        <Route path="/" element={<Navigate to={user ? "/home" : "/login"} replace />} />
        <Route path="/register" element={<Register />} />
        <Route path="/login" element={<Login />} />
        <Route
          path="/home"
          element={
            <ProtectedRoute>
              <Home />
            </ProtectedRoute>
          }
        />
        <Route
          path="/profile"
          element={
            <ProtectedRoute>
              <Profile />
            </ProtectedRoute>
          }
        />
      </Routes>
    </Router>
  );
}

export default App;