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

@WebServlet(urlPatterns = {"/InsertDatasetDrug"})
public class InsertDatasetDrug extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Input Data Pasien</title>\n" +
                "\n" +
                "    <link rel=\"stylesheet\" href=\"edit.css\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"InsertDatasetDrug\" method=\"post\">\n" +
                "    <div class=\"form-container\">\n" +
                "        <h2>Update Data Drug</h2>\n" +
                "        \n" +
                "        <form>\n" +
                "            <div class=\"form-group\">\n" +
                "                <label for=\"id_drug\">Id_Drug:</label>\n" +
                "                <input type=\"text\" id=\"id_drug\" name=\"id_drug\"\">\n" +
                "            </div>\n" +
                "            \n" +
                "            <div class=\"form-group\">\n" +
                "                <label for=\"type_drug\">Type_Drug:</label>\n" +
                "                <input type=\"text\" id=\"type_drug\" name=\"type_drug\"\">\n" +
                "            </div>" +
                "<div class=\"form-group\">\n" +
                "                <button type=\"submit\">Insert</button>\n" +
                "            </div>\n" +
                "        </form>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n");
//        writer.println("<body>");
//        writer.println("<pre>");
//        writer.println("<h4>Input Data Drug</h4>");
//        writer.println("<form action=\"InsertDatasetDrug\" method=\"post\">");
//        writer.println("Id_Drug           :     <input type=\"text\" size=\"2\" name=\"id_drug\"/><br/>");
//        writer.println("Type_Drug         :     <input type=\"text\" size=\"10\" name=\"type_drug\"/><br/>");
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
            String stringId_Narkoba = req.getParameter("id_drug");
            int id_drug = Integer.parseInt(stringId_Narkoba);
            String type_drug = req.getParameter("type_drug");

            PrintWriter writer = resp.getWriter();
            writer.println("<html><body>");
            if (DBUtil.insertDrug("narkoba", id_drug, type_drug)) {
                writer.println("Insert" + stringId_Narkoba + "Berhasil!<br/>");
            } else {
                writer.println("Insert" + stringId_Narkoba + "Gagal!<br/>");
            }
            writer.println("<h4><a href=\"Main\">Kembali kemenu utama</a></h4>");
            writer.println("</html></body>");
            writer.flush();

        }else {
            LoginAuth.unauthorizedAcess(resp);
        }
    }
}
