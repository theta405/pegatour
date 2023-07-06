package FM_Pegatour_Server.sys;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Request {
    
    // 静态变量
    private static Request request = new Request();

    // 静态方法
    public static Request getInstance() {
        return request;
    }

    // 成员变量
    ServerSocket serverSocket;

    // 成员方法
    private Request() {
        try {
            serverSocket = new ServerSocket(8080);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleRequests() {
        try {
            IO.println("服务器已启动");
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

                String identifier = jsonObject.getString("identifier");
                JSONObject data = jsonObject.getJSONObject("data");
                JSONObject response = new JSONObject();

                CommandsList.getInstance().getCommand(identifier).exec(data, response);
                
                out.println(response.toString());
                clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
