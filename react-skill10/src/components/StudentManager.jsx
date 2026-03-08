import { useState } from 'react'

function StudentManager() {
  const [students, setStudents] = useState([
    { id: '101', name: 'Pavani', course: 'OOPS' },
    { id: '102', name: 'Sitha', course: 'FSAD' },
    { id: '103', name: 'Ram', course: 'DBMS' },
    { id: '104', name: 'Priya', course: 'JavaScript' },
    { id: '105', name: 'Karthik', course: 'React' },
  ])

  const [newStudent, setNewStudent] = useState({
    id: '',
    name: '',
    course: '',
  })

  const handleInputChange = (event) => {
    const { name, value } = event.target
    setNewStudent((prevStudent) => ({...prevStudent,[name]: value,}))
  }

  const handleAddStudent = () => {
    if (!newStudent.id.trim() || !newStudent.name.trim() || !newStudent.course.trim()) {
      return
    }

    const duplicateId = students.some((student) => student.id === newStudent.id)
    if (duplicateId) {
      return
    }

    setStudents((prevStudents) => [...prevStudents, { ...newStudent }])
    setNewStudent({ id: '', name: '', course: '' })
  }

  const handleDeleteStudent = (studentId) => {
    setStudents((prevStudents) => prevStudents.filter((student) => student.id !== studentId))
  }

  return (
    <div className="student-manager">
      <h1>Student Manager</h1>

      <div className="form-row">
        <input
          type="text"
          name="id"
          placeholder="Enter ID"
          value={newStudent.id}
          onChange={handleInputChange}
        />
        <input
          type="text"
          name="name"
          placeholder="Enter Name"
          value={newStudent.name}
          onChange={handleInputChange}
        />
        <input
          type="text"
          name="course"
          placeholder="Enter Course"
          value={newStudent.course}
          onChange={handleInputChange}
        />
        <button className="add-btn" onClick={handleAddStudent}>
          Add Student
        </button>
      </div>

      {students.length === 0 ? (<p>No students available</p>) : (
        <table className="student-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Course</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {students.map((student) => (
              <tr key={student.id}>
                <td>{student.id}</td>
                <td>{student.name}</td>
                <td>{student.course}</td>
                <td>
                <button className="delete-btn" onClick={() => { handleDeleteStudent(student.id)}}>
                    Delete </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  )
}

export default StudentManager
