
package browser1;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * 
 */
public class HistoryController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<Link>historyTableView;
    @FXML
    private TableColumn<Link,String>link;
     @FXML
    private TableColumn linkdd;
    @FXML
    private Button historyClearButton;
    
    DBImplement dbih = new DBImplement("history");
    List<Link> historyList = new ArrayList<Link>();
    public ObservableList<Link> list;
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        historyList= dbih.selectAll();//takes data from database and returns it
        list = FXCollections.observableArrayList(historyList);//Creates a new observable array list and adds a content of collection col to it.
        link.setCellValueFactory(new PropertyValueFactory<Link,String>("link"));
     //   linkdd.setCellValueFactory(new PropertyValueFactory("linkdd"));
        historyTableView.setItems(list);
       
        historyClearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                dbih.delete();//method of dbi implement
                list.clear();
                
                historyTableView.setItems(list);
                
                
            }
        });
    }    
    
}
