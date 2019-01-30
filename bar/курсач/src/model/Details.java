package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Details{

        private IntegerProperty details_id;
        private StringProperty details_sale;
        private IntegerProperty details_quantity;
        private StringProperty details_product;
        private StringProperty details_inventory;



        //Constructor
        Details() {
            this.details_id = new SimpleIntegerProperty();
            this.details_sale = new SimpleStringProperty();
            this.details_quantity = new SimpleIntegerProperty();
            this.details_product = new SimpleStringProperty();
            this.details_inventory = new SimpleStringProperty();
        }


        //assort_id
        public int getDetailsId() {
            return details_id.get();
        }

        void setDetails_id(int details_id){
            this.details_id.set(details_id);
        }

        public IntegerProperty detailsIdProperty(){
            return details_id;
        }

        //fio
        public String getDetailsSale() {
            return details_sale.get();
        }

        void setDetails_sale(String details_sale){
            this.details_sale.set(details_sale);
        }

        public StringProperty detailsSaleProperty() {
            return details_sale;
        }


        //expr
        public Integer getDetailsQuantity() {
            return details_quantity.get();
        }

        void setDetails_quantity(int details_quantity){
            this.details_quantity.set(details_quantity);
        }

        public IntegerProperty detailsQuantityProperty(){
            return details_quantity;
        }


        public String getDetailsProduct() {
            return details_product.get();
        }

        void setDetails_product(String details_product){
            this.details_product.set(details_product);
        }

        public StringProperty detailsProductProperty(){
            return details_product;
        }


        public String getDetailsInventory() {
            return details_inventory.get();
        }

        void setDetails_inventory(String details_inventory){
            this.details_inventory.set(details_inventory);
        }

        public StringProperty detailsInventoryProperty(){
            return details_inventory;
        }

}
