package com.shux.hms.DAO.DAOImpl;

import com.shux.hms.Bean.Department;
import com.shux.hms.Bean.Employee;
import com.shux.hms.CustomExceptions.CustomException;
import com.shux.hms.DAO.EmployeeDAO;
import com.shux.hms.Util.HibernateSessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean updateEmployee(Employee empObj) {
        Transaction transaction;
        Session session;
        try {
            session = HibernateSessionUtil.getSession();
            transaction = session.beginTransaction();
            try {

                Query q = session.createQuery("update Employee set firstname =:g, email=:a, empDepartment=:b, lastname=:d, photographPath=:e, title=:f where employeeId="+empObj.getEmployeeId());
                q.setParameter("g", empObj.getFirstname());
                q.setParameter("a", empObj.getEmail());
                q.setParameter("b", empObj.getEmpDepartment());
                q.setParameter("d", empObj.getLastname());
                q.setParameter("e", empObj.getPhotographPath());
                q.setParameter("f", empObj.getTitle());

                int r = q.executeUpdate();
                System.out.println(r);;
//                session.update(empObj);
                transaction.commit();
                return true;
            }
            catch (JDBCException ex) {
                transaction.rollback();
                System.out.println("SQL State" +ex.getSQLState() +" <> " +ex.getCause() +"<>"+ex.getMessage());
                throw (ex);
            }
        }
        catch (HibernateException ex) {
            System.out.print(" Hibernate Exception "+ex.getLocalizedMessage());
            throw(ex);
        }
        catch (Exception ex) {
            System.out.println("Unknown Exception" + ex.getLocalizedMessage());
            throw(ex);
        }
    }

    @Override
    public boolean addEmployee(Employee empObj) {
        Transaction transaction;
        Session session;
        try {
            session = HibernateSessionUtil.getSession();
            transaction = session.beginTransaction();
            try {
                session.save(empObj);
                transaction.commit();
                return true;
            }
            catch (JDBCException ex) {
                transaction.rollback();
                System.out.println("SQL State" +ex.getSQLState() +" <> " +ex.getCause() +"<>"+ex.getMessage());
                throw (ex);
            }
        }
        catch (HibernateException ex) {
            System.out.print(" Hibernate Exception "+ex.getLocalizedMessage());
            throw(ex);
        }
        catch (Exception ex) {
            System.out.println("Unknown Exception" + ex.getLocalizedMessage());
            throw(ex);
        }
    }

    @Override
    public Employee getEmployeeByID(int empID) {
        try (Session session = HibernateSessionUtil.getSession()) {
            return session.get(Employee.class, empID);
        }
        catch (Exception ex) {
            System.out.print("getEMP by ID error" + ex.getLocalizedMessage());
            throw (ex);
        }
    }

    public int getEmployeeListbyDept(Department dept){
        try (Session session = HibernateSessionUtil.getSession()){
            List<Object> listObject =  new ArrayList<>();
            Query q = session.createQuery("from Employee where empDepartment =:b");
            q.setParameter("b", dept);
            listObject = q.list();
            return listObject.size();

        }
        catch (HibernateException ex) {
            System.out.print(" Hibernate Exception "+ex.getLocalizedMessage());
            throw(ex);
        }
        catch (Exception ex) {
            System.out.println("Unknown Exception" + ex.getLocalizedMessage());
            throw(ex);
        }
    }
    public List<Employee> getEmployeeList(){
        try (Session session = HibernateSessionUtil.getSession()){
            List<Employee> empList = new ArrayList<>();
            for (final Object d : session.createQuery("from Employee ", Employee.class).list()) {
                empList.add((Employee) d);
            }
            return empList;

        }
        catch (HibernateException ex) {
            System.out.print(" Hibernate Exception "+ex.getLocalizedMessage());
            throw(ex);
        }
        catch (Exception ex) {
            System.out.println("Unknown Exception" + ex.getLocalizedMessage());
            throw(ex);
        }
    }

    @Override
    public boolean deleteEmployee(int empID) {
        Transaction transaction;
        Session session;
        try {
            session = HibernateSessionUtil.getSession();
            transaction = session.beginTransaction();
            try {
                Employee emp = session.get(Employee.class, empID);
                if(emp == null) return false;
                session.delete(emp);
                transaction.commit();
                return true;
            }
            catch (JDBCException ex) {
                transaction.rollback();
                System.out.println("SQL State" +ex.getSQLState() +" <> " +ex.getCause() +"<>"+ex.getMessage());
                throw (ex);
            }
        }
        catch (HibernateException ex) {
            System.out.print(" Hibernate Exception "+ex.getLocalizedMessage());
            throw(ex);
        }
        catch (Exception ex) {
            System.out.println("Unknown Exception" + ex.getLocalizedMessage());
            throw(ex);
        }
    }
}
