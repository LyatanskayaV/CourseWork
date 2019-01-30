package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;


public class AuthorizeController {

    @FXML
    private TextField loginTxt;
    @FXML
    private TextField passwordTxt;
    @FXML
    private void login() throws IOException {
        String authorize = loginTxt.getText();
        String passw = passwordTxt.getText();
        String login = "e8a48653851e28c69d0506508fb27fc5";
        String password = "e4728f444b24839e3f80adf3829bcba9";
        if (Objects.equals(md5Custom(authorize), login) && Objects.equals(md5Custom(passw), password)){
            System.out.print("Верные данные");
            start();
        } else{
            System.out.print(md5Custom(authorize));
            System.out.println(md5Custom(passw));
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка входа");
            alert.setHeaderText("Неверные данные");
            alert.setContentText("Проверьте правильность ввода данных!");
            alert.showAndWait();
        }
    }


    public void start() throws IOException {
        Stage primaryStage = new Stage();
        Parent rootLayout = FXMLLoader.load(AuthorizeController.class.getClass().getResource("/resources/view/EmployeeView.fxml"));
        primaryStage.setScene(new Scene(rootLayout,900,500));
        primaryStage.show();
    }


    public static String md5Custom(String st) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);
        while (md5Hex.length() < 32) {
            md5Hex = "0" + md5Hex;
        }
        return md5Hex;
    }


}
