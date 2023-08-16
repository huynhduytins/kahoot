package Client2;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

////////////// helpdesk class ////////////////////////////////////
class Client2 extends Frame implements ActionListener {
	
	static Frame f = new Frame("KAHOOT");
	static Font titleFont = new Font(Font.MONOSPACED, Font.BOLD, 45);
	static Font titleBut = new Font(Font.SANS_SERIF, Font.BOLD, 25);
	static Font n_answer = new Font(Font.SANS_SERIF, Font.ITALIC, 30);
	
	static ArrayList<String[]> questions = new ArrayList<String[]>();
    ////////////////// charactericts of helpdesk class ////////////////
    Label l, ser1, ser2, cl1;
    TextField t1;
    Button b1;
    Button b2;
    Button b11, b21, b31, b41, b51;
    Socket s1;
    ServerSocket ss;
    String str1, str2;
    Label counttime;
    TextField inputPin, inputName;
    Button b112; //enter Pin
    Button b122;//enter name
    Button ansa, ansb, ansc, ansd;

    static String nameOfClient = "";
    static int scoreOfClient = 0;
    /////////////////// helpdesk Class Constructor ///////////////////
    public Client2() throws Exception {
    	setBackground(new Color(36,48,94));
        setTitle("Client");
        setSize(1200, 720);
        setLayout(null);
        setVisible(true);

        //////////// function for closing window ////////////////////////////////
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent w) {
                System.exit(0);
            }
        });

        ////////////////// Label, textfield and buttons /////////////////
        l = new Label("KAHOOT!");
        l.setForeground(new Color(239,75,75));
    	l.setFont(titleFont);
    	l.setBounds(500, 150, 200, 50);
    	l.setAlignment(Label.CENTER);
    	l.setBackground(new Color(36,48,94));
        add(l);
    	
        b1 = new Button("Moderator");
        b1.setBounds(250, 350, 250, 100);
		b1.setBackground(new Color(93, 109, 156));
		b1.setForeground(new Color(215,216,214));
		b1.setFont(titleBut);
        b1.addActionListener(this);
        add(b1);
        
        b2 = new Button("Participant");
        b2.setBounds(700, 350, 250, 100);
		b2.setBackground(new Color(93, 109, 156));
		b2.setForeground(new Color(215,216,214));
		b2.setFont(titleBut);
		b2.addActionListener(this);
        add(b2);
        
        
        
        try {
        	s1 = new Socket("localhost", 2227);
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}  
        while (true) {
        	
            try{
            	DataInputStream dis = new DataInputStream(s1.getInputStream());
            	String st1 = dis.readUTF();
            	removeAll();
            	if (st1.equals("start_signal")) {
            		ansa = new Button("A");
            		ansa.setBounds(120, 320, 450, 100); 
            		ansa.setFont(titleBut);
            		ansa.setBackground(Color.PINK);	
            		ansa.setForeground(new Color(36,48,94));
            		ansa.addActionListener(this);
            		
            		ansb = new Button("B"); 
            		ansb.setBounds(120, 450, 450, 100);  	
            		ansb.setFont(titleBut);
            		ansb.setBackground(Color.YELLOW);	
            		ansb.setForeground(new Color(36,48,94));
            		ansb.addActionListener(this);
            		
            		ansc = new Button("C");
            		ansc.setBounds(630, 320, 450, 100); 
            		ansc.setFont(titleBut);
            		ansc.setBackground(Color.GREEN);	
            		ansc.setForeground(new Color(36,48,94));
            		ansc.addActionListener(this);
            		
            		ansd = new Button("D");
            		ansd.setBounds(630, 450, 450, 100);  
            		
            		ansd.setFont(titleBut);
            		ansd.setBackground(Color.WHITE);	
            		ansd.setForeground(new Color(36,48,94));
            		ansd.addActionListener(this);
            		
            		add(ansa);
            		add(ansb);
            		add(ansc);
            		add(ansd);
            	}
            	
            	
            	if (st1.charAt(0) == 's' && st1.charAt(1) == 'c') {
            		st1 = st1.replace("sc", "");
            		System.out.println(st1);
            		int score_new = Integer.parseInt(st1);
            		// right
            		if (score_new > scoreOfClient) {
            			Label l152 = new Label("Correct!");
	            		l152.setBounds(550, 350, 100, 35);
	            		l152.setAlignment(Label.CENTER);
	            		l152.setFont(titleBut);
	            		l152.setForeground(new Color(46,160,117));
	            		
	            		Label l252 = new Label("+200");
	            		l252.setBounds(550, 390, 100, 35);
	            		l252.setAlignment(Label.CENTER);
	            		l252.setFont(titleBut);
	            		l252.setForeground(new Color(46,160,117));
	            		l252.setBackground(Color.WHITE);
	            		
	            		add(l152);
	            		add(l252);
	            		scoreOfClient = score_new; // update score
            		}
            		else if (score_new == scoreOfClient){
            			Label l162 = new Label("Incorrect!");
            			l162.setBounds(540, 350, 120, 35);
            			l162.setAlignment(Label.CENTER);
            			l162.setFont(titleBut);
            			l162.setForeground(new Color(239,75,75));
            			
            			Label l262 = new Label("+0");
            			l262.setBounds(550, 390, 100, 35);
            			l262.setAlignment(Label.CENTER);
            			l262.setFont(titleBut);
            			l262.setForeground(new Color(239,75,75));
            			l262.setBackground(Color.WHITE);
            			
            			add(l162);
	            		add(l262);
            		}
            	}
            	
            }catch(Exception ee){
                System.exit(0);
            }
        }
    }

    ///////////// method which implements actionListener//////////
    public void actionPerformed(ActionEvent e) {
        
    	// moderator mode
    	if (e.getSource() == b1) {
    		
    		removeAll();
    		
        	b11 = new Button("Import Questions");
    		b21 = new Button("Create Questions");
    		b31 = new Button("Setting");
    		b41 = new Button("Back");
    		b51 = new Button("Done");
    		
    		b11.setBounds(480, 250, 250, 70);
    		b11.setBackground(new Color(93, 109, 156));
    		b11.setForeground(new Color(215,216,214));
    		b11.setFont(titleBut);
    		b11.addActionListener(this);
            add(b11);
            
    		b21.setBounds(480, 350, 250, 70);
    		b21.setBackground(new Color(93, 109, 156));
    		b21.setForeground(new Color(215,216,214));
    		b21.setFont(titleBut);
    		b21.addActionListener(this);
            add(b21);
            
    		b31.setBounds(480, 450, 250, 70);
    		b31.setBackground(new Color(93, 109, 156));
    		b31.setForeground(new Color(215,216,214));
    		b31.setFont(titleBut);
    		b31.addActionListener(this);
            add(b31);
            
    		b41.setBounds(200, 550, 150, 70);
    		b41.setBackground(new Color(93, 109, 156));
    		b41.setForeground(new Color(215,216,214));
    		b41.setFont(titleBut);
    		b41.addActionListener(this);
            add(b41);
            
    		b51.setBounds(850, 550, 150, 70);
    		b51.setBackground(new Color(93, 109, 156));
    		b51.setForeground(new Color(215,216,214));
    		b51.setFont(titleBut);
    		b51.addActionListener(this);
            add(b51);

            try {
                DataOutputStream dos = new DataOutputStream(s1.getOutputStream());
                dos.writeUTF("clientjoin");
            } catch (Exception e1) {
                try {
                    //Thread.sleep(3000);
                    System.exit(0);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    	// CLIENT
    	if (e.getSource() == b2) {
        	removeAll();
        	
        	add(l);
        	Label l112 = new Label("PIN:");
    		l112.setBounds(480, 290, 50, 60);
    		l112.setFont(titleBut);
    		l112.setForeground(new Color(247,108,108));
    		add(l112);
    		
    		inputPin = new TextField();  
    		inputPin.setBounds(540,300, 180, 40);  
    		inputPin.setFont(titleBut);
    		add(inputPin);
    		
    		b112 = new Button("Enter");
    		b112.setBounds(520, 400, 160, 60);  
    		b112.setFont(titleBut);
    		b112.setBackground(new Color(215,216,214));	
    		b112.setForeground(new Color(36,48,94));
    		b112.addActionListener(this); 
    		add(b112);
    			
        	
        }
    	
    	if (e.getSource() == b11) {
    		String fileName = "src/questions.csv";
			int i = 0;
			String line = "";
			try {
				BufferedReader br = new BufferedReader(new FileReader(fileName));
				while((line = br.readLine()) != null) {
					String[] values = line.split(",");
					questions.add(values);
					i++;
				}
				JOptionPane.showMessageDialog(f, "The questions were successfully imported");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            
        }
    	if (e.getSource() == ansa) {
    		removeAll();
    		Label lc = new Label("You chosen A! Please wait time count to 0");
    		lc.setBounds(300, 350, 600, 100);  
    		lc.setAlignment(Label.CENTER);
    		lc.setFont(titleBut);
    		lc.setBackground(new Color(93, 109, 156));	
    		lc.setForeground(new Color(36,48,94));
   
    		add(lc);
    		
    		try {
                DataOutputStream dos = new DataOutputStream(s1.getOutputStream());
                
                String temp=String.valueOf(scoreOfClient);
                dos.writeUTF("aa"+temp);
            } catch (Exception e1) {
                try {
                    //Thread.sleep(3000);
                    System.exit(0);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
    	}
    	if (e.getSource() == ansb) {
    		removeAll();
    		Label lc = new Label("You chosen B! Please wait time count to 0");
    		lc.setBounds(300, 350, 600, 100);  
    		lc.setAlignment(Label.CENTER);
    		lc.setFont(titleBut);
    		lc.setBackground(new Color(93, 109, 156));	
    		lc.setForeground(new Color(36,48,94));
    		add(lc);
    		
    		try {
                DataOutputStream dos = new DataOutputStream(s1.getOutputStream());
                String temp=String.valueOf(scoreOfClient);
                dos.writeUTF("bb"+temp);
            } catch (Exception e1) {
                try {
                    //Thread.sleep(3000);
                    System.exit(0);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
    	}
		if (e.getSource() == ansc) {
			removeAll();
    		Label lc = new Label("You chosen C! Please wait time count to 0");
    		lc.setBounds(300, 350, 600, 100);  
    		lc.setAlignment(Label.CENTER);
    		lc.setFont(titleBut);
    		lc.setBackground(new Color(93, 109, 156));	
    		lc.setForeground(new Color(36,48,94));
    		add(lc);
    		
    		try {
                DataOutputStream dos = new DataOutputStream(s1.getOutputStream());
                String temp=String.valueOf(scoreOfClient);
                dos.writeUTF("cc"+temp);
            } catch (Exception e1) {
                try {
                    //Thread.sleep(3000);
                    System.exit(0);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
		}
		if (e.getSource() == ansd) {
			removeAll();
    		Label lc = new Label("You chosen D! Please wait time count to 0");
    		lc.setBounds(300, 350, 600, 100);  
    		lc.setAlignment(Label.CENTER);
    		lc.setFont(titleBut);
    		lc.setBackground(new Color(93, 109, 156));	
    		lc.setForeground(new Color(36,48,94));
    		add(lc);
    		
    		try {
                DataOutputStream dos = new DataOutputStream(s1.getOutputStream());
                String temp=String.valueOf(scoreOfClient);
                dos.writeUTF("dd"+temp);
            } catch (Exception e1) {
                try {
                    //Thread.sleep(3000);
                    System.exit(0);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
    	}
		
		if (e.getSource() == b112) {//enter Pin
			System.out.println(inputPin.getText());
			removeAll();
			if (inputPin.getText().equals("2227")) {
				
				Label l122 = new Label("Nickname:");
				l122.setBounds(440, 290, 130, 60);
				l122.setFont(titleBut);
				l122.setForeground(new Color(247,108,108));
				add(l122);
				
				inputName = new TextField();  
				inputName.setBounds(570,300, 190, 40);  
				inputName.setFont(titleBut);
				add(inputName);
				
				b122 = new Button("Ok, go!");
				b122.setBounds(520, 400, 160, 60);  
				b122.setFont(titleBut);
				b122.setBackground(new Color(215,216,214));	
				b122.setForeground(new Color(36,48,94));
				b122.addActionListener(this);
				add(b122);
			}
			else {
				JOptionPane.showMessageDialog(f, "Can't find this host");
	        	add(l);
	        	
	        	Label l112 = new Label("PIN:");
	    		l112.setBounds(480, 290, 50, 60);
	    		l112.setFont(titleBut);
	    		l112.setForeground(new Color(247,108,108));
	    		add(l112);
	    		
	    		inputPin = new TextField();  
	    		inputPin.setBounds(540,300, 180, 40);  
	    		inputPin.setFont(titleBut);
	    		add(inputPin);
	    		
	    		b112 = new Button("Enter");
	    		b112.setBounds(520, 400, 160, 60);  
	    		b112.setFont(titleBut);
	    		b112.setBackground(new Color(215,216,214));	
	    		b112.setForeground(new Color(36,48,94));
	    		b112.addActionListener(this); 
	    		add(b112);
			}
		}
		
		if (e.getSource() == b122) {
			removeAll();
			
			Label l151 = new Label("You are in room. Please wait host start!");
    		l151.setBounds(300, 350, 600, 100); 
    		l151.setAlignment(Label.CENTER);
    		l151.setFont(titleBut);
    		l151.setBackground(new Color(93, 109, 156));	
    		l151.setForeground(new Color(36,48,94));
    		add(l151);
            try {
                DataOutputStream dos = new DataOutputStream(s1.getOutputStream());
                nameOfClient = inputName.getText();
                dos.writeUTF("cj"+nameOfClient);//clientjoin = cj + name;
            } catch (Exception e1) {
                try {
                    //Thread.sleep(3000);
                    System.exit(0);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
		}
    	
    }

    ///////////// Entry gate main function //////////
    public static void main(String[] args) throws Exception {
        Client2 obj = new Client2();
    }
}