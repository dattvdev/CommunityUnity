package control;

import dao.AccountDAO;
import dao.ActivityDAO;
import dao.NotificateDAO;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author datka
 */
@MultipartConfig
public class ActivityPendingControl extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ActivityPendingControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ActivityPendingControl at " + request.getContextPath() + "</h1>");
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
        Part imagePart = request.getPart("image");
        HttpSession session = request.getSession();
        String activityName = request.getParameter("activityName");
        String description = request.getParameter("description");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        String province = request.getParameter("province");
        String district = request.getParameter("district");
        String ward = request.getParameter("ward");

        String location = ward + "-" + district + "-" + province;
        int memberLimit = Integer.parseInt(request.getParameter("memberLimit"));

        String realPath = request.getServletContext().getRealPath("/images");
        String filename = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
        String image = realPath + "/" + filename;

        if (!Files.exists(Paths.get(realPath))) {
            Files.createDirectory(Paths.get(realPath));
        }
        Date startDate = null;
        Date endDate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            startDate = dateFormat.parse(startDateStr);
            endDate = dateFormat.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (imagePart != null && imagePart.getSize() > 0) {

            try {
                if (isImageFile(image)) {
                    imagePart.write(image);
                    request.setAttribute("img", "images/" + filename);
                    
                } else {
                    response.setContentType("text/plain");
                    response.getWriter().write("Invalid file type. Please upload an image.");
                }
                AccountDAO dao = new AccountDAO();
                int oid = ((Account) session.getAttribute("LOGIN_USER")).getAccId();
                ActivityDAO acDAO = new ActivityDAO();
                acDAO.CreatePendingActivity(activityName, description, startDate, endDate, location, oid, memberLimit, "images/" + filename);
                // Sau khi xử lý thành công, chuyển hướng đến trang thành công hoặc trang danh sách sự kiện
                NotificateDAO noti = new NotificateDAO();
                Date date = new Date();
                noti.addNotification(1, "You have new event need to approve", date, "ApproveControl", 1);
                response.sendRedirect("home");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ActivityPendingControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // Xử lý tải lên hình ảnh (nếu có)
        // Xử lý ngày bắt đầu và ngày kết thúc (chuyển từ String sang Date)

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
