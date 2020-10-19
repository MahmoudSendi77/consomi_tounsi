package tn.esprit.spring.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import tn.esprit.spring.entities.Cart;
import tn.esprit.spring.entities.Command;
import tn.esprit.spring.entities.Facture;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.TypeCommand;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.CartRepository;
import tn.esprit.spring.repository.CommandRepository;
import tn.esprit.spring.repository.FactureRepository;

@Service
public class FactureService implements IFactureService {

	@Autowired
	FactureRepository factureRepository;

	@Autowired
	CartRepository cartRepository;

	@Autowired
	CommandRepository commandRepository;

	@Autowired
	CommandService commandService;

	// ajout facture
	@Override
	public Facture createFacture(Long idCommand) {
		Command commandManagedEntity = commandRepository.findById(idCommand).get();
		Facture factureManagedEntity = new Facture();
		LocalDate today = LocalDate.now();

		List<Product> prodcts = new ArrayList<>();
		int taille = commandService.getAllProductByCommand(idCommand).size();
		for (int index = 0; index < taille; index++) {
			prodcts.add(commandService.getAllProductByCommand(idCommand).get(index));
		}

		factureManagedEntity.setCommand(commandManagedEntity);
		factureManagedEntity.setMontantHT(commandManagedEntity.getMontantHT());
		factureManagedEntity.setMontantTTC(commandManagedEntity.getMontantTTC());
		factureManagedEntity.setFactureDate(commandManagedEntity.getCommandDate());
		factureManagedEntity.setFactureNumero((Long) commandManagedEntity.getNumeroCommand());
		factureRepository.save(factureManagedEntity);
		return factureManagedEntity;

		// suprimer facture
	}

	@Override
	public String DeleteFacture(Long idFacture) {
		// TODO Auto-generated method stub
		return null;
	}

	// update facture
	@Override
	public String UpdateFacture(Long idFacture) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Facture searchFactureByNumero(Long numero) {
		// TODO Auto-generated method stub
		return null;
	}

	// recupere une liste de tous les produit d'un facture
	@Override
	public List<Product> getAllProductByFacture(Long idFacture) {
		// TODO Auto-generated method stub
		Facture fact = factureRepository.findById(idFacture).get();
		List<Product> prodcts = new ArrayList<>();

		int taille = fact.getCommand().getProduits().size();

		for (int index = 0; index < taille; index++) {
			prodcts.add(fact.getCommand().getProduits().get(index));

		}

		return prodcts;

	}

	// liste de tous les factures

	@Override
	public List<Facture> getAllFacture() {
		// TODO Auto-generated method stub

		return (List<Facture>) factureRepository.findAll();
	}

	@Override
	public List<Facture> getFactureByDate(LocalDate date1, LocalDate date2) {
		// TODO Auto-generated method stu
		return factureRepository.findAllByPublicationTimeBetween(date1, date2);
	}

	// recpere la commande d'un facture

	@Override
	public Command getCommandByFacture(Long idFacture) {
		Facture facturedManagedEntity = factureRepository.findById(idFacture).get();

		return facturedManagedEntity.getCommand();
	}

	// rechercher la facture par leur id

	@Override
	public Facture getFactureById(Long idFacture) {
		// TODO Auto-generated method stub
		Facture facturedManagedEntity = factureRepository.findById(idFacture).get();
		return facturedManagedEntity;
	}

	// pdf facture
		public String export(Long idFacture) {
			Font header = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD);
			Document document = new Document();
			try {
				// create file
				Facture fact = factureRepository.findById(idFacture).get();
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Facture"+fact.getFactureId()+".pdf"));
				document.open();
				Command cd = getCommandByFacture(idFacture);
				User usr = getCommandByFacture(idFacture).getUser();

				// Add Image

				Image image1 = Image.getInstance("LOGO.JPG");
				image1.setAbsolutePosition(50f, 800f);
				image1.scaleAbsolute(100, 100);

				document.add(image1);

				// ajouter un header to the pdf file

				Paragraph par = new Paragraph();
				par.setFont(header);
				par.add("\n");
				par.add("\n");
				par.add("\n");
				par.add(" Facture Consommi Tounsi : -619");
				par.add("\n =============================================================");
				par.add("\n Facture Number  :" + String.valueOf(fact.getFactureNumero()));
				par.add("\n Date de Facture :" + cd.getCommandDate().toString());
				par.add("\n Client:");
				par.add("\n Name :" + usr.getName());
				par.add("\n Email :" + usr.getEmail());
				par.add("\n =============================================================");

				document.add(par);

				// create table des produit dans la commande

				PdfPTable table = new PdfPTable(4); // 3 columns.
				table.setWidthPercentage(100); // Width 100%
				table.setSpacingBefore(10f); // Space before table
				table.setSpacingAfter(10f); // Space after table

				// Set Column widths
				float[] columnWidths = { 1f, 1f, 1f, 1f };
				table.setWidths(columnWidths);
				table.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell("name");
				table.addCell("price");
				table.addCell("brand");
				table.addCell("tva");

				List<Product> prodcts = new ArrayList<>();
				List<Product> prods = getAllProductByFacture(idFacture);
				int taille = prods.size();
				for (int index = 0; index < taille; index++) {
					prodcts.add(prods.get(index));

					table.addCell(prods.get(index).getName());

					float f = prods.get(index).getPrice();
					String s = Float.toString(f);
					table.addCell(s);

					table.addCell(prods.get(index).getBrand().getName());

					float q = prods.get(index).getTva();
					String a = Float.toString(f);
					table.addCell(a);
				}
				document.add(table);

				// ajouter un header to the pdf file

				Paragraph par1 = new Paragraph();
				par1.add("\n");
				par1.add("\n ====================================================================");
				par1.add("\n Montant de Facture Number  :" + String.valueOf(fact.getFactureNumero()));
				document.add(par1);

				// create table des pour les montant

				PdfPTable t2 = new PdfPTable(4); // 3 columns.
				t2.setWidthPercentage(100); // Width 100%
				t2.setSpacingBefore(10f); // Space before table
				t2.setSpacingAfter(10f); // Space after table

				// Set Column widths

				t2.setWidths(columnWidths);
				t2.setHorizontalAlignment(Element.ALIGN_CENTER);
				t2.addCell("montant HT");
				t2.addCell("Montant TVA");
				t2.addCell("montant TTC");
				t2.addCell("Taux TVA");

				float fmontantHT = fact.getMontantHT();
				String montantHT = Float.toString(fmontantHT);
				t2.addCell(montantHT);

				float fMontantTVA = 0;
				String MontantTVA = Float.toString(fMontantTVA);
				t2.addCell(MontantTVA);

				float fmontantTTC = fact.getMontantTTC();
				String montantTTC = Float.toString(fmontantTTC);
				t2.addCell(montantTTC);

				float fTauxTVA = 0;
				String TauxTVA = Float.toString(fTauxTVA);
				t2.addCell(TauxTVA);

				document.add(t2);

				document.close();
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "exporting";
		}

	@Override
	public List<Facture> getFactureByUserName(String nameUser) {
		// TODO Auto-generated method stub
		return factureRepository.findFactureByUser(nameUser);
	}

	@Override
	public List<Facture> getFactureByType(String type) {
		// TODO Auto-generated method stub
		return factureRepository.findAllByTypeFacture(type);

	}

	@Override
	public String createFactureDelivery(Long idCommand) {
		// TODO Auto-generated method stub
		Command cd = commandRepository.findById(idCommand).get();
		Facture fact = new Facture();

		cd.setCommandEtat(true);
		LocalDate today = LocalDate.now();
		// 0 consommer prisx aprés livraison du module livraison
		float prixLivraison = 0 + cd.getMontantTTC();

		fact.setCommand(cd);
		fact.setFactureDate(today);
		fact.setFactureNumero(cd.getNumeroCommand());
		fact.setMontantHT(cd.getMontantHT());
		fact.setMontantTTC(prixLivraison);
		return "facture created";
	}

	private static String QRCODE_PATH = "E:\\projet Spring\\-619";

	public String writeQRCode(Long idFacture) throws Exception {
		Facture facture = factureRepository.findById(idFacture).get();
		// path de qr code
		String qrcode = QRCODE_PATH +"-Consommi_Tounsi"+facture.getCommand().getUser().getName()+".png";

		QRCodeWriter writer = new QRCodeWriter();
		BitMatrix bitMatrix = writer.encode(facture.getCommand().getUser().getName() + "\n" + facture.getFactureNumero()
				+ "\n" + facture.getFactureDate() + "\n" + facture.getMontantTTC(), BarcodeFormat.QR_CODE, 350, 350);

		Path path = FileSystems.getDefault().getPath(qrcode);
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", (java.nio.file.Path) path);

		return "QRCODE is generated successfully....";

	}
}
