package tn.esprit.spring.control;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Aisle;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.service.IAisleService;

@RestController
@RequestMapping("/aisle")
public class AisleControllerImpl implements IAisleController {

	@Autowired
	IAisleService aisleService;

	@PostMapping("/addAisle")
	@ResponseBody
	public String addAisle(@RequestBody Aisle aisle){
		aisleService.addAisle(aisle);
		return "aisle added successfuly";
	}
	
	
	@GetMapping("/countBrandRentabilteByAisle/{brandName}")
	@ResponseBody
	public List< List<Float>> countBrandRentabilteByAisle(@PathVariable("brandName") String brandName){
		return	aisleService.countBrandRentabilteByAisle(brandName) ;	
	}
	
	
	@GetMapping("/countRentabilteByAisle")
	@ResponseBody
	public List< List<Float>>  countRentabilteByAisle(){
		return aisleService.countRentabilteByAisle();
		
	}
	
	@GetMapping("/countRentabilteByAisleCategory/{brandName}")
	@ResponseBody
	public List< List<Float>>  countRentabilteByAisle2(@PathVariable("brandName") long ll){
		return aisleService.countAisleRentabilteByCategory(ll);
		
	}
	
	@DeleteMapping("/deleteProduct/{id}")
	
	public String addAisle(@PathVariable("id") Long aisleId){
		aisleService.deleteAisle(aisleId);
		return "aisle deleted successfuly";
	}
	
	@PutMapping("/updateProduct")
	@ResponseBody
	public String updateAisle(@RequestBody Aisle aisle){
		aisleService.updateAisle(aisle);
		return "aisle addedsuccessfuly";
	}
	
	
	
//	 @GetMapping("/populateAisle/{id}")
//	 @ResponseBody
//		public String populateProduct(@PathVariable("id") Long id,@RequestBody List<Long> listProduct){
//		  aisleService.populateAisle(id, listProduct);
//		  return "ok";
//		}
	 
	 
	 @GetMapping("/getAisleProduct/{id}")
		public List<Product> getAisleProduct(@PathVariable("id") Long id){
		 return aisleService.getAisleProduct(id);
			 
		}
	 
	 @GetMapping("/getAisle/{id}")
		public Aisle getAisleById(@PathVariable("id") Long id){
		 return aisleService.getAislebyId(id);
			 
		}
	 
	 @GetMapping("/getAllAisle")
		public List<Aisle> getAllAisle(){
		 return aisleService.getAllAisle();
			 
		}
	 
	 
	@PutMapping("/addCategoryToAisle/{aisleId}/{categoryId}")
	public String addCategoryToAisle(@PathVariable("aisleId") Long aisleId,@PathVariable("categoryId") Long categoryId){
		aisleService.addCategoryToAisle( aisleId,  categoryId);
		return "aisle updated successfuly";
	}
	
	@PutMapping("/deleteCategoryFromAisle/{aisleId}/{categoryId}")
	public String deleteCategoryFromAisle(@PathVariable("aisleId") Long aisleId,@PathVariable("categoryId") Long categoryId){
		aisleService.deleteCategoryFromAisle( aisleId,  categoryId);
		return "aisle deleted successfuly";
	}
	
@PutMapping("/affecterCheafAisleToAisle/{aisleId}/{userId}")	
	public String affecterCheafAisleToAisle(@PathVariable("aisleId") Long aisleId,@PathVariable("userId") Long userId){
		aisleService.affecterCheafAisleToAisle(aisleId, userId); 
		return "aisle affecterCheafAisleToAisle successfuly";
	}

@PutMapping("/desaffecterCheafAisleToAisle/{aisleId}/{userId}")	
public String desaffecterCheafAisleToAisle(@PathVariable("aisleId") Long aisleId,@PathVariable("userId") Long userId){
	aisleService.desaffecterCheafAisleToAisle( aisleId,  userId);
	return "aisle desaffecterCheafAisleToAisle successfuly";
}
	
	
}
