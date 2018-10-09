package clippy.controllers;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.LocalDate;


public class Chandler extends Thread {
    private String oldString = "";
    private DbController dbController;
    private int delay;
    private Clipboard clipboard;

    public Chandler(int delay){
        this.delay = delay;
        this.dbController = new DbController();
        this.clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    }

    public void run(){

        while(true){
            LocalDate localDate = LocalDate.now();
            try{
                Transferable content = this.clipboard.getContents(this);
                String newString = (String) content.getTransferData(DataFlavor.stringFlavor);
                if(newString.equals(oldString)){
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        System.out.println(e);
                    }

                    continue;
                }
                this.dbController.writeData(newString, localDate.toString());
                System.out.println("Date: " + localDate + "\n");
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
