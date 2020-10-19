package tn.esprit.spring.security;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import tn.esprit.spring.control.Profile;
import tn.esprit.spring.control.SigninJsf;
import tn.esprit.spring.service.UserserviceInterface;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;

	@Autowired
	SigninJsf SigninJsf;
	@Autowired
	UserserviceInterface UserserviceInterface;
    
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    	String str = "abc,def,ghi";
    	String search = "df";
    	//System.err.println(str.indexOf(search));
   	if (request.getRequestURL().indexOf("http://localhost:8081/comfirme")!= -1)
   		
   	{
    		System.err.println(request.getRequestURL().indexOf("http://localhost:8081/comfirme"));
    	
    		

    	System.err.println("lien eli 7achty bih " +request.getRequestURL()); 
    	System.err.println("request.getRequestURL().lastIndexOf " +	request.getRequestURL().substring(request.getRequestURL().lastIndexOf("/")+1));
    	System.err.println("lien  " +request.getRequestURL().substring(0,30));
    	
    	    if (UserserviceInterface.finduserbycode(request.getRequestURL().substring(request.getRequestURL().lastIndexOf("/")+1)) == null)
    	  
    	    	
    	    	
    	    	System.err.println("existe pas");
    		

    	
    	    else
    	    {
    	    	
    	    	System.err.println("existe");
    	    	UserserviceInterface.confirmation(request.getRequestURL().substring(request.getRequestURL().lastIndexOf("/")+1));
    	   
    	    	
    	    	
    	    	
    	    	//    	    	
//    	    	
    	    	 String page = "";
    	 	    try {

    	 	    } catch (Exception e) {
    	 	      page = "/login/confirmation.jsf";
    	 	    } finally {
    	 	      page = "/login/confirmation.jsf";
    	 	    }

    	 	RequestDispatcher dd=request.getRequestDispatcher(page);
    	 	dd.forward(request, response);
//    	    	
    	    	
//    	    	response.sendRedirect("http://localhost:8081/login");
    	    	
//    	    	FacesMessage facesMessage =
//
//						new FacesMessage("Login Failed: please check your username/password and try again.");
//				FacesContext.getCurrentInstance().addMessage("form:btn", facesMessage);
    	    	
    	    	
    	    //	System.err.println(UserserviceInterface.finduserbycode(request.getRequestURL().substring(request.getRequestURL().lastIndexOf("/")+1)));
    	//    UserserviceInterface.confirmation(request.getRequestURL().substring(request.getRequestURL().lastIndexOf("/")+1));}
    	}}
   	
   	
   	
	if (request.getRequestURL().indexOf("http://localhost:8081/mycode")!= -1)
	{
		
		
		
		
		
		
		
		
		SigninJsf.setCodesignup(request.getRequestURL().substring(request.getRequestURL().lastIndexOf("/")+1 ));
		
		
		
		
		
		
		
	    String page = "";
	    try {

	    } catch (Exception e) {
	      page = "/login/templatelogin.jsf";
	    } finally {
	      page = "/login/templatelogin.jsf";
	    }

	RequestDispatcher dd=request.getRequestDispatcher(page);
	dd.forward(request, response);
		
		
//		filterChain.doFilter(request, response);
//	 	response.sendRedirect("http://localhost:8081/login");
//	SigninJsf.rnder();
//		response.sendRedirect("http://localhost:8082/login/templatelogin.xhtml");
//	
	
	}
   	
   	
//
//    	}
//    	
//    if (UserserviceInterface.finduserbycode(request.getRequestURL().lastIndexOf("/")) == null)
//    		
//
//    	 UserserviceInterface.confirmation(code);
//
//    	}
    	
    	try {
    		

    		
 
    		 
    		 
    		
    		String jwt = this.getJwtFromRequest(request,response);
    	

    		 
    		
    		 
    		 
            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                Long userId = tokenProvider.getUserIdFromJWT(jwt);

                /*
                    Note that you could also encode the user's username and roles inside JWT claims
                    and create the UserDetails object by parsing those claims from the JWT.
                    That would avoid the following database hit. It's completely up to you.
                 */
                UserDetails userDetails = customUserDetailsService.loadUserById(userId);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


               
               
              
              
                
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);
    }
    
    
    private Cookie createCookie(final String content, final int expirationTimeSeconds) {
        final Cookie cookie = new Cookie("token", content);
        cookie.setMaxAge(expirationTimeSeconds);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        return cookie;
      }
    

   
    
    private String getJwtFromRequest(HttpServletRequest request,HttpServletResponse response) {
  
    	
    	  String bearerToken = null ;
    	
    	
    	 
    	 
    	 if (SigninJsf.getToken() != null)
       	 {
    		 
    		 
    			 System.err.println("i3ammer fe cookies");
       		 
       		 System.err.println("aaa "+SigninJsf.getToken());
       	final Cookie cookieToken = createCookie(SigninJsf.getToken(), 5000);
       	
        
//    	ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
//		HttpServletRequest request1 = (HttpServletRequest) ex.getRequest();
//
//		HttpServletResponse response1 = (HttpServletResponse) ex.getResponse();
//		
//		
//		response1.addCookie(cookieToken);
		
		
		
        response.addCookie(cookieToken);
     
        
        System.err.println(SigninJsf.getToken()+  "qqqqqqqqqqqqqqqqqqqqqqqqqqqqdsqdqsdqsdqsdqsd");
        
        
        
        
        
        
        bearerToken = SigninJsf.getToken();
     //   bearerToken = tn.esprit.spring.control.SigninJsf.token1;

        SigninJsf.setToken(null);
        
       	
        
       
		
		
        
       	 }
    	 else{
    		 
    	
        	 Cookie[] cookies = request.getCookies(); 
        	 if (cookies == null)
        			System.err.println("true");
        String token =	 Arrays.stream(cookies)
             .filter(cookie -> cookie.getName().equals("token"))
             .findFirst()
             .map(Cookie::getValue).orElse(null);

        bearerToken =  token ; 
       
       
    		
    		 
    	 }
        	
    	 
    	 
    	 
         
//         try {
//			TimeUnit.SECONDS.sleep(2);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//         
//         
    	 
    	 
//         final Cookie cookieToken = createCookie(AuthController.token, 500);
//         response.addCookie(cookieToken);
    	
    	
    	
    	
    
    	 
    
    
    
    	 
//    	 if (token == null && AuthController.token.equals("")== false)
//    	 {
//    	 final Cookie cookieToken = createCookie(AuthController.token, 500);
//         response.addCookie(cookieToken);
//         AuthController.token =  "";
//         token = AuthController.token;
//    	 }
    	 
    
    	 
    
  
    	
// System.out.println("berer tokeb   " + bearerToken);
  
        if (StringUtils.hasText(bearerToken) /*&& bearerToken.startsWith("Bearer ")*/) {
        	

        	
            return bearerToken;
            
            
            }
     
        return null;
    }
}
