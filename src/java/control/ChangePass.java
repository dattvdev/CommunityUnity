/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.AccountDAO;
import dao.Login;
import entity.Account;
import entity.SecurityUtils;
import entity.SendMail;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author twna21
 */
public class ChangePass extends HttpServlet {

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
            out.println("<title>Servlet ChangePass</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePass at " + request.getContextPath() + "</h1>");
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
        Cookie[] cookies = request.getCookies();
        String user = "";

        if (cookies != null) {

            for (Cookie cookie : cookies) {
                String name = cookie.getName();

                if (name.equals("email")) {
                    user = cookie.getValue().trim();
                }

            }

            String email = user;
            request.setAttribute("email", email);
            request.getRequestDispatcher("changePass.jsp").forward(request, response);

        } else {
            request.setAttribute("ERROR_MASSEGE", "You cannot access.");

            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
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

        SendMail send = new SendMail();

        String email = request.getParameter("email").trim();
        String pass = request.getParameter("new_password");
        String repass = request.getParameter("repass");

        if (!pass.equals(repass)) {
            request.setAttribute("ERROR_MASSEGE", "Account creation failed");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            AccountDAO dao = new AccountDAO();

            request.setAttribute("ERROR_MASSEGE", "Please check your email to verify your change");
            Login l = new Login();
            int otp = l.generateOTP(6);
            l.insertOTP(email, otp);

            String link = "http://localhost:8080/CommunityUnity/changepassprc";
            String title = "CHANGE PASS - COMMUNITY UNITY";
            send.sendEmail(email, otp, link, title, "");


            Cookie passwordCookie = new Cookie("newpass", pass);
            //dat time ton tai

            //add browser cua nguoi dung

            passwordCookie.setMaxAge(60 * 60 * 24);

            response.addCookie(passwordCookie);
            request.getRequestDispatcher("login.jsp").forward(request, response);

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
