package apps.window.staticwindow;

import java.awt.BorderLayout;
import java.awt.Component; 
import java.util.ArrayList; 
import java.util.Vector;

 
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder; 

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import util.CosmosException;
import util.commonUTIL; 
import apps.window.staticwindow.util.WindowSheetWindowUtil; 
import apps.window.util.propertyTable.WindowSheetPropertyTable;
import apps.window.util.tableModelUtil.WindowSheetTableModelUtil;
import beans.WindowSheet;
import constants.CurrencyDefaultConstant;
import constants.WindowSheetConstants;

public class WindowSheetWindow extends BasePanel {
	   ActionMap actions = null;
	   private static final long serialVersionUID = 1L; 
	   public WindowSheetTableModelUtil model =null;
	  public String searchData [];
	     WindowSheet windowSheet = new WindowSheet(); /// used as a bean 
	  // used for Validation and save,update and delete and get Data from DB.
	     WindowSheetWindowUtil windowUtil = null;
	     Vector<WindowSheet> rightPanelJtableWindowSheetdata = new Vector<WindowSheet>(); // used maintain data in rightPanel in center area.
		
		 /**
		 * @return the data
		 */
		public Vector<WindowSheet> getData() {
			return rightPanelJtableWindowSheetdata;
		}

		/**
		 * @param data the data to set
		 */
		public void setData(Vector<WindowSheet> data) {
			//this.data = data;
			rightPanelJtableWindowSheetdata = data;
		}

		
		public JTextArea textArea = new JTextArea();
		 public JScrollPane textAreaScrollPane = new JScrollPane(textArea);
		  
	      
	     // leftTopPanel Data
	    protected JLabel windowName = new JLabel("WindowName");
	    public final	JTextField WindowSheetSearchTextField = new JTextField("WindowSheetTextField"); // search textfield in leftTopPanel Data
		

		// rightTopPanel Data
		 private JLabel windowSheetLabel = new JLabel("Window Name");
		public JButton windowDetails = new JButton("Load Scripts");
		// leftSide PropertyTable 
		public WindowSheetPropertyTable  propertyTable = null; 
		
		// Constructor
		public WindowSheetWindow() {
			try {
				initComponents();
			} catch (CosmosException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError(WindowSheetConstants.WINDOW_NAME, "Constructor", e);
			}
			 
					 
		}

		private void initComponents() throws CosmosException {
			/// init() data required while loading this window.
			init();
			
			
			setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			setLayout(new BorderLayout()); 
	      // add  model to table 
	       model = new WindowSheetTableModelUtil(rightPanelJtableWindowSheetdata);
	       rightSideCenterTable.setModel(model); 
	       
	       
		   createSingleSplitPaneLayout(CurrencyDefaultConstant.SPLITWINDOWLOCATION);	 
		 setSize(CurrencyDefaultConstant.WINDOWWIDTH , CurrencyDefaultConstant.WINDOWHIGHT); 
		}
		 
		 
		  

		 
		/**
		 * @return the WindowSheet
		 */
		public WindowSheet getWindowSheet() {
			return windowSheet;
		}

		/**
		 * @param WindowSheet the WindowSheet to set
		 */
		public void setWindowSheet(WindowSheet windowSheet) {
			this.windowSheet = windowSheet;
		}
		 
		  
		
	    	
	       
		
		 
		@Override
		public ActionMap getHotKeysActionMapper() {
			// TODO Auto-generated method stub
			ActionMap action = new ActionMap();
			 
			return action;
		}

		@Override
		public JPanel getHotKeysPanel() {
			// TODO Auto-generated method stub
			return  rightTopbuttonsPanel;
		}

		@Override
		public ArrayList<Component> getFocusOrderList() {
			// TODO Auto-generated method stub
			ArrayList<Component> comps = new ArrayList<Component>();
			comps.add(loadButton);
			return comps;
		}
        // add Window Validation util for search,save,new,saveAsNew,close and other custom components. 
		@Override
		public void setWindowValidationUtil( ) {
			// TODO Auto-generated method stub
			windowUtil = new WindowSheetWindowUtil(); 
			  windowUtil.setWindow(this);
				this. validationActionUtil = windowUtil;
			
			
		}
		// add listerener to panel Jcompenonts. 
		@Override
		public void setWindowActionListener()   {
			// TODO Auto-generated method stub
			try {
				setEventListener(windowDetails);
				setEventListener(WindowSheetSearchTextField);
			} catch (CosmosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		 // add lefttop panel componenonts
		@Override
		public void addTopLeftSidePanelComponents() {
			// TODO Auto-generated method stub
			
		       WindowSheetSearchTextField.setName(WindowSheetConstants.SEARCHTEXTBOX); 
		      leftTopbuttonsPanel.add(windowSheetLabel);
		      setSearchOnLeftTopPanel(WindowSheetSearchTextField,searchData);	      
		      leftTopbuttonsPanel.add(WindowSheetSearchTextField);  
		}
		// add righttop panel componenonts
		@Override
		public void addTopRigthSidePanelComponents() {
			// TODO Auto-generated method stub
		
		       rightTopbuttonsPanel.add(windowName);
		       rightTopbuttonsPanel.add(windowDetails);
		}
		 // create property proprities. 
		@Override
		public void createPropertyPaneTable() {
			// TODO Auto-generated method stub
			
		       propertyTable = new WindowSheetPropertyTable(WindowSheetConstants.WINDOW_NAME,windowSheet);
		       setLeftSidePropertyPanePanel(propertyTable.getWindowSheetPropertyTable(generateProperty(WindowSheetConstants.WINDOW_NAME) ));
			   
			
		}
		 

		@Override
		public void addCenterRightSidePanelComponents() {
			// TODO Auto-generated method stub
			//centerRightSidePanel.add(scrollPane, BorderLayout.CENTER);
			JPanel   jPanel1 = new JPanel();
			jPanel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(textAreaScrollPane, new Constraints(new Leading(7, 674, 10, 10), new Leading(223, 231, 10, 10)));
			jPanel1.add(scrollPane,new Constraints(new Leading(4, 680, 12, 12), new Leading(3, 214, 12, 12)));
		    centerRightSidePanel.add(jPanel1);
		}
}