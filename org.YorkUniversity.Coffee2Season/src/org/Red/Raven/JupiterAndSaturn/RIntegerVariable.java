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
