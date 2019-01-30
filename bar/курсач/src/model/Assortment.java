package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Assortment {
     
    private IntegerProperty assort_id;
        private StringProperty assort_name;
        private IntegerProperty assort_cost;
        private StringProperty description;
        private StringProperty assort_kind;



        //Constructor
        Assortment() {
            this.assort_id = new SimpleIntegerProperty();
            this.assort_name = new SimpleStringProperty();
            this.assort_cost = new SimpleIntegerProperty();
            this.description = new SimpleStringProperty();
            this.assort_kind = new SimpleStringProperty();
        }


        //assort_id
        public int getAssortId() {
            return assort_id.get();
        }

        void setAssortId(int assortId){
            this.assort_id.set(assortId);
        }

        public IntegerProperty assortIdProperty(){
            return assort_id;
        }

        //fio
        public String getAssortName() {
            return assort_name.get();
        }

        void setAssortname(String assortname){
            this.assort_name.set(assortname);
        }

        public StringProperty assortNameProperty() {
            return assort_name;
        }


        //expr
        public Integer getAssortCost() {
            return assort_cost.get();
        }

        void setAssortCost(int assortCost){
            this.assort_cost.set(assortCost);
        }

        public IntegerProperty costProperty(){
            return assort_cost;
        }


        public String getAssortDescription() {
            return description.get();
        }

        void setAssortDescription(String description){
            this.description.set(description);
        }

        public StringProperty descriptionProperty(){
            return description;
        }


        public String getAssortKind() {
            return assort_kind.get();
        }

        void setAssortKind(String kind){
            this.assort_kind.set(kind);
        }

        public StringProperty assortKindProperty(){
            return assort_kind;
        }
    
}
