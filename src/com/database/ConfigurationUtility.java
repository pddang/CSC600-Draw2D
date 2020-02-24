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
public class ConfigurationUtility
{
    private static final String CONFIGURATION_FILE_PATH = "/Users/phan/IdeaProjects/Drawing2D/src/com/database/Configuration.xml";
    
    public static DataSource getDataSource()
    {
        ConfigurationFileParser configFileParser = new ConfigurationFileParser(ConfigurationUtility.CONFIGURATION_FILE_PATH);
        return configFileParser.getDataSource();         
    }    
}
