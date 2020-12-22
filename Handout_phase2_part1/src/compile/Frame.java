package compile;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


public class Frame {
	
	private static String BEGIN = 
			  ".class public %s\n"
	        + ".super java/lang/Object\n";

	private static String END = 
			
		      ".method public <init>()V\n"
		    + "\taload_0\n"
		    + "\tinvokenonvirtual java/lang/Object/<init>()V\n"
		    + "\treturn\n"
	        + ".end method\n";
	
	private Frame father;
	private String id;
	private int counter;
	private Map<String, String> fields;
	
	

	public Frame(Frame father, String id) {
		this.father = father;
		this.id = id;
		this.fields = new HashMap<>();
		this.fields.put("sl", father == null ? "Ljava/lang/Object;" : "L"+this.father.getId()+";");
	}

	public Frame getFather() {
		return father;
	}
	
	public String getId() {
		return id;
	}
	

	public String addField(String type) {
		String field = String.format("x%s", counter++);
		fields.put(field, type);
		return field;
	}
	
	 public String getType(String field){
	        return fields.get(field);
	    }
	 
	 public void write() throws IOException {
			
			try {
				File file = new File(id + ".j");
				PrintWriter writer = new PrintWriter(file);
				
				writer.write(String.format(BEGIN, id));
				
				
				for(Map.Entry<String, String> entry : fields.entrySet() ) {
					writer.println(String.format(".field public %s %s", entry.getKey(), entry.getValue()));
				}
				
				writer.write(END);
				writer.flush();
				writer.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
}
