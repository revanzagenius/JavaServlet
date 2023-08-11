package menu;

import auth.LoginAuth;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUtil;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/Main"})
public class Main extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Connect To Databases
        // DBUtil.connect();
        PrintWriter writer = resp.getWriter();
        if (LoginAuth.isValid == true) {
            writer.println("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "  <title>Dashboard</title>\n" +
                    "  <link rel=\"stylesheet\" href=\"DashboardStyle.css\">\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "  <div class=\"menu\">\n" +
                    "      <h1>Menu Utama</h1>\n" +
                    "      <ul>\n" +
                    "          <li><a href=\"ShowDataset\">Show Dataset Pasien</a></li>\n" +
                    "          <li><a href=\"InsertDataset\">Insert New Dataset Pasien</a></li>\n" +
                    "          <li><a href=\"ShowDatasetDrug\">Show Dataset Drug</a></li>\n" +
                    "          <li><a href=\"InsertDatasetDrug\">Insert New Dataset Drug</a></li>\n" +
                    "          <li><a href=\"ShowRelasi\">Show Relasi</a></li>\n" +
                    "          <li><a href=\"InsertRelasi\">Insert New Relasi</a></li>\n" +
                    "          <li><a href=\"ShowAllDataset\"> Show All Dataset</a></li>\n" +
                    "          <li><a href=\"DownloadData\"> Download All Dataset</a></li>\n" +
                    "          <li><a href=\"Predict\">Predict Data</a></li>\n" +
                    "      </ul>\n" +
                    "      <a href=\"#\">Logout</a>\n" +
                    "  </div>\n" +
                    "</body>\n" +
                    "</html>");
//            writer.println("<!DOCTYPE html>\n" +
//                    "<html>\n" +
//                    "<head>\n" +
//                    "    <meta charset=\"UTF-8\">\n" +
//                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
//                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
//                    "    <link rel=\"stylesheet\" href=\"mainstyle.css\">\n" +
//                    "\n" +
//                    "    <title><Menu></Menu></title>\n" +
//                    "</head>\n" +
//                    "<body>\n" +
//                    "    <div class=\"container\">\n" +
//                    "        <div class=\"menu\">\n" +
//                    "            <ul>\n" +
//                    "                <p> Menu Utama </p>\n" +
//                    "                <li><a href=\"ShowDataset\">1. Show Dataset Pasien</a></li>\n" +
//                    "                <li><a href=\"InsertDataset\">2.  New Dataset Pasien</a></li>\n" +
//                    "                <li><a href=\"ShowDatasetDrug\">3. Show Dataset Drug</a></li>\n" +
//                    "                <li><a href=\"InsertDatasetDrug\">4. Insert New Dataset Drug</a></li>\n" +
//                    "                <li><a href=\"ShowRelasi\">5. Show Relasi</a></li>\n" +
//                    "                <li><a href=\"InsertRelasi\">6. Insert New Relasi</a></li>\n" +
//                    "                <li><a href=\"ShowAllDataset\">7. Show All Dataset</a></li>\n" +
//                    "                <li><a href=\"Predict\">8. Predict</a></li>\n" +
//                    "            </ul>\n" +
//                    "        </div>\n" +
//                    "    </div>\n" +
//                    "</body>\n" +
//                    "</html>\n");
//            writer.println("<body>");
//            writer.println("<h2>Menu Utama</h2>");
//            writer.println("<h3><a href=\"ShowDataset\">1. Show Dataset Pasien</a></h3>");
//            writer.println("<h3><a href=\"InsertDataset\">2. Insert New Dataset Pasien</a></h3>");
//            writer.println("<h3><a href=\"ShowDatasetDrug\">3. Show Dataset Drug</a></h3>");
//            writer.println("<h3><a href=\"InsertDatasetDrug\">4. Insert New Dataset Drug</a></h3>");
//            writer.println("<h3><a href=\"ShowRelasi\">5. Show Relasi</a></h3>");
//            writer.println("<h3><a href=\"InsertRelasi\">6. Insert New Relasi</a></h3>");
//            writer.println("<h3><a href=\"ShowAllDataset\">7. Show All Dataset</a></h3>");
//            writer.println("<h3><a href=index.jsp>back</a></h3>");
//            writer.println("</body>");
//            writer.println("</html>");
            writer.flush();

        }else{
            LoginAuth.unauthorizedAcess(resp);
        }
    }
}