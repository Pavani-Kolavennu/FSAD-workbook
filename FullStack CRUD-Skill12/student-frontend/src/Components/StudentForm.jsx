import { useEffect, useState } from "react";

const StudentForm = ({ onSave, selectedStudent, onCancel }) => {
  const [student, setStudent] = useState({
    name: "",
    email: "",
    course: "",
  });

  useEffect(() => {
    if (selectedStudent) {
      setStudent(selectedStudent);
    } else {
      setStudent({ name: "", email: "", course: "" });
    }
  }, [selectedStudent]);

  const handleChange = (e) => {
    setStudent({
      ...student,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSave(student);
    setStudent({ name: "", email: "", course: "" });
  };

  return (
    <form className="form" onSubmit={handleSubmit}>
      <h2>{selectedStudent ? "Update Student" : "Add Student"}</h2>

      <input
        type="text"
        name="name"
        placeholder="Student Name"
        value={student.name}
        onChange={handleChange}
        required
      />

      <input
        type="email"
        name="email"
        placeholder="Email"
        value={student.email}
        onChange={handleChange}
        required
      />

      <input
        type="text"
        name="course"
        placeholder="Course"
        value={student.course}
        onChange={handleChange}
        required
      />

      <button type="submit">
        {selectedStudent ? "Update" : "Save"}
      </button>

      {selectedStudent && (
        <button type="button" className="cancel" onClick={onCancel}>
          Cancel
        </button>
      )}
    </form>
  );
};

export default StudentForm;