package control;

import dao.AccountDAO;
import entity.Account;
import entity.SecurityUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author twna21
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("LOGIN_USER") != null) {
            resp.sendRedirect(req.getContextPath());
            return;
        }

        // Check cookie
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("name")) {
                    session = req.getSession(true);
                    session.setAttribute("name", cookie.getValue());
                    resp.sendRedirect(req.getContextPath());
                    return;
                }
            }
        }
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String user = request.getParameter("username");
            String pass = SecurityUtils.hashMd5(request.getParameter("password"));
            AccountDAO loginDAO = new AccountDAO();

            if (!loginDAO.checkAccountExits(user)) {
                request.setAttribute("ERROR_MASSEGE", "Account not existed");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            Account a = loginDAO.getAccount1(user, pass);
            HttpSession session = request.getSession();
            session.setAttribute("LOGIN_USER", a);
            if (a == null) {
                request.setAttribute("ERROR_MASSEGE", "Wrong password");

                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                Cookie userCookie = new Cookie("name", user);
                Cookie passwordCookie = new Cookie("pass", pass);
                //dat time ton tai
                userCookie.setMaxAge(60 * 60 * 24);
                passwordCookie.setMaxAge(60 * 60 * 24);
                //add browser cua nguoi dung
                response.addCookie(userCookie);
                response.addCookie(passwordCookie);
                if (a.getRole() == 0) {
                    Account aa = loginDAO.getAccount1(user, pass);
                    session.setAttribute("LOGIN_USER", aa);
                    response.sendRedirect("adminPage.jsp");
                } else {
                    Account aa = loginDAO.getAccount1(user, pass);
                    session.setAttribute("LOGIN_USER", aa);
                    session.setAttribute("LOGIN_USER", a);
                    response.sendRedirect("home");
                }
            }

        } catch (Exception e) {

        }
    }
}
