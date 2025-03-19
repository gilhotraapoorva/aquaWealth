<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Loan Application Success</title>
</head>
<body>
    <h2>Loan Application Submitted Successfully!</h2>

    <p><strong>Loan ID:</strong> ${loan.loanId}</p>
    <p><strong>Applicant Name:</strong> ${loan.user.name}</p>
    <p><strong>Government ID:</strong> ${loan.user.governmentId}</p>
    <p><strong>Amount:</strong> ${loan.amount}</p>
    <p><strong>Interest Rate:</strong> ${loan.interestRate}%</p>
    <p><strong>Term:</strong> ${loan.termMonths} months</p>
    <p><strong>Status:</strong> Under Review</p>

    <a href="/loans/apply">Apply for a New Loan</a> |
    <a href="/">Back to Home</a>
</body>
</html>
