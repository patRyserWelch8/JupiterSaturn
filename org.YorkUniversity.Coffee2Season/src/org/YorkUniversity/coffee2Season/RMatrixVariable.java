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
