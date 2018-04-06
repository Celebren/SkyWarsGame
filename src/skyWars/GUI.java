package skyWars;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.GridLayout;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class GUI extends JFrame {

	private JPanel contentPane;
	private int NUMBER_OF_TILES = 16;
	private final String SKY_BACKGROUND = "/images/space.jpg";
	private final String MASTER_SHIP_DEFENSIVE = "/images/cobra_defensive.png";
	private final String MASTER_SHIP_OFFENSIVE = "/images/cobra_offensive.png";
	private final String BATTLE_STAR = "/images/fighterSmall.png";
	private final String BATTLE_CRUISER = "/images/corvetteSmall.png";
	private final String BATTLE_SHOOTER = "/images/gunshipSmall.png";

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
		ArrayList<JLabel> listOfTiles = new ArrayList<JLabel>();
		Border border = LineBorder.createGrayLineBorder();
		
		setTitle("Sky Wars Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTile1 = new JLabel("tile01");
		lblTile1.setVerticalAlignment(SwingConstants.TOP);
		lblTile1.setForeground(Color.CYAN);
		lblTile1.setBackground(Color.DARK_GRAY);
		lblTile1.setBounds(35, 61, 100, 100);
		lblTile1.setBorder(border);
		contentPane.add(lblTile1);
		
		JLabel lblTile2 = new JLabel("tile02");
		lblTile2.setVerticalAlignment(SwingConstants.TOP);
		lblTile2.setForeground(Color.CYAN);
		lblTile2.setBackground(Color.DARK_GRAY);
		lblTile2.setBounds(234, 61, 100, 100);
		lblTile2.setBorder(border);
		contentPane.add(lblTile2);
		
		JLabel lblTile3 = new JLabel("tile03");
		lblTile3.setVerticalAlignment(SwingConstants.TOP);
		lblTile3.setForeground(Color.CYAN);
		lblTile3.setBackground(Color.DARK_GRAY);
		lblTile3.setBounds(134, 61, 100, 100);
		lblTile3.setBorder(border);
		contentPane.add(lblTile3);
		
		JLabel lblTile4 = new JLabel("tile04");
		lblTile4.setVerticalAlignment(SwingConstants.TOP);
		lblTile4.setForeground(Color.CYAN);
		lblTile4.setBackground(Color.DARK_GRAY);
		lblTile4.setBounds(333, 61, 100, 100);
		lblTile4.setBorder(border);
		contentPane.add(lblTile4);
		
		JLabel lblTile5 = new JLabel("tile05");
		lblTile5.setVerticalAlignment(SwingConstants.TOP);
		lblTile5.setForeground(Color.CYAN);
		lblTile5.setBackground(Color.DARK_GRAY);
		lblTile5.setBounds(35, 161, 100, 100);
		lblTile5.setBorder(border);
		contentPane.add(lblTile5);
		
		JLabel lblTile6 = new JLabel("tile06");
		lblTile6.setVerticalAlignment(SwingConstants.TOP);
		lblTile6.setForeground(Color.CYAN);
		lblTile6.setBackground(Color.DARK_GRAY);
		lblTile6.setBounds(134, 161, 100, 100);
		lblTile6.setBorder(border);
		contentPane.add(lblTile6);
		
		JLabel lblTile7 = new JLabel("tile07");
		lblTile7.setVerticalAlignment(SwingConstants.TOP);
		lblTile7.setForeground(Color.CYAN);
		lblTile7.setBackground(Color.DARK_GRAY);
		lblTile7.setBounds(234, 161, 100, 100);
		lblTile7.setBorder(border);
		contentPane.add(lblTile7);
		
		JLabel lblTile8 = new JLabel("tile08");
		lblTile8.setVerticalAlignment(SwingConstants.TOP);
		lblTile8.setForeground(Color.CYAN);
		lblTile8.setBackground(Color.DARK_GRAY);
		lblTile8.setBounds(333, 161, 100, 100);
		lblTile8.setBorder(border);
		contentPane.add(lblTile8);
		
		JLabel lblTile9 = new JLabel("tile09");
		lblTile9.setVerticalAlignment(SwingConstants.TOP);
		lblTile9.setForeground(Color.CYAN);
		lblTile9.setBackground(Color.DARK_GRAY);
		lblTile9.setBounds(35, 261, 100, 100);
		lblTile9.setBorder(border);
		contentPane.add(lblTile9);
		
		JLabel lblTile10 = new JLabel("tile10");
		lblTile10.setVerticalAlignment(SwingConstants.TOP);
		lblTile10.setForeground(Color.CYAN);
		lblTile10.setBackground(Color.DARK_GRAY);
		lblTile10.setBounds(134, 261, 100, 100);
		lblTile10.setBorder(border);
		contentPane.add(lblTile10);
		
		JLabel lblTile11 = new JLabel("tile11");
		lblTile11.setVerticalAlignment(SwingConstants.TOP);
		lblTile11.setForeground(Color.CYAN);
		lblTile11.setBackground(Color.DARK_GRAY);
		lblTile11.setBounds(234, 261, 100, 100);
		lblTile11.setBorder(border);
		contentPane.add(lblTile11);
		
		JLabel lblTile12 = new JLabel("tile12");
		lblTile12.setVerticalAlignment(SwingConstants.TOP);
		lblTile12.setForeground(Color.CYAN);
		lblTile12.setBackground(Color.DARK_GRAY);
		lblTile12.setBounds(333, 261, 100, 100);
		lblTile12.setBorder(border);
		contentPane.add(lblTile12);
		
		JLabel lblTile13 = new JLabel("tile13");
		lblTile13.setVerticalAlignment(SwingConstants.TOP);
		lblTile13.setForeground(Color.CYAN);
		lblTile13.setBackground(Color.DARK_GRAY);
		lblTile13.setBounds(35, 360, 100, 100);
		lblTile13.setBorder(border);
		contentPane.add(lblTile13);
		
		JLabel lblTile14 = new JLabel("tile14");
		lblTile14.setVerticalAlignment(SwingConstants.TOP);
		lblTile14.setForeground(Color.CYAN);
		lblTile14.setBackground(Color.DARK_GRAY);
		lblTile14.setBounds(134, 360, 100, 100);
		lblTile14.setBorder(border);
		contentPane.add(lblTile14);
		
		JLabel lblTile15 = new JLabel("tile15");
		lblTile15.setVerticalAlignment(SwingConstants.TOP);
		lblTile15.setForeground(Color.CYAN);
		lblTile15.setBackground(Color.DARK_GRAY);
		lblTile15.setBounds(234, 360, 100, 100);
		lblTile15.setBorder(border);
		contentPane.add(lblTile15);
		
		JLabel lblTile16 = new JLabel("tile16");
		lblTile16.setVerticalAlignment(SwingConstants.TOP);
		lblTile16.setForeground(Color.CYAN);
		lblTile16.setBackground(Color.DARK_GRAY);
		lblTile16.setBounds(333, 360, 100, 100);
		lblTile16.setBorder(border);
		contentPane.add(lblTile16);
		
		JLabel starlblTile1 = new JLabel("tile01");
		starlblTile1.setVerticalAlignment(SwingConstants.TOP);
		starlblTile1.setForeground(Color.CYAN);
		starlblTile1.setBackground(Color.DARK_GRAY);
		starlblTile1.setBounds(35, 61, 100, 100);
		starlblTile1.setBorder(border);
		contentPane.add(starlblTile1);
		
		JLabel starlblTile2 = new JLabel("tile02");
		starlblTile2.setVerticalAlignment(SwingConstants.TOP);
		starlblTile2.setForeground(Color.CYAN);
		starlblTile2.setBackground(Color.DARK_GRAY);
		starlblTile2.setBounds(234, 61, 100, 100);
		starlblTile2.setBorder(border);
		contentPane.add(starlblTile2);
		
		JLabel starlblTile3 = new JLabel("tile03");
		starlblTile3.setVerticalAlignment(SwingConstants.TOP);
		starlblTile3.setForeground(Color.CYAN);
		starlblTile3.setBackground(Color.DARK_GRAY);
		starlblTile3.setBounds(134, 61, 100, 100);
		starlblTile3.setBorder(border);
		contentPane.add(starlblTile3);
		
		JLabel starlblTile4 = new JLabel("tile04");
		starlblTile4.setVerticalAlignment(SwingConstants.TOP);
		starlblTile4.setForeground(Color.CYAN);
		starlblTile4.setBackground(Color.DARK_GRAY);
		starlblTile4.setBounds(333, 61, 100, 100);
		starlblTile4.setBorder(border);
		contentPane.add(starlblTile4);
		
		JLabel starlblTile5 = new JLabel("tile05");
		starlblTile5.setVerticalAlignment(SwingConstants.TOP);
		starlblTile5.setForeground(Color.CYAN);
		starlblTile5.setBackground(Color.DARK_GRAY);
		starlblTile5.setBounds(35, 161, 100, 100);
		starlblTile5.setBorder(border);
		contentPane.add(starlblTile5);
		
		JLabel starlblTile6 = new JLabel("tile06");
		lblTile6.setVerticalAlignment(SwingConstants.TOP);
		lblTile6.setForeground(Color.CYAN);
		lblTile6.setBackground(Color.DARK_GRAY);
		lblTile6.setBounds(134, 161, 100, 100);
		lblTile6.setBorder(border);
		contentPane.add(lblTile6);
		
		JLabel starlblTile7 = new JLabel("tile07");
		lblTile7.setVerticalAlignment(SwingConstants.TOP);
		lblTile7.setForeground(Color.CYAN);
		lblTile7.setBackground(Color.DARK_GRAY);
		lblTile7.setBounds(234, 161, 100, 100);
		lblTile7.setBorder(border);
		contentPane.add(lblTile7);
		
		JLabel starlblTile8 = new JLabel("tile08");
		lblTile8.setVerticalAlignment(SwingConstants.TOP);
		lblTile8.setForeground(Color.CYAN);
		lblTile8.setBackground(Color.DARK_GRAY);
		lblTile8.setBounds(333, 161, 100, 100);
		lblTile8.setBorder(border);
		contentPane.add(lblTile8);
		
		JLabel starlblTile9 = new JLabel("tile09");
		lblTile9.setVerticalAlignment(SwingConstants.TOP);
		lblTile9.setForeground(Color.CYAN);
		lblTile9.setBackground(Color.DARK_GRAY);
		lblTile9.setBounds(35, 261, 100, 100);
		lblTile9.setBorder(border);
		contentPane.add(lblTile9);
		
		JLabel starlblTile10 = new JLabel("tile10");
		lblTile10.setVerticalAlignment(SwingConstants.TOP);
		lblTile10.setForeground(Color.CYAN);
		lblTile10.setBackground(Color.DARK_GRAY);
		lblTile10.setBounds(134, 261, 100, 100);
		lblTile10.setBorder(border);
		contentPane.add(lblTile10);
		
		JLabel starlblTile11 = new JLabel("tile11");
		lblTile11.setVerticalAlignment(SwingConstants.TOP);
		lblTile11.setForeground(Color.CYAN);
		lblTile11.setBackground(Color.DARK_GRAY);
		lblTile11.setBounds(234, 261, 100, 100);
		lblTile11.setBorder(border);
		contentPane.add(lblTile11);
		
		JLabel starlblTile12 = new JLabel("tile12");
		lblTile12.setVerticalAlignment(SwingConstants.TOP);
		lblTile12.setForeground(Color.CYAN);
		lblTile12.setBackground(Color.DARK_GRAY);
		lblTile12.setBounds(333, 261, 100, 100);
		lblTile12.setBorder(border);
		contentPane.add(lblTile12);
		
		JLabel starlblTile13 = new JLabel("tile13");
		lblTile13.setVerticalAlignment(SwingConstants.TOP);
		lblTile13.setForeground(Color.CYAN);
		lblTile13.setBackground(Color.DARK_GRAY);
		lblTile13.setBounds(35, 360, 100, 100);
		lblTile13.setBorder(border);
		contentPane.add(lblTile13);
		
		JLabel starlblTile14 = new JLabel("tile14");
		lblTile14.setVerticalAlignment(SwingConstants.TOP);
		lblTile14.setForeground(Color.CYAN);
		lblTile14.setBackground(Color.DARK_GRAY);
		lblTile14.setBounds(134, 360, 100, 100);
		lblTile14.setBorder(border);
		contentPane.add(lblTile14);
		
		JLabel starlblTile15 = new JLabel("tile15");
		starlblTile15.setVerticalAlignment(SwingConstants.TOP);
		starlblTile15.setForeground(Color.CYAN);
		starlblTile15.setBackground(Color.DARK_GRAY);
		starlblTile15.setBounds(234, 360, 100, 100);
		starlblTile15.setBorder(border);
		contentPane.add(lblTile15);
		
		JLabel starlblTile16 = new JLabel("tile16");
		starlblTile16.setVerticalAlignment(SwingConstants.TOP);
		starlblTile16.setForeground(Color.CYAN);
		starlblTile16.setBackground(Color.DARK_GRAY);
		starlblTile16.setBounds(333, 360, 100, 100);
		starlblTile16.setBorder(border);
		contentPane.add(starlblTile16);
		
		JLabel lblTile1 = new JLabel("tile01");
		lblTile1.setVerticalAlignment(SwingConstants.TOP);
		lblTile1.setForeground(Color.CYAN);
		lblTile1.setBackground(Color.DARK_GRAY);
		lblTile1.setBounds(35, 61, 100, 100);
		lblTile1.setBorder(border);
		contentPane.add(lblTile1);
		
		JLabel lblTile2 = new JLabel("tile02");
		lblTile2.setVerticalAlignment(SwingConstants.TOP);
		lblTile2.setForeground(Color.CYAN);
		lblTile2.setBackground(Color.DARK_GRAY);
		lblTile2.setBounds(234, 61, 100, 100);
		lblTile2.setBorder(border);
		contentPane.add(lblTile2);
		
		JLabel lblTile3 = new JLabel("tile03");
		lblTile3.setVerticalAlignment(SwingConstants.TOP);
		lblTile3.setForeground(Color.CYAN);
		lblTile3.setBackground(Color.DARK_GRAY);
		lblTile3.setBounds(134, 61, 100, 100);
		lblTile3.setBorder(border);
		contentPane.add(lblTile3);
		
		JLabel lblTile4 = new JLabel("tile04");
		lblTile4.setVerticalAlignment(SwingConstants.TOP);
		lblTile4.setForeground(Color.CYAN);
		lblTile4.setBackground(Color.DARK_GRAY);
		lblTile4.setBounds(333, 61, 100, 100);
		lblTile4.setBorder(border);
		contentPane.add(lblTile4);
		
		JLabel lblTile5 = new JLabel("tile05");
		lblTile5.setVerticalAlignment(SwingConstants.TOP);
		lblTile5.setForeground(Color.CYAN);
		lblTile5.setBackground(Color.DARK_GRAY);
		lblTile5.setBounds(35, 161, 100, 100);
		lblTile5.setBorder(border);
		contentPane.add(lblTile5);
		
		JLabel lblTile6 = new JLabel("tile06");
		lblTile6.setVerticalAlignment(SwingConstants.TOP);
		lblTile6.setForeground(Color.CYAN);
		lblTile6.setBackground(Color.DARK_GRAY);
		lblTile6.setBounds(134, 161, 100, 100);
		lblTile6.setBorder(border);
		contentPane.add(lblTile6);
		
		JLabel lblTile7 = new JLabel("tile07");
		lblTile7.setVerticalAlignment(SwingConstants.TOP);
		lblTile7.setForeground(Color.CYAN);
		lblTile7.setBackground(Color.DARK_GRAY);
		lblTile7.setBounds(234, 161, 100, 100);
		lblTile7.setBorder(border);
		contentPane.add(lblTile7);
		
		JLabel lblTile8 = new JLabel("tile08");
		lblTile8.setVerticalAlignment(SwingConstants.TOP);
		lblTile8.setForeground(Color.CYAN);
		lblTile8.setBackground(Color.DARK_GRAY);
		lblTile8.setBounds(333, 161, 100, 100);
		lblTile8.setBorder(border);
		contentPane.add(lblTile8);
		
		JLabel lblTile9 = new JLabel("tile09");
		lblTile9.setVerticalAlignment(SwingConstants.TOP);
		lblTile9.setForeground(Color.CYAN);
		lblTile9.setBackground(Color.DARK_GRAY);
		lblTile9.setBounds(35, 261, 100, 100);
		lblTile9.setBorder(border);
		contentPane.add(lblTile9);
		
		JLabel lblTile10 = new JLabel("tile10");
		lblTile10.setVerticalAlignment(SwingConstants.TOP);
		lblTile10.setForeground(Color.CYAN);
		lblTile10.setBackground(Color.DARK_GRAY);
		lblTile10.setBounds(134, 261, 100, 100);
		lblTile10.setBorder(border);
		contentPane.add(lblTile10);
		
		JLabel lblTile11 = new JLabel("tile11");
		lblTile11.setVerticalAlignment(SwingConstants.TOP);
		lblTile11.setForeground(Color.CYAN);
		lblTile11.setBackground(Color.DARK_GRAY);
		lblTile11.setBounds(234, 261, 100, 100);
		lblTile11.setBorder(border);
		contentPane.add(lblTile11);
		
		JLabel lblTile12 = new JLabel("tile12");
		lblTile12.setVerticalAlignment(SwingConstants.TOP);
		lblTile12.setForeground(Color.CYAN);
		lblTile12.setBackground(Color.DARK_GRAY);
		lblTile12.setBounds(333, 261, 100, 100);
		lblTile12.setBorder(border);
		contentPane.add(lblTile12);
		
		JLabel lblTile13 = new JLabel("tile13");
		lblTile13.setVerticalAlignment(SwingConstants.TOP);
		lblTile13.setForeground(Color.CYAN);
		lblTile13.setBackground(Color.DARK_GRAY);
		lblTile13.setBounds(35, 360, 100, 100);
		lblTile13.setBorder(border);
		contentPane.add(lblTile13);
		
		JLabel lblTile14 = new JLabel("tile14");
		lblTile14.setVerticalAlignment(SwingConstants.TOP);
		lblTile14.setForeground(Color.CYAN);
		lblTile14.setBackground(Color.DARK_GRAY);
		lblTile14.setBounds(134, 360, 100, 100);
		lblTile14.setBorder(border);
		contentPane.add(lblTile14);
		
		JLabel lblTile15 = new JLabel("tile15");
		lblTile15.setVerticalAlignment(SwingConstants.TOP);
		lblTile15.setForeground(Color.CYAN);
		lblTile15.setBackground(Color.DARK_GRAY);
		lblTile15.setBounds(234, 360, 100, 100);
		lblTile15.setBorder(border);
		contentPane.add(lblTile15);
		
		JLabel lblTile16 = new JLabel("tile16");
		lblTile16.setVerticalAlignment(SwingConstants.TOP);
		lblTile16.setForeground(Color.CYAN);
		lblTile16.setBackground(Color.DARK_GRAY);
		lblTile16.setBounds(333, 360, 100, 100);
		lblTile16.setBorder(border);
		contentPane.add(lblTile16);
		JLabel lblTile1 = new JLabel("tile01");
		lblTile1.setVerticalAlignment(SwingConstants.TOP);
		lblTile1.setForeground(Color.CYAN);
		lblTile1.setBackground(Color.DARK_GRAY);
		lblTile1.setBounds(35, 61, 100, 100);
		lblTile1.setBorder(border);
		contentPane.add(lblTile1);
		
		JLabel lblTile2 = new JLabel("tile02");
		lblTile2.setVerticalAlignment(SwingConstants.TOP);
		lblTile2.setForeground(Color.CYAN);
		lblTile2.setBackground(Color.DARK_GRAY);
		lblTile2.setBounds(234, 61, 100, 100);
		lblTile2.setBorder(border);
		contentPane.add(lblTile2);
		
		JLabel lblTile3 = new JLabel("tile03");
		lblTile3.setVerticalAlignment(SwingConstants.TOP);
		lblTile3.setForeground(Color.CYAN);
		lblTile3.setBackground(Color.DARK_GRAY);
		lblTile3.setBounds(134, 61, 100, 100);
		lblTile3.setBorder(border);
		contentPane.add(lblTile3);
		
		JLabel lblTile4 = new JLabel("tile04");
		lblTile4.setVerticalAlignment(SwingConstants.TOP);
		lblTile4.setForeground(Color.CYAN);
		lblTile4.setBackground(Color.DARK_GRAY);
		lblTile4.setBounds(333, 61, 100, 100);
		lblTile4.setBorder(border);
		contentPane.add(lblTile4);
		
		JLabel lblTile5 = new JLabel("tile05");
		lblTile5.setVerticalAlignment(SwingConstants.TOP);
		lblTile5.setForeground(Color.CYAN);
		lblTile5.setBackground(Color.DARK_GRAY);
		lblTile5.setBounds(35, 161, 100, 100);
		lblTile5.setBorder(border);
		contentPane.add(lblTile5);
		
		JLabel lblTile6 = new JLabel("tile06");
		lblTile6.setVerticalAlignment(SwingConstants.TOP);
		lblTile6.setForeground(Color.CYAN);
		lblTile6.setBackground(Color.DARK_GRAY);
		lblTile6.setBounds(134, 161, 100, 100);
		lblTile6.setBorder(border);
		contentPane.add(lblTile6);
		
		JLabel lblTile7 = new JLabel("tile07");
		lblTile7.setVerticalAlignment(SwingConstants.TOP);
		lblTile7.setForeground(Color.CYAN);
		lblTile7.setBackground(Color.DARK_GRAY);
		lblTile7.setBounds(234, 161, 100, 100);
		lblTile7.setBorder(border);
		contentPane.add(lblTile7);
		
		JLabel lblTile8 = new JLabel("tile08");
		lblTile8.setVerticalAlignment(SwingConstants.TOP);
		lblTile8.setForeground(Color.CYAN);
		lblTile8.setBackground(Color.DARK_GRAY);
		lblTile8.setBounds(333, 161, 100, 100);
		lblTile8.setBorder(border);
		contentPane.add(lblTile8);
		
		JLabel lblTile9 = new JLabel("tile09");
		lblTile9.setVerticalAlignment(SwingConstants.TOP);
		lblTile9.setForeground(Color.CYAN);
		lblTile9.setBackground(Color.DARK_GRAY);
		lblTile9.setBounds(35, 261, 100, 100);
		lblTile9.setBorder(border);
		contentPane.add(lblTile9);
		
		JLabel lblTile10 = new JLabel("tile10");
		lblTile10.setVerticalAlignment(SwingConstants.TOP);
		lblTile10.setForeground(Color.CYAN);
		lblTile10.setBackground(Color.DARK_GRAY);
		lblTile10.setBounds(134, 261, 100, 100);
		lblTile10.setBorder(border);
		contentPane.add(lblTile10);
		
		JLabel lblTile11 = new JLabel("tile11");
		lblTile11.setVerticalAlignment(SwingConstants.TOP);
		lblTile11.setForeground(Color.CYAN);
		lblTile11.setBackground(Color.DARK_GRAY);
		lblTile11.setBounds(234, 261, 100, 100);
		lblTile11.setBorder(border);
		contentPane.add(lblTile11);
		
		JLabel lblTile12 = new JLabel("tile12");
		lblTile12.setVerticalAlignment(SwingConstants.TOP);
		lblTile12.setForeground(Color.CYAN);
		lblTile12.setBackground(Color.DARK_GRAY);
		lblTile12.setBounds(333, 261, 100, 100);
		lblTile12.setBorder(border);
		contentPane.add(lblTile12);
		
		JLabel lblTile13 = new JLabel("tile13");
		lblTile13.setVerticalAlignment(SwingConstants.TOP);
		lblTile13.setForeground(Color.CYAN);
		lblTile13.setBackground(Color.DARK_GRAY);
		lblTile13.setBounds(35, 360, 100, 100);
		lblTile13.setBorder(border);
		contentPane.add(lblTile13);
		
		JLabel lblTile14 = new JLabel("tile14");
		lblTile14.setVerticalAlignment(SwingConstants.TOP);
		lblTile14.setForeground(Color.CYAN);
		lblTile14.setBackground(Color.DARK_GRAY);
		lblTile14.setBounds(134, 360, 100, 100);
		lblTile14.setBorder(border);
		contentPane.add(lblTile14);
		
		JLabel lblTile15 = new JLabel("tile15");
		lblTile15.setVerticalAlignment(SwingConstants.TOP);
		lblTile15.setForeground(Color.CYAN);
		lblTile15.setBackground(Color.DARK_GRAY);
		lblTile15.setBounds(234, 360, 100, 100);
		lblTile15.setBorder(border);
		contentPane.add(lblTile15);
		
		JLabel lblTile16 = new JLabel("tile16");
		lblTile16.setVerticalAlignment(SwingConstants.TOP);
		lblTile16.setForeground(Color.CYAN);
		lblTile16.setBackground(Color.DARK_GRAY);
		lblTile16.setBounds(333, 360, 100, 100);
		lblTile16.setBorder(border);
		contentPane.add(lblTile16);
		
		listOfTiles.addAll(Arrays.asList(lblTile1, lblTile2, lblTile3, lblTile4, lblTile5, lblTile6, lblTile7, lblTile8, lblTile9, lblTile10, lblTile11, lblTile12, lblTile13, lblTile14, lblTile15, lblTile16));
		
		JButton btnReset = new JButton("Reset");
		
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetTiles(listOfTiles, bl1);
				
				System.out.println("reset clicked");
				
				int spawnMaster = bl1.masterShipSpawn();
				drawMasterShip(spawnMaster, listOfTiles);
				int spawnEnemy = bl1.enemyShipSpawn();
				int enemyType = bl1.getEnemyType();
				drawSpawnedEnemies(spawnEnemy, listOfTiles, enemyType);
			}
		});
		
		btnReset.setBounds(586, 506, 89, 23);
		contentPane.add(btnReset);
		
		JButton btnMove = new JButton("Move");
		btnMove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetTiles(listOfTiles);
				
				int move = bl1.moveMasterShip();
				drawMasterShip(move, listOfTiles);
				
				bl1.moveEnemyShips();
				drawMovedEnemies(bl1, listOfTiles);
				
				int spawnEnemy = bl1.enemyShipSpawn();
				if (spawnEnemy != 0) {
					int enemyType = bl1.getEnemyType();
					drawSpawnedEnemies(spawnEnemy, listOfTiles, enemyType);
				}
			}
		});
		btnMove.setBounds(685, 506, 89, 23);
		contentPane.add(btnMove);
		
		JLabel lblBackGround = new JLabel("");
		lblBackGround.setIcon(new ImageIcon(GUI.class.getResource(SKY_BACKGROUND)));
		lblBackGround.setBounds(0, 0, 784, 540);
		contentPane.add(lblBackGround);
	} // end GUI()
	
	// reset tiles method
	public void resetTiles(ArrayList<JLabel> listOfTiles, ButtonsLogic bl1) {
		bl1.setMasterShipTile(null);
		bl1.setBattleStarTile(null);
		bl1.setBattleCruiserTile(null);
		bl1.setBattleShooterTile(null);
		
		for (JLabel label : listOfTiles) {
			label.setIcon(null);
		}
	} // end reset tiles 
	
	// clear tiles method
		public void resetTiles(ArrayList<JLabel> listOfTiles) {			
			for (JLabel label : listOfTiles) {
				label.setIcon(null);
			}
		} // end clear tiles 
	
	// method for drawing master ship
	public void drawMasterShip(int tileNumber, ArrayList<JLabel> listOfTiles) {		
		for (int i = 0; i < NUMBER_OF_TILES; i++) {
			if (tileNumber-1 == i) {
				listOfTiles.get(i).setIcon(new ImageIcon(GUI.class.getResource(MASTER_SHIP_DEFENSIVE)));
			}
		}
	} // end drawMasterShop()
	
	// method for drawing enemy ships
	public void drawSpawnedEnemies(int tileNumber, ArrayList<JLabel> listOfTiles, int enemyType) {
		for (int i = 0; i < NUMBER_OF_TILES; i++) {
			if (tileNumber-1 == i) {
				switch (enemyType) {
					case 1: listOfTiles.get(i).setIcon(new ImageIcon(GUI.class.getResource(BATTLE_STAR))); break;
					case 2: listOfTiles.get(i).setIcon(new ImageIcon(GUI.class.getResource(BATTLE_CRUISER))); break;
					case 3: listOfTiles.get(i).setIcon(new ImageIcon(GUI.class.getResource(BATTLE_SHOOTER))); break;
				}
				
			}
		}
	} // end drawSpawnedEnemies()
	
	public void drawMovedEnemies(ButtonsLogic bl1, ArrayList<JLabel> listOfTiles) {
		if (bl1.getBattleStarTile() != null) {
			for (int i = 0; i < NUMBER_OF_TILES; i++) {
				if (bl1.getBattleStarTile().getTileId()-1 == i) {
					listOfTiles.get(i).setIcon(new ImageIcon(GUI.class.getResource(BATTLE_STAR)));
					}					
				}
		}
		if (bl1.getBattleCruiserTile() != null) {
			for (int i = 0; i < NUMBER_OF_TILES; i++) {
				if (bl1.getBattleCruiserTile().getTileId()-1 == i) {
					listOfTiles.get(i).setIcon(new ImageIcon(GUI.class.getResource(BATTLE_CRUISER)));
					}					
				}
		}
		if (bl1.getBattleShooterTile() != null) {
			for (int i = 0; i < NUMBER_OF_TILES; i++) {
				if (bl1.getBattleShooterTile().getTileId()-1 == i) {
					listOfTiles.get(i).setIcon(new ImageIcon(GUI.class.getResource(BATTLE_SHOOTER)));
					}					
				}
		}
	}
} //  end GUI
