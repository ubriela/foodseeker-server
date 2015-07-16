<%-- 
    Document   : success
    Created on : 05-10-2011, 01:04:09
    Author     : ubriela
--%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>


<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style type="text/css" href="stylesheet.css"></style>
    <title>Login Success</title>
</head>
<body>
    <h1>Congratulations!</h1>

    <p>You have successfully logged in.</p>

    <p>Your name is: <bean:write name="LoginForm" property="name" />.</p>

    <p>Your email address is: <bean:write name="LoginForm" property="email" />.</p>
</body>
