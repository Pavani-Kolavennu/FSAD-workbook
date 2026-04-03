import { useState } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";

function Login() {
  const [data, setData] = useState({ username: "", password: "" });
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleChange = (e) => {
    setData({ ...data, [e.target.name]: e.target.value });
  };

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      setLoading(true);
      setError("");
      const res = await axios.post("http://localhost:8080/login", data);

      if (res.data && res.data.id) {
        localStorage.setItem(
          "authUser",
          JSON.stringify({ id: res.data.id, username: res.data.username })
        );
        navigate("/home");
      } else {
        setError("Invalid credentials. Please try again.");
      }
    } catch {
      setError("Unable to login at the moment.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="auth-page">
      <form className="auth-card" onSubmit={handleLogin}>
        <h2>Welcome Back</h2>

        <input
          name="username"
          value={data.username}
          placeholder="Username"
          onChange={handleChange}
          required
        />
        <input
          name="password"
          value={data.password}
          type="password"
          placeholder="Password"
          onChange={handleChange}
          required
        />

        {error && <p className="error">{error}</p>}
        <button type="submit" disabled={loading}>
          {loading ? "Signing in..." : "Login"}
        </button>

        <p className="switch-link">
          New user? <Link to="/register">Create account</Link>
        </p>
      </form>
    </div>
  );
}

export default Login;