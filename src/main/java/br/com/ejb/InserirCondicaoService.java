package br.com.ejb;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

@Stateless
public class InserirCondicaoService {

	public String gerarOutput(String input) {
		List<String> rows = Arrays.asList(input.split("\n"));
		Map<String, String> valuesPaths = new HashMap<>();

		String tag = "";
		for(String row : rows) {
		
			if(row.contains("value-of")) {
				int begin = row.indexOf("\"")+1;
				int end = row.lastIndexOf("\"");
				valuesPaths.put(tag, row.substring(begin, end));
				
			}else if(!row.contains("for-each") && !row.trim().equals("")) {
				int begin = row.contains(":") ? row.indexOf(":")+1 : 1;
				int end = row.indexOf(">");
				tag = row.substring(begin, end);
				
			}
		}
		
		return this.contruirOutputComCondicoes(rows, valuesPaths);
	}
	
	private String contruirOutputComCondicoes(List<String> rows, Map<String, String> valuesPaths) {
		StringBuilder output = new StringBuilder();
		
		rows.forEach(row -> {
			if(!row.trim().equals("") && !row.contains("head")) {
				int begin = row.contains(":") ? row.indexOf(":")+1 : 1;
				int end = row.indexOf(">");
				
				String tag = row.substring(begin, end);
				if(valuesPaths.containsKey(tag)) {
					if(!row.contains("/") ) {
						String space = row.substring(0, row.indexOf("<"));
						
						output.append("\n");
						output.append(space);
						output.append("<xsl:if test=\"");
						output.append(valuesPaths.get(tag));
						output.append("\">");
						
						output.append("\n");
						output.append("	");
						output.append(row);
						
					}else {
						String space = row.substring(0, row.indexOf("<"));
						output.append("\n");
						output.append("	");
						output.append(row);
						output.append("\n");
						output.append(space);
						output.append("</xsl:if>");
					}
				}else if(row.contains("value-of")) {
					output.append("\n");
					output.append("	");
					output.append(row);
				}else {
					output.append("\n");
					output.append(row);
				}
			}else {
				output.append("\n");
				output.append(row);
			}
		});
		
		return output.toString();
	}
}
