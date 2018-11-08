/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvp.devfinity.identity.app.utils;

/**
 *
 * @author ajamiscosa
 */
import java.sql.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConnectionManager
{
    private static Connection _connection;

    public static final String CONNECTION_STRING = "jdbc:sqlserver://127.0.0.1:1433;databaseName=identity;user=sa;password=9951354a";
    
    private ConnectionManager()
    {

        if (_connection == null)
        {
            try {
                _connection = DriverManager.getConnection(CONNECTION_STRING);
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static Connection GetConnection()
    {
        new ConnectionManager();
        return _connection;
    }

    public static ResultSet ExecuteQuery(String Query)
    {
        try
        {
            Statement statement = _connection.createStatement();
            return statement.executeQuery(Query);
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static ResultSet ExecuteQuery(String Query, Object...args)
    {
        try
        {
            Statement statement = _connection.createStatement();
            return statement.executeQuery(String.format(Query, args));
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static boolean ExecuteCommand(String Command)
    {
        try
        {
            Statement statement = _connection.createStatement();
            return statement.execute(Command);
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean ExecuteCommand(String Command, Object...args)
    {
        try
        {
            Statement statement = _connection.createStatement();
            return statement.execute(String.format(Command, args));
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
            return false;
        }
    }
}