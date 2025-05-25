package Database;
import java.sql.*;

public class Database {
    private static final String url = "jdbc:mysql://localhost:3306/talky";
    private static final String user = "root";
    private static final String password = "1534";
    Connection conn;


    Database(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(url,user,password);
            System.out.println("connected to : " + conn.getCatalog().toUpperCase() + " database");
        } catch (Exception e) {
            System.out.println("error in db.init");
        }
    }
    public ResultSet query(String query){
        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            ResultSet result = stmt.executeQuery();
            System.out.println(result);
            stmt.close();
            return result;
        } catch (SQLException e) {
            System.out.println("error in db.query");
            throw new RuntimeException(e);
        }finally {

        }
    }
    public ResultSet getAllUsers(){
        ResultSet result;
        result = this.query("select * from users;");
        return result;
    }
    public void close(){
        try {
            this.conn.close();
        } catch (SQLException e) {
            System.out.println("error in db.close");
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {

        Database db = new Database();
        ResultSet result=db.query("insert into Users (NAME,IP,PORT) values (\"user1\",\"1.0.0.0.0\",01);");

        while(result.next()){
            System.out.println(result.getString("name"));
        }
        db.close();


    }
}
