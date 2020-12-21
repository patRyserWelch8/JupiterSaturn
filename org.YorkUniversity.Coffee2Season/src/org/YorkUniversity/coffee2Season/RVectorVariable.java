/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.YorkUniversity.coffee2Season;

import java.util.ArrayList;

/**
 *
 * @author prw509
 */
public class RVectorVariable extends RDataType
{

    protected ArrayList<RDataType> values;
    protected int dataType;

    public RVectorVariable(ArrayList values, int aDataType, String aVariableName, boolean isByValue) 
    {
        super(aVariableName, isByValue);
        this.values = values;
        this.dataType = aDataType;
    }
    
    public int getDataType()
    {
        return dataType;
    }
    
    @Override public String getRCommnandToInitaliseVariable() 
    {
        String result = variableName + " <- ";
        result = result + toString();
        return result;        
    }

    @Override public void setValue(String aValue) 
    {
        String[] tempValues = aValue.split(" ");
        values.clear();
       
        for (int i = 0; i < tempValues.length; i++)
        {
            if (tempValues[i].length() > 0)
            {
                switch (dataType)
                {
                    case RArguments.LOGICAL:    values.add(new RLogicalVariable(tempValues[i].equals("TRUE"),Integer.toString(i),true));
                                                break;
                    case RArguments.CHARACTER:  values.add(new RCharacterVariable(tempValues[i], Integer.toString(i),true));
                                                break;
                    case RArguments.NUMERIC:    values.add(new RNumericVariable(new Double(tempValues[i]),Integer.toString(i),true));
                                                break;
                    case RArguments.INTEGER:    values.add(new RIntegerVariable(new Integer(tempValues[i]),Integer.toString(i),true));
                                                break; 
                    case RArguments.COMPLEX:    values.add(new RComplexVariable(tempValues[i],Integer.toString(i),true));
                                                break;                        
                }
            }
        }
        
        
    }
    
    

    @Override public Object getValue() 
    {
        return values;
    }

    @Override
    public String toString() 
    {
        String result =  "c(";
        for (int i = 0; i < values.size(); i++)
        {
           
            switch (dataType)
            {
                    case RArguments.LOGICAL:    result = result + values.get(i) + ",";
                                                break;
                    case RArguments.CHARACTER:  result = result + "\"" + values.get(i) + "\",";
                                                break;
                    case RArguments.NUMERIC:    result = result + values.get(i) + ",";
                                                break;
                    case RArguments.INTEGER:    result = result + values.get(i) + ",";
                                                break;
                    case RArguments.COMPLEX:    result = result + values.get(i) + ",";
                                                break;
            }
           
        }
        result = result.substring(0, result.length()-1) +  ")";
        return result;         
    }
    
    
    
   
}
