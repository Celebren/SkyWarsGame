package skyWars;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		ButtonsLogic bl1 = new ButtonsLogic();
		
		setTitle("Sky Wars Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTile1 = new JLabel("tile01");
		lblTile1.setForeground(Color.BLACK);
		lblTile1.setBackground(Color.DARK_GRAY);
		lblTile1.setBounds(10, 11, 46, 46);
		lblTile1.setOpaque(true);
		contentPane.add(lblTile1);
		
		JLabel lblTile2 = new JLabel("tile02");
		lblTile2.setForeground(Color.BLACK);
		lblTile2.setBackground(Color.DARK_GRAY);
		lblTile2.setBounds(63, 11, 46, 46);
		lblTile2.setOpaque(true);
		contentPane.add(lblTile2);
		
		JLabel lblTile3 = new JLabel("tile03");
		lblTile3.setForeground(Color.BLACK);
		lblTile3.setBackground(Color.DARK_GRAY);
		lblTile3.setBounds(120, 11, 46, 46);
		lblTile3.setOpaque(true);
		contentPane.add(lblTile3);
		
		JLabel lblTile4 = new JLabel("tile04");
		lblTile4.setForeground(Color.BLACK);
		lblTile4.setBackground(Color.DARK_GRAY);
		lblTile4.setBounds(176, 11, 46, 46);
		lblTile4.setOpaque(true);
		contentPane.add(lblTile4);
		
		JLabel lblTile5 = new JLabel("tile05");
		lblTile5.setForeground(Color.BLACK);
		lblTile5.setBackground(Color.DARK_GRAY);
		lblTile5.setBounds(10, 59, 46, 46);
		lblTile5.setOpaque(true);
		contentPane.add(lblTile5);
		
		JLabel lblTile6 = new JLabel("tile06");
		lblTile6.setForeground(Color.BLACK);
		lblTile6.setBackground(Color.DARK_GRAY);
		lblTile6.setBounds(63, 59, 46, 46);
		lblTile6.setOpaque(true);
		contentPane.add(lblTile6);
		
		JLabel lblTile7 = new JLabel("tile07");
		lblTile7.setForeground(Color.BLACK);
		lblTile7.setBackground(Color.DARK_GRAY);
		lblTile7.setBounds(120, 59, 46, 46);
		lblTile7.setOpaque(true);
		contentPane.add(lblTile7);
		
		JLabel lblTile8 = new JLabel("tile08");
		lblTile8.setForeground(Color.BLACK);
		lblTile8.setBackground(Color.DARK_GRAY);
		lblTile8.setBounds(176, 59, 46, 46);
		lblTile8.setOpaque(true);
		contentPane.add(lblTile8);
		
		JLabel lblTile9 = new JLabel("tile09");
		lblTile9.setForeground(Color.BLACK);
		lblTile9.setBackground(Color.DARK_GRAY);
		lblTile9.setBounds(10, 116, 46, 46);
		lblTile9.setOpaque(true);
		contentPane.add(lblTile9);
		
		JLabel lblTile10 = new JLabel("tile10");
		lblTile10.setForeground(Color.BLACK);
		lblTile10.setBackground(Color.DARK_GRAY);
		lblTile10.setBounds(63, 116, 46, 46);
		lblTile10.setOpaque(true);
		contentPane.add(lblTile10);
		
		JLabel lblTile11 = new JLabel("tile11");
		lblTile11.setForeground(Color.BLACK);
		lblTile11.setBackground(Color.DARK_GRAY);
		lblTile11.setBounds(119, 116, 46, 46);
		lblTile11.setOpaque(true);
		contentPane.add(lblTile11);
		
		JLabel lblTile12 = new JLabel("tile12");
		lblTile12.setForeground(Color.BLACK);
		lblTile12.setBackground(Color.DARK_GRAY);
		lblTile12.setBounds(176, 116, 46, 46);
		lblTile12.setOpaque(true);
		contentPane.add(lblTile12);
		
		JLabel lblTile13 = new JLabel("tile13");
		lblTile13.setForeground(Color.BLACK);
		lblTile13.setBackground(Color.DARK_GRAY);
		lblTile13.setBounds(10, 173, 46, 46);
		lblTile13.setOpaque(true);
		contentPane.add(lblTile13);
		
		JLabel lblTile14 = new JLabel("tile14");
		lblTile14.setForeground(Color.BLACK);
		lblTile14.setBackground(Color.DARK_GRAY);
		lblTile14.setBounds(63, 173, 46, 46);
		lblTile14.setOpaque(true);
		contentPane.add(lblTile14);
		
		JLabel lblTile15 = new JLabel("tile15");
		lblTile15.setForeground(Color.BLACK);
		lblTile15.setBackground(Color.DARK_GRAY);
		lblTile15.setBounds(120, 173, 46, 46);
		lblTile15.setOpaque(true);
		contentPane.add(lblTile15);
		
		JLabel lblTile16 = new JLabel("tile16");
		lblTile16.setForeground(Color.BLACK);
		lblTile16.setBackground(Color.DARK_GRAY);
		lblTile16.setBounds(176, 173, 46, 46);
		lblTile16.setOpaque(true);
		contentPane.add(lblTile16);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetTiles(lblTile1, lblTile2, lblTile3, lblTile4, lblTile5, lblTile6, lblTile7, lblTile8, lblTile9, lblTile10, lblTile11, lblTile12, lblTile13, lblTile14, lblTile15, lblTile16);
				System.out.println("reset clicked");
				
				int spawn = bl1.masterShipSpawn();
				switch(spawn) {
					case 1: lblTile1.setBackground(Color.RED); break;
					case 2: lblTile2.setBackground(Color.RED); break;
					case 3: lblTile3.setBackground(Color.RED); break;
					case 4: lblTile4.setBackground(Color.RED); break;
					case 5: lblTile5.setBackground(Color.RED); break;
					case 6: lblTile6.setBackground(Color.RED); break;
					case 7: lblTile7.setBackground(Color.RED); break;
					case 8: lblTile8.setBackground(Color.RED); break;
					case 9: lblTile9.setBackground(Color.RED); break;
					case 10: lblTile10.setBackground(Color.RED); break;
					case 11: lblTile11.setBackground(Color.RED); break;
					case 12: lblTile12.setBackground(Color.RED); break;
					case 13: lblTile13.setBackground(Color.RED); break;
					case 14: lblTile14.setBackground(Color.RED); break;
					case 15: lblTile15.setBackground(Color.RED); break;
					case 16: lblTile16.setBackground(Color.RED); break;
				}
			}
		});
		btnReset.setBounds(232, 196, 89, 23);
		contentPane.add(btnReset);
		
		JButton btnMove = new JButton("Move");
		btnMove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetTiles(lblTile1, lblTile2, lblTile3, lblTile4, lblTile5, lblTile6, lblTile7, lblTile8, lblTile9, lblTile10, lblTile11, lblTile12, lblTile13, lblTile14, lblTile15, lblTile16);
				
				int move = bl1.moveMasterShip();
				switch(move) {
				case 1: lblTile1.setBackground(Color.RED); break;
				case 2: lblTile2.setBackground(Color.RED); break;
				case 3: lblTile3.setBackground(Color.RED); break;
				case 4: lblTile4.setBackground(Color.RED); break;
				case 5: lblTile5.setBackground(Color.RED); break;
				case 6: lblTile6.setBackground(Color.RED); break;
				case 7: lblTile7.setBackground(Color.RED); break;
				case 8: lblTile8.setBackground(Color.RED); break;
				case 9: lblTile9.setBackground(Color.RED); break;
				case 10: lblTile10.setBackground(Color.RED); break;
				case 11: lblTile11.setBackground(Color.RED); break;
				case 12: lblTile12.setBackground(Color.RED); break;
				case 13: lblTile13.setBackground(Color.RED); break;
				case 14: lblTile14.setBackground(Color.RED); break;
				case 15: lblTile15.setBackground(Color.RED); break;
				case 16: lblTile16.setBackground(Color.RED); break;
			}
			}
		});
		btnMove.setBounds(331, 196, 89, 23);
		contentPane.add(btnMove);
	} // end GUI()
	
	public void resetTiles(JLabel lblTile1, JLabel lblTile2, JLabel lblTile3, JLabel lblTile4, JLabel lblTile5, JLabel lblTile6, JLabel lblTile7, JLabel lblTile8, JLabel lblTile9, JLabel lblTile10, JLabel lblTile11, JLabel lblTile12, JLabel lblTile13, JLabel lblTile14, JLabel lblTile15, JLabel lblTile16) {
		lblTile1.setBackground(Color.DARK_GRAY); 
		lblTile2.setBackground(Color.DARK_GRAY); 
		lblTile3.setBackground(Color.DARK_GRAY); 
		lblTile4.setBackground(Color.DARK_GRAY); 
		lblTile5.setBackground(Color.DARK_GRAY); 
		lblTile6.setBackground(Color.DARK_GRAY); 
		lblTile7.setBackground(Color.DARK_GRAY); 
		lblTile8.setBackground(Color.DARK_GRAY); 
		lblTile9.setBackground(Color.DARK_GRAY); 
		lblTile10.setBackground(Color.DARK_GRAY); 
		lblTile11.setBackground(Color.DARK_GRAY); 
		lblTile12.setBackground(Color.DARK_GRAY); 
		lblTile13.setBackground(Color.DARK_GRAY); 
		lblTile14.setBackground(Color.DARK_GRAY); 
		lblTile15.setBackground(Color.DARK_GRAY); 
		lblTile16.setBackground(Color.DARK_GRAY);
	}
}
