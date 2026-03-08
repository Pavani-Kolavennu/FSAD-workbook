import { useEffect, useState } from 'react'

function UserList() {
  const [users, setUsers] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')

  useEffect(() => {
    const loadUsers = async () => {
      setLoading(true)
      setError('')

      try {
        const response = await fetch('https://jsonplaceholder.typicode.com/users')

        if (!response.ok) {
          throw new Error(`Failed to fetch users API (${response.status})`)
        }

        const data = await response.json()
        setUsers(data)
      } catch (err) {
        setError('Unable to load users')
      } finally {
        setLoading(false)
      }
    }

    loadUsers()
  }, [])

  return (
    <section>
      <h2>Fetching from JSONPlaceholder</h2>

      {loading && <p>Loading users from API...</p>}
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

export default UserList
