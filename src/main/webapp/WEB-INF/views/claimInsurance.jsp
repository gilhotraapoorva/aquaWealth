<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Claim Insurance</title>
    <script>
        function submitClaim(event) {
            event.preventDefault(); // Prevents default form submission

            let governmentId = document.getElementById("governmentId").value.trim();
            let city = document.getElementById("city").value.trim();
            let date = document.getElementById("date").value;
            let claimAmount = parseFloat(document.getElementById("claimAmount").value); //  Ensure it's a number

            if (!governmentId || !city || !date || isNaN(claimAmount)) {
                alert("Please enter all required fields.");
                return;
            }

            let claimData = JSON.stringify({
                governmentId: governmentId,
                city: city,
                date: date,
                claimAmount: claimAmount
            });

         fetch("<%= request.getContextPath() %>/insurance/claim", {
             method: "POST",
             headers: { "Content-Type": "application/json" },
             body: claimData
         })
         .then(response => response.json()) 
         .then(data => {
             if (data.message) {
                 alert(data.message);
             } else if (data.error) {
                 alert("Error: " + data.error);
             }
         })
         .catch(error => {
             console.error("Error:", error);
             alert("An error occurred: " + error.message);
         });

        }
    </script>
</head>
<body>
    <h2>Claim Insurance</h2>

    <form onsubmit="submitClaim(event)">
        <label for="governmentId">Government ID:</label>
        <input type="text" id="governmentId" name="governmentId" required><br><br>

        <label for="city">City:</label>
        <input type="text" id="city" name="city" required><br><br>

        <label for="date">Date:</label>
        <input type="date" id="date" name="date" required><br><br>

        <label for="claimAmount">Claim Amount:</label>
        <input type="number" id="claimAmount" name="claimAmount" required><br><br>

        <button type="submit">Submit Claim</button>
    </form>

    <h3 id="result"></h3>
</body>
</html>
