import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server started...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    String inputLine = in.readLine();
                    JSONObject jsonObject = new JSONObject(inputLine);
                    System.out.println("Server received: " + jsonObject.toString());

                    JSONObject response = new JSONObject();
                    response.put("message", "Hello, client!");
                    out.println(response.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
