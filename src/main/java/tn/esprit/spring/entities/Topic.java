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
public class Topic implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_t;
	
	private String title;
	
	private String content;
	
	private Long views;
	
	@Temporal(TemporalType.DATE)
	private Date creationDate;
	
	@Temporal(TemporalType.DATE)
	private Date lastUpDate;
	
	private boolean closed;

	private Long likeCount;
	
	private Long commentCount;
	private String image_t;
	
	@ManyToOne
	User user ;

	@ManyToOne
	Section section ;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="topic")
    private List<Comment> comments ;
	
	
	
	public Long getId_t() {
		return id_t;
	}
	public void setId_t(Long id_t) {
		this.id_t = id_t;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getViews() {
		return views;
	}
	public void setViews(Long views) {
		this.views = views;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getLastUpDate() {
		return lastUpDate;
	}
	public void setLastUpDate(Date lastUpDate) {
		this.lastUpDate = lastUpDate;
	}
	public boolean isClosed() {
		return closed;
	}
	public void setClosed(boolean closed) {
		this.closed = closed;
	}
	public Long getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Long likeCount) {
		this.likeCount = likeCount;
	}
	public Long getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Long commentCount) {
		this.commentCount = commentCount;
	}
	public String getImage_t() {
		return image_t;
	}
	public void setImage_t(String image_t) {
		this.image_t = image_t;
	}
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public Topic(Long id_t, String title, String content, Long views, Date creationDate, Date lastUpDate,
			boolean closed, Long likeCount, Long commentCount, String image_t, User user, Section section,
			List<Comment> comments) {
		super();
		this.id_t = id_t;
		this.title = title;
		this.content = content;
		this.views = views;
		this.creationDate = creationDate;
		this.lastUpDate = lastUpDate;
		this.closed = closed;
		this.likeCount = likeCount;
		this.commentCount = commentCount;
		this.image_t = image_t;
		this.user = user;
		this.section = section;
		this.comments = comments;
	}
	public Topic() {
		super();
	}
	
	@Override
	public String toString() {
		
		return "Topic [id_t=" + id_t + ", title=" + title + ", content=" + content + ", views=" + views + ", creationDate="
				+ creationDate + ", lastUpDate=" + lastUpDate + ", closed=" + closed + ", likeCount=" + likeCount + ", commentCount=" + commentCount
				+ ", image_t=" + image_t + ", user=" + user + ", section=" + section + ",  comments=" + comments
				 +  "]";
	}
	
	
}
