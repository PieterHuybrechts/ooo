package controller.event;

public enum EventEnum {
	
	CUSTOMERSBUTTONEVENT("customerButtonEvent"),
	ADDCUSTOMERBUTTONEVENT("addCustomerButtonEvent"),
	CANCELADDCUSTOMERBUTTONEVENT("cancelAddCustomerButtonEvent"),
	OKADDCUSTOMERBUTTONEVENT("okAddCustomerButtonEvent"),
	OKUPDATEPRODUCTEVENT("okUpdateProductEvent"),

	PRODUCTSBUTTONEVENT("productsButtonEvent"),
	ADDPRODUCTBUTTONEVENT("addProductButtonEvent"),
	CANCELADDPRODUCTBUTTONEVENT("cancelAddProductButtonEvent"),
	OKADDPRODUCTBUTTONEVENT("okAddProductButtonEvent"),
	
	QUITBUTTONEVENT("quitButtonEvent"),
	NOACTION("noAction");
	
	private String actionCommand;
	
	private EventEnum(String actionCommand){
		this.actionCommand=actionCommand;
	}
	
	public String getActionCommand(){
		return this.actionCommand;
	}
	
}
