import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


import javax.swing.*;


public class applet extends JApplet implements ActionListener
{
   
    /**
	 * 
	 */
    static final long serialVersionUID = 1L;
	private JButton bttnForLogIn, bttnForTime,bttnForViewingTables;
    private String server_connect,user_name,user_pwd,oracle_driver;
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private String getTime;
    private JTextField txtFieldForUserName, pwdFieldForPwd,txtFieldForURL,txtFieldForPort,txtFieldForSID;
    
    private boolean getConnected=true;
    private JLabel lblTitle, lblHint,lblForUserName,lblForPwd, lblForURL, lblForPort, lblForSID, lblDisplayTime;
    private JTextArea textArea;
    public void init() {
		// TODO Auto-generated method stub
		
    	setSize(800, 700);
    	if(getConnected){
		setLayout(new BorderLayout());}
		
		add(new applet(),BorderLayout.CENTER);
		
		
		
		System.out.println("Init");

 }
 

    public applet()
    {
      if(getConnected){
    	
        oracle_driver = "oracle.jdbc.driver.OracleDriver";
        getConnected = true;
        //getContentPane().setLayout(null);
        
    	//setContentPane(new JLabel(new ImageIcon("http://www.pulsarecard.com/data/media/70/Simple%20Cluds%20And%20Grass%201280X960%20Wallpaper.jpg")));
    	//setLayout(new FlowLayout());
    	//getContentPane().getBackground();
      
     

        JScrollPane scrollPane;
        getContentPane().add(scrollPane = new JScrollPane(this.textArea = new JTextArea()));
        textArea.setEditable(false);
        textArea.setBackground(Color.WHITE);
        scrollPane.setBounds(20,480, 700, 100);
        
        
        
        lblTitle = new JLabel("Oracle Database Connection VIA Java");
        lblTitle.setFont(new Font("Slab Serif", Font.BOLD, 18));
        lblTitle.setForeground(Color.red);
        lblTitle.setBounds(100, 11, 400, 60);
       

        lblHint= new JLabel("for example: bonnet19.cs.qc.edu");
        lblHint.setBounds(300, 100, 236, 20);
        
        
        lblForURL= new JLabel("URL:");
        lblForURL.setBounds(20, 103, 124, 14);        
        
        txtFieldForURL = new JTextField();
        txtFieldForURL.setBounds(50, 100, 236, 20);
        txtFieldForURL.setColumns(10);
        txtFieldForURL.setText("jdbc:oracle:thin:@");
        
        lblForPort = new JLabel("Port:");
        lblForPort.setBounds(20, 133, 37, 14);
        txtFieldForPort = new JTextField();
        txtFieldForPort.setBounds(50,130, 55, 20);
              
        
        lblForSID = new JLabel("SID:");
        lblForSID.setBounds(20, 163, 31, 14);
        txtFieldForSID = new JTextField();
        txtFieldForSID.setBounds(50, 160, 68, 20);
        
            
        lblForUserName = new JLabel("User Name:");
        lblForUserName.setBounds(20, 193, 141, 14);
        txtFieldForUserName = new JTextField();
        txtFieldForUserName.setBounds(90, 190, 141, 20);
        
      
        
        lblForPwd = new JLabel("Password:");
        pwdFieldForPwd = new JPasswordField();
        pwdFieldForPwd.setBounds(90, 223, 150, 20);
        lblForPwd.setBounds(20, 220, 124, 14);        
       
        getContentPane().setBackground(Color.WHITE);
        bttnForLogIn = new JButton("Log In");
        bttnForLogIn.setBounds(21, 300, 150, 20);
        bttnForLogIn.addActionListener(this);
        
        bttnForTime = new JButton("Check Time:");
        bttnForTime.setBounds(21, 350, 150, 35);
        //bttnForTime.setEnabled(false);
        bttnForTime.addActionListener(this);
        
        bttnForViewingTables = new JButton("View Tables:");
        bttnForViewingTables.setBounds(21, 400, 150, 35);
        //bttnForViewingTables.setEnabled(false);
        bttnForViewingTables.addActionListener(this);
        
      
        lblDisplayTime = new JLabel("");
        lblDisplayTime.setFont(new Font("Times New Roman",30,12));
        lblDisplayTime.setForeground(Color.RED);
        lblDisplayTime.setBounds(180, 345, 400, 50);
        
      
        
        getContentPane().add(lblTitle);
        getContentPane().add(lblHint);
        getContentPane().add(bttnForLogIn);  
        getContentPane().add(txtFieldForPort);
        getContentPane().add(txtFieldForSID);
        getContentPane().add(lblForUserName);
        getContentPane().add(txtFieldForUserName);
        getContentPane().add(lblForPwd);
        getContentPane().add(pwdFieldForPwd);
        getContentPane().add(bttnForTime);
        getContentPane().add(bttnForViewingTables);
        getContentPane().add(txtFieldForURL);
        getContentPane().add(lblForURL);
        getContentPane().add(lblForPort);
        getContentPane().add(lblForSID);
        getContentPane().add(lblDisplayTime);
        
       
      }
    }
    
    public void setConnection(boolean getConnection)
    {
    	getConnection=false;
    }
    
    public void buttonChanged(){

        bttnForLogIn.setText("Log Out");
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == bttnForLogIn)
        {
            user_name = txtFieldForUserName.getText();
            user_pwd = pwdFieldForPwd.getText();
            setURL();
            
            if(getConnected)
            {
                if(testConnection() == 0)
                {
                	
                    JOptionPane.showMessageDialog(null, "Successful Completion");
                    
                    bttnForTime.setEnabled(true);
                    
                    buttonChanged();
                    getConnected = false;
                } else
                {
                	JOptionPane.showMessageDialog(null, "Oracle DB connection fail");
                    bttnForTime.setEnabled(false);

               
                }
            } else 
            {
                try
                {
                    conn.close();
                }
                catch(SQLException e1)
                {
                    e1.printStackTrace();
                }
                bttnForLogIn.setText("Login");
                setConnection(getConnected);

                JOptionPane.showMessageDialog(null, "You have logged out! Have a good day!");
                txtFieldForURL.setText(null);
                txtFieldForPort.setText(null);
                txtFieldForSID.setText(null);
                txtFieldForUserName.setText(null);
                pwdFieldForPwd.setText(null);
                lblDisplayTime.setText(null);
                textArea.setText(null);
                getConnected=true;

                txtFieldForURL.setText("jdbc:oracle:thin:@");
            }
        }else
            if(e.getSource() == bttnForTime)
    			sysTime();


            else {
            	if(e.getSource() == bttnForViewingTables){
 
            		try {
            			sysTime();
		   listTables();
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}}}




}
    private void listTables() throws SQLException {
    	DatabaseMetaData dbmd;

    	dbmd = conn.getMetaData();

    	String[] types = { "TABLE" };
    	int t_index=0;
    	ResultSet resultSet = dbmd.getTables(null, null, "%", types);

    	while (resultSet.next()) {

    		String tableName = resultSet.getString(3);
    		t_index++;
    		textArea.append(t_index+"."+tableName+"\n");
    		}
    	String t=""+t_index;
    	JOptionPane.showMessageDialog(null,t+" tables are found and Names of the tables are now being displayed in TextArea!");

}
    
      
           
    private String getURL()
    {
    	return server_connect;
    }
    
    private void setURL()
    {
        server_connect = txtFieldForURL.getText()+(":")+txtFieldForPort.getText()+(":")+txtFieldForSID.getText();
    }

    public int testConnection()
    {
        int flag = 0;
        try
        {
            Class.forName(oracle_driver);
        }
        catch(Exception e)
        {
        	JOptionPane.showMessageDialog(null, e.getMessage());
        }
        try
        {
            conn = DriverManager.getConnection(getURL(), user_name, user_pwd);
        }
        catch(SQLException e)
        {
        	JOptionPane.showMessageDialog(null, e.getMessage());
        	flag = -1;
        }
        return flag;
    }
    public void displayTime(String getTime){
    	textArea.setText(" hour(s) ahead of the system clock of Oracle at bonnet19 is: "+getTime);
    }
    
    private void sysTime()  
    {
    try		
    {
        String stmtQuery = "select sysdate + 1 from dual";
        pstmt = conn.prepareStatement(stmtQuery);
        rs = pstmt.executeQuery();
        if(rs.next())
        {
            getTime = rs.getString(1);
            displayTime(getTime);
        }
        rs.close();
        pstmt.close();
    } catch(SQLException e2)
    {
        e2.printStackTrace();
    }
}
  

    	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("destroy");
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		System.out.println("Start");
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		if(!getConnected)
		System.out.println("Stop");
		
	}

} 

 

