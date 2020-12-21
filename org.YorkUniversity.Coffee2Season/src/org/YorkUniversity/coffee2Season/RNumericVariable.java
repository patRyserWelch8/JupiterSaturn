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
public class RNumericVariable extends RDataType 
{

    private Double value;

    public RNumericVariable(Double aValue, String aVariableName, boolean isByValue) 
    {
        super(aVariableName, isByValue);
        this.value = aValue;
    }
    
    @Override
    public String getRCommnandToInitaliseVariable() 
    {
        return variableName + " <- " + (Double) value;
    }

    @Override
    public Object getValue() 
    {
        
        return value;
            
    }

    @Override
    public void setValue(String aValue) 
    {
        value = new Double(aValue);
    }

    @Override
    public String toString() 
    {
        return value.toString();
    }
    
    
    
}
