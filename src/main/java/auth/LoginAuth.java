package auth;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/LoginAuth"})
public class LoginAuth extends HttpServlet {

    public static boolean isValid = false;

       public static void unauthorizedAcess(HttpServletResponse resp) throws IOException {
       PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("Invalid Acess<br/>");
        writer.println("<h4><a href=\"index.jsp\">Kembali ke login</a></h4>");
        writer.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Koneksi Ke DB cukup sekali di login saja
        DBUtil.connect();

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String passwordMD5 = MD5.getMd5(password);

        ResultSet resultSet = null;
        PrintWriter writer = resp.getWriter();
        writer.println("<html><body>");
        try {
            resultSet = DBUtil.selectUser(email, passwordMD5);
            if (resultSet.next()){
                isValid = true;
//                    writer.println("User " + email + " valid");
//                writer.println("<h4><a href=\"Main\">Silahkan Akses Menu Utama");
                resp.sendRedirect("Main");
            }else {
                isValid = false;
                writer.println("<h2>Email/Password Invalid</h2>");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        writer.println("<h3><a href=\"index.jsp\">Kembali Ke Login");
        writer.println("</html></body>");
        writer.flush();
    }
}

