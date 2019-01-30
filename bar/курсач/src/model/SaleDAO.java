package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class SaleDAO {


    public static Sale discountDays() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        try {
            String updateStmt = "UPDATE SALES SET total_cost = (total_cost - total_cost/10)\n" +
                    "WHERE DATE_SALE IN ('01.01.2019', '08.03.2019','07.01.2019','18.02.2019')";
            ResultSet rsEmp = DBConnection.dbExecuteQuery(updateStmt);
            Sale sale = getSaleFromResultSet(rsEmp);
            return sale;
        } catch (SQLException e) {
            System.out.println("While updating a product with " + e);
            throw e;
        }
    }




        public static ObservableList<Sale> searchEmpSale(String text) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
            String selectStmt = "SELECT fio, date_sale, total_cost, ID_VIP\n" +
                    " FROM sales, employees \n" +
                    "WHERE sales.id_employee = employees.id " +
                    "AND fio = " + "'" + text + "'";
            try {
                ResultSet rsSales = DBConnection.dbExecuteQuery(selectStmt);
                ObservableList<Sale> saleList = getSaleList(rsSales);
                return saleList;
            } catch (SQLException e) {
                System.out.println("SQL select operation has been failed: " + e);
                throw e;
            }
        }

    public static ObservableList<Sale> searchDateSale(String text) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String selectStmt = "SELECT fio, date_sale, total_cost, ID_VIP\n" +
                " FROM sales, employees \n" +
                "WHERE sales.id_employee = employees.id " +
                "AND date_sale = " + "'" + text + "'";
        try {
            ResultSet rsSales = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Sale> saleList = getSaleList(rsSales);
            return saleList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    public static ObservableList<Sale> searchSumSale(String text) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String selectStmt = "SELECT fio, date_sale, total_cost, ID_VIP\n" +
                " FROM sales, employees \n" +
                "WHERE sales.id_employee = employees.id " +
                "AND total_cost = " + "'" + text + "'";
        try {
            ResultSet rsSales = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Sale> saleList = getSaleList(rsSales);
            return saleList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    public static ObservableList<Sale> searchVipSale(String text) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String selectStmt = "SELECT fio, date_sale, total_cost, SALES.ID_VIP\n" +
                " FROM sales, employees \n" +
                "WHERE sales.id_employee = employees.id " +
                "AND ID_VIP = " + "'" + text + "'";
        try {
            ResultSet rsSales = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Sale> saleList = getSaleList(rsSales);
            return saleList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }


        public static ObservableList<Sale> searchSales() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
            String selectStmt = "SELECT sales.id, fio, date_sale, total_cost, SALES.ID_VIP\n" +
                    " FROM sales, employees \n" +
                    "WHERE sales.id_employee = employees.id";
            try {
                ResultSet rsSale = DBConnection.dbExecuteQuery(selectStmt);
                ObservableList<Sale> saleList = getSaleList(rsSale);
                return saleList;
            } catch (SQLException e) {
                System.out.println("SQL select operation has been failed: " + e);
                throw e;
            }
        }


    private static ObservableList<Sale> getSaleList(ResultSet rs) throws SQLException {
            ObservableList<Sale> saleList = FXCollections.observableArrayList();
            while (rs.next()) {
                Sale sale = new Sale();
                sale.setSaleId(rs.getInt(1));
                sale.setSale_emp(rs.getString(2));
                sale.setSaleDate(rs.getString(3));
                sale.setSum(rs.getInt(4));
                sale.setSale_card(rs.getString(5));
                saleList.addAll(sale);
            }
            return saleList;
        }



    private static Sale getSaleFromResultSet(ResultSet rs) throws SQLException {
        Sale sale = null;
        if (rs.next()) {
            sale = new Sale();
            sale.setSaleId(rs.getInt(1));
            sale.setSale_emp(rs.getString(2));
            sale.setSaleDate(rs.getString(3));
            sale.setSum(rs.getInt(4));
            sale.setSale_card(rs.getString(5));
        }
        return sale;
    }

        public static void insertSale(int emp, String date, int cost, int vip) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
            String updateStmt =
                    "INSERT INTO SALES\n" +
                            "(ID_EMPLOYEE, DATE_SALE, TOTAL_COST, ID_VIP)\n" +
                            "VALUES\n" +
                            "('" + emp + "', '" + date + "', '" + cost + "','" + vip + "')";
            try {
                DBConnection.dbExecuteUpdate(updateStmt);
            } catch (SQLException e) {
                System.out.print("Error occurred while DELETE Operation: " + e);
                throw e;
            }
        }



        public static void deleteSale(String string1, String string2) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
            if (Objects.equals(string2, "Сотрудник")) {
                String updateStmt =
                        "DELETE FROM sales\n" +
                                "WHERE ID_EMPLOYEE = (SELECT ID FROM EMPLOYEES WHERE FIO =" + "'"+string1+"')";
                System.out.print(updateStmt);
                try {
                    DBConnection.dbExecuteUpdate(updateStmt);
                } catch (SQLException e) {
                    System.out.print("Error occurred while DELETE Operation: " + e);
                    throw e;
                }
            }
            if (Objects.equals(string2, "Дата")) {
                String updateStmt =
                        "DELETE FROM sales\n" +
                                "WHERE date_sale =" + "'"+string1+"'";
                System.out.print(updateStmt);
                try {
                    DBConnection.dbExecuteUpdate(updateStmt);
                } catch (SQLException e) {
                    System.out.print("Error occurred while DELETE Operation: " + e);
                    throw e;
                }
            }
            if (Objects.equals(string2, "Сумма")) {
                String updateStmt =
                        "DELETE FROM sales\n" +
                                "WHERE total_cost = " + "'"+string1+"'";
                System.out.print(updateStmt);
                try {
                    DBConnection.dbExecuteUpdate(updateStmt);
                } catch (SQLException e) {
                    System.out.print("Error occurred while DELETE Operation: " + e);
                    throw e;
                }
            }
            if (Objects.equals(string2, "Vip")) {
                String updateStmt =
                        "DELETE FROM sales\n" +
                                "WHERE ID_VIP = (SELECT ID FROM \"VIP\" WHERE card_number =" + "'"+string1+"')";
                System.out.print(updateStmt);
                try {
                    DBConnection.dbExecuteUpdate(updateStmt);
                } catch (SQLException e) {
                    System.out.print("Error occurred while DELETE Operation: " + e);
                    throw e;
                }
            }
        }




        public static Sale updateSaleEmp(String oldValue, String newValue) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
            try {
                String updateStmt = "UPDATE Employees SET fio =" + "'" + newValue + "' \n" +
                        "WHERE fio =" + "'" + oldValue + "'";
                ResultSet rsEmp = DBConnection.dbExecuteQuery(updateStmt);
                Sale sale = getSaleFromResultSet(rsEmp);
                return sale;
            } catch (SQLException e) {
                System.out.println("While updating a product with " + oldValue + " name, an error occurred: " + e);
                throw e;
            }
        }

    public static Sale updateSaleDate(String oldValue, String newValue) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        try {
            String updateStmt = "UPDATE Sales SET date_sale =" + "'" + newValue + "' \n" +
                    "WHERE date_sale =" + "'" + oldValue + "'";
            ResultSet rsEmp = DBConnection.dbExecuteQuery(updateStmt);
            Sale sale = getSaleFromResultSet(rsEmp);
            return sale;
        } catch (SQLException e) {
            System.out.println("While updating a product with " + oldValue + " name, an error occurred: " + e);
            throw e;
        }
    }
    public static Sale updateSaleSum(String oldValue, String newValue) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        try {
            String updateStmt = "UPDATE Sales SET total_cost =" + "'" + newValue + "' \n" +
                    "WHERE total_cost =" + "'" + oldValue + "'";
            ResultSet rsEmp = DBConnection.dbExecuteQuery(updateStmt);
            Sale sale = getSaleFromResultSet(rsEmp);
            return sale;
        } catch (SQLException e) {
            System.out.println("While updating a product with " + oldValue + " name, an error occurred: " + e);
            throw e;
        }
    }

    public static Sale updateSaleVip(String oldValue, String newValue) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        try {
            String updateStmt = "UPDATE \"VIP\" SET card_number  =" + "'" + newValue + "' \n" +
                    "WHERE card_number =" + "'" + oldValue + "'";
            ResultSet rsEmp = DBConnection.dbExecuteQuery(updateStmt);
            Sale sale = getSaleFromResultSet(rsEmp);
            return sale;
        } catch (SQLException e) {
            System.out.println("While updating a product with " + oldValue + " name, an error occurred: " + e);
            throw e;
        }
    }

}