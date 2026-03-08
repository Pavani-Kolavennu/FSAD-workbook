import { useEffect, useState } from 'react'

function LocalUserList() {
  const [users, setUsers] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')

  useEffect(() => {
    const loadUsers = async () => {
      setLoading(true)
      setError('')

      try {
        const response = await fetch('/users.json')

        if (!response.ok) {
          throw new Error(`Failed to fetch local users (${response.status})`)
        }

        const data = await response.json()
        setUsers(data)
      } catch (err) {
        setError('Unable to load local users')
      } finally {
        setLoading(false)
      }
    }

    loadUsers()
  }, [])

  return (
    <section>
      <h2>Fetching from Local JSON </h2>

      {loading && <p>Loading local users...</p>}
      {!loading && error && <p>{error}</p>}

      {!loading && !error && (
        <div>
          {users.map((user) => (
            <article key={user.id} className="card-item">
              <h3>{user.name}</h3>
              <p>
                <strong>Email:</strong> {user.email}
              </p>
              <p>
                <strong>Phone:</strong> {user.phone}
              </p>
            </article>
          ))}
        </div>
      )}
    </section>
  )
}

export default LocalUserList
