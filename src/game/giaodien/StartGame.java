package game.giaodien;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.play.GAME;


public class StartGame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5343347362428808735L;
	private ImageIcon BackGround;
    private JPanel jpanel;
    private JButton a,b,c;
    public StartGame() {
    	JLabel background;
        
        setSize(700,500);
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        BackGround = new ImageIcon(getClass().getClassLoader().getResource("images/anhbatdau.jpg"));
       
        setResizable(false);
        setLocationRelativeTo(null);
		background = new JLabel("",BackGround,JLabel.CENTER);
		background.setBounds(0,0,700,500);
		add(background);
		setVisible(true);

        jpanel = new JPanel() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (BackGround != null) {
                    g.drawImage(BackGround.getImage(), 0, 0, getWidth(), getHeight(), null);
                }
            }
        };
        setContentPane(jpanel);

        this.setLayout(null);
        BeginControl(); 
    }
 // tao cac button  
    public void BeginControl(){
        a = new JButton("Easy");
        add(a);
        a.setSize(100, 50);
        a.setLocation(300, 100);
        a.addActionListener(this);
        
        b = new JButton("Hard");
        add(b);
        b.setSize(100, 50);
        b.setLocation(300, 200);
        b.addActionListener(this);
        
        c= new JButton("Quit");
        add(c);
        c.setSize(100,50);
        c.setLocation(300,300);
        c.addActionListener(this);
    }
// Button perform    
    @Override
    public void actionPerformed(ActionEvent e){
        JButton btn =(JButton) e.getSource();
        
        if (btn == a){
            this.dispose();
            GAME ez = new GAME(1);
            ez.start();
            
        }
        else if(btn == b) {
        	this.dispose();
        	GAME dif = new GAME(10);
        	dif.start();
        }
        else if(btn == c) {
        	System.exit(0); 
        }
    }
    
	public static void main(String [] args) {
        StartGame sGame = new StartGame();
        sGame.setVisible(true);
	}
}
