package tn.esprit.spring.control;




import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Don;
import tn.esprit.spring.entities.Event;
import tn.esprit.spring.entities.Jackpot;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.service.IProductService;
import tn.esprit.spring.service.JackpotService;



@RestController
@RequestMapping("/api/aymen")
public class JackpotController {

	
	
	@Autowired
	JackpotService jackpotService;
	
	

	
	//http://localhost:8081/SpringMVC/servlet/api/aymen/jackpot/addJackpot
	@PostMapping("/addJackpot")
	@ResponseBody
	public Long addJackpot(@RequestBody Jackpot jackpot) {
	
		System.out.println("ddddddddddddddd"+jackpot.toString());
		return jackpotService.addJackpot(jackpot);
	
	}
	
	//http://localhost:8081/SpringMVC/updateJackpot
	@PutMapping("/updateJackpot")
	@ResponseBody
	public Jackpot updateJackpot(@RequestBody Jackpot jackpot) {
		//System.out.println(Don.getMontantdon());

		jackpotService.updateJackpot(jackpot);
		return jackpot;
	}

	
	
	
	//URL : http://localhost:8081/deletejackpotById/3
	@DeleteMapping("/deletejackpotById/{idJackpot}") 
	@ResponseBody
	public void deletejackpotById(@PathVariable("idJackpot")Long JackpotId) {
		jackpotService.deletejackpotById(JackpotId);
		
	}
	
	
	
	//http://localhost:8081/listejackpot
	@GetMapping("/listejackpot")
	@ResponseBody
	private List<Jackpot> View() {
	return jackpotService.getAllJackpot();

	}
	
	
	//http://localhost:8081/api/aymen/daterestant/3
	@GetMapping("/daterestant/{jackpotId}")
	
	@ResponseBody
	public Long  daysBetween(@PathVariable("jackpotId" ) Long jackpotId){
		
		return jackpotService.daysBetween(jackpotId);
	}
	
	
	
//	//http://localhost:8081/api/aymen/getProductInJackpot/1
//	@PostMapping("getProductInJackpot/{id}")
//	public List<Product> getProductInJackpot(@PathVariable("id" ) Long id){
//		return jackpotService.getAllProductByJackpot(id);
//	}
	
	
	
	

}
