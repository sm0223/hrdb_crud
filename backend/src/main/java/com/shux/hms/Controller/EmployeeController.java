package com.shux.hms.Controller;


import com.shux.hms.Bean.Employee;
import com.shux.hms.DAO.DAOImpl.DepartmentDAOImpl;
import com.shux.hms.DAO.DAOImpl.EmployeeDAOImpl;
import com.shux.hms.DAO.DepartmentDAO;
import com.shux.hms.DAO.EmployeeDAO;
import com.shux.hms.Util.Result;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.hibernate.JDBCException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Path("/employee")
public class EmployeeController {
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEmployees(Employee emp) {
        System.out.println("image : " + emp.getImageData());
        System.out.println("emp" +  emp.toString());


        if(emp.getEmpDepartment() !=null){
           int count  = this.employeeDAO.getEmployeeListbyDept(emp.getEmpDepartment());
           System.out.println("count"+count);
           System.out.println("count"+emp.getEmpDepartment().getCapacity());
           if(count >= emp.getEmpDepartment().getCapacity() - 1 ){

               Result result = new Result(208,"\"" + "Department Full" + "\"" );
               return Response.status(208).entity(result.toString()).build();
           }
        }

        if(emp.getImageData() != null ) {
            try {
                FileWriter Writer= new FileWriter(emp.getPhotographPath(), false);
                Writer.write(emp.getImageData());
                Writer.close();
                System.out.println("Successfully written.");
            }
            catch (IOException ex) {
                Result result = new Result(422, "\"" +"Error" +"\"" ,"\"" +"Error in Uploading Image" +"\"");
                return Response.status(422).entity(result.toString()).build();
            }
        }
        try {
            if (this.employeeDAO.addEmployee(emp) == true) {
                Result result = new Result(200,"\"" + "Success" + "\"");
                return Response.status(200).entity(result.toString()).build();
            }
            else{

                Result result = new Result(204,"\"" + "Unsuccessful" + "\"" );
                return Response.status(200).entity(result.toString()).build();
            }
        }
        catch(JDBCException ex) {
            Result result = new Result(422, "\"" +"Error" +"\"" ,"\"" +ex.getCause().toString() +"\"");
            return Response.status(422).entity(result.toString()).build();
        }
        catch (Exception ex) {
            Result result = new Result(500, "\"" +"Error" +"\"" , "\"" +ex.getCause().toString() +"\"");
            return Response.status(500).entity(result.toString()).build();
        }
    }
    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmployees(Employee emp) {
//        System.out.println(emp.toString());
        System.out.println("image : " + emp.getImageData());
        if(emp.getImageData() != null ) {
            try {
                FileWriter Writer= new FileWriter(emp.getPhotographPath(), false);
                Writer.write(emp.getImageData());
                Writer.close();
                System.out.println("Successfully written");
            }
            catch (IOException ex) {
                Result result = new Result(422, "\"" +"Error" +"\"" ,"\"" +ex.getCause().toString() +"\"");
                return Response.status(422).entity(result.toString()).build();
            }
        }
        try {
            if (this.employeeDAO.updateEmployee(emp)) {
                Result result = new Result(200,"\"" + "Success" + "\"");
                return Response.status(200).entity(result.toString()).build();
            }
            else{

                Result result = new Result(204,"\"" + "Unsuccessful" + "\"" );
                return Response.status(200).entity(result.toString()).build();
            }
        }
        catch(JDBCException ex) {
            Result result = new Result(422, "\"" +"Error" +"\"" ,"\"" +ex.getCause().toString() +"\"");
            return Response.status(422).entity(result.toString()).build();
        }
        catch (Exception ex) {
            Result result = new Result(500, "\"" +"Error" +"\"" , "\"" +ex.getCause().toString() +"\"");
            return Response.status(500).entity(result.toString()).build();
        }
    }

    @GET
    @Path("/get-all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees(){
        try {
            List<Employee> empList = this.employeeDAO.getEmployeeList();
            if(empList == null){
                Result result = new Result(204, "\"" + "Success" + "\"", empList.toString());
                return Response.status(200).entity(result.toString()).build();
            }
            for (int i = 0; i < empList.size(); i++) {
                if(empList.get(i).getPhotographPath() != null) {
                    String data = null;
                    try {
                        File Obj = new File(empList.get(i).getPhotographPath());
                        Scanner Reader = new Scanner(Obj);
                        while (Reader.hasNextLine()) {
                            data = Reader.nextLine();
                            System.out.println(data);
                        }
                        Reader.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("An error has occurred.");
                        e.printStackTrace();
                    }
                    empList.get(i).setImageData(data);
                }
            }
            Result result = new Result(200, "\"" + "Success" + "\"", empList.toString());
            return Response.status(200).entity(result.toString()).build();
        }
        catch (Exception ex) {
            Result result = new Result(500, "\"" +"Error" +"\"" , "\""  + ex.getCause().toString() + "\"" );
            return Response.status(500).entity(result.toString()).build();
        }
    }

    @GET
    @Path("/get/{empId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response get_employee(@PathParam("empId") int id){

        try {
            Employee emp = this.employeeDAO.getEmployeeByID(id);
            if (emp == null) { //NO Content
                Result result = new Result(204, "\"" + "Success" + "\"", emp.toString());
                return Response.status(200).entity(result.toString()).build();
            }
            else{//
                Result result = new Result(200, "\"" + "Unsuccessful" + "\"" , "\"Employee Not Found\"");
                return Response.status(200).entity(result.toString()).build();

            }
        }
        catch (Exception ex) {
            Result result = new Result(500, "\"" +"Error" +"\"", "\"" + ex.getCause().toString() + "\"" );
            return Response.status(500).entity(result.toString()).build();
        }

    }

    @DELETE
    @Path("/delete/{empId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response delete(@PathParam("empId") int id){
        try {
            if (employeeDAO.deleteEmployee(id)) {
                Result result = new Result(200, "\"" + "Success" + "\"");
                return Response.status(200).entity(result.toString()).build();
            }
            else{ //NO Content
                Result result = new Result(204,  "\"" +"Unsuccessful" + "\""  , "\"Employee Not Found\"");
                return Response.status(204).entity(result.toString()).build();
            }
        }
        catch(JDBCException ex) {
            Result result = new Result(422, "\"" +"Error" +"\"" ,"\"" +ex.getCause().toString() +"\"");
            return Response.status(422).entity(result.toString()).build();
        }
        catch (Exception ex) {
            Result result = new Result(500, "\"" +"Error" +"\"" , "\"" +ex.getCause().toString() +"\"");
            return Response.status(500).entity(result.toString()).build();
        }
    }

}