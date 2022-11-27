import axios from 'axios'

const api = axios.create(
    {
        baseURL : 'http://localhost:8080/api/department'
    }
);
const getAllDepartments = async () => {
    const response = await api.get('/get');
    console.log(response);
    return response;
}
const getDepartmentById = async  (department) => {
    const response = await api.get('/getByName/{department.dname}');
    console.log(response);
    return  await response.data;
}

const departmentService = { getAllDepartments, getDepartmentById }

export default departmentService