<%@page import="java.util.Arrays"%>
<%@page import="com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%
    String url = new GoogleAuthorizationCodeRequestUrl(
            "550723132061-vb5fjtdh1h0dk5315fovli2vqu92area.apps.googleusercontent.com",
            "http://localhost:8080/jsp/redirectGoogle.jsp",
            Arrays.asList("https://www.googleapis.com/auth/userinfo.email",
                    "https://www.googleapis.com/auth/userinfo.profile"))
            .setState("/profile").build();
       response.sendRedirect(url);
    %>
</body>
</html>