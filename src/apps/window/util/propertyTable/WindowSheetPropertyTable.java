package apps.window.util.propertyTable;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import util.commonUTIL;

import apps.window.util.propertyUtil.BasePropertyTable;
import apps.window.util.propertyUtil.PropertyGenerator;
import beans.WindowSheet;

import com.jidesoft.grid.Property;
import com.jidesoft.grid.PropertyTable;

import constants.JavaFileGeneratorConstants;
import constants.WindowSheetConstants;
import constants.WindowTableModelMappingConstants;

public class WindowSheetPropertyTable implements PropertyChangeListener {

	List<Property> windowSheetProperties = null;

	public WindowSheet wSheet;

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

	public PropertyTable propertyTable = null;

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		Property property = propertyTable.getSelectedProperty();

	}

	String name = "";

	public WindowSheetPropertyTable(String name, WindowSheet windowSheet) {

		this.name = name;
		setwSheet(windowSheet);

	}

	public PropertyTable getWindowSheetPropertyTable() {

		// windowSheetProperties = getWindowSheetProperties();
		setPropertyTable(BasePropertyTable
				.makePropertyTable(windowSheetProperties));
		return propertyTable;

	}

	public PropertyTable getWindowSheetPropertyTable(List<Property> properties) {

		windowSheetProperties = getWindowSheetProperties(properties);
		setPropertyTable(BasePropertyTable
				.makePropertyTable(windowSheetProperties));
		return propertyTable;

	}

	private List<Property> getWindowSheetProperties(List<Property> properties) {
		// TODO Auto-generated method stub

		// final List< Property> properties =
		// WindowSheetEnumList.WINDOWSHEET.getPropertyList(WindowSheetConstants.WINDOWNAME);
		for (int i = 0; i < properties.size(); i++) {
			Property property = properties.get(i);
			if (property.getName().equalsIgnoreCase(
					WindowSheetConstants.ISSTARTUPDATA)) {
				addListenerToProperty(property, properties);
			}
			if (property.getName().equalsIgnoreCase(
					WindowSheetConstants.STARTUPDATANAME)) {
				addListenerToProperty(property, properties);
			}
			if (property.getName().equalsIgnoreCase(
					JavaFileGeneratorConstants.BEANNAME)) {
				addListenerToProperty(property, properties);
			}
			if (property.getName().equalsIgnoreCase(
					WindowSheetConstants.DESIGNTYPE)) {
				addListenerToProperty(property, properties);
			}

		}

		return properties;
	}

	public void setfillValues() {
		try {
			wSheet = new WindowSheet();
			if (propertyTable.getValueAt(1, 1) != null)
				wSheet.setDesignType(propertyTable.getValueAt(1, 1).toString());
			if (propertyTable.getValueAt(2, 1) != null)
				wSheet.setWindowName(propertyTable.getValueAt(2, 1).toString());
			if (propertyTable.getValueAt(3, 1) != null)
				wSheet.setFieldName(propertyTable.getValueAt(3, 1).toString());
			if (propertyTable.getValueAt(4, 1) != null)
				wSheet.setDataType(propertyTable.getValueAt(4, 1).toString());
			if (propertyTable.getValueAt(5, 1) != null)
				wSheet.setCategory(propertyTable.getValueAt(5, 1).toString());
			if (propertyTable.getValueAt(6, 1) != null
					&& ((Boolean) propertyTable.getValueAt(6, 1)) == true) {

				wSheet.setIsStartupdata(1);
			}
			if (wSheet.getIsStartupdata() == 1) {
				if (propertyTable.getValueAt(7, 1) != null)
					wSheet.setStartUpDataName(propertyTable.getValueAt(7, 1)
							.toString());
			}
			if (propertyTable.getValueAt(8, 1) != null)
				wSheet.setDefaultValue(propertyTable.getValueAt(8, 1)
						.toString());
			if (propertyTable.getValueAt(9, 1) != null)
				wSheet.setCustomPanelName(propertyTable.getValueAt(9, 1)
						.toString());
			if (propertyTable.getValueAt(10, 1) != null
					&& ((Boolean) propertyTable.getValueAt(10, 1)) == true) {

				wSheet.setNullChecked(true);
			}

			if (propertyTable.getValueAt(11, 1) != null) {

				wSheet.setBeanName(propertyTable.getValueAt(10, 1).toString());
			}
			if (propertyTable.getModel().getRowCount() > 11) {
				if (propertyTable.getValueAt(12, 1) != null) {

					wSheet.setMethodName(propertyTable.getValueAt(12, 1)
							.toString());
				}
			}

		} catch (Exception e) {
			commonUTIL.displayError("WindowSheetPropertyTable",
					"setfillValues", e);

		}

	}

	public void clearPropertyValues() {
		// CurrencyDefaultPropertyEnumList.clearALLList();
		try {
			if(propertyTable != null) {
			propertyTable.clearSelection();
			propertyTable.getPropertyTableModel().reloadProperties();
			}

		} catch (Exception e) {
			commonUTIL.displayError("WindowSheetPropertyTable",
					"clearPropertyValues", e);
			return;
		}

	}

	private void addListenerToProperty(final Property property,
			final List<Property> properties) {
		if (property.getName().trim()
				.equalsIgnoreCase(WindowSheetConstants.ISSTARTUPDATA)) {
			property.addPropertyChangeListener(Property.PROPERTY_VALUE,
					new PropertyChangeListener() {
						public void propertyChange(PropertyChangeEvent evt) {
							Boolean isStartUpRequired = (Boolean) evt
									.getNewValue();

							if (isStartUpRequired != null) {
								if (isStartUpRequired.booleanValue() == true)
									(getPropertyName(
											properties,
											WindowSheetConstants.STARTUPDATANAME))
											.setEditable(true);
								if (isStartUpRequired.booleanValue() == false) {
									(getPropertyName(
											properties,
											WindowSheetConstants.STARTUPDATANAME))
											.setValue(null);
									(getPropertyName(
											properties,
											WindowSheetConstants.STARTUPDATANAME))
											.setEditable(false);
								}
							}

						}

					});
		}
		if (property.getName().trim()
				.equalsIgnoreCase(WindowSheetConstants.STARTUPDATANAME)) {
			property.addPropertyChangeListener(Property.PROPERTY_VALUE,
					new PropertyChangeListener() {
						public void propertyChange(PropertyChangeEvent evt) {
							if (!property.isEditable()) {
								property.setValue(null);
							}

						}

					});

		}

		if (property.getName().trim()
				.equalsIgnoreCase(WindowSheetConstants.BEANNAME)) {
			property.addPropertyChangeListener(Property.PROPERTY_VALUE,
					new PropertyChangeListener() {
						public void propertyChange(PropertyChangeEvent evt) {

							if (property.getValue() != null) {
								String value = (String) property.getValue();

								Property p = propertyTable
										.getPropertyTableModel()
										.getProperty(
												WindowSheetConstants.METHODNAME);
								if (p == null
										&& !value.equalsIgnoreCase("NONE")) {

									p = PropertyGenerator
											.getMethodNames(
													property.getValue()
															.toString(),
													WindowTableModelMappingConstants.METHODNAME,
													property.getCategory());
									properties.add(p);
								} else {
									if (!value.equalsIgnoreCase("NONE")) {
										int index = propertyTable
												.getPropertyTableModel()
												.getPropertyIndex(p);
										propertyTable.getPropertyTableModel()
												.getOriginalProperties()
												.remove(index - 1);
										propertyTable.getPropertyTableModel()
												.refresh();
										p = PropertyGenerator
												.getMethodNames(
														property.getValue()
																.toString(),
														WindowTableModelMappingConstants.METHODNAME,
														property.getCategory());
										properties.add(p);
										propertyTable.getPropertyTableModel()
												.refresh();
									} else {
										int index = propertyTable
												.getPropertyTableModel()
												.getPropertyIndex(p);
										propertyTable.getPropertyTableModel()
												.getOriginalProperties()
												.remove(index - 1);
										propertyTable.getPropertyTableModel()
												.refresh();
									}

								}

							}

						}

					});

		}
		if (property.getName().trim()
				.equalsIgnoreCase(WindowSheetConstants.DESIGNTYPE)) {
			property.addPropertyChangeListener(Property.PROPERTY_VALUE,
					new PropertyChangeListener() {
						public void propertyChange(PropertyChangeEvent evt) {
							String name = (String) evt.getNewValue();
							Property prop = PropertyGenerator
									.createPropertyFromStartUp(name + "Name",
											name + "Name",
											property.getCategory());
							if (prop.getName().equalsIgnoreCase(
									WindowSheetConstants.WINDOWNAME)) {
								Property p = propertyTable
										.getPropertyTableModel().getProperty(
												WindowSheetConstants.FIELDNAME);

								Property propfield = PropertyGenerator
										.getStringProperty(
												WindowSheetConstants.FIELDNAME,
												WindowSheetConstants.FIELDNAME,
												property.getCategory());
								if (propfield != null) {
									int index = propertyTable
											.getPropertyTableModel()
											.getPropertyIndex(p);

									propertyTable.getPropertyTableModel()
											.getOriginalProperties()
											.remove(index - 1);
									propertyTable.getPropertyTableModel()
											.refresh();

									properties.add(index - 1, propfield);
									propertyTable.getPropertyTableModel()
											.refresh();
								}
							}

							if (prop != null) {

								propertyTable.getPropertyTableModel()
										.getOriginalProperties().remove(1);
								propertyTable.getPropertyTableModel().refresh();

								prop.addPropertyChangeListener(
										Property.PROPERTY_VALUE,
										new PropertyChangeListener() {
											public void propertyChange(
													PropertyChangeEvent evt) {
												Property attributeTypeName = (Property) evt
														.getSource();
												if (attributeTypeName
														.getName()
														.equalsIgnoreCase(
																WindowSheetConstants.ATTRIBUTENAME)) {
													Property p = propertyTable
															.getPropertyTableModel()
															.getProperty(
																	WindowSheetConstants.FIELDNAME);

													Property propfield = PropertyGenerator
															.createPropertyFromStartUp(
																	WindowSheetConstants.FIELDNAME,
																	attributeTypeName
																			.getValue()
																			+ "Attributes",
																	property.getCategory());
													if (propfield != null) {
														int index = propertyTable
																.getPropertyTableModel()
																.getPropertyIndex(
																		p);

														propertyTable
																.getPropertyTableModel()
																.getOriginalProperties()
																.remove(index - 1);
														propertyTable
																.getPropertyTableModel()
																.refresh();

														properties.add(
																index - 1,
																propfield);
														propertyTable
																.getPropertyTableModel()
																.refresh();
													}

												} else if (attributeTypeName
														.getName()
														.equalsIgnoreCase(
																WindowSheetConstants.WINDOWNAME)) {
													Property p = propertyTable
															.getPropertyTableModel()
															.getProperty(
																	WindowSheetConstants.FIELDNAME);

													Property propfield = PropertyGenerator
															.getStringProperty(
																	WindowSheetConstants.FIELDNAME,
																	WindowSheetConstants.FIELDNAME,
																	property.getCategory());
													if (propfield != null) {
														int index = propertyTable
																.getPropertyTableModel()
																.getPropertyIndex(
																		p);

														propertyTable
																.getPropertyTableModel()
																.getOriginalProperties()
																.remove(index - 1);
														propertyTable
																.getPropertyTableModel()
																.refresh();

														properties.add(
																index - 1,
																propfield);
														propertyTable
																.getPropertyTableModel()
																.refresh();
													}
												}
											}

										});
								properties.add(1, prop);

							}
						}

					});
		}

	}

	private Property getPropertyName(List<Property> properties, String name) {
		Property property = null;
		for (int i = 0; i < properties.size(); i++) {
			property = properties.get(i);
			if (property.getName().equalsIgnoreCase(name)) {
				break;
			}
		}
		return property;
	}

	/**
	 * @return the wSheet
	 */
	public WindowSheet getwSheet() {
		return wSheet;
	}

	/**
	 * @param wSheet
	 *            the wSheet to set
	 */
	public void setwSheet(WindowSheet window) {
		this.wSheet = window;
	}

	public void setPropertiesValues(WindowSheet firstRecord) {
		// TODO Auto-generated method stub
		propertyTable.clearSelection();
		propertyTable.setValueAt(firstRecord.getDesignType(), 1, 1);
		propertyTable.setValueAt(firstRecord.getWindowName(), 2, 1);

		propertyTable.setValueAt(firstRecord.getFieldName(), 3, 1);
		propertyTable.setValueAt(firstRecord.getDataType(), 4, 1);
		propertyTable.setValueAt(firstRecord.getCategory(), 5, 1);
		if (firstRecord.getIsStartupdata() == 1)
			propertyTable.setValueAt(true, 6, 1);
		else
			propertyTable.setValueAt(false, 6, 1);
		propertyTable.setValueAt(firstRecord.getStartUpDataName(), 7, 1);
		propertyTable.setValueAt(firstRecord.getDefaultValue(), 8, 1);

		propertyTable.setValueAt(firstRecord.getCustomPanelName(), 9, 1);

		propertyTable.setValueAt(firstRecord.isNullChecked(), 10, 1);
		propertyTable.setValueAt(firstRecord.getBeanName(), 11, 1);
		if (!commonUTIL.isEmpty(firstRecord.getMethodName()))
			propertyTable.setValueAt(firstRecord.getMethodName(), 12, 1);
		setwSheet(firstRecord);
	}

}