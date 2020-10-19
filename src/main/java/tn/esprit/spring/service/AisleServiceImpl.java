package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Aisle;
import tn.esprit.spring.entities.Category;
import tn.esprit.spring.entities.Image;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.AisleRepository;
import tn.esprit.spring.repository.CategoryRepository;
import tn.esprit.spring.repository.ImageRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class AisleServiceImpl implements IAisleService {

//	public enum AisleType{
//		refrigerateur,normal
//	}
	@Autowired
	AisleRepository aisleRepository;
	//@Autowired
	//ProductRepository productRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ImageRepository imageRepository;



	@Transactional
	@Override
	public long deleteCategoryFromAisle(long aisleId, Long categoryId) {
		Aisle aisle = aisleRepository.findById(aisleId).get();

		if (aisle.getCategory().getId() == categoryId) {
			aisle.setCategory(null);
			aisleRepository.save(aisle);
			return 0;
		}
		return -1;
	}

	@Override
	public List<Product> getAisleProduct(long id) {
		return aisleRepository.getAisleProductById(id);
	}

	@Override
	public List<Aisle> getAisleX(Aisle aisle) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
		return (List<Aisle>) aisleRepository.findAll(Example.of(aisle, matcher));
		
	}

	@Transactional
	@Override
	public void affecterCheafAisleToAisle(long aisleId, long userId) {
		System.err.println("affecterCheafAisleToAisle + ids  "+ aisleId
				+" userid  ="+ userId);
		Aisle aisle = aisleRepository.findById(aisleId).get();
		System.err.println("aisle = "+aisle);
		User user = userRepository.findById(userId).get();
		System.err.println("user "+user);
		aisle.setUser(user);
		aisleRepository.save(aisle);

	}

	@Transactional
	@Override
	public void desaffecterCheafAisleToAisle(long aisleId, long userId) {
		Aisle aisle = aisleRepository.findById(aisleId).get();
		if (aisle.getUser().getId() == userId) {
			aisle.setUser(null);
			aisleRepository.save(aisle);
		}
	}

	@Override
	public long addAisle(Aisle aisle) {
		aisleRepository.save(aisle);
		return aisle.getId();
	}

	@Override
	public long deleteAisle(Long aisleId) {
		Aisle aisle = aisleRepository.findById(aisleId).get();
		aisleRepository.delete(aisle);
		return 0;
	}

	@Override
	public long updateAisle(Aisle aisle) {
		if (aisleRepository.findById(aisle.getId()).get() != null) {
			aisleRepository.save(aisle);
			return  aisle.getId();
		}
		return 0;
	}

	@Override
	public List<Aisle> getAllAisle() {

		return aisleRepository.findAll();
	}

	@Override
	public Aisle getAislebyId(long id) {

		return aisleRepository.findById(id).get();
	}

	@Override
	public long addCategoryToAisle(long aisleId, Long categoryId) {
		Aisle aisle = aisleRepository.findById(aisleId).get();
		Category category = categoryRepository.findById(categoryId).get();

		if (aisle.getCategory() == null) {

			aisle.setCategory(category);
			aisleRepository.save(aisle);
		} else {

			return -1;
		}
		return 0;
	}

	@Override
	public Aisle addImageToAisle(long aisleId, long imageId) {
		Aisle aisle = aisleRepository.findById(aisleId).get();
		Image image = imageRepository.findById(imageId).get();

		if (aisle.getImages() == null) {

			List<Image> images = new ArrayList<>();
			images.add(image);
			aisle.setImages(images);
			aisleRepository.save(aisle);
		} else {
			aisle.getImages().add(image);
			aisleRepository.save(aisle);
		}
		return aisle;
	}

	@Override
	public Aisle deleteImageOfAisle(long aisleId, long imageId) {
		// TODO Auto-generated method stub
		Aisle aisle = aisleRepository.findById(aisleId).get();
		Image image = imageRepository.findById(imageId).get();

		if (aisle.getImages() != null)
			if (aisle.getImages().contains(image)) {

				for (int i = 0; i < aisle.getImages().size(); i++) {
					if (aisle.getImages().get(i).getId() == imageId) {
						aisle.getImages().remove(i);
						aisleRepository.save(aisle);
						break;
					}
				}
			}
		return aisle;
	}

	@Override
	public List<Image> getListImageOfProductAisle(long aisleId) {
		// TODO Auto-generated method stub
		return null;
				//aisleRepository.getListImageOfProductAisle(aisleId);
	}

	@Override
	public float profitabilityOfAisle(long aisleId) {
		
		return aisleRepository.rentabilteAisle(aisleId);
	}

	@Override
	public List< List<Float>>  countBrandRentabilteByAisle(String brandName) {
		
		return aisleRepository.countBrandRentabilteByAisle( brandName);
	}

	

	@Override
	public List<Image> getListImageOfAisle(long aisleId) {
		return aisleRepository.getListImageOfAisle(aisleId);
	}

	@Override
	public List<Image> getListImageOfAllAisle(long aisleId) {
		return aisleRepository.getListImageOfAllAisle();
	}
	
	@Override
	public List< List<Float>> countRentabilteByAisle() {
				
		 return  aisleRepository.countAisleRentability();
	}

	
	@Override
	public List< List<Float>> countAisleRentabilteByCategory(long cat) {
				
		 return  aisleRepository.countAisleRentabilteByCategory(cat);
	}
	
	@Override
	public Map<Aisle, Float> profitabilityOfAllAisle(long aisleId) {
		List< List<Float>> l= countRentabilteByAisle();
		Map<Aisle,Float> map= new HashMap<>();
		l.forEach((e)->map.put(aisleRepository.findById((long) e.get(0).intValue()).get(), e.get(1)));
		return map;
	}

}
