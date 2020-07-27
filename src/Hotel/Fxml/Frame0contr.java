package Hotel.Fxml;

import Hotel.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;


public class Frame0contr {

    @FXML
    private Button checkin;

    @FXML
    private Button checkout;

    @FXML
    private Button current;

    @FXML
    private Button reserve;

    @FXML
    private Button free;

    @FXML
    Parent root;

    @FXML
    void onCheckin(ActionEvent event)  {

        try{
            Stage frame1stage= Main.stage;
            frame1stage.setTitle("Guest Check-In");
            root = FXMLLoader.load(getClass().getResource("/Hotel/Fxml/Frame1.fxml"));
            frame1stage.setScene(new Scene(root));
            frame1stage.show();
        }catch ( IOException e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    void onCheckout(ActionEvent event) throws IOException {

        Stage checkout= Main.stage;
        checkout.setTitle("Guest Check-Out");
        root = FXMLLoader.load(getClass().getResource("/Hotel/Fxml/Frame2.fxml"));
        checkout.setScene(new Scene(root));
        checkout.show();


    }

    @FXML
    void onCurrent(ActionEvent event) throws IOException {

        Stage occupy= Main.stage;
        occupy.setTitle("Occupied Rooms");
        root = FXMLLoader.load(getClass().getResource("/Hotel/Fxml/Frame3.fxml"));
        occupy.setScene(new Scene(root));
        occupy.show();

    }

    @FXML
    void onReserve(ActionEvent event) throws IOException {

        Stage resr= Main.stage;
        resr.setTitle("Reservations");
        root = FXMLLoader.load(getClass().getResource("/Hotel/Fxml/Frame5.fxml"));
        resr.setScene(new Scene(root));
        resr.show();

    }

    @FXML
    void onFree(ActionEvent event) throws IOException {

        Stage free= Main.stage;
        free.setTitle("Unoccupied Rooms");
        root = FXMLLoader.load(getClass().getResource("/Hotel/Fxml/Frame4.fxml"));
        free.setScene(new Scene(root));
        free.show();

    }

}
