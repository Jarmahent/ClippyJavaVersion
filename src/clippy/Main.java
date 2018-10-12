package clippy;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.PickResult;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.stage.WindowEvent;

import javafx.scene.input.MouseEvent;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

public class Main extends Application {

    ScheduledExecutorService threadpool;

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            /*
                IT ONLY ERRORS OUT WHENEVER YOU DONT CLICK ON THE TEXT AND CLICK ON SOMETHING ELSE...
                PROBABLY USE AN IF STATEMENT TO HANDLE THAT OR A TRY STATEMENT...

             */
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 2){
                    EventTarget event = (Text) mouseEvent.getTarget();
                    System.out.println(((Text) event).getText());
                }

            }
        });
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml")); // Build Scene from fxml file
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 640, 480);
//        scene.getStylesheets().add
//                (Main.class.getResource("assets/scene.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
