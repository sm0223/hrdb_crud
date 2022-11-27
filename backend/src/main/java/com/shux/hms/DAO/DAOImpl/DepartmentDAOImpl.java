package com.shux.hms.DAO.DAOImpl;

import com.shux.hms.Bean.Department;
import com.shux.hms.Bean.Employee;
import com.shux.hms.CustomExceptions.CustomException;
import com.shux.hms.DAO.DepartmentDAO;
import com.shux.hms.Util.HibernateSessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO {
    @Override
    public boolean addDepartment(Department deptObj) {
        Transaction transaction;
        Session session;
        try {
            session = HibernateSessionUtil.getSession();
            transaction = session.beginTransaction();
            try {
                session.save(deptObj);
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

    public List<Department> getDepartmentList() {

        try (Session session = HibernateSessionUtil.getSession()){
            List<Department> departmentList = new ArrayList<>();
            for (final Object d : session.createQuery("from Department ", Department.class).list()) {
                departmentList.add((Department) d);
            }
            return departmentList;
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
