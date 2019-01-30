package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Inventory {

        private IntegerProperty inventory_id;
        private StringProperty inventory_name;
        private IntegerProperty inventory_quantity;
        private IntegerProperty inventory_cost;

    //Constructor
        public Inventory() {
            this.inventory_id = new SimpleIntegerProperty();
            this.inventory_name = new SimpleStringProperty();
            this.inventory_quantity = new SimpleIntegerProperty();
            this.inventory_cost = new SimpleIntegerProperty();
        }


        //inventory_id
        public int getInventoryId() {
            return inventory_id.get();
        }

        public void setInventoryId(int inventoryId){
            this.inventory_id.set(inventoryId);
        }

        public IntegerProperty inventoryIdProperty(){
            return inventory_id;
        }


        //inventory_name
        public String getName() {
            return inventory_name.get();
        }

    public void setName(String inventory_name){
        this.inventory_name.set(inventory_name);
    }

    public StringProperty inventoryNameProperty() {
        return inventory_name;
    }


        public int getInventoryQuantity() {
        return inventory_quantity.get();
    }

        public void setInventory_quantity(int inventory_quantity){
        this.inventory_quantity.set(inventory_quantity);
    }

        public IntegerProperty inventoryQuantityProperty(){
        return inventory_quantity;
    }


        public int getInventoryCost() {
        return inventory_cost.get();
    }

        public void setInventory_cost(int inventory_cost){
        this.inventory_cost.set(inventory_cost);
    }

        public IntegerProperty inventoryCostProperty(){return inventory_cost;}

}
