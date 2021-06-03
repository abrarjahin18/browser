
package browser1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class DBImplement {
    
    String flag;
    

    public DBImplement(String flag) {
        this.flag = flag;
    }
    
    public List<Link> selectAll() {
        
        List<Link> historyList = new ArrayList<Link>();
	Connection connection = null;
	Statement statement = null;
        ResultSet resultSet = null;
        //String F;
        try {
                connection = ConnectionConfigure.getConnection();
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM browser."+flag);//takes input from database

                while (resultSet.next()) {
                       Link link = new Link();
                       link.setLink(resultSet.getString("Link"));
                       link.setLinkDate(resultSet.getString("Link_date"));
                   // link.setLinkDate(resultSet.getString("Link_date"));   
                       System.out.println(link.setLinkDate(resultSet.getString("Link_date")));
                       historyList.add(link);
                }

        } catch (Exception e) {
                e.printStackTrace();
        } finally {
                if (resultSet != null) {
                        try {
                                resultSet.close();//Releases this ResultSet object's database and JDBC resources immediately instead of waiting for this to happen when it is automatically closed.
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }
                if (statement != null) {
                        try {
                                statement.close();//Releases this Statement object's database and JDBC resources immediately instead of waiting for this to happen when it is automatically closed.
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }
                if (connection != null) {
                        try {
                                connection.close();//Releases this Connection object's database and JDBC resources immediately instead of waiting for them to be automatically released
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }
        }

        return historyList; //To change body of generated methods, choose Tools | Templates.
    }
     
    public void insert(Link link) {
        
        Connection connection = null;
	PreparedStatement preparedStatement = null;

		try {
			connection = ConnectionConfigure.getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO "+flag+"(Link, Link_date)" +
					"VALUES (?, ?)");
                        
			preparedStatement.setString(1, link.getLink());
                        preparedStatement.setString(2, new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
			preparedStatement.executeUpdate();
			//System.out.println("complete");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	
    }
      
      
       public void delete() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
                connection = ConnectionConfigure.getConnection();
                preparedStatement = connection.prepareStatement("DELETE FROM "+flag);              
                preparedStatement.executeUpdate();

        } catch (Exception e) {
                e.printStackTrace();
        } finally {
                if (preparedStatement != null) {
                        try {
                                preparedStatement.close();
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }
                if (connection != null) {
                        try {
                                connection.close();
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }
        } //To change body of generated methods, choose Tools | Templates.
    }
}
