package tn.esprit.spring.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Category;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.PromosMagazin;
import tn.esprit.spring.repository.ProductRepository;
import tn.esprit.spring.repository.PromosMagazinRepository;
import tn.esprit.spring.repository.StockRepository;

@Service
@EnableScheduling
public class PromosMagazinServiceImpl implements IPromosMagazinService {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	PromosMagazinRepository promosMagazinRepository;
	@Autowired
	StockRepository stockRepository;
	
	@Override
	public long addPromos(PromosMagazin p) {
		promosMagazinRepository.save(p);
		return p.getId();
	}

	@Override
	public long updatePromos(PromosMagazin p) {
		promosMagazinRepository.save(p);
		return p.getId();
	}

	@Override
	public long deletePromos(long id) {
		// TODO Auto-generated method stub
		promosMagazinRepository.delete(promosMagazinRepository.findById(id).get());
		return 0;
	}
	
	@Scheduled(fixedDelay = 1000000)
	public void proposePromos() {
		
		List<Product> products = productRepository.getRedZonProduct(22);// product that expire in 30 day
		Map<Category,List<Product>> promosByCategory= new HashMap<>();
		List<Product> l=new ArrayList<Product>();
		System.out.println(products.size());
		for(int i=0;i<products.size();i++){
			if(!promosByCategory.containsKey(products.get(i).getCategory()))
			{
			promosByCategory.put(products.get(i).getCategory(),new ArrayList<Product>());
			System.out.println(i +"  ==  "+ products.get(i).getCategory() );
			}
			 l= promosByCategory.get(products.get(i).getCategory());
			l.add(products.get(i));
			promosByCategory.put(products.get(i).getCategory(), l);
		}
		System.out.println(promosByCategory.size());
		System.out.println(promosByCategory.get(1));
		
		for (int i = 0; i < products.size(); i++) {
			promosMagazinRepository.save(new PromosMagazin());
			System.out.println("the product   "+products.get(i).getName() +"   on promos   "+ products.get(i).getCategory().getCategoryName());
		//	deleteProduct(products.get(i).getId());
		}
		System.out.println(promosByCategory.toString());
	}
	
	@Scheduled(fixedDelay = 1000000)
	public void targetClientPromos() {
//		List<Product> products = productRepository.getRedZonProduct(30);
//		//notify user specific	
//		for (int i = 0; i < products.size(); i++) {
//			System.out.println("the product   "+products.get(i).getName() +"   have been expired .. To Be  Deleted  " );
//		//	deleteProduct(products.get(i).getId());
//		}
	}
	
	//@Scheduled(fixedDelay = 1000000)
	public float rentabilityOfPromo(long id) {
//		List<Product> products = productRepository.getRedZonProduct(30);
//		//notify user specific
//		for (int i = 0; i < products.size(); i++) {
//			System.out.println("the product   "+products.get(i).getName() +"   have been expired .. To Be  Deleted  " );
//		//	deleteProduct(products.get(i).getId());
//		}
		return 0;
	}
	
	@Scheduled(fixedDelay = 1000000)
	public void productsPromosByAisle() {
//		List<Product> products = productRepository.getRedZonProduct(30);
//		//notify user specific
//		for (int i = 0; i < products.size(); i++) {
//			System.out.println("the product   "+products.get(i).getName() +"   have been expired .. To Be  Deleted  " );
//		//	deleteProduct(products.get(i).getId());
//		}
	}
	
	@Scheduled(fixedDelay = 1000000)
	public void promosReveillant() {
//		List<Product> products = productRepository.getRedZonProduct(30);
//		//notify user specific
//		for (int i = 0; i < products.size(); i++) {
//			System.out.println("the product   "+products.get(i).getName() +"   have been expired .. To Be  Deleted  " );
//		//	deleteProduct(products.get(i).getId());
//		}
	}
	
	@Scheduled(fixedDelay = 1000000)
	public void promoIslamique() {
		List<Product> products = productRepository.getRedZonProduct(30);
		//notify user specific
		for (int i = 0; i < products.size(); i++) {
			System.out.println("the product   "+products.get(i).getName() +"   have been expired .. To Be  Deleted  " );
		//	deleteProduct(products.get(i).getId());
		}
	}

	@Override
	public PromosMagazin getPromosById(long id) {
		// TODO Auto-generated method stub
		return promosMagazinRepository.findById(id).get();
	}

	@Override
	public List<PromosMagazin> getAllPromos() {
		// TODO Auto-generated method stub
		return promosMagazinRepository.findAll();
	}

	@Override
	public List<PromosMagazin> getCurrentPromos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PromosMagazin> getPromosByDate(Date date1,Date date2 ) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
	

}
