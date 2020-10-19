package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity

public class Choix implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

    private String optionss;
    
    public Choix(String optionss, Long score) {
		super();
		this.optionss = optionss;
		this.score = score;
		
	}


	@Override
	public String toString() {
		return "Choix [id=" + id + ", optionss=" + optionss + ", score=" + score + ", poll=" + poll + "]";
	}


	private Long score ;

    public Choix() {
    }

    public Choix(String value) {
        this.optionss = value;
    }
    
    
    @JsonIgnore
    @ManyToOne
  
    private Poll poll;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }
     

    public String getOption() {
        return optionss;
    }

    public void setOption(String value) {
        this.optionss = value;
    }
    
    
    
}
