package tn.esprit.spring.control;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name="login")
@RequestScoped
public class LoginBean implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
    private String password;
    
    @PostConstruct
    public void init(){
        username = "";
        password = "";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void access(){
        String dummy_user = "rahma";
        String dummy_pass = "123";
        FacesContext context = FacesContext.getCurrentInstance();
        //I use JDBC PostgreSQL driver for find the users in table "users"
        if (username.equals(dummy_user) && password.equals(dummy_pass)) {
            //Here you must put your code to redirect or do something 
            context.addMessage(null, new FacesMessage("Successful login",  "You are logged in") );
        }
        else{
            context.addMessage(null, new FacesMessage("Wrong access",  "Use rahma and 123") );
        }
    }
}