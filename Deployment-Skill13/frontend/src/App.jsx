// src/App.jsx
import { useEffect, useState } from "react";
import CLIENT_CONFIG from "./client";

function App() {
  const [message, setMessage] = useState("");

  useEffect(() => {
    fetch(`${CLIENT_CONFIG.API_URL}/hello`)
      .then(res => res.text())
      .then(data => setMessage(data));
  }, []);

  return (
    <div>
      <h1>React + Spring Boot App</h1>
      <p>Message from backend: {message}</p>
    </div>
  );
}

export default App;