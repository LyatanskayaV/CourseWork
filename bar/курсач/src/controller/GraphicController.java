package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import util.DBConnection;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import static jdk.nashorn.internal.objects.NativeArray.lastIndexOf;


public class GraphicController {
    @FXML
    private DatePicker firstDate, secondDate;

   ObservableList<String> dateList = FXCollections.observableArrayList();
    public void selectDate() throws IOException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            String nammList = "SELECT DATE_SALE FROM SALES\n" +
                    "    WHERE DATE_SALE BETWEEN"+"'"+ firstDate.getValue().toString()+"' " +
                    "AND" + "'"+secondDate.getValue().toString()+"' order by date_sale";
            try (ResultSet rs = DBConnection.dbExecuteQuery(nammList)) {
                while (rs.next()) {
                    String inv = rs.getString(1);
                    dateList.add(inv+"\n");

                }
            }
        }catch (Exception e){
        }
    }

    ObservableList<Integer> sumList = FXCollections.observableArrayList();
    public void selectSum() throws IOException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            String nammList = "SELECT TOTAL_COST FROM SALES\n" +
                    "    WHERE DATE_SALE BETWEEN"+"'"+ firstDate.getValue().toString()+"'" +
                    " AND" + "'"+secondDate.getValue().toString()+"'";
            try (ResultSet rs = DBConnection.dbExecuteQuery(nammList)) {
                while (rs.next()) {
                    int inv = rs.getInt(1);
                    sumList.add(inv);
                }
            }
        }catch (Exception e){
        }
    }




    @FXML
    private void drawGraphic(ActionEvent actionEvent) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
        selectDate();
        selectSum();
        Stage stage = new Stage();
        stage.setTitle("График продаж");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Цифра таблицы");
        final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);

        lineChart.setTitle("Построение графика");
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Продажи");
        for (int i = 0; i< dateList.toArray().length;i++) {
            series1.getData().add(new XYChart.Data(String.valueOf(dateList.get(i)), sumList.get(i)));
        }

        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().addAll(series1);
        stage.setScene(scene);
        stage.show();
    }

}
