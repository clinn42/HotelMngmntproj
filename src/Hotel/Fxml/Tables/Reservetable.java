package Hotel.Fxml.Tables;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class Reservetable {

private IntegerProperty resrnum;
private SimpleStringProperty resname;
private Date resindate;
private  SimpleStringProperty rsetype;
private IntegerProperty resprice;

    public Reservetable( int resrnum, String resname, Date resindate, String restype, int resprice ) {

        this.resrnum = new SimpleIntegerProperty(resrnum);
        this.resname=new SimpleStringProperty(resname);
        this.resindate=resindate;
        this.rsetype=new SimpleStringProperty(restype);
        this.resprice=new SimpleIntegerProperty(resprice);

    }

    public int getResrnum() {
        return resrnum.get();
    }

    public IntegerProperty resrnumProperty() {
        return resrnum;
    }

    public void setResrnum(int resrnum) {
        this.resrnum.set(resrnum);
    }

    public String getResname() {
        return resname.get();
    }

    public SimpleStringProperty resnameProperty() {
        return resname;
    }

    public void setResname(String resname) {
        this.resname.set(resname);
    }

    public Date getResindate() {
        return resindate;
    }

    public void setResindate(Date resindate) {
        this.resindate = resindate;
    }

    public String getRsetype() {
        return rsetype.get();
    }

    public SimpleStringProperty rsetypeProperty() {
        return rsetype;
    }

    public void setRsetype(String rsetype) {
        this.rsetype.set(rsetype);
    }

    public int getResprice() {
        return resprice.get();
    }

    public IntegerProperty respriceProperty() {
        return resprice;
    }

    public void setResprice(int resprice) {
        this.resprice.set(resprice);
    }
}
