package tn.esprit.spring.control;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.stereotype.Controller;

@Controller(value = "fileUploadManagedBeana")
@ELBeanName(value = "fileUploadManagedBeana")

public class FileUploadManagedBeanasss {
	
	
	
	private File filess ;
	
	
	public File getFiless() {
		return filess;
	}



	public void setFiless(File filess) {
		this.filess = filess;
	}



	
	
	

	
	
	
	

List <	UploadedFile> file;

	

	public List<UploadedFile> getFile() {
	return file;
}



public void setFile(List<UploadedFile> file) {
	this.file = file;
}



	public void handleFileUpload(FileUploadEvent event) {
		System.out.println("wsel");
		System.out.println("oooooooooooooooooooo houssem  " + event.getFile());
		
		file.add(event.getFile());
		// imageService.verifyBarCode(uploadedFile);
	}
}