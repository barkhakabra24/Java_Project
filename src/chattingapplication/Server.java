package chattingapplication;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author bkabra
 */
public class Server extends JFrame implements ActionListener {
    
    JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea a1;
    static ServerSocket skt;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    boolean typing;
    Server() {
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 70);
        add(p1);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("chattingapplication/icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(5, 20, 25, 25);
        p1.add(l1);
        l1.addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent ae){
               System.exit(0);
           }
        });
       
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("chattingapplication/icons/user.png"));
        Image i5 = i4.getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT);
        ImageIcon i6= new ImageIcon(i5);
        JLabel l2 = new JLabel(i6);
        l2.setBounds(40,5,60,60);
        p1.add(l2);
        
         ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("chattingapplication/icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel l5 = new JLabel(i9);
        l5.setBounds(290, 20, 30, 30);
        p1.add(l5);

        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("chattingapplication/icons/phone.png"));
        Image i12 = i11.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        ImageIcon i13 = new ImageIcon(i12);
        JLabel l6 = new JLabel(i13);
        l6.setBounds(350, 20, 35, 30);
        p1.add(l6);

        ImageIcon i14 = new ImageIcon(ClassLoader.getSystemResource("chattingapplication/icons/3icon.png"));
        Image i15 = i14.getImage().getScaledInstance(13, 25, Image.SCALE_DEFAULT);
        ImageIcon i16 = new ImageIcon(i15);
        JLabel l7 = new JLabel(i16);
        l7.setBounds(410, 20, 13, 25);
        p1.add(l7);

        JLabel l3 = new JLabel("Barkha");
        l3.setBounds(110, 15, 100, 18);
        l3.setFont(new Font("SAN_SERIF",Font.PLAIN, 18));
        l3.setForeground(Color.WHITE);
        p1.add(l3);
        
        
        JLabel l4 = new JLabel("Active Now");
        l4.setBounds(110, 35, 100, 20);
        l4.setFont(new Font("SAN_SERIF",Font.PLAIN, 14));
        l4.setForeground(Color.WHITE);
        p1.add(l4);
        
        Timer t = new Timer(1,new ActionListener(){
            public void actionPerformed(ActionEvent ae){
               if(!typing){
                   l4.setText("Active Now");
               }
            }
        });
        
        t.setInitialDelay(1000);
        
        a1 = new JTextArea();
        a1.setBounds(5, 75, 440, 470);
        a1.setFont(new Font("SAN_SERIF",Font.PLAIN, 16));
        a1.setEditable(false);
        a1.setLineWrap(true);
        a1.setWrapStyleWord(true);
        add(a1);
        
        
        t1 = new JTextField();
        t1.setBounds(5,550,310,40);
        t1.setFont(new Font("SAN_SERIF",Font.PLAIN, 16));
        add(t1);
        t1.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent ke){
                l4.setText("typing...");
                t.stop();
                typing = true;
            }
            public void keyReleased(KeyEvent ke){
                typing=false;
                t.start();
            }
        });
        
        b1 = new JButton("Send");
        b1.setBounds(320, 550, 123, 40);
        b1.setBackground(new Color(7, 94, 84));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("SAN_SERIF", Font.BOLD, 16));
        b1.addActionListener(this);
        add(b1);
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        setSize(450,600);
        setLocation(150,50);
        setUndecorated(true);
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        try {
            String out = t1.getText();
            a1.setText(a1.getText()+"\n\t\t\t"+out);
            dout.writeUTF(out);
            t1.setText("");
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String args[]){
        new Server().setVisible(true);
        String msginput = "";
        try{
            skt = new ServerSocket(6001); 
            s = skt.accept();
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            while(true){
               msginput = din.readUTF();
               a1.setText(a1.getText()+"\n"+msginput);
            }
        }catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
