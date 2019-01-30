package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class DetailsDAO {


        public static ObservableList<Details> searchDetails() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
            String selectStmt = "SELECT sales.id, sale_details.quantity, name_assort, name_inventory\n" +
                    "                    FROM assortment, inventory, sales, sale_details\n" +
                    "                    WHERE sales.id = sale_details.id_sale\n" +
                    "                    AND sale_details.id_inventory = inventory.id\n" +
                    "                    AND sale_details.id_product = assortment.id";
            try {
                ResultSet rsAssorts = DBConnection.dbExecuteQuery(selectStmt);
                ObservableList<Details> assortList = getDetailsList(rsAssorts);
                return assortList;
            } catch (SQLException e) {
                System.out.println("SQL select operation has been failed: " + e);
                throw e;
            }
        }

         private static ObservableList<Details> getDetailsList(ResultSet rs) throws SQLException {
            ObservableList<Details> assortList = FXCollections.observableArrayList();
            while (rs.next()) {
                Details details = new Details();
                details.setDetails_sale(rs.getString(1));
                details.setDetails_quantity(rs.getInt(2));
                details.setDetails_product(rs.getString(3));
                details.setDetails_inventory(rs.getString(4));
                assortList.addAll(details);
            }
            return assortList;
        }

        private static Details getDetailsFromResultSet(ResultSet rs) throws SQLException {
            Details details = new Details();
            if (rs.next()) {
                details.setDetails_sale(rs.getString(1));
                details.setDetails_quantity(rs.getInt(2));
                details.setDetails_product(rs.getString(3));
                details.setDetails_inventory(rs.getString(4));
            }
            return details;
        }




    public static ObservableList<Details> searchDetailsSale(String text) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String selectStmt = "SELECT sales.id, sale_details.quantity, name_assort, name_inventory " +
                "FROM assortment, inventory, sales, sale_details " +
                "WHERE sales.id = sale_details.id_sale " +
                "AND sale_details.id_inventory = inventory.id " +
                "AND sale_details.id_product = assortment.id AND " +
                "sales.id =" + "'"+text+"'";
        try {
            ResultSet rsDetails = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Details> details = getDetailsList(rsDetails);
            return details;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    public static ObservableList<Details> searchDetailsQuantity(int text) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String selectStmt = "SELECT sales.id, sale_details.quantity, name_assort, name_inventory " +
                "FROM assortment, inventory, sales, sale_details " +
                "WHERE sales.id = sale_details.id_sale " +
                "AND sale_details.id_inventory = inventory.id " +
                "AND sale_details.id_product = assortment.id AND " +
                "sale_details.quantity =" + "'"+text+"'";
        try {
            ResultSet rsDetails = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Details> details = getDetailsList(rsDetails);
            return details;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    public static ObservableList<Details> searchDetailsProduct(String text) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String selectStmt = "SELECT sales.id, sale_details.quantity, name_assort, name_inventory " +
                "FROM assortment, inventory, sales, sale_details " +
                "WHERE sales.id = sale_details.id_sale " +
                "AND sale_details.id_inventory = inventory.id " +
                "AND sale_details.id_product = assortment.id AND " +
                "name_assort =" + "'"+text+"'";
        try {
            ResultSet rsDetails = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Details> details = getDetailsList(rsDetails);
            return details;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    public static ObservableList<Details> searchDetailsInventory(String text) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String selectStmt = "SELECT sales.id, sale_details.quantity, name_assort, name_inventory " +
                "FROM assortment, inventory, sales, sale_details " +
                "WHERE sales.id = sale_details.id_sale " +
                "AND sale_details.id_inventory = inventory.id " +
                "AND sale_details.id_product = assortment.id AND " +
                "name_inventory =" + "'"+text+"'";
        try {
            ResultSet rsDetails = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Details> details = getDetailsList(rsDetails);
            return details;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    public static Details updateDetailsQuantity(String oldValue, String newValue) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        try {
            String updateStmt = "UPDATE sale_details SET quantity =" + "'" + newValue + "' \n" +
                    "WHERE quantity =" + "'" + oldValue + "'";
            ResultSet rsEmp = DBConnection.dbExecuteQuery(updateStmt);
            Details details = getDetailsFromResultSet(rsEmp);
            return details;
        } catch (SQLException e) {
            System.out.println("While updating a product with " + oldValue + " quantity, an error occurred: " + e);
            throw e;
        }
    }

    public static Details updateDetailsInventory (int oldValue, int newValue) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        try {
            String updateStmt = "UPDATE sale_details SET id_inventory =" + "'" +newValue+"' "+
                    "WHERE id_inventory = "+"'"+oldValue+"'";
            ResultSet rsEmp = DBConnection.dbExecuteQuery(updateStmt);
            Details details = getDetailsFromResultSet(rsEmp);
            return details;
        } catch (SQLException e) {
            System.out.println("While updating a product with " + oldValue + " quantity, an error occurred: " + e);
            throw e;
        }
    }

    public static Details updateDetailsProduct (int oldValue, int newValue) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        try {
            String updateStmt = "UPDATE sale_details SET id_product =" + "'" +newValue+"' "+
                    "WHERE id_product = (SELECT ID FROM assortment WHERE id = "+"'"+oldValue+"')";
            ResultSet rsEmp = DBConnection.dbExecuteQuery(updateStmt);
            Details details = getDetailsFromResultSet(rsEmp);
            return details;
        } catch (SQLException e) {
            System.out.println("While updating a product with " + oldValue + " quantity, an error occurred: " + e);
            throw e;
        }
    }







        public static void deleteDetails(String fioEmp, String string) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
            if (Objects.equals(string, "Номер продажи")) {
                String updateStmt =
                        "DELETE FROM sale_details\n" +
                                "WHERE id_sale =" + "'" + fioEmp + "'";
                System.out.print(updateStmt);
                try {
                    DBConnection.dbExecuteUpdate(updateStmt);
                } catch (SQLException e) {
                    System.out.print("Error occurred while DELETE Operation: " + e);
                    throw e;
                }
            }
            if (Objects.equals(string, "Количество")) {
                String updateStmt =
                        "DELETE FROM sale_details\n" +
                                "WHERE quantity = " + "'" + fioEmp + "'";
                try {
                    DBConnection.dbExecuteUpdate(updateStmt);
                } catch (SQLException e) {
                    System.out.print("Error occurred while DELETE Operation: " + e);
                    throw e;
                }
            }
            if (Objects.equals(string, "Продукт")) {
                String updateStmt =
                        "DELETE FROM sale_details\n" +
                                "WHERE id_product = (SELECT id FROM ASSORTMENT WHERE name_assort = " + "'" +fioEmp+ "')";
                try {
                    DBConnection.dbExecuteUpdate(updateStmt);
                } catch (SQLException e) {
                    System.out.print("Error occurred while DELETE Operation: " + e);
                    throw e;
                }
            }
            if (Objects.equals(string, "Инвентарь")) {
                String updateStmt =
                        "DELETE FROM sale_details " +
                                "WHERE id_inventory = (SELECT id FROM inventory WHERE name_inventory = " + "'" +fioEmp+ "')";
                try {
                    DBConnection.dbExecuteUpdate(updateStmt);
                } catch (SQLException e) {
                    System.out.print("Error occurred while DELETE Operation: " + e);
                    throw e;
                }
            }
        }

    public static void insertDetails(int sale, int product, int inventory, String quantity) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        String updateStmt =
                "INSERT INTO SALE_DETAILS\n" +
                        "(ID_SALE, QUANTITY, ID_PRODUCT, ID_INVENTORY)\n" +
                        "VALUES\n" +
                        "('" + sale + "', '" + quantity + "', '" + product + "','" + inventory + "')";
        try {
            DBConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while insert Operation: " + e);
            throw e;
        }
    }

    }


