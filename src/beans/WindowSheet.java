package beans;

import java.io.Serializable;

public class WindowSheet implements Serializable {
	
	
	String designType ;
	

	/**
	 * @return the designType
	 */
	public String getDesignType() {
		return designType;
	}

	/**
	 * @param designType the designType to set
	 */
	public void setDesignType(String designType) {
		this.designType = designType;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -495377846383122604L;
	String windowName;

	/**
	 * @return the columnSerialNo
	 */
	public int getColumnSerialNo() {
		return columnSerialNo;
	}

	/**
	 * @param columnSerialNo
	 *            the columnSerialNo to set
	 */
	public void setColumnSerialNo(int columnSerialNo) {
		this.columnSerialNo = columnSerialNo;
	}

	int columnSerialNo = 0;

	/**
	 * @return the windowName
	 */
	public String getWindowName() {
		return windowName;
	}

	/**
	 * @param windowName
	 *            the windowName to set
	 */
	public void setWindowName(String windowName) {
		this.windowName = windowName;
	}

	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @param fieldName
	 *            the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * @return the dataType
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * @param dataType
	 *            the dataType to set
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	/**
	 * @return the isStartupdata
	 */
	public int getIsStartupdata() {
		return isStartupdata;
	}

	/**
	 * @param isStartupdata
	 *            the isStartupdata to set
	 */
	public void setIsStartupdata(int isStartupdata) {
		this.isStartupdata = isStartupdata;
	}

	/**
	 * @return the startUpDataName
	 */
	public String getStartUpDataName() {
		return startUpDataName;
	}

	/**
	 * @param startUpDataName
	 *            the startUpDataName to set
	 */
	public void setStartUpDataName(String startUpDataName) {
		this.startUpDataName = startUpDataName;
	}

	/**
	 * @return the customPanelName
	 */
	public String getCustomPanelName() {
		return customPanelName;
	}

	/**
	 * @param customPanelName
	 *            the customPanelName to set
	 */
	public void setCustomPanelName(String customPanelName) {
		this.customPanelName = customPanelName;
	}

	/**
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * @param defaultValue
	 *            the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * @return the nullChecked
	 */
	public boolean isNullChecked() {
		return nullChecked;
	}

	/**
	 * @param nullChecked
	 *            the nullChecked to set
	 */
	public void setNullChecked(boolean nullChecked) {
		this.nullChecked = nullChecked;
	}

	String fieldName;
	String dataType;
	int isStartupdata = 0;
	String startUpDataName;
	String customPanelName;
	String defaultValue;
	boolean nullChecked = false;

	/**
	 * @return the beanName
	 */
	public String getBeanName() {
		return beanName;
	}

	/**
	 * @param beanName
	 *            the beanName to set
	 */
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @param methodName
	 *            the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	String beanName;
	String methodName;

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	String category;

}
