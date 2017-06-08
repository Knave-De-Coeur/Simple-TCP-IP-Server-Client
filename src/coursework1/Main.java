/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework1;


public class Main 
{
    //default etxt to output when theres no args
    final static String usrmsg = "--server <port>\n--client <host> <port>";
    public static void main(String[] args) 
    {
        //Checks if there are no args
        if(args.length == 0)
        {
            //Outouts what the user did wrong
            System.out.println("Invalid arguments provided. Arguments: ");
            System.out.println(usrmsg);
            return;
        }//End if
        //Checks if there are 2 args and one is server
        if(args[0].equals("--server")&& args.length == 2)
        {
            //sets last arg as port number in server object
            final Server server = new Server(Integer.parseInt(args[1]));
            server.runServer();
        }
        //Checks if there are three args and one is client
        else if(args[0].equals("--client")&& args.length == 3)
        {
            //sets 2nd arg as hostname and 3rd as port number
            final Client client = new Client(args[1],Integer.parseInt(args[2]));
            client.runClient();
        }
        else
        {
            // if none of the other conditions are met will output
            System.out.println("Invalid arguments provided. Arguments: ");
            System.out.println(usrmsg);
        }//if else
    }// end main()
}//End Main class
