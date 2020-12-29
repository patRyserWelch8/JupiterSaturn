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
public class RMatrixVariable extends RVectorVariable
{
    private int             numberOfRows;
    private int             numberOfColumns;
    private RDataType[][]   matrix;
    
   /***
    * 
    * @param someValues             an List of values
    * @param aDataType              a datatype defined by the constants of RArguments (i.e. logical, characters, integers, complex, numeric)
    * @param aNumberOfRows          a number of rows in the matrix
    * @param aNumberOfColumns       a number of columns
    * @param aVariableName          a variable name in R
    * @param isByValue              a true value suggest by value and false by reference. The value can be captured in Java.
    */
    public RMatrixVariable(ArrayList someValues, int aDataType, int aNumberOfRows, int aNumberOfColumns, String aVariableName, boolean isByValue) 
    {
        super(someValues, aDataType, aVariableName, isByValue);
        this.numberOfRows = aNumberOfRows;
        this.numberOfColumns = aNumberOfColumns;
    }
    
   
    public RMatrixVariable (String someValues, int aDataType, String aVariableName, boolean isByValue)  
    {
        super(new ArrayList<RDataType>(), aDataType,  aVariableName, isByValue);
    }
   
    public int getNumberOfRows()
    {
        return numberOfRows;
    }
    
    public int getNumberOfColumns()
    {
        return numberOfColumns;
    }
    
    @Override public String getRCommnandToInitaliseVariable() 
    {
        String result;
         
        if (super.values.isEmpty())
        {
            result = variableName + "<-matrix(c(\"\"), ncol = 0, nrow = 0)";
        }
        else
        {
            result = variableName + "<- matrix(";
            result = result + super.toString() + ", ";
            result = result + "nrow = " + numberOfRows +  ", ";
            result = result + "ncol = " + numberOfColumns + ", byrow=TRUE)\n";
        }
        return result;
    }

    @Override public void setValue(String aValue) 
    {
       super.setValue(aValue);
    }

    @Override public Object getValue() 
    {
        createMatrix();
        return matrix;
    }

    @Override
    public String toString() 
    {
        String result = "";
        createMatrix();
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                result = result + matrix[i][j].getValue() + " ";
            }
            result  = result + "\n";
        }
       
        return result;
    }
    
    private void createMatrix()
    {
        matrix = new RDataType[numberOfRows][numberOfColumns];
        int index;
        for (int i = 0; i < matrix.length;i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {   
                index = (i * numberOfColumns) + j;
               
                if (index < values.size())
                {
                    matrix[i][j] = values.get(index);
                }
            }
        }   
    }
    
}
