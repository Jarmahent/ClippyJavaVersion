package clippy.controllers;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;


public class Chandler extends Thread {
    private String oldString = "";

    private int delay;
    private Clipboard clipboard;
    public Chandler(int delay){
        this.delay = delay;
        this.clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    }

    public void run(){

        while(true){
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
                System.out.println(newString);

                oldString = newString;
            }catch(UnsupportedFlavorException | IOException e){
                System.out.println(e);
            }

            try{
                Thread.sleep(this.delay);
            }catch(InterruptedException n){
                System.out.println(n);
            }

        }
    }
}
