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
public class Sphere extends ThreeDShapes 
{
    private double sphsurf;//Sphere's area
    private double sphvolume;//Sphere's volume
    
    /*
    Constructor willl take in args to calc volume and surface area
    as well as apropriating the correct args to the super classes
    */
    public Sphere(String sphname, double sphdepth, double sphrad)
    {
        super(sphname,1,1,0,sphdepth,sphrad);
        sphsurf = 0.0;
        sphvolume = 0.0;
    }//End of constructor
    
    // Constructor taking no args
    public Sphere()
    {
        sphsurf = 0.0;
        sphvolume = 0.0;
    }// End Sphere()
    
    //Overrides method for proper equation of Shpere surface area
    @Override
    public double getSurfacearea()
    {
        //Calculates surphace area
        sphsurf = (4*super.getPi()*(Math.pow(super.getRadius(),3)));
        return sphsurf;//Returns Surface Area
    }//end getSurfacearea()
    
    //Overrides method for proper equation of Shpere volume
    @Override
    public double getVolume()
    {
        //calculates volume
        sphvolume = (4*super.getPi()*(Math.pow(super.getRadius(),2)));
        return sphvolume;//returns volume 
    }//End of getVolume()
    
    // Overrides Object class to output the Object like so
    public String toString()
    {
        return "Name: " + super.getName() + " " + "Area: " + getSurfacearea() + " " + "Perimeter: " + getVolume();
    }// End toString()
    
}//End of Sphere class