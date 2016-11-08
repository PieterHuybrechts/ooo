package controller.event;

public enum EventEnum {

	PRODUCTSBUTTONLISTENER("productsButtonEvent"),
	QUITBUTTONLISTENER("quitButtonEvent"),
	CUSTOMERSBUTTONLISTENER("customerButtonListener");
	
	private String actionCommand;
	
	private EventEnum(String actionCommand){
		this.actionCommand=actionCommand;
	}
	
	public String getActionCommand(){
		return this.actionCommand;
	}
	
}
