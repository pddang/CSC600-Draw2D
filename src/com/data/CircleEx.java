
package com.data;

import javafx.scene.shape.Circle;

public class CircleEx extends Circle
{
    private boolean selected;
    
    public CircleEx()
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
