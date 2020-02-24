/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Parse the XML configuration file. The example of the configuration file is shown below:
 *
 * @author peter.nguyen
 */
public class ConfigurationFileParser
{
    private static final String ROOT_ELEMENT_NAME = "Draw";
    private static final String DATA_SOURCE_ELEMENT_NAME = "DataSource";
    private static final String CONNECTION_STRING_ATTRIBUTE_NAME = "connectionString";
    private static final String USERNAME_ATTRIBUTE_NAME = "userName";
    private static final String USERPASSWORD_ATTRIBUTE_NAME = "userPassword";
    
    private Document configurationFileDocument = null;
    
    public ConfigurationFileParser(String configurationFile)
    {
        try
        {
            File configFile = new File(configurationFile);
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();        
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            this.configurationFileDocument = dBuilder.parse(configFile);
            this.configurationFileDocument.getDocumentElement().normalize();
        }
        
        catch (Exception ex)
        {
            System.out.printf("Unable to load configuration file: %s\n", configurationFile);
            System.out.printf("%s\n", ex.getMessage());
        }
    }
    
    public boolean isValid()
    {
        if (null == this.configurationFileDocument)
            return false;
        
        String rootElementName = this.configurationFileDocument.getDocumentElement().getNodeName();
        return rootElementName.equals(ConfigurationFileParser.ROOT_ELEMENT_NAME);
    }
    
    public DataSource getDataSource()
    {
        if (!isValid())
            return null;
        
        NodeList nodeList = this.configurationFileDocument.getElementsByTagName(ConfigurationFileParser.DATA_SOURCE_ELEMENT_NAME);
        if (nodeList.getLength() != 1)
        {
            System.out.printf("Expected only one XML element (%s)\n", ConfigurationFileParser.DATA_SOURCE_ELEMENT_NAME);
            return null;
        }
        
        Element element = (Element) nodeList.item(0);
        DataSource ds = new DataSource(element.getAttribute(ConfigurationFileParser.CONNECTION_STRING_ATTRIBUTE_NAME),
                                       element.getAttribute(ConfigurationFileParser.USERNAME_ATTRIBUTE_NAME),
                                       element.getAttribute(ConfigurationFileParser.USERPASSWORD_ATTRIBUTE_NAME));         
        
        return ds;
    }
}
