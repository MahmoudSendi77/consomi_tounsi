package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tn.esprit.spring.repository.NewsletterRepository;
import tn.esprit.spring.repository.UserRepository;

import tn.esprit.spring.entities.newsletter;
@Service
public class NewsletterService {

	
	@Autowired
	UserRepository userRepository;
	@Autowired
	NewsletterRepository newsletterRepository;

	public Long ajouternewsletter(newsletter email) {
		newsletterRepository.save(email);
		return email.getId();
	}
}
