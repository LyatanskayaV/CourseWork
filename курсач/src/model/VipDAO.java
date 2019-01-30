package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VipDAO {


    public static ObservableList<Vip> searchVipFio(String str) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        try {
            String selectStmt = "SELECT * \n" +
                    "FROM \"VIP\"" +
                    " WHERE  fio_vip = " + "'" + str + "'";

            ResultSet rsVip = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Vip> vip = getVipList(rsVip);
            return vip;
        } catch (SQLException e) {
            System.out.println("While searching a vip with fio, an error occurred: " + e);
            throw e;
        }
    }
    public static ObservableList<Vip> searchVipPhone(String str) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        try {
            String selectStmt = "SELECT * \n" +
                    "FROM \"VIP\"" +
                    " WHERE  phone_vip = " + "'" + str + "'";

            ResultSet rsVip = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Vip> vip = getVipList(rsVip);
            return vip;
        } catch (SQLException e) {
            System.out.println("While searching a vip with fio, an error occurred: " + e);
            throw e;
        }
    } public static ObservableList<Vip> searchVipDate(String str) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        try {
            String selectStmt = "SELECT * \n" +
                    "FROM \"VIP\"" +
                    " WHERE  date_start = " + "'" + str + "'";

            ResultSet rsVip = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Vip> vip = getVipList(rsVip);
            return vip;
        } catch (SQLException e) {
            System.out.println("While searching a vip with fio, an error occurred: " + e);
            throw e;
        }
    } public static ObservableList<Vip> searchVipDiscount(String str) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        try {
            String selectStmt = "SELECT * \n" +
                    "FROM \"VIP\"" +
                    " WHERE  discount = " + "'" + str + "'";

            ResultSet rsVip = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Vip> vip = getVipList(rsVip);
            return vip;
        } catch (SQLException e) {
            System.out.println("While searching a vip with fio, an error occurred: " + e);
            throw e;
        }
    } public static ObservableList<Vip> searchVipSum(String str) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        try {
            String selectStmt = "SELECT * \n" +
                    "FROM \"VIP\"" +
                    " WHERE  total_sum = " + "'" + str + "'";

            ResultSet rsVip = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Vip> vip = getVipList(rsVip);
            return vip;
        } catch (SQLException e) {
            System.out.println("While searching a vip with fio, an error occurred: " + e);
            throw e;
        }
    } public static ObservableList<Vip> searchVipCard(String str) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        try {
            String selectStmt = "SELECT * \n" +
                    "FROM \"VIP\"" +
                    " WHERE  card_number = " + "'" + str + "'";

            ResultSet rsVip = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Vip> vip = getVipList(rsVip);
            return vip;
        } catch (SQLException e) {
            System.out.println("While searching a vip with fio, an error occurred: " + e);
            throw e;
        }
    }








    public static ObservableList<Vip> searchVips() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String selectStmt = "SELECT * \n" +
                "FROM \"VIP\" ORDER BY ID";

        try {
            ResultSet rsVip = DBConnection.dbExecuteQuery(selectStmt);
            ObservableList<Vip> vipList = getVipList(rsVip);
            return vipList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    private static ObservableList<Vip> getVipList(ResultSet rs) throws SQLException {
        ObservableList<Vip> vipList = FXCollections.observableArrayList();

        while (rs.next()) {
            Vip vip = new Vip();
            vip.setVipFio(rs.getString("fio_vip"));
            vip.setPhoneVip(rs.getString("phone_vip"));
            vip.setDiscount(rs.getInt("discount"));
            vip.setSum(rs.getInt("total_sum"));
            vip.setCard(rs.getString("card_number"));
            vip.setDateVip(rs.getString("date_start"));
            vipList.add(vip);
        }
        return vipList;
    }

    public static void deleteFioVip(String fioEmp) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        String updateStmt =
                        " DELETE FROM  \"VIP\"\n" +
                        " WHERE fio_vip =" + "'" + fioEmp + "'";
        try {
            DBConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    public static void deletePhoneVip(String fioEmp) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        String updateStmt =
                        " DELETE FROM  \"VIP\"\n" +
                        " WHERE phone_vip =" + "'" + fioEmp + "'";
        try {
            DBConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    public static void deleteDateVip(String fioEmp) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        String updateStmt =
                        " DELETE FROM  \"VIP\"\n" +
                        " WHERE date_start =" + "'" + fioEmp + "'";
        try {
            DBConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    public static void deleteDiscountVip(String fioEmp) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        String updateStmt =
                        " DELETE FROM  \"VIP\"\n" +
                        " WHERE discount =" + "'" + fioEmp + "'";
        try {
            DBConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    public static void deleteSumVip(String fioEmp) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        String updateStmt =
                        " DELETE FROM  \"VIP\"\n" +
                        " WHERE total_sum =" + "'" + fioEmp + "'";
        try {
            DBConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    public static void deleteCardVip(String fioEmp) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        String updateStmt =
                        " DELETE FROM  \"VIP\"\n" +
                        " WHERE card_number =" + "'" + fioEmp + "'";
        try {
            DBConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }



    public static void insertVip(String fio, String phone, int discount, int sum, String date, String card) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        String updateStmt =
                "INSERT INTO  \"VIP\"\n" +
                        "(FIO_VIP, PHONE_VIP, DATE_START, DISCOUNT, TOTAL_SUM, CARD_NUMBER)\n" +
                        "VALUES\n" +
                        "('"+fio+"', '"+phone+"', '"+date+"','"+discount+"','"+sum+"','"+card+"')";
        try {
            DBConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    public static Vip updateVipFio(String oldValue, String newValue) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        try {
            String updateStmt = "UPDATE \"VIP\" SET fio_vip =" + "'" + newValue + "' \n" +
                    "WHERE fio_vip =" + "'" + oldValue + "'";
            ResultSet rsEmp = DBConnection.dbExecuteQuery(updateStmt);
            Vip vip = getVipFromResultSet(rsEmp);
            return vip;
        } catch (SQLException e) {
            System.out.println("While updating a product with " + oldValue + " name, an error occurred: " + e);
            throw e;
        }
    }

    public static Vip updateVipPhone(String oldValue, String newValue) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        try {
            String updateStmt = "UPDATE \"VIP\" SET phone_vip =" + "'" + newValue + "' \n" +
                    "WHERE phone_vip =" + "'" + oldValue + "'";
            ResultSet rsEmp = DBConnection.dbExecuteQuery(updateStmt);
            Vip vip = getVipFromResultSet(rsEmp);
            return vip;
        } catch (SQLException e) {
            System.out.println("While updating a product with " + oldValue + " name, an error occurred: " + e);
            throw e;
        }
    }

    public static Vip updateVipDate(String oldValue, String newValue) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        try {
            String updateStmt = "UPDATE \"VIP\" SET date_start =" + "'" + newValue + "' \n" +
                    "WHERE date_start =" + "'" + oldValue + "'";
            ResultSet rsEmp = DBConnection.dbExecuteQuery(updateStmt);
            Vip vip = getVipFromResultSet(rsEmp);
            return vip;
        } catch (SQLException e) {
            System.out.println("While updating a product with " + oldValue + " name, an error occurred: " + e);
            throw e;
        }
    }
    public static Vip updateVipDiscount(String oldValue, String newValue) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        try {
            String updateStmt = "UPDATE \"VIP\" SET discount =" + "'" + newValue + "' \n" +
                    "WHERE discount =" + "'" + oldValue + "'";
            ResultSet rsEmp = DBConnection.dbExecuteQuery(updateStmt);
            Vip vip = getVipFromResultSet(rsEmp);
            return vip;
        } catch (SQLException e) {
            System.out.println("While updating a product with " + oldValue + " name, an error occurred: " + e);
            throw e;
        }
    }
    public static Vip updateVipSum(String oldValue, String newValue) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        try {
            String updateStmt = "UPDATE \"VIP\" SET total_sum =" + "'" + newValue + "' \n" +
                    "WHERE total_sum =" + "'" + oldValue + "'";
            ResultSet rsEmp = DBConnection.dbExecuteQuery(updateStmt);
            Vip vip = getVipFromResultSet(rsEmp);
            return vip;
        } catch (SQLException e) {
            System.out.println("While updating a product with " + oldValue + " name, an error occurred: " + e);
            throw e;
        }
    }
    public static Vip updateVipCard(String oldValue, String newValue) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        try {
            String updateStmt = "UPDATE \"VIP\" SET card_number =" + "'" + newValue + "' \n" +
                    "WHERE card_number =" + "'" + oldValue + "'";
            ResultSet rsEmp = DBConnection.dbExecuteQuery(updateStmt);
            Vip vip = getVipFromResultSet(rsEmp);
            return vip;
        } catch (SQLException e) {
            System.out.println("While updating a product with " + oldValue + " name, an error occurred: " + e);
            throw e;
        }
    }







    private static Vip getVipFromResultSet(ResultSet rs) throws SQLException {
        Vip vip = null;
        if (rs.next()) {
            vip = new Vip();
            vip.setVipId(rs.getInt("ID"));
            vip.setVipFio(rs.getString("fio_vip"));
            vip.setPhoneVip(rs.getString("phone_vip"));
            vip.setDateVip(rs.getString("date_start"));
            vip.setDiscount(rs.getInt("discount"));
            vip.setSum(rs.getInt("total_sum"));
            vip.setCard(rs.getString("card"));
        }
        return vip;
    }


    public static Vip raiseDiscount() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        try {
            String updateStmt = "SELECT \"F_RAISE_DISCOUNT\"();";
            ResultSet rsEmp = DBConnection.dbExecuteQuery(updateStmt);
            Vip vip = getVipFromResultSet(rsEmp);
            return vip;
        } catch (SQLException e) {
            System.out.println("While updating a discount, an error occurred: " + e);
            throw e;
        }
    }




}
