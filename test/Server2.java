import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server started...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            System.out.println("New thread started...");
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                
                Thread.sleep(3000);
                String inputLine = in.readLine();
                JSONObject jsonObject = new JSONObject(inputLine);
                System.out.println("Server received: " + jsonObject.toString());

                JSONObject response = new JSONObject();
                response.put("message", "Hello, client!");
                out.println(response.toString());

                clientSocket.close();
                System.out.println("Thread closed...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
