package menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUtil;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/DeleteDrug"})
public class DeleteDrug extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id_drug = req.getParameter("id_drug");
        String type_drug = req.getParameter("type_drug");

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
                   "<form action=\"DeleteDrug\" method=\"post\">\n" +
                   "\t<div class=\"container\">\n" +
                   "\t\t<h1>Delete Drug</h1>\n" +
                   "\t\t<form>\n" +
                   "\t\t\t<label for=\"id\">ID DRUG :</label>\n" +
                   "\t\t\t<input type=\"text\" size=\"2\" name=\"id_drug\" " +
                           " value="+id_drug+" readonly><br/>\n" +
                   "\t\t\t<label for=\"type_drug\">TYPE_DRUG :</label>\n" +
                   "\t\t\t<input type=\"text\" size=\"25\" name=\"type_drug\" " +
                            " value="+type_drug+" readonly><br/> \n" +
                   "\t\t\t</select>\n" +
                   "\t\t\t<input type=\"submit\" value=\"Delete\">\n" +
                   "\t\t</form>\n" +
                   "\t</div>\n" +
                   "</body>\n" +
                   "</html>");
//        writer.println("<body>");
//        writer.println("<head>");
//        writer.println("<meta charset=\"UTF-8\">");
//        writer.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
//        writer.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
//        writer.println("<title>Drug 200 Care</title>");
//        writer.println("<link rel=\"stylesheet\" href=\"style.css\">");
//        writer.println("</head>");
//
//        writer.println("<pre>");
//        writer.println("<h4>Delete Drug</h4>");
//        writer.println("<form action=\"DeleteDrug\" method=\"post\">");
//        writer.println("ID_Drug :     <input type=\"text\" size=\"2\" name=\"id_drug\" value="+id_drug+" readonly><br/>");
//        writer.println("Type_Drug    :     <input type=\"text\" size=\"25\" name=\"type_drug\" value="+type_drug+" readonly><br/>");
//
//        writer.println("<input type=\"submit\" value=\"Delete\"/>");
//        writer.println("</form>");
//        writer.println("</pre>");
//        writer.println("</body>");
//        writer.println("</html>");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ambil paramater yang di input
       String stringId_Drug = req.getParameter("id_drug");
       int id_drug = Integer.parseInt(stringId_Drug);

       String stringType_drug = req.getParameter("type_drug");
//        String stringAge = req.getParameter("age");


        PrintWriter writer = resp.getWriter();
        writer.println("<html><body>");

        if (DBUtil.deleteDrug("narkoba", id_drug)) {
            writer.println("Delete" + stringId_Drug + "Berhasil!<br/>");
        } else {
            writer.println("Delete" + stringId_Drug + "Gagal!<br/>");
        }
        writer.println("<h4><a href=\"Main\">Kembali ke Menu Utama</a></h4>");
        writer.println("</html></body>");
        writer.flush();
    }
}
