package menu;

import auth.LoginAuth;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUtil;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/InsertDataset"})
public class InsertDataset extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Input/Update Data Pasien</title>\n" +
                "    <link rel=\"stylesheet\" href=\"edit.css\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"InsertDataset\" method=\"post\">\n" +
                "<form>\n" +
                "    <label for=\"id_pasien\">Id Pasien:</label>\n" +
                "    <input type=\"text\" size=\"2\" name=\"id_pasien\"/><br/>\n" +
                "    <label for=\"age\">Age:</label>\n" +
                "    <input type=\"text\" size=\"2\" name=\"age\"/><br/>\n" +
                "    <label for=\"sex\">Sex:</label>\n" +
                "    <input type=\"text\" size=\"2\" name=\"sex\"/><br/>\n" +
//                "    <label for=\"age\">Age:</label>\n" +
//                "    <input type=\"text\" size=\"2\" name=\"age\"/><br/>\n" +
                "    <label for=\"bp\">BP:</label>\n" +
                "     <input type=\"text\" size=\"8\" name=\"bp\"/><br/>\n"+
                "    <label for=\"cholesterol\">Cholesterol:</label>\n" +
                "    <input type=\"text\" size=\"8\" name=\"cholesterol\"/><br/>\n" +
                "    <label for=\"na_to_k\">Na_to_k:</label>\n" +
                "     <input type=\"text\" size=\"8\" name=\"na_to_k\"/><br/>\n"+
                "    <button type=\"submit\" id=\"update-btn\">Simpan</button>\n" +
                "  </form>\n" +
                "</body>\n" +
                "</html>");
//        writer.println("<body>");
//        writer.println("<pre>");
//        writer.println("<h4>Input Data Pasien</h4>");
//        writer.println("<form action=\"InsertDataset\" method=\"post\">");
//        writer.println("Id_Pasien :       <input type=\"text\" size=\"2\" name=\"id_pasien\"/><br/>");
//        writer.println("Sex         :     <input type=\"text\" size=\"2\" name=\"sex\"/><br/>");
//        writer.println("Age         :     <input type=\"text\" size=\"2\" name=\"age\"/><br/>");
//        writer.println("BP          :     <input type=\"text\" size=\"8\" name=\"bp\"/><br/>");
//        writer.println("Cholesterol :     <input type=\"text\" size=\"8\" name=\"cholesterol\"/><br/>");
//        writer.println("Na_to_k :         <input type=\"text\" size=\"8\" name=\"na_to_k\"/><br/>");
//        writer.println("<input type=\"submit\" value=\"Simpan\"/>");
//        writer.println("</form>");
//        writer.println("</pre>");
//        writer.println("</body>");
//        writer.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (LoginAuth.isValid == true) {
            //ambil Parameter
            String stringId_Pasien = req.getParameter("id_pasien");
            int Id_Pasien = Integer.parseInt(stringId_Pasien);
            String stringAge = req.getParameter("age");
            int Age = Integer.parseInt(stringAge);
            String stringSex = req.getParameter("sex");
//            String stringAge = req.getParameter("age");
//            int Age = Integer.parseInt(stringAge);
            String stringBP = req.getParameter("bp");
            String stringCholesterol = req.getParameter("cholesterol");
            String na_to_k = req.getParameter("na_to_k");

            PrintWriter writer = resp.getWriter();
            writer.println("<html><body>");
            if (DBUtil.insert("pasien", Id_Pasien, Age, stringSex, stringBP,  stringCholesterol, na_to_k)) {
                writer.println("Insert" + stringId_Pasien + "Berhasil!<br/>");
            } else {
                writer.println("Insert" + stringId_Pasien + "Gagal!<br/>");
            }
            writer.println("<h4><a href=\"Main\">Kembali kemenu utama</a></h4>");
            writer.println("</html></body>");
            writer.flush();

        }else {
            LoginAuth.unauthorizedAcess(resp);
        }
    }
}
