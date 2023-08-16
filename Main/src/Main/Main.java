package Main;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.Timer;

////////////// helpdesk class ////////////////////////////////////
class Main extends Frame implements ActionListener {
	
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
    Socket s, s1, s2;
    ServerSocket ss, ss1;
    String str1, str2;
    TextField port, port1, numparti;
    Button b132, b132t; // Start button
    Label l131;//num parti
    Button startgame, startgame1;
    Label l151, l251, l351, l451, l551, l751, l651;
    Button b151; //view result
    Button b111, b211;
    Button b1111;
    TextField tf111, tf211, tf311, tf411, tf511;
	Checkbox cb1, cb2, cb3, cb4;
	Label l231;
	Button b221;
	TextField tf121, tf221, tf321;
	Button b121;
    static Timer timer;
    static int[] ListScore = new int [10];
    static String[] ListName = new String[10];
    static int[] ListChose = new int [4];
    
	static int settings[] = {10, 5, 4};
	static int count = 0;
    static Boolean running = false;
    
    int maxNumClient = 4;
    
    public void viewAnswer() {
		String[] question = questions.get(count);
		String seconds_string = String.format("%02d", settings[1]);
		
		Label l151 = new Label(question[1]);
		l151.setBounds(120, 320, 450, 100); 
		l151.setAlignment(Label.CENTER);
		l151.setFont(titleBut);
		l151.setBackground(Color.PINK);	
		l151.setForeground(new Color(36,48,94));
		
		Label l251 = new Label(question[2]);
		l251.setBounds(120, 450, 450, 100);  
		l251.setAlignment(Label.CENTER);
		l251.setFont(titleBut);
		l251.setBackground(Color.YELLOW);	
		l251.setForeground(new Color(36,48,94));
		
		Label l351 = new Label(question[3]);
		l351.setBounds(630, 320, 450, 100); 
		l351.setAlignment(Label.CENTER);
		l351.setFont(titleBut);
		l351.setBackground(Color.GREEN);	
		l351.setForeground(new Color(36,48,94));
		
		Label l451 = new Label(question[4]);
		l451.setBounds(630, 450, 450, 100);  
		l451.setAlignment(Label.CENTER);
		l451.setFont(titleBut);
		l451.setBackground(Color.WHITE);	
		l451.setForeground(new Color(36,48,94));
		
		Label l551 = new Label("Time");
		l551.setBounds(500, 240, 100, 60);
		l551.setFont(titleBut);
		l551.setForeground(new Color(247,108,108));
		
		Label l751 = new Label(seconds_string);  
		l751.setBounds(600, 250, 100, 40);  
		l751.setAlignment(Label.CENTER);
		l751.setFont(titleBut);
		l751.setBackground(new Color(215,216,214));
		l751.setForeground(new Color(36,48,94));
		
		Label l651 = new Label(question[0]);
		l651.setBounds(250, 150, 700, 60);
		l651.setAlignment(Label.CENTER);
		l651.setFont(titleBut);
		l651.setBackground(new Color(215,216,214));
		l651.setForeground(new Color(36,48,94));
		
		b151 = new Button("View Results");
		b151.setBounds(500, 580, 200, 60);  
		b151.setFont(titleBut);
		b151.setBackground(new Color(215,216,214));	
		b151.setForeground(new Color(36,48,94));
		
		add(l151);
		add(l251);
		add(l351);
		add(l451);
		add(l551);
		add(l651);
		add(l751);

		int[] time = {settings[1]*1000, settings[1]};
		
		timer = new Timer(1000, new ActionListener() {  
			  public void actionPerformed(ActionEvent e) {			   
			   time[0] = time[0] - 1000;
			   time[1] = (time[0]/1000) % 60;
			   String seconds_string = String.format("%02d", time[1]);
			   
			   if(time[1] == 0) {
				   l751.setText("00");
				   add(b151);
			   } else if(time[1] > 0) {		   
				   l751.setText(seconds_string);
			   }
			  }
		});
		b151.addActionListener(this);
		
		timer.start();
	}
    
    public void question() {
		String[] question = questions.get(count);
		Label l141 = new Label("KAHOOT");
		l141.setBounds(50, 50, 200, 60);
		l141.setFont(titleBut);
		l141.setForeground(Color.WHITE);
		
		Label l241 = new Label("Game PIN: 2227");
		l241.setBounds(950, 50, 200, 60);
		l241.setFont(titleBut);
		l241.setForeground(Color.WHITE);
		
		Label l341 = new Label("Question " + (count + 1) + " of " + (questions.size()));
		l341.setBounds(200, 300, 200, 60);
		l341.setFont(titleBut);
		l341.setForeground(Color.WHITE);
		
		Label l441 = new Label(question[0]);
		l441.setBounds(250, 450, 700, 60);
		l441.setAlignment(Label.CENTER);
		l441.setFont(titleBut);
		l441.setBackground(new Color(215,216,214));
		l441.setForeground(new Color(36,48,94));
		
		Button b141 = new Button("View Answers");
		b141.setBounds(500, 550, 200, 60);  
		b141.setFont(titleBut);
		b141.setBackground(new Color(215,216,214));	
		b141.setForeground(new Color(36,48,94));
		b141.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){ 
				removeAll();
				viewAnswer();
			}
		});
		add(l141);
		add(l241);
		add(l341); 
		add(l441);
		add(b141);
		try {
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeUTF("start_signal");
            DataOutputStream dos1 = new DataOutputStream(s1.getOutputStream());
            dos1.writeUTF("start_signal");
            DataOutputStream dos2 = new DataOutputStream(s2.getOutputStream());
            dos2.writeUTF("start_signal");
        } catch (Exception e1) {
            try {
                System.exit(0);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
	}
    
    public void score() {
		Label l171 = new Label("Scoreboard");
		l171.setForeground(new Color(239,75,75));
		l171.setFont(titleFont);
		l171.setBounds(500, 150, 300, 50);
		l171.setAlignment(Label.CENTER);
		l171.setBackground(new Color(36,48,94));
		
		Label l271 = new Label("1st");
		l271.setForeground(Color.WHITE);
		l271.setFont(titleBut);
		l271.setBounds(150, 250, 100, 50);
		l271.setAlignment(Label.CENTER);
		l271.setBackground(new Color(36,48,94));
		
		Label l371 = new Label("2nd");
		l371.setForeground(Color.WHITE);
		l371.setFont(titleBut);
		l371.setBounds(150, 350, 100, 50);
		l371.setAlignment(Label.CENTER);
		l371.setBackground(new Color(36,48,94));
		
		Label l471 = new Label("3rd");
		l471.setForeground(Color.WHITE);
		l471.setFont(titleBut);
		l471.setBounds(150, 450, 100, 50);
		l471.setAlignment(Label.CENTER);
		l471.setBackground(new Color(36,48,94));
		
		
	    int[] ListScoret = new int [10];
	    String[] ListNamet = new String[10];
	    
	    for (int i = 0; i < 3; i++) {
	    	ListScoret[i] = ListScore[i];
	    }
	    
	    for (int i = 0; i < 3; i++) {
	    	ListNamet[i] = ListName[i];
	    }
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (ListScoret[i] > ListScoret[j]) {
					int t = ListScoret[i];
					String te_name = ListNamet[i];
					ListScoret[i] = ListScoret[j];
					ListNamet[i] = ListNamet[j];
					ListScoret[j] = t; 
					ListNamet[j] = te_name;
					
				}
			}
		}
		
		
		Label l571 = new Label("Name: "+ListNamet[0]+"                    Score: " + ListScoret[0]);
		l571.setFont(titleBut);
		l571.setBounds(350, 250, 700, 50);
		l571.setAlignment(Label.CENTER);
		l571.setBackground(new Color(215,216,214));	
		l571.setForeground(new Color(36,48,94));
		
		Label l671 = new Label("Name: "+ListNamet[1]+"                    Score: " + ListScoret[1]);
		l671.setFont(titleBut);
		l671.setBounds(350, 350, 700, 50);
		l671.setAlignment(Label.CENTER);
		l671.setBackground(new Color(215,216,214));	
		l671.setForeground(new Color(36,48,94));
		
		Label l771 = new Label("Name: "+ListNamet[2]+"                    Score: " + ListScoret[2]);
		l771.setFont(titleBut);
		l771.setBounds(350, 450, 700, 50);
		l771.setAlignment(Label.CENTER);
		l771.setBackground(new Color(215,216,214));	
		l771.setForeground(new Color(36,48,94));
		
		Button b171 = new Button("Next");
		b171.setBounds(900, 580, 150, 70);
		b171.setBackground(new Color(93, 109, 156));
		b171.setForeground(new Color(215,216,214));
		b171.setFont(titleBut);
		b171.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	removeAll();
            	if(count < questions.size()) {
            		question();
            	}
            }
        });
		
		add(l171);
		add(l271);
		add(l371);
		add(l471);
		add(l571);
		add(l671);
		add(l771);
		if(count < questions.size()) {
			add(b171);
		}
	}
    
    public void viewResult() {
		String[] question = questions.get(count);
		Label l161 = new Label(question[1]);
		l161.setBounds(120, 320, 450, 100); 
		l161.setAlignment(Label.CENTER);
		l161.setFont(titleBut);
		l161.setBackground(Color.PINK);	
		l161.setForeground(new Color(36,48,94));
		
		Label l261 = new Label(question[2]);
		l261.setBounds(120, 450, 450, 100);  
		l261.setAlignment(Label.CENTER);
		l261.setFont(titleBut);
		l261.setBackground(Color.YELLOW);	
		l261.setForeground(new Color(36,48,94));
		
		Label l361 = new Label(question[3]);
		l361.setBounds(630, 320, 450, 100); 
		l361.setAlignment(Label.CENTER);
		l361.setFont(titleBut);
		l361.setBackground(Color.GREEN);	
		l361.setForeground(new Color(36,48,94));
		
		Label l461 = new Label(question[4]);
		l461.setBounds(630, 450, 450, 100);  
		l461.setAlignment(Label.CENTER);
		l461.setFont(titleBut);
		l461.setBackground(Color.WHITE);	
		l461.setForeground(new Color(36,48,94));
					
		Label l561 = new Label(question[0]);
		l561.setBounds(250, 150, 700, 60);
		l561.setAlignment(Label.CENTER);
		l561.setFont(titleBut);
		l561.setBackground(new Color(215,216,214));
		l561.setForeground(new Color(36,48,94));
		
		String numa = String.valueOf(ListChose[0]);
		String numb = String.valueOf(ListChose[1]);
		String numc = String.valueOf(ListChose[2]);
		String numd = String.valueOf(ListChose[3]);
		Label l661 = new Label(numa);  
		l661.setBounds(250, 250, 100, 40);  
		l661.setAlignment(Label.RIGHT);
		l661.setFont(titleBut);
		l661.setBackground(Color.PINK);
		l661.setForeground(new Color(36,48,94));
		
		Label l761 = new Label(numb);  
		l761.setBounds(450, 250, 100, 40);  
		l761.setAlignment(Label.RIGHT);
		l761.setFont(titleBut);
		l761.setBackground(Color.YELLOW);
		l761.setForeground(new Color(36,48,94));
		
		Label l861 = new Label(numc);  
		l861.setBounds(650, 250, 100, 40);  
		l861.setAlignment(Label.RIGHT);
		l861.setFont(titleBut);
		l861.setBackground(Color.GREEN);
		l861.setForeground(new Color(36,48,94));
		
		Label l961 = new Label(numd);  
		l961.setBounds(850, 250, 100, 40);  
		l961.setAlignment(Label.RIGHT);
		l961.setFont(titleBut);
		l961.setBackground(Color.WHITE);
		l961.setForeground(new Color(36,48,94));
		
		for (int i = 0; i< 4; i++) ListChose[i]=0;
		
		Button b161 = new Button("Next");
		b161.setBounds(900, 580, 150, 70);
		b161.setBackground(new Color(93, 109, 156));
		b161.setForeground(new Color(215,216,214));
		b161.setFont(titleBut);
		b161.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				count++;
				removeAll();
				score();
			}
		});
		
		if(question[5].equals("1")) {
			l161.setFont(n_answer);
			l161.setForeground(Color.RED);
			l661.setFont(n_answer);
			l661.setForeground(Color.RED);
		} else if(question[5].equals("2")) {
			l261.setFont(n_answer);
			l261.setForeground(Color.RED);
			l761.setFont(n_answer);
			l761.setForeground(Color.RED);
		} else if(question[5].equals("3")) {
			l361.setFont(n_answer);
			l361.setForeground(Color.RED);
			l861.setFont(n_answer);
			l861.setForeground(Color.RED);
		} else if(question[5].equals("4")) {
			l461.setFont(n_answer);
			l461.setForeground(Color.RED);
			l961.setFont(n_answer);
			l961.setForeground(Color.RED);
		}
		
		add(l161);
		add(l261);
		add(l361);
		add(l461);
		add(l561);
		add(l661);
		add(l761);
		add(l861);
		add(l961);
		add(b161);
	}

    /////////////////// helpdesk Class Constructor ///////////////////
    public Main() throws Exception {
        setBackground(new Color(36,48,94));
        setTitle("Server");
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
        Font f1 = new Font("Georgia", Font.BOLD, 20);
        Font f2 = new Font("Georgia", Font.BOLD, 13);
        
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
        
        
        
        ss = new ServerSocket(2227);
		s = ss.accept();
		s1 = ss.accept();
		s2 = ss.accept();
        
        while (true) {
        	
	            try{
	            	DataInputStream dis = new DataInputStream(s.getInputStream());
	            	String str1 = dis.readUTF();
	            	if (str1.charAt(0) == 'c' && str1.charAt(1) == 'j') {
	            		str1 = str1.replace("cj", "");
	            		Label client1 = new Label(str1);
	            		ListName[0] = str1;
	            		client1.setBounds(500, 250, 200, 50);
	            		client1.setAlignment(Label.CENTER);
	            		client1.setFont(titleBut);
	            		client1.setBackground(new Color(57,68,110));
	            		//client1.setBackground(new Color(36,48,94));
	            		add(client1);
	            	}
	            	
	            	if (str1.charAt(0) == 'a' && str1.charAt(1) == 'a') {
	            		String[] question = questions.get(count);
	            		System.out.println(str1);
	            		str1 = str1.replace("aa", "");
	            		System.out.println(str1);
	            		int tempscore = Integer.parseInt(str1);
	            		ListChose[0] += 1;
	            		// cong diem cho client 0
	            		if(question[5].equals("1")) {
	            			tempscore += 200;
	            			ListScore[0] = tempscore; 
	            		}
	            	}
	            	
	            	if (str1.charAt(0) == 'b' && str1.charAt(1) == 'b') {
	            		String[] question = questions.get(count);
	            		System.out.println(str1);
	            		str1 = str1.replace("bb", "");
	            		System.out.println(str1);
	            		int tempscore = Integer.parseInt(str1);
	            		ListChose[1] += 1;
	            		// cong diem cho client 0
	            		if(question[5].equals("2")) {
	            			tempscore += 200;
	            			ListScore[0] = tempscore; 
	            		}
	            	}
	            	
	            	if (str1.charAt(0) == 'c' && str1.charAt(1) == 'c') {
	            		String[] question = questions.get(count);
	            		System.out.println(str1);
	            		str1 = str1.replace("cc", "");
	            		System.out.println(str1);
	            		int tempscore = Integer.parseInt(str1);
	            		ListChose[2] += 1;
	            		// cong diem cho client 0
	            		if(question[5].equals("3")) {
	            			tempscore += 200;
	            			ListScore[0] = tempscore; 
	            		}
	            	}
	            	
	            	if (str1.charAt(0) == 'd' && str1.charAt(1) == 'd') {
	            		String[] question = questions.get(count);
	            		System.out.println(str1);
	            		str1 = str1.replace("dd", "");
	            		System.out.println(str1);
	            		int tempscore = Integer.parseInt(str1);
	            		ListChose[3] += 1;
	            		// cong diem cho client 0
	            		if(question[5].equals("4")) {
	            			tempscore += 200;
	            			ListScore[0] = tempscore; 
	            		}
	            	}
	            	
	            	DataInputStream dis1 = new DataInputStream(s1.getInputStream());
	            	str1 = dis1.readUTF();
	            	if (str1.charAt(0) == 'c' && str1.charAt(1) == 'j') {
	            		str1 = str1.replace("cj", "");
	            		Label client1 = new Label(str1);
	            		ListName[1] = str1;
	            		client1.setBounds(500, 350, 200, 50);
	            		client1.setAlignment(Label.CENTER);
	            		client1.setFont(titleBut);
	            		client1.setBackground(new Color(57,68,110));
	            		//client1.setBackground(new Color(36,48,94));
	            		add(client1);
	            	}
	            	
	            	if (str1.charAt(0) == 'a' && str1.charAt(1) == 'a') {
	            		String[] question = questions.get(count);
	            		System.out.println(str1);
	            		str1 = str1.replace("aa", "");
	            		System.out.println(str1);
	            		int tempscore = Integer.parseInt(str1);
	            		ListChose[0] += 1;
	            		// cong diem cho client 0
	            		if(question[5].equals("1")) {
	            			tempscore += 200;
	            			ListScore[1] = tempscore; 
	            		}
	            	}
	            	
	            	if (str1.charAt(0) == 'b' && str1.charAt(1) == 'b') {
	            		String[] question = questions.get(count);
	            		System.out.println(str1);
	            		str1 = str1.replace("bb", "");
	            		System.out.println(str1);
	            		int tempscore = Integer.parseInt(str1);
	            		ListChose[1] += 1;
	            		// cong diem cho client 0
	            		if(question[5].equals("2")) {
	            			tempscore += 200;
	            			ListScore[1] = tempscore; 
	            		}
	            	}
	            	
	            	if (str1.charAt(0) == 'c' && str1.charAt(1) == 'c') {
	            		String[] question = questions.get(count);
	            		System.out.println(str1);
	            		str1 = str1.replace("cc", "");
	            		System.out.println(str1);
	            		int tempscore = Integer.parseInt(str1);
	            		ListChose[2] += 1;
	            		// cong diem cho client 0
	            		if(question[5].equals("3")) {
	            			tempscore += 200;
	            			ListScore[1] = tempscore; 
	            		}
	            	}
	            	
	            	if (str1.charAt(0) == 'd' && str1.charAt(1) == 'd') {
	            		String[] question = questions.get(count);
	            		System.out.println(str1);
	            		str1 = str1.replace("dd", "");
	            		System.out.println(str1);
	            		int tempscore = Integer.parseInt(str1);
	            		ListChose[3] += 1;
	            		// cong diem cho client 0
	            		if(question[5].equals("4")) {
	            			tempscore += 200;
	            			ListScore[1] = tempscore; 
	            		}
	            	}
	            	
	            	DataInputStream dis2 = new DataInputStream(s2.getInputStream());
	            	str1 = dis2.readUTF();
	            	if (str1.charAt(0) == 'c' && str1.charAt(1) == 'j') {
	            		str1 = str1.replace("cj", "");
	            		Label client1 = new Label(str1);
	            		ListName[2] = str1;
	            		client1.setBounds(500, 450, 200, 50);
	            		client1.setAlignment(Label.CENTER);
	            		client1.setFont(titleBut);
	            		client1.setBackground(new Color(57,68,110));
	            		//client1.setBackground(new Color(36,48,94));
	            		add(client1);
	            	}
	            	
	            	if (str1.charAt(0) == 'a' && str1.charAt(1) == 'a') {
	            		String[] question = questions.get(count);
	            		System.out.println(str1);
	            		str1 = str1.replace("aa", "");
	            		System.out.println(str1);
	            		int tempscore = Integer.parseInt(str1);
	            		ListChose[0] += 1;
	            		// cong diem cho client 0
	            		if(question[5].equals("1")) {
	            			tempscore += 200;
	            			ListScore[2] = tempscore; 
	            		}
	            	}
	            	
	            	if (str1.charAt(0) == 'b' && str1.charAt(1) == 'b') {
	            		String[] question = questions.get(count);
	            		System.out.println(str1);
	            		str1 = str1.replace("bb", "");
	            		System.out.println(str1);
	            		int tempscore = Integer.parseInt(str1);
	            		ListChose[1] += 1;
	            		// cong diem cho client 0
	            		if(question[5].equals("2")) {
	            			tempscore += 200;
	            			ListScore[2] = tempscore; 
	            		}
	            	}
	            	
	            	if (str1.charAt(0) == 'c' && str1.charAt(1) == 'c') {
	            		String[] question = questions.get(count);
	            		System.out.println(str1);
	            		str1 = str1.replace("cc", "");
	            		System.out.println(str1);
	            		int tempscore = Integer.parseInt(str1);
	            		ListChose[2] += 1;
	            		// cong diem cho client 0
	            		if(question[5].equals("3")) {
	            			tempscore += 200;
	            			ListScore[2] = tempscore; 
	            		}
	            	}
	            	
	            	if (str1.charAt(0) == 'd' && str1.charAt(1) == 'd') {
	            		String[] question = questions.get(count);
	            		System.out.println(str1);
	            		str1 = str1.replace("dd", "");
	            		System.out.println(str1);
	            		int tempscore = Integer.parseInt(str1);
	            		ListChose[3] += 1;
	            		// cong diem cho client 0
	            		if(question[5].equals("4")) {
	            			tempscore += 200;
	            			ListScore[2] = tempscore; 
	            		}
	            	}
	            	
	            	
	            } catch(Exception ee){
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
        }
    	// CLIENT
    	if (e.getSource() == b2) {
        	removeAll();
//        	Button b89 = new Button("PARTICIPANT MODE");
//            b89.setBounds(100, 100, 50, 100);
//            b89.setBackground(new Color(111, 66, 192));
//            b89.setForeground(Color.white);
//            b89.addActionListener(this);
//            add(b89);
            

            try {
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                dos.writeUTF("sendhide");
                DataOutputStream dos1 = new DataOutputStream(s1.getOutputStream());
                dos1.writeUTF("sendhide");
                DataOutputStream dos2= new DataOutputStream(s2.getOutputStream());
                dos2.writeUTF("sendhide");
            } catch (Exception e1) {
                try {
                    //Thread.sleep(3000);
                    System.exit(0);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    	
    	if ((e.getSource() == b21)) {
    		removeAll();
    		tf111 = new TextField("Question");
    		tf111.setBounds(475, 225, 250, 100);
    		tf211 = new TextField("Answer");
    		tf211.setBounds(250, 350, 250, 50);
    		tf311 = new TextField("Answer");
    		tf311.setBounds(700, 350, 250, 50);
    		tf411 = new TextField("Answer");
    		tf411.setBounds(250, 450, 250, 50);
    		tf511 = new TextField("Answer");
    		tf511.setBounds(700, 450, 250, 50);
    		cb1 = new Checkbox();
    		cb1.setBounds(510, 350, 50, 50);
    		cb1.setSize(50,50);
    		cb2 = new Checkbox();
    		cb2.setBounds(960, 350, 50, 50);
    		cb2.setSize(50,50);
    		cb3 = new Checkbox();
    		cb3.setBounds(510, 450, 50, 50);
    		cb3.setSize(50,50);
    		cb4 = new Checkbox();
    		cb4.setBounds(960, 450, 50, 50);
    		cb4.setSize(50,50);
    		b111 = new Button("Back");
    		b111.setBounds(200, 550, 150, 70);
    		b111.setBackground(new Color(93, 109, 156));
    		b111.setForeground(new Color(215,216,214));
    		b111.setFont(titleBut);
    		b111.addActionListener(this);
    		add(b111);
    		
    		b211 = new Button("Save");
    		b211.setBounds(850, 550, 150, 70);
    		b211.setBackground(new Color(93, 109, 156));
    		b211.setForeground(new Color(215,216,214));
    		b211.setFont(titleBut);
    		b211.addActionListener(this);
    		add(b211);
    		
			add(l);
			add(tf111);
			add(tf211);
			add(tf311);
			add(tf411);
			add(tf511);
			add(cb1);
			add(cb2);
			add(cb3);
			add(cb4);
    	}
    	if (e.getSource() == b111) {
        	removeAll();
			add(l);
			add(b11);
			add(b21);
			add(b31);
			add(b41);
			add(b51);
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
    	if (e.getSource() == b211) {
    		
//    		tf111 = new TextField("Question");
//    		tf111.setBounds(475, 225, 250, 100);
//    		tf211 = new TextField("Answer");
//    		tf211.setBounds(250, 350, 250, 50);
//    		tf311 = new TextField("Answer");
//    		tf311.setBounds(700, 350, 250, 50);
//    		tf411 = new TextField("Answer");
//    		tf411.setBounds(250, 450, 250, 50);
//    		tf511 = new TextField("Answer");
//    		tf511.setBounds(700, 450, 250, 50);
//    		cb1 = new Checkbox();
//    		cb1.setBounds(510, 350, 50, 50);
//    		cb1.setSize(50,50);
//    		cb2 = new Checkbox();
//    		cb2.setBounds(960, 350, 50, 50);
//    		cb2.setSize(50,50);
//    		cb3 = new Checkbox();
//    		cb3.setBounds(510, 450, 50, 50);
//    		cb3.setSize(50,50);
//    		cb4 = new Checkbox();
//    		cb4.setBounds(960, 450, 50, 50);
//    		cb4.setSize(50,50);
//    		add(b111);
    		String t111 = tf111.getText();
        	tf111.setText(t111);
        	String t211 = tf211.getText();
        	tf211.setText(t211);
        	String t311 = tf311.getText();
        	tf311.setText(t311);
        	String t411 = tf411.getText();
        	tf411.setText(t411);
        	String t511 = tf511.getText();
        	tf511.setText(t511);
        	boolean c1 = cb1.getState();
        	cb1.setState(c1);;
        	boolean c2 = cb2.getState();
        	cb2.setState(c2);
        	boolean c3 = cb3.getState();
        	cb3.setState(c3);
        	boolean c4 = cb4.getState();
        	cb4.setState(c4);
        	
        	String[] addQues = {null, null, null, null, null, null};
        	
        	addQues[0] = t111;
        	addQues[1] = t211;
        	addQues[2] = t311;
        	addQues[3] = t411;
        	addQues[4] = t511;
        	
        	if(c1 == true) {
        		addQues[5] = "1";
        	} else if(c2 == true) {
        		addQues[5] = "2";
        	} else if(c3 == true) {
        		addQues[5] = "3";
        	} else if(c4 == true) {
        		addQues[5] = "4";
        	}
        	
        	questions.add(addQues);
    	}
    	
    	// done
    	if (e.getSource() == b51) {
    		
    		removeAll();
    		
    		add(l);
    		
    		port = new TextField();
    		port.setBounds(500, 250, 200, 50);
    		port.setFont(titleBut);
            add(port);
            
            b132 = new Button("CREATE");
    		b132.setBounds(520, 450, 160, 60);  
    		b132.setFont(titleBut);
    		b132.setBackground(new Color(215,216,214));	
    		b132.setForeground(new Color(36,48,94));
    		b132.addActionListener(this);
            add(b132);
            
            l131 = new Label("Participants");
    		l131.setBounds(460, 340, 150, 60);
    		l131.setFont(titleBut);
    		l131.setForeground(new Color(247,108,108));
    		add(l131);
    		
    		l231 = new Label(""+maxNumClient);
    		l231.setAlignment(Label.CENTER);
    		l231.setBounds(640, 350, 100, 40);  
    		l231.setFont(titleBut);
    		l231.setBackground(new Color(215,216,214));
    		l231.setForeground(new Color(36,48,94));
    		add(l231);
    		
//    		numparti = new TextField();
//    		numparti.setBounds(640, 350, 100, 40);
//    		numparti.setFont(titleBut);
//            add(numparti);
        }
    	
    	if ((e.getSource() == b132) && (port.getText() != "")) {
    		removeAll();
    		int int_port = Integer.parseInt(port.getText());
    		System.out.println("Port start: " + int_port);
    		
    		Label player = new Label("LIST PLAYER");
    		player.setBounds(460, 100, 300, 50);
    		player.setAlignment(Label.CENTER);
    		player.setFont(titleBut);
    		player.setBackground(new Color(45,54,88));
    		add(player);
    		
    		startgame = new Button("START");
    		startgame.setBounds(520, 570, 160, 60);  
    		startgame.setFont(titleBut);
    		startgame.setBackground(new Color(215,216,214));	
    		startgame.setForeground(new Color(36,48,94));
    		startgame.addActionListener(this);
            add(startgame);
        }
    	
    	if ((e.getSource() == b132t) && (port1.getText() != "")) {
    		removeAll();
    		int int_port = Integer.parseInt(port1.getText());
    		System.out.println("Port start: " + int_port);
    		
    		Label player = new Label("LIST PLAYER");
    		player.setBounds(460, 100, 300, 50);
    		player.setAlignment(Label.CENTER);
    		player.setFont(titleBut);
    		player.setBackground(new Color(45,54,88));
    		add(player);
    		
    		startgame = new Button("START");
    		startgame.setBounds(520, 570, 160, 60);  
    		startgame.setFont(titleBut);
    		startgame.setBackground(new Color(215,216,214));	
    		startgame.setForeground(new Color(36,48,94));
    		startgame.addActionListener(this);
            add(startgame);
        }
    	
    	
    	
    	if ((e.getSource() == b151)) {
    		removeAll();
			timer.stop();
			viewResult();
			try {
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());     
                String temp=String.valueOf(ListScore[0]);
                dos.writeUTF("sc"+temp);//send score
                
                DataOutputStream dos1 = new DataOutputStream(s1.getOutputStream());     
                String temp1=String.valueOf(ListScore[1]);
                dos1.writeUTF("sc"+temp1);//send score
                
                DataOutputStream dos2 = new DataOutputStream(s2.getOutputStream());     
                String temp2=String.valueOf(ListScore[2]);
                dos2.writeUTF("sc"+temp2);//send score
            } catch (Exception e1) {
                try {
                    System.exit(0);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } 
    	}
    	if (e.getSource() == b31) {
    		/////Setting////////////////////
    		
    		
    		tf121 = new TextField();
    		tf121.setBounds(700, 250, 100, 50);
    		tf121.setFont(titleFont);
    		tf121.setText("10");
    		
    		tf221 = new TextField();
    		tf221.setBounds(700, 350, 100, 50);
    		tf221.setFont(titleFont);
    		tf221.setText("30");
    		
    		tf321 = new TextField();
    		tf321.setBounds(700, 450, 100, 50);
    		tf321.setFont(titleFont);
    		tf321.setText("4");
    		
    		Label l121 = new Label("Current questions");
    		l121.setBounds(400, 250, 250, 50);
    		l121.setFont(titleBut);
    		l121.setForeground(Color.WHITE);
    		
    		Label l221 = new Label("Set time (s)");
    		l221.setBounds(400, 350, 250, 50);
    		l221.setFont(titleBut);
    		l221.setForeground(Color.WHITE);
    		
    		Label l321 = new Label("Set participants");
    		l321.setBounds(400, 450, 250, 50);
    		l321.setFont(titleBut);
    		l321.setForeground(Color.WHITE);
    		
    		b121 = new Button("Back");
    		b121.setBounds(200, 550, 150, 70);
    		b121.setBackground(new Color(93, 109, 156));
    		b121.setForeground(new Color(215,216,214));
    		b121.setFont(titleBut);
    		b121.addActionListener(this);
    		
    		
    		
    		b221 = new Button("Done");
    		b221.setBounds(850, 550, 150, 70);
    		b221.setBackground(new Color(93, 109, 156));
    		b221.setForeground(new Color(215,216,214));
    		b221.setFont(titleBut);
    		b221.addActionListener(this);
    		
    		removeAll();
			add(l);
			add(tf121);
			add(tf221);
			add(tf321);
			add(b121);
			add(b221);
			add(l121);
			add(l221);
			add(l321);
    	}
    	if (e.getSource() == b221) {
    		String t121 = tf121.getText();
        	tf121.setText(t121);
        	String t221 = tf221.getText();
        	tf221.setText(t221);
        	String t321 = tf321.getText();
        	tf321.setText(t321);
        	maxNumClient = Integer.parseInt(t321);
        	
    		
    		port1 = new TextField();
    		port1.setBounds(500, 250, 200, 50);
    		port1.setFont(titleBut);
            
            
            l231 = new Label(""+maxNumClient);
    		l231.setAlignment(Label.CENTER);
    		l231.setBounds(640, 350, 100, 40);  
    		l231.setFont(titleBut);
    		l231.setBackground(new Color(215,216,214));
    		l231.setForeground(new Color(36,48,94));
    		
    		
    		Label l1311 = new Label("Participants");
    		l1311.setBounds(460, 340, 150, 60);
    		l1311.setFont(titleBut);
    		l1311.setForeground(new Color(247,108,108));
			
    		startgame1 = new Button("START");
    		startgame1.setBounds(520, 570, 160, 60);  
    		startgame1.setFont(titleBut);
    		startgame1.setBackground(new Color(215,216,214));	
    		startgame1.setForeground(new Color(36,48,94));
    		startgame1.addActionListener(this);
    		
    		b132t = new Button("CREATE");
    		b132t.setBounds(520, 450, 160, 60);  
    		b132t.setFont(titleBut);
    		b132t.setBackground(new Color(215,216,214));	
    		b132t.setForeground(new Color(36,48,94));
    		b132t.addActionListener(this);
            
    		
			settings[0] = Integer.parseInt(t121);
			settings[1] = Integer.parseInt(t221);
			settings[2] = Integer.parseInt(t321);
			
			
			
			removeAll();
			add(b132t);
			add(l231);
			add(port1);
			add(l1311);
			//add(l331);
			add(l);
			
    	}
    	
    	if (e.getSource() == b121) {
    		removeAll();
    		add(l);
    		add(b11);
    		add(b21);
    		add(b31);
			add(b41);
			add(b51);
    	}
    	
    	if ((e.getSource() == startgame)) {
    		removeAll();
    		question();
    		
    	}
    	
    	if ((e.getSource() == startgame1)) {
    		removeAll();
    		question();
    		
    	}
    }

    ///////////// Entry gate main function //////////
    public static void main(String[] args) throws Exception {
        Main obj = new Main();
    }
}