package Hotel.Fxml;


import Hotel.DBConnect;
import Hotel.Fxml.Tables.TablecIn;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;


public class Frame1contr {

   ObservableList<String> selcfloorlist = FXCollections.observableArrayList("1","2","3");
   ObservableList<Integer> selcnumppl = FXCollections.observableArrayList(1,2,3,4,5,6);
   static int finalnumppl=1,finalbill=0,multi,finalrnum,Inbill=0,buttonchange=0,utilities=0;
   static LocalDate cIndate,cOutdate;
   static String finalindate,finaloutdate,name,mobile;


    @FXML
    private TableView<TablecIn> checkintab;

    @FXML
    private TableColumn<TablecIn,Integer> roomno;

    @FXML
    private TableColumn<TablecIn,String> type;

    @FXML
    private TableColumn<TablecIn,String> nobeds;

    @FXML
    private TableColumn<TablecIn,String> aircon;

    @FXML
    private TableColumn<TablecIn,Integer> roomprice;

    @FXML
    private ChoiceBox<String> selcFloor;

    @FXML
    private ChoiceBox<Integer> numPpl;

    @FXML
    private Label totBill;

    @FXML
    private Label msgBox;

    @FXML
    private Label cInbill;

    @FXML
    private TextField newName;

    @FXML
    private TextField newMobile;

    @FXML
    private Button bookroom;

    @FXML
    private Button confirm;

    @FXML
    private Button goreserve;


    @FXML
    private DatePicker checkindate;

    @FXML
    private DatePicker checkoutdate;

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
    Parent root;


    @FXML
    void onRes(ActionEvent event) throws IOException {

        Stage gores= Main.stage;
        gores.setTitle("Reservations");
        root = FXMLLoader.load(getClass().getResource("/Hotel/Fxml/Frame11.fxml"));
        gores.setScene(new Scene(root));
        gores.show();

    }

    public static ObservableList<TablecIn> getTable1data(int floornum ) throws SQLException, ClassNotFoundException {
        try{

            String sqry= String.format(" SELECT * FROM `room_data` WHERE `Occupied`=0 && `floor`=%d",floornum);
            Connection con =DBConnect.connect();
            ResultSet rs=con.createStatement().executeQuery(sqry);
            ObservableList<TablecIn> datalist=getTable1objects(rs);

            return datalist;

        }catch (SQLException e)
        {
            System.out.println("Connection error");
            e.printStackTrace();
            throw e;
        }
    }

    private static ObservableList<TablecIn> getTable1objects(ResultSet rs) throws  SQLException
    {

        ObservableList<TablecIn> datalist =FXCollections.observableArrayList();

        while (rs.next())
        {
            datalist.add(new TablecIn(rs.getInt("Room"),rs.getString("Type"),
                    rs.getString("Beds"), rs.getString("AC"),rs.getInt("Price")));
        }
        return datalist;


    }

    @FXML
    public void initialize () throws Exception
    {

        roomno.setCellValueFactory(new PropertyValueFactory<>("rnum"));
        aircon.setCellValueFactory(new PropertyValueFactory<>("rac"));
        type.setCellValueFactory(new PropertyValueFactory<>("rtypes"));
        nobeds.setCellValueFactory(new PropertyValueFactory<>("rbeds"));
        roomprice.setCellValueFactory(new PropertyValueFactory<>("rcost"));

        ObservableList<TablecIn> datalist= Frame1contr.getTable1data(1);
        populateTable1(datalist);

        selcFloor.setValue("1");
        selcFloor.setItems(selcfloorlist);

        numPpl.setValue(1);
        numPpl.setItems(selcnumppl);
        confirm.setVisible(false);

        bookroom.setText("Book Room");


        selcFloor.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                int index=newValue.intValue() + 1 ;
                ObservableList<TablecIn> datalist= null;
                try {
                    datalist = Frame1contr.getTable1data(index);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                populateTable1(datalist);

            }
        });

        numPpl.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                finalnumppl=newValue.intValue() + 1;
                //totBill.setText(String.valueOf(finalnumppl));

            }
        });

    }

    private void populateTable1(ObservableList<TablecIn> datalist) {

        checkintab.setItems(datalist);

    }

    @FXML
    void selRom(MouseEvent event) {

       // check++;
        //msgBox.setText(String.valueOf(check));

        TablecIn selrow = checkintab.getSelectionModel().getSelectedItem();
       finalrnum=selrow.getRnum();
        multi=selrow.getRcost();

    }

    @FXML
    void onBook(ActionEvent event) {

        if (buttonchange==0)
        {

            if ( checkindate.getValue()==null || checkoutdate.getValue()==null ||
                    newName.getText().isEmpty() || newMobile.getText().isEmpty() ||
                    checkintab.getSelectionModel().getSelectedItem()==null )
            {
                msgBox.setText("Please enter all fields !!");
            }
            else
            {
                confirm.setVisible(true);

                Period nodays=Period.between(cIndate,cOutdate);
                Inbill= utilities + multi;
                finalbill=Inbill+nodays.getDays() * multi;
                name=newName.getText();
                mobile= newMobile.getText();

                String finalstr="Name: "+name+"\tMobile: "+mobile+"\nRoom: "+finalrnum
                        +"\tFrom: "+finalindate+"\tTo: "+finaloutdate+"\nResiding: "+finalnumppl;

                msgBox.setText(finalstr);
                totBill.setText(String.valueOf(finalbill));
                cInbill.setText(String.valueOf(Inbill));
                buttonchange=1;
                bookroom.setText("Change Entries");

            }
        }
        else {

            confirm.setVisible(false);
            Inbill=0;

            //confirm.setVisible(true);
            bookroom.setText("Book Room");

            buttonchange=0;

        }
    }


    @FXML
    void onConf(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {

        Connection fcon=DBConnect.connect();

        String save= String.format("UPDATE `room_data` SET `Occupied`=1,\n " +
                "`Checkin`='%s' ," +
                "`Checkout`='%s'," +
                "\n `Bill`=%d," +
                "`Name`='%s', `Mobile`=%s ," +
                "\n`Resident`=%d WHERE `Room`=%d ;"
                ,finalindate,finaloutdate,Inbill,name,mobile,finalnumppl,finalrnum);

        fcon.createStatement().executeUpdate(save);

        Stage fromcheckin = Main.stage;
        Parent root = FXMLLoader.load(getClass().getResource("/Hotel/Fxml/Frame0.fxml"));
        fromcheckin.setTitle("Mascot Hotel's");
        fromcheckin.setScene(new Scene(root));
        fromcheckin.setResizable(false);
        fromcheckin.show();

        //msgBox.setText(save);

    }

    @FXML
    void onCkbuff(ActionEvent event) {

        if (ckBuff.isSelected())
        {
            utilities=utilities+450;
           //totBill.setText(String.valueOf(finalbill));
        }else
        {
            utilities=utilities-450;
          // totBill.setText(String.valueOf(finalbill));
        }
    }

    @FXML
    void onCkextrm(ActionEvent event) {

        if (ckExtrm.isSelected())
        {
            utilities=utilities+200;
            //totBill.setText(String.valueOf(finalbill));
        }else
        {
            utilities=utilities-200;
            //totBill.setText(String.valueOf(finalbill));
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
    void onChckindate(ActionEvent event) {

        cIndate= checkindate.getValue();
        finalindate= String.valueOf(cIndate);

    }

    @FXML
    void onChckoutdate(ActionEvent event) {

        cOutdate= checkoutdate.getValue();
        finaloutdate= String.valueOf(cOutdate);


    }

    @FXML
    void onback(ActionEvent event) throws IOException {

        Stage fromcheckin = Main.stage;
        Parent root = FXMLLoader.load(getClass().getResource("/Hotel/Fxml/Frame0.fxml"));
        fromcheckin.setTitle("Mascot Hotel's");
        fromcheckin.setScene(new Scene(root));
        fromcheckin.setResizable(false);
        fromcheckin.show();

    }


}


