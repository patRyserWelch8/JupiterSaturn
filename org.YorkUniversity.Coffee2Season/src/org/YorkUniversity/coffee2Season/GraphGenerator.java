/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.YorkUniversity.coffee2Season;

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
