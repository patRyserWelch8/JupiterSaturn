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
