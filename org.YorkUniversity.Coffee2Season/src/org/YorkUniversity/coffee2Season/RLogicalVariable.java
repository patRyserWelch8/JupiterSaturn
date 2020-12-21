/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.YorkUniversity.coffee2Season;

/**
 *
 * @author prw509
 */
public class RLogicalVariable extends RDataType
{
    private Boolean value;

    public RLogicalVariable(Boolean aValue, String aVariableName, boolean isByValue) 
    {
        super(aVariableName, isByValue);
        this.value = aValue;
    }

    @Override public String getRCommnandToInitaliseVariable() 
    {
        if (value)
        {
            return variableName + " <- TRUE";
        }
        else
        {
            return variableName + " <- FALSE";
        }
    }

    @Override public Object getValue() 
    {
        return value;      
    }

    @Override
    public void setValue(String aValue) 
    {
        String tempValue = aValue;
        tempValue = tempValue.trim();
        if (tempValue.equals("TRUE"))
        {
            value = true;
        }
        else 
        {
            value = false;
        }
    }

    @Override
    public String toString() 
    {
        if (value)
            return "TRUE";
        else 
            return "FALSE";
       
    }
    
}
