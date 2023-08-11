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

@WebServlet(urlPatterns = {"/ShowAllDataset"})
public class ShowAllDataset extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showAllRecord(resp);
    }

    public static  void showAllRecord(HttpServletResponse resp) throws IOException {
        ResultSet resultSet = null;
        // DBUtil.connect();
        PrintWriter writer = resp.getWriter();

        try {
            resultSet = DBUtil.selectAll("drug200");
            writer.println("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <link rel=\"stylesheet\" href=\"showdataset.css\">\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<p><h2>SHOW DATA PASIEN</h2></p>\n" +
                    "    <table>\n" +
                    "        <thead>\n" +
                    "            <tr>\n" +
                    "                <th>ID_PASIEN</th>\n" +
                    "                <th>AGE</th>\n" +
                    "                <th>SEX</th>\n" +
                    "                <th>BP</th>\n" +
                    "                <th>CHOLESTEROL</th>\n" +
                    "                <th>NA_TO_K</th>\n" +
                    "                <th>TYPE_DRUG </th>\n" +
                    "            </tr>\n" +
                    "        </thead>");
//            writer.println("<!DOCTYPE html>\n" +
//                    "<html>\n" +
//                    "<head>\n" +
//                    "\t<title>Tampilan Data Pasien</title>\n" +
//                    "\t<link rel=\"stylesheet\" href=\"showall.css\">\n" +
//                    "</head>\n" +
//                    "<body>\n" +
//                    "\t<h1>Tampilan Data Pasien</h1>\n" +
//                    "\t<table>\n" +
//                    "\t\t<thead>\n" +
//                    "\t\t\t<tr>\n" +
//                    "\t\t\t\t<th>ID_PASIEN</th>\n" +
//                    "\t\t\t\t<th>AGE</th>\n" +
//                    "\t\t\t\t<th>SEX</th>\n" +
//                    "\t\t\t\t<th>BP</th>\n" +
//                    "\t\t\t\t<th>CHOLESTEROL</th>\n" +
//                    "\t\t\t\t<th>NA_TO_K</th>\n" +
//                    "\t\t\t\t<th>TYPE_DRUG </th>\n" +
//                    "\t\t\t</tr>\n" +
//                    "\t\t</thead>");
//            writer.println("<h2>Drugs 200 Dataset</h2>");
//            writer.println("<table border=2>");
//            writer.println("<tr>\n" +
//                    "   <th> ID_PASIEN </th>\n" +
//                    "   <th> AGE </th>\n" +
//                    "   <th> SEX </th>\n" +
////                    "   <th> AGE </th>\n" +
//                    "   <th> BP </th>\n" +
//                    "   <th> CHOLESTEROL </th>\n" +
//                    "   <th> NA_TO_K </th>\n" +
//                    "   <th> TYPE_DRUG </th>\n" +
//                    " </tr>");

            while (resultSet.next()) {
                int Id_Pasien = resultSet.getInt( "id_pasien");
                System.out.println(Id_Pasien);
                int age = resultSet.getInt("age");
                String sex = resultSet.getString("sex");
//                int age = resultSet.getInt("age");
                String bp = resultSet.getString("bp");
                String cholesterol = resultSet.getString("cholesterol");
                String na_to_k = resultSet.getString("na_to_k");
                String type_drug = resultSet.getString("type_drug");

                writer.println("<tr>");
                writer.println("<td>" + Id_Pasien +"</td>");
                writer.println("<td>" + age +"</td>");
                writer.println("<td>" + sex +"</td>");
//                writer.println("<td>" + age +"</td>");
                writer.println("<td>" + bp +"</td>");
                writer.println("<td>" + cholesterol +"</td>");
                writer.println("<td>" + na_to_k +"</td>");
                writer.println("<td>" + type_drug +"</td>");

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
