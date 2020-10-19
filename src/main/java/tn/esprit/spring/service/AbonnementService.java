package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Abonnement;
import tn.esprit.spring.repository.AbonnementRepository;

@Service
public class AbonnementService implements AbonnementServiceInterface {

	@Autowired
	AbonnementRepository AbonnementRepository;
@Transactional
	@Override
	public String add_abonnement(Abonnement Ab) {
		// TODO Auto-generated method stub
if (this.existbytype(Ab.getType()))
	return "Abonnement"+Ab.getType() +" deja enregistré" ;


		AbonnementRepository.saveAndFlush(Ab);

		return "Abonnement"+Ab.getType() +" enregistré avec succes";
	}

	@Override
	public String update_abonnement(Abonnement Ab) {
		// TODO Auto-generated method stub
	
		AbonnementRepository.save(Ab);
		
		return null;
	}

	@Override
	public String delete_abonnement(Long idAb) {
		// TODO Auto-generated method stub
		
		AbonnementRepository.delete(	AbonnementRepository.findById(idAb).get() );
		
		return null;
	}

	@Override
	public Boolean existbytype(String type) {
		// TODO Auto-generated method stub
		List<Abonnement> AB = AbonnementRepository.findByType(type);

		if (AB.size() == 0)
			return false;

		return true;
	}

}
