/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package browser1;

public class Link {
   
    private String linkp;
    private String linkdd;
    
    public Link() {
    }

  //public Link(String link, String link_date) {
    //    this.link = link;
      //  this.link_date = link_date;
    //}

    public String getLink() {
        return linkp;
    }

    public void setLink(String linkp) {
        this.linkp = linkp;
    }
    
    public String getLinkDate() {
        return linkdd;
    }

    public String setLinkDate(String linkdd) {
        this.linkdd = linkdd;
        return linkdd; 
    }
}
