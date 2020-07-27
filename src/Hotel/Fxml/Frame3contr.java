package Hotel.Fxml;

import Hotel.DBConnect;
import Hotel.Fxml.Tables.OccuTable;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Frame3contr {

    @FXML
    private TableView<OccuTable> occtable;

    @FXML
    private TableColumn<OccuTable,Integer> occnum;

    @FXML
    private TableColumn<OccuTable,String> occname;

    @FXML
    private TableColumn<OccuTable, Date> occIn;

    @FXML
    private TableColumn<OccuTable,Date> occOut;

    @FXML
    private TableColumn<OccuTable,Integer> numppl;

    @FXML
    private TableColumn<OccuTable,Integer> occbill;

    @FXML
    private TableColumn<OccuTable,String> occtype;

    @FXML
    private ChoiceBox<String> selfloor;

    @FXML
    private ChoiceBox<String> optsearch;

    @FXML
    private Label searchparam;

    @FXML
    private TextField paramaenter;

    @FXML
    private Button gosearch;

    @FXML
    private Button goback;

    @FXML
    void onSearch(ActionEvent event) throws SQLException, ClassNotFoundException {

       String searchstate= Tablesearch(optsearch.getSelectionModel().getSelectedIndex());

       //goback.setText(searchstate);

        ObservableList<OccuTable> srlist=Frame3contr.getOccdata(searchstate);
        populateoc(srlist);

    }

    private String Tablesearch(int selectedIndex) {

        String sendstate;

        if (selectedIndex==0)
        {
            sendstate=String.format("SELECT * FROM `room_data` WHERE `Occupied`=1 && `Room`=%d ",
                    Integer.parseInt(paramaenter.getText()));
            return sendstate;
        }
        else if (selectedIndex==1)
        {
            sendstate = String.format("SELECT * FROM `room_data` WHERE `Occupied`=1 && `Name`='%s' ",paramaenter.getText());
            return sendstate;
        }
        else if (selectedIndex==2)
        {
            sendstate=String.format("SELECT * FROM `room_data` WHERE `Occupied`=1 && `Checkin`='%s'",paramaenter.getText()) ;
            return sendstate;
        }
        else
        {
            sendstate=String.format("SELECT * FROM `room_data` WHERE `Occupied`=1 && `Checkout`='%s'",paramaenter.getText());
            return sendstate;
        }

    }


    ObservableList<String> selfloorlist = FXCollections.observableArrayList("1","2","3");
    ObservableList<String> srchby = FXCollections.observableArrayList("Room No.","Name","Check-In","Check-Out");
    String[] itlist = {"Room No.","Name","Check-In","Check-Out"};
    String[] txtprompt= {"room number","enter name","yyyy-mm-dd","yyyy-mm-dd"};


    public static ObservableList<OccuTable> getOccdata(String exe) throws SQLException, ClassNotFoundException {

        Connection occon= DBConnect.connect();
        ResultSet ocrs = occon.createStatement().executeQuery(exe);

        ObservableList<OccuTable> oclist= getOcobj(ocrs);
        return oclist;

    }

    private static ObservableList<OccuTable> getOcobj(ResultSet ocrs) throws SQLException {

        ObservableList<OccuTable> oclist= FXCollections.observableArrayList();


        while (ocrs.next())
        {
            oclist.add(new OccuTable(ocrs.getInt("Room"),ocrs.getString("Name"),ocrs.getDate("Checkin"),
                    ocrs.getDate("Checkout"),ocrs.getInt("Resident"),ocrs.getInt("Bill"),
                    ocrs.getString("Type")));
        }

        return oclist;

    }

    public  void  initialize() throws Exception
    {

        occnum.setCellValueFactory(new PropertyValueFactory<>("ocrnum"));
        occname.setCellValueFactory(new PropertyValueFactory<>("ocname"));
        occIn.setCellValueFactory(new PropertyValueFactory<>("indate"));
        occOut.setCellValueFactory(new PropertyValueFactory<>("outdate"));
        numppl.setCellValueFactory(new PropertyValueFactory<>("ocres"));
        occbill.setCellValueFactory(new PropertyValueFactory<>("ocbill"));
        occtype.setCellValueFactory(new PropertyValueFactory<>("octype"));

        ObservableList<OccuTable> oclist = Frame3contr.getOccdata(String.format(" SELECT * FROM `room_data` WHERE `Occupied`=1 && `floor`=%d",1));
        populateoc(oclist);

        selfloor.setValue("1");
        selfloor.setItems(selfloorlist);

        optsearch.setValue("Room No.");
        optsearch.setItems(srchby);

        selfloor.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                int index= newValue.intValue() + 1;
                String tabshow= String.format(" SELECT * FROM `room_data` WHERE `Occupied`=1 && `floor`=%d",index);

                ObservableList<OccuTable> oclist = null;
                try {
                    oclist = Frame3contr.getOccdata(tabshow);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                populateoc(oclist);


            }
        });

        optsearch.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                searchparam.setText(itlist[(int) newValue]);
                paramaenter.clear();
                paramaenter.setPromptText(txtprompt[(int)newValue]);

            }
        });

    }

    private void populateoc(ObservableList<OccuTable> oclist) {

        occtable.setItems(oclist);

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

}
