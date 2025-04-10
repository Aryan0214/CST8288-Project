package presentation;

import business.VehicleService;
import business.GPSData;
import business.User;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;
import java.util.List;
/**
 * Servlet for handling GPS tracking requests.
 * Manages GET requests for reports and POST requests for logging.
 * @author Aryan
 */
public class GPSTrackingServlet extends HttpServlet {

    private VehicleService vehicleService;
    
    /**
     * Initializes the servlet with a VehicleService instance.
     */
    @Override
    public void init() throws ServletException {
        try {
            vehicleService = new VehicleService();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new ServletException("Failed to initialize VehicleService", e);
        }
    }

    /**
     * Handles GET requests to display GPS reports for Managers.
     * @param request  the HTTP request
     * @param response the HTTP response
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.html");
            return;
        }

        if ("Manager".equals(user.getRole())) {
            try {
                List<GPSData> report = vehicleService.getAllGPSData();
                request.setAttribute("gpsData", report);
            } catch (SQLException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error: " + e.getMessage());
                return;
            }
        }
        request.getRequestDispatcher("gps_tracking.jsp").forward(request, response);
    }
    
    /**
     * Handles POST requests to log GPS data.
     * @param request  the HTTP request
     * @param response the HTTP response
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.html");
            return;
        }

        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        String location = request.getParameter("location");
        String status = request.getParameter("status");

        try {
            vehicleService.logGPSData(vehicleId, location, status);
            if ("Manager".equals(user.getRole())) {
                List<GPSData> report = vehicleService.getAllGPSData();
                request.setAttribute("gpsData", report);
            }
            request.setAttribute("message", "GPS data logged successfully");
            request.getRequestDispatcher("gps_tracking.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("gps_tracking.jsp").forward(request, response);
        }
    }
}
