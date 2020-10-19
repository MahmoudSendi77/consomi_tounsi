package tn.esprit.spring.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.control.SigninJsf;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.service.CatalogueService;
import tn.esprit.spring.service.IImageService;
import tn.esprit.spring.service.IProductService;

@Controller(value = "detailsController")
@ELBeanName(value = "detailsController")
@Join(path = "/admin/manageCategory", to = "/pages/adminSide/Store/addSubCategory.jsf")

public class ProductDetailsController {

	@Autowired
	SigninJsf signinJsf;
	@Autowired
	IImageService imageService;
	@Autowired
	CatalogueService catalogueService;
	@Autowired
	IProductService productService;

	private Part uploadedFile;
	private String ff = "mdglkngmldbdbg ";

	public String getFf() {
		return ff;
	}

	public void setFf(String ff) {
		this.ff = ff;
	}

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public void saveFiless(){
		System.err.println("edvddv");
		System.err.println("edvddv=== " +uploadedFile);
		System.err.println("edvddvkfkjb");
	}
	public void saveFile() {

		System.out.println("saveFile method invoked..");
		System.out.println("content-type:{0}" + uploadedFile.getContentType());
		System.out.println("filename:{0}" + uploadedFile.getName());
		System.out.println("submitted filename:{0}" + uploadedFile.getSubmittedFileName());
		System.out.println("size:{0}" + uploadedFile.getSize());
		String fileName = "";

		// try {
		//
		// fileName = getFilename(uploadedFile);
		//
		// System.out.println("fileName " + fileName);
		//
		// uploadedFile.write(folder+fileName);
		//
		//
		// } catch (IOException ex) {
		// System.out.println(ex);
		//
		//
		// }

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("File " + fileName + " Uploaded!"));

	}

	private static String getFilename(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE
																													// fix.
			}
		}
		return null;
	}

	private List<Product> listProduct = new ArrayList<Product>();

	private String reference;

	public List<Product> getListProduct() {
		listProduct = productService.getProductByRef(reference);
		return listProduct;
	}

	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

}
