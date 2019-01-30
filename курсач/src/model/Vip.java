package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Vip {

        private IntegerProperty vip_id;
        private StringProperty vip_fio;
        private StringProperty phone;
        private IntegerProperty discount;
        private IntegerProperty total_sum;
        private StringProperty card_number;
        private StringProperty date_vip;




    //Constructor
        public Vip() {
            this.vip_id = new SimpleIntegerProperty();
            this.vip_fio = new SimpleStringProperty();
            this.phone = new SimpleStringProperty();
            this.discount = new SimpleIntegerProperty();
            this.total_sum = new SimpleIntegerProperty();
            this.card_number = new SimpleStringProperty();
            this.date_vip = new SimpleStringProperty();
        }


        //vip_id
        public int getVipId() {
            return vip_id.get();
        }

        public void setVipId(int vipId){
            this.vip_id.set(vipId);
        }

        public IntegerProperty vipIdProperty(){
            return vip_id;
        }

        //fio
        public String getVipFio() {
            return vip_fio.get();
        }

        public void setVipFio(String vip_fio){
            this.vip_fio.set(vip_fio);
        }

        public StringProperty fioVipProperty() {
            return vip_fio;
        }



        public String getPhoneVip() {
            return phone.get();
        }

        public void setPhoneVip(String phoneVip){
            this.phone.set(phoneVip);
        }

        public StringProperty phoneVipProperty(){
            return phone;
        }


        public String getDateVip() {
            return date_vip.get();
        }

        public void setDateVip(String dateVip){
            this.date_vip.set(dateVip);
        }

        public StringProperty dateVipProperty(){
            return date_vip;
        }


        public Integer getDiscount() {
            return discount.get();
        }

        public void setDiscount(int discount){
            this.discount.set(discount);
        }

        public IntegerProperty discountProperty(){
            return discount;
        }



        public Integer getSum() {
            return total_sum.get();
        }

        public void setSum(int total_sum){
            this.total_sum.set(total_sum);
        }

        public IntegerProperty sumProperty(){
            return total_sum;
        }


        public String getCard() {
            return card_number.get();
        }

        public void setCard(String card){
            this.card_number.set(card);
        }

        public StringProperty cardProperty(){
            return card_number;
        }

    }


