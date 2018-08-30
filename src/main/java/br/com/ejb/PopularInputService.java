package br.com.ejb;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ejb.Stateless;

@Stateless
public class PopularInputService {

	public String gerarOutput(String input) {
		StringBuilder output = new StringBuilder();
		
		AtomicInteger counter = new AtomicInteger(0);
		List<String> rows = Arrays.asList(input.split("\n"));
		
		rows.forEach(row -> {
			int begin = row.contains(":") ? row.indexOf(":")+1 : 1;
			int end = row.indexOf(">");
			
			String value = row.substring(begin, end);
			if(row.contains("?")) {
				counter.addAndGet(1);
				output.append("\n" + row.replace("?", value + counter));
			}else {
				output.append("\n" + row);
			}
		});
		
		return output.toString();
	}
}
