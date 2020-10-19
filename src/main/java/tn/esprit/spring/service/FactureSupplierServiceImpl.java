package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.FactureSupplier;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.repository.FactureSupplierRepository;

@Service
public class FactureSupplierServiceImpl implements IFactureSupplierService {

	@Autowired
	FactureSupplierRepository factureSupplierRepository;
	
	public void getSupplierFacturePrice(Long idFacture){
		
	}

	@Override
	public Float countPrice(Long id) {	
		return factureSupplierRepository.countFacturePrice(id);
	}

	@Override
	public long addFacture(FactureSupplier factureSuplier) {
		factureSupplierRepository.save(factureSuplier);
		return factureSuplier.getId();
	}

	@Override
	public long deleteFacture(Long factureSuplierId) {
		factureSupplierRepository.delete(factureSupplierRepository.findById(factureSuplierId).get());
		return 0;
	}

	@Override
	public long updateFacture(FactureSupplier factureSuplier) {
		if(factureSupplierRepository.findById(factureSuplier.getId()).get()!=null)
		factureSupplierRepository.save(factureSuplier);
		return 0;
	}

	@Override
	public List<FactureSupplier> getAllFacture() {
		// TODO Auto-generated method stub
		return factureSupplierRepository.findAll();
	}

	@Override
	public FactureSupplier getFactureById(Long factureSuplierId) {
		// TODO Auto-generated method stub
		return factureSupplierRepository.findById(factureSuplierId).get();
	}

	@Override
	public List<FactureSupplier> getFactureBySuplierId(Long suplierId) {
		// TODO Auto-generated method stub
		return factureSupplierRepository.getFactureByUser(suplierId);
	}

	@Override
	public Float countFactureBySuplierId(Long suplierId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float countQuantityAddedByProduct(Long productId, Date date1, Date date2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Long, Long> countAllQuantityAddedByProduct(Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getMostProductQuantity() {
		// TODO Auto-generated method stub
		return null;
	}
}
