package org.springfield.lou.application.types;

import org.springfield.lou.application.Html5Application;
import org.springfield.lou.euscreen.config.Config;
import org.springfield.lou.euscreen.config.ConfigEnvironment;
import org.springfield.lou.euscreen.config.SettingNotExistException;
import org.springfield.lou.screen.Screen;

public class EuscreenxlstaticpagesApplication extends Html5Application{
	
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
 		this.loadContent(s, "helppage", "helppage");
 		this.loadGeneric(s);
 		this.loadStyleSheet(s, "help");
 	}

}
