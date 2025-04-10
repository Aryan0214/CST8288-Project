<!-- src/main/webapp/gps_tracking.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><title>GPS Tracking</title></head>
<body>
    <c:if test="${not empty message}">
        <p style="color: green">${message}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p style="color: red">${error}</p>
    </c:if>

    <c:if test="${sessionScope.user.role == 'Manager'}">
        <h2>GPS Tracking Report</h2>
        <table border="1">
            <tr><th>Vehicle ID</th><th>Timestamp</th><th>Station</th><th>Status</th></tr>
            <c:forEach var="data" items="${gpsData}">
                <tr>
                    <td>${data.vehicleId}</td>
                    <td>${data.timestamp}</td>
                    <td>${data.location}</td>
                    <td>${data.status}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <h2>Log GPS Data</h2>
    <form action="gps" method="post">
        Vehicle ID: <input type="number" name="vehicleId" required><br>
        Station: <input type="text" name="location" required><br>
        Status: <select name="status">
            <option value="Active">Active</option>
            <option value="Break">Break</option>
            <option value="Out-of-Service">Out-of-Service</option>
        </select><br>
        <input type="submit" value="Log GPS Data">
    </form>
    <a href="dashboard.html">Back to Dashboard</a>
</body>
</html>