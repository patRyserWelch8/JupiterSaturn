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

/**
 * This abstract class maps the R data types to some equivalent data types in Java. The properties of this class 
 * define the name of a R variable. The latter should be declared in the header of a function as an argument in a chosen R script.
 * A boolean value indicates whether the argument passes some value from Java to R (i.e byValue is set to true). When this byValue is set 
 * to false, then a value from R is passed back to Java. This behaviour mimics passing some arguments by references.
 * @author Patricia Ryser-Welch
 */
public abstract class RDataType 
{
    protected String variableName; 
    protected boolean byValue;

    /***
     * constructor
     * @param aVariableName - a R variable Name
     * @param isByValue   - a flag indicating whether some values are passed by value (true) or by reference  (false)
     */
    public RDataType(String aVariableName, boolean isByValue) 
    {
        this.variableName = aVariableName;
        this.byValue = isByValue;
    }

    
    /**
     * Get the value of ByValue
     *
     * @return the value of ByValue - true the variable passes some values by values.  False indicates the values are passed by reference
     */
    public boolean isByValue() 
    {
        return byValue;
    }

     /**
     * Get the R variable Name 
     *
     * @return the value of a R argument (or R variable)
     */
    public String getVariableName()
    {
        return variableName;
    }

   /***
    * This methods compares whether the instantiation is the same. It also verifies the name
    * of the R variables are the same. It is worth noting R variables and arguments can 
    * change R data types during the execution of the R script. It is not a suitable option to check 
    * for the data type. 
    * @param otherArgument - an other RData type
    * @return True if the name the variable is the same or the unique object id is the same.
    */
    @Override  public boolean equals(Object otherArgument) 
    {
        RDataType other = (RDataType) otherArgument;
        boolean result = false;
        if (otherArgument != null)
        {
            if (this == other)
            {
                result = true;
            }
            else if (variableName.equals(other.getVariableName()))
            {
                result = true;
            }
        }
        return result;  
    }
    
    
    /***
     * initialises a value of a variable or an argument in R.
     * @return a R command
     */
    public abstract String getRCommnandToInitaliseVariable();
    
     /***
     * sets a value of a R variable or a R argument from a Java variable.
     * @return a R command
     */
    public abstract void   setValue(String aValue);
    
    /**
     * gets the value of a R argument or a R variable
     * @return  the value as an Object
     */
    public abstract Object getValue();
    
           
}
