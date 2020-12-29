/*
 * Copyright (C) 2020 Red Raven Patricia Ryser-Welch
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.Red.Raven.JupiterAndSaturn;

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
