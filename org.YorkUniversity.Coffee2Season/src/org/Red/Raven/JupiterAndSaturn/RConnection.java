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


import org.Red.Raven.JupiterAndSaturn.RConnectionException;

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
