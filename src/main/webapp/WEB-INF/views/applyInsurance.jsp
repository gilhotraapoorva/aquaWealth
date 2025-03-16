<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Apply for Insurance</title>
    <script>
        function updatePremium() {
            let coverageAmount = document.getElementById("coverageAmount").value;
            if (coverageAmount) {
                document.getElementById("premiumAmount").value = coverageAmount / 30;
            }
        }

        function updateEndDate() {
            let startDate = document.getElementById("startDate").value;
            if (startDate) {
                let start = new Date(startDate);
                start.setFullYear(start.getFullYear() + 1); // Add 1 year
                document.getElementById("endDate").value = start.toISOString().split('T')[0];
            }
        }

        function submitForm(event) {
            event.preventDefault();

            let formData = {
                user: {
                    name: document.getElementById("userName").value,
                    email: document.getElementById("userEmail").value // ✅ No userId required!
                },
                governmentId: document.getElementById("governmentId").value,
                coverageType: document.getElementById("coverageType").value,
                coverageAmount: document.getElementById("coverageAmount").value,
                premiumAmount: document.getElementById("premiumAmount").value,
                startDate: document.getElementById("startDate").value,
                endDate: document.getElementById("endDate").value,
                status: "Active"
            };

            fetch("<%= request.getContextPath() %>/insurance/apply", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(formData)
            })
            .then(response => response.json())
            .then(data => {
                alert("Insurance Policy Applied Successfully!");
                console.log(data);
            })
            .catch(error => {
                console.error("Error:", error);
                alert("Failed to apply for insurance.");
            });
        }
    </script>
</head>
<body>

    <h2>Apply for Insurance</h2>

    <form onsubmit="submitForm(event)">
        <label>Government ID:</label>
        <input type="text" id="governmentId" required>
        <br>

        <label>User Name:</label>
        <input type="text" id="userName" required>
        <br>

        <label>User Email:</label>
        <input type="email" id="userEmail" required>
        <br>

        <label>Coverage Type:</label>
        <select id="coverageType" required>
            <option value="Basic">Basic</option>
            <option value="Premium">Premium</option>
        </select>
        <br>

        <label>Coverage Amount:</label>
        <select id="coverageAmount" onchange="updatePremium()" required>
            <option value="100000">1L</option>
            <option value="200000">2L</option>
            <option value="300000">3L</option>
            <option value="400000">4L</option>
            <option value="500000">5L</option>
            <option value="600000">6L</option>
            <option value="700000">7L</option>
            <option value="800000">8L</option>
            <option value="900000">9L</option>
            <option value="1000000">10L</option>
        </select>
        <br>

        <label>Premium Amount:</label>
        <input type="number" id="premiumAmount" readonly required>
        <br>

        <label>Start Date:</label>
        <input type="date" id="startDate" onchange="updateEndDate()" required>
        <br>

        <label>End Date:</label>
        <input type="date" id="endDate" readonly required>
        <br>

        <button type="submit">Submit</button>
    </form>

</body>
</html>
