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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import org.YorkUniversity.Coffee2StoredData.FileTools;

/**
 * This class evaluates some R codes and executes it. A given R environment is executed with some R codes. The values of some arguments passed by references are
 * saved in some csv files and decoded to communicate back to Java.  While the R code is executed then the Java program waits for the R codes to complete before resuming 
 * its activities. 
 * 
 * @author Patricia Ryser-Welch
 */
public class DirectConnection implements RConnection
{
    
    protected static String pathToR;
    protected FileTools r_script_executable;
   
    
    protected FileTools input;
    protected Process process;
    
    /****
     * Constructor
     * @param aPathToR - The path to the R environment where the Rscript executable is stored.
     * @throws IOException - Input/output error
     */
    public DirectConnection(String aPathToR) throws IOException 
    {
       DirectConnection.pathToR = aPathToR;
       this.input = new FileTools(File.createTempFile("r_input", ".r"));
    }

    /***
     * verifies the Rscript executable exists in a given folder. 
     * @throws RConnectionException  - when the Rscript executable cannot be found
     */
    public void startConnection() throws RConnectionException 
    {
        if  (System.getProperty("os.name").toLowerCase().contains("win"))
        {
            r_script_executable = new FileTools(pathToR, "Rscript.exe");
        }
        else
        {
            r_script_executable = new FileTools(pathToR, "Rscript");
        }
     
              
       System.out.println("RScript executable :  " + 
                          r_script_executable.getAbsolutePathAndName());
       
       if (!r_script_executable.doesFileExists())
       {
           System.out.println("RScript executable :  " + 
                               r_script_executable.getAbsolutePathAndName() );
            throw new RConnectionException(10);
       }
    }

    /***
     * closes the connection to the Rscript executable
     * @throws RConnectionException 
     */
    public void endConnection() throws RConnectionException 
    {
        r_script_executable = null;
        
    }
    /***
     * Evaluate an R command 
     * @param aRCommand - a valid R command 
     * @throws RConnectionException when a temporary folders and files becomes unavailable by the operating system.
     */
    public void evaluateRCommand(String aRCommand) throws RConnectionException 
    {
       
        if (!input.doesFolderExists()) 
        {
            input.createFolder();
        }
        
        try 
        {
            System.out.println("Evaluate R Command 1");
            input.writeData(aRCommand);
            System.out.println("Evaluate R Command 2");
            evaluateRScript(input);
            System.out.println("Evaluate R Command 3");
        } 
        catch (Exception e) 
        {
            throw new RConnectionException(14, e.getMessage());
        } 
    }

    /***
     * Evaluates  a  R script 
     * @param aScript           a valid R script
     * @throws RConnectionException  when the R script does not exist
     * @throws IOException - when the problem occurs when reading the R script
     * @throws InterruptedException  - when problem occurs during the execution of the script
     */
    protected void evaluateRScript(FileTools aScript) throws RConnectionException, IOException, InterruptedException 
    {
        System.out.println("DirectConnection.evaluateRScript 1");
        System.out.println(aScript.toString());
        if (aScript.doesFileExists()) 
        {
            System.out.println("DirectConnection.evaluateRScript 2");
            executeRScript();
            System.out.println("DirectConnection.evaluateRScript 3");
            process.waitFor();
            System.out.println("DirectConnection.evaluateRScript 4");
        } 
        else 
        {
            throw new RConnectionException(12);
        }
    }
 
    /**
     * This protected method starts a runtime execution of R using the Runtime.getRuntime.exec() provided by the 
     * Java architecture. A R script is passed as a parameter to the RScript executable. Any values that need to be
     * communicated back to Java are written into an output file. This file is read to retrieve these values.
     * @throws IOException when an error occurs when reading the files. 
     * @throws InterruptedException  when an error occurs during the execution of the R script
     * @throws RConnectionException   when any errors occurs 
     */
    protected void executeRScript() throws IOException, InterruptedException, RConnectionException 
    {
        BufferedInputStream outputFromR;
        byte[] dataRead = new byte[100];
        int status = 0;
        
        System.out.println("Command to execute: " + r_script_executable.getAbsolutePathAndName() + " " + input.getAbsolutePathAndName());
        
        try 
        {
            System.out.println("Command to execute: " + r_script_executable.getAbsolutePathAndName() + " " + input.getAbsolutePathAndName());
            process = Runtime.getRuntime().exec(r_script_executable.getAbsolutePathAndName() + " " + input.getAbsolutePathAndName());
            outputFromR = new BufferedInputStream(process.getInputStream());
            process.waitFor();
            status = outputFromR.read(dataRead);
            while (status != -1) 
            {
                System.out.println(new String(dataRead));
                status = outputFromR.read(dataRead);
            }
        } 
        catch (Exception e) 
        {
            throw new RConnectionException(13, e.getMessage());
        }
    }

    public String getPathToR()
    {
        return pathToR;
    } 
}
