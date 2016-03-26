package apps.window.util.propertyTable;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import util.commonUTIL;

import apps.window.util.propertyUtil.PropertyGenerator;
import beans.WindowTableModelMapping;
import com.jidesoft.grid.Property;

import constants.JavaFileGeneratorConstants;
import constants.PropertyPaneConstants;
import constants.WindowTableModelMappingConstants;


public class WindowTableModelMappingPropertyTable  extends WindowPropertyTable   {
	List< Property> windowtablemodelmappingProperties = null;
	public WindowTableModelMapping windowtablemodelmapping ;
	
	
	
	
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
					}
					
	
	
		public WindowTableModelMappingPropertyTable(String name,WindowTableModelMapping windowtablemodelmapping ) {
			this.name = name;
		setWindowTableModelMapping(windowtablemodelmapping);
		}



		@Override
		public List< Property> addListenerToProperty(List< Property> properties) {
			 for(  int i=0;i<properties.size();i++) {
					Property property = properties.get(i);
					if(property.getName().equalsIgnoreCase(WindowTableModelMappingConstants.BEANNAME)){
						addListenerToProperty(property,properties);
					}
					if(property.getName().equalsIgnoreCase(WindowTableModelMappingConstants.COLUMNDATATYPE)) {
						addListenerToProperty(property,properties);
					}
						 
						 
				
				}
		return properties;
		}
			// add listener to the property
				
					private void addListenerToProperty(final Property property ,final List< Property> properties  ) {
						if(property.getName().trim().equalsIgnoreCase(WindowTableModelMappingConstants.BEANNAME)) {
						property.addPropertyChangeListener(Property.PROPERTY_VALUE, new PropertyChangeListener() { 
							public void propertyChange(PropertyChangeEvent evt) {
								 
								if(property.getValue() != null) {
									Property p = propertyTable.getPropertyTableModel().getProperty(WindowTableModelMappingConstants.METHODNAME);
									if(p == null) {
								      p  =  PropertyGenerator.getMethodNames(property.getValue().toString(), WindowTableModelMappingConstants.METHODNAME, property.getCategory() );
								            properties.add(p);
									} else {
										 int index = propertyTable.getPropertyTableModel().getPropertyIndex(p);
										 propertyTable.getPropertyTableModel().getOriginalProperties().remove(index-1);
										 propertyTable.getPropertyTableModel().refresh();
										 p  =  PropertyGenerator.getMethodNames(property.getValue().toString(), WindowTableModelMappingConstants.METHODNAME, property.getCategory());
								            properties.add(p);
										 
									}
								
								 
								
							}
								
							}
				
					});
						} 
						if(property.getName().trim().equalsIgnoreCase(WindowTableModelMappingConstants.COLUMNDATATYPE)) {
							property.addPropertyChangeListener(Property.PROPERTY_VALUE, new PropertyChangeListener() { 
								public void propertyChange(PropertyChangeEvent evt) {
									if(property.getValue() != null && ((String)property.getValue()).equalsIgnoreCase("Others")) {
										addNewBeanPropertyToPropertyTable(property, propertyTable, WindowTableModelMappingConstants.CUSTOMCOLUMNNAME, properties);
									} else {
										removePropertyFromPropertyList(WindowTableModelMappingConstants.CUSTOMCOLUMNNAME,propertyTable); 
									}
								}
							});
					 
						 
					}
					}
/**
* @return the windowtablemodelmapping
*/
public WindowTableModelMapping getWindowTableModelMapping() {
return windowtablemodelmapping;
}
	/**
* @param windowtablemodelmapping the windowtablemodelmapping to set
*/
public void setWindowTableModelMapping(WindowTableModelMapping bean) {
this.windowtablemodelmapping = bean;
}
}
