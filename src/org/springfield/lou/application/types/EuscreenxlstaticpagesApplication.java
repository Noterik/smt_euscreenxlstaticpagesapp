package org.springfield.lou.application.types;

import org.springfield.lou.application.Html5Application;
import org.springfield.lou.euscreen.config.Config;
import org.springfield.lou.euscreen.config.ConfigEnvironment;
import org.springfield.lou.euscreen.config.SettingNotExistException;
import org.springfield.lou.screen.Screen;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class EuscreenxlstaticpagesApplication extends Html5Application{
	public String ipAddress="";
	public static boolean isAndroid = false;
	private Config config;
	
 	public EuscreenxlstaticpagesApplication(String id) {
		super(id); 
		
		try{
			if(this.inDevelopemntMode()){
				config = new Config(ConfigEnvironment.DEVEL);
			}else{
				config = new Config(ConfigEnvironment.PROD);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		this.addReferid("mobilenav", "/euscreenxlelements/mobilenav");
		this.addReferid("header", "/euscreenxlelements/header");
		this.addReferid("footer", "/euscreenxlelements/footer");
		this.addReferid("linkinterceptor", "/euscreenxlelements/linkinterceptor");
		this.addReferid("config", "/euscreenxlelements/config");
		this.addReferid("urltransformer", "/euscreenxlelements/urltransformer");
		
		this.addReferidCSS("fontawesome", "/euscreenxlelements/fontawesome");
		this.addReferidCSS("bootstrap", "/euscreenxlelements/bootstrap");
		this.addReferidCSS("theme", "/euscreenxlelements/theme");
		this.addReferidCSS("genericadditions", "/euscreenxlelements/generic");
		this.addReferidCSS("all", "/euscreenxlelements/all");
		
	}
 	
 	private void loadGeneric(Screen s){
		this.loadStyleSheet(s, "fontawesome");
 		this.loadStyleSheet(s, "bootstrap");
 		this.loadStyleSheet(s, "theme");
 		this.loadStyleSheet(s, "genericadditions");
 		this.loadStyleSheet(s, "all");
 		
 		this.loadContent(s,"header", "header");
 		this.loadContent(s,"footer", "footer");
 		this.loadContent(s,"mobilenav", "mobilenav");
 		this.loadContent(s,"linkinterceptor","linkinterceptor");
 		
 		if(config != null){
 			this.loadContent(s, "config", "config");
 			this.loadContent(s, "urltransformer", "urltransformer");
 			
 			try {
				s.putMsg("config", "", "update(" + config.getSettingsJSON() + ")");
			} catch (SettingNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 		}
 		
 	}
 	
	public void init(Screen s){
		String page = s.getParameter("page");
		System.out.println("INIT EUSCREEN STATIC PAGES APPLICATION(" + page + ")");
		System.out.println("LOAD PAGE: " + page);
		if(page.equals("contact")){
			loadContact(s);
		}else if(page.equals("terms")){
			loadTerms(s);
		}else if(page.equals("privacy")){
			loadPrivacy(s);
		}
		else if(page.equals("help")){
			loadHelp(s);
		}
	}
 	
 	public void loadContact(Screen s){
 		this.loadContent(s,"contactpage","contactpage");
 		this.loadGeneric(s);
 		this.loadStyleSheet(s,"contact");
 	}
 	
 	public void loadTerms(Screen s){
 		this.loadContent(s,"termspage","termspage");
 		this.loadGeneric(s);
 		this.loadStyleSheet(s,"terms");
 	}
 	
 	public void loadPrivacy(Screen s){
 		this.loadContent(s, "privacypage", "privacypage");
 		this.loadGeneric(s);
 		this.loadStyleSheet(s, "privacy");
 	}
 	
 	public void loadHelp(Screen s){
 		System.out.println("IF MOBILE: " + isAndroid);
 		if(isAndroid == false){
 			this.loadContent(s, "helppage", "helppage");
 		}else {
 			this.loadContent(s, "helppagemobilevideos", "helppagemobilevideos");
 		}
 		this.loadGeneric(s);
 		this.loadStyleSheet(s, "help");
 	}
 	
 	public String getMetaHeaders(HttpServletRequest request) {

		ipAddress=getClientIpAddress(request);
				
		String browserType = request.getHeader("User-Agent");
		
		System.out.println("===============");
		System.out.println(browserType);
		if(browserType.indexOf("Mobile") != -1 ) {
			String ua = request.getHeader("User-Agent").toLowerCase();
			isAndroid = ua.indexOf("android") > -1; //&& ua.indexOf("mobile");
			
			if(!isAndroid){
				isAndroid = browserType.indexOf("iPhone") > -1;
			}
			
		}else{
			isAndroid = false;
		}
		return "";
	}
 	
	private static final String[] HEADERS_TO_TRY = { 
			"X-Forwarded-For",
			"Proxy-Client-IP",
			"WL-Proxy-Client-IP",
			"HTTP_X_FORWARDED_FOR",
			"HTTP_X_FORWARDED",
			"HTTP_X_CLUSTER_CLIENT_IP",
			"HTTP_CLIENT_IP",
			"HTTP_FORWARDED_FOR",
			"HTTP_FORWARDED",
			"HTTP_VIA",
			"REMOTE_ADDR" };
			
		public static String getClientIpAddress(HttpServletRequest request) {
			for (String header : HEADERS_TO_TRY) {
			String ip = request.getHeader(header);
				if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
					return ip;
				}
			}
			return request.getRemoteAddr();
		}

}
