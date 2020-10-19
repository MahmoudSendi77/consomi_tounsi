package tn.esprit.spring.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tn.esprit.spring.repository.UserRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {
	
	@Autowired
	Profile profile;
	
	@Autowired
	UserRepository UserRepository;
	@Autowired
	SigninJsf SigninJsf;
	
//	File dir = new File( "ads" + File.separator + type);
	
    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = System.getProperty("user.dir")+"/src/main/webapp/resources/uploads/userProfileImage";

    @GetMapping("/")
    public void index() {
       // return "upload";
    }

    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file  
                                 ) {

        if (file.isEmpty()) {
        	System.out.println("ssssssssssssssssssssssssssssss");
        	
        	
        	profile.redirecToSom();
        	
           // redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
           // return "redirect:uploadStatus";
        }

        try {
        	System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" );
            // Get the file and save it somewhere
        	File dir = new File( UPLOADED_FOLDER);
			
        	if (!dir.exists())
				dir.mkdirs();
            byte[] bytes = file.getBytes();
      
            Path path = Paths.get(UPLOADED_FOLDER +File.separator+ file.getOriginalFilename());
            Files.write(path, bytes);

//            redirectAttributes.addFlashAttribute("message",
//                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
      
            UserRepository.findById(SigninJsf.idusercurrent()).get().setProfileImage(file.getOriginalFilename());
            UserRepository.save( UserRepository.findById(SigninJsf.idusercurrent()).get());
            return "redirect:/login/editprofile.jsf";

        } catch (IOException e) {
        	
            e.printStackTrace();
            profile.redirecToSom();
            return "redirect:/uploadStatus";
        }

      //  return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public void uploadStatus() {
      //  return "uploadStatus";
    }

}