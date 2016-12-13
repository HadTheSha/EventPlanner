
/*TO DO : 2 drop down menue in the first row ( Event information ) -->> Salsabeel ** LEAVE THE DATE AND TIME
  TO DO: 3 text fields for the customer infotmation in the second row (Defined already) -->> Jawaher 
  TO DO: 1 drop down menue in the last row -->> Jawaher 
  TO DO: Integrate the CardLayout into the menues and calanders -->> Shahad


  *** IMPORTANT JAWAHER AND SALSABEEL *** 
  - To the menue list please add the opstion 'else' at the end of the list 
  - Leave the file commented. 
*/
package layout;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import org.jdatepicker.impl.*;
import org.jdatepicker.util.*;
import org.jdatepicker.*;


public class EventPlan extends JFrame implements ActionListener, ItemListener 
{
  public static final int WIDTH = 1300;
  public static final int HEIGHT = 500;
  public static final int charactersNumber = 15; 
  public static final int MIN_ZOOM = 0;// GOOGLE API
  public static final int MAX_ZOOM = 21;//GOOGLE API
  private static int zoomValue = 4;// GOOGLE API  
  private JTextField theSearch;
  private JLabel background01;
  private JPanel search ;
  JFrame elseEventWindow;
  JFrame newEventWindow;
  JFrame welcomeWindow;
  JFrame frame;
  JFrame birthdayEventWindow;
  private JPanel eventPanel;
  private JPanel customerPanel;
  private JPanel paymentPanel;
  private JTextField costumerName;
  private JTextField email;
  private JTextField number; 
  private JTextArea comments;
  private JCheckBox femaleBox;
  private JCheckBox maleBox;
  private JTextArea additionalcomments;
  private JComboBox paymentMethod;
  private JComboBox eventType;
  private JComboBox eventPlace;
  private JComboBox eventTiming;
  private JButton submitBtn;
  private JButton cancelBtn;
  private JTextField cardNumber;
  private JTextField cardName;
  private JTextField securityCode;
  private JPanel cards; 
  private JTextField bankName;
  private JTextField chequeNumber;
  private JPanel paymentCard2;
  private JPanel paymentCard1;
  private JPanel birthdayInfo;
  private JPanel Birthdaycomment;
  private JScrollPane nweScrollbar;
  
  
public static void main (String [] args){
    EventPlan gui = new EventPlan();
    gui.setVisible(true);
    
  }

  public EventPlan(){
	  // WELCOME WINDOW ( FIRST ONE ) 
    JFrame welcomeWindow = new JFrame("Event Planner");
    setSize(300,200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());
    
    background01=new JLabel(new ImageIcon("THEBG01.png"));
    add(background01);
    background01.setLayout(new FlowLayout());
    
    JPanel btns = new JPanel();
    btns.setLayout(new GridLayout(1,2));
    JButton searchButton = new JButton("Search");
    searchButton.addActionListener(this);
    btns.add(searchButton);
    JButton newEventButton = new JButton("New Event");
    newEventButton.addActionListener(this);
    btns.add(newEventButton);
    
    search = new JPanel();
    search.setLayout(new GridLayout(2,2));
    theSearch = new JTextField(charactersNumber);
    JLabel searchLabel = new JLabel("Search by a customer's name ");
    JButton goSearch= new JButton ("Go");
    search.add(searchLabel);
    search.add(theSearch);
    search.add(goSearch);
    search.setOpaque(false);
    btns.setOpaque(false);
    background01.add(btns);

  }

  
public void actionPerformed(ActionEvent e){
	
    String buttonString = e.getActionCommand();
    
    if (buttonString.equals("New Event")) {
    	// SETTING UP THE MAIN WINDOW TO SCHEDUEL THE EVENT 
      newEventWindow = new JFrame("Scheduel a new event");
      newEventWindow.setSize(WIDTH, HEIGHT);
      newEventWindow.setLayout(new BorderLayout()); 
      newEventWindow.setVisible(true);
      newEventWindow.setLocationRelativeTo(null);
      final Browser browser = new Browser();
      BrowserView browserView = new BrowserView(browser);
     JLabel background1=new JLabel(new ImageIcon("THEBG.png"));
     newEventWindow.add(background1);
     background1.setLayout(new FlowLayout());
     JPanel bar = new JPanel();
     nweScrollbar = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
     JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
     setPreferredSize(new Dimension(499,500));
     newEventWindow.add(nweScrollbar);
     nweScrollbar.add(background1);
     nweScrollbar.setViewportView(background1);
     JPanel theGrid= new JPanel();
     theGrid.setLayout(new GridLayout(1,3));
     JPanel spaceOccupying = new JPanel();
     spaceOccupying.setLayout(new BorderLayout());
     
    
      // EVENT TYPE PANEL STARTS HERE :
     
      JLabel eventTimingLabel= new JLabel("Event Timing");
      eventTimingLabel.setFont(new Font("Times New Roman",Font.BOLD,15));
      eventPanel = new JPanel();
      eventPanel.setLayout(new GridLayout(4,5));  
      JPanel dateAndCalender = new JPanel();
      dateAndCalender.setLayout(new GridLayout(1,4));
      dateAndCalender.setOpaque(false);
      
      JLabel eventDateLabel= new JLabel("Date");// calender goes here 
      eventDateLabel.setFont(new Font("Times New Roman",Font.BOLD,15));
      dateAndCalender.add(eventDateLabel);
      UtilDateModel model = new UtilDateModel();
      Properties p = new Properties();
      JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
      dateAndCalender.add(datePanel);
      spaceOccupying.add(dateAndCalender, BorderLayout.NORTH);
      eventPanel.add(eventTimingLabel);
      
      // JCOMBO DROP DOWN LIST FIRST DEFINED HERE:
      String[] eventTimestr = {"Morning", "Noon", "Night"};
      JComboBox eventTiming= new JComboBox(eventTimestr);
      eventTiming.addActionListener(this);
      eventPanel.add(eventTiming);

      JLabel eventTypeLabel= new JLabel("Event Type");
      eventPanel.add(eventTypeLabel);
      eventTypeLabel.setFont(new Font("Times New Roman",Font.BOLD,15));
      String[] eventTypestr = {"Wedding", "Birthday", "Graduation party", "else"};
      JComboBox eventType= new JComboBox(eventTypestr);
      eventType.addActionListener(this);// ActionListener to control the JCOMBO elements
      eventPanel.add(eventType);
      eventType.addActionListener(new ActionListener() { 
          public void actionPerformed(ActionEvent e) {
            JComboBox eType = (JComboBox)e.getSource();
            String typeName = (String)eType.getSelectedItem();
            String buttonString = e.getActionCommand();
            
            if(typeName.equals("else")){
                elseEventWindow = new JFrame("A New Event");
                elseEventWindow.setSize(400, 300);
                elseEventWindow.setLayout(new BorderLayout()); 
                elseEventWindow.setVisible(true);
                JLabel background2=new JLabel(new ImageIcon("elseWin.png"));
                elseEventWindow.add(background2);
                background2.setLayout(new FlowLayout());
                JPanel mainPanel =new JPanel();
                mainPanel.setLayout(new BorderLayout());
                JPanel elseInfo = new JPanel();
                elseInfo.setLayout(new GridLayout(4,3));
                
                
                JLabel eventLabel = new JLabel("Event Type");//Text area goes here
                JTextField elseLabel = new JTextField (charactersNumber);
                elseInfo.add(eventLabel);
                elseInfo.add(elseLabel);
                JLabel commentsLabel = new JLabel ("else");
                commentsLabel.setFont(new Font("Times New Roman",Font.BOLD,15));
               JTextArea Else;
                Else= new JTextArea();
                elseLabel.add(Else);
               

                JLabel nogLabel = new JLabel("Number of guests");//Text area goes here
                JTextField guest= new JTextField (charactersNumber);
                elseInfo.add(nogLabel);
                 elseInfo.add(guest);
             
                JLabel priceLabel = new JLabel("Note:260 SR/person");
                priceLabel.setFont(new Font("Times New Roman",Font.BOLD,10));
                priceLabel.setForeground(Color.RED);
                elseInfo.add(priceLabel);
                
                

                JPanel elsecomment= new JPanel();
                JLabel addComment = new JLabel("Additional Comments:");
                additionalcomments = new JTextArea(6,20);
                elsecomment.add(addComment);
                elsecomment.add(additionalcomments);
                JPanel Combtns = new JPanel();
                Combtns.setLayout(new GridLayout(1,2));
                submitBtn = new JButton("Proceed");
                submitBtn.addActionListener(this);
                Combtns.add(submitBtn);
                submitBtn.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                    String buttonString = e.getActionCommand();
                    elseEventWindow.setVisible(false);
                      JOptionPane.showMessageDialog (null, "Your Information has been submitted" ); 
                  }
                });
                cancelBtn = new JButton("Cancel");
                cancelBtn.addActionListener(this);
                Combtns.add(cancelBtn);
                cancelBtn.addActionListener(this);
                cancelBtn.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                    String buttonString = e.getActionCommand();
                    elseEventWindow.setVisible(false);
                  }
                });
                
                mainPanel.add(elseInfo, BorderLayout.NORTH);
                mainPanel.add(elsecomment, BorderLayout.CENTER);
                elsecomment.setOpaque(false);
                mainPanel.add(Combtns, BorderLayout.SOUTH);
                Combtns.setOpaque(false);
                elseInfo.setOpaque(false);
                mainPanel.setOpaque(false);
                background2.add(mainPanel);
                
            }
            if (typeName.equals("Birthday")){
              // NEW WINDOW TO SET A BIRTHDAY EVENT 
            	
              birthdayEventWindow = new JFrame("Birthday Event");
                birthdayEventWindow.setSize(700, 200);
                birthdayEventWindow.setLayout(new BorderLayout());
                birthdayEventWindow.setVisible(true);

                

                birthdayInfo = new JPanel();
                birthdayInfo.setLayout(new GridLayout(5,3));
                birthdayInfo.setOpaque(false);
                JLabel background=new JLabel(new ImageIcon("BRlogo.png"));
                birthdayEventWindow.add(background);
                background.setLayout(new FlowLayout());
                // CHECK BOX GOE HERE 
                JLabel genderLabel = new JLabel("Gender");
                maleBox = new JCheckBox("Male");
                maleBox.setMnemonic(KeyEvent.VK_C);
                maleBox.setSelected(false); 

                femaleBox = new JCheckBox("Femael");
                femaleBox.setMnemonic(KeyEvent.VK_G);
                femaleBox.setSelected(false);
                birthdayInfo.add(genderLabel);
                birthdayInfo.add(maleBox);
                birthdayInfo.add(femaleBox);

                JLabel ageLabel = new JLabel("Age");
                String[] agestr = {"1-5", "6-10", "11-15"};
                JComboBox age= new JComboBox(agestr);
                eventType.setSelectedIndex(2);
                eventType.addActionListener(this);
                birthdayInfo.add(ageLabel);
                birthdayInfo.add(age);
                
                Birthdaycomment= new JPanel();
                JLabel addCom = new JLabel("Additional Comments:");
                additionalcomments = new JTextArea(6,20);
                Birthdaycomment.add(addCom);
                Birthdaycomment.add(additionalcomments);


              JPanel BDbtns = new JPanel();
              BDbtns.setLayout(new GridLayout(1,2));
              submitBtn = new JButton("Proceed");
              submitBtn.addActionListener(this);
              BDbtns.add(submitBtn);
              submitBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                  String buttonString = e.getActionCommand();
                    birthdayEventWindow.setVisible(false);
                    JOptionPane.showMessageDialog (null, "Your Information has been submitted" ); 
                }
              });
              cancelBtn = new JButton("Cancel");
              cancelBtn.addActionListener(this);
              BDbtns.add(cancelBtn);
              cancelBtn.addActionListener(this);
              cancelBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                  String buttonString = e.getActionCommand();
                    birthdayEventWindow.setVisible(false);
                }
              });

              background.add(birthdayInfo);
              background.add(Birthdaycomment);
              Birthdaycomment.setOpaque(false);
              background.add(BDbtns);
              BDbtns.setOpaque(false);
              
            }
          }
       
        });

      JLabel eventPlaceLabel= new JLabel("Event Place ");//menue goes here
      eventPlaceLabel.setFont(new Font("Times New Roman",Font.BOLD,15));
      String[] eventPlacestr = {"Hotel","Wedding Hall", "Resturant", "Else"};
      JComboBox eventPlace= new JComboBox(eventPlacestr);
      eventPlace.setSelectedIndex(0);
      eventPlace.addActionListener(this);
      eventPlace.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JComboBox place = (JComboBox)e.getSource();
          String placeName = (String)place.getSelectedItem();
          if(placeName.equals("Else")){
        	  // The start of the HTML file that holds the google map
             final Browser browser = new Browser(); // the class browser is from the library jxbrowser 
             BrowserView browserView = new BrowserView(browser);

             JButton zoomInButton = new JButton("Zoom In"); //  to function the zoom In buttons
             zoomInButton.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                     if (zoomValue < MAX_ZOOM) {
                         browser.executeJavaScript("map.setZoom(" + ++zoomValue + ")");
                     }
                 }
             });

             JButton zoomOutButton = new JButton("Zoom Out");//  to function the zoom Out buttons
             zoomOutButton.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                     if (zoomValue > MIN_ZOOM) {
                         browser.executeJavaScript("map.setZoom(" + --zoomValue + ")");
                     }
                 }
             });

             JButton setMarkerButton = new JButton("Set Marker"); // to function the set marker button
             setMarkerButton.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                     browser.executeJavaScript("var myLatlng = new google.maps.LatLng(27.139134, 49.559108);\n" +
                             "var marker = new google.maps.Marker({\n" +
                             "    position: myLatlng,\n" +
                             "    map: map,\n" +
                             "    title: 'Set Marker!'\n" +
                             "});");
                 } // This is a javascript code that is excuted by the method .excuteJavaScript from the jxbrowser library
                 // "LatLng(27.139134, 49.559108)" Are fixed location of the JUC 
             });

             JPanel toolBar = new JPanel();
             toolBar.add(zoomInButton);
             toolBar.add(zoomOutButton);
             toolBar.add(setMarkerButton);

             JFrame frame = new JFrame("Set A Location");
             frame.add(toolBar, BorderLayout.SOUTH);
             frame.add(browserView, BorderLayout.CENTER);
             frame.setSize(900, 500);
             frame.setLocationRelativeTo(null);
             frame.setVisible(true);

             browser.loadURL("file:///Users/shahdadwani/Documents/workspace/EventPlan/src/map.html"); // The path of the html file rendered 
             //by the browser API provided by jxbroswer
         
      }
      }
      
         });

      eventPanel.add(eventPlaceLabel);
      eventPanel.add(eventPlace);
      eventPlace.setOpaque(false);
      

      // CUSTOMER INFORMATION PANEL STARTS HERE :

      customerPanel = new JPanel();
      customerPanel.setLayout(new GridLayout(3,1));
      JLabel customerNameLabel= new JLabel ("Customer Name");
      customerPanel.add(customerNameLabel);
      costumerName= new JTextField(charactersNumber);
      customerPanel.add(costumerName);
      JLabel emailLabel= new JLabel ("Customer Email");
      customerPanel.add(emailLabel);
      email= new JTextField(charactersNumber);
      customerPanel.add(email);
      JLabel numberLabel= new JLabel (" Customer Phone Number");
      customerPanel.add(numberLabel);
       number= new JTextField(charactersNumber);
       customerPanel.add(number);
       customerPanel.setOpaque(false);



      //PAYMENT INFORMATION PANEL SARTS HERE :
       
       paymentPanel = new JPanel();
       paymentPanel.setLayout(new BorderLayout());
       JLabel paymentMethodLabel= new JLabel("Payment Method:"); //menue goes here
       paymentPanel.add(paymentMethodLabel, BorderLayout.NORTH);
       paymentMethodLabel.setFont(new Font("Times New Roman",Font.BOLD,15));
       String[] paymentMethodstr = {"Credit Card", "Cheque"};
       JComboBox paymentMethod= new JComboBox(paymentMethodstr);
       paymentMethod.setEditable(false);
       paymentMethod.addActionListener(this);
       // Credit card info goes here 
       paymentPanel.add(paymentMethod, BorderLayout.NORTH);
	  	 paymentCard1 = new JPanel();
	     paymentCard1.setLayout(new GridLayout(4,4)); 
	     JLabel cName= new JLabel("Name on Card");
	     paymentCard1.add(cName);
	     cardName= new JTextField(charactersNumber);
	     paymentCard1.add(cardName);
	     
	     JLabel cNumber= new JLabel("Card Number");
	     paymentCard1.add(cNumber);
	     cardNumber= new JTextField(charactersNumber);
	     paymentCard1.add(cardNumber);
	     
	     JLabel cEDate= new JLabel("Expiry Date");
	     paymentCard1.add(cEDate);
	     JPanel comboDate= new JPanel();
	     comboDate.setLayout(new GridLayout(1,2));// menue goes here 
	     String[] expiryDate = {"01", "02","03","04","05", "06", "07", "08", "09", "10", "11", "12"};
	     JComboBox paymentDate= new JComboBox(expiryDate);
	     String[] year = {"2017","2018","2019","2020"};
	     JComboBox paymentyear= new JComboBox(year); 
	     comboDate.add(paymentDate);
	     comboDate.add(paymentyear);
	     comboDate.setOpaque(false);
	     paymentCard1.add(comboDate);
	     JLabel cSCode = new JLabel("Security Code");
	     paymentCard1.add(cSCode);
	     securityCode= new JTextField(3);
	     paymentCard1.add(securityCode);
	     cards = new JPanel();
	     cards.add(paymentCard1);
	     paymentPanel.add(cards, BorderLayout.CENTER);
	     paymentCard1.setOpaque(false);
	     cards.setOpaque(false);
	     paymentMethod.addActionListener(new ActionListener() { // a listener to the change in the 
	    	 //Jcombo box item whereas a new panel is set invisible and the other is set to visible
           public void actionPerformed(ActionEvent e) {
             JComboBox eType = (JComboBox)e.getSource();
             String typeName = (String)eType.getSelectedItem();
	         if (typeName.equals("Cheque")){
	        	 paymentCard1.setVisible(false);
	        	  	 paymentCard2 = new JPanel();
		             paymentCard2.setLayout(new GridLayout(2,2)); // TO BE Visible when chosen
		             JLabel bName= new JLabel("Issuing Bank");
		             paymentCard2.add(bName);
		             bankName= new JTextField(charactersNumber);
		             paymentCard2.add(bName);
		             paymentCard2.add(bankName);
		             
		             
		            JLabel chNumber= new JLabel("Cheque Number");
					paymentCard2.add(chNumber);
		             chequeNumber= new JTextField(charactersNumber);
		             paymentCard2.add(chNumber);
		             paymentCard2.add(chequeNumber);
					cards.add(paymentCard2);
		             paymentPanel.add(cards, BorderLayout.CENTER);
		             paymentCard2.setOpaque(false);
		             cards.setOpaque(false);

	          }else if (typeName.equals("Credit Card")){
	        	  paymentCard2.setVisible(false);
	        	  paymentCard1.setVisible(true);
	          }
           }
      });
    
     
       
       
      // TWO BUTTONS GO HERE :
       JPanel theBtns = new JPanel();
       theBtns.setLayout(new GridLayout(1,2));
       JButton submitButton = new JButton("Submit");
       submitButton.addActionListener(this);
       theBtns.add(submitButton);
       JButton clearButton = new JButton("Clear");
       clearButton.addActionListener(this);
       theBtns.add(clearButton);
       theBtns.setOpaque(false);
       
       // BACKGROUND SET UP: 
       theGrid.add(eventPanel);
       theGrid.add(customerPanel);
       theGrid.add(paymentPanel);
       eventPanel.setOpaque(false);
       customerPanel.setOpaque(false);
       paymentPanel.setOpaque(false);
       theGrid.setOpaque(false);
       spaceOccupying.add(theGrid, BorderLayout.CENTER);
       spaceOccupying.add(theBtns, BorderLayout.SOUTH);
       spaceOccupying.setOpaque(false);
       background1.add(spaceOccupying);


    }// Continuation of button string action listener
    else if (buttonString.equals("Search")){
    	setSize(400,250);
    	background01.add(search);
    }else if (buttonString.equals("Clear")){
    	  costumerName.setText("");
    	  email.setText("");
    	  number.setText(""); 
    	  cardNumber.setText("");
    	  cardName.setText("");
    	  securityCode.setText("");
    	  bankName.setText("");
    	  chequeNumber.setText("");
    }else if(buttonString.equals("Submit")){
    	JOptionPane.showMessageDialog (null, "Thank you, We Will reach Out to you for further Arrangments"); 
    }
    
  }

@Override // TO OVERRIDE THE ACTION LISTENER THAT IS IMPLEMENTED IN THE MAIN CLASS . 
public void itemStateChanged(ItemEvent e) {
	// TODO Auto-generated method stub
	
}

}
// END OF PROGRAM 