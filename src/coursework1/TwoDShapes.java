/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework1;

/**
 *
 * @author alexmifsud
 */
public abstract class TwoDShapes extends Shape 
{
    // Sum of interior angles is always 360 degrees in any 2Dshape
    private final double tot_angle = 360.0; 
    private double xlength;// Length of 2Dshape
    private double yheight;// Height of 2Dshape
    
    /*
    Constructor taking in 4 args and will use them to set the instance variables 
    in this and its super class
    */
    public TwoDShapes(String twod_name, int sidenum, double xlength, double yheight)
    {
        super(twod_name,sidenum);
        // 1st two args when called will go to superclass to set varaibles there
        this.xlength = xlength;
        this.yheight = yheight;
    }// End of TwoDShapes constructor
    
    // Constructor taking no args sets height and length to 0
    public TwoDShapes()
    {
        this.xlength = 0.0;
        this.yheight = 0.0;
    }// End TwoDShapes()
    
    public double getLength() 
    {
        return xlength; // Returns shapes length
    }// End of getLength method
    
    public double getHeight()
    {
        return yheight;//returns shapes height
    }// End of getHeight method
    
    public double getTotalangle()
    {
        return tot_angle;//returns shapes angle
    }// End of getTotalangle method
    
    // Abstract method that will be used to calculate the subclass' perimeter
    public abstract double getArea();
    
    // Abstract that will be used to calculate the subclass' area
    public abstract double getPerimeter();
    
}// End of TwoDShape Class