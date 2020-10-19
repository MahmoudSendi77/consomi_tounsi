package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sun.istack.NotNull;

@Entity
@Table(name = "Delivery")
public class Delivery implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Delivery_Id")
	private Long id;
	
	
    @Column(name = "delivery_price")
    private Double price;


   
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date; 
    
   
    @Column(name = "status")
    private String status;
	
   
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy="delvey")
	private List<Command> Commands;

    @ManyToOne
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Command> getCommands() {
		return Commands;
	}

	public void setCommands(List<Command> commands) {
		Commands = commands;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Delivery(Long id, Double price, Date date, String status, String description, List<Command> commands,
			User user) {
		super();
		this.id = id;
		this.price = price;
		this.date = date;
		this.status = status;
		this.description = description;
		Commands = commands;
		this.user = user;
	}

	public Delivery(Double price, Date date, String status, String description, List<Command> commands, User user) {
		super();
		this.price = price;
		this.date = date;
		this.status = status;
		this.description = description;
		Commands = commands;
		this.user = user;
	}

	public Delivery() {
		super();
	}

	@Override
	public String toString() {
		return "Delivery [id=" + id + ", price=" + price + ", date=" + date + ", status=" + status + ", description="
				+ description + ", Commands=" + Commands + ", user=" + user + "]";
	}
    
    
    
    
}
