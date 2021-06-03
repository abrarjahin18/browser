
package browser1;

import com.sun.javaws.Main;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Browser1 extends Application {
   
    TabPane root;
    
    public void start(Stage primaryStage) throws Exception {
        
        Parent browser = FXMLLoader.load(getClass().getResource("MainPage.fxml"));//loads a fxml document
        Tab browserTab = new Tab("New Tab", browser);//creating tab
        Tab addTab = new Tab("+", null);
        addTab.setClosable(false);  //Sets true if the tab is closable.
        addTab.setOnSelectionChanged(new EventHandler<Event>() {//Defines a function to be called when a selection changed has occurred on the tab.
            @Override
            public void handle(Event event) {
                addNewTab();//adding new tab
            }
        });
        
        root = new TabPane(browserTab, addTab);// allows switching between a group of Tabs
        Scene scene = new Scene(root);//creating scene
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {//Sets the value of the property onCloseRequest
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        primaryStage.setScene(scene);//setting scene on stage
        primaryStage.setTitle("ARP Browser");//setting title of stage
        scene.getStylesheets().add(getClass().getResource("mainpage.css").toExternalForm());//Gets an observable list of string URLs linking to the stylesheets to use with this scene's contents
        primaryStage.show();//shows created stage
        
    }
    
    final void addNewTab() {
        try {
            Parent browser = FXMLLoader.load(getClass().getResource("MainPage.fxml"));//loads a fxml document
            Tab browserTab = new Tab("New Tab", browser);
            root.getTabs().add(root.getTabs().size() - 1, browserTab);//gets The tab to display in a TabPane.
            root.getSelectionModel().select(browserTab);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
