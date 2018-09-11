package br.com.bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ejb.InserirCondicaoService;

@ManagedBean
@ViewScoped
public class InserirCondicaoBean extends ValidateSession {

	@EJB
	private InserirCondicaoService inserirCondicaoService;
	
	private String input;
	private String output;
	
	public void popular() {
		this.output = this.inserirCondicaoService.gerarOutput(this.getInput());
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