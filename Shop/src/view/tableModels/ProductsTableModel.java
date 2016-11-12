package view.tableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import controller.ShopController;
import domain.DomainException;
import domain.products.Product;
import domain.products.ProductTypeEnum;
import domain.products.producstates.ProductStateEnum;

public class ProductsTableModel extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String[] columnsNames = {"id","Title","type","Current State"};
	private final List<Product> products;
	private ShopController shopController;
	
	public ProductsTableModel(ShopController shopController) {
		this.products = new ArrayList<Product>();
		this.shopController = shopController;
	}
	
	public void updateTable() throws DomainException{
		this.products.clear();
		this.products.addAll(shopController.getAllProducts());
		fireTableRowsInserted(this.products.size()-1, this.products.size()-1);
	}
	
	public Product getProductAtRow(int row){
		return products.get(row);
	}
	
	@Override
	public int getRowCount() {
		return this.products.size();
	}

	@Override
	public int getColumnCount() {
		return columnsNames.length;
	}

	@Override
	public String getColumnName(int column){
		return columnsNames[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Product p = products.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return p.getId();
		case 1:
			return p.getTitle();
		case 2:
			ProductTypeEnum type=null;
			
			for(ProductTypeEnum t : ProductTypeEnum.values()){
				if(t.getClassName().equals(p.getClass().getName())){
					type = t;
					break;
				}
			}
			
			return type;
			
		case 3:
			return p.getCurrentState();
		default:
			return null;
		}
	}
	
	@Override
	public Class<?> getColumnClass(int column){
		switch(column){
		case 0:
			return int.class;
		case 1:
			return String.class;
		case 2:
			return ProductTypeEnum.class;
		case 3:
			return ProductStateEnum.class;
		default:
			return null;
		}
	}

}
