package presentation;

import business.VehicleService;
import business.User;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;
/**
 * Servlet for handling vehicle management requests.
 * Processes POST requests to add vehicles for Managers.
 * @author Salman
 */
public class VehicleServlet extends HttpServlet {
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
     * Handles POST requests to add a vehicle.
     * @param request  the HTTP request
     * @param response the HTTP response
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || !"Manager".equals(user.getRole())) {
            response.sendRedirect("login.html");
            return;
        }

        String type = request.getParameter("type");
        String number = request.getParameter("number");
        String fuelType = request.getParameter("fuel_type");
        double rate = Double.parseDouble(request.getParameter("consumption_rate"));
        int maxPassengers = Integer.parseInt(request.getParameter("max_passengers"));
        String currentRoute = request.getParameter("current_route");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            vehicleService.addVehicle(type, number, fuelType, rate, maxPassengers, currentRoute);
            out.write("<html><body>Vehicle added successfully<br><a href='index.html'>Back to Home</a></body></html>");
        } catch (SQLException e) {
            out.write("<html><body>Error: " + e.getMessage() + "<br><a href='vehicle_management.html'>Back</a></body></html>");
        }
    }
}