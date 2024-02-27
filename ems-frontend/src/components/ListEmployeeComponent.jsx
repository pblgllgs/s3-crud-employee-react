import {useEffect, useState} from "react";
import {deleteEmployee, listEmployees} from "../services/EmployeeService";
import {useNavigate} from "react-router-dom";
import Swal from "sweetalert2";

const ListEmployeeComponent = () => {
    const [employees, setEmployees] = useState([]);
    const navigator = useNavigate();
    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        const response = await listEmployees();
        return setEmployees(response.data);
    };

    const addEmployee = () => {
        navigator("/add-employee");
    };

    const handlerUpdateEmployee = (e) => {
        navigator(`/edit-employee/${e}`);
    };

    const handlerDeleteEmployee = (e) => {
        Swal.fire({
            title: "Are you sure?",
            text: "You won't be able to revert this!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes, delete it!"
        }).then((result) => {
            if (result.isConfirmed) {
                deleteEmployee(e);
                navigator(`/employees`);
                Swal.fire({
                    title: "Deleted!",
                    text: "Your file has been deleted.",
                    icon: "success"
                });
            }
        });
    };

    return (
        <div className="container">
            <h1>Employees: {employees.length}</h1>
            <button className="btn btn-primary mb-2" onClick={addEmployee}>
                Create employee
            </button>
            <table className="table table-striped table-bordered">
                <thead>
                <tr>
                    <th>Employee Id</th>
                    <th>Employee First Name</th>
                    <th>Employee Last Name</th>
                    <th>Employee Email</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {employees.map((employee) => (
                    <tr key={employee.id}>
                        <td>{employee.id}</td>
                        <td>{employee.firstName}</td>
                        <td>{employee.lastName}</td>
                        <td>{employee.email}</td>
                        <td>
                            <button
                                className="btn btn-info"
                                onClick={(e) => handlerUpdateEmployee(employee.id)}
                            >
                                Update
                            </button>
                            <button
                                className="btn btn-danger"
                                onClick={(e) => handlerDeleteEmployee(employee.id)}
                            >
                                Delete
                            </button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default ListEmployeeComponent;
