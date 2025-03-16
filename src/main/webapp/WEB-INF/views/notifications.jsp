<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Weather Notifications</title>
</head>
<body>
    <h2>Weather Alerts</h2>
    <p>Get real-time weather notifications for your insured property.</p>
    <form action="/weather-alerts" method="get">
        <label>Enter Location:</label>
        <input type="text" name="location" required><br>
        <input type="submit" value="Check Weather Alerts">
    </form>
</body>
</html>
