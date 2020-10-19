package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import tn.esprit.spring.entities.FactureSupplier;
import tn.esprit.spring.entities.Product;

public interface IFactureSupplierService {

	public Float countPrice(Long id);

	public long addFacture(FactureSupplier factureSuplier);

	public long deleteFacture(Long factureSuplierId);

	public long updateFacture(FactureSupplier factureSuplier);

	public List<FactureSupplier> getAllFacture();

	public FactureSupplier getFactureById(Long factureSuplierId);

	public List<FactureSupplier> getFactureBySuplierId(Long suplierId);

	public Float countFactureBySuplierId(Long suplierId);

	public Float countQuantityAddedByProduct(Long productId, Date date1, Date date2);

	public Map<Long, Long> countAllQuantityAddedByProduct(Long productId);

	public Product getMostProductQuantity();

}
