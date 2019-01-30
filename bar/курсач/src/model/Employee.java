package model;

import javafx.beans.property.*;

public class Employee {
    private IntegerProperty employee_id;
    private StringProperty employee_fio;
    private StringProperty employee_expr;
    private StringProperty employee_phone;
    private StringProperty employee_address;
    private StringProperty employee_schedule;
    private StringProperty employee_position;



    //Constructor
    public Employee() {
        this.employee_id = new SimpleIntegerProperty();
        this.employee_fio = new SimpleStringProperty();
        this.employee_expr = new SimpleStringProperty();
        this.employee_phone = new SimpleStringProperty();
        this.employee_address = new SimpleStringProperty();
        this.employee_schedule = new SimpleStringProperty();
        this.employee_position = new SimpleStringProperty();
    }


    //employee_id
    public int getEmployeeId() {
        return employee_id.get();
    }

    public void setEmployeeId(int employeeId){
        this.employee_id.set(employeeId);
    }

    public IntegerProperty employeeIdProperty(){
        return employee_id;
    }

    //fio
    public String getFio() {
        return employee_fio.get();
    }

    public void setFio(String employee_fio){
        this.employee_fio.set(employee_fio);
    }

    public StringProperty fioProperty() {
        return employee_fio;
    }


    //expr
    public String getExpr() {
        return employee_expr.get();
    }

    public void setExpr(String employee_expr){
        this.employee_expr.set(employee_expr);
    }

    public StringProperty exprProperty(){
        return employee_expr;
    }


    public String getPhone() {
        return employee_phone.get();
    }

    public void setPhone(String employee_phone){
        this.employee_phone.set(employee_phone);
    }

    public StringProperty phoneProperty(){
        return employee_phone;
    }


    public String getAddress() {
        return employee_address.get();
    }

    public void setAddress(String employee_address){
        this.employee_address.set(employee_address);
    }

    public StringProperty addressProperty(){
        return employee_address;
    }



    public String getSchedule() {
        return employee_schedule.get();
    }

    public void setSchedule(String employee_schedule){
        this.employee_schedule.set(employee_schedule);
    }

    public StringProperty scheduleProperty(){
        return employee_schedule;
    }


    public String getPosition() {
        return employee_position.get();
    }

    public void setPosition(String employee_position){
        this.employee_position.set(employee_position);
    }

    public StringProperty positionProperty(){
        return employee_position;
    }

}