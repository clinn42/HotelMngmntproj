package Hotel.Fxml.Tables;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class OccuTable {

    private IntegerProperty ocrnum;
    private SimpleStringProperty ocname;
    private Date indate;
    private Date outdate;
    private IntegerProperty ocres;
    private IntegerProperty ocbill;
    private SimpleStringProperty octype;

    public OccuTable( int ocrnum, String ocname, Date indate, Date outdate,int ocres,int ocbill,String octype ) {

        this.ocrnum = new SimpleIntegerProperty(ocrnum);
        this.ocres = new SimpleIntegerProperty(ocres);
        this.ocbill = new SimpleIntegerProperty(ocbill);
        this.ocname = new SimpleStringProperty(ocname);
        this.octype = new SimpleStringProperty(octype);
        this.indate = indate;
        this.outdate = outdate;

    }

    public int getOcrnum() {
        return ocrnum.get();
    }

    public IntegerProperty ocrnumProperty() {
        return ocrnum;
    }

    public void setOcrnum(int ocrnum) {
        this.ocrnum.set(ocrnum);
    }

    public String getOcname() {
        return ocname.get();
    }

    public SimpleStringProperty ocnameProperty() {
        return ocname;
    }

    public void setOcname(String ocname) {
        this.ocname.set(ocname);
    }

    public Date getIndate() {
        return indate;
    }

    public void setIndate(Date indate) {
        this.indate = indate;
    }

    public Date getOutdate() {
        return outdate;
    }

    public void setOutdate(Date outdate) {
        this.outdate = outdate;
    }

    public int getOcres() {
        return ocres.get();
    }

    public IntegerProperty ocresProperty() {
        return ocres;
    }

    public void setOcres(int ocres) {
        this.ocres.set(ocres);
    }

    public int getOcbill() {
        return ocbill.get();
    }

    public IntegerProperty ocbillProperty() {
        return ocbill;
    }

    public void setOcbill(int ocbill) {
        this.ocbill.set(ocbill);
    }

    public String getOctype() {
        return octype.get();
    }

    public SimpleStringProperty octypeProperty() {
        return octype;
    }

    public void setOctype(String octype) {
        this.octype.set(octype);
    }



}
