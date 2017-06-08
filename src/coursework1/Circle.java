/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework1;

import java.io.Serializable;

/**
 *
 * @author alexa
 */
public class Circle extends TwoDShapes 
{
    private double circarea;// Circle's area
    private double circumference;// Circle's volume
    private double radius;// Circle's raduis
    // For the purpose of this coursework, assigned as an instance variable here
    private final double pi = 3.14159;// Universal constant
    
    /*
    Construtor to take args to calculate circle's area and circumference
    also appropriate args 
    */
    public Circle(String circname, double diameter)
    {
        /*Diameter is set to both TwoDShape's length and 
          height since they ar one in the same in a perfect circle
        */
        super(circname, 0, diameter, diameter);
        radius = diameter/2;//Simple calc to asign proper raduis based on the args
        circarea = 0.0;
        circumference = 0.0;
        
    }//End of Circle Constructor
    
    // Constructor taking no args
    public Circle()
    {
        radius = 0.0;
        circarea = 0.0;
        circumference = 0.0;
    }// End Circle()
    
    //Overrides TwoDShapes method to get proper equation and value of Circle's area
    @Override
    public double getArea()
    {
        circarea = pi*(Math.pow(pi, 2));//Calc circle area
        return circarea;//Returns Circle area
    }//End of getArea()
    
    
    //Overrides TwoDShapes method to get proper equation and value of Cricle's cirumference
    @Override
    public double getPerimeter()
    {
        circumference = pi*super.getLength();//Calc circumference of circle
        return circumference;//Returns circles circumeference
    }//End of getPerimeter
    
    //Overrides Object class to output the Object like so 
    public String toString()
    {
        return "Name: " + super.getName() + " " + "Area: " + getArea() + " " + "Perimeter: " + getPerimeter();
    }//End toString
    
}//End of Circles Class
