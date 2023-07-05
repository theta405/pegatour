import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloWorldServlet extends HttpServlet {
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
      response.setContentType("text/html");
      RequestDispatcher dispatcher = request.getRequestDispatcher("/src/index.html");
      dispatcher.forward(request, response);
   }

   @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求数据
        String param1 = req.getParameter("param1");
        String param2 = req.getParameter("param2");

        // 对请求数据进行处理
        // ...

        // 返回数据
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("Processed data");
    }
}