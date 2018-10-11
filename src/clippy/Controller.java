package clippy;

import clippy.controllers.DbController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import clippy.controllers.Chandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import javax.sound.sampled.Clip;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Controller{
    @FXML
    private Button mybutton;


    @FXML
    private Pane rootpane;

    @FXML
    TableColumn<ClipboardData, String> copyColumn = new TableColumn<ClipboardData, String>();

    @FXML
    private TableView<ClipboardData> CopyTable = new TableView<ClipboardData>();

    private DbController Dbcontroller;

    private ObservableList<ClipboardData> MyData(ArrayList data){
        List<ClipboardData> list = new ArrayList<>();
        for(int i = 0; i < data.size(); i++){
            String dataString = data.get(i).toString();
            list.add(new ClipboardData(dataString));
        }
        return FXCollections.observableArrayList(list);
    }



    public Controller(){  // Constructor
        super();
        this.Dbcontroller = new DbController();

    }

    @FXML
    private void Listener(){
        Chandler handler = new Chandler(1000);
        handler.setDaemon(true);
        handler.start();
    }

    @FXML
    private void initialize(){
        System.out.println("Init!");
        CopyTable.setItems(MyData(this.Dbcontroller.getAllData()));
        copyColumn.setCellValueFactory(new PropertyValueFactory<ClipboardData, String>("data"));
    }

    @FXML
    private void handleButtonClicked(ActionEvent event){

//        copyData.setCellValueFactory(new PropertyValueFactory<String, String>("copyData"));

    }
}



