/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server implements Serializable 
{

    private int portnum;// TCP Port Number of server
    private ServerSocket serversocket;// Object to register an available TCP port
    private Socket clientConnection;// connection to clinet
    private final File shapefile;// Creates file that will be json
    private FileWriter writer;// writes to json file
    private ObjectOutputStream outshape;// Stream to send objects to clients
    private ObjectInputStream inshape;// Stream to receive objects from clients
    private ArrayList<Shape> servershapes;// Repository for inscomming shapes
    private Gson gson;// Gson Object for serial and deserialization
    private Scanner intext;// Scanner object
    private String clientreq; // String to hold what the client asks for
    
    private PrintWriter outtext;// PrintWriter Object

    //Sets up sever serverfile and repository
    public Server(int port) 
    {
        this.portnum = port;
        //Create file to store json objects
         shapefile= new File("shaperepos.json");
         servershapes  = new ArrayList<Shape>();//Repository
    }//End of Server constructor

    // Setup and run server
    public void runServer() 
    {
        try 
        {
            //Alerts the system starts
            System.out.println("Server started");
            //Create socket to listen for client
            serversocket = new ServerSocket(portnum);
            System.out.println("Waiting for clients...");
            //Waiting for client...
            while(true)
            {
            //Returns socket when connection is established
            clientConnection = serversocket.accept();
            //outputs hosts name
            System.out.println(clientConnection.getInetAddress().getHostName() + " Connected");

            Thread clientThread = new Thread(() -> execute(clientConnection ));
            //Opens up streams

            clientThread.start();
            }

        }//End Try
        catch (IOException e) 
        {
            System.out.println("IO ERROR: " + e.getMessage());
        }//End Catch
        catch(final Throwable ex)
        {
            System.out.println("Throwable ERROR: " + ex.getMessage());
        }
    }//End of Start() Constructor

    // get streams to send and receive data
    private void execute(Socket clientConnection) 
    {
        try 
        {
            //Open input stream to get requests from clients
            intext = new Scanner(clientConnection.getInputStream());
            //Open outputstream to send to clients
            outtext = new PrintWriter(clientConnection.getOutputStream(), true);
            //Stream to retrieve shapes from clients
            inshape = new ObjectInputStream(clientConnection.getInputStream());
            //Stream to send shapes to clients
            outshape = new ObjectOutputStream(clientConnection.getOutputStream());
            // GsonBuilder class used for serial and deserialization of shapes and shapelists
            gson = new GsonBuilder().create();
            //Object will be used to write objects to the file
            writer = new FileWriter(shapefile);
            
            serverFunction();//Gets and sends data
            
        }//end of try
        catch (IOException ioException) 
        {
            // Outputs error if caught
            System.out.println(ioException.getMessage());
        }//end of catch
        catch(final Throwable ex)
        {
            System.out.println("Throwable ERROR: " + ex.getMessage());
        }
        System.out.println("Client disconnected");
    }//End of getStreams()

    // all the data transfer and management is handled here
    private void serverFunction() 
    {
        // Main loop
            while (intext.hasNext()) 
            {
                // Client sent request and is read
                clientreq = intext.nextLine();
                    //System.out.println(clientreq);
                //Response is sent to client
                outtext.println("Request Received: ' " + clientreq.toUpperCase() + " ' ");
                /* 
                 Condition checks and validates the request to carry out clients required function
                 will save the data recieved if any of these conditions is met, otherwise will send data
                 */
                if (clientreq.equals("Rectangle sent") || clientreq.equals("Triangle sent")
                        || clientreq.equals("Circle sent") || clientreq.equals("Sphere sent")
                        || clientreq.equals("Cylinder sent")) 
                {
                    saveShape(clientreq);//Saves shape from clients
                    // Outputs current number of shapes in repository
                    System.out.println("Shapes in repository: " + servershapes.size());
                } else 
                {
                    sendShapetoClient(clientreq);//Sends Shapes to clients
                    System.out.println("Sending...");
                }//End If else

                /* At the end of every loop the contents of the ArrayList is 
                 serialized and saved to file
                 */
                gson.toJson(servershapes, writer);
            }//End while do
        
    }// End of serverFunction

    // Saves shapes 
    private void saveShape(String sent) 
    {
        String tempstr;
        // Temp Shape object to be used to hold new shape recieved
        Shape rcvdshape = null;
        
        try {
            System.out.println("Adding shape to repository...");
            // String is created to temp hold received json shape
            tempstr = (String) inshape.readObject();
            System.out.println("Shape recieved: " + tempstr);

            /* Condition validates which shape is being sent to deserialize it 
             into the appropriate shape object
             */
            if (sent.equals("Rectangle sent")) 
            {
                rcvdshape = gson.fromJson(tempstr, Rectangle.class);
            } 
            else if (sent.equals("Triangle sent")) 
            {
                rcvdshape = gson.fromJson(tempstr, Triangle.class);
            } 
            else if (sent.equals("Circle sent")) 
            {
                rcvdshape = gson.fromJson(tempstr, Circle.class);
            } 
            else if (sent.equals("Sphere sent")) 
            {
                rcvdshape = gson.fromJson(tempstr, Sphere.class);
            } 
            else if (sent.equals("Cylinder sent")) 
            {
                rcvdshape = gson.fromJson(tempstr, Cylinder.class);
            }//end of if else

            System.out.println("Shape converted to: " + rcvdshape);
            // Once the Shape has been deserialized it is added to the repository
            servershapes.add(rcvdshape);
            
            System.out.println("Shape: " + servershapes.get(servershapes.size()-1).getName() + " Saved in Repository");
            // Once the Shape is added the value of repoitory shapes is incremented
        }//end of try
        //Outputs exceptions
        catch (IOException ioe) 
        {
            System.out.println(ioe.getMessage());
        } 
        catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe.getMessage());
        }
    }//end of saveShape()

    // Sends shapes filtered or otherwise
    private void sendShapetoClient(String type) 
    {
        // Creates temperary string for json format 
        String sendclient;
        // Creates temperary arraylist to hold filtered list
        ArrayList<Shape> shapestoclient = new ArrayList<Shape>();

        try 
        {
            //Condition will validate the command to see weather to send full or filtered repository
            if (type.equals("All Shapes") && servershapes.size() >= 1) 
            {
                sendclient = gson.toJson(servershapes);
                // Json string is sent to clients
                outshape.writeObject(sendclient);
                //Informs client that shape has been sent
                outshape.writeObject("Sent shapes");
                //System.out.println("Sent: " + sendclient);
            } 
            else if(type.equals("All Shapes") && servershapes.size() == 0)
            {
                outshape.writeObject("No shapes were made yet!");
            }
            else 
            {
                //Loop goes through the repository to see which shape matches the filter
                for (Shape shape : servershapes) 
                {
                    if (shape.getName().equals(type)) 
                    {
                        // Will add the shape to the the temp repos
                        shapestoclient.add(shape);
                    }
                }
                // Will check that there is at least 1 object in the list
                if(shapestoclient.size() >= 1)
                {
                //Serializes filtered list
                sendclient = gson.toJson(shapestoclient);
                // Json string is sent to clients
                outshape.writeObject(sendclient);
                //Informs client that shape has been sent
                outshape.writeObject("Sent shapes");
                //System.out.println("Sent: " + sendclient);
                }
                else
                {
                    outshape.writeObject("No shapes of this type were created");
                }// end of ifelse
            }// end of ifelse
        } 
        catch (IOException ioE) 
        {
            System.out.println(ioE.getMessage());
        }
    }

    // when class is called will run server
    public static void main(String[] args) 
    {
        final Server server = new Server(12345);
        server.runServer();
    }//End of Main
}//End of Server Class
