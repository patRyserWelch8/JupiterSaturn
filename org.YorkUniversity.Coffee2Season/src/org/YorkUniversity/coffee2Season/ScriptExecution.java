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

import java.io.File;
import java.io.IOException;
import org.YorkUniversity.Coffee2StoredData.FileTools;

/**
 * This abstract class provides the main tools to execute some R functions from Java. 
 * @author Patricia Ryser-Welch
 */
public abstract class ScriptExecution 
{
    protected DirectConnection process;
    protected FileTools        rScript;
    protected FileTools        rLibPath;
    protected RArguments       argumentList;
    protected RSources         sourcesList;
    protected String           functionName;
    protected String           pathToRScript;
   
    
    /****
     * Constructor 
     * @param aPathToR  - The path to the R environment
     * @param aScript  -  The absolute path to a R script
     * @param aFunctionName - The name of a R function to execute. The function must be defined in the R script.
     * @param aRLibPath   - The path to R libraries. 
     * @param aNumberOfArguments - The number of R arguments 
     * @param aNumberOfSources - The number of R sources required to run the R script
     * @throws IOException  - 
     * @throws RConnectionException  - 
     */
    public ScriptExecution(String aPathToR, String aScript, String aFunctionName, String aRLibPath, int aNumberOfArguments, int aNumberOfSources) throws IOException, RConnectionException 
    {
        this.process = new DirectConnection(aPathToR);
        this.rScript = new FileTools (new File(aScript));
        this.functionName = aFunctionName;   
        this.argumentList = new RArguments(aNumberOfArguments);
        this.sourcesList = new RSources(aNumberOfSources);
        this.rLibPath = new FileTools(new File(aRLibPath));
        this.pathToRScript = rScript.getFolderName();
        System.out.println(this.toString());
    }  
    
    /***
     * This method executes a R script and retrieve any arguments passed by reference.
     * @throws RConnectionException -
     * @throws IOException  -
     */
    public void executeAScript() throws RConnectionException, IOException
    {
        process = new DirectConnection(process.getPathToR());
        System.out.println("Execute R Script \n" + getRCodeToExecuteAFunction());
        process.startConnection();
        System.out.println("after start connection");
        process.evaluateRCommand(getRCodeToExecuteAFunction());
        System.out.println("after evaluate connection");
        process.endConnection();
        System.out.println("after end  connection");
        argumentList.extractRValues();  
        System.out.println("after extract values");
    }
    
    /****
     * Set some argument list
     * @param anArgumentList 
     */
    public void setArgumentList(RArguments anArgumentList)
    {
        argumentList = anArgumentList;
    }
      
    /***
     * 
     * @return the list of arguments
     */
    public RArguments getArgumentList()
    {
        return argumentList;
    }

    /***
     * 
     * @return a String value with all the R script details. 
     */
    @Override
    public String toString() 
    {
        return "ScriptExecution{" + "process=" + process + ", \nrScript=" + rScript + ", \nargumentList=" + argumentList + ", \nfunctionName=" + functionName + '}';
    }
    
    /***
     * This method builds a R code to execute a function
     * @return The R code to execute.
     */
    protected String getRCodeToExecuteAFunction() 
    {
        
        String r_code = "";
        System.out.println("Code to be executed\n"  + rLibPath);
        if (rLibPath.doesFolderExists())
        {
            r_code = ".libPaths(\"" + this.rLibPath.getAbsolutePathAndNameForR() + "\")\n";
            r_code = r_code + "setwd(\"" + this.rScript.getAbsolutePathForR() + "\")\n";
        }
        r_code = r_code + sourcesList.getAllTheSources();
        r_code = r_code + argumentList.getRCommandsToinitialiseArguments();
        
        if (argumentList.hasAFunctionReturnedVariableSet())
        {
            r_code = r_code + argumentList.getFunctionReturnedVariable() + "=" 
                                + functionName + "(" + argumentList.getFunctionArguments() + ")\n";
        }
        else
        {
            r_code = r_code + functionName + "(" + argumentList.getFunctionArguments() + ")\n";
        }
        r_code = r_code + argumentList.getRCommandToSaveRValuesIntoAFile();
        r_code = r_code + "q(\"no\")";
        System.out.println(r_code);
        return r_code;
    }

}
