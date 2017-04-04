import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public final class EchoClient {

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 22222)) {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            System.out.println(br.readLine());
            Scanner sc = new Scanner (System.in);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            System.out.print("Client> ");
            String userInput = sc.nextLine();
            while(!userInput.equals("exit")) {
            	out.println(userInput);
            	System.out.println(br.readLine());
            	System.out.print("Client> ");
            	userInput = sc.nextLine();
            }
            socket.close();
            //if exit is entered them program will end and 'disconnect'
            
        }
    }
}