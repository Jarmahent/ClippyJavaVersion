package clippy;

import javafx.beans.property.SimpleStringProperty;

import javax.sound.sampled.Clip;
import java.awt.datatransfer.Clipboard;

public class ClipboardData {
    private final SimpleStringProperty data;

    public ClipboardData(String data){
        this.data = new SimpleStringProperty(data);

    }

    public String getData(){
        return data.get();
    }

}
