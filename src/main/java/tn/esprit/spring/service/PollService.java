package tn.esprit.spring.service;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Choix;
import tn.esprit.spring.entities.Poll;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.OptionRepository;
import tn.esprit.spring.repository.PollRepository;
import tn.esprit.spring.repository.UserRepository;
@Service
public class PollService {
	
	
private final PollRepository pollRepository;
private final OptionRepository optionRepository;
private final UserRepository userRepository;

@Autowired
public PollService(PollRepository pollRepository, OptionRepository optionRepository, UserRepository userRepository) {
    this.pollRepository = pollRepository;
    this.optionRepository = optionRepository;
    this.userRepository = userRepository;
}

@Transactional
public Poll savePoll(Poll poll, String username) {



	
	
    User user = userRepository.findOneByUsername(username);

  poll.setUser(user);
    Poll savedPoll = pollRepository.save(poll);
    

    

    
    savedPoll.getOptions().stream().forEach(option -> {
    	
    	
    	if(option.getPoll()==null){
        option.setPoll(savedPoll);
        optionRepository.save(option);}
    });

    return savedPoll;
}

@Transactional
public Poll updatePoll(Poll poll) {
    optionRepository.deleteByPollId(poll.getId());

    Poll savedPoll = pollRepository.save(poll);
    poll.getOptions().stream().forEach(option -> {
        option.setPoll(savedPoll);
        optionRepository.save(option);
    });

    return savedPoll;
}

public List<Poll> getAll() {
    return pollRepository.findAll();
}

public Poll getPollById(Long id) {
    return pollRepository.getOne(id);
}

public void deletePollById(Long id) {
    pollRepository.deleteById(id);
}

@Transactional
public String vote(Long id, Long optionId, Long iduser)  {
    Poll poll = pollRepository.getOne(id);
User u = new User();
  u =  userRepository.findById(iduser).get();
    
    //TO-DO: check end-date
    if (poll.getEndDate().before(new Date())) {
    return"Voting has already ended!";
    }

    //TO-DO: add IP
    List<String> ipAdresses = poll.getIpAdresses();
    if (ipAdresses.contains(u.getUsername())) {
    	  return"You can only vote once!";

    }

    List<Choix> options = poll.getOptions().stream().filter(option -> Objects.equals(option.getId(), optionId)).collect(Collectors.toList());
    if (options.size() == 1) {
    	Choix option = options.get(0);
        option.setScore(option.getScore() + 1);
        optionRepository.save(option);
        poll.getIpAdresses().add(u.getUsername());
        pollRepository.save(poll);
        return"Merci pour votre aide";
    } else {
    	  return"Option id for poll not unique!";
      
    }
}

public List<Poll> getAllForUser(String username) {
    User user = userRepository.findOneByUsername(username);
    return pollRepository.findAllByUser(user);
}

public List<Poll> getAllVisibleForUser(String username) {
    User user = userRepository.findOneByUsername(username);

    return pollRepository.findAllByUserAndVisible(user, true);
}

}