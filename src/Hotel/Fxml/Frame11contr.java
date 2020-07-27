package Hotel.Fxml;


import Hotel.DBConnect;
import Hotel.Fxml.Tables.Reservetable;
import Hotel.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

public class Frame11contr {


    static int finalrnum,utilities=0,multi,Inbill;
    static String finalindtae,final0utdate,name;
    static LocalDate indate,outdate;

    @FXML
    private TableView<Reservetable> dorestable;

    @FXML
    private TableColumn<Reservetable,Integer> doresnum;

    @FXML
    private TableColumn<Reservetable, String> doresname;

    @FXML
    private TableColumn<Reservetable, Date> doresindate;

    @FXML
    private TableColumn<Reservetable, String> dorestype;

    @FXML
    private TableColumn<Reservetable,Integer> doresprice;

    @FXML
    private DatePicker doresoutdate;

    @FXML
    private Label getrescin;

    @FXML
    private CheckBox ckLnd;

    @FXML
    private CheckBox ckToil;

    @FXML
    private CheckBox ckBuff;

    @FXML
    private CheckBox ckExtrm;

    @FXML
    private Button goback;

    @FXML
    private Button bookroom;

    @FXML
    private Button confo;

    @FXML
    private Label msgbox;

    @FXML
    private Label cInbill;

    @FXML
    private Label totBill;


    public static ObservableList<Reservetable> getResdata() throws SQLException, ClassNotFoundException {
        try{

            String sqry= String.format(" SELECT * FROM `room_data` WHERE `Occupied`=2 ");
            Connection con = DBConnect.connect();
            ResultSet rs=con.createStatement().executeQuery(sqry);
            ObservableList<Reservetable> resdata=getResobjects(rs);

            return resdata;

        }catch (SQLException e)
        {
            System.out.println("Connection error");
            e.printStackTrace();
            throw e;
        }
    }


    private static ObservableList<Reservetable> getResobjects(ResultSet rs) throws  SQLException
    {

        ObservableList<Reservetable> resdata = FXCollections.observableArrayList();

        while (rs.next())
        {
            resdata.add(new Reservetable( rs.getInt("Room"), rs.getString("Name"),
                    rs.getDate("Checkin") ,rs.getString("Type"),rs.getInt("Price")));
        }
        return resdata;


    }

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {

        doresnum.setCellValueFactory(new PropertyValueFactory<>("resrnum"));
        doresname.setCellValueFactory(new PropertyValueFactory<>("resname"));
        doresindate.setCellValueFactory(new PropertyValueFactory<>("resindate"));
        dorestype.setCellValueFactory(new PropertyValueFactory<>("rsetype"));
        doresprice.setCellValueFactory( new PropertyValueFactory<>("resprice"));

        ObservableList<Reservetable> resdata=Frame11contr.getResdata();
        populate(resdata);

        confo.setVisible(false);

    }

    private void populate(ObservableList<Reservetable> resdata) {

        dorestable.setItems(resdata);

    }


    @FXML
    void getOutdate(ActionEvent event) {

        outdate=doresoutdate.getValue();
        final0utdate=String.valueOf(outdate);

    }

    @FXML
    void getresinfo(MouseEvent event) {

        Reservetable getin=dorestable.getSelectionModel().getSelectedItem();
        finalrnum=getin.getResrnum();
        finalindtae= String.valueOf(getin.getResindate());
        indate=LocalDate.parse(finalindtae);
        getrescin.setText("Check-In:  "+finalindtae);
        name=getin.getResname();
        multi=getin.getResprice();

    }

    @FXML
    void onBack(ActionEvent event) throws IOException {

        Stage fromcheckin = Main.stage;
        Parent root = FXMLLoader.load(getClass().getResource("/Hotel/Fxml/Frame0.fxml"));
        fromcheckin.setTitle("Mascot Hotel's");
        fromcheckin.setScene(new Scene(root));
        fromcheckin.setResizable(false);
        fromcheckin.show();

    }

    @FXML
    void onBook(ActionEvent event) {
        if (doresoutdate.getValue()!=null) {
            int finalbill=0;
            Period days=Period.between(indate,outdate);
            if (days.getDays()>0) {
                Inbill=utilities+multi;
                finalbill=Inbill+ (multi * days.getDays());
                String reult=String.format("Name: %s   Room: %d\nCheck-in: %s   Check-out: %s",name,finalrnum,finalindtae,final0utdate);
                totBill.setText(String.valueOf(finalbill));
                cInbill.setText(String.valueOf(Inbill));
                msgbox.setText(reult);
                confo.setVisible(true);
            }
            else {
                msgbox.setText("Invalid Date");
            }
        }
        else {
            msgbox.setText("Please Enter all fields");
        }


    }

    @FXML
    void onCkbuff(ActionEvent event) {

        if (ckBuff.isSelected())
        {
            utilities=utilities+450;

        }else
        {
            utilities=utilities-450;
        }

    }

    @FXML
    void onCkextrm(ActionEvent event) {

        if (ckExtrm.isSelected())
        {
            utilities=utilities+200;
        } else
        {
            utilities=utilities-200;
        }

    }

    @FXML
    void onCklnd(ActionEvent event) {

        if (ckLnd.isSelected())
        {
            utilities=utilities+150;
            //totBill.setText(String.valueOf(finalbill));
        }else
        {
            utilities=utilities-150;
            //totBill.setText(String.valueOf(finalbill));
        }

    }

    @FXML
    void onCktoil(ActionEvent event) {

        if (ckToil.isSelected())
        {
            utilities=utilities+60;
            //totBill.setText(String.valueOf(finalbill));
        }else
        {
            utilities=utilities-60;
            // totBill.setText(String.valueOf(finalbill));
        }

    }

    @FXML
    void onConfo(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {

        String exe=String.format("UPDATE `room_data` SET `Occupied`=1, `Checkout`='%s',`Bill`=%d  WHERE `Room`=%d ",final0utdate,Inbill,finalrnum);
        Connection setcon=DBConnect.connect();
        setcon.createStatement().executeUpdate(exe);


        Stage fromcheckin = Main.stage;
        Parent root = FXMLLoader.load(getClass().getResource("/Hotel/Fxml/Frame0.fxml"));
        fromcheckin.setTitle("Mascot Hotel's");
        fromcheckin.setScene(new Scene(root));
        fromcheckin.setResizable(false);
        fromcheckin.show();

    }

}

