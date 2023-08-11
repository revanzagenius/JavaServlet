package menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUtil;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/EditDrug"})
public class EditDrug extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id_drug = req.getParameter("id_drug");
        String type_drug = req.getParameter("type_drug");


        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Input/Update Data Pasien</title>\n" +
                "    <link rel=\"stylesheet\" href=\"edit.css\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"EditDrug\" method=\"post\">\n" +
                "    <div class=\"form-container\">\n" +
                "        <h2>Update Data Pasien</h2>\n" +
                "        \n" +
                "        <form>\n" +
                "            <div class=\"form-group\">\n" +
                "                <label for=\"id_drug\">Id_Drug:</label>\n" +
                "                <input type=\"text\" size=\"2\" name=\"id_drug\"" +
                "                   value=" + id_drug + " readonly/><br/>\n" +
                "            </div>\n" +
                "            \n" +
                "            <div class=\"form-group\">\n" +
                "                <input type=\"text\" size=\"5\" name=\"type_drug\"" +
                "                   value=" + type_drug + " /><br/>\n" +
                "            </div>" +
                " <div class=\"form-group\">\n" +
                "                <button type=\"submit\">Update</button>\n" +
                "            </div>\n" +
                "        </form>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n");
//        writer.println("<body>");
//        writer.println("<pre>");
//        writer.println("<h4>Edit Data Drug</h4>");
//        writer.println("<form action=\"EditDrug\" method=\"post\">");
//        writer.println("Id_Drug           :       <input type=\"text\" size=\"2\" name=\"id_drug\"" +
//                " value=" + id_drug + " readonly/><br/>");
//        writer.println("Type_Drug         :       <input type=\"text\" size=\"5\" name=\"type_drug\"" +
//                " value=" + type_drug + " /><br/>");
//
//        writer.println("<input type=\"submit\" value=\"Update\"/>");
//        writer.println("</form>");
//        writer.println("</pre>");
//        writer.println("</body>");
//        writer.println("</html>");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //ambil Parameter
        String stringId_Drug = req.getParameter("id_drug");
        int Id_Drug = Integer.parseInt(stringId_Drug);
        String stringType_Drug = req.getParameter("type_drug");



        PrintWriter writer = resp.getWriter();
        writer.println("<html><body>");
        if (DBUtil.updateDrug("narkoba", Id_Drug, stringType_Drug)) {
            writer.println("Update" + stringId_Drug + "Berhasil!<br/>");
        } else {
            writer.println("Update" + stringId_Drug + "Gagal!<br/>");
        }
        writer.println("<h4><a href=\"Main\">Kembali ke menu utama</a></h4>");
        writer.println("</html></body>");
        writer.flush();
    }
}
