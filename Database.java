import java.lang.String;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private final String username="klaudia";
    private final String password="jankopanko";
    private final String host = "localhost";
    private final String port = "3306";
    private final String url = "jdbc:mysql://localhost:3306/db1";

    private Connection getConnection(){
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("DriverLoaded");
            connection = DriverManager.getConnection(url, username, password);
            return connection;
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public void insertNewPerson(Person person){
        try {
            Connection conn = getConnection();

            assert conn != null;
            PreparedStatement stmt=conn.prepareStatement("INSERT INTO person(name,surname,dob,bnum) values(?,?,?,?)");
            stmt.setString(1,person.getName());
            stmt.setString(2,person.getSurname());
            stmt.setDate(3,new Date(person.getDob().getTime()));
            stmt.setString(4,person.getBnum());
            int result = stmt.executeUpdate();

            closeConnection(conn);

        } catch (SQLException e){
            e.printStackTrace();
        }


    }

    private void closeConnection(Connection conn){
        if(conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Person selectBySurname(String surname){
        Person jozo = null;
        try {
            Connection conn = getConnection();

            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement("select * from person where surname =? ");
            pst.setString(1, surname);
            rs = pst.executeQuery();
            while (rs.next()) {
                jozo = new Person(rs.getString("name"),rs.getString("surname"),
                        rs.getDate("dob"),rs.getString("bnum"));
            }


        }catch (SQLException e){
            e.printStackTrace();
        }


        return jozo;
    }

   public Person selectBybid(String bnum){
       Person jozo = null;
       try {
           Connection conn = getConnection();

           PreparedStatement pst = null;
           ResultSet rs = null;
           pst = conn.prepareStatement("select * FROM person where bnum = ? ");
           pst.setString(1,bnum);
           rs = pst.executeQuery();
           while (rs.next()){
               jozo = new Person(rs.getString("name"),
                       rs.getDate("dob"),rs.getString("bnum"));
           }

       }catch (SQLException e){
           e.printStackTrace();
       }
       return jozo;
   }

    public List <Person> getAllMan(){
        Connection conn=getConnection();
        String query="SELECT * FROM person WHERE pin LIKE '__0%' OR pin LIKE '__1%'";
        List<Person> persons = new ArrayList<>();
        ResultSet rs;
        try {
            PreparedStatement stmt=conn.prepareStatement(query);
            rs=stmt.executeQuery();
            while(rs.next()){
                String lastname=rs.getString("lname");
                String fname=rs.getString("fname");
                String pin=rs.getString("pin");
                Date dob=rs.getDate("dob");
                Person p=new Person(lastname,fname,dob,pin);
                persons.add(p);
            }
            closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }

}