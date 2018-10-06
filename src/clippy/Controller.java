package clippy;

import javafx.fxml.FXML;
import clippy.controllers.Chandler;


public class Controller{

    @FXML
    private void Listener(){
        Chandler handler = new Chandler(1000);
        handler.setDaemon(true);
        handler.start();
    }
}



