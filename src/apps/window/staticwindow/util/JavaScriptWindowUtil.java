package apps.window.staticwindow.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import util.ReferenceDataCache;
import util.commonUTIL;
import apps.window.staticwindow.BasePanel;
import apps.window.staticwindow.JavaScriptWindow;
import apps.window.util.tableModelUtil.TableUtils;
import beans.JavaScript;
import beans.Template;
import beans.WindowSheet;
import beans.WindowTableModelMapping;

import com.jidesoft.grid.Property;

import constants.CommonConstants;
import constants.JavaScriptConstants;
import constants.WindowTableModelMappingConstants;

public class JavaScriptWindowUtil extends BaseWindowUtil {
	JavaScriptWindow javaScriptWindow = null;
	JavaScript javaScript = null;
	String javaScriptName = "";

	/**
	 * @return the windowName
	 */
	public String getWindowName() {
		return JavaScriptConstants.WINDOW_NAME;
	}

	/**
	 * @param windowName
	 *            the windowName to set
	 */
	public void setWindowName(String javaScriptName) {
		this.javaScriptName = javaScriptName;
	}

	/**
	 * @return the javaScript
	 */
	public JavaScript getJavaScript() {
		return javaScript;
	}

	/**
	 * @param javaScript
	 *            the javaScript to set
	 */
	public void setJavaScript(JavaScript windowS) {
		javaScript = windowS;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		boolean flag = false;

		return validate(getJavaScript(), JavaScriptConstants.WINDOW_NAME);

	}

	@Override
	public Vector<String> fillData(String action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionMapper(String action) {
		// TODO Auto-generated method stub
		Property prop = javaScriptWindow.propertyTable.getPropertyTable()
				.getSelectedProperty();
		if (action.equalsIgnoreCase(CommonConstants.SAVEASNEWBUTTON)) {
			saveAsNewButtonAction();
		}
		if (action.equalsIgnoreCase(CommonConstants.NEWBUTTON)) {
			newButtonAction();
		}
		if (action.equalsIgnoreCase(CommonConstants.LOADBUTTON)) {
			loadButtonAction();

		}
		if (action.equalsIgnoreCase(JavaScriptConstants.SEARCHTEXTBOX)) {
			searchTextAction();
		}

		if (action.equalsIgnoreCase(CommonConstants.RIGHTSIDECENTERTABLE)) {
			rightSideCenterTableAction();
		}
		if (action.equalsIgnoreCase(CommonConstants.DELETEBUTTON)) {
			deleteButtonAction();
		}
	}

	@Override
	public void setWindow(BasePanel windowName) {
		// TODO Auto-generated method stub
		javaScriptWindow = (JavaScriptWindow) windowName;
		setJavaScript(javaScriptWindow.getJavaScript());

	}

	private void saveAsNewButtonAction() {
		javaScriptWindow.propertyTable
				.setfillValues(javaScriptWindow.propertyTable.getJavaScript());
		setJavaScript(javaScriptWindow.propertyTable.getJavaScript());
		javaScript = javaScriptWindow.propertyTable.getJavaScript();

	}

	private void newButtonAction() {
		javaScriptWindow.propertyTable.clearPropertyValues();
		javaScriptWindow.model.clear();
		setJavaScript(null);
	}

	private void loadButtonAction() {
		javaScriptWindow.propertyTable
				.setfillValues(javaScriptWindow.propertyTable.getJavaScript());
		String windowName = javaScriptWindow.propertyTable.getJavaScript()
				.getWindowName();
		javaScriptWindow.textarea.setText("");
		String script = "";
		if (javaScriptWindow.propertyTable.getJavaScript().getScriptName()
				.equalsIgnoreCase(JavaScriptConstants.CONSTANTS)) {
			script = createWindowConstantsScript(windowName);
			writeStringToFile(JavaScriptConstants.CONSTANTPATH, script,
					windowName + JavaScriptConstants.CONSTANTS);
			javaScriptWindow.textarea.append(script);
		}
		if (javaScriptWindow.propertyTable.getJavaScript().getScriptName()
				.equalsIgnoreCase(JavaScriptConstants.WINDOW)) {
			script = createWindowScript(windowName);
			script = script.replaceAll("Template", windowName);
			script = script.replaceAll("template", windowName.toLowerCase());
			writeStringToFile(JavaScriptConstants.WINDOWPATH, script,
					windowName + JavaScriptConstants.WINDOW);
			javaScriptWindow.textarea.append(script);
		}
		if (javaScriptWindow.propertyTable.getJavaScript().getScriptName()
				.equalsIgnoreCase(JavaScriptConstants.PROPERTY)) {
			script = createPropertyTableWindowScript(windowName);
			script = script.replaceAll("Template", windowName);
			script = script.replaceAll("template", windowName.toLowerCase());
			writeStringToFile(JavaScriptConstants.WINDOWPROPERTYPATH, script,
					windowName + JavaScriptConstants.PROPERTY);
			javaScriptWindow.textarea.append(script);
		}
		if (javaScriptWindow.propertyTable.getJavaScript().getScriptName()
				.equalsIgnoreCase(JavaScriptConstants.WINDOWUTIL)) {

			script = createWindowUtilScript(windowName);
			script = script.replaceAll("Template", windowName);
			script = script.replaceAll("template", windowName.toLowerCase());

			writeStringToFile(JavaScriptConstants.WINDOWUTILPATH, script,
					windowName + JavaScriptConstants.WINDOWUTIL);
			javaScriptWindow.textarea.append(script);
		}
		if (javaScriptWindow.propertyTable.getJavaScript().getScriptName()
				.equalsIgnoreCase("TableModelUtil")) {
			script = createWindowTableModelUtil(windowName);
			script = script.replaceAll("Template", windowName);
			script = script.replaceAll("template", windowName.toLowerCase());
			writeStringToFile(JavaScriptConstants.WINDOWTABLEMODELPATH, script,
					windowName + "TableModelUtil");
			javaScriptWindow.textarea.append(script);
		}

	}

	private void rightSideCenterTableAction() {

	}

	private void searchTextAction() {
		loadButtonAction();
	}

	// check Null pointerException.
	private void deleteButtonAction() {

	}

	// this method is required to get any data from db to populate Window.
	@Override
	public void windowstartUpData() {
		// TODO Auto-generated method stub

	}

	public String createWindowConstantsScript(String windowName) {
		String windowConstantScript = "package constants; \n\n public class "
				+ windowName
				+ "Constants { \n\n final public static String WINDOW_NAME  = \"";
		windowConstantScript = windowConstantScript + windowName + "\"";
		Vector<WindowSheet> windowPropertys = getWindowProperty(windowName);
		for (int i = 0; i < windowPropertys.size(); i++) {
			WindowSheet sheet = windowPropertys.get(i);
			windowConstantScript = windowConstantScript
					+ ";\n final public static String "
					+ sheet.getFieldName().toUpperCase() + "   = \"";
			windowConstantScript = windowConstantScript + sheet.getFieldName()
					+ "\"";
		}
		windowConstantScript = windowConstantScript
				+ ";\n final public static int SPLITWINDOWLOCATION =370";
		windowConstantScript = windowConstantScript
				+ ";\n final public static int WINDOWWIDTH = 1227";
		windowConstantScript = windowConstantScript
				+ ";\n final public static int WINDOWHIGHT =480 ";

		windowConstantScript = windowConstantScript
				+ ";\npublic static final String SEARCHTEXTBOX = ";
		windowConstantScript = windowConstantScript + "\"";
		windowConstantScript = windowConstantScript + windowName + "Search";
		windowConstantScript = windowConstantScript + "\"";
		windowConstantScript = windowConstantScript + ";\n}";

		return windowConstantScript;
	}

	public String createWindowScript(String windowName) {
		String windowScript = "";
		windowScript = windowScript
				+ "package apps.window.staticwindow; \n\n\n  import java.awt.BorderLayout;\n";
		windowScript = windowScript + "import java.awt.Component;\n";
		windowScript = windowScript + "import java.util.ArrayList;\n";
		windowScript = windowScript + "import java.util.Vector;\n";

		windowScript = windowScript + "import javax.swing.ActionMap;\n";
		windowScript = windowScript + "import javax.swing.BorderFactory;\n";
		windowScript = windowScript + "import javax.swing.JButton;\n";
		windowScript = windowScript + "import javax.swing.JLabel;\n";
		windowScript = windowScript + "import javax.swing.JPanel;\n";
		windowScript = windowScript + "import javax.swing.JTextField;\n";
		windowScript = windowScript
				+ "import javax.swing.border.EtchedBorder;\n";

		windowScript = windowScript + "import util.CosmosException;\n";
		windowScript = windowScript + "import util.commonUTIL;\n";
		windowScript = windowScript
				+ "import apps.window.staticwindow.util.TemplateWindowUtil;\n";
		windowScript = windowScript
				+ "import apps.window.util.propertyTable.TemplatePropertyTable;\n";
		windowScript = windowScript
				+ "import apps.window.util.tableModelUtil.TemplateTableModelUtil;\n";
		windowScript = windowScript + "import beans.Template;\n";
		windowScript = windowScript + "import constants.CommonConstants;\n";
		windowScript = windowScript
				+ "import constants.TemplateConstants;\n\n\n";

		windowScript = windowScript
				+ "public class TemplateWindow    extends BasePanel {\n";
		windowScript = windowScript + "   ActionMap actions = null;\n";
		windowScript = windowScript + "   public String searchData [];\n";
		windowScript = windowScript
				+ "  private static final long serialVersionUID = 1L; \n";
		windowScript = windowScript
				+ "   public TemplateTableModelUtil model =null;\n";
		windowScript = windowScript
				+ "     Template template = new Template(); /// used as a bean \n";
		windowScript = windowScript
				+ "  // used for Validation and save,update and delete and get Data from DB.\n";
		windowScript = windowScript + "TemplateWindowUtil windowUtil = null;\n";
		windowScript = windowScript
				+ "Vector<Template> rightPanelJtableTemplatedata = new Vector<Template>(); // used maintain data in rightPanel in center area.\n";

		windowScript = windowScript + "/**\n";
		windowScript = windowScript + "* @return the data\n";
		windowScript = windowScript + "/\n";
		windowScript = windowScript + "public Vector<Template> getData() {\n";
		windowScript = windowScript + "return rightPanelJtableTemplatedata;\n";
		windowScript = windowScript + "}\n";

		windowScript = windowScript + "/**\n";
		windowScript = windowScript + " * @param data the data to set\n";
		windowScript = windowScript + " */\n";
		windowScript = windowScript
				+ "public void setData(Vector<Template> data) {\n";
		windowScript = windowScript + "//this.data = data;\n";
		windowScript = windowScript + "rightPanelJtableTemplatedata = data;\n";
		windowScript = windowScript + "}\n";

		windowScript = windowScript + "// leftTopPanel Data\n";
		windowScript = windowScript
				+ "protected JLabel templateLabelName = new JLabel(";
		windowScript = windowScript + "\"";
		windowScript = windowScript + "Template Name";
		windowScript = windowScript + "\");\n";
		windowScript = windowScript
				+ "public final	JTextField templateSearchTextField = new JTextField(";
		windowScript = windowScript + "\"";
		windowScript = windowScript + "TemplateTextField";
		windowScript = windowScript + "\"";
		windowScript = windowScript
				+ "); // search textfield in leftTopPanel Data\n";

		windowScript = windowScript + "// rightTopPanel Data\n";
		windowScript = windowScript
				+ " private JLabel templateName = new JLabel(";
		windowScript = windowScript + "\"";
		windowScript = windowScript + "Template Name";
		windowScript = windowScript + "\"";
		windowScript = windowScript + ");\n";
		windowScript = windowScript
				+ "protected JButton templateDetails = new JButton(";
		windowScript = windowScript + "\"";
		windowScript = windowScript + "Load TemplateDetails";
		windowScript = windowScript + "\"";
		windowScript = windowScript + ");\n";
		windowScript = windowScript + "// leftSide PropertyTable \n";
		windowScript = windowScript
				+ "public TemplatePropertyTable  propertyTable = null; \n";

		windowScript = windowScript + "// Constructor\n";
		windowScript = windowScript + "public TemplateWindow() {\n";
		windowScript = windowScript + "try {\n";
		windowScript = windowScript + "initComponents();\n";
		windowScript = windowScript + "} catch (CosmosException e) {\n";
		windowScript = windowScript + "// TODO Auto-generated catch block\n";
		windowScript = windowScript
				+ "commonUTIL.displayError(TemplateConstants.WINDOW_NAME,";
		windowScript = windowScript + "\"";
		windowScript = windowScript + "Constructor";
		windowScript = windowScript + "\"";
		windowScript = windowScript + ", e);\n";
		windowScript = windowScript + "}\n";

		windowScript = windowScript + "}\n";

		windowScript = windowScript
				+ "private void initComponents() throws CosmosException {\n";
		windowScript = windowScript
				+ "/// init() data required while loading this window.\n";
		windowScript = windowScript + "init();\n";

		windowScript = windowScript
				+ "setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));\n";
		windowScript = windowScript + "setLayout(new BorderLayout()); \n";
		windowScript = windowScript + "// add  model to table \n";
		windowScript = windowScript
				+ "model = new TemplateTableModelUtil(rightPanelJtableTemplatedata);\n";
		windowScript = windowScript
				+ " rightSideCenterTable.setModel(model); \n";

		windowScript = windowScript
				+ "createSingleSplitPaneLayout(CommonConstants.SPLITWINDOWLOCATION);	\n";
		windowScript = windowScript
				+ "setSize(CommonConstants.WINDOWWIDTH , CommonConstants.WINDOWHIGHT); \n";
		windowScript = windowScript + "}\n";

		windowScript = windowScript + "/**\n";
		windowScript = windowScript + "* @return the Template\n";
		windowScript = windowScript + " */\n";
		windowScript = windowScript + "public Template getTemplate() {\n";
		windowScript = windowScript + "return template;\n";
		windowScript = windowScript + "}\n";

		windowScript = windowScript + "/**\n";
		windowScript = windowScript + "* @param Template the Template to set\n";
		windowScript = windowScript + " */\n";
		windowScript = windowScript
				+ "public void setTemplate(Template template) {\n";
		windowScript = windowScript + "this.template = template;\n";
		windowScript = windowScript + "}\n";

		windowScript = windowScript + "@Override\n";
		windowScript = windowScript
				+ "public ActionMap getHotKeysActionMapper() {\n";
		// TODO Auto-generated method stub\n";
		windowScript = windowScript + "ActionMap action = new ActionMap();\n";

		windowScript = windowScript + "return action;\n";
		windowScript = windowScript + "}\n";

		windowScript = windowScript + " @Override\n";
		windowScript = windowScript + "public JPanel getHotKeysPanel() {\n";
		// TODO Auto-generated method stub
		windowScript = windowScript + " return  rightTopbuttonsPanel;\n";
		windowScript = windowScript + "}\n";

		windowScript = windowScript + "@Override\n";
		windowScript = windowScript
				+ "public ArrayList<Component> getFocusOrderList() {\n";
		// TODO Auto-generated method stub
		windowScript = windowScript
				+ "ArrayList<Component> comps = new ArrayList<Component>();\n";
		windowScript = windowScript + "comps.add(loadButton);\n";
		windowScript = windowScript + "return comps;\n";
		windowScript = windowScript + "}\n";
		windowScript = windowScript
				+ " // add Window Validation util for search,save,new,saveAsNew,close and other custom components. \n";
		windowScript = windowScript + " @Override\n";
		windowScript = windowScript
				+ "public void setWindowValidationUtil( ) {\n";
		// TODO Auto-generated method stub
		windowScript = windowScript
				+ "windowUtil = new TemplateWindowUtil(); \n";
		windowScript = windowScript + "windowUtil.setWindow(this);\n";
		windowScript = windowScript
				+ "this. validationActionUtil = windowUtil;\n";

		windowScript = windowScript + "}\n";
		windowScript = windowScript
				+ "// add listerener to panel Jcompenonts. \n";
		windowScript = windowScript + "@Override\n";
		windowScript = windowScript
				+ "public void setWindowActionListener()   {\n";
		// TODO Auto-generated method stub
		windowScript = windowScript + "try {\n";
		windowScript = windowScript + "setEventListener(templateDetails);\n";
		windowScript = windowScript
				+ "setEventListener(templateSearchTextField);\n";
		windowScript = windowScript + "} catch (CosmosException e) {\n";
		// TODO Auto-generated catch block
		windowScript = windowScript + "e.printStackTrace();\n";
		windowScript = windowScript + "}\n";

		windowScript = windowScript + "}\n";
		windowScript = windowScript + " // add lefttop panel componenonts\n";
		windowScript = windowScript + "@Override\n";
		windowScript = windowScript
				+ "public void addTopLeftSidePanelComponents() {\n";
		// TODO Auto-generated method stub

		windowScript = windowScript
				+ " templateSearchTextField.setName(TemplateConstants.SEARCHTEXTBOX); \n";
		windowScript = windowScript
				+ "leftTopbuttonsPanel.add(templateLabelName);\n";
		windowScript = windowScript
				+ "setSearchOnLeftTopPanel(templateSearchTextField,searchData);	 \n";
		windowScript = windowScript
				+ "leftTopbuttonsPanel.add(templateSearchTextField);  \n";
		windowScript = windowScript + "}\n";
		windowScript = windowScript + "// add righttop panel componenonts\n";
		windowScript = windowScript + "@Override\n";
		windowScript = windowScript
				+ "public void addTopRigthSidePanelComponents() {\n";

		windowScript = windowScript + "rightTopbuttonsPanel.add(templateName);\n";
		windowScript = windowScript
				+ " rightTopbuttonsPanel.add(templateDetails);\n";
		windowScript = windowScript + "}\n";
		windowScript = windowScript + " // create property proprities. \n";
		windowScript = windowScript + "@Override\n";
		windowScript = windowScript
				+ "public void createPropertyPaneTable() {\n";
		// TODO Auto-generated method stub

		windowScript = windowScript
				+ "propertyTable = new TemplatePropertyTable(TemplateConstants.WINDOW_NAME,template);\n";
		windowScript = windowScript
				+ " setLeftSidePropertyPanePanel(propertyTable.getPropertyTable(generateProperty(TemplateConstants.WINDOW_NAME) ));\n";

		windowScript = windowScript + "}\n";

		windowScript = windowScript + "@Override\n";
		windowScript = windowScript
				+ "public void addCenterRightSidePanelComponents() {\n";
		windowScript = windowScript + "// TODO Auto-generated method stub\n";
		windowScript = windowScript
				+ "centerRightSidePanel.add(scrollPane, BorderLayout.CENTER);\n";
		windowScript = windowScript + "}\n";
		windowScript = windowScript + "}\n";

		return windowScript;
	}

	public String createPropertyTableWindowScript(String windowName) {
		String windowScript = "";
		windowScript = windowScript
				+ "package apps.window.util.propertyTable;\n";

		windowScript = windowScript
				+ "import java.beans.PropertyChangeEvent;\n";
		windowScript = windowScript + "import java.util.List;\n";
		windowScript = windowScript + "import beans.Template;\n";
		windowScript = windowScript + "import com.jidesoft.grid.Property;\n";

		windowScript = windowScript
				+ "public class TemplatePropertyTable  extends WindowPropertyTable   {\n";

		windowScript = windowScript
				+ "List< Property> templateProperties = null;\n";
		windowScript = windowScript + "public Template template ;\n";
		windowScript = windowScript + "@Override\n";
		windowScript = windowScript
				+ "public void propertyChange(PropertyChangeEvent evt) {\n";
		windowScript = windowScript + "}\n";
		// name of the window
		windowScript = windowScript
				+ "public TemplatePropertyTable(String name,Template template ) {\n";
		windowScript = windowScript + "this.name = name;\n";
		windowScript = windowScript + "setTemplate(template);\n";
		windowScript = windowScript + "}\n";

		windowScript = windowScript + "@Override\n";
		windowScript = windowScript
				+ "public List< Property> addListenerToProperty(List< Property> properties) {\n";
		windowScript = windowScript + "return properties;\n";
		windowScript = windowScript + "}\n";
		windowScript = windowScript + "// add listener to the property\n";

		windowScript = windowScript
				+ "public void addListenerToProperty(final Property property ,final List< Property> properties  ) {\n";
		windowScript = windowScript + "}\n";

		windowScript = windowScript + "/**\n";
		windowScript = windowScript + "* @return the template\n";
		windowScript = windowScript + "*/\n";
		windowScript = windowScript + "public Template getTemplate() {\n";
		windowScript = windowScript + "return template;\n";
		windowScript = windowScript + "}\n";

		windowScript = windowScript + "	/**\n";
		windowScript = windowScript + "* @param template the template to set\n";
		windowScript = windowScript + "*/\n";
		windowScript = windowScript
				+ "public void setTemplate(Template bean) {\n";
		windowScript = windowScript + "this.template = bean;\n";
		windowScript = windowScript + "}\n";

		windowScript = windowScript + "}\n";

		return windowScript;
	}

	public String createWindowUtilScript(String windowName) {
		String windowScript = "";
		windowScript = windowScript
				+ "package apps.window.staticwindow.util; \n\n\n import java.util.Vector;\n";

		windowScript = windowScript
				+ "import apps.window.util.tableModelUtil.TableUtils;";
		windowScript = windowScript + "import util.ReferenceDataCache;\n";
		windowScript = windowScript + "import util.commonUTIL;\n";
		windowScript = windowScript
				+ "import apps.window.staticwindow.BasePanel;\n";
		windowScript = windowScript
				+ "import apps.window.staticwindow.TemplateWindow;\n";
		windowScript = windowScript + "import beans.Template; \n";
		windowScript = windowScript + "import beans.WindowSheet;\n";
		windowScript = windowScript + "import com.jidesoft.grid.Property;\n";
		windowScript = windowScript + "import constants.CommonConstants;\n";
		windowScript = windowScript + "import constants.TemplateConstants;\n";

		windowScript = windowScript
				+ "public class TemplateWindowUtil extends BaseWindowUtil {\n";
		windowScript = windowScript + " TemplateWindow templateWindow= null;\n";
		windowScript = windowScript + "Template template = null;\n";
		windowScript = windowScript + " String templateName;\n";

		windowScript = windowScript + "/**\n";
		windowScript = windowScript + " * @return the windowName\n";
		windowScript = windowScript + " */\n";
		windowScript = windowScript + "public String getWindowName() {\n";
		windowScript = windowScript
				+ "	return TemplateConstants.WINDOW_NAME;\n";
		windowScript = windowScript + "}\n";

		windowScript = windowScript + "	/**\n";
		windowScript = windowScript
				+ " * @param windowName the windowName to set\n";
		windowScript = windowScript + " */\n";
		windowScript = windowScript
				+ "public void setWindowName(String templateName) {\n";
		windowScript = windowScript + "	this.templateName = templateName;\n";
		windowScript = windowScript + "}\n";

		windowScript = windowScript + "/**\n";
		windowScript = windowScript + " * @return the template\n";
		windowScript = windowScript + " */\n";
		windowScript = windowScript + "	public Template getTemplate() {\n";
		windowScript = windowScript + "	return template;\n";
		windowScript = windowScript + "	}\n";

		windowScript = windowScript + "		/**\n";
		windowScript = windowScript
				+ " * @param template the template to set\n";
		windowScript = windowScript + "	 */\n";
		windowScript = windowScript
				+ "public void setTemplate(Template template) {\n";
		windowScript = windowScript + "	  this.template = template;\n}";
		windowScript = windowScript + "@Override\n";
		windowScript = windowScript + "public boolean validate( ) {\n";
		windowScript = windowScript + "		// TODO Auto-generated method stub\n";
		windowScript = windowScript + "		boolean flag = false;\n";

		windowScript = windowScript
				+ "			return validate(getTemplate(),TemplateConstants.WINDOW_NAME);\n";

		windowScript = windowScript + "			}\n";

		windowScript = windowScript + "	@Override\n";
		windowScript = windowScript
				+ "					public Vector<String> fillData(String action) {\n";
		windowScript = windowScript
				+ "				// TODO Auto-generated method stub\n";
		windowScript = windowScript + "			return null;\n";
		windowScript = windowScript + "}\n";

		windowScript = windowScript + "	@Override\n";
		windowScript = windowScript
				+ "public void actionMapper(String action) {\n";
		windowScript = windowScript + "	// TODO Auto-generated method stub\n";
		windowScript = windowScript
				+ "Property prop = templateWindow.propertyTable.getPropertyTable().getSelectedProperty(); \n";
		windowScript = windowScript
				+ "if(action.equalsIgnoreCase(CommonConstants.SAVEASNEWBUTTON)) {\n";
		windowScript = windowScript + "saveAsNewButtonAction();\n";
		windowScript = windowScript + "}\n";
		windowScript = windowScript
				+ "if(action.equalsIgnoreCase(CommonConstants.NEWBUTTON)) {\n";
		windowScript = windowScript + "newButtonAction();\n";
		windowScript = windowScript + "	}\n";
		windowScript = windowScript
				+ "		if(action.equalsIgnoreCase(CommonConstants.LOADBUTTON)) {\n";
		windowScript = windowScript + "loadButtonAction();\n";

		windowScript = windowScript + "	}\n";
		windowScript = windowScript
				+ "	if(action.equalsIgnoreCase(TemplateConstants.SEARCHTEXTBOX)) {\n";
		windowScript = windowScript + "searchTextAction();\n";
		windowScript = windowScript + "	}\n";

		windowScript = windowScript
				+ "if(action.equalsIgnoreCase(CommonConstants.RIGHTSIDECENTERTABLE)) {\n";
		windowScript = windowScript + "	rightSideCenterTableAction();\n";
		windowScript = windowScript + "}\n";
		windowScript = windowScript
				+ "	if(action.equalsIgnoreCase(CommonConstants.DELETEBUTTON)) {\n";
		windowScript = windowScript + "	deleteButtonAction();\n";
		windowScript = windowScript + "}\n";
		windowScript = windowScript
				+ "if(action.equalsIgnoreCase(CommonConstants.SAVEBUTTON)) {\n";
		windowScript = windowScript + "saveButtonAction();\n";
		windowScript = windowScript + "}\n";
		windowScript = windowScript + "	}				\n";

		windowScript = windowScript + "@Override\n";
		windowScript = windowScript
				+ "public void setWindow(BasePanel windowName) {\n";
		windowScript = windowScript + "	// TODO Auto-generated method stub\n";
		windowScript = windowScript
				+ "	templateWindow = (TemplateWindow)windowName;\n";
		windowScript = windowScript
				+ "setTemplate(templateWindow.getTemplate()); \n";

		windowScript = windowScript + "}\n";

		windowScript = windowScript + "private void saveButtonAction() {\n";
		// TODO Auto-generated method stub
		windowScript = windowScript + "templateWindow.propertyTable\n";
		windowScript = windowScript + ".setfillValues(template);\n";
		windowScript = windowScript
				+ "setTemplate((Template) templateWindow.propertyTable.getBean());\n";
		windowScript = windowScript + "//if(validate( )) \n";

		windowScript = windowScript
				+ "// if(ReferenceDataCache.updateTemplate(getTemplate())) {\n";
		windowScript = windowScript
				+ "//if(templateWindow.rightSideCenterTable.getSelectedRow() != -1) {\n";
		windowScript = windowScript
				+ "	 // int i=  TableUtils.getSelectedRowIndex( templateWindow.rightSideCenterTable);\n";
		windowScript = windowScript
				+ "	  //templateWindow.model.udpateValueAt(getTemplate(), i, 0);\n";
		windowScript = windowScript + " //}\n";

		windowScript = windowScript + " //}\n";

		windowScript = windowScript + "}		\n";
		windowScript = windowScript
				+ "private void saveAsNewButtonAction() { \n";
		windowScript = windowScript + "Template template = new Template();\n";
		windowScript = windowScript
				+ "	templateWindow.propertyTable.setfillValues(template);\n";
		windowScript = windowScript + "	 setTemplate(template);\n";

		windowScript = windowScript + "//if(validate( )){\n";
		windowScript = windowScript
				+ "// template = ReferenceDataCache.saveTemplate(template); \n";
		windowScript = windowScript
				+ " // templateWindow.model.addRow(getTemplate());\n";
		 
		windowScript = windowScript + " // setTemplate(template);\n";

		windowScript = windowScript + "  //}\n";
		windowScript = windowScript + "}\n";

		windowScript = windowScript + "private void newButtonAction() {\n";
		windowScript = windowScript
				+ "templateWindow.propertyTable.clearPropertyValues();\n";
		windowScript = windowScript + "templateWindow.model.clear();\n";
		windowScript = windowScript + "setTemplate(null);\n";
		windowScript = windowScript + "	}\n";

		windowScript = windowScript + "private void loadButtonAction() {\n";
		windowScript = windowScript + "	newButtonAction();\n";
		windowScript = windowScript
				+ "String searchText =   templateWindow.templateSearchTextField.getText();\n";
		windowScript = windowScript
				+ " if(!commonUTIL.isEmpty(searchText)) {\n";
		windowScript = windowScript
				+ "Vector<Template> data = null;//ReferenceDataCache.selectTemplates(searchText);\n";
		windowScript = windowScript + "templateWindow.model.clear();\n";
		windowScript = windowScript + "if(!commonUTIL.isEmpty(data)) {\n";
		windowScript = windowScript + "Template firstRecord = data.get(0);\n";
		windowScript = windowScript + "for(int i=0;i<data.size();i++) {\n";
		windowScript = windowScript
				+ " templateWindow.model.addRow((Template)data.get(i));\n";
		windowScript = windowScript + "				}\n";
		windowScript = windowScript
				+ "				templateWindow.propertyTable.setPropertiesValues(firstRecord);\n";
		windowScript = windowScript + "				 setTemplate(firstRecord);\n";
		windowScript = windowScript + "		}\n";

		windowScript = windowScript + "	 }\n";
		windowScript = windowScript + "}\n";

		windowScript = windowScript
				+ " private void rightSideCenterTableAction() {\n";
		windowScript = windowScript
				+ "	if(templateWindow.rightSideCenterTable.getSelectedRow() != -1) \n";
		windowScript = windowScript
				+ "		 templateWindow.propertyTable.setPropertiesValues( templateWindow.model.getRow(templateWindow.rightSideCenterTable.getSelectedRow()));\n";
		windowScript = windowScript
				+ "	templateWindow.setTemplate(templateWindow.model.getRow(templateWindow.rightSideCenterTable .getSelectedRow()));\n";
		windowScript = windowScript
				+ "	setTemplate(templateWindow.model .getRow(templateWindow.rightSideCenterTable .getSelectedRow()));\n";
		windowScript = windowScript + "}\n";

		windowScript = windowScript + "private void searchTextAction() {\n";
		windowScript = windowScript + "	loadButtonAction();\n";
		windowScript = windowScript + "}\n";
		windowScript = windowScript + "// check Null pointerException.\n";
		windowScript = windowScript + "private void deleteButtonAction() {\n";
		windowScript = windowScript + "	try {\n";
		windowScript = windowScript
				+ "//if(ReferenceDataCache.deleteTemplate(template)) {\n";
		windowScript = windowScript
				+ "//if( templateWindow.rightSideCenterTable.getSelectedRow() != -1) {\n";
		windowScript = windowScript
				+ "	//templateWindow.model.delRow(templateWindow.rightSideCenterTable.getSelectedRow()); \n";
		windowScript = windowScript + "	//}\n";
		windowScript = windowScript + "//setTemplate(null);\n";
		windowScript = windowScript
				+ " //templateWindow.propertyTable.clearPropertyValues();\n";
		windowScript = windowScript + "//}	\n";
		windowScript = windowScript + "} catch(Exception e) {\n";
		windowScript = windowScript
				+ "commonUTIL.displayError(TemplateConstants.WINDOW_NAME+";
		windowScript = windowScript + "\"";
		windowScript = windowScript + "Util";
		windowScript = windowScript + "\"";
		windowScript = windowScript + " , ";
		windowScript = windowScript + "\"";
		windowScript = windowScript + "deleteButtonAction";
		windowScript = windowScript + "\"";
		windowScript = windowScript + ", e);\n";
		windowScript = windowScript + "}\n";

		windowScript = windowScript + "}\n";

		// this method is required to get any data from db to populate Window.
		windowScript = windowScript + "@Override\n";
		windowScript = windowScript + "public void windowstartUpData() {\n";
		// TODO Auto-generated method stub
		windowScript = windowScript + " \n";
		windowScript = windowScript + "\n}";

		windowScript = windowScript + "@Override\n";
		windowScript = windowScript + "public void clearALL() {\n";
		// TODO Auto-generated method stub

		windowScript = windowScript + "}\n}";
		return windowScript;
	}

	public String createWindowTableModelUtil(String windowName) {
		addColMap("BookNameWindow", "Book", "BookNo", "Book_no");
		addColMap("BookNameWindow", "Book", "BookName", "Book_name");
		addColMap("BookNameWindow", "Book", "LegalEntity", "le");
		String col = getColumnName(windowName);
		String switchCase = getSwitchCaseMapping(windowName);
		String classTypeMethod = getColumnClassDataType(windowName);
		String windowScript = "";
		windowScript = windowScript
				+ "package apps.window.util.tableModelUtil;\n";
		windowScript = windowScript + "import java.util.Vector;\n";
		windowScript = windowScript
				+ "import javax.swing.table.AbstractTableModel;\n";
		windowScript = windowScript + " import beans.Template;\n";
		windowScript = windowScript
				+ " public class TemplateTableModelUtil extends AbstractTableModel {\n";

		windowScript = windowScript + "		final String[] columnNames;  \n";
		windowScript = windowScript + "		 String col[] =" + col + " ;\n";

		windowScript = windowScript + " /**\n";
		windowScript = windowScript + "	 * @return the data\n";
		windowScript = windowScript + "	 */\n";
		windowScript = windowScript + "public Vector<Template> getData() {\n";
		windowScript = windowScript + "	return mydata;\n";
		windowScript = windowScript + "	}\n";

		windowScript = windowScript + "	final Vector<Template> mydata;   \n";

		windowScript = windowScript
				+ " public TemplateTableModelUtil( Vector<Template> data  ) {\n";
		windowScript = windowScript + "		 	this.columnNames = col;\n";
		windowScript = windowScript + "		this.mydata = data;   \n";
		windowScript = windowScript + "	}   \n";
		windowScript = windowScript
				+ " public TemplateTableModelUtil( Vector<Template> data ,String [] col ) {  \n";
		windowScript = windowScript + "	 	this.columnNames = col;\n";
		windowScript = windowScript + "	this.mydata = data;   \n";
		windowScript = windowScript + "		}   \n";

		windowScript = windowScript + "	 public int getColumnCount() {   \n";
		windowScript = windowScript + "	     return columnNames.length;   \n";
		windowScript = windowScript + "	 }   \n";

		windowScript = windowScript + "		    public int getRowCount() {   \n";
		windowScript = windowScript + "			     return mydata.size();   \n";

		windowScript = windowScript + "			 }  \n";
		windowScript = windowScript
				+ "			 public Template getRow(int i) {   \n";
		windowScript = windowScript + "			     return mydata.get(i)  ; \n";

		windowScript = windowScript + "			 }\n";
		windowScript = windowScript
				+ "			 public String getColumnName(int col) {  \n";
		windowScript = windowScript + "		     return columnNames[col];  \n";
		windowScript = windowScript + "		 }   \n";
		windowScript = windowScript
				+ " public Object getValueAt(int row, int col) {   \n";
		windowScript = windowScript + "		     Object value = null;  	 \n";

		windowScript = windowScript
				+ "		     Template  template = (Template) mydata.get(row);	\n";
		windowScript = windowScript + "  switch (col) { " + switchCase + " }\n";
		windowScript = windowScript + " return value;}   \n " +classTypeMethod;

		windowScript = windowScript
				+ " public boolean isCellEditable(int row, int col) {   \n";
		windowScript = windowScript + "  return false;   \n";
		windowScript = windowScript + "		  }   \n";
		windowScript = windowScript
				+ "  public void setValueAt(Object value, int row, int col) {   \n";

		windowScript = windowScript + "  if(value instanceof Template) {\n";
		windowScript = windowScript
				+ "	 	 mydata.set(row,(Template ) value) ;\n";
		windowScript = windowScript
				+ "			     this.fireTableDataChanged();      \n";
		windowScript = windowScript + " }\n";

		windowScript = windowScript + " }   \n";
		windowScript = windowScript + " public void clear() {\n";
		windowScript = windowScript + "	 mydata.clear();\n";
		windowScript = windowScript + "   this.fireTableDataChanged();\n";
		windowScript = windowScript + "}\n";
		windowScript = windowScript + " public void addRow(Object value) {  \n";

		windowScript = windowScript + " mydata.add((Template ) value) ;\n";
		windowScript = windowScript + "this.fireTableDataChanged(); \n";

		windowScript = windowScript + " }   	\n";
		windowScript = windowScript + " public void delRow(int row) {   \n";

		windowScript = windowScript + "		  mydata.remove(row);  \n";
		windowScript = windowScript + " this.fireTableDataChanged(); \n";

		windowScript = windowScript + " }   \n";

		windowScript = windowScript
				+ "public void udpateValueAt(Object value, int row, int col) {\n";
		windowScript = windowScript + "	 mydata.set(row,(Template) value) ;\n";
		windowScript = windowScript + "    fireTableCellUpdated(row, col); \n";

		windowScript = windowScript + "} \n}";

		return windowScript;

	}

	private String getSwitchCaseMapping(String windowName2) {
		// TODO Auto-generated method stub

		String switchcase = "";
		Vector<WindowTableModelMapping> maps = getMapColumns(windowName2);
		for (int i = 0; i < maps.size(); i++) {
			WindowTableModelMapping mp = maps.get(i);
			switchcase = switchcase + " case " + i + ":\n";
			switchcase = switchcase + "value =  " + windowName2.toLowerCase()
					+ ".get" + mp.getMethodName() + "();\n break; \n";

		}
		return switchcase;
	}
	// add this method later. 
	private void addCustomColumnMethod(Vector<String> customColumns,String windowName2) {
		Vector<WindowTableModelMapping> maps = getMapColumns(windowName2);
		for (int i = 0; i < maps.size(); i++) {
			WindowTableModelMapping mp = maps.get(i);
			String methodName = "";
			if(mp.getColumnDataType().equalsIgnoreCase(WindowTableModelMappingConstants.COLUMNDATATYPE)) {
			//	methodName = methodName = 
			}
		}
	}
	 
	private String getColumnClassDataType (String windowName2) {
		// TODO Auto-generated method stub

		String classType = "@Override \n      public Class getColumnClass(int column) { \n switch (column) {\n";
		Vector<WindowTableModelMapping> maps = getMapColumns(windowName2);
		for (int i = 0; i < maps.size(); i++) {
			WindowTableModelMapping mp = maps.get(i);
			classType = classType + " case " + i + ":\n";
			if(mp.getColumnDataType().equalsIgnoreCase("Boolean")) {
				classType = classType + " return Boolean.class; \n";
			} else 	if(mp.getColumnDataType().equalsIgnoreCase("Integer")) {
				classType = classType + " return Integer.class; \n";
			} else 			if(mp.getColumnDataType().equalsIgnoreCase("Double")) {
				classType = classType + " return Double.class; \n";
			} else {
				classType = classType + " return String.class; \n";
			}
		
             

		}
		classType = classType + "  default: \n return String.class;\n }\n}";
		return classType;
	}
	

	Vector<WindowTableModelMapping> mappingCol = new Vector<WindowTableModelMapping>();

	private String getColumnName(String windowName2) {
		// TODO Auto-generated method stub
		String col = "{";
		Vector<WindowTableModelMapping> maps = getMapColumns(windowName2);
		for (int i = 0; i < maps.size(); i++) {
			WindowTableModelMapping mp = maps.get(i);
			col = col + "\"" + mp.getColumnName() + "\"" + ",";
		}

		col = col.substring(0, col.length() - 1) + "}";
		return col;
	}

	public void addColMap(String windowName, String beanName,
			String columnName, String methodName) {
		WindowTableModelMapping mp = new WindowTableModelMapping();
		mp.setWindowName(windowName);
		mp.setMethodName(methodName);
		mp.setBeanName(beanName);
		mp.setColumnName(columnName);
		mappingCol.add(mp);
	}

	public Vector<WindowTableModelMapping> getMapColumns(String windowName) {
		Vector<WindowTableModelMapping> mp = ReferenceDataCache
				.selectWindowTableModelMappings(windowName);

		return mp;
	}

	@Override
	public void clearALL() {
		// TODO Auto-generated method stub
		javaScriptWindow.propertyTable = null;

	}

	private void writeStringToFile(String path, String content, String fileName) {
		File file = new File(path + "/" + fileName + ".java");

		// if file doesnt exists, then create it
		if (!file.exists()) {
			try {
				FileWriter fw;
				file.createNewFile();
				fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(content);
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
