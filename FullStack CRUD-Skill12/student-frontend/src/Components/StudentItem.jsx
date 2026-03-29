const StudentItem = ({ student, onEdit, onDelete }) => {
  return (
    <tr>
      <td>{student.id}</td>
      <td>{student.name}</td>
      <td>{student.email}</td>
      <td>{student.course}</td>
      <td>
        <button onClick={() => onEdit(student)}>Edit</button>
        <button className="danger" onClick={() => onDelete(student.id)}>
          Delete
        </button>
      </td>
    </tr>
  );
};

export default StudentItem;