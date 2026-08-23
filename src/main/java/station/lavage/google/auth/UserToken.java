package station.lavage.google.auth;

import java.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UserToken {
	private String sub;
	private String name;
	private String given_name;
	private String family_name;
	private String email;
	private String picture;
	private String exp;
    
	//operation encour
	private String operation_encoure;
	//token jwt apps
	private String token_jwt;
	
	
	
	
	public  UserToken() {
		
	}
    public static UserToken  getUserToken(String token) {
    	System.out.println(token);
		String body64=token.split("\\.")[1];
		String body=new String(Base64.getDecoder().decode(body64));
		System.out.println(body);
		GsonBuilder builder = new GsonBuilder();
	      builder.setPrettyPrinting();
	      Gson gson = builder.create();
	      UserToken user = gson.fromJson(body, UserToken.class);
	      return user;
	}
    
	public String getOperation_encoure() {
		return operation_encoure;
	}
	public void setOperation_encoure(String operation_encoure) {
		this.operation_encoure = operation_encoure;
	}
	public String getToken_jwt() {
		return token_jwt;
	}
	public void setToken_jwt(String token_jwt) {
		this.token_jwt = token_jwt;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGiven_name() {
		return given_name;
	}
	public void setGiven_name(String given_name) {
		this.given_name = given_name;
	}
	public String getFamily_name() {
		return family_name;
	}
	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	@Override
	public String toString() {
		return "UserToken [sub=" + sub + ", name=" + name + ", given_name=" + given_name + ", family_name="
				+ family_name + ", email=" + email + ", picture=" + picture + ", exp=" + exp + ", operation_encoure="
				+ operation_encoure + ", token_jwt=" + token_jwt + "]";
	}
	
	
	
	
	
    
   
}
