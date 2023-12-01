/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.AccountDAO;
import dao.Login;
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
public class ForgetPass extends HttpServlet {

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

        int otp = Integer.parseInt(request.getParameter("otp"));
        String email = request.getParameter("email");
        /* TODO output your page here. You may use following sample code. */

        Login dao = new Login();

        if ((otp == dao.getOTP(email)) && dao.checkTIME_OTP(email)) {

            dao.deleteOTP(email);

            request.setAttribute("email", email);
            request.getRequestDispatcher("forgetPass_newpass.jsp").forward(request, response);

        } else {

            request.setAttribute("ERROR_MASSEGE", " OTP not valid" + " or maybe expired. ");
            dao.deleteOTP(email);
            request.getRequestDispatcher("otp.jsp").forward(request, response);

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
        response.setContentType("text/html;charset=UTF-8");

        String email = request.getParameter("email");

        Login dao = new Login();
        AccountDAO acc = new AccountDAO();

        if (!acc.checkEmail(email)) {
            request.setAttribute("ERROR_MASSEGE", "The email is not exist.");
            request.getRequestDispatcher("foget.jsp").forward(request, response);
        } else {
            int otp = dao.generateOTP(6);
            dao.insertOTP(email, otp);

            SendMail send = new SendMail();
            String link = "http://localhost:8080/CommunityUnity/forget";
            String title = "FORGOT PASSWORD - COMMUNITY UNITY";
            String otpMSG = "\n" + "<p>This is OTP:</p>" + "<p>OTP: " + otp + "</p>";
            send.sendEmail(email, otp, link, title, otpMSG);
            request.setAttribute("email", email);
            request.getRequestDispatcher("otp.jsp").forward(request, response);
        }

    }
}
