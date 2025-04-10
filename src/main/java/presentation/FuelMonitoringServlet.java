package presentation;

import business.VehicleService;
import business.FuelConsumption;
import business.User;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;
import java.util.List;
/**
 * Servlet for handling fuel monitoring requests.
 * @author Aryan
 */
public class FuelMonitoringServlet extends HttpServlet {
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
        double consumption = Double.parseDouble(request.getParameter("consumption"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            vehicleService.logFuelConsumption(vehicleId, consumption);
            out.write("<html><body>Fuel data logged successfully<br><a href='fuel_monitoring.html'>Back</a></body></html>");
        } catch (SQLException e) {
            out.write("<html><body>Error: " + e.getMessage() + "<br><a href='fuel_monitoring.html'>Back</a></body></html>");
        }
    }

    /**
     * Handles GET requests to display fuel consumption data.
     * @param request  the HTTP request
     * @param response the HTTP response
     * @throws javax.servlet.ServletException
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            List<FuelConsumption> report = vehicleService.getFuelReport(vehicleId);
            out.write("<html><body><h2>Fuel Report for Vehicle " + vehicleId + "</h2><table border='1'>");
            out.write("<tr><th>Date</th><th>Consumption</th></tr>");
            for (FuelConsumption data : report) {
                out.write("<tr><td>" + data.getDate() + "</td><td>" + data.getConsumption() + "</td></tr>");
            }
            out.write("</table><br><a href='index.html'>Back to Home</a></body></html>");
        } catch (SQLException e) {
            out.write("<html><body>Error: " + e.getMessage() + "<br><a href='index.html'>Back</a></body></html>");
        }
    }
}