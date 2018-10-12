package clippy.controllers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

        }catch(SQLException sqlerr){
            System.out.println(sqlerr.getMessage());
        }
    }
    public ArrayList getAllData(){
        String query = "SELECT * FROM textData ORDER BY id DESC LIMIT ?";
        ArrayList<String> textList = new ArrayList<>();
        try{
            PreparedStatement pstmt = this.conn.prepareStatement(query);
            pstmt.setInt(1, 10);
            System.out.println("Data Selected for getAllData()");
            ResultSet set = pstmt.executeQuery();
            while (set.next()){
                textList.add(set.getString("text"));

            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return textList;

    }



}
