package menu;

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

@WebServlet(urlPatterns = {"/InsertRelasi"})
public class InsertRelasi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        DBUtil.connect();
        PrintWriter writer = resp.getWriter();
        ResultSet rs = null;
//        if (loginPage.isValid) {

        try {
            rs = DBUtil.selectAll("narkoba");
            rs = DBUtil.selectAll("pasien");
//                rs = DBUtils.selectAll("emp");
            writer.println("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Input/Update Data Pasien</title>\n" +
                    "    <link rel=\"stylesheet\" href=\"edit.css\">\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<form action=\"InsertRelasi\" method=\"post\">\n" +
                    "<form>\n" +
                    " <tr>\n" +
                    "<td><label for='narkoba'>Choose a drug: </label>\n" +
                    "<td><select name='id_drug' id='id_drug'>" );

           rs = DBUtil.selectAll("narkoba");
            while (rs.next()) {
                int id_drug = rs.getInt("id_drug");
                String type_drug = rs.getString("type_drug");
                writer.println("<option value='"+ id_drug +"'>"+ type_drug + "</option>");
            }
            writer.println("</select></td></tr>");
            writer.println("<tr>\n" +
                    "<td><label for='pasien'>Choose an id Pasien: </label>\n" +
                    "<td><select name='id_pasien' id='id_pasien'>" );

            rs = DBUtil.selectAll("pasien");
            while (rs.next()) {
                int id_pasien = rs.getInt("id_pasien");
                writer.println("<option value='"+ id_pasien +"'>"+ id_pasien + "</option>");
            }
            writer.println("</select></td></tr>");
            writer.println("</table>");
            writer.println("<input type=\"submit\" value=\"Insert Record\"/></form>");
            writer.println("</body>");
            writer.println("</html>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        } else {
//            loginPage.unAuthorizedAccess(resp);
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBUtil.connect();
        PrintWriter writer = resp.getWriter();
//        if (loginPage.isValid) {

        int id_pasien = Integer.parseInt(req.getParameter("id_pasien"));
        int id_drug = Integer.parseInt(req.getParameter("id_drug"));


        writer.println("<html>");
        writer.println("<body>");
        if (DBUtil.insertRole("relation", id_pasien, id_drug)) {
            writer.println("<h2>Record " + id_pasien + " is succesfully added</h2><br/>");
//            writer.println("<a href =\"Main\">Kembali ke Menu Utama</a>");
            writer.println("<a href=\"ShowRelasi\">Lihat Data</a>");
        } else {
            writer.println(id_pasien +" is failed!");
        }
        writer.println("</body>");
        writer.println("</html>");
//        } else {
//            loginPage.unAuthorizedAccess(resp);
    }
}
