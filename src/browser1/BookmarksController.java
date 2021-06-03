
package browser1;

import java.net.URL;
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
 */
public class BookmarksController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView bookmarkTableView;
    @FXML
    private TableColumn linkdd;
    @FXML
    private TableColumn link;
    @FXML
    private Button bookmarkClearButton;
    
    List<Link> bookmarkList = new ArrayList<Link>();
    public ObservableList<Link> list;
    
    DBImplement dbib = new DBImplement("bookmark");
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        bookmarkList= dbib.selectAll();//takes data from database and returns it
       
        list = FXCollections.observableArrayList(bookmarkList);//Creates a new observable array list and adds a content of collection col to it.
        link.setCellValueFactory(new PropertyValueFactory("link"));
      //  linkdd.setCellValueFactory(new PropertyValueFactory("linkdd"));
       
        bookmarkTableView.setItems(list);
                
        bookmarkClearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {  //abstract class 
                
                dbib.delete();//delete method of DBImplement 
                list.clear();
                bookmarkTableView.setItems(list);
            }
        });
    }    
}
