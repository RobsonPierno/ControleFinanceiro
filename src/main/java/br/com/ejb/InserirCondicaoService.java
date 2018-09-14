package br.com.ejb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

@Stateless
public class InserirCondicaoService {
	
	private List<String> hierarquiaDeTags;

	public String gerarOutput(String input) {
		List<String> rows = Arrays.asList(input.split("\n"));
		Map<String, String> valuesPaths = new HashMap<>();
		this.setHierarquiaDeTags(new ArrayList<>());
		
		boolean afterHead = false;

		String tag = "";
		for(String row : rows) {
			
			afterHead = !afterHead ? row.contains("<xsl:template") : true;
			
			if(!row.trim().equals("")) {
				
				if(row.contains("value-of")) {
					int begin = row.indexOf("\"")+1;
					int end = row.lastIndexOf("\"");
					
					String tagComPath = this.contruirEstruturaDaTag(); 
					valuesPaths.put(tagComPath, row.substring(begin, end));
					
				}else if(this.isTagRow(afterHead, row)) {
					int begin = row.contains(":") ? row.indexOf(":")+1 : row.indexOf("<")+1;
					int end = row.indexOf(">");
					tag = row.substring(begin, end);
					
					if(row.contains("</")) {
						this.getHierarquiaDeTags().remove(tag);
					}else {
						this.getHierarquiaDeTags().add(tag);
					}
				}
			}
		}
		
		return this.contruirOutputComCondicoes(rows, valuesPaths);
	}
	
	private String contruirOutputComCondicoes(List<String> rows, Map<String, String> valuesPaths) {
		StringBuilder output = new StringBuilder();
		
		this.setHierarquiaDeTags(new ArrayList<>());
		boolean afterHead = false;
		
		for(String row : rows) {
			afterHead = !afterHead ? row.contains("<xsl:template") : true;
			
			if(this.isTagRow(afterHead, row)) {
				int begin = row.contains(":") ? row.indexOf(":")+1 : row.indexOf("<")+1;
				int end = row.indexOf(">");
				
				String tag = row.substring(begin, end);
				if(!row.contains("</")) {
					this.getHierarquiaDeTags().add(tag);
				}
				
				String tagComPath = this.contruirEstruturaDaTag(); 
				if(valuesPaths.containsKey(tagComPath)) {
					if(!row.contains("/") ) {
						String space = row.substring(0, row.indexOf("<"));
						
						output.append("\n");
						output.append(space);
						output.append("<xsl:if test=\"");
						output.append(valuesPaths.get(tagComPath));
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
				
				if(row.contains("</")) {
					this.getHierarquiaDeTags().remove(tag);
				}
			}else {
				if(!row.contains("if test") && !row.contains("if>")) {
					output.append("\n");
					if(row.contains("value-of")) {
						output.append("	");
					}
					output.append(row);
				}
			}
		}
		
		return output.toString();
	}

	private String contruirEstruturaDaTag() {
		StringBuilder tagComPath = new StringBuilder();
		
		this.getHierarquiaDeTags().forEach(tagFromList -> {
			tagComPath.append(tagFromList);
			tagComPath.append("/");
		});
		
		return tagComPath.toString();
	}
	
	private boolean isTagRow(boolean afterHead, String row) {
		return 	afterHead &&
				!row.trim().equals("") && 
				row.contains("<") && 
				row.contains(">") && 
				!row.contains("value-of") && 
				!row.contains("for-each") &&
				!row.contains("if test") &&
				!row.contains("if>");
	}

	public List<String> getHierarquiaDeTags() {
		return hierarquiaDeTags;
	}

	public void setHierarquiaDeTags(List<String> hierarquiaDeTags) {
		this.hierarquiaDeTags = hierarquiaDeTags;
	}
}
