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
public abstract class ThreeDShapes extends Shape
{
    private int edges;// where 2 faces meet
    private int vertexs;// point of contact of three edges
    private double depth;// how deep the shape is 
    private double radius;// distance from center
    private final double pi = 3.14159;// Universal constant
    //For the purpose of the courswork pi is declared in this class
    
    /*
        Constructer will take arguments for this and the super class
    */
    public ThreeDShapes(String threedname, int sidenum, int edge, int vert, 
            double dep, double rad)
    {
        super(threedname,sidenum);
        edges = edge;
        vertexs = vert;
        depth = dep;
        radius = rad;
    }// End constructor
    
    public ThreeDShapes()
    {
        edges = 0;
        vertexs = 0;
        depth = 0.0;
        radius = 0.0;
    }// End ThreeDShapes()
    
    public int getEdges()
    {
        return edges; // will return value of edges
    }// End getEdges()
    
    public int getVertexs()
    {
        return vertexs; // will return value of Vertexes
    }// End getVertexes()
    
    public double getDepth()
    {
        return depth; // will return value of depth
    }// End getDepth()
    
    public double getRadius()
    {
        return radius;// will return value of radius
    }// End getRadius
    
    public double getPi()
    {
        return pi;// will retut=rn value of Pi
    }// End getPi()
    
    // Will  be overriden for correct equation and value of shapes' surphace area
    public abstract double getSurfacearea();
    
    // Will be overriden for correct equation and value of shapes' Volume
    public abstract double getVolume(); 
    
}// End of ThreeDShape Class