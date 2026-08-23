<%@page import="com.google.api.client.googleapis.auth.oauth2.GoogleCredential"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.File"%>
<%@page import="com.google.api.client.auth.oauth2.TokenResponse"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="com.google.api.client.json.JsonFactory"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets"%>
<%@page import="com.google.api.client.http.HttpTransport"%>
<%@page import="com.google.api.client.json.gson.GsonFactory"%>
<%@page import="com.google.api.client.http.javanet.NetHttpTransport"%>
<%@page import="com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest"%>
<%@page import="com.google.api.client.auth.oauth2.TokenResponseException"%>
<%@page import="com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse"%>
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
    try {
    	String code=(String) request.getParameter("code");
        GoogleTokenResponse respons = new GoogleAuthorizationCodeTokenRequest(
            new NetHttpTransport(), new GsonFactory(),
            "550723132061-vb5fjtdh1h0dk5315fovli2vqu92area.apps.googleusercontent.com", "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",
            code, "http://localhost:8080/jsp/redirectGoogle.jsp")
            .execute();
        
        System.out.println("Access token: "+respons.toPrettyString());
        response.sendRedirect("http://localhost:4200/ConnectionGoogleSuccesComponent?code="+respons.getIdToken());
      } catch (TokenResponseException e) {
        if (e.getDetails() != null) {
          System.err.println("Error: " + e.getDetails().getError());
          if (e.getDetails().getErrorDescription() != null) {
            System.err.println(e.getDetails().getErrorDescription());
          }
          if (e.getDetails().getErrorUri() != null) {
            System.err.println(e.getDetails().getErrorUri());
          }
        } else {
          System.err.println(e.getMessage());
        }
      }
    %>
</body>
</html>