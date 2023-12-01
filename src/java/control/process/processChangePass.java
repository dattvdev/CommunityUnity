/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.process;

import dao.AccountDAO;
import dao.Login;
import entity.SecurityUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ytbhe
 */
public class processChangePass extends HttpServlet {

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

        String pass = "";
        int otp = Integer.parseInt(request.getParameter("otp"));
        String email = request.getParameter("email");
        /* TODO output your page here. You may use following sample code. */

        Login dao = new Login();
        AccountDAO ac = new AccountDAO();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();

                if (name.equals("newpass")) {
                    pass = cookie.getValue();
                }
            }
            if ((otp == dao.getOTP(email))) {

                ac.updateAccountPassword(email, SecurityUtils.hashMd5(pass));

                dao.deleteOTP(email);
                request.setAttribute("ERROR_MASSEGE", "Password had changed. Input again to login.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                request.setAttribute("ERROR_MASSEGE", "error");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } else {
            dao.deleteOTP(email);
            request.setAttribute("ERROR_MASSEGE", "You cann't access.");
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
        processRequest(request, response);
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
