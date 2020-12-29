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
 * This class represents a variable of type character in R; this type of variables encodes
 * a string of characters and are treated as text. In Java, the wrapper class String provides 
 * the equivalent data type. As a result, a class variable of type String is encapsulated within 
 * this class. \n\n
 * 
 * @author Patricia Ryser-Welch
 */
public class RCharacterVariable extends RDataType
{
    private String value;
    
    /***
     * Constructor of RCharacterVariable
     * @param aValue - some texts to be passed in R 
     * @param aVariableName  - the name of the variable in R
     * @param isByValue  - true is the variable is considered by value. False if the value is passed by reference
     */
    public RCharacterVariable(String aValue, String aVariableName, boolean isByValue) 
    {
        super(aVariableName, isByValue);
        this.value = aValue;
    }

    /****
     * This method writes an R command that assigns a 'Character' value to the R variable
     * @return a R assignment statements
     */
    @Override public String getRCommnandToInitaliseVariable() 
    {
        return variableName + " <- \"" + (String) value + "\"";  
    }

    /***
     * This method returns the value of the variable.
     * @return the value of variable
     */
    @Override
    public Object getValue() 
    {
       return value;
    }

    /***
     * This method assigns a value to the variable
     * @param aValue representing a text 
     */
    @Override
    public void setValue(String aValue) 
    {
        value = aValue;
    }

    /**
     * 
     * @return the value of the variable
     */
    @Override
    public String toString() 
    {
        return "\"" + value + "\"";
    }
    
}
