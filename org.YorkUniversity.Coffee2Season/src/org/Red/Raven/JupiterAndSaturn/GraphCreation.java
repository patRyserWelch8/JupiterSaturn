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

/**
 * This class generates some graphs in R and then displays them into a graphical user interface.
 * @author Patricia Ryser-Welch
 */
public class GraphCreation extends ScriptExecution
{
    private String graphicFormat;
    public final static String PDF = ".pdf";
    public final static String TIFF = ".tiff";
    public final static String JPEG = ".jpg";
    public final static String POSTSCRIPT = ".ps";

    /****
     * constructor 
     * @param aPathToR  - The path to the R environment
     * @param aScript  -  The absolute path to a R script
     * @param aFunctionName - The name of a R function to execute. The function must be defined in the R script.
     * @param aRLibPath   - The path to R libraries. 
     * @param aNumberOfArguments - The number of R arguments 
     * @param aNumberOfSources - The number of R sources required to run the R script
     ** @param aGraphicFormat  - The preferred graphic format 
     * @throws IOException when IO errors occurs
     * @throws RConnectionException  when a problem occurs with the R execution
     */
    public GraphCreation(String aPathToR, String aScript, String aFunctionName, String aRLibPath, int aNumberOfArguments, int aNumberOfSources, String aGraphicFormat) throws IOException, RConnectionException 
    {
        super(aPathToR, aScript, aFunctionName, aRLibPath, aNumberOfArguments, aNumberOfSources);// ADD FUNCTION NAME;
     //   output = new FileTools(File.createTempFile("Graph", aGraphicFormat));
        this.graphicFormat = aGraphicFormat;
    }
    
    /**
     * This method executes a R script and retrieve any arguments passed by reference.
     * @param aFunctionName
     * @throws RConnectionException
     * @throws IOException
     */
    @Override
    public void executeAScript() throws RConnectionException, IOException 
    {
        process.startConnection();
        System.out.println(getRCodeToDrawAPlot());
        process.evaluateRCommand(getRCodeToDrawAPlot());
        process.endConnection();
    }

  
    
    protected String getRCodeToDrawAPlot() 
    {
        String r_code = "";
     //   r_code = r_code + "source(\"" + process.getR_script().getAbsolutePathAndNameForR() + "\")\n";
      //  r_code = r_code + defineRDevice();
        r_code = r_code + argumentList.getRCommandsToinitialiseArguments();
        r_code = r_code + argumentList.getFunctionReturnedVariable() + "=" + functionName + "(" + argumentList.getFunctionArguments() + ")\n";
        r_code = r_code + "dev.off(); \n";
        r_code = r_code + argumentList.getRCommandToSaveRValuesIntoAFile();
      
        return r_code;
    }

   
  
    
    protected void generateGraph() throws RConnectionException 
    {
        System.out.println(getRCodeToDrawAPlot());
        process.evaluateRCommand(getRCodeToDrawAPlot());
    }
    
  
    
}
    


 
      
       
     