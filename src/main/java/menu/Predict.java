package menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.ResultSet;

@WebServlet(urlPatterns = {"/Predict"})
public class Predict extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // test tampilan
        PrintWriter writer = resp.getWriter();

        writer.println("<!DOCTYPE html>\n" +
                "<html>\n" +
                "  <head>\n" +
                "    <title>Form Input</title>\n" +
                "    <link rel=\"stylesheet\" href=\"predict.css\">\n" +
                "  </head>\n" +
                "  <body>\n" +
                "<form action=\"Predict\" method=\"post\">\n" +
                "    <h2>Form Input</h2>\n" +
                "    <form>\n" +
                "      <label for=\"age\">Age:</label>\n" +
                "      <input type=\"number\" id=\"age\" name=\"age\" placeholder=\"Enter your age\" required>\n" +
                "\n" +
                "      <label for=\"sex\">Sex:</label>\n" +
                "      <select id=\"sex\" name=\"sex\">\n" +
                "        <option value=\"\" disabled selected>Select your sex</option>\n" +
                "        <option value=\"0\">Male</option>\n" +
                "        <option value=\"1\">Female</option>\n" +
                "      </select>\n" +
                "\n" +
                "      <label for=\"bp\">BP:</label>\n" +
                "      <select id=\"bp\" name=\"bp\">\n" +
                "        <option value=\"\" disabled selected>Select your BP</option>\n" +
                "        <option value=\"0\">Low</option>\n" +
                "        <option value=\"1\">Normal</option>\n" +
                "        <option value=\"2\">High</option>\n" +
                "      </select>\n" +
                "\n" +
                "      <label for=\"cholesterol\">Cholesterol:</label>\n" +
                "      <select id=\"cholesterol\" name=\"cholesterol\">\n" +
                "        <option value=\"0\">High</option>\n" +
                "        <option value=\"1\">Normal</option>\n" +
                "      </select>\n" +
                "\n" +
                "      <label for=\"na_to_k\">Na_to_K:</label>\n" +
                "      <input type=\"number\" id=\"na_to_k\" name=\"na_to_k\" placeholder=\"Enter Na_to_K\" required>\n" +
                "\n" +
                "      <input type=\"submit\" value=\"Submit\">\n" +
                "    </form>\n" +
                "  </body>\n" +
                "</html>");
//        writer.println("<!DOCTYPE html>\n" +
//                "<html>\n" +
//                "<head>\n" +
//                "    <meta charset=\"UTF-8\">\n" +
//                "    <title>PREDICT DATA</title>\n" +
//                "    <link rel=\"stylesheet\" href=\"edit.css\">\n" +
//                "</head>\n" +
//                "<body>\n" +
//                "<form action=\"Predict\" method=\"post\">\n" +
//                "<form>\n" +
//                "    <label for=\"age\">Age:</label>\n" +
//                "    <input type=\"text\" size=\"2\" name=\"age\"/><br/>\n" +
//
//                "    <label for=\"sex\">Sex:</label>\n" +
//                "    <select name=\"sex\" id=\"sex\">\n" +
//                "       <option value=\"0\">Female</option>\n" +
//                "       <option value=\"1\">Male</option>\n" +
//                "</select>\n" +
//       //         "    <label for=\"bp\">BP:</label>\n" +
//       //         "    <select name=\"bp\" id=\"bp\">\n" +
//       //         "       <option value=\"0\">Low</option>\n" +
//       //         "       <option value=\"1\">Normal</option>\n" +
//       //         "       <option value=\"2\">High</option>\n" +
//
//                " Bp:     <select name=\"bp\">\n" +
//                "   <option value=\"0\">Low</option>\n" +
//                   "<option value=\"1\">Normal</option>\n" +
//                   "<option value=\"2\">High</option>\n" +
//                     "</select><br/>\n" +
//
//                "    <label for=\"cholesterol\">Cholesterol:</label>\n" +
//                "     <select name=\"cholesterol\" id=\"cholesterol\">\n" +
//                "       <option value=\"0\">High</option>\n" +
//                "       <option value=\"1\">Normal</option>\n" +
//                "</select><br/>\n" +
//                "      <label for=\"na_to_k\">Na_to_k:</label>\n" +
//                "     <input type=\"text\" size=\"8\" name=\"na_to_k\"/><br/>\n"+
//                "    <button type=\"submit\" id=\"update-btn\">Predict Salary</button>\n" +
//                "  </form>\n" +
//                "</body>\n" +
//                "</html>");
////        writer.println("<body>");
//        writer.println("<h2>Prediksi salary</h2>");
//        writer.println("<pre>\n" +
//                "\n" +
//                "<form action=\"Predict\" method=\"post\">\n" +
//                "Age        :    <input type=\"text\" size=\"5\" name=\"age\"/>\n" +
//                "Sex        :    <input type=\"text\" size=\"5\" name=\"sex\"/>\n" +
////                "Age        :    <input type=\"text\" size=\"5\" name=\"age\"/>\n" +
//                "Bp         :    <input type=\"text\" size=\"5\" name=\"bp\"/>\n" +
//                "Cholesterol:    <input type=\"text\" size=\"5\" name=\"cholesterol\"/>\n" +
//                "na_to_k    :    <input type=\"text\" size=\"5\" name=\"na_to_k\"/>\n" +
//                "\n" +
//                "<input type=\"submit\" value=\"Predict Salary\"/>\n" +
//                "</form>\n" +
//                "</pre>\n");
//        writer.println("</body>");
//        writer.println("</html>");
//        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<h2>Hasil prediksi Pasien</h2>");
        // ambil parameter panjang
        String stringAge = req.getParameter("age");
        int age = Integer.parseInt(stringAge);
        String stringSex = req.getParameter("sex");
        int sex = Integer.parseInt(stringSex);
//        String stringAge = req.getParameter("age");
//        int age = Integer.parseInt(stringAge);
        String stringBp = req.getParameter("bp");
        int bp = Integer.parseInt(stringBp);
        String stringCholesterol = req.getParameter("cholesterol");
        int cholesterol = Integer.parseInt(stringCholesterol);
        String stringNa_to_k = req.getParameter("na_to_k");
        int na_to_k = Integer.parseInt(stringNa_to_k);

//        writer.println("age = " + age + "<br>");
//        writer.println("sex = " + sex + "<br>");
////        writer.println("age = " + age + "<br>");
//        writer.println("bp = " + bp + "<br>");
//        writer.println("cholesterol = " + cholesterol + "<br>");
//        writer.println("na_to_k = " + na_to_k + "<br>");

        // eksekusi file python
        ProcessBuilder processBuilder = new ProcessBuilder(
                "python",
                "C:\\Users\\Revanza Zakly\\IdeaProjects\\Prediksi_Narkoba\\target\\predict.py",
                "C:\\Users\\Revanza Zakly\\IdeaProjects\\Prediksi_Narkoba\\target\\drug200.csv",
                stringAge, stringSex, stringBp, stringCholesterol, stringNa_to_k);
//        ProcessBuilder processBuilder = new ProcessBuilder("python",
//                "/Users/macbookpro/Coding/python/hello.py", stringExperience);
        Process process = processBuilder.start();
        // menampilkan hasil eksekusi
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            // menampilkan hasil ke web page
//            writer.println("<h4> Res = "  + salary + "</h4>");
            writer.println("<h4> Drug = "  + line + "</h4>");
            System.out.println(line);
        }

        writer.println("<a href=\"Main\">Kembali ke Menu Utama</a>");
        writer.println("</html>");
        writer.flush();
    }
}
