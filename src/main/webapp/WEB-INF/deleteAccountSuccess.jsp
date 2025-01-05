<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Account Deleted</title>
</head>
<body>
    <h1>Your account has been successfully deleted.</h1>
    <p>You will be redirected to the home page in 3 seconds.</p>

    <script>
        setTimeout(function() {
            window.location.href = "index.html"; 
        }, 3000); 
    </script>
</body>
</html>