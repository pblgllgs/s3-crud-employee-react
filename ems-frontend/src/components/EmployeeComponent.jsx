import { useEffect, useState } from "react";
import { addEmployee, getEmployee, updateEmployee } from "../services/EmployeeService";
import { useNavigate, useParams } from "react-router-dom";
import Swal from "sweetalert2";

const EmployeeComponent = () => {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");

  const { id } = useParams();

  const [errors, setErrors] = useState({
    firstName: "",
    lastName: "",
    email: "",
  });

  const pageTitle = () => {
    if (id) {
      return <h2 className="text-center">Update Employee</h2>;
    } else {
      return <h2 className="text-center">Add Employee</h2>;
    }
  };

  useEffect(() => {
    if (id) {
      const fetchDataAndSetEmployeeDetails = async (id) => {
        const response = await getEmployee(id);
        setFirstName(response.data.firstName);
        setLastName(response.data.lastName);
        setEmail(response.data.email);
      };
      fetchDataAndSetEmployeeDetails(id);
    }
  }, [id]);

  const navigator = useNavigate();

  const handleSave = (e) => {
    e.preventDefault();
    if (validateForm()) {
      try {
        const employee = {
          firstName,
          lastName,
          email,
        };
        if (id) {
          updateEmployee(id,employee);
          Swal.fire({
            position: "top-end",
            icon: "success",
            title: "Employee updated successfully!!",
            showConfirmButton: false,
            timer: 1500,
          });
          setFirstName("");
          setLastName("");
          setEmail("");
          navigator("/employees");
        }
        addEmployee(employee);
        Swal.fire({
          position: "top-end",
          icon: "success",
          title: "Employee saved successfully!!",
          showConfirmButton: false,
          timer: 1500,
        });
        setFirstName("");
        setLastName("");
        setEmail("");
        navigator("/employees");
      } catch (error) {
        console.log(error);
        Swal.fire({
          position: "top-end",
          icon: "error",
          title: "Error then try save the employee, see logs",
          showConfirmButton: false,
          timer: 1500,
        });
      }
    }
  };

  const validateForm = () => {
    let valid = true;
    const errorsCopy = { ...errors };
    if (firstName.trim()) {
      errorsCopy.firstName = "";
    } else {
      errorsCopy.firstName = "First name is required";
      valid = false;
    }
    if (lastName.trim()) {
      errorsCopy.lastName = "";
    } else {
      errorsCopy.lastName = "Last name is required";
      valid = false;
    }
    if (email.trim()) {
      errorsCopy.email = "";
    } else {
      errorsCopy.email = "Email name is required";
      valid = false;
    }
    setErrors(errorsCopy);
    return valid;
  };

  return (
    <div className="container">
      <br />
      <div className="row">
        <div className="card col-md-6 offset-md-3">
          {pageTitle()}
          <div className="card-body">
            <form>
              <div className="form-group mb-2">
                <label className="form-label" htmlFor="firstName">
                  First name:
                </label>
                <input
                  type="text"
                  placeholder="Enter Employee first name..."
                  name="firstName"
                  value={firstName}
                  className={`form-control ${
                    errors.firstName ? "is-invalid" : ""
                  }`}
                  onChange={(e) => setFirstName(e.target.value)}
                />
                {errors.firstName && (
                  <div className="invalid-feedback">{errors.firstName}</div>
                )}
              </div>
              <div className="form-group mb-2">
                <label className="form-label" htmlFor="lastName">
                  Last name:
                </label>
                <input
                  type="text"
                  placeholder="Enter Employee last name..."
                  name="lastName"
                  value={lastName}
                  className={`form-control ${
                    errors.lastName ? "is-invalid" : ""
                  }`}
                  onChange={(e) => setLastName(e.target.value)}
                />
                {errors.firstName && (
                  <div className="invalid-feedback">{errors.lastName}</div>
                )}
              </div>
              <div className="form-group mb-2">
                <label className="form-label" htmlFor="email">
                  Email:
                </label>
                <input
                  type="email"
                  placeholder="Enter email..."
                  name="email"
                  value={email}
                  className={`form-control ${errors.email ? "is-invalid" : ""}`}
                  onChange={(e) => setEmail(e.target.value)}
                />
                {errors.firstName && (
                  <div className="invalid-feedback">{errors.email}</div>
                )}
              </div>
              <button
                type="submit"
                className="btn btn-primary"
                onClick={handleSave}
              >
                Save
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default EmployeeComponent;
