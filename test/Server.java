import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final Object resourceLock = new Object();

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
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String inputLine = in.readLine();
                JSONObject jsonObject = new JSONObject(inputLine);
                System.out.println("Server received: " + jsonObject.toString());

                int type = jsonObject.getInt("type");
                JSONObject response = new JSONObject();

                synchronized (resourceLock) {
                    // Simulate the consumption of the resource for 3 seconds
                    Thread.sleep(3000);

                    if (type == 0) {
                        response.put("message", "Hello, client!");
                    } else if (type == 1) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        response.put("message", data.toString());
                    }
                }
                
                out.println(response.toString());
                clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
