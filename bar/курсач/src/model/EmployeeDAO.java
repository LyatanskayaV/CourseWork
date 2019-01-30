package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import util.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {

    public static ObservableList<Employee> daysEmployee(String str) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String selectStmt = "SELECT * FROM employees" +
                " WHERE  schedule = " + "'" + str + "';";
        try {
            ResultSet rsEmps = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Employee> empList = getEmployeeList(rsEmps);
            return empList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }


    public static ObservableList<Employee> searchFIOEmployee(String str) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
            String selectStmt = "SELECT * FROM employees" +
                    " WHERE  fio = " + "'" + str + "';";
        try {
            ResultSet rsEmps = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Employee> empList = getEmployeeList(rsEmps);
            return empList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }
    public static ObservableList<Employee> searchPhoneEmployee(String str) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

            String selectStmt = "SELECT * FROM employees" +
                    " WHERE  phone = " + "'" + str + "'";
        try {
            ResultSet rsEmps = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Employee> empList = getEmployeeList(rsEmps);
            return empList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }
    public static ObservableList<Employee> searchAddressEmployee(String str) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
            String selectStmt = "SELECT * FROM employees" +
                    " WHERE  address = " + "'" + str + "'";
        try {
            ResultSet rsEmps = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Employee> empList = getEmployeeList(rsEmps);
            return empList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    public static ObservableList<Employee> searchExprEmployee(String str) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
            String selectStmt = "SELECT * FROM employees" +
                    " WHERE  experience = " + "'" + str + "'";
        try {
            ResultSet rsEmps = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Employee> empList = getEmployeeList(rsEmps);
            return empList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    public static ObservableList<Employee> searchPositionEmployee(String str) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String selectStmt = "SELECT * FROM employees" +
                " WHERE  position = " + "'" + str + "'";
        try {
            ResultSet rsEmps = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Employee> empList = getEmployeeList(rsEmps);
            return empList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }


    public static ObservableList<Employee> searchScheduleEmployee(String str) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
            String selectStmt = "SELECT * FROM employees" +
                    " WHERE  schedule = " + "'" + str + "'";
        try {
            ResultSet rsEmps = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Employee> empList = getEmployeeList(rsEmps);
            return empList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    private static Employee getEmployeeFromResultSet(ResultSet rs) throws SQLException {
        Employee emp = null;
        if (rs.next()) {
            emp = new Employee();
            emp.setFio(rs.getString("FIO"));
            emp.setExpr(rs.getString("Experience"));
            emp.setPhone(rs.getString("Phone"));
            emp.setAddress(rs.getString("Address"));
            emp.setPosition(rs.getString("Position"));
            emp.setSchedule(rs.getString("Schedule"));
        }
        return emp;
    }
////////////////////////////////////////////////////////Poisk/////////////////////////////////////

    public static ObservableList<Employee> searchEmployees() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String selectStmt = "SELECT * FROM employees";

        try {
            ResultSet rsEmps = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Employee> empList = getEmployeeList(rsEmps);
            return empList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    private static ObservableList<Employee> getEmployeeList(ResultSet rs) throws SQLException {
        ObservableList<Employee> empList = FXCollections.observableArrayList();

        while (rs.next()) {
            Employee emp = new Employee();
            emp.setFio(rs.getString("FIO"));
            emp.setExpr(rs.getString("Experience"));
            emp.setPhone(rs.getString("Phone"));
            emp.setAddress(rs.getString("Address"));
            emp.setPosition(rs.getString("Position"));
            emp.setSchedule(rs.getString("Schedule"));
            empList.add(emp);
        }
        return empList;
    }

    /////////////////////////////////////////vivod/////////////////////////////////////////////

   public static void deleteEmp(String fioEmp, ComboBox comboBox) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
       if (comboBox.getSelectionModel().getSelectedItem() == "ФИО") {
           String updateStmt =
                   "DELETE FROM employees\n" +
                           "WHERE fio = " + "'" + fioEmp + "'"+
                           "COMMIT";

           try {
               DBConnection.dbExecuteUpdate(updateStmt);
           } catch (SQLException e) {
               System.out.print("Error occurred while DELETE Operation: " + e);
               throw e;
           }
       }
       if (comboBox.getSelectionModel().getSelectedItem() == "Телефон") {
           String updateStmt =
                   "DELETE FROM employees\n" +
                           "WHERE phone = " + "'" + fioEmp + "'";
           try {
               DBConnection.dbExecuteUpdate(updateStmt);
           } catch (SQLException e) {
               System.out.print("Error occurred while DELETE Operation: " + e);
               throw e;
           }
       }
       if (comboBox.getSelectionModel().getSelectedItem() == "Адрес") {
           String updateStmt =
                   "DELETE FROM employees\n" +
                           "WHERE address = " + "'" + fioEmp + "'";
           try {
               DBConnection.dbExecuteUpdate(updateStmt);
           } catch (SQLException e) {
               System.out.print("Error occurred while DELETE Operation: " + e);
               throw e;
           }
       }
       if (comboBox.getSelectionModel().getSelectedItem() == "Должность") {
           String updateStmt =
                   "DELETE FROM employees\n" +
                           "WHERE position = " + "'" + fioEmp + "'";
           try {
               DBConnection.dbExecuteUpdate(updateStmt);
           } catch (SQLException e) {
               System.out.print("Error occurred while DELETE Operation: " + e);
               throw e;
           }
       }
   }

//////////////////////////////////////////////////delete/////////////////////////////////////////////

  public static void insertEmp(String fio, String phone, String address, String expr, String position, String schedule) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        String updateStmt =
                "INSERT INTO employees\n" +
                        "(ID, FIO, PHONE, ADDRESS, EXPERIENCE, POSITION, SCHEDULE)\n" +
                        "VALUES\n" +
                        "(nextval('employees_id_seq'), '"+fio+"', '"+phone+"', '"+address+"','"+expr+"','"+position+"','"+schedule+"')";
        try {
            DBConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
//////////////////////////////dobavlenie////////////////////////////////////////////////

    public static Employee updateFio(String oldValue, String newValue) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        try {
            String updateStmt = "UPDATE EMPLOYEES SET FIO =" + "'" + newValue + "' \n" +
            "                    WHERE FIO =" + "'" + oldValue + "'";

            ResultSet rsEmp = DBConnection.dbExecuteQuery(updateStmt);
            Employee employee = getEmployeeFromResultSet(rsEmp);
            return employee;
        } catch (SQLException e) {
            System.out.println("While updating a product with " + oldValue + " fio, an error occurred: " + e);
            throw e;
        }
    }

    public static Employee updateExpr(String oldValue, String newValue) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        try {
            String selectStmt =
                    "UPDATE EMPLOYEES\n" +
                    "                    SET EXPERIENCE =" + "'" + newValue + "' \n" +
                    "                    WHERE EXPERIENCE =" + "'" + oldValue + "'";

            ResultSet rsEmp = DBConnection.dbExecuteQuery(selectStmt);
            Employee employee = getEmployeeFromResultSet(rsEmp);
            return employee;
        } catch (SQLException e) {
            System.out.println("While updating a product with " + oldValue + " experience, an error occurred: " + e);
            throw e;
        }
    }

    public static Employee updatePhone(String oldValue, String newValue) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        try {
            String selectStmt =
                    "UPDATE EMPLOYEES\n" +
                    "                    SET PHONE =" + "'" + newValue + "' \n" +
                    "                    WHERE PHONE =" + "'" + oldValue + "'";
            ResultSet rsEmp = DBConnection.dbExecuteQuery(selectStmt);
            Employee employee = getEmployeeFromResultSet(rsEmp);
            return employee;
        } catch (SQLException e) {
            System.out.println("While updating a product with " + oldValue + " phone, an error occurred: " + e);
            throw e;
        }
    }
    public static Employee updateAddress(String oldValue, String newValue) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        try {
            String selectStmt =
                    "UPDATE EMPLOYEES\n" +
                    "                    SET ADDRESS =" + "'" + newValue + "' \n" +
                    "                    WHERE ADDRESS =" + "'" + oldValue + "'";

            ResultSet rsEmp = DBConnection.dbExecuteQuery(selectStmt);
            Employee employee = getEmployeeFromResultSet(rsEmp);
            return employee;
        } catch (SQLException e) {
            System.out.println("While updating a product with " + oldValue + " address, an error occurred: " + e);
            throw e;
        }
    }
    public static Employee updateSchedule(String oldValue, String newValue) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        try {
            String selectStmt =
                    "UPDATE EMPLOYEES\n" +
                    "                    SET SCHEDULE =" + "'" + newValue + "' \n" +
                           " WHERE SCHEDULE =" + "'" + oldValue + "'";

            ResultSet rsEmp = DBConnection.dbExecuteQuery(selectStmt);
            Employee employee = getEmployeeFromResultSet(rsEmp);
            return employee;
        } catch (SQLException e) {
            System.out.println("While updating a product with " + oldValue + " schedule, an error occurred: " + e);
            throw e;
        }
    }
    public static Employee updatePosition(String oldValue, String newValue) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        try {
            String selectStmt =
                    "UPDATE EMPLOYEES\n" +
                    "                    SET POSITION =" + "'" + newValue + "' \n" +
                    "                    WHERE POSITION =" + "'" + oldValue + "'";

            ResultSet rsEmp = DBConnection.dbExecuteQuery(selectStmt);
            Employee employee = getEmployeeFromResultSet(rsEmp);
            return employee;
        } catch (SQLException e) {
            System.out.println("While updating a product with " + oldValue + " POSITION, an error occurred: " + e);
            throw e;
        }
    }

}