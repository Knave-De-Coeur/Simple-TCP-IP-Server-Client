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
public class Rectangle extends TwoDShapes 
{
    private double rectarea;// rectangle area
    private double rectper;// rectangle perimeter
    
    // Constructor for no args
    public Rectangle()
    {
        this.rectarea = 0.0;
        this.rectper = 0.0;
    }// End constructor Rectangle()
    /*
        Constructor takes arguments that will be set in the abstract classes it
        inherits from.
    */
    public Rectangle(String name, double rectlength, double rectheight)
    {
        super(name, 4, rectlength, rectheight);
        rectarea = 0.0;
        rectper = 0.0;
    }// End of constructor Rectangle()
    
    // Overriding getArea() in Shape class to use the formula for a rectangle
    @Override
    public double getArea()
    {
        rectarea = super.getLength()* super.getHeight();
        return rectarea;
    }// End of getArea() function
    
    // Overriding getPerimeter() in TwoDShape class to use the formula for a rectangle 
    @Override
    public double getPerimeter()
    {
        rectper = (super.getLength() + super.getHeight())*2;
        return rectper;
    } //End of getPerimeter() function
    
    // Overrides Object class to output the Object like so 
    public String toString()
    {
        return "Name: " + super.getName() + " " + "Area: " + getArea() + " " + "Perimeter: " + getPerimeter();
    }// End toString
}// End of Recangle Class
