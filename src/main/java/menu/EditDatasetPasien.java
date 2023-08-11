package menu;

import auth.LoginAuth;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUtil;

import javax.swing.text.html.HTMLEditorKit;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/EditDatasetPasien"})
public class EditDatasetPasien extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (LoginAuth.isValid == true) {
            String id_pasien = req.getParameter("id_pasien");
            String sex = req.getParameter("sex");
            String age = req.getParameter("age");
            String bp  = req.getParameter("bp");
            String cholesterol  = req.getParameter("cholesterol");
            String na_to_k  = req.getParameter("na_to_k");


            PrintWriter writer = resp.getWriter();
            writer.println("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Input/Update Data Pasien</title>\n" +
                    "    <link rel=\"stylesheet\" href=\"edit.css\">\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<form action=\"EditDatasetPasien\" method=\"post\">\n" +
                    "<form>\n" +
                    "    <label for=\"id_pasien\">Id Pasien:</label>\n" +
                    "    <input type=\"text\" size=\"2\" name=\"id_pasien\"" +
                    "       value="+id_pasien+" readonly/><br/>\n" +
                    "    <label for=\"age\">Age:</label>\n" +
                    "    <input type=\"text\" size=\"2\" name=\"age\"" +
                    "       value="+age+" /><br/>\n" +
                    "    <label for=\"sex\">Sex:</label>\n" +
                    "    <input type=\"text\" size=\"2\" name=\"sex\"" +
                    "      value="+sex+" /><br/>\n" +
//                    "    <label for=\"age\">Age:</label>\n" +
//                    "    <input type=\"text\" size=\"2\" name=\"age\"" +
//                    "       value="+age+" /><br/>\n" +
                    "    <label for=\"bp\">BP:</label>\n" +
                    "     <input type=\"text\" size=\"8\" name=\"bp\"" +
                    "       value="+bp+" /><br/>\n" +
                    "    <label for=\"cholesterol\">Cholesterol:</label>\n" +
                    "    <input type=\"text\" size=\"8\" name=\"cholesterol\"" +
                    "       value="+cholesterol+" /><br/>\n"+
                    "    <label for=\"na_to_k\">Na_to_k:</label>\n" +
                    "     <input type=\"text\" size=\"8\" name=\"na_to_k\"" +
                    "       value="+na_to_k+" /><br/>\n"+
                    "    <button type=\"submit\" id=\"update-btn\">Update</button>\n" +
                    "  </form>\n" +
                    "</body>\n" +
                    "</html>");
//            writer.println("<body>");
//            writer.println("<pre>");
//            writer.println("<h4>Edit Data Pasien</h4>");
//            writer.println("<form action=\"EditDatasetPasien\" method=\"post\">");
//            writer.println("Id_Pasien :       <input type=\"text\" size=\"2\" name=\"id_pasien\"" +
//                    " value="+id_pasien+" readonly/><br/>");
//            writer.println("Sex         :     <input type=\"text\" size=\"2\" name=\"sex\"" +
//                    " value="+sex+" /><br/>");
//            writer.println("Age         :     <input type=\"text\" size=\"2\" name=\"age\"" +
//                    " value="+age+" /><br/>");
//            writer.println("BP          :     <input type=\"text\" size=\"8\" name=\"bp\"" +
//                    " value="+bp+" /><br/>");
//            writer.println("Cholesterol :     <input type=\"text\" size=\"8\" name=\"cholesterol\"" +
//                    " value="+cholesterol+" /><br/>");
//            writer.println("Na_to_k :         <input type=\"text\" size=\"8\" name=\"na_to_k\"" +
//                    " value="+na_to_k+" /><br/>");
//            writer.println("<input type=\"submit\" value=\"Update\"/>");
//            writer.println("</form>");
//            writer.println("</pre>");
//            writer.println("</body>");
//            writer.println("</html>");

        }else {
            LoginAuth.unauthorizedAcess(resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //ambil Parameter
        String stringId_Pasien = req.getParameter("id_pasien");
        int Id_Pasien = Integer.parseInt(stringId_Pasien);
        String stringAge = req.getParameter("age");
        int Age = Integer.parseInt(stringAge);
        String stringSex = req.getParameter("sex");
//        String stringAge = req.getParameter("age");
//        int Age = Integer.parseInt(stringAge);
        String stringBP = req.getParameter("bp");
        String stringCholesterol = req.getParameter("cholesterol");
        String na_to_k = req.getParameter("na_to_k");


        PrintWriter writer = resp.getWriter();
        writer.println("<html><body>");
        if (DBUtil.update("pasien", Id_Pasien, Age, stringSex,  stringBP,  stringCholesterol, na_to_k)) {
//            writer.println("Update" + stringId_Pasien + "Berhasil!<br/>");
            resp.sendRedirect("ShowDataset");
        } else {
            writer.println("Update" + stringId_Pasien + "Gagal!<br/>");
        }
        writer.println("<h4><a href=\"Main\">Kembali ke menu utama</a></h4>");
        writer.println("</html></body>");
        writer.flush();
    }
}


