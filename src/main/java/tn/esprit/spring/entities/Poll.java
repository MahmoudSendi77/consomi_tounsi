package tn.esprit.spring.entities;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;


@Entity

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Poll implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "poll")
    private List<Choix> options;

    private String title;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;

    @ElementCollection
    @JsonIgnore
    private List<String> ipAdresses;

    private Boolean visible;

    public Long getId() {
        return id;
    }

    public Poll() {
    }

 //   this annotation takes care of only returning user's id as json DTO
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    private User user;

    public Poll(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Choix> getOptions() {
        return options;
    }

    public void setOptions(List<Choix> options) {
        this.options = options;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<String> getIpAdresses() {
        return ipAdresses;
    }

    public void setIpAdresses(List<String> ipAdresses) {
        this.ipAdresses = ipAdresses;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

	public Poll(List<Choix> options, String title, Date endDate, Boolean visible) {
		super();
		this.options = options;
		this.title = title;
		this.endDate = endDate;
		this.visible = visible;
	}
    
    
    

}