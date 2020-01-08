package com.company;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
    static String DB_URL = "jdbc:mysql://localhost/";
    static final String DB_NAME = "Immobilien";

    static final String Table1 = "Immobilien";
    static final String Table2 = "Owner";

    static final String conString = DB_URL + DB_NAME;

    static final String USER = "root";
    static final String PASS = "root";

    static Connection conn = null;
    static Statement stmt = null;


    ////// DB Preperation
    static void ClearDB(){
        try {
            conn = DriverManager.getConnection(conString, USER, PASS);
            stmt = conn.createStatement();
            String clear_sql = "DROP DATABASE " + DB_NAME;
            stmt.executeUpdate(clear_sql);
            System.out.println("Clear Database...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void CreateDB(){
        try {
            String create_sql = "CREATE DATABASE " + DB_NAME;
            stmt.executeUpdate(create_sql);
            System.out.println("Create Database...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void CreateDBTables(){
        try {
            conn = DriverManager.getConnection(conString, USER, PASS);

            stmt = conn.createStatement();

            String sql1 = "CREATE TABLE " + Table1 +
                    " (id INTEGER not NULL AUTO_INCREMENT, " +
                    " address VARCHAR(255), " +
                    " type VARCHAR(255), " +
                    " owner_id INTEGER, " +
                    " PRIMARY KEY ( id ))";

            String sql2 = "CREATE TABLE " + Table2 +
                    " (id INTEGER not NULL AUTO_INCREMENT, " +
                    " first VARCHAR(255), " +
                    " last VARCHAR(255), " +
                    " PRIMARY KEY ( id ))";

            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);

            System.out.println("Create Database Tables...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void FillDBTables(){
        try {
            for(int i = 0; i < 10;) {
                String sql3 = "INSERT INTO " + Table1 + "(address, type, owner_id) " +
                        "VALUES ('Placeholder Street " + i + "', 'private', 0)";
                String sql4 = "INSERT INTO " + Table2 + "(first, last) " +
                        "VALUES ('Max', 'Mustermann_" + i + "')";
                stmt.executeUpdate(sql3);
                stmt.executeUpdate(sql4);
                i++;
            }
            System.out.println("Fill Database Tables...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void PrepareDB(){
        ClearDB();
        CreateDB();
        CreateDBTables();
        FillDBTables();
    }
    ///______


    //INSERT entry
    static public Boolean addImmobilie(String address, String type, int owner_id) {
        //SQL STATEMENT
        String sql = "INSERT INTO " + Table1 + "(address,type, owner_id) VALUES('" + address + "','" + type + "','" + owner_id + "')";

        try {
            Connection con = DriverManager.getConnection(conString, USER, PASS);
            //PREPARED STMT
            Statement s = con.prepareStatement(sql);

            s.execute(sql);

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    static public Boolean addOwner(String first, String last) {
        //SQL STATEMENT
        String sql = "INSERT INTO " + Table2 + "(first, last) VALUES('" + first + "','" + last + "')";

        try {
            Connection con = DriverManager.getConnection(conString, USER, PASS);
            //PREPARED STMT
            Statement s = con.prepareStatement(sql);

            s.execute(sql);

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static Immobilie getImmobilie(int id) {
        Immobilie immo = new Immobilie();
        String sql = "SELECT * FROM Immobilien WHERE id = " + id;
        try {
            Connection con = DriverManager.getConnection(conString, USER, PASS);
            Statement s = con.prepareStatement(sql);
            ResultSet rs = s.executeQuery(sql);

            while(rs.next()) {
                immo.setId(rs.getInt("id"));
                immo.setAddress(rs.getString("address"));
                immo.setType(rs.getString("type"));
                immo.setOwner_id(rs.getInt("owner_id"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while fetching Immobilien Data.");
            return null;
        }
        return immo;
    }

    public static Owner getOwner(int id) {
        Owner own = new Owner();
        String sql = "SELECT * FROM Owner WHERE id = " + id;
        try {
            Connection con = DriverManager.getConnection(conString, USER, PASS);
            Statement s = con.prepareStatement(sql);
            ResultSet rs = s.executeQuery(sql);

            while(rs.next()) {
                own.setId(rs.getInt("id"));
                own.setFirst(rs.getString("first"));
                own.setLast(rs.getString("last"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while fetching Owner Data.");
            return null;
        }
        return own;
    }


    //UPDATE ENTRY
    static Boolean updateImmobilie(int id, String address, String type) {
        Immobilie immo = getImmobilie(id);
        if (address.equals("")){
            address = immo.getAddress();
        }
        if (type.equals("")){
            type = immo.getType();
        }


        //SQL STMT
        String sql = "UPDATE Immobilien SET address ='" + address + "',type='" + type + "' WHERE ID='" + id + "'";

        try {
            //GET CONNECTION
            Connection con = DriverManager.getConnection(conString, USER, PASS);

            //STATEMENT
            Statement s = con.prepareStatement(sql);

            //EXECUTE
            s.execute(sql);

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    static Boolean updateOwner(int id, String first, String last) {
        Owner own = getOwner(id);
        if (first.equals("")){
            first = own.getFirst();
        }
        if (last.equals("")){
            last = own.getLast();
        }


        //SQL STMT
        String sql = "UPDATE Owner SET first ='" + first + "',last='" + last + "' WHERE ID='" + id + "'";

        try {
            //GET CONNECTION
            Connection con = DriverManager.getConnection(conString, USER, PASS);

            //STATEMENT
            Statement s = con.prepareStatement(sql);

            //EXECUTE
            s.execute(sql);

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static List<Immobilie> getImmobilienData() {
        List<Immobilie> ImmobilienData = new ArrayList<>();
        String sql = "SELECT * FROM " + Table1;
        try {
            Connection con = DriverManager.getConnection(conString, USER, PASS);
            //PREPARED STMT
            Statement s = con.prepareStatement(sql);
            ResultSet rs = s.executeQuery(sql);

            while(rs.next()) {
                Immobilie immo = new Immobilie();
                immo.setId(rs.getInt("id"));
                immo.setAddress(rs.getString("address"));
                immo.setType(rs.getString("type"));
                immo.setOwner_id(rs.getInt("owner_id"));
                ImmobilienData.add(immo);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while fetching Immobilien Data.");
            return null;
        }
        return ImmobilienData;
    }

    public static List<Owner> getOwnerData() {
        List<Owner> OwnerData = new ArrayList<>();
        String sql = "SELECT * FROM " + Table2;
        try {
            Connection con = DriverManager.getConnection(conString, USER, PASS);
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                Owner owner = new Owner();
                owner.setId(rs.getInt("id"));
                owner.setFirst(rs.getString("first"));
                owner.setLast(rs.getString("last"));
                OwnerData.add(owner);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while fetching Owner Data.");
            return null;
        }
        return OwnerData;
    }

    //DELETE DATA
    static public Boolean deleteImmobilie(int id)
    {
        //SQL STMT
        String sql="DELETE FROM Immobilien WHERE ID ='"+id+"'";

        try
        {
            //GET COONECTION
            Connection con=DriverManager.getConnection(conString, USER, PASS);

            //STATEMENT
            Statement s=con.prepareStatement(sql);

            //EXECUTE
            s.execute(sql);

            return true;

        }catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
    static public Boolean deleteOwner(int id)
    {
        //SQL STMT
        String sql="DELETE FROM Owner WHERE ID ='"+id+"'";

        try
        {
            //GET COONECTION
            Connection con=DriverManager.getConnection(conString, USER, PASS);

            //STATEMENT
            Statement s=con.prepareStatement(sql);

            //EXECUTE
            s.execute(sql);

            return true;

        }catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }


    public static void main(String args[]) {
        PrepareDB();
    }
}
