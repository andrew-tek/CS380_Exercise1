import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public final class EchoServer {
//private static volatile Socket socket;
    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(22222)) {
            while (true) {
                try ( Socket socket = serverSocket.accept()) {
                	
                	Runnable client = () -> {
                	try {
                	System.out.println("HERE");
                    String address = socket.getInetAddress().getHostAddress();
                	System.out.println("HERE1");
                    System.out.printf("Client connected: %s%n", address);
                    OutputStream os = socket.getOutputStream();
                	System.out.println("HERE2");
                    PrintStream out = new PrintStream(os, false, "UTF-8");
                    out.printf("Hi %s, thanks for connecting!%n", address);
                    String inputLine = "";
                	System.out.println("HERE3");
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    while ((inputLine = in.readLine()) != null) {
                    	System.out.println(inputLine);
                    	out.println("Server> " + inputLine);
                    }
                	System.out.println("HERE4");
                    System.out.printf("Client disconnected: %s%n", address); 
                	}
                	catch (Exception e) {
                		System.out.println("Exception: " + e);
                	}
                	};
                	Thread clientThread = new Thread (client);
                	clientThread.start();
            
                }
            }
        }
    }
}