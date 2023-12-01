package control;

import dao.AccountDAO;

import entity.Account;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.servlet.http.Part;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author twna21
 */
@MultipartConfig
public class ProfileControl extends HttpServlet {

    private boolean isImageFile(String filename) {
        String[] allowedExtensions = {".jpg", ".jpeg", ".png", ".gif"};
        for (String extension : allowedExtensions) {
            if (filename.toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String Username = request.getParameter("username");
        AccountDAO data = new AccountDAO();
        Account a = data.getAccount_BYUSER(Username);
        request.setAttribute("LOGIN_USER", a);
        request.getRequestDispatcher("Profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String user = request.getParameter("username");

        Part imagePart = request.getPart("image");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String birthday = request.getParameter("birthDAY");
        String phone = request.getParameter("phone");

        String email = request.getParameter("email");
        int sex = Integer.parseInt(request.getParameter("sex"));

        String realPath = request.getServletContext().getRealPath("/images");
        String filename = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
        String image = realPath + "/" + filename;

        if (!Files.exists(Paths.get(realPath))) {
            Files.createDirectory(Paths.get(realPath));
        }

        if (imagePart != null && imagePart.getSize() > 0) {

            if (isImageFile(image)) {
                imagePart.write(image);
                request.setAttribute("img", "images/" + filename);

                AccountDAO data = new AccountDAO();

                int userID = data.GetUSERID(user);

                data.updateACCOUNT(userID, phone, address, name, email, birthday, "images/" + filename, sex);

                Account a = data.getAccount_BYUSER(user);

                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_USER", a);
                request.setAttribute("LOGIN_USER", a);
                request.getRequestDispatcher("Profile.jsp").forward(request, response);
            } else {
                response.setContentType("text/plain");
                response.getWriter().write("Invalid file type. Please upload an image.");
            }

        }
    }
}
