/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.data;

import javafx.scene.shape.Rectangle;


public class RectangleEx extends Rectangle
{
    private boolean selected;
    
    public RectangleEx()
    {
        super();
        this.selected = false;
    }    


    public boolean isSelected()
    {
        return selected;
    }


    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }
}
