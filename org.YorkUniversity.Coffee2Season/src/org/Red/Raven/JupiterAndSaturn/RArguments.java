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


import java.io.File;
import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Map;
import org.YorkUniversity.Coffee2StoredData.FileTools;

/**
 * This class builds all the arguments of a function as a list. All the data type are used in Java and in R programming languages. 
 * @author Patricia Ryser-Welch
 */
public class RArguments 
{
    public static final int LOGICAL = 0;
    public static final int NUMERIC = 1;
    public static final int INTEGER = 2;
    public static final int CHARACTER = 3;
    public static final int COMPLEX = 4;
    
    private AbstractList<RDataType> argumentList;
    private FileTools output;
    private Map<String,String> valuesFromR;
    private int maxNumOfArguments;
    
    /***
     * constructor
     * @param numberOfArguments - maximum number of arguments.
     * @throws IOException when a problem occurs during the creation of temporary files
     */
    public RArguments(int numberOfArguments) throws IOException 
    {
        this.argumentList = new ArrayList(numberOfArguments);
        this.output = new FileTools(File.createTempFile("ArgumentList", ".csv"));
        this.maxNumOfArguments = numberOfArguments;
    }
    
    /****
     * removes all the arguments the list.
     */
    public void clearAllArguments()
    {
        argumentList.clear();
    }
    /***
     * adds an argument to a list of argument.
     * @param anArgument 
     */
    public void addNewArgument(RDataType anArgument)
    {
        if (anArgument != null)
        {
            argumentList.add(anArgument);
        }
    }
       
    /***
     * removes a specific argument from the list 
     * @param anArgument 
     */
    public void deleteAnArgument (RDataType anArgument)
    {
        int indexToRemove = -1;
        indexToRemove = findAnArgument(anArgument);
   
        if (indexToRemove > -1)
        {
            argumentList.remove(indexToRemove);
        }
    }

   
    /***
     * retrieves the value of a R variable
     * @param aVariableName - the name of a valid R variable
     * @return The value of a R variable
     * @throws RConnectionException 
     */
    public RDataType getAnArgument(String aVariableName) throws RConnectionException
    {
        RDataType argumentToFind = new RVariable(aVariableName, false);
        int index = findAnArgument(argumentToFind);
        if (index > -1)
        {
            return argumentList.get(index);
        }
        else 
        {
            throw new RConnectionException(15);
        }
    }
    
    /***
     * retrieve all the names of the arguments that passes some values by references and by values.
     * @return the names of every argument in the list.
     */
    public String getFunctionArguments()
    {
        String result = "";
        RDataType argument;
        
        
        for (int i = 0; i < this.maxNumOfArguments; i++)
        {
            if (argumentList.get(i).isByValue())
            {
                if (result.equals(""))
                {
                    result = result + argumentList.get(i).getVariableName();
                }
                else
                {
                    result = result + "," + argumentList.get(i).getVariableName();
                }
            }
        }
        
        return result;
    }
    
    /***
     * retrieve the name of the variable  that hold the returned value of a function.
     * @return  the name of the returned variable from R to Java
     */
    public String getFunctionReturnedVariable()
    {
        String result="";
        for (RDataType anArgument: argumentList)
        {
            if (!anArgument.isByValue())
            {
                result = anArgument.getVariableName();
            }
        }
        
        return result;
    }
    
  
    /**
     * writes the R commands to initialise all the arguments
     * @return a set of R commands
     */  
    public String getRCommandsToinitialiseArguments()        
    {
        String result = " ";
        if (maxNumOfArguments <= argumentList.size())
        {
            for (int i = 0; i < maxNumOfArguments; i++)
            {
                    result = result + argumentList.get(i).getRCommnandToInitaliseVariable() + "\n";
            }
        }
        return result;
    }
   
    /***
     * writes a R command to write the values into a file
     * @return a set of R commands
     */
    public String getRCommandToSaveRValuesIntoAFile() 
    {
        String result = "";
       
        result = "sink('" + output.getAbsolutePathAndNameForR() + "')\n";
        
        if (maxNumOfArguments <= argumentList.size())
        {
            for (int i = 0; i < maxNumOfArguments; i++)
            {
                if (!argumentList.get(i).isByValue())
                {    
                    result = result + "cat(\"" + argumentList.get(i).getVariableName() + ",\"," + argumentList.get(i).getVariableName() + ",\"\\n\")\n";
                }
            }
        }
        
        result = result + "sink()\n";
        return result;
    }
    
    /***
     * Retrieves the values of some arguments after the R script has been extracted. The values 
     * are read from a csv file.
     * @throws RConnectionException 
     */
    public void extractRValues() throws RConnectionException
    {
        RDataType argument;
        valuesFromR = output.readDataFromCSVfiles();
        for(Map.Entry valueFromR : valuesFromR.entrySet())
        {
            argument = getAnArgument((String) valueFromR.getKey());
            argument.setValue((String) valueFromR.getValue());
        }
    }
    
    
    /***
     * This boolean method verifies whether an argument by reference exists. 
     * @return If one argument has the property "ByValue" set to false, then the method returns true. Otherwise, it returns False.
     */
    public boolean hasAFunctionReturnedVariableSet()
    {
        boolean result = false;
        int i = 0;
        while (!result && i < argumentList.size())
        {
            System.out.println("$$$$$" + !argumentList.get(i).isByValue());
            result = !argumentList.get(i).isByValue();
            i++;
        }
        
        return result;
    }
    
    public int getNoOfArguments()
    {
        return argumentList.size();
    }

    
    private int findAnArgument(RDataType anArgument) 
    {
        int i = 0;
        int indexToReturn = -1;
        while (i < argumentList.size())
        {
            if (argumentList.get(i).equals(anArgument))
            {
                indexToReturn = i;
                i = argumentList.size();
            }
            else  
            {
                i++;
            }          
        }
        return indexToReturn;
    }
    
    
    
    private class RVariable extends RDataType
    {

        public RVariable(String aVariableName, boolean isByValue) 
        {
            super(aVariableName, isByValue);
        }

        @Override
        public String getRCommnandToInitaliseVariable() 
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object getValue() 
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void setValue(String aValue) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    
}
