package clippy;

import clippy.controllers.DbController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.scene.input.MouseEvent;


public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        DbController controller = new DbController();
        controller.createTable();
        primaryStage.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            /*
                IT ONLY ERRORS OUT WHENEVER YOU DONT CLICK ON THE TEXT AND CLICK ON SOMETHING ELSE...
                PROBABLY USE AN IF STATEMENT TO HANDLE THAT OR A TRY STATEMENT...

             */
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 2){
                    try{
                        EventTarget event = (Text) mouseEvent.getTarget();
                        System.out.println(((Text) event).getText());
                    }catch (ClassCastException e){
                        System.out.println("Didnt Click on the Text");
                    }
                    
                }

            }
        });
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml")); // Build Scene from fxml file
        primaryStage.setTitle("Clippy v.0.1.9");
        Scene scene = new Scene(root, 495, 480);
        scene.getStylesheets().add
                (Main.class.getResource("assets/scene.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("assets/airplane@32.png")));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
