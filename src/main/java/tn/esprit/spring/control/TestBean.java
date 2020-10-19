package tn.esprit.spring.control;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.primefaces.event.FileUploadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component("testBean")
@Scope("view")
public class TestBean implements Serializable {

    private static final long serialVersionUID = -3568561158943789169L;

    private static final Logger log = LoggerFactory.getLogger(TestBean.class);

    public void handleFileUpload(FileUploadEvent event) {
        log.info("####### handleFileUpload,file: " + event.getFile().getFileName());
    }   

}
