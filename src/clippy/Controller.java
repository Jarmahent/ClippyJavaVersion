package clippy;

import clippy.controllers.DbController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import clippy.controllers.Chandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Controller{
    @FXML
    private Button mybutton;


    @FXML
    private Pane rootpane;

    @FXML
    TableColumn<ClipboardData, String> copyColumn = new TableColumn<ClipboardData, String>();

    @FXML
    private TableView<ClipboardData> CopyTable = new TableView<ClipboardData>();

    private Chandler Clistener;


    private DbController dbController;

    private Clipboard clipboard;

    private DbController Dbcontroller;

    private ObservableList<ClipboardData> MyData(ArrayList data){ // List for the clipboard Data
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
        CopyTable.setItems(MyData(this.Dbcontroller.getAllData()));
        copyColumn.setCellValueFactory(new PropertyValueFactory<ClipboardData, String>("data"));

    }

    @FXML
    private void initialize(){ // Spawn a new Thread when the initialized method is called and run the listener on a separate Thread
        Task task = new Task<Void>() {
            @Override public Void call() {

                clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                dbController = new DbController();


                String oldString = "";
                Transferable Initialcontent = clipboard.getContents(this);
                try{
                    System.out.println("Old String = " + oldString + " <--- Should be empty");
                    String InitialString = (String) Initialcontent.getTransferData(DataFlavor.stringFlavor);
                    oldString = InitialString;
                    System.out.println("Old String = " + oldString + " <--- Should no longer be empty");

                }catch(UnsupportedFlavorException | IOException e){
                    System.out.println(e.getMessage());
                }

                while(true){ // Start the listener
                    LocalDate localDate = LocalDate.now();
                    try{
                        Transferable content = clipboard.getContents(this);
                        String newString = (String) content.getTransferData(DataFlavor.stringFlavor);
                        if(newString.equals(oldString)){ // Ignore same content until it changes

                            try{
                                Thread.sleep(1000);
                            }catch(InterruptedException e){
                                System.out.println(e);
                            }

                            continue;
                        }
                        dbController.writeData(newString, localDate.toString());
                        System.out.println("Clipboard Data: " + newString);
                        System.out.println("Updating Table...");
                        CopyTable.getItems().clear();
                        CopyTable.setItems(MyData(Dbcontroller.getAllData()));
                        copyColumn.setCellValueFactory(new PropertyValueFactory<ClipboardData, String>("data"));
                        oldString = newString;
                    }catch(UnsupportedFlavorException | IOException e){
                        System.out.println(e);
                    }

                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException n){
                        System.out.println(n.getMessage()      );
                    }

                }
            }
        };

        Thread ClipboardListenerThread = new Thread(task);
        ClipboardListenerThread.setDaemon(true);
        ClipboardListenerThread.start();


        CopyTable.setItems(MyData(this.Dbcontroller.getAllData()));
        copyColumn.setCellValueFactory(new PropertyValueFactory<ClipboardData, String>("data"));
    }

    @FXML
    private void handleButtonClicked(ActionEvent event){
        CopyTable.getItems().clear();
        CopyTable.setItems(MyData(this.Dbcontroller.getAllData()));
        copyColumn.setCellValueFactory(new PropertyValueFactory<ClipboardData, String>("data"));
    }

}



