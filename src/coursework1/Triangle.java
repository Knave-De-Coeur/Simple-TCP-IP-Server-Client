/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework1;

/**
 *
 * @author alexa
 */
public class Triangle extends TwoDShapes 
{
    private double triarea;//Triangle area
    private double triper;//Triangle Perimeter
    /*
    Constructor to simply take in 3 args
    */
    public Triangle(String triname, double trilength, double triheight)
    {
        super(triname, 3, trilength, triheight);
        triarea = 0.0;
        triper = 0.0;
    }//End Constructor
    
    // Constructor for no args
    public Triangle()
    {
        triarea = 0.0;
        triper = 0.0;
    }// End Trinagle()
    
    // Overides getArea() method in super class Shape to get triangle area
    @Override
    public double getArea()
    {
        triarea = (super.getLength()* super.getHeight())/2;
        return triarea;
    }// End getArea()
    
    //Overrides getPerimeter() method in super class TwoDShape to get Triangle perimeter
    @Override
    public double getPerimeter()
    {
        triper = super.getLength()*super.getNoOfSides();
        return triper;
    }// End getPerimeter()
    
    //Overrides Object class to output the Object like so 
    public String toString()
    {
        return "Name: " + super.getName() + " " + "Area: " + getArea() + " " + "Perimeter: " + getPerimeter();
    }//End toString()
}//End of Triangle Class()
