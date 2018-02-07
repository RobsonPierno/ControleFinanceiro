package br.com.bean;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class PopularInputBean extends ValidateSession {
	
	private String input;
	private String output;
	
	public void popular() {
		this.output = new String();
		AtomicInteger counter = new AtomicInteger(0);
		List<String> rows = Arrays.asList(this.input.split("\n"));
		rows.forEach(row -> {
			int begin = row.indexOf(":")+1;
			int end = row.indexOf(">");
			String value = row.substring(begin, end);
			if(row.contains("?")) {
				counter.addAndGet(1);
				this.output +=  "\n" + row.replace("?", value + counter);
			}else {
				this.output +=  "\n" + row;
			}
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