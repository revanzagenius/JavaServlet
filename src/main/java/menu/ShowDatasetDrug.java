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

@WebServlet(urlPatterns = {"/ShowDatasetDrug"})
public class ShowDatasetDrug extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showAllRecord(resp);
    }

    public static  void showAllRecord(HttpServletResponse resp) throws IOException {
        ResultSet resultSet = null;
        // DBUtil.connect();
        PrintWriter writer = resp.getWriter();

        try {
            resultSet = DBUtil.selectAll("narkoba");
            writer.println("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <link rel=\"stylesheet\" href=\"showdataset.css\">\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <p><h2>SHOW DATA OBAT</h2></p>\n" +
                    "    <table>\n" +
                    "        <thead>\n" +
                    "            <tr>\n" +
                    "               <th> ID_OBAT </th>\n" +
                    "               <th> TYPE_OBAT </th>\n" +
                    "               <th>Action</th>\n" +
                    "            </tr>\n" +
                    "        </thead>");
//            writer.println("<h2>Drugs 200 Dataset</h2>");
//            writer.println("<table border=2>");
//            writer.println("<tr>\n" +
//                    "   <th> ID_NARKOBA </th>\n" +
//                    "   <th> TYPE_NARKOBA </th>\n" +
//                    "   <th colspan=2>Action</th>\n"+
//                    " </tr>");

            while (resultSet.next()) {
                int id_drug = resultSet.getInt( "id_drug");
                System.out.println(id_drug);
                String type_drug = resultSet.getString("type_drug");


                writer.println("<tr>");
                writer.println("<td>" + id_drug +"</td>");
                writer.println("<td>" + type_drug +"</td>");


                writer.println("<td><a href=EditDrug?id_drug="+id_drug+"&type_drug="+type_drug+">Edit</a>\n" +
                            "<a href=DeleteDrug?id_drug="+id_drug+"&type_drug="+type_drug+">Delete</a></td>");
//                writer.println("<td><a href=DeleteDrug?id_drug="+id_drug+"&type_drug="+type_drug+">Delete</a></td>");

                writer.println("</tr>");
            }
            writer.println("</table>");
            writer.println("</html>");
            writer.flush();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
