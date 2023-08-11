package menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUtil;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/DownloadData"})
public class DownloadData extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("text.csv");
            resp.setHeader("Content-Disposition", "attachment; filename=\"drugData.csv\"");

            ResultSet rs = DBUtil.selectAll("drug200");
            ServletOutputStream outputStream = resp.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            writer.println("age, sex, bp, cholesterol, na_to_k, type_drug");

            while (rs.next()) {
                int age = rs.getInt("age");
                String gender = rs.getString("sex");
                String bp = rs.getString("bp");
                String cholesterol = rs.getString("cholesterol");
                String na_to_k = rs.getString("na_to_k");
                String type_drug = rs.getString("type_drug");
                writer.write(age + "," + gender + "," + bp + "," + cholesterol + "," + na_to_k + "," + type_drug +"\n");
            }
            writer.flush();
            writer.close();

            outputStream.flush();
            outputStream.close();

            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
