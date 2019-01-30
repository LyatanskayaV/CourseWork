package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Kind {

        private IntegerProperty kind_id;
        private StringProperty name_kind;
        private StringProperty grade;

        //Constructor
        public Kind() {
            this.kind_id = new SimpleIntegerProperty();
            this.name_kind = new SimpleStringProperty();
            this.grade = new SimpleStringProperty();
        }


        //vip_id
        public int getKindId() {
            return kind_id.get();
        }

        public void setKindId(int kindId){
            this.kind_id.set(kindId);
        }

        public IntegerProperty kindIdProperty(){
            return kind_id;
        }

        //fio
        public String getNameKind(String string) {
            return name_kind.get();
        }

        public void setName_kind(String name_kind){
            this.name_kind.set(name_kind);
        }

        public StringProperty nameKindVipProperty() {
            return name_kind;
        }



        public String getGrade(String string) {
            return grade.get();
        }

        public void setGrade(String grade){
            this.grade.set(grade);
        }

        public StringProperty gradeProperty(){
            return grade;
        }

    }


