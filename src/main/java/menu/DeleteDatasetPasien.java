package menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUtil;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/DeleteDatasetPasien"})
public class DeleteDatasetPasien extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id_pasien = req.getParameter("id_pasien");
        String sex = req.getParameter("sex");
        String age = req.getParameter("age");
        String bp  = req.getParameter("bp");
        String cholesterol  = req.getParameter("cholesterol");
        String na_to_k  = req.getParameter("na_to_k");


        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <link rel=\"stylesheet\" href=\"delete.css\">\n" +
                "    <title>Delete Pasien</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"DeleteDatasetPasien\" method=\"post\">\n" +
                "\t<div class=\"container\">\n" +
                "\t\t<h1>Delete Pasien</h1>\n" +
                "\t\t<form>\n" +
                "\t\t\t<label for=\"id\">ID_Pasien :</label>\n" +
                "<input type=\"text\" size=\"2\" name=\"id_pasien\"" +
                " value="+id_pasien+" readonly/><br/>\n" +
                "\t\t\t<label for=\"age\">age :</label>\n" +
                "<input type=\"text\" size=\"2\" name=\"age\"" +
                " value="+age+" readonly/><br/>\n" +
                "\t\t\t<label for=\"sex\">sex :</label>\n" +
                "<input type=\"text\" size=\"2\" name=\"sex\"" +
                " value="+sex+" readonly/><br/>\n" +
                "\t\t\t<input type=\"submit\" value=\"Delete\">\n" +
                "\t\t</form>\n" +
                "\t</div>\n" +
                "</body>\n" +
                "</html>");
//        writer.println("<body>");
//        writer.println("<pre>");
//        writer.println("<h4>Delete Data Pasien</h4>");
//        writer.println("<form action=\"DeleteDatasetPasien\" method=\"post\">");
//        writer.println("Id_Pasien :       <input type=\"text\" size=\"2\" name=\"id_pasien\"" +
//                " value="+id_pasien+" readonly/><br/>");
//        writer.println("Age         :     <input type=\"text\" size=\"2\" name=\"age\"" +
//                " value="+age+" readonly/><br/>");
//        writer.println("Sex         :     <input type=\"text\" size=\"2\" name=\"sex\"" +
//                " value="+sex+" readonly/><br/>");
////        writer.println("Age         :     <input type=\"text\" size=\"2\" name=\"age\"" +
////                " value="+age+" readonly/><br/>");
//        writer.println("<input type=\"submit\" value=\"Delete\"/>");
//        writer.println("</form>");
//        writer.println("</pre>");
//        writer.println("</body>");
//        writer.println("</html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ambil paramater yang di input
        String stringId_Pasien = req.getParameter("id_pasien");
        int Id_Pasien = Integer.parseInt(stringId_Pasien);
//        String stringSex = req.getParameter("sex");
//        String stringAge = req.getParameter("age");


        PrintWriter writer = resp.getWriter();
        writer.println("<html><body>");

        if (DBUtil.delete("pasien", Id_Pasien)) {
            writer.println("Delete" + stringId_Pasien + "Berhasil!<br/>");
        } else {
            writer.println("Delete" + stringId_Pasien + "Gagal!<br/>");
        }
        writer.println("<h4><a href=\"Main\">Kembali ke Menu Utama</a></h4>");
        writer.println("</html></body>");
        writer.flush();
    }
}
