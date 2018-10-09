package clippy.controllers;

import java.sql.*;

public class DbController {
    private Connection conn;
    public DbController(){
        this.conn = null;
        try{
            String url = "jdbc:sqlite:clippy.db";
            this.conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        }catch(SQLException sqlerr){
            System.out.println(sqlerr.getMessage());
        }
    }
    public void createTable(){
        String query = "CREATE TABLE `textdata` \n"
        + "( `id` INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, `text` TEXT, `time` TEXT )";

        try{
            Statement stmt = this.conn.createStatement();
            System.out.println("Ran query...");
            stmt.execute(query);
            try{
                if(this.conn != null){
                    System.out.println("Closing Connection...1");
                    this.conn.close();
                }
            }catch(SQLException sqlerr){
                System.out.println(sqlerr.getMessage());
            }
        }catch(SQLException sqlerr){
            System.out.println(sqlerr.getMessage());
        }
    }

    public void writeData(String text, String time){
        String query = "INSERT INTO textdata(text, time) VALUES(?, ?)";
        try{
            PreparedStatement pstmt = this.conn.prepareStatement(query);
            pstmt.setString(1, text);
            pstmt.setString(2, time);
            System.out.println("Data inserted...2");
            pstmt.executeUpdate();
            try{
                if(this.conn != null){
                    this.conn.close();
                    System.out.println("Closing connection...2");
                }
            }catch(SQLException sqlerr){
                System.out.println(sqlerr.getMessage());
            }
        }catch(SQLException sqlerr){
            System.out.println(sqlerr.getMessage());
        }
    }

}
