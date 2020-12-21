package org.YorkUniversity.coffee2Season;


import org.YorkUniversity.coffee2Season.RConnectionException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * 
 * @author prw509
 */
public interface RConnection 
{
    
    
    /****
     * 
     * Start a new instance of R. 
     * @throws RConnectionException 
     */
    public void startConnection() throws RConnectionException;
  
    /***
     * End an instance of R running
     * @throws RConnectionException 
     */
    public void endConnection() throws RConnectionException;
    
    /***
     * evaluate an R command and extract the result. 
     * @param aRCommand- A valid R command such as a function call.
     * @throws RConnectionException 
     */
    public void evaluateRCommand(String aRCommand) throws RConnectionException;

}
