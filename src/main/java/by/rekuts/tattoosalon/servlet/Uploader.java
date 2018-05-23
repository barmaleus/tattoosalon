package by.rekuts.tattoosalon.servlet;

import by.rekuts.tattoosalon.db.dao.PublicationDAO;
import by.rekuts.tattoosalon.resource.ConfigurationManager;
import by.rekuts.tattoosalon.resource.MessageManager;
import by.rekuts.tattoosalon.subject.Publication;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@MultipartConfig(fileSizeThreshold=1024*1024*1, // 1MB
        maxFileSize=1024*1024*2,      // 2MB
        maxRequestSize=1024*1024*10)   // 10MB
public class Uploader extends HttpServlet {

     /* Name of the directory where uploaded files will be saved, relative to
     * the web application directory.*/

    private static final String SAVE_DIR = "image/gallery";
    private static String relatedDirAndFilePath = null;

    /* handles file upload */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        final String title = request.getParameter("title");
        final boolean textNotPhoto = false;
        final String author = request.getSession().getAttribute("user").toString();

        final Part filePart = request.getPart("file");
        String fileName = getFileName(filePart);
        String savePath = request.getServletContext().getRealPath("") + SAVE_DIR;

        /* Validating of image type */
        if (fileName.endsWith(".jpg") || fileName.endsWith(".gif") || fileName.endsWith(".png")
                || fileName.endsWith(".JPG") || fileName.endsWith(".GIF") || fileName.endsWith(".PNG")) {

            /* Validating of avoiding rewriting image file*/

            File savingFilePath = new File(savePath + File.separator + fileName);
            while (savingFilePath.exists()) {
                //realize it
                String[] parts = fileName.split("\\.");
                if (parts.length > 1) {
                    String nameBody = parts[parts.length - 2];
                    Pattern pattern = Pattern.compile(".+\\(\\d+\\)");
                    Matcher matcher = pattern.matcher(nameBody);
                    if (matcher.matches()) {
                        int openingBracet = nameBody.lastIndexOf("(");
                        int closeBracet = nameBody.lastIndexOf(")");
                        int incrementingNumber = Integer.parseInt(nameBody.substring(openingBracet + 1, closeBracet));
                        parts[parts.length - 2] = nameBody.substring(0, openingBracet) + "(" + ++incrementingNumber + ")";
                    } else {
                        parts[parts.length - 2] = parts[parts.length - 2] + "(1)";
                    }
                    fileName = StringUtils.join(new ArrayList<String>(Arrays.asList(parts)), ".");
                    savingFilePath = new File(savePath + File.separator + fileName);
                } else {
                    request.setAttribute("fileTypeError", MessageManager.getProperty("message.ubloader.bigsize"));
                    String page = ConfigurationManager.getProperty(("path.page.uploader"));
                    getServletContext().getRequestDispatcher(page).forward(request, response);
                }
            }

            OutputStream out = null;
            InputStream filecontent = null;
            final PrintWriter writer = response.getWriter();

            try {
                out = new FileOutputStream(savingFilePath);
                filecontent = filePart.getInputStream();

                int read = 0;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                final Publication image = new Publication(title, relatedDirAndFilePath, textNotPhoto, author);
                PublicationDAO.insertNewPublication(image);
                request.setAttribute("message", "File " + /*fileName +*/ " has been uploaded successfully!");
                String page = ConfigurationManager.getProperty("path.page.command.uploaded");
                getServletContext().getRequestDispatcher(page).forward(request, response);
            } finally {
                if (out != null) {
                    out.close();
                }
                if (filecontent != null) {
                    filecontent.close();
                }
                if (writer != null) {
                    writer.close();
                }
            }
        } else {
            request.setAttribute("fileTypeError", MessageManager.getProperty("message.uploader.wrongtype"));
            String page = ConfigurationManager.getProperty(("path.page.uploader"));
            getServletContext().getRequestDispatcher(page).forward(request, response);
        }
    }

    /* Extracts file name from HTTP header content-disposition */

    private String getFileName(final Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                String fName = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
                relatedDirAndFilePath = SAVE_DIR + File.separator + fName;
                return fName;
            }
        }
        return null;
    }
}
