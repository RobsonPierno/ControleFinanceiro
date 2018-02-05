package br.com.beans;

import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PopularInputBean {
	
	private String input;
	private String output;
	
	public void popular() {
		List<String> rows = Arrays.asList(this.input.split("\n"));
		rows.forEach(row -> {
			int begin = row.indexOf(":")+1;
			int end = row.indexOf(">");
			String value = row.substring(begin, end);
			this.output +=  "\n" + row.replace("?", value);
		});
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