package menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUtil;

import javax.xml.transform.Result;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/ShowRelasi"})
public class ShowRelasi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showAllRecord(resp);
    }

    public static  void showAllRecord(HttpServletResponse resp) throws IOException {
        //        DBUtil.connect();
        PrintWriter writer = resp.getWriter();
        ResultSet rs = null;
//        if (loginPage.isValid) {
        try {
            rs = DBUtil.selectAllRole("pasien", "narkoba", "relation",  "age", "type_drug");

            writer.println("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <link rel=\"stylesheet\" href=\"showdataset.css\">\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<p><h2>SHOW DATA RELASI</h2></p>\n" +
                    "    <table>\n" +
                    "        <thead>\n" +
                    "            <tr>\n" +
                    "                <th>ID</th>\n" +
                    "                <th>Age</th>\n" +
                    "                <th>Sex</th>\n" +
                    "                <th>Action</th>\n" +
                    "            </tr>\n" +
                    "        </thead>");

            //        writer.println("<!DOCTYPE html>\n" +
//                    "<html lang=\"en\">\n" +
//                    "<head>\n" +
//                    "    <meta charset=\"UTF-8\">\n" +
//                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
//                    "    <link rel=\"stylesheet\" href=\"ShowRelasi.css\">\n" +
//                    "    <title>Document</title>\n" +
//                    "</head>\n" +
//                    "<body>\n" +
//                    "    <table>\n" +
//                    "        <thead>\n" +
//                    "          <tr>\n" +
//                    "            <th>ID</th>\n" +
//                    "            <th>Age</th>\n" +
//                    "            <th>Type_Drug</th>\n" +
//                    "            <th>Action</th>\n" +
//                    "          </tr>\n" +
//                    "        </thead>");
//            writer.println("<body>");
//            writer.println("<h2>List data Employees</h2>");
//            writer.println("<table border=1 >");
//            writer.println("<tr><th>ID</th><th>Age</th><th>Type_Drug</th><th>Action</th></tr>")
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String age = rs.getString("age");
                String type_drug = rs.getString("type_drug");

                writer.println("<tr><td>" + id + "</td><td>"
                        + age + "</td><td>"
                        + type_drug +"</td>"
                        + "<td><a href=DeleteRelasi?id="+id+"&age="+age+"&type_drug="+type_drug+">Delete</a></td>");
//                writer.println("</tr>");
            }
            writer.println("</table><br/>");
            writer.println("<a href =\"Main\">Kembali ke Menu Utama</a>");
            writer.println("</body>");
            writer.println("</html>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        } else {
//            loginPage.unAuthorizedAccess(resp);
//        }
    }
}