import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.xml.internal.bind.v2.model.core.Element;

public class RSSReader extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JPanel pan = new JPanel();
	
	public RSSReader(){
		
		JFrame j = new JFrame();
		j.setVisible(true);
		j.setSize(1000, 600);
		j.setTitle("Flux RSS : Le Dojo Manga");
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setLocationRelativeTo(null);
			
		JTextArea text = new JTextArea(readRSS("http://www.ledojomanga.com/rss.xml"));
		j.setLayout(new GridLayout(1, 1));
		j.getContentPane().add(text);
		j.setVisible(true);
		j.setBounds(0, 0, 900, 600);
		
		JScrollPane scrollpane = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		j.setPreferredSize(new Dimension(450, 110));
		j.add(scrollpane, BorderLayout.CENTER);
		
		/* JMenu Bar */ 
		
		JMenuBar menuBar = new JMenuBar();
		JMenu flux = new JMenu("Fichier");
		JMenu quitter = new JMenu("Quitter");
		JMenuItem fermer = new JMenuItem("Fermer");
		JMenuItem animekun = new JMenuItem("Anime Kun");
		JMenuItem mangasanc = new  JMenuItem("Manga Sanctuary");
		
		flux.add(animekun);
		flux.add(mangasanc);
		quitter.add(fermer);
		menuBar.add(flux);
		menuBar.add(quitter);
		j.setJMenuBar(menuBar);
		j.setVisible(true);
	
		fermer.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				System.exit(0);
			}
		});
		mangasanc.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				j.setVisible(false);
				new RSSReader3();
			}
		});
		
		animekun.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				j.setVisible(false);
				new RSSReader2();
			}
		});
		
	}
	
	public static void main(String[] args) {
		new RSSReader();	
	}
	
	public static String readRSS(String urlAddress){
		try{
			URL rssUrl = new URL(urlAddress);
			BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream(),"UTF-8"));
			String sourceCode ="";
			String line;
			while((line = in.readLine())!=null){
				// title, link, description, pubDate
							
				if(line.contains("<title>")){
					int firstPos = line.indexOf("<title>");
					String temp = line.substring(firstPos);
					temp = temp.replace("<title>","");
					int lastPos = temp.indexOf("</title>");
					temp = temp.substring(0, lastPos);
					sourceCode += temp+"\n";
				}
				
				if(line.contains("<description>")){
					int firstP = line.indexOf("<description>");
					String tem = line.substring(firstP);
					tem = tem.replace("<description>","");
					//int lastPo = tem.indexOf("</description>");
					//tem = tem.substring(0, lastPo);
					sourceCode += tem+"\n";
					
				}
				
				if(line.contains("<link>")){
					int firstPoss = line.indexOf("<link>");
					String temps = line.substring(firstPoss);
					temps = temps.replace("<link>","");
					int lastPoss = temps.indexOf("</link>");
					temps = temps.substring(0, lastPoss);
					sourceCode += temps+"\n";
				}
			
				if(line.contains("<pubDate>")){
					int firstPosss = line.indexOf("<pubDate>");
					String tempss = line.substring(firstPosss);
					tempss = tempss.replace("<pubDate>","");
					int lastPosss = tempss.indexOf("</pubDate>");
					tempss = tempss.substring(0, lastPosss);
					sourceCode += tempss+"\n \n";	
				}
				
			}
			in.close();
			return sourceCode;
			
		}catch(MalformedURLException ue){
			System.out.println("Mal formed URL");
		}
		catch(IOException ioe){
			System.out.println("test");
		}
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}