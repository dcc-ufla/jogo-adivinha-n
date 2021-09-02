import java.io.*;
import java.net.*;

public class AdivinhaClient {
    public static void main(String[] args) throws IOException {

        String serverHostname = new String ("localhost");

              //System.out.println ("Attemping to connect to host " +
           //     serverHostname + " on port 10008.");

        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            echoSocket = new Socket(serverHostname, 5003);
            System.out.println("Conexão estabelecida. Tente acertar o número.");
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                                        echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + serverHostname);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                               + "the connection to: " + serverHostname);
            System.exit(1);
        }

	      BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	      String userInput;

        //System.out.println ("Type Message (\"Bye.\" to quit)");
          String response;
	      while ((userInput = stdIn.readLine()) != null) {
	          out.println(userInput);
		      response = in.readLine();
                if (response.equals("="))
                    System.out.println("Acertou! jogo reiniciado");
                else
                    if (response.equals("<"))
                        System.out.println("Errou! tente um número menor");
                    else
                        System.out.println("Errou! tente um número maior"); 
	      }

	      out.close();
	      in.close();
	      stdIn.close();
	      echoSocket.close();
    }
}

