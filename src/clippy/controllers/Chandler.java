package clippy.controllers;

import clippy.Controller;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.LocalDate;


public class Chandler extends Thread {
    private DbController dbController;
    private int delay;
    private Clipboard clipboard;

    public Chandler(int delay){
        this.delay = delay;
        this.dbController = new DbController();
        this.clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

    }

    public void run(){
        // If the same content is in the clipboard dont add it
        String oldString = "";
        Transferable Initialcontent = this.clipboard.getContents(this);
        try{
            System.out.println("Old String = " + oldString + "<--- Should be empty");
            String InitialString = (String) Initialcontent.getTransferData(DataFlavor.stringFlavor);
            oldString = InitialString;
            System.out.println("Old String = " + oldString + "<--- Should no longer be empty");

        }catch(UnsupportedFlavorException | IOException e){
            System.out.println(e.getMessage());
        }




        while(true){
            LocalDate localDate = LocalDate.now();
            try{
                Transferable content = this.clipboard.getContents(this);
                String newString = (String) content.getTransferData(DataFlavor.stringFlavor);
                if(newString.equals(oldString)){ // Ignore same content until it changes

                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        System.out.println(e);
                    }

                    continue;
                }
                this.dbController.writeData(newString, localDate.toString());
                System.out.println("Clipboard Data: " + newString);
                oldString = newString;
            }catch(UnsupportedFlavorException | IOException e){
                System.out.println(e);
            }

            try{
                Thread.sleep(this.delay);
            }catch(InterruptedException n){
                System.out.println(n.getMessage()      );
            }

        }
    }
}
