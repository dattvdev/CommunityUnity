/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.AccountDAO;
import dao.ActivityDAO;
import dao.BlogsDAO;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author ytbhe
 */
@MultipartConfig
public class CreatePostController extends HttpServlet {

    private boolean isImageFile(String filename) {
        String[] allowedExtensions = {".jpg", ".jpeg", ".png", ".gif"};
        for (String extension : allowedExtensions) {
            if (filename.toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreatePostController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreatePostController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("createpost.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        BlogsDAO blogDAO = new BlogsDAO();
        HttpSession session = request.getSession();
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String shortcontent = request.getParameter("shortcontent");
        String category = request.getParameter("category");
        Part imagePart = request.getPart("photo");

        AccountDAO dao = new AccountDAO();
        String name = ((Account) session.getAttribute("LOGIN_USER")).getUserName();

        out.print(title + "" + name);
        String realPath = request.getServletContext().getRealPath("/images");
        String filename = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
        String image = realPath + "/" + filename;

        if (!Files.exists(Paths.get(realPath))) {
            Files.createDirectory(Paths.get(realPath));
        }

        if (imagePart != null && imagePart.getSize() > 0) {

            if (isImageFile(image)) {
                imagePart.write(image);
                blogDAO.insertBlogs(title, content, shortcontent, dao.GetUSERID(name), category, "images/" + filename);
                request.setAttribute("ERROR_MASSEGE", "Create success. Please wait for admin approval.");
                request.getRequestDispatcher("blogs").forward(request, response);

            } else {
                response.setContentType("text/plain");
                response.getWriter().write("Invalid file type. Please upload an image.");
            }

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
