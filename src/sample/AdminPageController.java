package sample;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.deploy.security.SelectableSecurityManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AdminPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtLoginRegister;

    @FXML
    private TextField txtPassRegister;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    @FXML
    void initialize() {
        btnSave.setOnAction(actionEvent -> {

            boolean isValid = isValid();
            if (isValid) {


                User user = new User(txtLoginRegister.getText(), txtPassRegister.getText());
                DbWork dbWork = new DbWork();
                dbWork.saveUser(user);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Не введены данные");
                alert.show();
            }
        });
    }



    private boolean isValid(){

        if (txtLoginRegister.getText().equals("") && txtPassRegister.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Не введены данные");
            alert.show();
            return false;
        }else if (txtPassRegister.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Не введен пароль");
            alert.show();
            return false;
        } else if (txtLoginRegister.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Не введен логин");
            alert.show();
            return false;
        }
        return true;


    }

}
