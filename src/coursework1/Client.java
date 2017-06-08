/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author alexa
 */
public class Client  implements Serializable 
{
    private static String stroption;
    private static int option;// Stores user option input
    private Socket clientSocket;// object for client to connect 
    private String server;// Client name
    private int portnum;// Port client will connect to
    private static ObjectOutputStream shapeout;// Stream to output shapes to server
    private static ObjectInputStream shapein;// Stream to input shapes from server
    private static Scanner userin = new Scanner(System.in);// Userinput
    private static Scanner servertxt;// Receives text from serever
    private static Gson gson;// Serial and deserialization
    private static Shape ashape;// Stores created shape
    private static PrintWriter outtext;// Sends texts to server
    private static String shapeident;// Holds string of the shape type
    private static boolean shapemade;
    
    // Constructor sets the host and port
    public Client(String host, int port)
    {
        this.server = host;
        this.portnum = port;
        this.shapemade = false;
    }//end of Client Constructor
    
    // Set up and run server
    public void runClient()
    {
        try
        {
            //Open socket. Connect to server
            clientSocket = new Socket(server, portnum);
            System.out.println("Connected to: " + portnum);
            
            getStreams();// get connections 
            
            getMenu();// runs the menu  
        }// end of try
        catch(IOException e)
        {
            System.out.println("ERROR: " + e.getMessage());
        }//end catch
    }// end of runClient()
    
    private void getStreams()
    {
        try
        {
            //Open stream to output text command
            outtext = new PrintWriter(clientSocket.getOutputStream(),true);
            //Open Input stream to recieve data from server
            servertxt = new Scanner(clientSocket.getInputStream());
            //Open outputstream to send shapes
            shapeout = new ObjectOutputStream(clientSocket.getOutputStream());
            //Open input stream to recieve shapes
            shapein = new ObjectInputStream(clientSocket.getInputStream());
            //Creates gson object for serialization and deserialization
            gson = new GsonBuilder().create();
            
        }// end try
        catch(IOException ioE)
        {
            System.out.println(ioE.getMessage());
        }// end catch
    }//end of getStreams()
    
    // Opens up menu for client to work with
    private void getMenu()
    {
        //Stores main menu
        String main_menu = "Welcome! " + "\n" + "Enter Option " + "\n" + 
        "1. Exit Program" + "\t" + "2. Create 2D Shape " + "\t" + "3. Create 3D Shapes" + "\n";
        
        //Infinate loop
        while(true)
        {
            //Outputs menu
            System.out.println(main_menu);
            /* switch uses function to return validated int and 
            will determine which menu to go to
            */
            switch(checkInput())
            {
                case 1: 
                    System.exit(0);// closes program
                    break;
                case 2:
                    TwoDmenu();// Displays 2D menu and its functionality
                    break;
                case 3: 
                    ThreeDmenu();// Displays 3D menu and its functionality
                    break;
                default:
                    //When user enters a number not on the menu
                    System.out.println("Invalid entry");
                    break;
            }// end switch
        }// end of while
    }// end of getMenu()
    
    // 2D menu and its functions
    private static void TwoDmenu(){
        //Stores 2D menu
        String TwoD_menu = "2D Shapes: " + "\n" + "\n" +
                "1. Create Rectangle shape" + "\t" + "2. Create Triangle Shape" 
                + "\t" + "3. Create Circle shape" + "\n" +"\n" +
                "Options: " + "\n" +
                "4. Send Shape" + "\n" + "\n" +
                "5. Retrieve all Rectangles" + "\t" + "6. Retrieve all Triangles" + "\t" + 
                "7. Retrieve all Circles" + "\t" + "\t" + "8. Retrieve ALL Shapes" + "\n" + "\n" +
                "9. Back" + "\t" + "\t" + "10. Exit" + "\n";
        
        System.out.println(TwoD_menu);
        
        // loop until input is 9
        do
        {
            /*
            Carries out specific function to user input. In the cases where the 
            user creates a shape the variable shapeident will be set a certain string
            which will be sent to the client so the client knows what to do with the shape
            Menu will be outputted where appropriate
            Switch uses function to return validated int and will determine 
            which menu to go to
            */
            switch(checkInput())
            {
            case 1: 
                shapemade = createtwodShape(option);
                System.out.println(TwoD_menu);
                break;
            case 2:
                shapemade = createtwodShape(option);
                System.out.println(TwoD_menu);
                break;
            case 3:
                shapemade = createtwodShape(option);
                System.out.println(TwoD_menu);
                break;
            case 4:
                checkShape();
                break;
            case 5:
                System.out.println("Retrieving Rectangles..." + "\n");
                recieveShapes("Rectangle");// Tells server to send its Rectangles
                break;
            case 6:
                System.out.println("Retrieving Triangles..." + "\n");
                recieveShapes("Triangle");// Tells server to send its Triangles
                break;
            case 7:
                System.out.println("Retrieving Circles..." + "\n");
                recieveShapes("Circle");// Tells server to send its Circles
                break;
            case 8:
                System.out.println("Retrieving all the shapes" + "\n");
                recieveShapes("All Shapes");// Tells server to send all the shapes
                break;
            case 9:
                // Will make sure that every time user returns to this menu they craete a shape
                shapemade = false;
                break;
            case 10: 
                System.out.println("Now exiting...");
                //Ends program
                System.exit(0);
                break;
            default:
                // Any other option entered that isn't on the menu
                System.out.println("Invalid entry");
                break;
            }// end switch
        }//end loop
        while(option != 9);
    }
    
    // 3D menu and  its functions 
    private static void ThreeDmenu()
    {
        //Stores 3D menu
        String ThreeD_menu = "3D Shapes: " + "\n" + "\n" +
                "1. Create Sphere shape" + "\t" + "2. Create Cylinder shape" + "\n" + "\n" +
                "Options: " + "\n" + "\n" +
                "3. Send Shape" + "\n" + "\n" +
                "4. Retriev all Shperes" + "\t" + "5. Retrieve all Cylinders" + "\t" +
                "6. Retrieve ALL Shapes" + "\n" + "\n" +
                "7. Back" + "\t" +" 8. Exit" + "\n";
        
        System.out.println(ThreeD_menu);
        
        /*
        Carries out specific function to user input. In the cases where the 
        user creates a shape the variable shapeident will be set a certain string
        which will be sent to the client so the client knows what to do with the shape
        Menu will be outputted where appropriate
        */
        do
        {
            /* switch uses function to return validated int and 
            will determine which menu to go to
            */
            switch(checkInput())
            {
                case 1:
                    shapemade = createthreedShape(option);
                    System.out.println(ThreeD_menu);
                    break;
                case 2:
                    shapemade = createthreedShape(option);
                    System.out.println(ThreeD_menu);
                    break;
                case 3:
                    checkShape();
                    break;
                case 4: 
                    System.out.println("Retrieving Spheres..." + "\n");
                    recieveShapes("Sphere");// Tells the server to send its Shperes
                    break;
                case 5:
                    System.out.println("Retrieve Cylinders..." + "\n");
                    recieveShapes("Cylinder");// Tells the server to send its Cylinder
                    break;
                case 6:
                    System.out.println("Retrieving all the shapes..." + "\n");
                    recieveShapes("All Shapes");// Tells the server to send all its shapes
                    break;
                case 7:
                    // Will make sure that every time user returns to this menu they create a shape
                    shapemade = false;
                    break;
                case 8:
                    // Ends Program
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Entry" + "\n");
                    break;
            }// end switch
        }// end do
        while(option != 7);// will terminate loop and end method
    }// end ThreeDShape()
    
    // Creates loop unil valid entry is made then returns it
    private static int checkInput()
    {
        System.out.print("Enter Option: ");
        // Checks 1st if the user entered and int
        while(!userin.hasNextInt())
        {
            // Out error
            System.out.println("Invalid entry");
            // Go to next
            userin.next();
        }//end while
        // User input is stored in variable
        option = userin.nextInt();
        return option;
    }// en checkInput()
    
    // function to create 2d shapes and return true for shapemade
    private static boolean createtwodShape(int opt)
    {
        switch(opt)
        {
            case 1:
                ashape = new Rectangle("Rectangle", 4, 8);
                System.out.println(ashape);
                shapeident = "Rectangle sent"; 
                System.out.println("You created a rectangle!" + "\n");
                break;
            case 2:
                // ashape is now a triangle
                ashape = new Triangle("Triangle", 3, 4);
                shapeident = "Triangle sent";
                System.out.println("You created a Triangle!: " + "\n");
                break;
            case 3:
                // ashape is now a Cicle
                ashape = new Circle("Circle",10);
                shapeident = "Circle sent";
                System.out.println("You created a Circle!" + "\n");
                break;
        }// end switch
        return true;
    }// end create2dShape
    
    // Will create3d shapes and set shapemade to true
    private static boolean createthreedShape(int opt)
    {
        switch(opt)
        {
            case 1:
                //ashape is now a Sphere
                ashape = new Sphere("Sphere", 5, 5);
                shapeident = "Sphere sent";
                System.out.println("You created a Shpere!" + "\n");
                break;
            case 2:
                //ashape is now a Cylinder
                ashape = new Cylinder("Cylinder", 10, 20);
                shapeident = "Cylinder sent";
                System.out.println("You created a Cylinder!" + "\n");
                break;
        }
        return true;
    }
    
    //Will check to see if a shape has been made
    private static void checkShape()
    {
        //Condition to validate if a shape has been created
        if (shapemade == true)
        {
            System.out.println("Sending Shape..." + "\n");
            //Sends server validation of which shape is being sent
            outtext.println(shapeident);
            // Server acknowledges the validation
            System.out.println("SERVER>>>" + servertxt.nextLine() + "\n");
            sendShape();// Sends current ashape to server
        }
        else
        {
            System.out.println("You haven't made a shapes yet!" + "\n");
        }// end if else
    }// end checkShapes
    
    // serializes and sends shape to server
    private static void sendShape()
    {
        System.out.println("Sending Shape to server..." + "\n");
        try
        {
            //Serializes the object and saves it in a string
            String shapejson = gson.toJson(ashape);
            //shape string is sent to server
            shapeout.writeObject(shapejson);
        }catch(IOException ioException){
            System.out.println("Error wiriting Object");
        }
    }// end of sendShpe()
    
    // Receives and outputs shapes
    private static void recieveShapes(String filter)
    {
        try
        {
            // Sends the server what shapes to send back
            outtext.println(filter);
            // Server acknowledges what shapes to send back
            System.out.println("SERVER>>> " + servertxt.nextLine() + "\n");
            //saves incomming json string shapelist in variable
            String showshapes = (String) shapein.readObject();
            //Outputs the shapelist to user
            System.out.println("Here are the shapes: ");  
            System.out.println(showshapes);
        }catch(IOException ioException){
            System.out.println(ioException.getMessage());
        }catch(ClassNotFoundException cnfe){
            System.out.println(cnfe.getMessage());
        }
    }// end of receiveShape()
    
    
    public static void main(String[] args) 
    {
        //Create client object
        Client cli = new Client("127.0.0.1", 12345);
        cli.runClient();
    }// end of main()
    
}// end of Client class
