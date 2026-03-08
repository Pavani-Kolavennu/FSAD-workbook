import { useState } from 'react'
import LocalUserList from './LocalUserList'
import UserList from './UserList'
import FakePostList from './FakePostList'

const SECTIONS = {
  local: 'local',
  usersApi: 'usersApi',
  fakeApi: 'fakeApi',
}

function Dashboard() {
  const [activeSection, setActiveSection] = useState(SECTIONS.local)

  return (
    <main className="app-container">
      <header className="hero">
        <h1>API Fetching Dashboard</h1>
      </header>

      <nav className="nav-links">
        <button
          type="button"
          className={activeSection === SECTIONS.local ? 'active' : ''}
          onClick={() => setActiveSection(SECTIONS.local)}
        >
        Local Users
        </button>
        <button
          type="button"
          className={activeSection === SECTIONS.usersApi ? 'active' : ''}
          onClick={() => setActiveSection(SECTIONS.usersApi)}
        >
          Users API
        </button>
        <button
          type="button"
          className={activeSection === SECTIONS.fakeApi ? 'active' : ''}
          onClick={() => setActiveSection(SECTIONS.fakeApi)}
        >
          Fake API Posts
        </button>
      </nav>

      {activeSection === SECTIONS.local && <LocalUserList />}
      {activeSection === SECTIONS.usersApi && <UserList />}
      {activeSection === SECTIONS.fakeApi && <FakePostList />}
    </main>
  )
}

export default Dashboard
