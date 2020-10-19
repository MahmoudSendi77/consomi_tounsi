package tn.esprit.spring.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Image;

@Service
public class CatalogueService {

	
	
	
	public String updateCatalogue(List<Image> images) throws IOException{
		File dir = new File(System.getProperty("user.dir")+"/src/main/webapp/catalogue/samples/magazine/pages");
		
		if (!dir.exists())
			dir.mkdirs();
		
		for(int i=1;i<=images.size();i++){
			File large = new File(dir.getAbsolutePath() + File.separator + ""+i+"-large.jpg");
			File thumb = new File(dir.getAbsolutePath() + File.separator + ""+i+"-thumb.jpg");
			File page = new File(dir.getAbsolutePath() + File.separator + ""+i+".jpg");
			File toBeReplaced = new File(System.getProperty("user.dir")+"/src/main/webapp/resources/uploads"+ File.separator + "catalogue/image" + File.separator + images.get(i-1).getImage());
			
			//Files.copy(  , target, options)
//			File thumb = new File(toBeReplaced.getParent(), ""+i+"-thumb.jpg");
//			File large = new File(toBeReplaced.getParent(), ""+i+"-large.jpg");
//			File page = new File(toBeReplaced.getParent(), ""+i+".jpg");
//			Files.copy(thumb.toPath(), dir.toPath(), StandardCopyOption.REPLACE_EXISTING);
//			Files.copy(page.toPath(), dir.toPath(), StandardCopyOption.REPLACE_EXISTING);
//			Files.copy(large.toPath(), dir.toPath(), StandardCopyOption.REPLACE_EXISTING);
			
			Files.copy(toBeReplaced.toPath(),thumb.toPath(), StandardCopyOption.REPLACE_EXISTING);
			Files.copy(toBeReplaced.toPath(),page.toPath(),  StandardCopyOption.REPLACE_EXISTING);
			Files.copy(toBeReplaced.toPath(),large.toPath(), StandardCopyOption.REPLACE_EXISTING);
		}
		return "";
		
		
	}
	
	public void creatFileAndInsertValue() throws IOException{
		
		
		File dir = new File(System.getProperty("user.dir")+"/src/main/webapp/catalogue/samples/steve-jobs/pages");

		if (!dir.exists())
			dir.mkdirs();
		// Create the file on server
		for(int i=0;i<122 ;i++){
		File page = new File(dir.getAbsolutePath() + File.separator + "page"+i+".html");
		Writer output;
		output = new BufferedWriter(new FileWriter(page,true));  //clears file every time
		output.append("<div class=\"book-content\"> \n");
		output.append("<img class=\"left-pic zoom-this\" src=\""+"samples/steve-jobs/pics/highschool.jpg"+"\" width=\"172\" height=\"159\">");
		output.append("<img class=\"right-pic zoom-this\" src=\""+"samples/steve-jobs/pics/highschool.jpg"+"\" width=\"172\" height=\"159\">");
		output.append("<img class=\"left-pic zoom-this\" src=\""+"samples/steve-jobs/pics/highschool.jpg"+"\" width=\"172\" height=\"159\">");
		output.append("<img class=\"right-pic zoom-this\" src=\""+"samples/steve-jobs/pics/highschool.jpg"+"\" width=\"172\" height=\"159\">");
		output.append("<img class=\"left-pic zoom-this\" src=\""+"samples/steve-jobs/pics/highschool.jpg"+"\" width=\"172\" height=\"159\">");
		output.append("<img class=\"right-pic zoom-this\" src=\""+"samples/steve-jobs/pics/highschool.jpg"+"\" width=\"172\" height=\"159\">");
		output.append("<img class=\"left-pic zoom-this\" src=\""+"samples/steve-jobs/pics/highschool.jpg"+"\" width=\"172\" height=\"159\">");
		output.append("<img class=\"right-pic zoom-this\" src=\""+"samples/steve-jobs/pics/highschool.jpg"+"\" width=\"172\" height=\"159\">");
		output.append("<p> " +" dkjfksdjbvkdvjkdvjbkvbdkvjbdvkjd " + " \n");
		output.append("</div> \n");
		output.append("<span class=\"page-number\">"+i+"</span> \n");
		
		output.close();
		System.out.println("wselna file nnnnnnnnnnnnnnnnnnn   = "+i);
		}
		System.out.println("file dzed hmd :)))))))))))))))))))))");
	}
	public void messageTo(String to,String message){
		FacesMessage facesMessage =

				new FacesMessage(message);

		FacesContext.getCurrentInstance()
		.addMessage(to, facesMessage);

	}
}
