package FM_Pegatour_Client.sys;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Request {

    // 成员变量
    Socket socket;
    PrintWriter out;
    BufferedReader in;

    public JSONObject send(String identifier, JSONObject data, boolean quiet) {
        if (!quiet) {
            IO.println("正在发送请求...");
        }
        try (
            Socket socket = new Socket("localhost", 33060);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
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