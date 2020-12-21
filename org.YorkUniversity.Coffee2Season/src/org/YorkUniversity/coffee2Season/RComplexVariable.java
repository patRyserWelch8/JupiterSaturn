/*
 * Copyright (C) 2018 The University of York
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
package org.YorkUniversity.coffee2Season;

import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author prw509
 */
public class RComplexVariable extends RDataType
{
    
    private Complex value;

    public RComplexVariable(double aRealValue, double anImaginaryValue, String aVariableName, boolean isByValue) 
    {
        super(aVariableName, isByValue);
        this.value = new Complex (aRealValue, anImaginaryValue);
    }

    public RComplexVariable(String aComplexNumber, String aVariableName, boolean isByValue) 
    {
        super(aVariableName, isByValue);
        this.setValue(aComplexNumber);
    }


    @Override public String getRCommnandToInitaliseVariable() 
    {
        return variableName + " <- " + value.getReal() + "+" + value.getImaginary() + "i";
    }

    @Override public Object getValue() 
    {
        return value;
    }

    @Override public void setValue(String aValue) 
    {
        System.out.println(aValue);
        if (aValue != null)
        {
            value = new Complex(extractRealValue(aValue), this.extractImgValue(aValue));
        }
    }
       
    private Double extractRealValue(String aValue) 
    {
        int index = 1;
        Double realValue = 0.0;
        String tempValue = aValue.trim();
      
        if (tempValue.startsWith("-"))
        {
            if (tempValue.indexOf("+") > 0)
            {
                index = tempValue.indexOf("+");
                realValue = new Double(tempValue.substring(0, index));
            }
            else if (tempValue.substring(1).indexOf("-") > 0)
            {
                index = tempValue.substring(1).indexOf("-");
                realValue = new Double(tempValue.substring(0, index+1));
            } 
        }
        else
        {
            if (tempValue.indexOf("+") > 0)
            {
                index = tempValue.indexOf("+");
            }
            else
            {
                index = tempValue.indexOf("-");
            }
            realValue = new Double(tempValue.substring(0, index));
        }
        return realValue;
    }
    
    private Double extractImgValue(String aValue) 
    {
        int index;
        Double imgValue = null;
        String tempValue = aValue.trim();
      
        // remove the - of realNumber. 
        if (tempValue.startsWith("-"))
        {
            tempValue = tempValue.substring(1);
        }
        
        if (tempValue.contains("+"))
        {
            imgValue = new Double (tempValue.substring(tempValue.indexOf("+") + 1, tempValue.length()-1));
        }
        else if (tempValue.contains("-"))
        {
            imgValue = new Double (tempValue.substring(tempValue.indexOf("-") + 1, tempValue.length()-1));
            imgValue *= -1;
        }
        
        return imgValue;
    }

    @Override public String toString() 
    {
        return value.getReal() + "+" + value.getImaginary() + "i";
    }
    
    
}
