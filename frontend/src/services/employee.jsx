import axios from 'axios'

const api = axios.create(
    {
        baseURL : 'http://localhost:8080/api/employee'
    }
);
const addEmployee = async (employee) => {
    const response = await api.post('/add', employee).catch(error => {
        console.log(error.response);
        return error.response;
    })
    console.log(response);
    return response;
}
const deleteEmployee = async (employee) => {
    const response = await api.delete('/delete/' + employee.employeeId).catch(error => {
        console.log(error.response);
        return error.response;
    })
    console.log(response);
    return response;
}
const updateEmployee = async (employee) => {
    const response = await api.post('/update', employee).catch(error => {
        console.log(error.response);
        return error.response;
    })
    console.log(response);
    return response;
}
const getAllEmployee = async () => {
    const response = await api.get('/get-all');
    console.log(response);
    return response;
}
const getEmployeeById = async  (employeeId) => {
    const response = await api.get('/get/' + employeeId);
    console.log(response);
    return response;
}
export default { getEmployeeById, getAllEmployee, addEmployee, updateEmployee, deleteEmployee }
