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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Frame5contr {

    ObservableList<String> selcfloorlist = FXCollections.observableArrayList("1","2","3");
    ObservableList<Integer> selcnumppl = FXCollections.observableArrayList(1,2,3,4,5,6);
    static int floornum=1,finalnumppl=1,finalrnum;
    static String finalindate,name,mobile;
    static LocalDate cIndate;



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
    private Button goback;

    @FXML
    private Button donereserve;

    @FXML
    private ChoiceBox<Integer> numPpl;

    @FXML
    private TextField newName;

    @FXML
    private TextField newMobile;

    @FXML
    private DatePicker checkindate;

    @FXML
    private Label msgbox;


    @FXML
    void onReserve(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {

        if( newName.getText().isEmpty() || newMobile.getText().isEmpty()
                || checkindate.getValue()==null || checkintab.getSelectionModel().isEmpty() )
        {
            msgbox.setText("Enter all fields !!");
        }
        else
        {
            name=newName.getText();
            mobile=newMobile.getText();

           // msgbox.setText(String.format("UPDATE `room_data` \n SET `Occupied`=2,`Checkin`='%s'," +
               //     "`Name`='%s',`Mobile`='%s',\n`Resident`=%d WHERE `Room`=%d;",finalindate,name,mobile,finalnumppl,finalrnum));

           String resstat=String.format("UPDATE `room_data` SET `Occupied`=2,`Checkin`='%s'," +
                    "`Name`='%s',`Mobile`='%s',`Resident`=%d WHERE `Room`=%d;",finalindate,name,mobile,finalnumppl,finalrnum);
           Connection rcon =DBConnect.connect();
           rcon.createStatement().executeUpdate(resstat);

            Stage fromcheckin = Main.stage;
            Parent root = FXMLLoader.load(getClass().getResource("/Hotel/Fxml/Frame0.fxml"));
            fromcheckin.setTitle("Mascot Hotel's");
            fromcheckin.setScene(new Scene(root));
            fromcheckin.setResizable(false);
            fromcheckin.show();


        }

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
    void selRom(MouseEvent event) {

        // check++;
        //msgBox.setText(String.valueOf(check));

        TablecIn selrow = checkintab.getSelectionModel().getSelectedItem();
        finalrnum=selrow.getRnum();

    }



    public static ObservableList<TablecIn> getTable1data(int floornum ) throws SQLException, ClassNotFoundException {
        try{

            String sqry= String.format(" SELECT * FROM `room_data` WHERE `Occupied`=0 && `floor`=%d",floornum);
            Connection con = DBConnect.connect();
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

        ObservableList<TablecIn> datalist = FXCollections.observableArrayList();

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

        numPpl.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                finalnumppl=newValue.intValue() + 1;

            }
        });


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


    }

    private void populateTable1(ObservableList<TablecIn> datalist) {

        checkintab.setItems(datalist);

    }

    @FXML
    void onChckindate(ActionEvent event) {

        cIndate= checkindate.getValue();
        finalindate= String.valueOf(cIndate);

    }


}
