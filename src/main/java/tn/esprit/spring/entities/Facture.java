package tn.esprit.spring.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Facture implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "factureId")
	private Long factureId;

	@Column(name = "factureNumero")
	private Long factureNumero;

	private float montantHT;

	private float montantTTC;

	@Column(name = "facturedate")
	private LocalDate factureDate;

	@JsonIgnore
	@OneToOne
	private Command command;

	public Long getFactureId() {
		return factureId;
	}

	public void setFactureId(Long factureId) {
		this.factureId = factureId;
	}

	public Long getFactureNumero() {
		return factureNumero;
	}

	public void setFactureNumero(Long factureNumero) {
		this.factureNumero = factureNumero;
	}

	public float getMontantHT() {
		return montantHT;
	}

	public void setMontantHT(float montantHT) {
		this.montantHT = montantHT;
	}

	
	public float getMontantTTC() {
		return montantTTC;
	}

	public void setMontantTTC(float montantTTC) {
		this.montantTTC = montantTTC;
	}

	

	public LocalDate getFactureDate() {
		return factureDate;
	}

	public void setFactureDate(LocalDate factureDate) {
		this.factureDate = factureDate;
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public Facture() {
		super();
	}

	public Facture(Long factureNumero, float montantHT, float montantTTC, LocalDate factureDate, Command command) {
		super();
		this.factureNumero = factureNumero;
		this.montantHT = montantHT;
		this.montantTTC = montantTTC;
		this.factureDate = factureDate;
		this.command = command;
	}

	public Facture(Long factureId, Long factureNumero, float montantHT, float montantTTC, LocalDate factureDate,
			Command command) {
		super();
		this.factureId = factureId;
		this.factureNumero = factureNumero;
		this.montantHT = montantHT;
		this.montantTTC = montantTTC;
		this.factureDate = factureDate;
		this.command = command;
	}

	@Override
	public String toString() {
		return "Facture [factureId=" + factureId + ", factureNumero=" + factureNumero + ", montantHT=" + montantHT
				+ ", montantTTC=" + montantTTC + ", factureDate=" + factureDate + ", command=" + command + "]";
	}

	
}
