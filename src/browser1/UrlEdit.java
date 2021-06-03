
package browser1;

public class UrlEdit {
    
    public String urlEdit(String userText)
    {
        //userText= userText.trim();
        userText = userText.replaceAll("\\s","");
        if(userText.charAt(0)!='h' || userText.charAt(1)!='t' || userText.charAt(2)!='t' || userText.charAt(3)!='p'){
         
        String st = "https://www.";
        st+=userText;
        return st;
        }
        else{
            return userText;
        }
        
        
    }
}
