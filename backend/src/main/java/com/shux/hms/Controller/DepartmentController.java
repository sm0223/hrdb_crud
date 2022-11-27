package com.shux.hms.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.shux.hms.Bean.Department;
import com.shux.hms.Bean.Employee;
import com.shux.hms.CustomExceptions.CustomException;
import com.shux.hms.DAO.DAOImpl.DepartmentDAOImpl;
import com.shux.hms.DAO.DepartmentDAO;
import com.shux.hms.Util.Result;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.hibernate.JDBCException;

import java.util.List;

@Path("/department")
public class DepartmentController {
    DepartmentDAO deptDAO = new DepartmentDAOImpl();

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDepartment(Department dept) {
        try {
            if (this.deptDAO.addDepartment(dept) == true) {
                Result result = new Result(200,"Success");
                return Response.status(200).entity(result.toString()).build();
            }
            else{
                Result result = new Result(204,"Unsuccessful! Try Again!");
                return Response.status(204).entity(result.toString()).build();
            }
        }
        catch(JDBCException ex) {
            Result result = new Result(403, "Error" , ex.getCause().toString());
            return Response.status(403).entity(result.toString()).build();
        }
        catch (Exception ex) {
            Result result = new Result(500, "Error" , ex.getCause().toString());
            return Response.status(500).entity(result.toString()).build();
        }
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get_all_departments(){
        System.out.printf("Printing All Departments");
        List<Department> depts = deptDAO.getDepartmentList();
        if(depts.isEmpty()){
            Result result = new Result(204,"EmptySet");
            return Response.status(200).entity(result.toString()).build();
        }
        Result result = new Result(200, "\"" + "Success" + "\"", depts.toString());
        return Response.status(200).entity(result.toString()).build();
    }


//    @GET
//    @Path("/get_employees/{dept_id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response get_department(@PathParam("dept_id") int d_id){
//        System.out.println(d_id);
//
//        List<Employee> employees = deptDAO.employeeListInDepartment(d_id);
//
//        return Response.status(200).entity(employees).build();
//    }
}