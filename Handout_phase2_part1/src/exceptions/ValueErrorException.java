package exceptions;

@SuppressWarnings("serial")
public class ValueErrorException extends Exception {

	public ValueErrorException(String messageError) {
		super(messageError);

	}
	
	public void printError() {
		System.out.println(this.getMessage());
	}

}
