
package browser1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.ConditionalFeature.FXML;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory.Entry;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * 
 */
public class MainPageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField userUrlField;
    @FXML
    private Button backwardButton;
    @FXML
    private Button forwardButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button reloadButton;
    @FXML
    private Button historyButton;
    @FXML
    private Button bookmarksButton;
    @FXML
    private Button addBookmarksButton;
    
    @FXML
    private WebView webview;
    
    private String homeUrl ="https://www.google.com/?gws_rd=cr,ssl&ei=dQWMV7GIOYz0vgTd-LfYCg&fg=1";
    private String userUrl;
    //private String updateUrl;
    private String lastUrl;
    DBImplement dbih = new DBImplement("history");
    DBImplement dbib = new DBImplement("bookmark");
    Link link = new Link();
    
    String bfString = null;
    
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        final WebEngine engine = webview.getEngine();
        engine.load(homeUrl);//for starting time load.
        userUrlField.setText(homeUrl);
        lastUrl = homeUrl;
        
        EventHandler<ActionEvent> toEnter= new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                UrlEdit ue = new UrlEdit();
                userUrl=userUrlField.getText();
                userUrl = ue.urlEdit(userUrl);
                lastUrl=userUrl;
                engine.load(userUrl);
                engine.setJavaScriptEnabled(true);//Sets the value of the property javaScriptEnabled.
                link.setLink(lastUrl);
                dbih.insert(link);
                
            }
        };
        
        userUrlField.setOnAction(toEnter);
        
        backwardButton.setOnAction(e -> engine.getHistory().go(-1));
        forwardButton.setOnAction(e -> engine.getHistory().go(1));
        
        engine.locationProperty().addListener(new ChangeListener<String>() {//setting URL of the current Web page.in url field
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                userUrlField.setText(newValue);
                lastUrl = newValue;
                link.setLink(newValue);
                dbih.insert(link);//inseting history
            }  
        });
        
        reloadButton.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              engine.reload();//Reloads the current page, whether loaded from URL or directly from a String in one of the loadContent methods.
          }
        });
        
        homeButton.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              homeUrl= "https://www.google.com/?gws_rd=cr,ssl&ei=dQWMV7GIOYz0vgTd-LfYCg&fg=1";
              lastUrl=homeUrl;
             engine.load(homeUrl);//Loads a Web page into this engine.
             engine.setJavaScriptEnabled(true);//Sets the value of the property javaScriptEnabled
          }
      });
        
        addBookmarksButton.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
             
               link.setLink(lastUrl);
               dbib.insert(link);
          //insert database Bookmark table
              
          }
      });
        
        bookmarksButton.setOnAction(new EventHandler<ActionEvent>() {
           Stage stage;
           Parent root;
           
           @Override
          public void handle(ActionEvent event) {
              
              try {
                  
                  stage = new Stage();
                  
                  //stage = (Stage) settingButton.getScene().getWindow();
                  root = FXMLLoader.load(getClass().getResource("Bookmarks.fxml"));//loads a fxml
                  Scene scene = new Scene(root);
                  stage.setScene(scene);
                  scene.getStylesheets().add(getClass().getResource("bookmarks.css").toExternalForm());//Gets an observable list of string URLs linking to the stylesheets to use with this scene's contents
                  stage.initModality(Modality.APPLICATION_MODAL);
                  //stage.initOwner(settingButton.getScene().getWindow());
                  stage.setTitle("Bookmarks");
                  stage.show();
                 // scene = new Scene(root);
              } catch (IOException ex) {
                  Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
              } 
              
              
              
          }
      });
        
        
        historyButton.setOnAction(new EventHandler<ActionEvent>() {
           Stage stage;
          Parent root;
           
           @Override
          public void handle(ActionEvent event) {
              
              
              try {
                  
                  stage = new Stage();
                  
                  //stage = (Stage) settingButton.getScene().getWindow();
                  root = FXMLLoader.load(getClass().getResource("History.fxml"));
                  Scene scene = new Scene(root);
                  stage.setScene(scene);
                  scene.getStylesheets().add(getClass().getResource("history.css").toExternalForm());
                  stage.initModality(Modality.APPLICATION_MODAL);
                  //stage.initOwner(settingButton.getScene().getWindow());
                  stage.setTitle("History");
                  stage.show();
                 // scene = new Scene(root);
              } catch (IOException ex) {
                  Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
              }
              
              
              
          }
      });
        
    }
}
