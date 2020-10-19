//package tn.esprit.spring.control;
//
//import java.io.Serializable;
//import java.security.AuthProvider;
//import java.util.Map;
//import java.util.Properties;
//
//import javax.faces.bean.SessionScoped;
//import javax.faces.context.ExternalContext;
//import javax.faces.context.FacesContext;
//import javax.inject.Named;
//import javax.servlet.http.HttpServletRequest;
//
//
//import org.ocpsoft.rewrite.el.ELBeanName;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Controller;
//
//
//
//
//
//@Named
//@SessionScoped
//@Scope(value = "session")
//@Controller(value = "usuarioBean")
//@ELBeanName(value = "usuarioBean")
////@Join(path = "/servlet/jsf/", to = "/home.jsf")
//
//public class UsuarioBean implements Serializable {
//
//public String getCode() {
//		return code;
//	}
//
//	public void setCode(String code) {
//		this.code = code;
//	}
//
//public String code ;
//	private static final long serialVersionUID = 3658300628580536494L;
//	
//	//private SocialAuthManager socialManager;
//	private Profile profile;
//
//	private final String mainURL = "http://localhost:8081/SpringMVC/home.xhtml";
//	private final String redirectURL = "http://localhost:8081/SpringMVC/redirectHome.xhtml";
//private final String trt = "http://localhost:8081/SpringMVC/redirectHome.xhtml?code=AQCOaFIM8ur5PDrWxq471VQlxduo2DL4zNwYaoxVm7W4FnAADHJ48XParg4h_TWgdkTtognwTSA3Ldgoq-HE3MgNiJCorOm0g0ZIUcqa0Nb6B_OvNCaKSWgUN8XgHg1RSV_3aV-2MKB_Xt_3KeJg69jb-9DvskGiiPS0hUHWn50-04QWbsxo9QENSEe-_yZIgMB6nEIzdDDZfBbBXBhJwb7Jx6ZW1IvUoyAQkSQiLpk3bj_wJrdsp5oerAmPfgt3U4wBtTYzXcXkIKSkVw3gAQmyrVwnl2TYT71wcq7P9ypwVSQvtFT7rGFLjxVv5H_L6hfxG-b90wY8TxXZaGyBwpGh#_=_";
//	private final String provider = "facebook";
//
//	public void conectar() {
//		String navigateTo = "null";
//		Properties prop = System.getProperties();
//		prop.put("graph.facebook.com.consumer_key", "251264439388941");
//		prop.put("graph.facebook.com.consumer_secret", "3fc8e5766692da01d96e14c548842184");
//		prop.put("graph.facebook.com.custom_permissions", "public_profile,email");
//
//		SocialAuthConfig socialConfig = SocialAuthConfig.getDefault();
//		try {
//			socialConfig.load(prop);
//			socialManager = new SocialAuthManager();
//			socialManager.setSocialAuthConfig(socialConfig);
//			
//			String URLRetorno = socialManager.getAuthenticationUrl(provider, redirectURL);
//			
////			System.err.println(provider);
//		System.err.println(provider);
//			FacesContext.getCurrentInstance().getExternalContext().redirect(URLRetorno);
//			System.err.println();
//
//			System.err.println("vide");
//		
//			ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
//			HttpServletRequest request = (HttpServletRequest) ex.getRequest();
////			request = (HttpServletRequest) 
////			System.err.println(request);
//		//	FacesContext.getCurrentInstance().getExternalContext().redirect(mainURL);
//			//this.getPerfilUsuario();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//	
//	
//	
//	
//	
//	public String getPerfilUsuario() throws Exception {
//		System.err.println("vide");
//		ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
//		HttpServletRequest request = (HttpServletRequest) ex.getRequest();
//
//		Map<String, String> parametros = SocialAuthUtil.getRequestParametersMap(request);
//		System.err.println("vide");
//		if (socialManager != null) {
//			AuthProvider provider = socialManager.connect(parametros);
//			this.setProfile(provider.getUserProfile());
//			System.err.println(provider);
//
//		}
//return provider.toString();
//	//	FacesContext.getCurrentInstance().getExternalContext().redirect(mainURL);
//	}
//
//	public Profile getProfile() {
//		return profile;
//	}
//
//	public void setProfile(Profile profile) {
//		this.profile = profile;
//	}
//
//}
