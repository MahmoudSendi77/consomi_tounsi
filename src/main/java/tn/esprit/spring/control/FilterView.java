package tn.esprit.spring.control;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;



import tn.esprit.spring.entities.Command;
import tn.esprit.spring.service.CommandService;

@Named("dtFilterView")
@ViewScoped
public class FilterView implements Serializable {

	private List<Command> command1;
	private List<Command> command2;

	private List<Command> filteredCommand1;
	private List<Command> filteredCommand2;

	@Autowired
	private CommandService service;

	@PostConstruct
	public void init() {
		command1 = service.afficheAllCommand();
		command2 = service.afficheAllCommand();
	}

	public boolean filterByPrice(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim();
		if (filterText == null || filterText.equals("")) {
			return true;
		}

		if (value == null) {
			return false;
		}

		return ((Comparable) value).compareTo(getInteger(filterText)) > 0;
	}

	

	private int getInteger(String string) {
		try {
			return Integer.valueOf(string);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}

	public List<Command> getCommand1() {
		return command1;
	}
	public void setCommand1(List<Command> command1) {
		this.command1 = command1;
	}
	public List<Command> getCommand2() {
		return command2;
	}
	public void setCommand2(List<Command> command2) {
		this.command2 = command2;
	}
	public List<String> getStatus() {
	        return service.getStatus();
	    }

	public List<Command> getFilteredCommand1() {
		return filteredCommand1;
	}

	public void setFilteredCommand1(List<Command> filteredCommand1) {
		this.filteredCommand1 = filteredCommand1;
	}

	public List<Command> getFilteredCommand2() {
		return filteredCommand2;
	}

	public void setFilteredCommand2(List<Command> filteredCommand2) {
		this.filteredCommand2 = filteredCommand2;
	}
	
}