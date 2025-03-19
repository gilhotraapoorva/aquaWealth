<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Apply for Loan</title>
</head>
<body>
    <h2>Loan Application Form</h2>
    <form action="/loans/apply" method="post">

        <label>Full Name:</label>
        <input type="text" name="name" required><br>

        <label>Email:</label>
        <input type="email" name="email" required><br>

        <label>Government ID:</label>
        <input type="text" name="governmentId" required><br>

        <label>Loan Amount:</label>
        <input type="number" step="0.01" name="amount" required><br>

        <label>Collateral:</label>
        <select name="collateralDetails" id="collateralSelect" onchange="toggleCollateral()">
            <option value="">No</option>
            <option value="Farm Land">Yes</option>
        </select><br>

        <div id="collateralDetailsDiv" style="display: none;">
            <label>Collateral Details:</label>
            <input type="text" name="collateralDetails"><br>
        </div>

        <label>Loan Purpose:</label>
        <input type="text" name="purpose" required><br>

        <label>Term (Years):</label>
        <select name="termMonths">
            <option value="1">1 Year</option>
            <option value="2">2 Years</option>
            <option value="3">3 Years</option>
            <option value="4">4 Years</option>
            <option value="5">5 Years</option>
            <option value="6">6 Years</option>
            <option value="7">7 Years</option>
            <option value="8">8 Years</option>
            <option value="9">9 Years</option>
            <option value="10">10 Years</option>
        </select><br>

        <button type="submit">Apply</button>
    </form>

    <script>
        function toggleCollateral() {
            var collateralSelect = document.getElementById("collateralSelect");
            var collateralDetailsDiv = document.getElementById("collateralDetailsDiv");
            collateralDetailsDiv.style.display = collateralSelect.value ? "block" : "none";
        }
    </script>
</body>
</html>
