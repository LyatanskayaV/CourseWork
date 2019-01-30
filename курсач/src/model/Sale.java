package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Sale {


        private IntegerProperty sale_id;
        private StringProperty sale_emp;
        private StringProperty sale_date;
        private IntegerProperty sale_sum;
        private StringProperty sale_card;

    Sale() {
            this.sale_id = new SimpleIntegerProperty();
            this.sale_emp = new SimpleStringProperty();
            this.sale_date = new SimpleStringProperty();
            this.sale_sum = new SimpleIntegerProperty();
            this.sale_card = new SimpleStringProperty();
        }


        //sale_id
        public int getSaleId() {
            return sale_id.get();
        }

        void setSaleId(int saleId){
            this.sale_id.set(saleId);
        }

        public IntegerProperty saleIdProperty(){
            return sale_id;
        }


    public String getSaleEmp() {
        return sale_emp.get();
    }

    void setSale_emp(String sale_emp){
        this.sale_emp.set(sale_emp);
    }

    public StringProperty saleEmpProperty() {
        return sale_emp;
    }



    //fio
        public String getSaleDate() {
            return sale_date.get();
        }

        void setSaleDate(String saleDate){
            this.sale_date.set(saleDate);
        }

        public StringProperty saleDateProperty() {
            return sale_date;
        }


        //expr
        public Integer getSum() {
            return sale_sum.get();
        }

        void setSum(int saleSum){
            this.sale_sum.set(saleSum);
        }

        public IntegerProperty saleSumProperty(){
            return sale_sum;
        }


        public String getSaleCard() {
            return sale_card.get();
        }

        void setSale_card(String sale_card){
            this.sale_card.set(sale_card);
        }

        public StringProperty sale_cardProperty(){
            return sale_card;
        }
    }
