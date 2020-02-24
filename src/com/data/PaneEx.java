/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.data;

import javafx.scene.layout.Pane;



public class PaneEx extends Pane
{
    private String name;
    private boolean modified;
    
    public PaneEx()
    {
        super();
        this.name = "Drawing-01";
        this.modified = false;
    }


    public String getName()
    {
        return name;
    }


    public void setName(String name)
    {
        this.name = name;
    }


    public boolean isModified()
    {
        return modified;
    }

    public void setModified(boolean modified)
    {
        this.modified = modified;
    }
}
