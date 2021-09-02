import java.net.*; 
import java.io.*; 
import java.util.Random;


public class AdivinhaServer extends Thread
{ 
 protected Socket clientSocket;
 private int num;
 private Random rng;

 public static void main(String[] args) throws IOException 
   { 
    ServerSocket serverSocket = null; 

    try { 
         serverSocket = new ServerSocket(5003); 
         System.out.println ("Connection Socket Created");
         try { 
              while (true)
                 {
                  System.out.println ("Waiting for Connection");
                  new AdivinhaServer (serverSocket.accept()); 
                 }
             } 
         catch (IOException e) 
             { 
              System.err.println("Accept failed."); 
              System.exit(1); 
             } 
        } 
    catch (IOException e) 
        { 
         System.err.println("Could not listen on port: 10008."); 
         System.exit(1); 
        } 
    finally
        {
         try {
              serverSocket.close(); 
             }
         catch (IOException e)
             { 
              System.err.println("Could not close port: 10008."); 
              System.exit(1); 
             } 
        }
   }

 private AdivinhaServer (Socket clientSoc)
   {
    clientSocket = clientSoc;
    rng = new Random();
    num  = rng.nextInt(101);
    //System.out.println(num);
    start();
   }

 public void run()
   {
    System.out.println ("New Communication Thread Started");

    int n;

    try { 
         PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), 
                                      true); 
         BufferedReader in = new BufferedReader( 
                 new InputStreamReader( clientSocket.getInputStream())); 

         String inputLine; 

        while ((inputLine = in.readLine()) != null) {
          n = Integer.parseInt(inputLine);
             if (n == num) {
                out.println("=");
                num = rng.nextInt(101);
                    //System.out.println(num);

            } else
                if (num > n)
                out.println (">");
            else
                out.println ("<");
        }
           

         out.close(); 
         in.close(); 
         clientSocket.close(); 
			
        } 
    catch (IOException e) 
        { 
         System.err.println("Problem with Communication Server");
         System.exit(1); 
        } 
    }

} 
