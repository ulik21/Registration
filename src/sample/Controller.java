package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtLogin;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnEnter;

    @FXML
    private Button btnRegister;


    @FXML
    void initialize() {
//
        btnEnter.setOnAction(actionEvent -> {

                    String login = txtLogin.getText().trim();
                    String password = txtPassword.getText().trim();


                    User user = new User(login, password);
                    DbWork dbWork = new DbWork();
                    boolean isCheck = dbWork.checkUser(user);

                    if (isCheck == true) {

                        Alert alert = new Alert(Alert.AlertType.WARNING, "Вход не выполнен");
                        alert.show();
                    }
                });

        btnRegister.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/adminPage.fxml"));

            try {
                Scene scene = new Scene(loader.load(), 600, 400);
                Stage stage = new Stage();
                stage.setTitle("Registration");
                stage.setScene(scene);
                stage.show();

                Stage mainStage = (Stage)btnRegister.getScene().getWindow();
                mainStage.hide();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }


}
