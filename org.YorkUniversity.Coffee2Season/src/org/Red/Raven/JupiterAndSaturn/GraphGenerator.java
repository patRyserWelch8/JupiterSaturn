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

import java.io.IOException;
import javax.swing.ImageIcon;

/**
 * @deprecated 
 * @author prw509
 */
public class GraphGenerator 
{
    private ImageIcon[] generatedGraphs; // no
    
   /****
    * 
    * @param aPathToRscript
    * @param aFunctionName
    * @param aScript
    * @throws IOException 
    */
    public GraphGenerator(String aPathToR, String aFunctionName, String aScript) throws IOException, RConnectionException 
    {
      //  this.process = new DirectConnection(aPathToR,aScript);
      //  this.functionName = new String[1];
      //  this.functionName[0] = aFunctionName;
     //   this.generatedGraphs = new ImageIcon[this.functionName.length];
    }
    
    public GraphGenerator(String aPathToR, String[] someFunctionNames, String aScript) throws IOException, RConnectionException
    {
       // this.process = new DirectConnection(aPathToR,aScript);
       // this.functionName = someFunctionNames;
       // this.generatedGraphs = new ImageIcon[this.functionName.length];
    }
    
    
 /*   
    public void GenerateSeveralGraphs() throws RConnectionException, IOException
    {
        process.startConnection();
        for (int i = 0; i < functionName.length; i++)
        {
            generateAGraph(i);
        }
        process.endConnection();
    }


    public void paint(Graphics g)
    {
        super.paint(g);
        
        System.out.println(generatedGraphs.length);
        
        for (int i = 0; i < generatedGraphs.length; i++)
        {
            g.drawImage(generatedGraphs[i].getImage(), 600 * (i), 680 * (i), rootPane);
        }
       
    }
   
    public void showGraph() 
    {
        int i = 1;
        for (ImageIcon generatedGraph: generatedGraphs)
        {
            super.setSize(generatedGraph.getIconWidth() + (20), generatedGraph.getIconHeight() + (20 ));
            
            i++;
        }
        
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
        super.repaint(); 
        
       // }
    }
    */
}
