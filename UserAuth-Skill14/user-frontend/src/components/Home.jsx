import { useNavigate, Link } from "react-router-dom";

function Home() {
  const storedUser = JSON.parse(localStorage.getItem("authUser") || "null");
  const navigate = useNavigate();

  const logout = () => {
    localStorage.removeItem("authUser");
    localStorage.removeItem("userId");
    sessionStorage.removeItem("authUser");
    sessionStorage.removeItem("userId");
    navigate("/login");
  };

  return (
    <section className="page-card">
      <h2>Home</h2>
      {storedUser?.username ? (
        <>
          <p>
            Signed in as: <strong>{storedUser.username}</strong>
          </p>
          <p>
            <Link to="/profile">View Profile</Link>
          </p>
          <button type="button" onClick={logout}>
            Logout
          </button>
        </>
      ) : (
        <p>
          Signed in as: <strong>User</strong>
        </p>
      )}
    </section>
  );
}

export default Home;