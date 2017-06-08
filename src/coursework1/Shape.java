/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework1;

import java.io.Serializable;

/**
 *
 * @author alexmifsud
 */
public class Shape implements Serializable
{
    private int noOfSides; //Number of sides of each shape
    private String name;
    
    public Shape(String name, int noOfSides) 
    {
        this.name = name;
        this.noOfSides = noOfSides; 
    }//Contructor having and setting the names of the 2 variables of the class
    
    //When object created with no args
    public Shape()
    {
        this.noOfSides = 0;
        this.name = "Shape";
    }
    
    public int getNoOfSides() 
    {
        return noOfSides; // Will return set number of sides of the shape
    }
    
    public String getName() 
    {
        return name; //Will return the name of the shape
    }
    
}//End of Shape Class

