package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KindDAO {

        public static ObservableList<Kind> searchNameKind(String string) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
            try {
                String selectStmt = "SELECT * FROM kind WHERE NAME_KIND = "+ "'" + string + "'";
                ResultSet rsKind = DBConnection.dbExecuteQuery(selectStmt);
                ObservableList<Kind> kinds = getKindList(rsKind);
                return kinds;
            } catch (SQLException e) {
                System.out.println("While searching a kind with name, an error occurred: " + e);
                throw e;
            }
        }

    public static ObservableList<Kind> searchGradeKind(String string) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        try {
            String selectStmt = "SELECT * FROM kind WHERE GRADE = "+ "'" + string + "'";
            ResultSet rsKind = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Kind> kinds = getKindList(rsKind);
            return kinds;
        } catch (SQLException e) {
            System.out.println("While searching a kind with name, an error occurred: " + e);
            throw e;
        }
    }

    public static ObservableList<Kind> searchKinds() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String selectStmt = "SELECT * FROM kind";
        try {
            ResultSet rsKinds = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Kind> kindList = getKindList(rsKinds);
            return kindList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    private static ObservableList<Kind> getKindList(ResultSet rs) throws SQLException {
        ObservableList<Kind> kindsList = FXCollections.observableArrayList();
        while (rs.next()) {
            Kind kind = new Kind();
            kind.setName_kind(rs.getString("name_kind"));
            kind.setGrade(rs.getString("grade"));
            kindsList.add(kind);
        }
        return kindsList;
    }

    private static Kind getKindFromResultSet(ResultSet rs) throws SQLException {
        Kind kind = null;
        if (rs.next()) {
            kind = new Kind();
            kind.setName_kind(rs.getString("name_kind"));
            kind.setGrade(rs.getString("grade"));
        }
        return kind;
    }


    public static void deleteKind(String string) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
            String updateStmt =
                    "DELETE FROM kind\n" +
                            "WHERE name_kind ="  + "'" + string + "'" ;
            System.out.print(updateStmt);
            try {
                DBConnection.dbExecuteUpdate(updateStmt);
            } catch (SQLException e) {
                System.out.print("Error occurred while DELETE Operation: " + e);
                throw e;
            }
    }


    public static void insertKind(String name, String grade) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        String updateStmt =
                "INSERT INTO kind\n" +
                        "(NAME_KIND, GRADE)\n" +
                        "VALUES\n" +
                        "('"+name+"', '"+grade+"')";
        try {
            DBConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    public static Kind updateName(String oldValue, String newValue) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        try {
            String updateStmt = "UPDATE KIND SET NAME_KIND =" + "'" + newValue + "' \n" +
                    "WHERE NAME_KIND =" + "'" + oldValue + "'";

            ResultSet rsEmp = DBConnection.dbExecuteQuery(updateStmt);
            Kind kind = getKindFromResultSet(rsEmp);
            return kind;
        } catch (SQLException e) {
            System.out.println("While updating a product with " + oldValue + " name, an error occurred: " + e);
            throw e;
        }
    }
    public static Kind updateGrade(String oldValue, String newValue) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        try {
            String updateStmt = "UPDATE KIND SET grade =" + "'" + newValue + "' \n" +
                    "                    WHERE grade =" + "'" + oldValue + "'";

            ResultSet rsEmp = DBConnection.dbExecuteQuery(updateStmt);
            Kind kind = getKindFromResultSet(rsEmp);
            return kind;
        } catch (SQLException e) {
            System.out.println("While updating a product with " + oldValue + " grade, an error occurred: " + e);
            throw e;
        }
    }
}
