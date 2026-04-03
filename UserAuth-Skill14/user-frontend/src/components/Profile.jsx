import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Profile() {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const storedUser = JSON.parse(localStorage.getItem("authUser") || "null");

    if (!storedUser?.id) {
      navigate("/login");
      return;
    }

    const fetchProfile = async () => {
      try {
        setLoading(true);
        const res = await axios.get(`http://localhost:8080/user/${storedUser.id}`);

        if (!res.data) {
          setError("User profile was not found.");
          return;
        }

        setUser(res.data);
      } catch {
        setError("Failed to load profile.");
      } finally {
        setLoading(false);
      }
    };

    fetchProfile();
  }, [navigate]);

  if (loading) {
    return (
      <section className="page-card">
        <h2>Profile</h2>
        <p>Loading profile...</p>
      </section>
    );
  }

  return (
    <section className="page-card">
      <h2>Profile</h2>
      {error && <p className="error">{error}</p>}
      {user && (
        <>
          <p>Username: {user.username}</p>
          <p>Email: {user.email}</p>
          <p>User Id: {user.id}</p>
        </>
      )}
    </section>
  );
}

export default Profile;