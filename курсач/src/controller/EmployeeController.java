package controller;

import com.sun.rowset.internal.Row;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;
import sun.applet.Main;
import util.DBConnection;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class EmployeeController {

    @FXML
    private TableView<Vip> vipTable;
    @FXML
    private TableColumn<Vip, Integer> vipIdColumn;
    @FXML
    private TableColumn<Vip,String> vipFioColumn;
    @FXML
    private TableColumn<Vip,String> vipPhoneColumn;
    @FXML
    private TableColumn<Vip,String> vipDateColumn;
    @FXML
    private TableColumn<Vip,Integer> vipDiscountColumn;
    @FXML
    private TableColumn<Vip,Integer> vipSumColumn;
    @FXML
    private TableColumn<Vip,String> vipCardColumn;



    @FXML
    private TableView<Sale> saleTable;
    @FXML
    private TableColumn<Sale, Integer> saleIdColumn;
    @FXML
    private TableColumn<Sale,String> saleEmployeeColumn;
    @FXML
    private TableColumn<Sale,String> saleDateColumn;
    @FXML
    private TableColumn<Sale,String> saleCardColumn;
    @FXML
    private TableColumn<Sale,Integer> saleSumColumn;



    @FXML
    private TableView<Kind> kindTable;
    @FXML
    private TableColumn<Kind, Integer> IdKindColumn;
    @FXML
    private TableColumn<Kind,String> nameKindColumn;
    @FXML
    private TableColumn<Kind,String> gradeKindColumn;


    @FXML
    private TableView<Assortment> assortmentTable;
    @FXML
    private TableColumn<Assortment, Integer> assortIdColumn;
    @FXML
    private TableColumn<Assortment,String> assortNameColumn;
    @FXML
    private TableColumn<Assortment,Integer> assortCostColumn;
    @FXML
    private TableColumn<Assortment,String> assortDescriptionColumn;
    @FXML
    private TableColumn<Assortment,String> assortKindColumn;

    @FXML
    private TableView<Details> detailsTable;
    @FXML
    private TableColumn<Details,String> detailsSale;
    @FXML
    private TableColumn<Details,Integer> detailsQuantity;
    @FXML
    private TableColumn<Details,String> detailsProduct;
    @FXML
    private TableColumn<Details,String> detailsInventory;





    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, Integer> empIdColumn;
    @FXML
    private TableView<Inventory> inventoryTable;
    @FXML
    private TableColumn<Inventory, Integer> inventoryIdColumn;
    @FXML
    private TableColumn<Inventory,String> inventoryNameColumn;
    @FXML
    private TableColumn<Inventory,Integer> inventoryQuantityColumn;
    @FXML
    private TableColumn<Inventory,Integer> inventoryCostColumn;
    @FXML
    private TableColumn<Employee, String> empFioColumn;
    @FXML
    private TableColumn<Employee, String> empExprColumn;
    @FXML
    private TableColumn<Employee, String> empPhoneColumn;
    @FXML
    private TableColumn<Employee, String> empAddressColumn;
    @FXML
    private TableColumn<Employee, String> empScheduleColumn;
    @FXML
    private TableColumn<Employee, String> empPositionColumn;
    @FXML
    private DatePicker dateVip, dateSale;
    @FXML
    private Button searchEmpButton,searchDetailsBtn, changeDetailsBtn, deleteDetailsBtn, addDetailsBtn, changeEmpButton, deleteEmpButton,
            addEmpButton,deleteVipBtn, changeVipBtn, searchVipBtn, addVipBtn,
            searchAssortButton, searchSaleBtn, changeSaleBtn, deleteSaleBtn, addSaleBtn, deleteAssortButton, assortUpdateButton, addAssortButton,
            deleteInvBtn, changeInvBtn, addInvBtn, searchInvBtn;
    @FXML
    private TextField searchEmpTextfield, searchDetailsTxt, deleteDetailsTxt,
            searchSaleTxt, oldValueTxt, newValueTxt, deleteSaleTxt, sumSaleTxt, fioEmptxt,exprEmptxt,phoneEmptxt,scheduleEmptxt,
            deleteInvTxt, oldInvValue, newInvValue, costInvTxt, quantityInvTxt,
    nameInvTxt, deleteEmpTextfield, addressEmpTextfield, kindGradeTxt, kindNameTxt,oldKindValue,
            oldEmpValue, newEmpValue, searchInventoryTextfield, deleteKindTxt, newKindValue,  searchAssortTxt, assortDeleteTxt,
            assortOldValue, assortNewValue, nameAssortTxt, searchKindTxt, costAssortTxt, descriptionAssortTxt,
    fioVipTxt, phoneVipTxt, cardVipTxt, discountVipTxt, sumVipTxt, searchVipTxt, oldVipValue, newVipValue, deleteVipTxt;
    @FXML
    private ComboBox<String> comboEmp, oldDetailsCombo, newDetailsCombo;
    @FXML
    private ComboBox<String> searchDetailsCombo, detailsQuantityCombo;
    @FXML
    private ComboBox<String> comboChangeDetails, daysCombo;
    @FXML
    private ComboBox<String> deleteDetailsCombo;
    @FXML
    private ComboBox<String> detailsProductCombo;
    @FXML
    private ComboBox<String> detailsInventoryCombo;

    @FXML
    private ComboBox<String> detailsSaleCombo;



    @FXML
    private ComboBox<String> comboChangeEmp, detailsChooseCombo,comboDeleteEmp, empChoose,positionEmpCombo,deleteInvCombo,changeInvCombo;
    @FXML
    private ComboBox<String> comboSearchInv,assortChoose,searchAssortCombo,comboDeleteAssort,assortChangeCombo,comboKindAssort,searchKindCombo;
    @FXML
    private ComboBox<String> vipChoose,comboDeleteVip,changeVipCombo,comboSearchVip,invChoose,changeKindCombo,searchSaleCombo,changeSaleCombo;
    @FXML
    private ComboBox<String> deleteSaleCombo,empSaleCombo,vipSaleCombo,saleChoose;




    @FXML
    private void searchEmployee(ActionEvent actionEvent) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        if (Objects.equals(comboEmp.getSelectionModel().getSelectedItem(), "ФИО")) {
            try {
                ObservableList<Employee> emp = EmployeeDAO.searchFIOEmployee(searchEmpTextfield.getText());
                populateEmployees(emp);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        if (Objects.equals(comboEmp.getSelectionModel().getSelectedItem(), "Телефон")) {
            try {
                ObservableList<Employee> emp = EmployeeDAO.searchPhoneEmployee(searchEmpTextfield.getText());
                populateEmployees(emp);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        if (Objects.equals(comboEmp.getSelectionModel().getSelectedItem(), "Адрес")) {
            try {
                ObservableList<Employee> emp = EmployeeDAO.searchAddressEmployee(searchEmpTextfield.getText());
                populateEmployees(emp);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        if (Objects.equals(comboEmp.getSelectionModel().getSelectedItem(), "Опыт")) {
            try {
                ObservableList<Employee> emp = EmployeeDAO.searchExprEmployee(searchEmpTextfield.getText());
                populateEmployees(emp);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        if (Objects.equals(comboEmp.getSelectionModel().getSelectedItem(), "Должность")) {
            try {
                ObservableList<Employee> emp = EmployeeDAO.searchPositionEmployee(searchEmpTextfield.getText());
                populateEmployees(emp);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        if (Objects.equals(comboEmp.getSelectionModel().getSelectedItem(), "Расписание")) {
            try {
                ObservableList<Employee> emp = EmployeeDAO.searchScheduleEmployee(searchEmpTextfield.getText());
                populateEmployees(emp);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
    }
    @FXML
    private void searchEmployees(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            ObservableList<Employee> empData = EmployeeDAO.searchEmployees();
            populateEmployees(empData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }






    public void updateFio(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
       if (comboChangeEmp.getSelectionModel().getSelectedItem().equals("ФИО")) {
           Employee employee = EmployeeDAO.updateFio(oldEmpValue.getText(), newEmpValue.getText());
           populateAndShowEmployee(employee);
       }
        if (comboChangeEmp.getSelectionModel().getSelectedItem().equals("Опыт")) {
            Employee employee = EmployeeDAO.updateExpr(oldEmpValue.getText(), newEmpValue.getText());
            populateAndShowEmployee(employee);
        }
        if (comboChangeEmp.getSelectionModel().getSelectedItem().equals("Телефон")) {
            Employee employee = EmployeeDAO.updatePhone(oldEmpValue.getText(), newEmpValue.getText());
            populateAndShowEmployee(employee);
        }
        if (comboChangeEmp.getSelectionModel().getSelectedItem().equals("Адрес")) {
            Employee employee = EmployeeDAO.updateAddress(oldEmpValue.getText(), newEmpValue.getText());
            populateAndShowEmployee(employee);
        }
        if (comboChangeEmp.getSelectionModel().getSelectedItem().equals("Расписание")) {
            Employee employee = EmployeeDAO.updateSchedule(oldEmpValue.getText(), newEmpValue.getText());
            populateAndShowEmployee(employee);
        }
        if (comboChangeEmp.getSelectionModel().getSelectedItem().equals("Должность")) {
            Employee employee = EmployeeDAO.updatePosition(oldEmpValue.getText(), newEmpValue.getText());
            populateAndShowEmployee(employee);
        }
    }

    @FXML
    private void populateEmployee(Employee emp) {
        ObservableList<Employee> empData = FXCollections.observableArrayList();
        empData.add(emp);
        employeeTable.setItems(empData);
    }

    @FXML
    private void populateAndShowEmployee(Employee emp) {
        if (emp != null) {
            populateEmployee(emp);
        }
    }

    @FXML
    private void populateEmployees(ObservableList<Employee> empData) {
        employeeTable.setItems(empData);

    }



    @FXML
    private void daysEmployee(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            if( (Objects.equals(daysCombo.getSelectionModel().getSelectedItem(), "понедельник"))|| (Objects.equals(daysCombo.getSelectionModel().getSelectedItem(), "вторник"))||
                    (Objects.equals(daysCombo.getSelectionModel().getSelectedItem(), "среда"))|| (Objects.equals(daysCombo.getSelectionModel().getSelectedItem(), "четверг"))||
                    (Objects.equals(daysCombo.getSelectionModel().getSelectedItem(), "пятница"))|| (Objects.equals(daysCombo.getSelectionModel().getSelectedItem(), "суббота"))||
                    (Objects.equals(daysCombo.getSelectionModel().getSelectedItem(), "воскресенье"))) {
                ObservableList<Employee> emp = EmployeeDAO.daysEmployee(daysCombo.getSelectionModel().getSelectedItem());
                populateEmployees(emp);
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка вывода");
                alert.setContentText("Выберите день недели");
                alert.setHeight(200);
                alert.showAndWait();
            }
        } catch (SQLException e) {
            System.out.print("Problem occurred while search employees " + e);
            throw e;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void insertEmployee(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            EmployeeDAO.insertEmp(fioEmptxt.getText(), phoneEmptxt.getText(),
                    addressEmpTextfield.getText(), exprEmptxt.getText(), positionEmpCombo.getSelectionModel().getSelectedItem(), scheduleEmptxt.getText());
            System.out.print("Employee inserted! \n");
        } catch (SQLException e) {
            System.out.print("Problem occurred while inserting employee " + e);
            throw e;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteEmployee(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            EmployeeDAO.deleteEmp(deleteEmpTextfield.getText(), comboDeleteEmp);
            System.out.print("Employee deleted! Employee FIO: " + deleteEmpTextfield.getText() + "\n");
        } catch (SQLException e) {
            System.out.print("Problem occurred while deleting employee " + e);
            throw e;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void searchInventory(ActionEvent actionEvent) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
       if (comboSearchInv.getSelectionModel().getSelectedItem().equals("Наименование")) {
           try {

               ObservableList<Inventory> inventory = InventoryDAO.searchNameInventory(searchInventoryTextfield.getText());
               populateInventoryS(inventory);
           } catch (SQLException e) {
               e.printStackTrace();
               throw e;
           }
       }
        if (comboSearchInv.getSelectionModel().getSelectedItem().equals("Стоимость")) {
            try {

                ObservableList<Inventory> inventory = InventoryDAO.searchCostInventory(searchInventoryTextfield.getText());
                populateInventoryS(inventory);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        if (comboSearchInv.getSelectionModel().getSelectedItem().equals("Количество")) {
            try {

                ObservableList<Inventory> inventory = InventoryDAO.searchQuantityInventory(searchInventoryTextfield.getText());
                populateInventoryS(inventory);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
    }

    @FXML
    private void searchInventoryS(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            ObservableList<Inventory> inventoryData = InventoryDAO.searchInventorys();
            populateInventoryS(inventoryData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting inventoryS information from DB.\n" + e);
            throw e;
        }
    }


    @FXML
    private void populateInventory(Inventory inventory) {
        ObservableList<Inventory> inventoryData = FXCollections.observableArrayList();
        inventoryData.add(inventory);
        inventoryTable.setItems(inventoryData);
    }

    @FXML
    private void populateAndShowInventory(Inventory inventory) {
        if (inventory != null) {
            populateInventory(inventory);
        }
    }
    @FXML
    private void populateInventoryS(ObservableList<Inventory> invData) {
        inventoryTable.setItems(invData);
    }

    @FXML
    private void deleteInventory(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (deleteInvCombo.getSelectionModel().getSelectedItem().equals("Наименование")) {
            try {
                InventoryDAO.deleteNameInv(deleteInvTxt.getText());
                System.out.print("Employee deleted! Employee FIO: " + deleteEmpTextfield.getText() + "\n");
            } catch (SQLException e) {
                System.out.print("Problem occurred while deleting employee " + e);
                throw e;
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
        if (deleteInvCombo.getSelectionModel().getSelectedItem().equals("Стоимость")) {
            try {
                InventoryDAO.deleteCostInv(deleteInvTxt.getText());
                System.out.print("Employee deleted! Employee FIO: " + deleteEmpTextfield.getText() + "\n");
            } catch (SQLException e) {
                System.out.print("Problem occurred while deleting employee " + e);
                throw e;
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
        if (deleteInvCombo.getSelectionModel().getSelectedItem().equals("Количество")) {
            try {
                InventoryDAO.deleteQuantityInv(deleteInvTxt.getText());
                System.out.print("Inventory deleted! Inventory cost: " + deleteEmpTextfield.getText() + "\n");
            } catch (SQLException e) {
                System.out.print("Problem occurred while deleting inventory " + e);
                throw e;
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void insertInventory(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            InventoryDAO.insertInventory(nameInvTxt.getText(),Integer.valueOf(quantityInvTxt.getText()),Integer.valueOf(costInvTxt.getText()));
            System.out.print("Inventory inserted! \n");
        } catch (SQLException e) {
            System.out.print("Problem occurred while inserting inventory " + e);
            throw e;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void updateInventory(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (changeInvCombo.getSelectionModel().getSelectedItem().equals("Наименование")) {
            Inventory inventory = InventoryDAO.updateInvName(oldInvValue.getText(), newInvValue.getText());
            populateAndShowInventory(inventory);
        }
        if (changeInvCombo.getSelectionModel().getSelectedItem().equals("Количество")) {
            Inventory inventory = InventoryDAO.updateInvQuantity(oldInvValue.getText(), newInvValue.getText());
            populateAndShowInventory(inventory);
        }
        if (changeInvCombo.getSelectionModel().getSelectedItem().equals("Стоимость")) {
            Inventory inventory = InventoryDAO.updateInvCost(oldInvValue.getText(), newInvValue.getText());
            populateAndShowInventory(inventory);
        }
    }






    @FXML
    private void searchVip(ActionEvent actionEvent) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        if (comboSearchVip.getSelectionModel().getSelectedItem().equals("ФИО")) {
            try {

                ObservableList<Vip> vip = VipDAO.searchVipFio(searchVipTxt.getText());
                populateVips(vip);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        if (comboSearchVip.getSelectionModel().getSelectedItem().equals("Телефон")) {
            try {

                ObservableList<Vip> vip = VipDAO.searchVipPhone(searchVipTxt.getText());
                populateVips(vip);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        if (comboSearchVip.getSelectionModel().getSelectedItem().equals("Дата")) {
            try {

                ObservableList<Vip> vip = VipDAO.searchVipDate(searchVipTxt.getText());
                populateVips(vip);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        if (comboSearchVip.getSelectionModel().getSelectedItem().equals("Скидка")) {
            try {

                ObservableList<Vip> vip = VipDAO.searchVipDiscount(searchVipTxt.getText());
                populateVips(vip);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        if (comboSearchVip.getSelectionModel().getSelectedItem().equals("Сумма")) {
            try {

                ObservableList<Vip> vip = VipDAO.searchVipSum(searchVipTxt.getText());
                populateVips(vip);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        if (comboSearchVip.getSelectionModel().getSelectedItem().equals("Карта")) {
            try {

                ObservableList<Vip> vip = VipDAO.searchVipCard(searchVipTxt.getText());
                populateVips(vip);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
    }

    @FXML
    private void searchVips(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            ObservableList<Vip> vipData = VipDAO.searchVips();
            populateVips(vipData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting vips information from DB.\n" + e);
            throw e;
        }
    }

    @FXML
    private void populateVip(Vip vip) {
        ObservableList<Vip> vipData = FXCollections.observableArrayList();
        vipData.add(vip);
        vipTable.setItems(vipData);
    }

    @FXML
    private void populateAndShowVip(Vip vip) {
        if (vip != null) {
            populateVip(vip);
        }
    }

    @FXML
    private void populateVips(ObservableList<Vip> vipData) {
        vipTable.setItems(vipData);
    }


    @FXML
    private void insertVip() throws SQLException, ClassNotFoundException {
        try {
            VipDAO.insertVip(fioVipTxt.getText(),phoneVipTxt.getText(), Integer.valueOf(discountVipTxt.getText()), Integer.valueOf(sumVipTxt.getText()),dateVip.getValue().toString(),cardVipTxt.getText());
            System.out.print("Kind inserted! \n");
        } catch (SQLException e) {
            System.out.print("Problem occurred while inserting kind " + e);
            throw e;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    @FXML void raiseDiscount() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        try{
            VipDAO.raiseDiscount();
            System.out.print("Discount is raised");
        }catch (SQLException e) {
            System.out.print("Problem occurred while inserting kind " + e);
            throw e;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteVip(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
       if (comboDeleteVip.getSelectionModel().getSelectedItem().equals("ФИО")) {
           try {
               VipDAO.deleteFioVip(deleteVipTxt.getText());
           } catch (SQLException e) {
               System.out.print("Problem occurred while deleting vip " + e);
               throw e;
           } catch (IllegalAccessException | InstantiationException e) {
               e.printStackTrace();
           }
       }
        if (comboDeleteVip.getSelectionModel().getSelectedItem().equals("Телефон")) {
            try {
                VipDAO.deletePhoneVip(deleteVipTxt.getText());
            } catch (SQLException e) {
                System.out.print("Problem occurred while deleting vip " + e);
                throw e;
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
        if (comboDeleteVip.getSelectionModel().getSelectedItem().equals("Дата")) {
            try {
                VipDAO.deleteDateVip(deleteVipTxt.getText());
            } catch (SQLException e) {
                System.out.print("Problem occurred while deleting vip " + e);
                throw e;
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
        if (comboDeleteVip.getSelectionModel().getSelectedItem().equals("Скидка")) {
            try {
                VipDAO.deleteDiscountVip(deleteVipTxt.getText());
            } catch (SQLException e) {
                System.out.print("Problem occurred while deleting vip " + e);
                throw e;
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
        if (comboDeleteVip.getSelectionModel().getSelectedItem().equals("Сумма")) {
            try {
                VipDAO.deleteSumVip(deleteVipTxt.getText());
            } catch (SQLException e) {
                System.out.print("Problem occurred while deleting vip " + e);
                throw e;
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }if (comboDeleteVip.getSelectionModel().getSelectedItem().equals("Карта")) {
            try {
                VipDAO.deleteCardVip(deleteVipTxt.getText());
            } catch (SQLException e) {
                System.out.print("Problem occurred while deleting vip " + e);
                throw e;
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateVip(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (changeVipCombo.getSelectionModel().getSelectedItem().equals("ФИО")) {
            Vip vip = VipDAO.updateVipFio(oldVipValue.getText(), newVipValue.getText());
            populateAndShowVip(vip);
        }
        if (changeVipCombo.getSelectionModel().getSelectedItem().equals("Телефон")) {
            Vip vip = VipDAO.updateVipPhone(oldVipValue.getText(), newVipValue.getText());
            populateAndShowVip(vip);
        }
        if (changeVipCombo.getSelectionModel().getSelectedItem().equals("Дата")) {
            Vip vip = VipDAO.updateVipDate(oldVipValue.getText(), newVipValue.getText());
            populateAndShowVip(vip);
        }
        if (changeVipCombo.getSelectionModel().getSelectedItem().equals("Скидка")) {
            Vip vip = VipDAO.updateVipDiscount(oldVipValue.getText(), newVipValue.getText());
            populateAndShowVip(vip);
        }
        if (changeVipCombo.getSelectionModel().getSelectedItem().equals("Сумма")) {
            Vip vip = VipDAO.updateVipSum(oldVipValue.getText(), newVipValue.getText());
            populateAndShowVip(vip);
        }
        if (changeVipCombo.getSelectionModel().getSelectedItem().equals("Карта")) {
            Vip vip = VipDAO.updateVipCard(oldVipValue.getText(), newVipValue.getText());
            populateAndShowVip(vip);
        }
    }




    @FXML
    private void searchAssortment(ActionEvent actionEvent) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        if (Objects.equals(searchAssortCombo.getSelectionModel().getSelectedItem(), "Название"))
        try {
            ObservableList<Assortment> emp = AssortmentDAO.searchNameAssortment(searchAssortTxt.getText());
            populateAssortments(emp);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        if (Objects.equals(searchAssortCombo.getSelectionModel().getSelectedItem(), "Стоимость"))
            try {
                ObservableList<Assortment> emp = AssortmentDAO.searchCostAssortment(searchAssortTxt.getText());
                populateAssortments(emp);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        if (Objects.equals(searchAssortCombo.getSelectionModel().getSelectedItem(), "Описание"))
            try {
                ObservableList<Assortment> emp = AssortmentDAO.searchDescriptionAssortment(searchAssortTxt.getText());
                populateAssortments(emp);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        if (Objects.equals(searchAssortCombo.getSelectionModel().getSelectedItem(), "Вид"))
            try {
                ObservableList<Assortment> emp = AssortmentDAO.searchKindAssortment(searchAssortTxt.getText());
                populateAssortments(emp);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
    }

    @FXML
    private void searchAssortments(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            ObservableList<Assortment> assortData = AssortmentDAO.searchAssortments();
            populateAssortments(assortData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting assortment information from DB.\n" + e);
            throw e;
        }
    }

    @FXML
    private void populateAssortment(Assortment assortment) {
        ObservableList<Assortment> assortData = FXCollections.observableArrayList();
        assortData.add(assortment);
        assortmentTable.setItems(assortData);
    }

    @FXML
    private void populateAndShowAssortment(Assortment assortment) {
        if (assortment != null) {
            populateAssortment(assortment);
        }
    }

    @FXML
    private void populateAssortments(ObservableList<Assortment> assortData) {
        assortmentTable.setItems(assortData);
    }


    @FXML
    private void deleteAssortment(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            AssortmentDAO.deleteAssortment(assortDeleteTxt.getText(), comboDeleteAssort.getSelectionModel().getSelectedItem());
        } catch (SQLException e) {
            System.out.print("Problem occurred while deleting assortment " + e);
            throw e;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void insertAssort(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            AssortmentDAO.insertAssort(nameAssortTxt.getText(),costAssortTxt.getText(), descriptionAssortTxt.getText(),
                    comboKindAssort.getSelectionModel().getSelectedIndex());
            System.out.print("Assortment inserted! \n");
        } catch (SQLException e) {
            System.out.print("Problem occurred while inserting assortment " + e);
            throw e;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void updateAssortment(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (assortChangeCombo.getSelectionModel().getSelectedItem().equals("Наименование")) {
            Assortment assortment = AssortmentDAO.updateAssortName(assortOldValue.getText(), assortNewValue.getText());
            populateAndShowAssortment(assortment);
        }
        if (assortChangeCombo.getSelectionModel().getSelectedItem().equals("Стоимость")) {
            Assortment assortment = AssortmentDAO.updateAssortCost(assortOldValue.getText(), assortNewValue.getText());
            populateAndShowAssortment(assortment);
        }
        if (assortChangeCombo.getSelectionModel().getSelectedItem().equals("Описание")) {
            Assortment assortment = AssortmentDAO.updateAssortDescription(assortOldValue.getText(), assortNewValue.getText());
            populateAndShowAssortment(assortment);
        }
        if (assortChangeCombo.getSelectionModel().getSelectedItem().equals("Вид")) {
            Assortment assortment = AssortmentDAO.updateAssortKind(assortOldValue.getText(), assortNewValue.getText());
            populateAndShowAssortment(assortment);
        }
    }




    @FXML
    private void searchKind(ActionEvent actionEvent) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        if (Objects.equals(searchKindCombo.getSelectionModel().getSelectedItem(), "Название"))
            try {
            ObservableList<Kind> kind = KindDAO.searchNameKind(searchKindTxt.getText());
            populateKinds(kind);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        if (Objects.equals(searchKindCombo.getSelectionModel().getSelectedItem(), "Градус"))
            try {
                ObservableList<Kind> kind = KindDAO.searchGradeKind(searchKindTxt.getText());
                populateKinds(kind);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
    }

    @FXML
    private void searchKinds(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            ObservableList<Kind> kindData = KindDAO.searchKinds();
            populateKinds(kindData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting vips information from DB.\n" + e);
            throw e;
        }
    }


    @FXML
    private void populateKind(Kind kind) {
        ObservableList<Kind> kindData = FXCollections.observableArrayList();
        kindData.add(kind);
        kindTable.setItems(kindData);
    }

    @FXML
    private void populateAndShowKind(Kind kind) {
        if (kind != null) {
            populateKind(kind);
        }
    }

    @FXML
    private void populateKinds(ObservableList<Kind> kindData) {
        kindTable.setItems(kindData);
    }


    @FXML
    private void insertKind(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            KindDAO.insertKind(kindNameTxt.getText(), kindGradeTxt.getText());
            System.out.print("Kind inserted! \n");
        } catch (SQLException e) {
            System.out.print("Problem occurred while inserting kind " + e);
            throw e;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteKind(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            KindDAO.deleteKind(deleteKindTxt.getText());
        } catch (SQLException e) {
            System.out.print("Problem occurred while deleting kind " + e);
            throw e;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void updateKind(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (changeKindCombo.getSelectionModel().getSelectedItem().equals("Наименование")) {
            Kind kind = KindDAO.updateName(oldKindValue.getText(), newKindValue.getText());
            populateAndShowKind(kind);
        }
        if (changeKindCombo.getSelectionModel().getSelectedItem().equals("Градус")) {
            Kind kind = KindDAO.updateGrade(oldKindValue.getText(), newKindValue.getText());
            populateAndShowKind(kind);
        }
    }




    @FXML
    private void graphic() throws IOException {
        Stage stage = new Stage();
        AnchorPane rootLayout = FXMLLoader.load(GraphicController.class.getClass().getResource("/resources/view/graphic.fxml"));
        stage.setScene(new Scene(rootLayout,600,400));
        stage.show();
    }





    @FXML
    private void searchSale(ActionEvent actionEvent) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        if (Objects.equals(searchSaleCombo.getSelectionModel().getSelectedItem(), "Сотрудник")) {
            try {
                ObservableList<Sale> sale = SaleDAO.searchEmpSale(searchSaleTxt.getText());
                populateSales(sale);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        if (Objects.equals(searchSaleCombo.getSelectionModel().getSelectedItem(), "Дата")) {
            try {
                ObservableList<Sale> sale = SaleDAO.searchDateSale(searchSaleTxt.getText());
                populateSales(sale);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        if (Objects.equals(searchSaleCombo.getSelectionModel().getSelectedItem(), "Сумма")) {
            try {
                ObservableList<Sale> sale = SaleDAO.searchSumSale(searchSaleTxt.getText());
                populateSales(sale);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        if (Objects.equals(searchSaleCombo.getSelectionModel().getSelectedItem(), "Vip")) {
            try {
                ObservableList<Sale> sale = SaleDAO.searchVipSale(searchSaleTxt.getText());
                populateSales(sale);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
    }
    @FXML
    private void searchSales(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            ObservableList<Sale> saleData = SaleDAO.searchSales();
            populateSales(saleData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting sales information from DB.\n" + e);
            throw e;
        }
    }



    public void updateSale(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (changeSaleCombo.getSelectionModel().getSelectedItem().equals("Сотрудник")) {
            Sale sale = SaleDAO.updateSaleEmp(oldValueTxt.getText(), newValueTxt.getText());
            populateAndShowSale(sale);
        }
        if (changeSaleCombo.getSelectionModel().getSelectedItem().equals("Дата")) {
            Sale sale = SaleDAO.updateSaleDate(oldValueTxt.getText(), newValueTxt.getText());
            populateAndShowSale(sale);
        }
        if (changeSaleCombo.getSelectionModel().getSelectedItem().equals("Сумма")) {
            Sale sale = SaleDAO.updateSaleSum(oldValueTxt.getText(), newValueTxt.getText());
            populateAndShowSale(sale);
        }
        if (changeSaleCombo.getSelectionModel().getSelectedItem().equals("Vip")) {
            Sale sale = SaleDAO.updateSaleVip(oldValueTxt.getText(), newValueTxt.getText());
            populateAndShowSale(sale);
        }
    }


    @FXML
    private void populateSale(Sale sale) {
        ObservableList<Sale> saleData = FXCollections.observableArrayList();
        saleData.add(sale);
        saleTable.setItems(saleData);
    }

    @FXML
    private void populateAndShowSale(Sale sale) {
        if (sale != null) {
            populateSale(sale);
        }
    }

    @FXML
    private void populateSales(ObservableList<Sale> saleData) {
        saleTable.setItems(saleData);
    }
    @FXML
    private void insertSale(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            SaleDAO.insertSale(empSaleCombo.getSelectionModel().getSelectedIndex(),dateSale.getValue().toString(), Integer.valueOf(sumSaleTxt.getText()),
                    Integer.parseInt(vipSaleCombo.getSelectionModel().getSelectedItem()));
            System.out.print("Employee inserted! \n");
        } catch (SQLException e) {
            System.out.print("Problem occurred while inserting employee " + e);
            throw e;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void discountDays(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            SaleDAO.discountDays();
            System.out.print("Discount raised! \n");
        } catch (SQLException e) {
            System.out.print("Problem occurred while discount raising " + e);
            throw e;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void deleteSale(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        try {
            SaleDAO.deleteSale(deleteSaleTxt.getText(), deleteSaleCombo.getSelectionModel().getSelectedItem());
            System.out.print("Employee deleted! Employee FIO: " + deleteEmpTextfield.getText() + "\n");
        } catch (SQLException e) {
            System.out.print("Problem occurred while deleting employee " + e);
            throw e;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void searchDetailsSale(ActionEvent actionEvent) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        if (Objects.equals(searchDetailsCombo.getSelectionModel().getSelectedItem(), "Номер продажи"))
            try {
                ObservableList<Details> details = DetailsDAO.searchDetailsSale(searchDetailsTxt.getText());
                populateDetails(details);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        if (Objects.equals(searchDetailsCombo.getSelectionModel().getSelectedItem(), "Количество"))
            try {
                ObservableList<Details> details = DetailsDAO.searchDetailsQuantity(Integer.parseInt(searchDetailsTxt.getText()));
                populateDetails(details);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        if (Objects.equals(searchDetailsCombo.getSelectionModel().getSelectedItem(), "Продукт"))
            try {
                ObservableList<Details> details = DetailsDAO.searchDetailsProduct(searchDetailsTxt.getText());
                populateDetails(details);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        if (Objects.equals(searchDetailsCombo.getSelectionModel().getSelectedItem(), "Инвентарь"))
            try {
                ObservableList<Details> details = DetailsDAO.searchDetailsInventory(searchDetailsTxt.getText());
                populateDetails(details);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
    }

    @FXML
    private void searchDetails() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            ObservableList<Details> assortData = DetailsDAO.searchDetails();
            populateDetails(assortData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting details information from DB.\n" + e);
            throw e;
        }
    }

    @FXML
    private void populateDetail(Details details) {
        ObservableList<Details> detailsData = FXCollections.observableArrayList();
        detailsData.add(details);
        detailsTable.setItems(detailsData);
    }

    @FXML
    private void populateAndShowDetail(Details details) {
        if (details != null) {
            populateDetail(details);
        }
    }

    @FXML
    private void populateDetails(ObservableList<Details> detailsData) {
        detailsTable.setItems(detailsData);
    }


    ObservableList<String> employeeNameList = FXCollections.observableArrayList();
    public void selectEmployee() throws IOException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            String nammList = "Select fio from employees order by id";
            try (ResultSet rs = DBConnection.dbExecuteQuery(nammList)) {
                while (rs.next()) {
                    String inv = rs.getString(1);
                    employeeNameList.add(inv);
                }
            }
        }catch (Exception e){
        }
    }



    ObservableList<String> inventoryNameList = FXCollections.observableArrayList();
    public void selectInventory() throws IOException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            String nammList = "Select name_inventory from inventory";
            try (ResultSet rs = DBConnection.dbExecuteQuery(nammList)) {
                while (rs.next()) {
                    String inv = rs.getString(1);
                    inventoryNameList.add(inv);
                }
            }
        }catch (Exception e){
        }
    }

    ObservableList<String> productNameList = FXCollections.observableArrayList();
    public void selectProduct() throws IOException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            String nammList = "Select name_assort from assortment";
            try (ResultSet rs = DBConnection.dbExecuteQuery(nammList)) {
                while (rs.next()) {
                    String inv = rs.getString(1);
                    productNameList.add(inv);
                }
            }
        }catch (Exception e){
        }
    }


    ObservableList<String> quantityNameList = FXCollections.observableArrayList();
    public void selectQuantity() throws IOException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            String nammList = "Select quantity from sale_details";
            try (ResultSet rs = DBConnection.dbExecuteQuery(nammList)) {
                while (rs.next()) {
                    String inv = rs.getString(1);
                    quantityNameList.add(inv);
                }
            }
        }catch (Exception e){
        }
    }

    ObservableList<String> saleNameList = FXCollections.observableArrayList();
    public void selectSale() throws IOException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            String nammList = "Select id_sale from sale_details";
            try (ResultSet rs = DBConnection.dbExecuteQuery(nammList)) {
                while (rs.next()) {
                    String inv = rs.getString(1);
                    saleNameList.add(inv);
                }
            }
        }catch (Exception e){
        }
    }

    ObservableList<String> vipCardList = FXCollections.observableArrayList();
    public void selectVipCard() throws IOException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            String nammList = "Select card_number from \"VIP\" ";
            try (ResultSet rs = DBConnection.dbExecuteQuery(nammList)) {
                while (rs.next()) {
                    String inv = rs.getString(1);
                    vipCardList.add(inv);
                }
            }
        }catch (Exception e){
        }
    }

    ObservableList<String> kindList = FXCollections.observableArrayList();
    public void selectKind() throws IOException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            String nammList = "Select name_kind from kind";
            try (ResultSet rs = DBConnection.dbExecuteQuery(nammList)) {
                while (rs.next()) {
                    String inv = rs.getString(1);
                    kindList.add(inv);
                }
            }
        } catch (Exception e) {
        }
    }







    ObservableList<String> sumSaleList = FXCollections.observableArrayList();
    public void selectSum() throws IOException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            String nammList = "Select total_cost from sales";
            try (ResultSet rs = DBConnection.dbExecuteQuery(nammList)) {
                while (rs.next()) {
                    String inv = rs.getString(1);
                    sumSaleList.add(inv);
                }
            }
        }catch (Exception e){
        }
    }


    @FXML
    private void deleteDetails(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            DetailsDAO.deleteDetails(deleteDetailsTxt.getText(), deleteDetailsCombo.getSelectionModel().getSelectedItem());
        } catch (SQLException e) {
            System.out.print("Problem occurred while deleting details " + e);
            throw e;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void insertDetails(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            DetailsDAO.insertDetails(detailsSaleCombo.getSelectionModel().getSelectedIndex(),
                    detailsProductCombo.getSelectionModel().getSelectedIndex(),
                    detailsInventoryCombo.getSelectionModel().getSelectedIndex(),
                    detailsQuantityCombo.getSelectionModel().getSelectedItem());
            System.out.print("Assortment inserted! \n");
        } catch (SQLException e) {
            System.out.print("Problem occurred while inserting details " + e);
            throw e;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
    public void updateDetails(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (comboChangeDetails.getSelectionModel().getSelectedItem().equals("Продукт")) {
            Details details = DetailsDAO.updateDetailsProduct(detailsProductCombo.getSelectionModel().getSelectedIndex(),
                    newDetailsCombo.getSelectionModel().getSelectedIndex());
            populateAndShowDetail(details);
        }
        if (comboChangeDetails.getSelectionModel().getSelectedItem().equals("Количество")) {

            Details details = DetailsDAO.updateDetailsQuantity(detailsQuantityCombo.getSelectionModel().getSelectedItem(),
                    newDetailsCombo.getSelectionModel().getSelectedItem());
            populateAndShowDetail(details);
        }
        if (comboChangeDetails.getSelectionModel().getSelectedItem().equals("Инвентарь")) {
            Details details = DetailsDAO.updateDetailsInventory(detailsInventoryCombo.getSelectionModel().getSelectedIndex(),
                    newDetailsCombo.getSelectionModel().getSelectedIndex());
            populateAndShowDetail(details);
        }
    }




    @FXML
    private void initialize() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {

        selectSum();




        selectQuantity();
        selectInventory();
        selectProduct();
        selectSale();
        selectEmployee();
        selectVipCard();
        selectKind();

        detailsQuantityCombo.getItems().addAll(quantityNameList);
        detailsInventoryCombo.getItems().addAll(inventoryNameList);
        detailsProductCombo.getItems().addAll(productNameList);
        detailsSaleCombo.getItems().addAll(saleNameList);
        empSaleCombo.getItems().addAll(employeeNameList);
        comboKindAssort.getItems().addAll(kindList);



        comboChangeDetails.setOnAction((event -> {
            switch (comboChangeDetails.getSelectionModel().getSelectedItem()) {
                case "Продукт":
                    detailsProductCombo.setVisible(true);
                    detailsInventoryCombo.setVisible(false);
                    detailsQuantityCombo.setVisible(false);
                    newDetailsCombo.setVisible(true);
                    newDetailsCombo.getItems().clear();
                    newDetailsCombo.getItems().addAll(productNameList);
                    break;
                case "Инвентарь":
                    detailsProductCombo.setVisible(false);
                    detailsInventoryCombo.setVisible(true);
                    newDetailsCombo.setVisible(true);
                    newDetailsCombo.getItems().clear();
                    newDetailsCombo.getItems().addAll(inventoryNameList);
                    break;
                case "Количество":
                    detailsProductCombo.setVisible(false);
                    detailsInventoryCombo.setVisible(false);
                    detailsQuantityCombo.setVisible(true);
                    newDetailsCombo.setVisible(true);
                    newDetailsCombo.getItems().clear();
                    newDetailsCombo.getItems().addAll(quantityNameList);
                    break;
            }
        }));



        empFioColumn.setCellValueFactory(cellData -> cellData.getValue().fioProperty());
        empExprColumn.setCellValueFactory(cellData -> cellData.getValue().exprProperty());
        empPhoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        empAddressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        empPositionColumn.setCellValueFactory(cellData -> cellData.getValue().positionProperty());
        empScheduleColumn.setCellValueFactory(cellData -> cellData.getValue().scheduleProperty());
        employeeTable.getColumns().get(0).setVisible(false);

        inventoryNameColumn.setCellValueFactory(cellData -> cellData.getValue().inventoryNameProperty());
        inventoryQuantityColumn.setCellValueFactory(cellData -> cellData.getValue().inventoryQuantityProperty().asObject());
        inventoryCostColumn.setCellValueFactory(cellData -> cellData.getValue().inventoryCostProperty().asObject());
        inventoryTable.getColumns().get(0).setVisible(false);

        vipIdColumn.setCellValueFactory(cellData -> cellData.getValue().vipIdProperty().asObject());
        vipFioColumn.setCellValueFactory(cellData -> cellData.getValue().fioVipProperty());
        vipPhoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneVipProperty());
        vipDiscountColumn.setCellValueFactory(cellData -> cellData.getValue().discountProperty().asObject());
        vipSumColumn.setCellValueFactory(cellData -> cellData.getValue().sumProperty().asObject());
        vipCardColumn.setCellValueFactory(cellData -> cellData.getValue().cardProperty());
        vipDateColumn.setCellValueFactory(cellData -> cellData.getValue().dateVipProperty());
        vipTable.getColumns().get(0).setVisible(false);

        assortNameColumn.setCellValueFactory(cellData -> cellData.getValue().assortNameProperty());
        assortCostColumn.setCellValueFactory(cellData -> cellData.getValue().costProperty().asObject());
        assortDescriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        assortKindColumn.setCellValueFactory(cellData -> cellData.getValue().assortKindProperty());
        assortmentTable.getColumns().get(0).setVisible(false);

        nameKindColumn.setCellValueFactory(cellData -> cellData.getValue().nameKindVipProperty());
        gradeKindColumn.setCellValueFactory(cellData -> cellData.getValue().gradeProperty());
        kindTable.getColumns().get(0).setVisible(false);

        saleIdColumn.setCellValueFactory(cellData -> cellData.getValue().saleIdProperty().asObject());
        saleEmployeeColumn.setCellValueFactory(cellData -> cellData.getValue().saleEmpProperty());
        saleDateColumn.setCellValueFactory(cellData -> cellData.getValue().saleDateProperty());
        saleSumColumn.setCellValueFactory(cellData -> cellData.getValue().saleSumProperty().asObject());
        saleCardColumn.setCellValueFactory(cellData -> cellData.getValue().sale_cardProperty());

        detailsSale.setCellValueFactory(cellData -> cellData.getValue().detailsSaleProperty());
        detailsQuantity.setCellValueFactory(cellData -> cellData.getValue().detailsQuantityProperty().asObject());
        detailsProduct.setCellValueFactory(cellData -> cellData.getValue().detailsProductProperty());
        detailsInventory.setCellValueFactory(cellData -> cellData.getValue().detailsInventoryProperty());


        daysCombo.getItems().addAll("понедельник","вторник","среда","четверг","пятница","суббота","воскресенье");



        comboChangeEmp.getItems().addAll("ФИО","Опыт","Телефон","Адрес","Расписание","Должность");
        changeKindCombo.getItems().addAll("Наименование", "Градус");
        assortChangeCombo.getItems().addAll("Наименование","Стоимость","Описание","Вид");

        positionEmpCombo.getItems().addAll("Официант","Повар", "Администратор", "Бармен");
        comboDeleteEmp.getItems().addAll("ФИО", "Телефон", "Адрес", "Должность");
        comboEmp.getItems().addAll("ФИО","Телефон","Адрес","Опыт","Должность","Расписание");
        comboDeleteAssort.getItems().addAll("Название","Стоимость","Описание","Вид");
        searchAssortCombo.getItems().addAll("Название", "Стоимость", "Описание", "Вид");

        searchKindCombo.getItems().addAll("Название","Градус");
        comboDeleteVip.getItems().addAll("ФИО", "Телефон", "Дата", "Скидка","Сумма","Карта");
        changeVipCombo.getItems().addAll("ФИО", "Телефон", "Дата", "Скидка","Сумма","Карта");
        comboSearchVip.getItems().addAll("ФИО", "Телефон", "Дата", "Скидка","Сумма","Карта");

        deleteInvCombo.getItems().addAll("Наименование","Количество","Стоимость");
        changeInvCombo.getItems().addAll("Наименование","Количество","Стоимость");
        comboSearchInv.getItems().addAll("Наименование","Количество","Стоимость");

        deleteSaleCombo.getItems().addAll("Сотрудник", "Дата", "Сумма", "Vip");
        changeSaleCombo.getItems().addAll("Сотрудник", "Дата", "Сумма", "Vip");
        searchSaleCombo.getItems().addAll("Сотрудник", "Дата", "Сумма", "Vip");

        searchDetailsCombo.getItems().addAll("Номер продажи","Продукт","Инвентарь","Количество");
        comboChangeDetails.getItems().addAll("Продукт","Инвентарь","Количество");
        deleteDetailsCombo.getItems().addAll("Номер продажи","Продукт","Инвентарь","Количество");


        vipSaleCombo.getItems().addAll(vipCardList);



        detailsChooseCombo.getItems().addAll("Поиск", "Изменение", "Удаление", "Добавление");
        detailsChooseCombo.setOnAction((event -> {
            switch (detailsChooseCombo.getSelectionModel().getSelectedItem()){
                case "Поиск":
                    searchDetailsBtn.setVisible(true);
                    searchDetailsCombo.setVisible(true);
                    searchDetailsTxt.setVisible(true);

                    deleteDetailsBtn.setVisible(false);
                    deleteDetailsCombo.setVisible(false);
                    deleteDetailsTxt.setVisible(false);

                    addDetailsBtn.setVisible(false);
                    detailsProductCombo.setVisible(false);
                    detailsInventoryCombo.setVisible(false);
                    detailsQuantityCombo.setVisible(false);
                    detailsSaleCombo.setVisible(false);

                    changeDetailsBtn.setVisible(false);
                    newDetailsCombo.setVisible(false);
                    oldDetailsCombo.setVisible(false);
                    comboChangeDetails.setVisible(false);
                    break;
                case "Удаление":
                    searchDetailsBtn.setVisible(false);
                    searchDetailsCombo.setVisible(false);
                    searchDetailsTxt.setVisible(false);

                    deleteDetailsBtn.setVisible(true);
                    deleteDetailsCombo.setVisible(true);
                    deleteDetailsTxt.setVisible(true);

                    addDetailsBtn.setVisible(false);
                    detailsProductCombo.setVisible(false);
                    detailsInventoryCombo.setVisible(false);
                    detailsQuantityCombo.setVisible(false);
                    detailsSaleCombo.setVisible(false);

                    changeDetailsBtn.setVisible(false);
                    newDetailsCombo.setVisible(false);
                    oldDetailsCombo.setVisible(false);
                    comboChangeDetails.setVisible(false);
                    break;
                case "Добавление":
                    searchDetailsBtn.setVisible(false);
                    searchDetailsCombo.setVisible(false);
                    searchDetailsTxt.setVisible(false);

                    deleteDetailsBtn.setVisible(false);
                    deleteDetailsCombo.setVisible(false);
                    deleteDetailsTxt.setVisible(false);

                    addDetailsBtn.setVisible(true);
                    detailsProductCombo.setVisible(true);
                    detailsInventoryCombo.setVisible(true);
                    detailsQuantityCombo.setVisible(true);
                    detailsSaleCombo.setVisible(true);

                    changeDetailsBtn.setVisible(false);
                    newDetailsCombo.setVisible(false);
                    oldDetailsCombo.setVisible(false);
                    comboChangeDetails.setVisible(false);
                    break;
                case "Изменение":
                    searchDetailsBtn.setVisible(false);
                    searchDetailsCombo.setVisible(false);
                    searchDetailsTxt.setVisible(false);

                    deleteDetailsBtn.setVisible(false);
                    deleteDetailsCombo.setVisible(false);
                    deleteDetailsTxt.setVisible(false);

                    addDetailsBtn.setVisible(false);
                    detailsProductCombo.setVisible(false);
                    detailsInventoryCombo.setVisible(false);
                    detailsQuantityCombo.setVisible(false);
                    detailsSaleCombo.setVisible(false);

                    changeDetailsBtn.setVisible(true);
                    comboChangeDetails.setVisible(true);
            }
        }));





        saleChoose.getItems().addAll("Поиск", "Изменение", "Удаление", "Добавление");
        saleChoose.setOnAction((event -> {
            switch (saleChoose.getSelectionModel().getSelectedItem()){
                case "Поиск":
                    searchSaleBtn.setVisible(true);
                    searchSaleCombo.setVisible(true);
                    searchSaleTxt.setVisible(true);

                    deleteSaleBtn.setVisible(false);
                    deleteSaleCombo.setVisible(false);
                    deleteSaleTxt.setVisible(false);

                    dateSale.setVisible(false);
                    sumSaleTxt.setVisible(false);
                    empSaleCombo.setVisible(false);
                    vipSaleCombo.setVisible(false);
                    addSaleBtn.setVisible(false);

                    changeSaleBtn.setVisible(false);
                    changeSaleCombo.setVisible(false);
                    newValueTxt.setVisible(false);
                    oldValueTxt.setVisible(false);
                    break;
                case "Удаление":
                    searchSaleBtn.setVisible(false);
                    searchSaleCombo.setVisible(false);
                    searchSaleTxt.setVisible(false);

                    deleteSaleBtn.setVisible(true);
                    deleteSaleCombo.setVisible(true);
                    deleteSaleTxt.setVisible(true);

                    dateSale.setVisible(false);
                    sumSaleTxt.setVisible(false);
                    empSaleCombo.setVisible(false);
                    vipSaleCombo.setVisible(false);
                    addSaleBtn.setVisible(false);

                    changeSaleBtn.setVisible(false);
                    changeSaleCombo.setVisible(false);
                    newValueTxt.setVisible(false);
                    oldValueTxt.setVisible(false);
                    break;
                case "Добавление":
                    searchSaleBtn.setVisible(false);
                    searchSaleCombo.setVisible(false);
                    searchSaleTxt.setVisible(false);

                    deleteSaleBtn.setVisible(false);
                    deleteSaleCombo.setVisible(false);
                    deleteSaleTxt.setVisible(false);

                    dateSale.setVisible(true);
                    sumSaleTxt.setVisible(true);
                    empSaleCombo.setVisible(true);
                    vipSaleCombo.setVisible(true);
                    addSaleBtn.setVisible(true);

                    changeSaleBtn.setVisible(false);
                    changeSaleCombo.setVisible(false);
                    newValueTxt.setVisible(false);
                    oldValueTxt.setVisible(false);
                    break;
                case "Изменение":
                    searchSaleBtn.setVisible(false);
                    searchSaleCombo.setVisible(false);
                    searchSaleTxt.setVisible(false);

                    deleteSaleBtn.setVisible(false);
                    deleteSaleCombo.setVisible(false);
                    deleteSaleTxt.setVisible(false);

                    dateSale.setVisible(false);
                    sumSaleTxt.setVisible(false);
                    empSaleCombo.setVisible(false);
                    vipSaleCombo.setVisible(false);
                    addSaleBtn.setVisible(false);

                    changeSaleBtn.setVisible(true);
                    changeSaleCombo.setVisible(true);
                    newValueTxt.setVisible(true);
                    oldValueTxt.setVisible(true);
            }

        }));









        assortChoose.getItems().addAll("Поиск", "Изменение", "Удаление", "Добавление");
        assortChoose.setOnAction((event -> {
            switch (assortChoose.getSelectionModel().getSelectedItem()){
                case "Поиск":
                    searchAssortCombo.setVisible(true);
                    searchAssortButton.setVisible(true);
                    searchAssortTxt.setVisible(true);
                    comboDeleteAssort.setVisible(false);
                    comboKindAssort.setVisible(false);
                    assortDeleteTxt.setVisible(false);
                    deleteAssortButton.setVisible(false);
                    assortChangeCombo.setVisible(false);
                    assortOldValue.setVisible(false);
                    assortUpdateButton.setVisible(false);
                    assortNewValue.setVisible(false);
                    nameAssortTxt.setVisible(false);
                    costAssortTxt.setVisible(false);
                    descriptionAssortTxt.setVisible(false);
                    addAssortButton.setVisible(false);
                    break;
                case "Удаление":
                    searchAssortCombo.setVisible(false);
                    searchAssortButton.setVisible(false);
                    searchAssortTxt.setVisible(false);
                    comboDeleteAssort.setVisible(true);
                    comboKindAssort.setVisible(false);
                    assortDeleteTxt.setVisible(true);
                    deleteAssortButton.setVisible(true);
                    assortChangeCombo.setVisible(false);
                    assortOldValue.setVisible(false);
                    assortUpdateButton.setVisible(false);
                    assortNewValue.setVisible(false);
                    nameAssortTxt.setVisible(false);
                    costAssortTxt.setVisible(false);
                    descriptionAssortTxt.setVisible(false);
                    addAssortButton.setVisible(false);
                    break;
                case "Добавление":

                    searchAssortCombo.setVisible(false);
                    searchAssortButton.setVisible(false);
                    searchAssortTxt.setVisible(false);
                    comboDeleteAssort.setVisible(false);
                    comboKindAssort.setVisible(true);
                    assortDeleteTxt.setVisible(false);
                    deleteAssortButton.setVisible(false);
                    assortChangeCombo.setVisible(false);
                    assortOldValue.setVisible(false);
                    assortUpdateButton.setVisible(false);
                    assortNewValue.setVisible(false);
                    nameAssortTxt.setVisible(true);
                    costAssortTxt.setVisible(true);
                    descriptionAssortTxt.setVisible(true);
                    addAssortButton.setVisible(true);
                    break;
                case "Изменение":
                    searchAssortCombo.setVisible(false);
                    searchAssortButton.setVisible(false);
                    searchAssortTxt.setVisible(false);
                    comboDeleteAssort.setVisible(false);
                    comboKindAssort.setVisible(false);
                    assortDeleteTxt.setVisible(false);
                    deleteAssortButton.setVisible(false);
                    assortChangeCombo.setVisible(true);
                    assortOldValue.setVisible(true);
                    assortUpdateButton.setVisible(true);
                    assortNewValue.setVisible(true);
                    nameAssortTxt.setVisible(false);
                    costAssortTxt.setVisible(false);
                    descriptionAssortTxt.setVisible(false);
                    addAssortButton.setVisible(false);
            }

        }));



        invChoose.getItems().addAll("Поиск", "Изменение", "Удаление", "Добавление");
        invChoose.setOnAction((event -> {
            switch (invChoose.getSelectionModel().getSelectedItem()){
                case "Поиск":
                    comboSearchInv.setVisible(true);
                    searchInventoryTextfield.setVisible(true);
                    searchInvBtn.setVisible(true);

                    addInvBtn.setVisible(false);
                    costInvTxt.setVisible(false);
                    quantityInvTxt.setVisible(false);
                    nameInvTxt.setVisible(false);

                    changeInvCombo.setVisible(false);
                    oldInvValue.setVisible(false);
                    newInvValue.setVisible(false);
                    changeInvBtn.setVisible(false);

                    deleteInvCombo.setVisible(false);
                    deleteInvBtn.setVisible(false);
                    deleteInvTxt.setVisible(false);
                    break;
                case "Удаление":
                    deleteInvCombo.setVisible(true);
                    deleteInvBtn.setVisible(true);
                    deleteInvTxt.setVisible(true);
                    comboSearchInv.setVisible(false);
                    searchInventoryTextfield.setVisible(false);
                    searchInvBtn.setVisible(false);
                    addInvBtn.setVisible(false);
                    costInvTxt.setVisible(false);
                    quantityInvTxt.setVisible(false);
                    nameInvTxt.setVisible(false);
                    changeInvCombo.setVisible(false);
                    oldInvValue.setVisible(false);
                    newInvValue.setVisible(false);
                    changeInvBtn.setVisible(false);
                    break;
                case "Добавление":
                    addInvBtn.setVisible(true);
                    costInvTxt.setVisible(true);
                    quantityInvTxt.setVisible(true);
                    nameInvTxt.setVisible(true);
                    comboSearchInv.setVisible(false);
                    searchInventoryTextfield.setVisible(false);
                    searchInvBtn.setVisible(false);
                    changeInvCombo.setVisible(false);
                    oldInvValue.setVisible(false);
                    newInvValue.setVisible(false);
                    changeInvBtn.setVisible(false);
                    deleteInvCombo.setVisible(false);
                    deleteInvBtn.setVisible(false);
                    deleteInvTxt.setVisible(false);
                    break;
                case "Изменение":
                    changeInvCombo.setVisible(true);
                    oldInvValue.setVisible(true);
                    newInvValue.setVisible(true);
                    changeInvBtn.setVisible(true);

                    comboSearchInv.setVisible(false);
                    searchInventoryTextfield.setVisible(false);
                    searchInvBtn.setVisible(false);
                    addInvBtn.setVisible(false);
                    costInvTxt.setVisible(false);
                    quantityInvTxt.setVisible(false);
                    nameInvTxt.setVisible(false);
                    deleteInvCombo.setVisible(false);
                    deleteInvBtn.setVisible(false);
                    deleteInvTxt.setVisible(false);
            }

        }));


        vipChoose.getItems().addAll("Поиск", "Изменение", "Удаление", "Добавление");
        vipChoose.setOnAction((event -> {
            switch (vipChoose.getSelectionModel().getSelectedItem()){
                case "Поиск":
                    comboSearchVip.setVisible(true);
                    searchVipTxt.setVisible(true);
                    searchVipBtn.setVisible(true);

                    comboDeleteVip.setVisible(false);
                    deleteVipTxt.setVisible(false);
                    deleteVipBtn.setVisible(false);

                    changeVipBtn.setVisible(false);
                    changeVipCombo.setVisible(false);
                    oldVipValue.setVisible(false);
                    newVipValue.setVisible(false);

                    fioVipTxt.setVisible(false);
                    cardVipTxt.setVisible(false);
                    phoneVipTxt.setVisible(false);
                    discountVipTxt.setVisible(false);
                    sumVipTxt.setVisible(false);
                    dateVip.setVisible(false);
                    addVipBtn.setVisible(false);
                    break;
                case "Удаление":
                    comboDeleteVip.setVisible(true);
                    deleteVipTxt.setVisible(true);
                    deleteVipBtn.setVisible(true);
                    changeVipBtn.setVisible(false);
                    changeVipCombo.setVisible(false);
                    oldVipValue.setVisible(false);
                    newVipValue.setVisible(false);
                    comboSearchVip.setVisible(false);
                    searchVipTxt.setVisible(false);
                    searchVipBtn.setVisible(false);
                    cardVipTxt.setVisible(false);
                    fioVipTxt.setVisible(false);
                    phoneVipTxt.setVisible(false);
                    discountVipTxt.setVisible(false);
                    sumVipTxt.setVisible(false);
                    dateVip.setVisible(false);
                    addVipBtn.setVisible(false);
                    break;
                case "Добавление":
                    fioVipTxt.setVisible(true);
                    phoneVipTxt.setVisible(true);
                    discountVipTxt.setVisible(true);
                    cardVipTxt.setVisible(true);
                    sumVipTxt.setVisible(true);
                    dateVip.setVisible(true);
                    addVipBtn.setVisible(true);
                    comboDeleteVip.setVisible(false);
                    deleteVipTxt.setVisible(false);
                    deleteVipBtn.setVisible(false);
                    changeVipBtn.setVisible(false);
                    changeVipCombo.setVisible(false);
                    oldVipValue.setVisible(false);
                    newVipValue.setVisible(false);
                    comboSearchVip.setVisible(false);
                    searchVipTxt.setVisible(false);
                    searchVipBtn.setVisible(false);
                    break;
                case "Изменение":
                    changeVipBtn.setVisible(true);
                    changeVipCombo.setVisible(true);
                    oldVipValue.setVisible(true);
                    newVipValue.setVisible(true);
                    comboDeleteVip.setVisible(false);
                    deleteVipTxt.setVisible(false);
                    deleteVipBtn.setVisible(false);
                    comboSearchVip.setVisible(false);
                    searchVipTxt.setVisible(false);
                    searchVipBtn.setVisible(false);
                    fioVipTxt.setVisible(false);
                    phoneVipTxt.setVisible(false);
                    discountVipTxt.setVisible(false);
                    cardVipTxt.setVisible(false);
                    sumVipTxt.setVisible(false);
                    dateVip.setVisible(false);
                    addVipBtn.setVisible(false);
            }

        }));




        empChoose.getItems().addAll("Поиск", "Изменение", "Удаление", "Добавление");
        empChoose.setOnAction((event1) -> {
                    switch (empChoose.getSelectionModel().getSelectedItem()) {
                        case "Поиск":
                            searchEmpButton.setVisible(true);
                            searchEmpTextfield.setVisible(true);
                            comboEmp.setVisible(true);
                            comboChangeEmp.setVisible(false);
                            changeEmpButton.setVisible(false);
                            newEmpValue.setVisible(false);
                            oldEmpValue.setVisible(false);
                            deleteEmpTextfield.setVisible(false);
                            deleteEmpButton.setVisible(false);
                            comboDeleteEmp.setVisible(false);
                            addressEmpTextfield.setVisible(false);
                            addEmpButton.setVisible(false);
                            fioEmptxt.setVisible(false);
                            exprEmptxt.setVisible(false);
                            phoneEmptxt.setVisible(false);
                            scheduleEmptxt.setVisible(false);
                            positionEmpCombo.setVisible(false);
                            break;
                        case "Изменение":
                            searchEmpTextfield.setVisible(false);
                            comboChangeEmp.setVisible(true);
                            changeEmpButton.setVisible(true);
                            newEmpValue.setVisible(true);
                            oldEmpValue.setVisible(true);
                            deleteEmpTextfield.setVisible(false);
                            deleteEmpButton.setVisible(false);
                            comboDeleteEmp.setVisible(false);
                            addressEmpTextfield.setVisible(false);
                            addEmpButton.setVisible(false);
                            fioEmptxt.setVisible(false);
                            exprEmptxt.setVisible(false);
                            phoneEmptxt.setVisible(false);
                            scheduleEmptxt.setVisible(false);
                            positionEmpCombo.setVisible(false);
                            searchEmpButton.setVisible(false);
                            comboEmp.setVisible(false);
                            break;
                        case "Удаление":
                            deleteEmpTextfield.setVisible(true);
                            deleteEmpButton.setVisible(true);
                            comboDeleteEmp.setVisible(true);
                            searchEmpTextfield.setVisible(false);
                            comboChangeEmp.setVisible(false);
                            changeEmpButton.setVisible(false);
                            newEmpValue.setVisible(false);
                            oldEmpValue.setVisible(false);
                            addressEmpTextfield.setVisible(false);
                            addEmpButton.setVisible(false);
                            fioEmptxt.setVisible(false);
                            exprEmptxt.setVisible(false);
                            phoneEmptxt.setVisible(false);
                            scheduleEmptxt.setVisible(false);
                            positionEmpCombo.setVisible(false);
                            break;
                        case "Добавление":
                            addressEmpTextfield.setVisible(true);
                            addEmpButton.setVisible(true);
                            fioEmptxt.setVisible(true);
                            exprEmptxt.setVisible(true);
                            phoneEmptxt.setVisible(true);
                            scheduleEmptxt.setVisible(true);
                            positionEmpCombo.setVisible(true);
                            deleteEmpTextfield.setVisible(false);
                            deleteEmpButton.setVisible(false);
                            comboDeleteEmp.setVisible(false);
                            searchEmpTextfield.setVisible(false);
                            comboChangeEmp.setVisible(false);
                            changeEmpButton.setVisible(false);
                            newEmpValue.setVisible(false);
                            oldEmpValue.setVisible(false);
                            break;
                    }
                }
        );
    }

}
