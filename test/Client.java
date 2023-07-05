import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            JSONObject request = new JSONObject();
            JSONObject data = new JSONObject();
            request.put("type", "1");
            data.put("message","Hello Server");
            request.put("data",data);

            out.println(request.toString());

            String responseLine = in.readLine();
            JSONObject response = new JSONObject(responseLine);
            System.out.println("Client received: " + response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
