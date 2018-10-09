package clippy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import clippy.controllers.Chandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public class Controller{
    @FXML
    private Button mybutton;


    @FXML
    private Pane rootpane;


    @FXML
    private void Listener(){
        Chandler handler = new Chandler(1000);
        handler.setDaemon(true);
        handler.start();
    }

    @FXML
    private void initialize(){
        System.out.println("This was called first!");
    }

    @FXML
    private void handleButtonClicked(ActionEvent event){
        if(event.getSource() == mybutton){
            Text t = new Text(10, 15, "Insertable text");
            t.setText("Insertable text settext");
            rootpane.getChildren().add(t);
            System.out.println("This was called by the button #mybutton");
        }
    }
}



