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

import java.util.ArrayList;
import java.util.List;
import org.YorkUniversity.Coffee2StoredData.FileTools;

/**
 * This class manages the sources of a R script. 
 * @author Patricia Ryser-Welch
 */
public class RSources 
{
    private List<FileTools> listOfSources;

    /***
     * constructor
     * @param aNumberOfResources  - the maximum number of sources used by a R script 
     */
    public RSources(int aNumberOfResources) 
    {
        this.listOfSources = new ArrayList<>(aNumberOfResources);
    }
    
    /***
     * This method add an existing R script as a source. 
     * @param aSource  - The R script as an instantiation of FileTools
     */
    public void addASource(FileTools aSource)
    {
        if (aSource.doesFileExists())
        {
            listOfSources.add(aSource);
        }
    }
    
    /***
     * deletes an existing source from the sources list.
     * @param anIndex - an existing source
     */
    public void deleteASource(int anIndex)
    {
        if (anIndex < listOfSources.size())
        {
           listOfSources.remove(anIndex);
        }
    }
    
    /***
     * This method returns a source in R format. Those can be interpreted by R as sources of a script.
     * @param anIndex
     * @return a R-code for a given source.
     */
    public String getASource(int anIndex)
    {
        String result = "";
        if (anIndex < listOfSources.size())
        {
           result = result + "source(\"" + listOfSources.get(anIndex).getAbsolutePathAndNameForR() + "\")\n";
        }
        return result;
        
    }
            
    /***
     * This method write the R code for all the source of a R script. Each source become a valid R keyword "source()".
     * @return a R-code for all the sources
     */
    public String getAllTheSources()
    {
        String result = "";
        for (int i = 0; i < listOfSources.size(); i++)
        {
            result = result + getASource(i);
        }
        return result;
    }
    
  
    /**
     * Get the value of the list of sources
     *
     * @return the value of listOfSources
     */
    public List<FileTools> getListOfSources() 
    {
        return listOfSources;
    }

    
}
