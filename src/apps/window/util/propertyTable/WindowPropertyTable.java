package apps.window.util.propertyTable;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import util.commonUTIL;

import apps.window.util.propertyUtil.BasePropertyTable;
import apps.window.util.propertyUtil.PropertyEnum;
import apps.window.util.propertyUtil.PropertyGenerator;
import beans.BaseBean;
import beans.JavaFileGenerator;
import beans.Template;

import com.jidesoft.grid.Property;
import com.jidesoft.grid.PropertyTable;

import constants.WindowTableModelMappingConstants;

public abstract class WindowPropertyTable implements PropertyChangeListener {

	public PropertyTable propertyTable = null;
	String name = "";

	/**
	 * @return the bean
	 */
	public BaseBean getBean() {
		return bean;
	}

	/**
	 * @param bean
	 *            the bean to set
	 */
	public void setBean(BaseBean bean) {
		this.bean = bean;

	}

	BaseBean bean = null;

	/**
	 * @return the propertyTable
	 */
	public PropertyTable getPropertyTable() {
		return propertyTable;
	}

	/**
	 * @param propertyTable
	 *            the propertyTable to set
	 */
	public void setPropertyTable(PropertyTable propertyTable) {
		this.propertyTable = propertyTable;
	}

	public abstract List<Property> addListenerToProperty(
			List<Property> properties);

	 

	public PropertyTable getPropertyTable(List<Property> properties) {

		setPropertyTable(BasePropertyTable
				.makePropertyTable(addListenerToProperty(properties)));
		return propertyTable;

	}

	public void setfillValues(BaseBean bean) {
		try {
			try {

				List<Property> prop = propertyTable.getPropertyTableModel()
						.getProperties();
				for (int i = 0; i < prop.size(); i++) {
					Property property = prop.get(i);
					if (property.getValue() != null)
						bean.setPropertyValue(property.getName(),
								property.getValue());
				}
				setBean(bean);

			} catch (Exception e) {
				commonUTIL.displayError("JavaFileGeneratorPropertyTable",
						"setfillValues", e);

			}

		} catch (Exception e) {
			commonUTIL.displayError(name, "setfillValues", e);

		}
	}

	public void setPropertiesValues(BaseBean firstRecord) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		propertyTable.clearSelection();
		List<Property> prop = propertyTable.getPropertyTableModel()
				.getProperties();
		for (int i = 0; i < prop.size(); i++) {
			if (firstRecord.getPropertyValue(prop.get(i).getName()) != null) {
				prop.get(i).setValue(
						firstRecord.getPropertyValue(prop.get(i).getName()));
			}

		}
		setBean(firstRecord);

	}

	// helper method
	public Property getPropertyName(List<Property> properties, String name) {
		Property property = null;
		for (int i = 0; i < properties.size(); i++) {
			property = properties.get(i);
			if (property.getName().equalsIgnoreCase(name)) {
				break;
			}
		}
		return property;
	}
	
	
	// helper method 
	
	public void addNewBeanPropertyToPropertyTable(final Property p,final PropertyTable propertyTab,final String newPropertyName, final List< Property> properties ) {
		
		
		
		
				if(p.getValue() != null) {
					Property newPropertyp = propertyTab.getPropertyTableModel().getProperty(newPropertyName);
					if(newPropertyp == null) {
						
						newPropertyp =  PropertyGenerator.getBeanNames(newPropertyName,newPropertyName, p.getCategory() );
				            properties.add(newPropertyp);
						
					} else {
						int index = propertyTab.getPropertyTableModel().getPropertyIndex(newPropertyp);
						propertyTab.getPropertyTableModel().getOriginalProperties().remove(index-1);
						propertyTab.getPropertyTableModel().refresh();
						newPropertyp  =  PropertyGenerator.getBeanNames(newPropertyName,newPropertyName, p.getCategory());
				            properties.add(newPropertyp);
				            propertyTab.getPropertyTableModel().refresh();
						
					}
				}
			
		
	}
	
	public void clearPropertyValues() { 
		try {
			if(propertyTable == null)
				return;
			if(propertyTable != null) {
				 propertyTable.clearSelection();
				 
					 
			 for(int i=0;i<propertyTable.getPropertyTableModel().getProperties().size();i++) {
				 Property prop  = (Property ) propertyTable.getPropertyTableModel().getProperties().get(i);
				 if(prop instanceof PropertyEnum ) {
					 PropertyEnum<String> p = ( PropertyEnum<String>) prop;
					p.setValue("");
					
					// p.re
				 } else {
					 
					 if(prop.getValue() instanceof String) {
						 prop.setValue("");
					 } else
					 if(prop.getValue() instanceof Double) {
						 prop.setValue(0.0);
					 }else if(prop.getValue() instanceof Boolean) {
						 prop.setValue(false);
					 }else if(prop.getValue() instanceof Integer) {
						 prop.setValue(0);
					 }
				 }
				  
				
				 
			 }
			
			 
			}

		} catch (Exception e) {
			commonUTIL.displayError("WindowSheetPropertyTable",
					"clearPropertyValues", e);
			return;
		}

	}

	public void removePropertyFromPropertyList(String propertyName,final PropertyTable propertyTab) {
		Property newPropertyp = propertyTable.getPropertyTableModel().getProperty(propertyName);
		if(newPropertyp != null) {
			 int index = propertyTab.getPropertyTableModel().getPropertyIndex(newPropertyp);
			 propertyTab.getPropertyTableModel().getOriginalProperties().remove(index-1);
			 propertyTab.getPropertyTableModel().refresh();
			
		}
	}
				 
	public void addNewMethodPropertyToPropertyTable(final Property p,final PropertyTable propertyTab,final String newPropertyName, final List< Property> properties ) {
		
		
		
		
		if(p.getValue() != null) {
			Property newPropertyp = propertyTab.getPropertyTableModel().getProperty(newPropertyName);
			if(newPropertyp == null) {
				
				newPropertyp =  PropertyGenerator.getMethodNames(newPropertyName,newPropertyName, p.getCategory() );
		            properties.add(p);
				
			} else {
				int index = propertyTab.getPropertyTableModel().getPropertyIndex(p);
				propertyTab.getPropertyTableModel().getOriginalProperties().remove(index-1);
				propertyTab.getPropertyTableModel().refresh();
				newPropertyp  =  PropertyGenerator.getMethodNames(newPropertyName,newPropertyName, p.getCategory());
		            properties.add(p);
		            propertyTab.getPropertyTableModel().refresh();
				
			}
		}
	

}
		 
	 
	

}
