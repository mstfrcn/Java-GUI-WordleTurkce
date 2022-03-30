package wordleTurkce;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class formApp extends JFrame {

	private JTextField[][] txtKutucuklar = new JTextField[6][5];
	public String kelime = "";
	public String secilenKelime = Kelimeler.kelimeUret().toUpperCase();
	public int sayac = 0;
	public int kontrol = 0;
	public int sayac2 = 0;
	public int a = 0;
	public int b = 0;
	public HashSet<String> olanHarfler = new HashSet<>();
	public HashSet<String> olmayanHarfler = new HashSet<>();
	public HashSet<String> kullanilmayanHarfler = new HashSet<>(Arrays.asList("A", "B", "C", "Ç", "D", "E", "F", "G",
			"H", "I", "Ý", "J", "K", "L", "M", "N", "O", "Ö", "P", "R", "S", "T", "U", "Ü", "V", "Y", "Z"));

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					formApp frame = new formApp();
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
	public formApp() {
		getContentPane().setBackground(Color.PINK);
		setTitle("KELIME BULMACA");
		setForeground(Color.PINK);
		getContentPane().setForeground(Color.PINK);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 856);

		JButton btnEnter = new JButton("Sorgula");
		btnEnter.setBackground(Color.BLACK);
		btnEnter.setForeground(Color.WHITE);
		btnEnter.setBounds(181, 455, 102, 31);
		getContentPane().add(btnEnter);

		JLabel lblUyari = new JLabel("");
		lblUyari.setForeground(Color.RED);
		lblUyari.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUyari.setHorizontalAlignment(SwingConstants.CENTER);
		lblUyari.setBounds(20, 36, 398, 31);
		getContentPane().add(lblUyari);

		JButton btnNewButton = new JButton("Yeni Oyun");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setBounds(181, 496, 102, 31);
		getContentPane().add(btnNewButton);

		JLabel lblOlanHarf = new JLabel("");
		lblOlanHarf.setHorizontalAlignment(SwingConstants.CENTER);
		lblOlanHarf.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblOlanHarf.setBounds(20, 591, 398, 31);
		getContentPane().add(lblOlanHarf);

		JLabel lblBaþlýk = new JLabel("Kelimede \u00C7\u0131kan Harfler");
		lblBaþlýk.setHorizontalAlignment(SwingConstants.CENTER);
		lblBaþlýk.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblBaþlýk.setBounds(50, 550, 355, 31);
		getContentPane().add(lblBaþlýk);

		JLabel lblKelimedeOlmayanHarfler = new JLabel("Kelimede \u00C7\u0131kmayan Harfler");
		lblKelimedeOlmayanHarfler.setHorizontalAlignment(SwingConstants.CENTER);
		lblKelimedeOlmayanHarfler.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblKelimedeOlmayanHarfler.setBounds(43, 633, 375, 31);
		getContentPane().add(lblKelimedeOlmayanHarfler);

		JLabel lblOlmayanHarf = new JLabel("");
		lblOlmayanHarf.setHorizontalAlignment(SwingConstants.CENTER);
		lblOlmayanHarf.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblOlmayanHarf.setBounds(20, 668, 398, 31);
		getContentPane().add(lblOlmayanHarf);

		JLabel lblKullanlmamHarfler = new JLabel("Hen\u00FCz Kullan\u0131lmam\u0131\u015F Harfler");
		lblKullanlmamHarfler.setHorizontalAlignment(SwingConstants.CENTER);
		lblKullanlmamHarfler.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblKullanlmamHarfler.setBounds(30, 710, 400, 31);
		getContentPane().add(lblKullanlmamHarfler);

		JLabel lblKullanýlmayanHarfler = new JLabel("");
		lblKullanýlmayanHarfler.setVerticalAlignment(SwingConstants.BOTTOM);
		lblKullanýlmayanHarfler.setHorizontalAlignment(SwingConstants.CENTER);
		lblKullanýlmayanHarfler.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblKullanýlmayanHarfler.setBounds(0, 752, 461, 31);
		getContentPane().add(lblKullanýlmayanHarfler);

		// TextField Olusturma
		int x = 0;
		int y = 0;
		for (int i = 0; i < 5; i++) {
			y = 0;
			for (int j = 0; j < 6; j++) {

				txtKutucuklar[j][i] = new JTextField(1);
				txtKutucuklar[j][i].setBounds(90 + x, 90 + y, 40, 40);
				getContentPane().add(txtKutucuklar[j][i]);
				txtKutucuklar[j][i].setColumns(10);
				txtKutucuklar[j][i].setEnabled(false);
				txtKutucuklar[j][i].setHorizontalAlignment(SwingConstants.CENTER);
				txtKutucuklar[j][i].setFont(new Font("Tahoma", Font.BOLD, 15));
				txtKutucuklar[j][i].setBackground(Color.GRAY);
				txtKutucuklar[0][i].setEnabled(true);
				// GELEN HARFLERI BUYUK ALMA
				txtKutucuklar[j][i].addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						for (int i = 0; i < 5; i++) {
							for (int j = 0; j < 6; j++) {
								txtKutucuklar[j][i].setText(txtKutucuklar[j][i].getText().toUpperCase());
								if (txtKutucuklar[j][i].getText().length() >= 1)
								{
									txtKutucuklar[j][i].setText(txtKutucuklar[j][i].getText().substring(0,1));
								}
							}
						}
					}
				});
				y += 60;
			}
			x += 60;
		}

		// EVENT
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(secilenKelime);
				for (int i = 0; i < 5; i++) {
					kelime += txtKutucuklar[sayac][i].getText();
				}
				// KELIME KELIME LISTESININ ICINDEMI
				for (String k : Kelimeler.kelimeler) {
					if (k.equals(kelime.toLowerCase()))
						kontrol = 1;
				}
				if (kontrol == 1) {

					// HARFIN KELIMEDE OLDUGUNU KONTROL ETME VE SARIYA BOYAMA

					for (int i = 0; i < 5; i++) {
						for (int j = 0; j < 5; j++) {
							txtKutucuklar[sayac][j].setEnabled(false);
							if (sayac < 5)
								txtKutucuklar[sayac + 1][j].setEnabled(true);
							if (kelime.charAt(i) == secilenKelime.charAt(j)) {
								sayac2++;
								txtKutucuklar[sayac][i].setBackground(Color.YELLOW);
								olanHarfler.add(Character.toString(kelime.charAt(i)));

							}
							// HARFIN KELIMEDE DOGRU YERDE OLDUGUNU KONTROL ETME VE LACIVERTE BOYAMA
							if (txtKutucuklar[sayac][i].getText().charAt(0) == secilenKelime.charAt(i)) {
								txtKutucuklar[sayac][i].setBackground(Color.BLUE);
							}

						}

						if (sayac2 == 0) {
							olmayanHarfler.add(Character.toString(kelime.charAt(i)));
						}
						sayac2 = 0;
					}
					lblOlmayanHarf.setText("" + olmayanHarfler);
					lblOlanHarf.setText("" + olanHarfler);
					kullanilmayanHarfler.removeAll(olmayanHarfler);
					kullanilmayanHarfler.removeAll(olanHarfler);
					lblKullanýlmayanHarfler.setText("" + kullanilmayanHarfler);
					sayac++;
					kontrol = 0;
					lblUyari.setText("");
				} else {
					lblUyari.setText("LUTFEN GECERLI BIR KELIME GIRINIZ!");
					for (int j = 0; j < 5; j++) {
						txtKutucuklar[sayac][j].setText("");
					}
				}

				if (sayac == 6) {
					getContentPane().setBackground(Color.RED);
					lblUyari.setForeground(Color.BLACK);
					lblUyari.setText("KAYBETTINIZ!");

				}

				if (kelime.equals(secilenKelime)) {
					lblUyari.setForeground(Color.BLACK);
					lblUyari.setText("TEBRIKLER KAZANDINIZ");
					getContentPane().setBackground(Color.GREEN);
					for (int i = 0; i < 6; i++) {
						for (int j = 0; j < 5; j++) {
							txtKutucuklar[i][j].setEnabled(false);
						}
					}
				}

				kelime = "";

			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				secilenKelime = Kelimeler.kelimeUret().toUpperCase();
				lblUyari.setText("");
				kelime = "";
				sayac = 0;
				olanHarfler.clear();
				olmayanHarfler.clear();
				kullanilmayanHarfler.addAll(Arrays.asList("A", "B", "C", "Ç", "D", "E", "F", "G", "H", "I", "Ý", "J",
						"K", "L", "M", "N", "O", "Ö", "P", "R", "S", "T", "U", "Ü", "V", "Y", "Z"));
				lblOlanHarf.setText("");
				lblOlmayanHarf.setText("");
				lblKullanýlmayanHarfler.setText("");
				getContentPane().setBackground(Color.PINK);
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 6; j++) {

						txtKutucuklar[j][i].setEnabled(false);
						txtKutucuklar[j][i].setBackground(Color.GRAY);
						txtKutucuklar[j][i].setText("");
						txtKutucuklar[0][i].setEnabled(true);

					}
				}

			}
		});

	}
}
