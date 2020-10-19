package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity 
public class Comment implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_c;

	private String content_c;
	private boolean useful ;
	private Long replyCount ;
	private String image ;
	
	@Temporal(TemporalType.DATE) 
	private Date creationDate;
	
	@ManyToOne
	User user ;
	
	@ManyToOne
	Topic topic ;

	 @OneToMany(cascade = CascadeType.ALL, mappedBy="comment")
	    private List<Reply> replies ;
	

	public Long getId_c() {
		return id_c;
	}

	public void setId_c(Long id_c) {
		this.id_c = id_c;
	}

	public String getContent_c() {
		return content_c;
	}

	public void setContent_c(String content_c) {
		this.content_c = content_c;
	}

	public boolean isUseful() {
		return useful;
	}

	public void setUseful(boolean useful) {
		this.useful = useful;
	}

	public Long getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Long replyCount) {
		this.replyCount = replyCount;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public List<Reply> getReplies() {
		return replies;
	}

	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}

	public Comment(Long id_c, String content_c, boolean useful, Long replyCount, String image, Date creationDate,
			User user, Topic topic, List<Reply> replies) {
		super();
		this.id_c = id_c;
		this.content_c = content_c;
		this.useful = useful;
		this.replyCount = replyCount;
		this.image = image;
		this.creationDate = creationDate;
		this.user = user;
		this.topic = topic;
		this.replies = replies;
	}

	public Comment() {
		super();
	}

	@Override
	public String toString() {
		return "Comment [id_c=" + id_c + ", content_c=" + content_c + ", useful=" + useful + ", replyCount="
				+ replyCount + ", image=" + image + ", creationDate=" + creationDate + ", user=" + user + ", topic="
				+ topic + ", replies=" + replies + "]";
	}

	
	
}