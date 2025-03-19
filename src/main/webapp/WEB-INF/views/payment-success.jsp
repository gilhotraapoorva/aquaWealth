<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Payment Success</title>
</head>
<body>
    <h2>Payment Successful!</h2>
    <p>Payment ID: ${payment.paymentId}</p>
    <p>Loan ID: ${payment.loan.loanId}</p>
    <p>Amount Paid: ${payment.amount}</p>
    <p>Payment Type: ${payment.paymentType}</p>
    <p>Payment Date: ${payment.paymentDate}</p>
</body>
</html>
