<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Make Loan Payment</title>
</head>
<body>
    <h2>Make a Loan Payment</h2>

    <form action="process-payment" method="post">
        <label>Loan ID:</label>
        <input type="number" name="loanId" required><br>

        <label>Payment Amount:</label>
        <input type="number" step="0.01" name="amount" required><br>

        <label>Payment Type:</label>
        <select name="paymentType" required>
            <option value="Bank Transfer">Bank Transfer</option>
            <option value="Credit Card">Credit Card</option>
            <option value="Cash">Cash</option>
        </select><br>

        <button type="submit">Submit Payment</button>
    </form>
</body>
</html>
