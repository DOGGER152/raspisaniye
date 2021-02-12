package raspisaniye.rasp;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.parser.ParseException;

import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTree;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import raspisaniye.rasp.raspisaniye.email;
import raspisaniye.rasp.raspisaniye.vk;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.CardLayout;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.Window.Type;
import java.awt.Font;
import java.awt.Color;

public class launcher extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					launcher frame = new launcher(); 
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace(); 
				} 
			}
		}); 
	}

	public launcher() throws IOException { 
		setLocationRelativeTo(null); 
		setAutoRequestFocus(false);
		setResizable(false);
		setTitle("Расписание");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setBounds(100, 100, 390, 276);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);  
		
		JButton btnNewButton = new JButton("Запуск");
		btnNewButton.setBounds(12, 128, 360, 100);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				raspisaniye main = new raspisaniye(); 
				try {
					raspisaniye.main(null);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace(); 
				}  
			}

		});
		contentPane.add(btnNewButton);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 394, 32);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Меню");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("О программе");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
		     JOptionPane.showMessageDialog(null, "К сожалению, еще не дошли руки написать об этом", "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("Обратная связь");
		mnNewMenu.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Страница ВК");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				 vk vk = new vk(); 
				  try {
					vk.vk();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				} 
				  
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("E-mail");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				 email email = new email(); 
				 try {
					email.email();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JButton button = new JButton("Нашли баг?");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				JOptionPane.showMessageDialog(null, "Пожалуйста, воспользуйтесь обратной связью! Меню -> Oбратная связь", "Нашли баг?",JOptionPane.INFORMATION_MESSAGE);
			} 
		}); 

		
		menuBar.add(button);
		
		JLabel label = new JLabel("Расписание ИК-022");
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.PLAIN, 40));
		label.setBounds(12, 56, 360, 37);
		contentPane.add(label);
		
		JLabel lblVBy = new JLabel("v 1.0 by Alex F");
		lblVBy.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblVBy.setBounds(248, 95, 124, 32);
		contentPane.add(lblVBy);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});}	
}
