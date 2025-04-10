package presentation;

import business.UserService;
import business.User;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;
/**
 * Servlet for handling user authentication.
 * @author Anjalika
 */
public class UserServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        try {
            userService = new UserService();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new ServletException("Failed to initialize UserService", e);
        }
    }

    /**
     * Handles POST requests for user login.
     * @param request  the HTTP request
     * @param response the HTTP response
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if ("login".equals(action)) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            try {
                User user = userService.authenticate(email, password);
                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    response.sendRedirect("dashboard.html");
                } else {
                    out.write("<html><body>Invalid credentials<br><a href='index.html'>Back</a></body></html>");
                }
            } catch (SQLException e) {
                out.write("<html><body>Error: " + e.getMessage() + "<br><a href='index.html'>Back</a></body></html>");
            }
        } else if ("register".equals(action)) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
            try {
                userService.registerUser(name, email, password, role);
                response.sendRedirect("login.html");
            } catch (SQLException e) {
                out.write("<html><body>Error: " + e.getMessage() + "<br><a href='register.html'>Back</a></body></html>");
            }
        }
    }
}