package com.github.luan004.JavaBot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class Main {
	
	private static JTextArea log = new JTextArea();
	private static char prefixo = '?';
	
    public static void main(String[] args) {
    	// TOKEN
    	Token tokenClass = new Token();
        String token = tokenClass.getToken();
        
        // API
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();

    	//UX
    	ux();
        
        // COMANDOS
       	Comandos.ping(api);
       	Comandos.caraCoroa(api);
       	Comandos.stop(api);
       	Comandos.say(api);
       	Comandos.conectar(api);
       	//Comandos.sorteio(api);
       	Comandos.avatar(api);
       	Comandos.serverinfo(api);
       	Comandos.sayd(api);
       	Comandos.spam(api);
    }
    	
    	private static void ux() {
        	JFrame frame = new JFrame();
        	JLabel label = new JLabel("O Bot está online!");
        	log.setEditable(false);
    		Box vBox = Box.createVerticalBox();
    		vBox.add(label);
    		JScrollPane scroll = new JScrollPane (log, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    		vBox.add(scroll);
    		JButton bDesligar = new JButton("Desligar o Bot");
    		vBox.add(bDesligar);
    		
    		bDesligar.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				
    				if (JOptionPane.showConfirmDialog(frame,"Tem certeza que deseja desligar o JavaBot?", "Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
    					System.exit(prefixo);
    				}
    			      
    			}
    		});
    		
    		
        	frame.add(vBox);
    		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    		frame.setSize(500, 400);
    		frame.setVisible(true);
    		
    		
    		log("Bot iniciado!");
		
	}

		//LOG
    	public static void logUsoComando(String comando, String canal) {
    		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    		LocalDateTime now = LocalDateTime.now(); 
    		log.setText(log.getText() + dtf.format(now) +" | "+comando+" utilizado no canal #"+canal+"\n");
    	}
    	public static void log(String texto) {
    		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    		LocalDateTime now = LocalDateTime.now(); 
    		log.setText(log.getText() +dtf.format(now)+" | " +texto +"\n");
    	}
    	
    	
    	// GETTER E SETTER DO PREFIXO
		public static char getPrefixo() {
			return prefixo;
		}
		public static void setPrefixo(char prefixo) {
			Main.prefixo = prefixo;
		}
    	
    	

}