package co.wildsoft.swt.coffee.suppliers;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class SuppliersDialog extends TitleAreaDialog {
	private final static String[] COFFEE_TYPES = {"Brazilian", "Columbian", "Ethiopian", "South African"};

	public static final String TELEPHONE = "Telephone";
	public static final String ADDRESS = "Address";
	public static final String NAME = "Name";
	public static final String COFFEE_TYPE = "Coffee Type";

	public SuppliersDialog(Shell parentShell) {
		super(parentShell);
		setBlockOnOpen(false);
	}

	public int open() {
		int result =  super.open();
		setTitle("Coffee Suppliers");
		setMessage("Coffee Suppliers information");
		return result;
	}
	
	protected void cancelPressed() {
		super.cancelPressed();
		System.out.println("Suppliers NOT Updated");
	}

	protected void okPressed() {
		super.okPressed();
		System.out.println("Suppliers Updated");
	}

	protected Control createDialogArea(Composite parent) {
		Composite composite =  (Composite)super.createDialogArea(parent);
		Composite tableComposite = new Composite(composite, SWT.NONE);
		tableComposite.setLayout(new GridLayout(1, false));
		GridData layout = new GridData(SWT.FILL, SWT.FILL, true, false);
		layout.heightHint = 200;
		layout.widthHint = 600;
		tableComposite.setLayoutData(layout);
		createTableViewer(tableComposite);
		return composite;
	}
	
	private void createTableViewer(Composite parent){
		final TableViewer viewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.RESIZE | SWT.V_SCROLL | SWT.H_SCROLL);
		viewer.setUseHashlookup(true);
		viewer.getTable().setHeaderVisible(true);
		viewer.getTable().setLinesVisible(true);
		
		//setup columns
		viewer.setColumnProperties(new String[]{COFFEE_TYPE, NAME, ADDRESS, TELEPHONE});
		//coffee type
		TableColumn column = new TableColumn(viewer.getTable(), SWT.LEFT, 0);
	    column.setText(COFFEE_TYPE);
	    column.setWidth(200);
		//name
		column = new TableColumn(viewer.getTable(), SWT.LEFT, 1);
	    column.setText(NAME);
	    column.setWidth(200);
		//address
		column = new TableColumn(viewer.getTable(), SWT.LEFT, 2);
	    column.setText(ADDRESS);
	    column.setWidth(250);
		//telephone
		column = new TableColumn(viewer.getTable(), SWT.LEFT, 3);
	    column.setText(TELEPHONE);
	    column.setWidth(200);
	     
	    //setup providers 
		viewer.setContentProvider(new SuppliersContentProvider());
		viewer.setLabelProvider(new SuppliersLabelProvider());
		viewer.setCellModifier(new SuppliersCellModifier(viewer));
		createEditors(viewer);
		
//		viewer.getTable().getVerticalBar().setVisible(true);
		viewer.setInput(this);
	}
	
	private void createEditors(TableViewer viewer){
		final CellEditor[] editors = new CellEditor[viewer.getColumnProperties().length];
		//coffee type
		editors[0] = new ComboBoxCellEditor(viewer.getTable(), COFFEE_TYPES, SWT.READ_ONLY );
		//name
		editors[1] = new TextCellEditor(viewer.getTable());
		//address
		editors[2] = new TextCellEditor(viewer.getTable());
		//telephone
		editors[3] = new TextCellEditor(viewer.getTable());
		viewer.setCellEditors(editors);
	}

	static class SuppliersContentProvider implements IStructuredContentProvider {
		private static final Supplier[] SUPPLIERS = {
			new Supplier(0, "ABC", "123 Short Street, CT", "021-5552222"),
			new Supplier(1, "French Importers", "2 Kloofnek str, CT", "021-4815555"),
			new Supplier(2, "Cool Runnings", "6 Industry Rd, Atlantis", "021-3333333"),
			new Supplier(3, "The Mafia Coffee Importers", "1 Beach Rd, Sea Point", "021-2222222"),
			new Supplier(0, "ABC", "123 Short Street, CT", "021-5552222"),
			new Supplier(1, "French Importers", "2 Kloofnek str, CT", "021-4815555"),
			new Supplier(2, "Cool Runnings", "6 Industry Rd, Atlantis", "021-3333333"),
			new Supplier(3, "The Mafia Coffee Importers", "1 Beach Rd, Sea Point", "021-2222222"),
			new Supplier(0, "ABC", "123 Short Street, CT", "021-5552222"),
			new Supplier(1, "French Importers", "2 Kloofnek str, CT", "021-4815555"),
			new Supplier(0, "ABC", "123 Short Street, CT", "021-5552222"),
			new Supplier(1, "French Importers", "2 Kloofnek str, CT", "021-4815555"),
			new Supplier(2, "Cool Runnings", "6 Industry Rd, Atlantis", "021-3333333"),
			new Supplier(3, "The Mafia Coffee Importers", "1 Beach Rd, Sea Point", "021-2222222"),
			new Supplier(0, "ABC", "123 Short Street, CT", "021-5552222"),
			new Supplier(1, "French Importers", "2 Kloofnek str, CT", "021-4815555"),
			new Supplier(2, "Cool Runnings", "6 Industry Rd, Atlantis", "021-3333333"),
			new Supplier(3, "The Mafia Coffee Importers", "1 Beach Rd, Sea Point", "021-2222222"),
			new Supplier(0, "ABC", "123 Short Street, CT", "021-5552222"),
			new Supplier(1, "French Importers", "2 Kloofnek str, CT", "021-4815555"),
			new Supplier(2, "Cool Runnings", "6 Industry Rd, Atlantis", "021-3333333")
		};
		
		public Object[] getElements(Object inputElement) {
			return SUPPLIERS;
		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	static class SuppliersLabelProvider implements ITableLabelProvider {

		public String getColumnText(Object element, int columnIndex) {
			final Supplier supplier = (Supplier)element;
			switch (columnIndex) {
			case 0: return COFFEE_TYPES[supplier.getCoffeetype().intValue()];
			case 1: return supplier.getName();
			case 2: return supplier.getAddress();
			case 3: return supplier.getTelephone();
			}
			return "";
		}

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public void addListener(ILabelProviderListener listener) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void dispose() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isLabelProperty(Object element, String property) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void removeListener(ILabelProviderListener listener) {
			// TODO Auto-generated method stub
			
		}
	}
	
	static class SuppliersCellModifier implements ICellModifier {
		private final TableViewer viewer;
		
		SuppliersCellModifier(TableViewer viewer){
			this.viewer = viewer;
		}
		
		public boolean canModify(Object element, String property) {
			return true;
		}

		public Object getValue(Object element, String property) {
			final Supplier supplier = (Supplier)element;
			if (property.equals(COFFEE_TYPE)) return supplier.getCoffeetype();
			if (property.equals(NAME)) return supplier.getName();
			if (property.equals(ADDRESS)) return supplier.getAddress();
			if (property.equals(TELEPHONE)) return supplier.getTelephone();
			return null;
		}

		public void modify(Object element, String property, Object value) {
			final TableItem item = (TableItem)element;
			final Supplier supplier = (Supplier)item.getData();
			if (property.equals(COFFEE_TYPE)) supplier.setCoffeetype((Integer)value);
			if (property.equals(NAME)) supplier.setName(value.toString());
			if (property.equals(ADDRESS)) supplier.setAddress(value.toString());
			if (property.equals(TELEPHONE)) supplier.setTelephone(value.toString());
			viewer.update(supplier, new String[]{property});
		}
		
	}
}
