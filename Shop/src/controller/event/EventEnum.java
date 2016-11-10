package controller.event;

public enum EventEnum {

	PRODUCTSBUTTONEVENT("productsButtonEvent"),
	CUSTOMERSBUTTONEVENT("customerButtonEvent"),
	QUITBUTTONEVENT("quitButtonEvent"),
	
	ADDCUSTOMERBUTTONEVENT("addCustomerButtonEvent"),
	CANCELADDCUSTOMERBUTTONEVENT("cancelAddCustomerButtonEvent"),
	OKADDCUSTOMERBUTTONEVENT("okAddCustomerButtonEvent"),
	
	NOACTION("noAction");
	
	private String actionCommand;
	
	private EventEnum(String actionCommand){
		this.actionCommand=actionCommand;
	}
	
	public String getActionCommand(){
		return this.actionCommand;
	}
	
}
