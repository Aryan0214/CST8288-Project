package presentation;

import business.VehicleService;
import business.MaintenanceRecord;
import business.User;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;
import java.util.List;

public class MaintenanceServlet extends HttpServlet {
    private VehicleService vehicleService;

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
        if (user == null || !"Manager".equals(user.getRole())) {
            response.sendRedirect("login.html");
            return;
        }

        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        String component = request.getParameter("component");
        double wearLevel = Double.parseDouble(request.getParameter("wearLevel"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            vehicleService.logMaintenance(vehicleId, component, wearLevel);
            out.write("<html><body>Maintenance logged successfully<br><a href='maintenance.html'>Back</a></body></html>");
        } catch (SQLException e) {
            out.write("<html><body>Error: " + e.getMessage() + "<br><a href='maintenance.html'>Back</a></body></html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            List<MaintenanceRecord> report = vehicleService.getMaintenanceReport(vehicleId);
            out.write("<html><body><h2>Maintenance Report for Vehicle " + vehicleId + "</h2><table border='1'>");
            out.write("<tr><th>Component</th><th>Wear Level</th><th>Last Maintenance</th></tr>");
            for (MaintenanceRecord record : report) {
                out.write("<tr><td>" + record.getComponent() + "</td><td>" + record.getWearLevel() + "</td><td>" + record.getLastMaintenance() + "</td></tr>");
            }
            out.write("</table><br><a href='index.html'>Back to Home</a></body></html>");
        } catch (SQLException e) {
            out.write("<html><body>Error: " + e.getMessage() + "<br><a href='index.html'>Back</a></body></html>");
        }
    }
}