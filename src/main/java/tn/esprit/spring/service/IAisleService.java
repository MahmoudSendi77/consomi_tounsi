package tn.esprit.spring.service;

import java.util.List;
import java.util.Map;

import tn.esprit.spring.entities.Aisle;
import tn.esprit.spring.entities.Image;
import tn.esprit.spring.entities.Product;

public interface IAisleService {

	public List< List<Float>>  countBrandRentabilteByAisle(String brandName);
	public List< List<Float>> countRentabilteByAisle();//classement by ne9sa 5edma 
	
	public long addAisle(Aisle aisle);

	public long deleteAisle(Long aisleId);

	public long updateAisle(Aisle aisle);

	public List< List<Float>> countAisleRentabilteByCategory(long cat);
	public long addCategoryToAisle(long aisleId,  Long categoryId);

	public long deleteCategoryFromAisle(long aisleId, Long categoryId);

	public List<Product> getAisleProduct(long id);

	public List<Aisle> getAisleX(Aisle aisle);

	public void affecterCheafAisleToAisle(long aisleId, long productId);

	public void desaffecterCheafAisleToAisle(long aisleId, long userId);

	public List<Aisle> getAllAisle();

	public Aisle getAislebyId(long id);
	
	public Aisle addImageToAisle(long aisleId, long productId);
	
	public Aisle deleteImageOfAisle(long aisleId, long productId);
	
	public List<Image> getListImageOfProductAisle(long aisleId);
	
	public List<Image> getListImageOfAisle(long aisleId);
	
	public List<Image> getListImageOfAllAisle(long aisleId);
	
	public float profitabilityOfAisle(long aisleId);//rentability
	
	public Map<Aisle,Float> profitabilityOfAllAisle(long aisleId);
	
	

}
