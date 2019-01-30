package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryDAO {


    public static ObservableList<Inventory> searchNameInventory(String str) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        try {
            String selectStmt = "SELECT * FROM inventory WHERE  name_inventory = " + "'" + str + "'";
            ResultSet rsInv = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Inventory> inventory = getInventoryList(rsInv);
            return inventory;
        } catch (SQLException e) {
            System.out.println("While searching an inventory with id, an error occurred: " + e);
            throw e;
        }
    }
    public static ObservableList<Inventory> searchQuantityInventory(String str) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        try {
            String selectStmt = "SELECT * FROM inventory WHERE  quantity = " + "'" + str + "'";
            ResultSet rsInv = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Inventory> inventory = getInventoryList(rsInv);
            return inventory;
        } catch (SQLException e) {
            System.out.println("While searching an inventory with id, an error occurred: " + e);
            throw e;
        }
    }
    public static ObservableList<Inventory> searchCostInventory(String str) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        try {
            String selectStmt = "SELECT * FROM inventory WHERE  cost = " + "'" + str + "'";
            ResultSet rsInv = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Inventory> inventory = getInventoryList(rsInv);
            return inventory;
        } catch (SQLException e) {
            System.out.println("While searching an inventory with id, an error occurred: " + e);
            throw e;
        }
    }

    public static ObservableList<Inventory> searchInventorys() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String selectStmt = "SELECT * FROM inventory";
        try {
            ResultSet rsInv = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Inventory> invList = getInventoryList(rsInv);
            return invList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    private static ObservableList<Inventory> getInventoryList(ResultSet rs) throws SQLException {
        ObservableList<Inventory> invList = FXCollections.observableArrayList();

        while (rs.next()) {
            Inventory inventory = new Inventory();
            inventory.setName(rs.getString("name_inventory"));
            inventory.setInventory_quantity(rs.getInt("quantity"));
            inventory.setInventory_cost(rs.getInt("cost"));
            invList.add(inventory);
        }
        return invList;
    }

    public static void deleteNameInv(String string) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        String updateStmt =
                " DELETE FROM  inventory" +
                        " WHERE name_inventory =" + "'" + string + "'";
        try {
            DBConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    public static void deleteCostInv(String string) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        String updateStmt =
                " DELETE FROM  inventory" +
                        " WHERE cost =" + "'" + string + "'";
        try {
            DBConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
    public static void deleteQuantityInv(String string) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        String updateStmt =
                " DELETE FROM  inventory" +
                        " WHERE quantity =" + "'" + string + "'";
        try {
            DBConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    private static Inventory getInvFromResultSet(ResultSet rs) throws SQLException {
        Inventory inventory = null;
        if (rs.next()) {
            inventory = new Inventory();
            inventory.setName(rs.getString("name_assort"));
            inventory.setInventory_quantity(rs.getInt("quantity"));
            inventory.setInventory_cost(rs.getInt("cost"));
        }
        return inventory;
    }


    public static Inventory updateInvName(String oldValue, String newValue) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        try {
            String updateStmt = "UPDATE INVENTORY SET name_inventory =" + "'" + newValue + "' \n" +
                    "WHERE name_inventory =" + "'" + oldValue + "'";
            ResultSet rsEmp = DBConnection.dbExecuteQuery(updateStmt);
            Inventory inventory = getInvFromResultSet(rsEmp);
            return inventory;
        } catch (SQLException e) {
            System.out.println("While updating a product with " + oldValue + " name, an error occurred: " + e);
            throw e;
        }
    }

    public static Inventory updateInvQuantity(String oldValue, String newValue) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        try {
            String updateStmt = "UPDATE INVENTORY SET quantity =" + "'" + newValue + "' \n" +
                    "WHERE quantity =" + "'" + oldValue + "'";
            ResultSet rsEmp = DBConnection.dbExecuteQuery(updateStmt);
            Inventory inventory = getInvFromResultSet(rsEmp);
            return inventory;
        } catch (SQLException e) {
            System.out.println("While updating a product with " + oldValue + " quantity, an error occurred: " + e);
            throw e;
        }
    }

    public static Inventory updateInvCost(String oldValue, String newValue) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        try {
            String updateStmt = "UPDATE INVENTORY SET cost =" + "'" + newValue + "' \n" +
                    "WHERE cost =" + "'" + oldValue + "'";
            ResultSet rsEmp = DBConnection.dbExecuteQuery(updateStmt);
            Inventory inventory = getInvFromResultSet(rsEmp);
            return inventory;
        } catch (SQLException e) {
            System.out.println("While updating a product with " + oldValue + " cost, an error occurred: " + e);
            throw e;
        }
    }



    public static void insertInventory(String name, int quantity, int cost) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        String updateStmt =
                "INSERT INTO  inventory" +
                        "(name_inventory, quantity, cost)\n" +
                        "VALUES\n" +
                        "('"+name+"', '"+quantity+"', '"+cost+"')";
        try {
            DBConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

}