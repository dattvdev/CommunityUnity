/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.AccountDAO;
import dao.Login;
import entity.SecurityUtils;
import entity.SendMail;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUp extends HttpServlet {

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
            out.println("<title>Servlet SignUp</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUp at " + request.getContextPath() + "</h1>");
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
        String email = request.getParameter("email");

        Cookie[] cookies = request.getCookies();
        String user = "";
        String pass = "";
        int role = 1;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();

                if (name.equals("name")) {
                    user = cookie.getValue().trim();
                }
                if (name.equals("pass")) {
                    pass = cookie.getValue();
                }
                if (name.equals("role")) {
                    role = Integer.parseInt(cookie.getValue());
                }
            }

        }

        AccountDAO dao = new AccountDAO();
        dao.insertAccount(email, SecurityUtils.hashMd5(pass), user, "", 1, role, "images/uer.png", user);

        Login login = new Login();
        login.deleteOTP(email);
        request.setAttribute("ERROR_MASSEGE", "Verify Success");
        request.getRequestDispatcher("login.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SendMail send = new SendMail();
        PrintWriter out = response.getWriter();
        String user = request.getParameter("su_username").trim();
        String pass = request.getParameter("su_password");

        String repass = request.getParameter("repass");
        String email = request.getParameter("email").trim();
        int role = Integer.parseInt(request.getParameter("is"));

        if (!pass.equals(repass)) {
            request.setAttribute("ERROR_MASSEGE", "Account creation failed");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            AccountDAO dao = new AccountDAO();
            boolean a = dao.checkAccountExits(user);
            if (!a && !dao.checkEmail(email)) {
                request.setAttribute("ERROR_MASSEGE", "Account creation success. Please check your email to verify your identity");
                Login l = new Login();
                int otp = l.generateOTP(6);
                l.insertOTP(email, otp);

                String link = "http://localhost:8080/CommunityUnity/signup";
                String title = "VERIFY ACCOUNT - COMMUNITY UNITY";
                send.sendEmail(email, otp, link, title, "");

                //cookie
                Cookie userCookie = new Cookie("name", user);
                Cookie emaill = new Cookie("email", email);
                Cookie passwordCookie = new Cookie("pass", pass);
                Cookie roleCookie = new Cookie("role", String.valueOf(role));
                //dat time ton tai
                userCookie.setMaxAge(60 * 60 * 24);
                passwordCookie.setMaxAge(60 * 60 * 24);
                emaill.setMaxAge(60 * 60 * 24);
                roleCookie.setMaxAge(60 * 60 * 24);
                //add browser cua nguoi dung
                response.addCookie(userCookie);
                response.addCookie(passwordCookie);
                response.addCookie(emaill);
                response.addCookie(roleCookie);
                request.getRequestDispatcher("login.jsp").forward(request, response);

            } else {
                //Day ve trang login
                request.setAttribute("ERROR_MASSEGE", "Account already exists");
                request.getRequestDispatcher("login.jsp").forward(request, response);
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
