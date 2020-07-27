package Hotel.Fxml.Tables;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

//import java.time.LocalDate;

public class TablecIn {

    private SimpleStringProperty rtypes;
    private SimpleStringProperty rac;
    private SimpleStringProperty rbeds;
    private IntegerProperty rnum;
    private IntegerProperty rcost;

    public TablecIn(int rnum, String rtypes, String rbeds, String rac, int rcost)
    {

        this.rtypes = new SimpleStringProperty(rtypes);
        this.rac = new SimpleStringProperty(rac);
        this.rbeds = new SimpleStringProperty(rbeds);
        this.rnum =  new SimpleIntegerProperty(rnum);
        this.rcost = new SimpleIntegerProperty(rcost);

    }

    public String getRtypes() {
        return rtypes.get();
    }

    public SimpleStringProperty rtypesProperty() {
        return rtypes;
    }

    public void setRtypes(String rtypes) {
        this.rtypes.set(rtypes);
    }

    public String getRac() {
        return rac.get();
    }

    public SimpleStringProperty racProperty() {
        return rac;
    }

    public void setRac(String rac) {
        this.rac.set(rac);
    }

    public String getRbeds() {
        return rbeds.get();
    }

    public SimpleStringProperty rbedsProperty() {
        return rbeds;
    }

    public void setRbeds(String rbeds) {
        this.rbeds.set(rbeds);
    }

    public int getRnum() {
        return rnum.get();
    }

    public IntegerProperty rnumProperty() {
        return rnum;
    }

    public void setRnum(int rnum) {
        this.rnum.set(rnum);
    }

    public int getRcost() {
        return rcost.get();
    }

    public IntegerProperty rcostProperty() {
        return rcost;
    }

    public void setRcost(int rcost) {
        this.rcost.set(rcost);
    }
}
