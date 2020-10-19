package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entities.Poll;
import tn.esprit.spring.entities.User;

public interface PollRepository extends JpaRepository<Poll, Long> {

    List<Poll> findAllByUser(User user);

    public List<Poll> findAllByUserAndVisible(User user, boolean b);

}