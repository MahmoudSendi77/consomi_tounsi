package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "commands", "deliverys", "dons", "aisle", "events", "stocks", "cart", "ads", "abonnement",
		"abonnementDetail", "reporting", "polls", "users" })
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }),
		@UniqueConstraint(columnNames = { "email" }) })
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int salary;

	private String profileImage;

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public List<Poll> getPolls() {
		return polls;
	}

	public void setPolls(List<Poll> polls) {
		this.polls = polls;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	private int age;
	@Temporal(TemporalType.DATE)
	private Date datenaissance;

	@NotBlank
	@Size(max = 40)
	private String name;

	@NotBlank
	@Size(max = 15)
	private String username;

	@NaturalId
	@NotBlank
	@Size(max = 40)
	@Email
	private String email;

	@NotBlank
	@Size(max = 100)
	private String password;
	private boolean active;
	//
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Command> Commands;

	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)

	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	//
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)

	private List<Delivery> deliverys;
	//
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Don> dons;
	//
	@OneToOne(mappedBy = "user")

	private Aisle aisle;
	//
	@ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Event> events;
	//
	@ManyToOne
	private Stock stocks;
	//
	@OneToOne
	private Cart cart;
	//
	@OneToOne

	private Ads ads;
	//

	//

	private String idCustomer;

	private String idCard;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Abonnement abonnement;
	//

	@OneToOne(cascade = CascadeType.PERSIST)
	private AbonnementDetail abonnementDetail;

	@OneToOne(cascade = CascadeType.PERSIST)
	private Reporting reporting;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
	private List<Poll> polls;

	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<User> users;

	
	
	public String getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Date getDatenaissance() {
		return datenaissance;
	}

	public void setDatenaissance(Date datenaissance) {
		this.datenaissance = datenaissance;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public AbonnementDetail getAbonnementDetail() {
		return abonnementDetail;
	}

	public void setAbonnementDetail(AbonnementDetail abonnementDetail) {
		this.abonnementDetail = abonnementDetail;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Command> getCommands() {
		return Commands;
	}

	public void setCommands(List<Command> commands) {
		Commands = commands;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public List<Delivery> getDeliverys() {
		return deliverys;
	}

	public void setDeliverys(List<Delivery> deliverys) {
		this.deliverys = deliverys;
	}

	public List<Don> getDons() {
		return dons;
	}

	public void setDons(List<Don> dons) {
		this.dons = dons;
	}

	public Aisle getAisle() {
		return aisle;
	}

	public void setAisle(Aisle aisle) {
		this.aisle = aisle;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Stock getStocks() {
		return stocks;
	}

	public void setStocks(Stock stocks) {
		this.stocks = stocks;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Ads getAds() {
		return ads;
	}

	public void setAds(Ads ads) {
		this.ads = ads;
	}

	public Abonnement getAbonnement() {
		return abonnement;
	}

	public void setAbonnement(Abonnement abonnement) {
		this.abonnement = abonnement;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public User() {

	}

	public User(String name, String username, String email, String password, Date datenaissance) {
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.datenaissance = datenaissance;
	}

	public User(Long id, int salary, @NotBlank @Size(max = 40) String name, @NotBlank @Size(max = 15) String username,
			@NotBlank @Size(max = 40) @Email String email, @NotBlank @Size(max = 100) String password,
			List<Command> commands, Set<Role> roles, List<Delivery> deliverys, List<Don> dons, Aisle aisle,
			List<Event> events, Stock stocks, Cart cart, Ads ads, List<Product> products, Abonnement abonnement,
			AbonnementDetail abonnementDetail) {
		super();
		this.id = id;
		this.salary = salary;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		Commands = commands;
		this.roles = roles;
		this.deliverys = deliverys;
		this.dons = dons;
		this.aisle = aisle;
		this.events = events;
		this.stocks = stocks;
		this.cart = cart;
		this.ads = ads;

		this.abonnement = abonnement;
		this.abonnementDetail = abonnementDetail;
	}

	public User(Long id, int salary, @NotBlank @Size(max = 40) String name, @NotBlank @Size(max = 15) String username,
			@NotBlank @Size(max = 40) @Email String email, @NotBlank @Size(max = 100) String password,
			List<Command> commands, Set<Role> roles, List<Delivery> deliverys, List<Don> dons, Aisle aisle,
			List<Event> events, Stock stocks, Cart cart, Ads ads, List<Product> products, Abonnement abonnement) {
		super();
		this.id = id;

		this.salary = salary;

		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		Commands = commands;
		this.roles = roles;
		this.deliverys = deliverys;
		this.dons = dons;
		this.aisle = aisle;
		this.events = events;
		this.stocks = stocks;
		this.cart = cart;
		this.ads = ads;

		this.abonnement = abonnement;
	}

	public User(Long id, int salary, int age, Date datenaissance, @NotBlank @Size(max = 40) String name,
			@NotBlank @Size(max = 15) String username, @NotBlank @Size(max = 40) @Email String email,
			@NotBlank @Size(max = 100) String password, List<Command> commands, Set<Role> roles,
			List<Delivery> deliverys, List<Don> dons, Aisle aisle, List<Event> events, Stock stocks, Cart cart, Ads ads,
			Abonnement abonnement, AbonnementDetail abonnementDetail, Reporting reporting) {
		super();
		this.id = id;
		this.salary = salary;
		this.age = age;
		this.datenaissance = datenaissance;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		Commands = commands;
		this.roles = roles;
		this.deliverys = deliverys;
		this.dons = dons;
		this.aisle = aisle;
		this.events = events;
		this.stocks = stocks;
		this.cart = cart;
		this.ads = ads;
		this.abonnement = abonnement;
		this.abonnementDetail = abonnementDetail;
		this.reporting = reporting;
	}

	public User(Long id, int salary, int age, Date datenaissance, @NotBlank @Size(max = 40) String name,
			@NotBlank @Size(max = 15) String username, @NotBlank @Size(max = 40) @Email String email,
			@NotBlank @Size(max = 100) String password) {
		super();
		this.id = id;
		this.salary = salary;
		this.age = age;
		this.datenaissance = datenaissance;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Reporting getReporting() {
		return reporting;
	}

	public void setReporting(Reporting reporting) {
		this.reporting = reporting;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", salary=" + salary + ", age=" + age + ", datenaissance=" + datenaissance + ", name="
				+ name + ", username=" + username + ", email=" + email + ", password=" + password + ", active=" + active
				+ ", roles=" + roles + ", users=" + users + "]";
	}

	// @Override
	// public String toString() {
	// return "User [id=" + id + ", salary=" + salary + ", name=" + name + ",
	// username=" + username + ", email="
	// + email + ", password=" + password + ", abonnementDetail=" +
	// abonnementDetail + ", reporting="
	// + reporting + "]";
	// }

}