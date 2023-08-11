package auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUtil;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/Register"})
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        DBUtil.connect();
        writer.println("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Form Pasien</title>\n" +
                "    <link rel=\"stylesheet\" href=\"login2.css\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"Register\" method=\"post\">\n" +
                "\t<div class=\"login\">\n" +
                "\t\t<div class=\"login-screen\">\n" +
                "\t\t\t<div class=\"app-title\">\n" +
                "\t\t\t\t<h1>Login</h1>\n" +
                "\t\t\t</div>\n" +
                "\n" +
                "\t\t\t<div class=\"login-form\">\n" +
                "\t\t\t\t<div class=\"control-group\">\n" +
                "\t\t\t\t<input type=\"text\" class=\"login-field\" value=\"\" placeholder=\"username\" name=\"email\">\n" +
                "\t\t\t\t<label class=\"login-field-icon fui-user\" for=\"login-name\"></label>\n" +
                "\t\t\t\t</div>\n" +
                "\n" +
                "\t\t\t\t<div class=\"control-group\">\n" +
                "\t\t\t\t<input type=\"password\" class=\"login-field\" value=\"\" placeholder=\"password\" name=\"password\">\n" +
                "\t\t\t\t<label class=\"login-field-icon fui-lock\" for=\"login-pass\"></label>\n" +
                "\t\t\t\t</div>\n" +
                "\n" +
                "\t\t\t\t<div class=\"control-group\">\n" +
                "\t\t\t\t<input type=\"password\" class=\"login-field\" value=\"\" placeholder=\"konfirmasi\" name=\"konfirmasi\">\n" +
                "\t\t\t\t<label class=\"login-field-icon fui-lock\" for=\"login-pass\"></label>\n" +
                "\t\t\t\t</div>\n" +
                "\n" +
                "\t\t\t\t<input type=\"submit\" class=\"btn btn-primary btn-large btn-block\" value=\"Simpan\">\n" +
                "\t\t\t</div>\n" +
                "\t\t</div>\n" +
                "\t</div>\n" +
                "</body>\n" +
                "</html>");
//        writer.println("<body>");
//        writer.println("<pre>");
//        writer.println("<h4>Regristrasi User</h4>");
//        writer.println("<form action=\"Register\" method=\"post\">");
//        writer.println("Email:       <input type=\"text\" size=\"25\" name=\"email\"/><br/>");
//        writer.println("Password:    <input type=\"text\" size=\"25\" name=\"password\"/><br/>");
//        writer.println("Konfirmasi:  <input type=\"text\" size=\"25\" name=\"konfirmasi\"/><br/>");
//        writer.println("<input type=\"submit\" value=\"Simpan\"/>");
//        writer.println("</form>");
//        writer.println("</pre>");
//        writer.println("</body>");
//        writer.println("</html>");

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String stringEmail = req.getParameter("email");
        System.out.println("Email = " + stringEmail);
        String stringPassword = req.getParameter("password");
        System.out.println("Password = " + stringPassword);
        String stringKonfirmasi = req.getParameter("konfirmasi");
        System.out.println("Konfirmasi = " + stringKonfirmasi);
        PrintWriter writer = resp.getWriter();
        writer.println("<html><body>");
        boolean isValid1 = false;
        if(stringEmail.isEmpty()||stringPassword.isEmpty()||stringKonfirmasi.isEmpty()){
            isValid1=false;
            writer.println(" Email/Password/konfirmasi Harus di Isi Semua !<br/>");
        }else {
            isValid1 = true;
        }
        boolean isValid2 = false;
        if(!stringPassword.equals(stringKonfirmasi)){
            isValid2=false;
            writer.println("Password/Konfirmasi tidak sama !<br/>");
        }else {
            isValid2=true;
        }
        if (isValid1&&isValid2){
            if (DBUtil.insertRegist("users",stringEmail, stringPassword)){
                writer.println("Daftar akun " + stringEmail + " Berhasil!<br/>");
            } else {
                writer.println("Daftar akun " + stringEmail + " Gagal!<br/>");
            }
        }
        writer.println("<h4><a href=\"Main\">Silakan Akses ke Menu Utama</a></h4>");
        writer.println("</html></body>");
        writer.flush();
    }
}