package compile;

import java.io.File;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class CodeBlock {

	public static final String BEGIN = ".class public Demo\r\n" + ".super java/lang/Object\r\n" + "\r\n"
			+ ".method public <init>()V\r\n" + "   aload_0\r\n" + "   invokenonvirtual java/lang/Object/<init>()V\r\n"
			+ "   return\r\n" + ".end method\r\n" + "\r\n" + ".method public static main([Ljava/lang/String;)V\r\n"
			+ "       .limit locals  2\r\n" + "       .limit stack 100\r\n" + "\r\n"
			+ "       getstatic java/lang/System/out Ljava/io/PrintStream;\r\n" + "\r\n";

	public static final String END =

			"       invokestatic java/lang/String/valueOf(I)Ljava/lang/String;\r\n"
					+ "       invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V\r\n" + "\r\n"
					+ "       return\r\n" + "\r\n" + ".end method";

	public static final String FRAME = "frame_%d";

	private List<String> instructions;
	private List<Frame> frames;
	private Frame currFrame;
	private int counter;
	private int currFlag;

	public CodeBlock() {

		this.instructions = new LinkedList<>();
		this.frames = new LinkedList<>();
		this.currFrame = null;
		this.counter = 0;
		this.currFlag = 0;
		this.addinstruction("aconst_null");
		this.addinstruction("astore_1");
	}

	public Frame createFrame() {
		String id = String.format(FRAME, counter);
		Frame frame = new Frame(currFrame, id);
		counter++;
		frames.add(frame);

		this.addinstruction("new " + id);
		this.addinstruction("dup");
		this.addinstruction(String.format("invokespecial %s/<init>()V", id));
		this.addinstruction("dup");
		this.addinstruction("aload_1");
		this.addinstruction(
				String.format("putfield %s/sl L%s;", id, currFrame == null ? "java/lang/Object" : currFrame.getId()));
		this.addinstruction("astore_1");
		currFrame = frame;
		return currFrame;

	}
	public void pop() {
		Frame father  = currFrame.getFather();
		this.addinstruction("aload_1");
		this.addinstruction(
				String.format("getfield %s/sl L%s;", currFrame.getId(), father == null ? "java/lang/Object" : father.getId()));
		this.addinstruction("astore_1");
		
		currFrame = father;
	}

	public Frame getCurrFrame() {
		return currFrame;
	}

	public void addinstruction(String instruction) {
		instructions.add(instruction);
	}
	
	public int getCurrFlag() {
		return ++currFlag;
	}

	public void write(String fName) throws IOException {

		try {
			File file = new File(fName);
			PrintWriter writer = new PrintWriter(file);

			writer.write(BEGIN);

			for (String instruction : instructions) {
				writer.println(instruction);
			}

			writer.write(END);
			writer.flush();
			writer.close();
			for ( Frame frame : frames) {
				frame.write();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
