package FM_Pegatour_Client.sys;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Request {

    // 静态变量
    private static Request request = new Request();

    // 成员变量
    Socket socket;
    PrintWriter out;
    BufferedReader in;

    private Request() {
        try {
            socket = new Socket("localhost", 8080);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Request getInstance() {
        return request;
    }

    public JSONObject send(String identifier, JSONObject data) {
        try {
            JSONObject request = new JSONObject();
            request.put("identifier", identifier);
            request.put("data",data);

            out.println(request.toString());

            String responseLine = in.readLine();
            return new JSONObject(responseLine);
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("status", 1);
            error.put("message", "请求失败");
            return error;
        }
    }
}