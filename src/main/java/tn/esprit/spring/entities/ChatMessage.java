package tn.esprit.spring.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChatMessage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private MessageType type;

	private String Content;

	private String Sender;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getSender() {
		return Sender;
	}

	public void setSender(String sender) {
		Sender = sender;
	}

	public ChatMessage(Long id, MessageType type, String content, String sender) {
		super();
		this.id = id;
		this.type = type;
		Content = content;
		Sender = sender;
	}

	public ChatMessage() {
		super();
	}
//test
	@Override
	public String toString() {
		return "ChatMessage [id=" + id + ", type=" + type + ", Content=" + Content + ", Sender=" + Sender + "]";
	}

	
	
}