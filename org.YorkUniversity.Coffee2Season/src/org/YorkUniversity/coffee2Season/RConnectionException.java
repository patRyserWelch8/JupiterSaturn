package org.YorkUniversity.coffee2Season;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author prw509
 */
public class RConnectionException extends Exception
{
    private int error;
    private String other_error;
    
    public RConnectionException(int anError)
    {
        this.error = anError;
    }
    
    public RConnectionException (int anError, String AnotherMessage)
    {
        this.error = anError;
        this.other_error = AnotherMessage;
    }
    
    public String getMessage()
    {
       
        switch(error)
        {
            case  1: return "Rengine: The API version of the Java code and the native library do not match. ";
   
            case  2: return "Rengine: An instance of R does not exist. Use the the method start connection before evaluating a R command.";
                   
            case  3: return "Rengine: No result has yet to be obtained. A R command that returns any values needs to executed first with the EvaluateRCommand method";
                                     
            case  4: return "Rengine: No result has yet to be obtained. A R command that returns a decimal value needs to executed first with the EvaluateRCommand method";
                                
            case  5: return "Rengine: No result has yet to be obtained. A R command that returns an integer value needs to executed first with the EvaluateRCommand method";
                               
            case  6: return "Rengine: No result has yet to be obtained. A R command that returns a boolean value needs to executed first with the EvaluateRCommand method";
                                  
            case  7: return "Rengine: No result has yet to be obtained. A R command that returns an array of integer values needs to executed first with the EvaluateRCommand method";
                                   
            case  8: return "Rengine: No result has yet to be obtained. A R command that returns an array of decimal values needs to executed first with the EvaluateRCommand method";
                                   
            case  9: return "Rengine: No result has yet to be obtained. A R command that returns a matrix of decimal values needs to executed first with the EvaluateRCommand method";
            case 10: return "Direct: The Rscript has not been installed on your computer. Without this part of the software will not work.";
            case 11: return "Direct: The folder where the R scripts are written does not exists.";
            case 12: return "Direct: A script cannot be found. Please check you have not misspelled it or save it in another folder.";
            case 13: return "Direct: RScript was unable to run. " + other_error;
            case 14: return "Direct: A script has experienced some problems. Have you started a connection to R?" + other_error;
            case 15: return "Direct: A variable has yet to included in the arguments list. Verify your code adds this variable before extracting its value.";
            default: return "an unknown error was thrown... Ooops";
        }
        
        
        
    }
}
