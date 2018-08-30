package br.com.bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ejb.PopularInputService;

@ManagedBean
@ViewScoped
public class PopularInputBean extends ValidateSession {

	@EJB
	private PopularInputService popularInputService;
	
	private String input;
	private String output;
	
	public void popular() {
		this.output = this.popularInputService.gerarOutput(this.getInput());
    }
	
    public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
}