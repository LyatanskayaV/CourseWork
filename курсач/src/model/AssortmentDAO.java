package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import util.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class AssortmentDAO {

    public static ObservableList<Assortment> searchNameAssortment(String text) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String selectStmt = "SELECT name_assort, cost, description, name_kind\n" +
                " FROM assortment, kind\n" +
                "WHERE kind.id = assortment.kind \n" +
                "AND name_assort = " + "'" + text + "'";
        try {
            ResultSet rsAssorts = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Assortment> assortList = getAssortmentList(rsAssorts);
            return assortList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    public static ObservableList<Assortment> searchCostAssortment(String text) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String selectStmt = "SELECT name_assort, cost, description, name_kind\n" +
                " FROM assortment, kind\n" +
                "WHERE kind.id = assortment.kind \n" +
                "AND cost = " + "'" + text + "'";
        try {
            ResultSet rsAssorts = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Assortment> assortList = getAssortmentList(rsAssorts);
            return assortList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    public static ObservableList<Assortment> searchDescriptionAssortment(String text) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String selectStmt = "SELECT name_assort, cost, description, name_kind\n" +
                " FROM assortment, kind\n" +
                "WHERE kind.id = assortment.kind \n" +
                "AND description = " + "'" + text + "'";
        try {
            ResultSet rsAssorts = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Assortment> assortList = getAssortmentList(rsAssorts);
            return assortList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    public static ObservableList<Assortment> searchKindAssortment(String text) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String selectStmt = "SELECT name_assort, cost, description, name_kind\n" +
                " FROM assortment, kind\n" +
                "WHERE kind.id = assortment.kind \n" +
                "AND name_kind = " + "'" + text + "'";
        try {
            ResultSet rsAssorts = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Assortment> assortList = getAssortmentList(rsAssorts);
            return assortList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    public static ObservableList<Assortment> searchAssortments() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String selectStmt = "select name_assort, cost, description, name_kind\n" +
                "from assortment a, kind k\n" +
                "where k.id = a.kind";
        try {
            ResultSet rsAssorts = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Assortment> assortList = getAssortmentList(rsAssorts);
            return assortList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    private static ObservableList<Assortment> getAssortmentList(ResultSet rs) throws SQLException {
        ObservableList<Assortment> assortList = FXCollections.observableArrayList();
        while (rs.next()) {
            Assortment assortment = new Assortment();
            assortment.setAssortname(rs.getString(1));
            assortment.setAssortCost(rs.getInt(2));
            assortment.setAssortDescription(rs.getString(3));
            assortment.setAssortKind(rs.getString(4));
            assortList.addAll(assortment);
        }
        return assortList;
    }

    public static void insertAssort(String name_assort, String cost, String description, int kind) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        String updateStmt =
                "INSERT INTO ASSORTMENT\n" +
                        "(NAME_ASSORT, COST, DESCRIPTION, KIND)\n" +
                        "VALUES\n" +
                        "('" + name_assort + "', '" + cost + "', '" + description + "','" + kind + "')";
        try {
            DBConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    private static Assortment getAssortmentFromResultSet(ResultSet rs) throws SQLException {
        Assortment assortment = null;
        if (rs.next()) {
            assortment = new Assortment();
            assortment.setAssortname(rs.getString("name_assort"));
            assortment.setAssortCost(rs.getInt("cost"));
            assortment.setAssortDescription(rs.getString("description"));
            assortment.setAssortKind(rs.getString("kind"));
        }
        return assortment;
    }

    public static void deleteAssortment(String fioEmp, String string) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        if (Objects.equals(string, "Название")) {
            String updateStmt =
                    "DELETE FROM assortment\n" +
                            "WHERE name_assort =" + "'" + fioEmp + "'";
            System.out.print(updateStmt);
            try {
                DBConnection.dbExecuteUpdate(updateStmt);
            } catch (SQLException e) {
                System.out.print("Error occurred while DELETE Operation: " + e);
                throw e;
            }
        }
        if (Objects.equals(string, "Стоимость")) {
            String updateStmt =
                    "DELETE FROM assortment\n" +
                            "WHERE cost = " + "'" + fioEmp + "'";
            try {
                DBConnection.dbExecuteUpdate(updateStmt);
            } catch (SQLException e) {
                System.out.print("Error occurred while DELETE Operation: " + e);
                throw e;
            }
        }
        if (Objects.equals(string, "Описание")) {
            String updateStmt =
                    "DELETE FROM assortment\n" +
                            "WHERE description = " + "'" + fioEmp + "'";
            try {
                DBConnection.dbExecuteUpdate(updateStmt);
            } catch (SQLException e) {
                System.out.print("Error occurred while DELETE Operation: " + e);
                throw e;
            }
        }
        if (Objects.equals(string, "Вид")) {
            String updateStmt =
                    "DELETE FROM assortment, kind\n" +
                            "WHERE kind.name_kind = " + "'" + fioEmp + "'";
            try {
                DBConnection.dbExecuteUpdate(updateStmt);
            } catch (SQLException e) {
                System.out.print("Error occurred while DELETE Operation: " + e);
                throw e;
            }
        }
    }


    public static Assortment updateAssortName(String oldValue, String newValue) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        try {
            String updateStmt = "UPDATE ASSORTMENT SET NAME_ASSORT =" + "'" + newValue + "' \n" +
                    "WHERE NAME_ASSORT =" + "'" + oldValue + "'";
            ResultSet rsEmp = DBConnection.dbExecuteQuery(updateStmt);
            Assortment assortment = getAssortmentFromResultSet(rsEmp);
            return assortment;
        } catch (SQLException e) {
            System.out.println("While updating a product with " + oldValue + " name, an error occurred: " + e);
            throw e;
        }
    }
    public static Assortment updateAssortCost(String oldValue, String newValue) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        try {
            String updateStmt = "UPDATE ASSORTMENT SET cost =" + "'" + newValue + "' \n" +
                    "WHERE cost =" + "'" + oldValue + "'";
            ResultSet rsEmp = DBConnection.dbExecuteQuery(updateStmt);
            Assortment assortment = getAssortmentFromResultSet(rsEmp);
            return assortment;
        } catch (SQLException e) {
            System.out.println("While updating a product with " + oldValue + " name, an error occurred: " + e);
            throw e;
        }
    }
    public static Assortment updateAssortDescription(String oldValue, String newValue) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        try {
            String updateStmt = "UPDATE ASSORTMENT SET description =" + "'" + newValue + "' \n" +
                    "WHERE description =" + "'" + oldValue + "'";
            ResultSet rsEmp = DBConnection.dbExecuteQuery(updateStmt);
            Assortment assortment = getAssortmentFromResultSet(rsEmp);
            return assortment;
        } catch (SQLException e) {
            System.out.println("While updating a product with " + oldValue + " name, an error occurred: " + e);
            throw e;
        }
    }
    public static Assortment updateAssortKind(String oldValue, String newValue) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        try {
            String updateStmt = "UPDATE kind SET name_kind =" + "'" + newValue + "' \n" +
                    "WHERE name_kind =" + "'" + oldValue + "'";
            ResultSet rsEmp = DBConnection.dbExecuteQuery(updateStmt);
            Assortment assortment = getAssortmentFromResultSet(rsEmp);
            return assortment;
        } catch (SQLException e) {
            System.out.println("While updating a product with " + oldValue + " name, an error occurred: " + e);
            throw e;
        }
    }
}
