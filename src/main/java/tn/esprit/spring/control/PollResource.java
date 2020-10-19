package tn.esprit.spring.control;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Poll;
import tn.esprit.spring.security.CurrentUser;
import tn.esprit.spring.security.UserPrincipal;
import tn.esprit.spring.service.PollService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/polls")
public class PollResource {

    @Autowired
   PollService pollService;

    @GetMapping()
    public List<Poll> list() {
        return pollService.getAll();
    }

    @GetMapping("/user/{username}")
 //   @Secured("ROLE_USER")
    public List<Poll> getUserPolls(@PathVariable String username, Principal p) {
        
        if (username.equals(p.getName())) {
            return pollService.getAllForUser(username);
        } else {
            return pollService.getAllVisibleForUser(username);
        }
    }

    @GetMapping("/{id}")
    public Poll get(@PathVariable String id) {
        return pollService.getPollById(Long.parseLong(id));
    }

    @PutMapping("/{id}")
//    @Secured("ROLE_USER")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody Poll poll) {
        poll.setId(id);
        Poll updatePoll = pollService.updatePoll(poll);
        return ResponseEntity.ok(updatePoll);

    }

    @PostMapping
 //   @Secured("ROLE_USER")
    public ResponseEntity<?>  post(@RequestBody Poll poll, @CurrentUser UserPrincipal currentUser) {
    	
    	
    	//System.err.println(currentUser.getUsername());
    	
    	
        Poll savedPoll = pollService.savePoll(poll,currentUser.getUsername());
        return ResponseEntity.status(201).body(savedPoll);
    }


    
  
    
    
//    @Secured("ROLE_USER")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Principal p) {
        pollService.deletePollById(id);
        return ResponseEntity.status(204).build();

    }

    @PostMapping("{id}/vote/{optionId}/{iduser}")
    public ResponseEntity<?> post(@PathVariable Long id, @PathVariable Long optionId, HttpServletRequest request ,@PathVariable Long iduser) throws Exception {
        pollService.vote(id, optionId, iduser);
       
        return ResponseEntity.ok().build();
    }
}