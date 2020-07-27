package Hotel.Fxml.Tables;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class Couttable {

    private IntegerProperty coutrnum;
    private SimpleStringProperty coutname;
    private Date coutindate;
    private Date coutoutdate;
    private IntegerProperty coutbill;
    private IntegerProperty coutres;

    public Couttable(int coutrnum, String coutname, Date coutindate,Date coutoutdate, int coutbill, int coutres) {

        this.coutrnum=new SimpleIntegerProperty(coutrnum);
        this.coutname=new SimpleStringProperty(coutname);
        this.coutindate=coutindate;
        this.coutoutdate=coutoutdate;
        this.coutbill=new SimpleIntegerProperty(coutbill);
        this.coutres=new SimpleIntegerProperty(coutres);

    }

    public int getCoutrnum() {
        return coutrnum.get();
    }

    public IntegerProperty coutrnumProperty() {
        return coutrnum;
    }

    public void setCoutrnum(int coutrnum) {
        this.coutrnum.set(coutrnum);
    }

    public String getCoutname() {
        return coutname.get();
    }

    public SimpleStringProperty coutnameProperty() {
        return coutname;
    }

    public void setCoutname(String coutname) {
        this.coutname.set(coutname);
    }

    public Date getCoutindate() {
        return coutindate;
    }

    public void setCoutindate(Date coutindate) {
        this.coutindate = coutindate;
    }

    public Date getCoutoutdate() {
        return coutoutdate;
    }

    public void setCoutoutdate(Date coutoutdate) {
        this.coutoutdate = coutoutdate;
    }

    public int getCoutbill() {
        return coutbill.get();
    }

    public IntegerProperty coutbillProperty() {
        return coutbill;
    }

    public void setCoutbill(int coutbill) {
        this.coutbill.set(coutbill);
    }

    public int getCoutres() {
        return coutres.get();
    }

    public IntegerProperty coutresProperty() {
        return coutres;
    }

    public void setCoutres(int coutres) {
        this.coutres.set(coutres);
    }
}
