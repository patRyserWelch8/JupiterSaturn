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
public class RIntegerVariable extends RDataType
{
    private Integer  value;

    public RIntegerVariable(Integer aValue, String aVariableName, boolean isByValue) 
    {
        super(aVariableName, isByValue);
        this.value = aValue;
    }

    @Override public String getRCommnandToInitaliseVariable() 
    {
        return variableName + " <- " + value + "L";
    }

    @Override public Object getValue() 
    {
        return value;
    }

    
    public void setValue(String aValue) 
    {
        String tempValue = aValue;
        tempValue = tempValue.trim();
        
        if (tempValue.endsWith("L"))
        {
            value = new Integer(tempValue.substring(0, tempValue.length()-1));
        }
        else
        {
            value = new Integer(tempValue);
        }
        
    }

  
    public String toString() 
    {
        return value.toString();
    }
    

   
    
}
