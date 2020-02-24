/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database;

/**
 *
 * @author peter.nguyen
 */
public class DataSource
{
    private String connection;
    private String userName;
    private String userPassword;
    
    public DataSource()
    {
        this.connection = null;
        this.userName = null;
        this.userPassword = null;
    }
    
    public DataSource(String connection, String userName, String userPassword)
    {
        this.connection = connection;
        this.userName = userName;
        this.userPassword = userPassword;        
    }

    /**
     * @return the connection
     */
    public String getConnection()
    {
        return connection;
    }

    /**
     * @param connection the connection to set
     */
    public void setConnection(String connection)
    {
        this.connection = connection;
    }

    /**
     * @return the userName
     */
    public String getUserName()
    {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    /**
     * @return the userPassword
     */
    public String getUserPassword()
    {
        return userPassword;
    }

    /**
     * @param userPassword the userPassword to set
     */
    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }
}
