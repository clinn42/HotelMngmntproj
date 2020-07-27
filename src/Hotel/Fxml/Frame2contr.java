package Hotel.Fxml;


import Hotel.DBConnect;
import Hotel.Fxml.Tables.Couttable;
import Hotel.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

public class Frame2contr {

    ObservableList<String> selcfloorlist= FXCollections.observableArrayList("1","2","3");
    static String finalindate,finaloutdate,newname;
    static LocalDate indate,outdate;
    static int finalfloornum,Inbill,multi,residing;


    @FXML
    private TableView<Couttable> outtable;

    @FXML
    private TableColumn<Couttable,Integer> outroom;

    @FXML
    private TableColumn<Couttable, String> outname;

    @FXML
    private TableColumn<Couttable, Date> outIndate;

    @FXML
    private TableColumn<Couttable, Date> outOutdate;

    @FXML
    private TableColumn<Couttable,Integer> outpbill;

    @FXML
    private TableColumn<Couttable,Integer> outres;

    @FXML
    private ChoiceBox<String> selcFloor;

    @FXML
    private Label msgbox;

    @FXML
    private Label indatebox;

    @FXML
    private Label outdatebox;

    @FXML
    private DatePicker newoutdate;

    @FXML
    private Label totbill;

    @FXML
    private Label remainbill;

    @FXML
    private TextField fines;

    @FXML
    private Button goback;

    @FXML
    private Button finroom;

    @FXML
    private Button confirm;


    public static ObservableList<Couttable> getOutdata(int floornum ) throws SQLException, ClassNotFoundException {
        try{

            String sqry= String.format(" SELECT * FROM `room_data` WHERE `Occupied`=1 && `floor`=%d",floornum);
            Connection con = DBConnect.connect();
            ResultSet rs=con.createStatement().executeQuery(sqry);
            ObservableList<Couttable> outlist=getOutobjects(rs);

            return outlist;

        }catch (SQLException e)
        {
            System.out.println("Connection error");
            e.printStackTrace();
            throw e;
        }
    }

    private static ObservableList<Couttable> getOutobjects(ResultSet rs) throws  SQLException
    {

        ObservableList<Couttable> outlist = FXCollections.observableArrayList();

        while (rs.next())
        {
            outlist.add(new Couttable( rs.getInt("Room"),rs.getString("Name"),
                    rs.getDate("Checkin"),rs.getDate("Checkout"),rs.getInt("Bill")
            ,rs.getInt("Resident")));
        }
        return outlist;


    }

    @FXML
    public  void initialize() throws Exception{

        outroom.setCellValueFactory(new PropertyValueFactory<>("coutrnum"));
        outname.setCellValueFactory(new PropertyValueFactory<>("coutname"));
        outIndate.setCellValueFactory(new PropertyValueFactory<>("coutindate"));
        outOutdate.setCellValueFactory(new PropertyValueFactory<>("coutoutdate"));
        outpbill.setCellValueFactory(new PropertyValueFactory<>("coutbill"));
        outres.setCellValueFactory(new PropertyValueFactory<>("coutres"));

        ObservableList<Couttable> outlist=Frame2contr.getOutdata(1);
        populate(outlist);

        selcFloor.setValue("1");
        selcFloor.setItems(selcfloorlist);

        confirm.setVisible(false);

        selcFloor.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                int index=newValue.intValue() + 1 ;
                ObservableList<Couttable> outlist= null;
                try {
                    outlist = Frame2contr.getOutdata(index);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                populate(outlist);


            }
        });

    }

    private void populate(ObservableList<Couttable> outlist) {

        outtable.setItems(outlist);

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
    void onConf(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {

        String exe=String.format("UPDATE `room_data` SET `Occupied`=0,`Checkin`='0000-00-00'," +
                "`Checkout`='0000-00-00',`Bill`=0,`Name`='',`Mobile`='',`Resident`=0 WHERE `Room`=%d ",finalfloornum);
        Connection confcon=DBConnect.connect();
        confcon.createStatement().executeUpdate(exe);

        Stage fromcheckin = Main.stage;
        Parent root = FXMLLoader.load(getClass().getResource("/Hotel/Fxml/Frame0.fxml"));
        fromcheckin.setTitle("Mascot Hotel's");
        fromcheckin.setScene(new Scene(root));
        fromcheckin.setResizable(false);
        fromcheckin.show();

    }

    @FXML
    void onFin(ActionEvent event) {

        if ( outtable.getSelectionModel().isEmpty() )
        {
            msgbox.setText("Please select room !!!");
        }
        else
        {
            int finalbill;
            String disp;

            Period diff = Period.between(indate,outdate);

            if (diff.getDays()>0) {
                confirm.setVisible(true);
                finalbill=Inbill + (diff.getDays() * multi) ;

                if (!fines.getText().isEmpty())
                {
                    finalbill=finalbill+ Integer.parseInt(fines.getText());
                }
                totbill.setText("Total Bill: "+String.valueOf(finalbill));
                remainbill.setText("Remainder Bill:  "+String.valueOf( finalbill-Inbill ));

                disp= String.format("Name:  %s  Room:  %d \n From: %s To:  %s \n Residing: %d",newname
                        ,finalfloornum,finalindate,finaloutdate,residing);

                msgbox.setText(disp);
            } else {
                msgbox.setText("Invalid Date");
            }


        }

    }

    @FXML
    void selcTable(MouseEvent event) throws SQLException, ClassNotFoundException {

        Couttable getinfo= outtable.getSelectionModel().getSelectedItem();

        finalindate= String.valueOf(getinfo.getCoutindate());
        indatebox.setText("Check-In: "+finalindate);
        indate= LocalDate.parse(finalindate);

        finaloutdate=String.valueOf(getinfo.getCoutoutdate());
        outdatebox.setText("Check-Out: "+finaloutdate);
        outdate= LocalDate.parse(finaloutdate);

        Inbill=getinfo.getCoutbill();
        finalfloornum=getinfo.getCoutrnum();
        multi=getroomprice(finalfloornum);

        newname=getinfo.getCoutname();
        residing=getinfo.getCoutres();


    }

    private int getroomprice(int serachcost) throws SQLException, ClassNotFoundException {

        String search=String.format("SELECT * FROM `room_data` WHERE `Room`=%d ",serachcost);

        Connection scon=DBConnect.connect();
        ResultSet srs=scon.createStatement().executeQuery(search);
        srs.next();
        return srs.getInt("Price");
    }

    @FXML
    void onGetoutdate(ActionEvent event) {

        outdate=newoutdate.getValue();
        finaloutdate= String.valueOf(newoutdate.getValue());

    }

}
