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
public class Cylinder extends ThreeDShapes 
{
    private double cylsurfarea;//Cylinder's surface area
    private double cylvolume;//Cylinder's volume
    
    /*
    Constructor to take in args to calculate surface area and volume of a cylinder
    and appropriate the variables to the super classes
    */
    public Cylinder(String cylname, double cyldepth, double cylradius)
    {
        super(cylname, 3, 2, 0, cyldepth, cylradius);
        cylsurfarea = 0.0;
        cylsurfarea = 0.0;
    }//End of Cylinder constructor
    
    //Overides method for appropriate calc and value of Cylinder Surface area
    @Override
    public double getSurfacearea()
    {
        cylsurfarea = (2*super.getPi()*super.getRadius()*super.getDepth())+
        (2*super.getPi()*(super.getRadius()*super.getRadius()));//Calculates surface area 
        return cylsurfarea;//Returns surface area
    }//End of getSurfaceArea()
    
    
    //Overrides method for appropriate calc and value of Cylinder Volume
    @Override
    public double getVolume()
    {
        //Calculates volume of cylinder
        cylvolume = super.getPi()*(Math.pow(super.getRadius(),2)*super.getDepth());
        return cylvolume; //returns value of cylinder volume
    }//End of getVolume()
    
    // Overrides Object class to output the Object like so
    public String toString()
    {
        return "Name: " + super.getName() + " " + "Area: " + getSurfacearea() + " " + "Perimeter: " + getVolume();
    }// End toString()
    
}//End of Cylinder Class

