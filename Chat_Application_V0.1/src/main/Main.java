package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;
import java.awt.event.*;
public class Main {
	// EASTER EGG
	public static List<Integer> usedIndex = new ArrayList<>();
	public static boolean questionShowing = false;
	public static boolean answeredCorrectly = false;
	public static int score;
	public static int currentQuestionIndex = -1;
	public static int randomIndex;
	public static Random random = new Random();
	public static int numberofQuestions = 0;
	public static JFrame jf;
	public static JTextField answer;
	public static JButton submit;
	public static JLabel question;
	public static JLabel guess;
	public static JLabel scoreLabel;
	public static JLabel numberofQuestion;
	public static JLabel wrong;
	public static boolean mouseHolding = false;
	public static boolean game = false;
	public static JButton close;

	public static String[] countries = { "Turkey", "France", "Japan", "Brazil", "Russia", "Africa", "India",
			"Australia", "Mexico", "Canada", "Germany", "Egypt", "argentina"

	};
	public static String[] Country2 = { "turkey", // 1
			"france", "japan", "brazil", "russia", "africa", "india", "australia", "mexico", "canada", "germany",
			"egypt", "argentina",// 13
	};
	public static String[] countryInfo = { "Its capital is Ankara, associated with the Ottoman Empire.",
			"Its currency is the Euro, known as a frequently visited country with a rich cultural and fashion scene.",
			"It is a hub for technology, and its currency is the Yen.",
			"Recognized as a football nation, famous for its tropical climate and vibrant live music.",
			"It is a captivating country with rich history and culture, with Moscow as its capital.",
			"Famous for its natural beauty and safari experiences, recognized for its mines.",
			"Known for its spices, colorful festivals, and multiple religions.",
			"Renowned for its unique wildlife and natural beauty, identified by its kangaroos.",
			"Known for its cartel, with the famous dish being tacos.",
			"Known for its plane tree, deer, and glacier lakes, and its currency is the dollar.",
			"Recognized as the center of Europe, a country where many cars are produced.",
			"Known for its pyramids and rich historical heritage.",
			"The country of the footballer known by the nickname 'The Hand of God'." };

	//////////////////////////////////////////////////////////////////////////////////////////
	public static String adminUsername;
	public static final int PORT = 9999;
	public static Socket socket;
	public static DataInputStream inputstream;
	public static DataOutputStream outputstream;
	public static DataInputStream inputstream2;
	public static DataInputStream inputstream3;

	public static String receivedData;
	public static String receivedData2;
	public static String receivedData3;

	public static JButton saveButton;
	public static JTextField usernameLogin;
	public static JButton settingsButton;
	public static boolean holding = false;
	// LOGO
	public static String iconPath = "res/Image/ius_logo.png";
	public static ImageIcon icon = new ImageIcon(iconPath);
	// LANGUAGE LOGİN
	public static String username;
	public static String username_classLogin_classRegister = "Username :";
	public static String password_classLogin_classRegister = "Password :";
	public static String setTitle_classLogin = "Login";
	public static String settingsButton_classLogin = "Settings";
	public static String loginButton_classLogin = "Login";
	public static String registerButton_classLogin = "Register";
	public static String usernamepasswordEmpty_classLogin_classRegister = "Username cannot be empty. Try again";
	public static String usernameorpasswordWrong_classLogin = "Username or password wrong!";

	// LANGUAGE REGİSTER
	public static String setTitle_classRegister = "Register";
	public static String registerButton_usernametaken_classRegister = "The name that you submit is already taken";

	// LANGUAGE settings
	public static String changepasswordButton_classSettings = "Change Password";
	public static String setTitle_classSettings = "Settings";
	public static String closeButton_classSettings = "Close";
	public static String language_classSettings = "Language";
	public static String changingpasswordFailed_classSettings = "password change failed";
	public static String changingpasswordSuccessfull_classSettings = "Password change successfull";
	public static String usernameSettingsorpasswordSettingsorpasswordSettings2isempty_classSettings = "Username and passwords can not be empty";
	public static String username_classSettings = "        Username :";
	public static String password_classSettings = "         Password :";
	public static String newpassword_classSettings = "New password :";

	// LANGUAGE NOTES
	public static String setTitle_classNotes = "Notes /";
	public static String saveandcloseButton_classNotes = "Save/Close";
	public static String notesaved_classNotes = "Your note has been saved successfully";

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Login().setVisible(true);
			}
		});
	}

	public static void serverOperations(String data, int operationIndex) {
		try { // 194.105.5.40
			socket = new Socket("194.105.5.40", 6472);

			outputstream = new DataOutputStream(socket.getOutputStream());
			inputstream = new DataInputStream(socket.getInputStream());
			inputstream2 = new DataInputStream(socket.getInputStream());
			inputstream3 = new DataInputStream(socket.getInputStream());

			if (operationIndex == 1)// LOGİN
			{
				outputstream.writeUTF("1" + ":" + data);
				receivedData = inputstream.readUTF();
				System.out.println(receivedData);
				if (Main.receivedData.equals("1")) {
					receivedData2 = inputstream2.readUTF();

				}
				if (Main.receivedData.equals("3")) {
					System.out.println("333");
				}

			}
			if (operationIndex == 2) // REGİSTER
			{

				outputstream.writeUTF("2" + ":" + data);

				receivedData = inputstream.readUTF();
				System.out.println(receivedData);

			}
			if (operationIndex == 3)// SENDİNG DATA
			{
				outputstream.writeUTF("3" + ":" + data);

				receivedData = inputstream.readUTF();

			}
			if (operationIndex == 4) // RECEİVİNG DATA
			{
				outputstream.writeUTF("4" + ":" + data);

				receivedData = inputstream.readUTF();

			}
			if (operationIndex == 5) {
				outputstream.writeUTF("5" + ":" + Main.adminUsername + ":" + data);
				System.out.println("data 22: " + data);

			}
			// Perform operations based on the provided index

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void play_Sound(int index) {
		AudioInputStream audioInputStream = null;
		try {
			if (index == 1) {
				audioInputStream = AudioSystem.getAudioInputStream(new File("res/Sounds/click.wav"));
			}
			if (index == 2) {

				audioInputStream = AudioSystem.getAudioInputStream(new File("res/Sounds/wrong2.wav"));

			}
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(0.2f);

			clip.start();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	ImageIcon imageIcon = new ImageIcon("path/to/your/image.jpg");

}

class ADMIN extends JFrame {
	private JTextField userInputField;
	private JTextArea chatArea;
	private Socket socket;
	private DataOutputStream outputstream;
	private DataInputStream inputstream;

	public ADMIN() {
		setTitle("ADMIN -->" + Main.adminUsername);
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		userInputField = new JTextField();
		add(userInputField, BorderLayout.SOUTH);

		chatArea = new JTextArea();
		chatArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(chatArea);
		add(scrollPane, BorderLayout.CENTER);
		JButton backButton = new JButton("Back");
		JButton sendButton = new JButton("Send");

	
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = userInputField.getText().trim();
				if (message == null) {
					System.out.println("null client");
				} else {
					try {
						// Daha önceden oluşturulmuş bir soketi kullanarak mesaj gönder
						outputstream.writeUTF("5" + ":" + Main.adminUsername + ":" + message);
						userInputField.setText(""); // Kullanıcı girdi alanını temizle
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
		        int option = JOptionPane.showConfirmDialog(ADMIN.this, Main.adminUsername+" Uygulamayı kapatmak istiyor musunuz?", "Uyarı", JOptionPane.YES_NO_OPTION);
		        if (option == JOptionPane.YES_OPTION) {
		            dispose();
					new Login();
					try {
			            outputstream.writeUTF("5"+":"+Main.adminUsername+":"+"disconnected");

					} catch (Exception e2) {
						// TODO: handle exception
					}
		        }
				

			}
		});
		add(backButton, BorderLayout.WEST);
		add(sendButton, BorderLayout.EAST);
		userInputField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					String message = userInputField.getText().trim();
					if (message == null) {
						System.out.println("null client");
					} else {
						try {
							// Daha önceden oluşturulmuş bir soketi kullanarak mesaj gönder
							outputstream.writeUTF("5" + ":" + Main.adminUsername + ":" + message);
							userInputField.setText(""); // Kullanıcı girdi alanını temizle
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}

				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		try {
			Socket socket = new Socket("194.105.5.40", 6472);

			outputstream = new DataOutputStream(socket.getOutputStream());
			inputstream = new DataInputStream(socket.getInputStream());
			new Thread(new IncomingMessageHandler(inputstream)).start();
			int i = 0;
			String message = Main.adminUsername + " Connected.";
			while (i < 2) {
				outputstream.writeUTF("5" + ":" + Main.adminUsername + ":" + message);
				i++;
			
			
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		chatArea.setLineWrap(true);
		chatArea.setRows(37);
		chatArea.setEditable(false);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	public class IncomingMessageHandler implements Runnable {
		private DataInputStream input;

		public IncomingMessageHandler(DataInputStream input) {
			this.input = input;
		}

		public void run() {
			try {
				while (true) {
					String message = input.readUTF();
					System.out.println("message :" + message);
					if (message != null) {
						System.out.println(message);
						chatArea.append(message + "\n");
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}

class Easteregg extends JFrame {
	public JTextField answer;
	public JButton submit;
	public JLabel guess;
	public JLabel scoreLabel;
	public JLabel numberofQuestion;
	public JTextArea question;
	public int score;
	public JButton close;
	public ImageIcon imageIcon;

	public Easteregg() {
		setTitle("Easteregg");
		setSize(500, 300);
		JPanel panel = new JPanel();
		answer = new JTextField(20);
		submit = new JButton("Submit");
		question = new JTextArea(5, 40);
		guess = new JLabel();
		scoreLabel = new JLabel();
		numberofQuestion = new JLabel();
		scoreLabel.setText("Score: " + score);
		close = new JButton("Close");
		imageIcon = new ImageIcon("res/Images/ius_logo.png");
		JLabel logoLabel = new JLabel(imageIcon);
		setIconImage(imageIcon.getImage());
		panel.add(question);
		panel.add(scoreLabel);
		panel.add(answer);
		panel.add(submit);
		panel.add(close);

		add(panel);
		setResizable(true);
		setLocationRelativeTo(null);
		setVisible(true);
		question.setEditable(false);
		question.setLineWrap(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Main.play_Sound(1);
				dispose();
				new Login();

			}
		});
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.play_Sound(1);

				if (answer.getText().isEmpty()) {
					guess.setText("You did not guess something. Guess it!");
					JOptionPane.showMessageDialog(Easteregg.this, "TYPE SOMETHİNG");
				} else {
					if (answer.getText().equalsIgnoreCase(Main.countries[Main.randomIndex])) {
						Main.usedIndex.add(Main.randomIndex);
						score++;
						scoreLabel.setText("Score: " + score);
						Main.answeredCorrectly = true;
					} else {
						JOptionPane.showMessageDialog(Easteregg.this, "Wrong answer!");
					}
					Main.questionShowing = false;
					showQuestion();
				}
			}
		});

		showQuestion();
	}

	public void showQuestion() {
		Main.answeredCorrectly = false;
		Main.questionShowing = true;

		if (Main.usedIndex.size() == Main.countryInfo.length) {
			JOptionPane.showMessageDialog(Easteregg.this, "Game Over.You answered all quesitons correctly");
			dispose();
			new Login();
		} else {
			do {
				Main.randomIndex = Main.random.nextInt(Main.countries.length);
			} while (Main.usedIndex.contains(Main.randomIndex));

			Main.numberofQuestions++;
			question.setText(Main.countryInfo[Main.randomIndex]);
			numberofQuestion.setText("Question: " + Main.numberofQuestions);
			guess.setText("Guess the country!");
		}
		answer.setEnabled(true);
		submit.setEnabled(true);
	}
}

class Notes extends JFrame {
	public JTextField usernameNotes;
	public JTextField passwordNotes;
	public JTextArea Note;
	public JButton saveButton;
	public ImageIcon imageIcon;

	public Notes() {
		setTitle(Main.setTitle_classNotes + Main.username);
		setSize(900, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel1 = new JPanel();
		Note = new JTextArea(37, 80);
		imageIcon = new ImageIcon("res/Images/ius_logo.png");
		JLabel logoLabel = new JLabel(imageIcon);
		setIconImage(imageIcon.getImage());
		JScrollPane scrollPane = new JScrollPane(Note);/// JTextArea ile JScrollpane i sarmaladık

		saveButton = new JButton(Main.saveandcloseButton_classNotes);

		Note.setLineWrap(true);
		Note.setRows(37);
		Note.setEditable(true);
		Note.setText(Main.receivedData2);

		add(panel1);
		setIconImage(imageIcon.getImage());

		panel1.add(scrollPane);
		panel1.add(saveButton);

		saveButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Main.play_Sound(1);

				String text = Note.getText();
				System.out.println(text);

				if (text.isEmpty()) {
					System.out.println("text is not empty");
					Main.serverOperations(Main.username + ":" + "EMPTY", 3);
					System.out.println("empty");
					JOptionPane.showMessageDialog(Notes.this, Main.notesaved_classNotes);
					dispose();
					new Login();
				}
				if (!text.isEmpty()) {
					System.out.println("text is  empty");

					Main.serverOperations(Main.username + ":" + text, 3);

					System.out.println("receivedData2: " + Main.receivedData2);

					if (Main.receivedData.equals("1")) {

						JOptionPane.showMessageDialog(Notes.this, Main.notesaved_classNotes);
						dispose();
						new Login();
					}

				}
			}
		});
		Note.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					String text = Note.getText();
					System.out.println(text);

					if (text.isEmpty()) {
						Main.serverOperations(Main.username + ":" + "EMPTY", 3);
						System.out.println("empty");
						JOptionPane.showMessageDialog(Notes.this, "Your note has been saved successfully");
						dispose();
						new Login();
					} else {
						Main.serverOperations(Main.username + ":" + text, 3);

						System.out.println("receivedData2: " + Main.receivedData2);

						if (Main.receivedData.equals("1")) {

							System.out.println("succesfuly has been saved ");
							JOptionPane.showMessageDialog(Notes.this, "Your note has been saved successfully");
							dispose();
							new Login();
						}

					}

				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

}

class Login extends JFrame {

	public JTextField usernameLogin;
	public JTextField passwordLogin;
	public ImageIcon imageIcon;

	public Login() {
		setTitle(Main.setTitle_classLogin);
		setSize(300, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panelLogin = new JPanel();
		usernameLogin = new JTextField(20);
		passwordLogin = new JPasswordField(20);
		JButton settingsButton = new JButton(Main.settingsButton_classLogin);
		JButton loginButton = new JButton(Main.loginButton_classLogin);
		JButton registerButton = new JButton(Main.registerButton_classLogin);
		imageIcon = new ImageIcon("res/Images/ius_logo.png");
		JLabel logoLabel = new JLabel(imageIcon);
		loginButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Main.play_Sound(1);

				String Username_password1 = usernameLogin.getText() + ":" + passwordLogin.getText();

				if (usernameLogin.getText().isEmpty() || passwordLogin.getText().isEmpty()) {
					JOptionPane.showMessageDialog(Login.this, Main.usernamepasswordEmpty_classLogin_classRegister);
					dispose();
					new Login();

				} else {
					Main.serverOperations(Username_password1, 1);
					if (Main.receivedData.equals("3")) {
						Main.adminUsername = usernameLogin.getText();
						System.out.println("33333");
						dispose();
						new ADMIN();
					}
					if (Main.receivedData.equals("0")) {
						Main.play_Sound(2);

						JOptionPane.showMessageDialog(Login.this, "Username or Password wrong!");

					} else if (Main.receivedData.equals("1")) {

						System.out.println("receivedData2: " + Main.receivedData2);

						System.out.println("LOGİN BAŞARILI");
						Main.username = usernameLogin.getText();
						dispose();
						new Notes();
						// yeni JFRAME

					}

				}

			}
		});
		passwordLogin.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Main.play_Sound(1);

					String Username_password1 = usernameLogin.getText() + ":" + passwordLogin.getText();

					if (usernameLogin.getText().isEmpty() || passwordLogin.getText().isEmpty()) {
						JOptionPane.showMessageDialog(Login.this, Main.usernamepasswordEmpty_classLogin_classRegister);
						dispose();
						new Login();

					} else {
						Main.serverOperations(Username_password1, 1);
						if (Main.receivedData.equals("3")) {
							Main.adminUsername = usernameLogin.getText();
							System.out.println("33333");
							dispose();
							new ADMIN();
						}
						if (Main.receivedData.equals("0")) {
							Main.play_Sound(2);

							JOptionPane.showMessageDialog(Login.this, "Username or Password wrong!");

						} else if (Main.receivedData.equals("1")) {

							System.out.println("receivedData2: " + Main.receivedData2);

							System.out.println("LOGİN BAŞARILI");
							Main.username = usernameLogin.getText();
							dispose();
							new Notes();
							// yeni JFRAME

						}

					}

				}

			}

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		registerButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Main.play_Sound(1);

				dispose();
				new Register();
			}
		});
		settingsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Main.play_Sound(1);

				String DIGITAL_NOTEBOOK = "DIGITAL_NOTEBOOK";
				if (usernameLogin.getText().equals(DIGITAL_NOTEBOOK))

				{

					dispose();
					new Easteregg();

				} else {
					dispose();
					new Settings();

				}

			}
		});
		setIconImage(imageIcon.getImage());

		panelLogin.add(new JLabel(Main.username_classLogin_classRegister));
		panelLogin.add(usernameLogin);
		panelLogin.add(new JLabel(Main.password_classLogin_classRegister));
		panelLogin.add(passwordLogin);
		panelLogin.add(settingsButton);
		panelLogin.add(loginButton);
		panelLogin.add(registerButton);

		add(panelLogin);
		setResizable(false);

		setLocationRelativeTo(null);
		setVisible(true);
	}
}

class Register extends JFrame {
	public JTextField usernameRegister;
	public JTextField passwordRegister;
	public ImageIcon imageIcon;

	public Register() {
		setTitle(Main.setTitle_classRegister);
		setSize(300, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panelRegister = new JPanel();
		usernameRegister = new JTextField(20);
		passwordRegister = new JPasswordField(20);
		JButton registerButton = new JButton(Main.setTitle_classRegister);
		imageIcon = new ImageIcon("res/Images/ius_logo.png");
		JLabel logoLabel = new JLabel(imageIcon);
		registerButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Main.play_Sound(1);

				if (usernameRegister.getText().isEmpty() || passwordRegister.getText().isEmpty()) {
					JOptionPane.showMessageDialog(Register.this, Main.usernamepasswordEmpty_classLogin_classRegister);
					dispose();
					new Login();

				} else {

					String Username_password = usernameRegister.getText() + ":" + passwordRegister.getText();
					Main.serverOperations(Username_password, 2);
					System.out.println("1:" + Main.receivedData);
					if (Main.receivedData.equals("0"))// the name that you submit is already taken
					{
						System.out.println(Main.receivedData);
						JOptionPane.showMessageDialog(Register.this, Main.registerButton_usernametaken_classRegister);
						dispose();
						new Login();

					}
					if (Main.receivedData.equals("1")) {

						dispose();
						new Login();

					}

				}
			}
		});
		passwordRegister.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			public void keyReleased(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					if (usernameRegister.getText().isEmpty()) {
						JOptionPane.showMessageDialog(Register.this,
								Main.usernamepasswordEmpty_classLogin_classRegister);
						dispose();
						new Login();

					} else {

						String Username_password = usernameRegister.getText() + ":" + passwordRegister.getText();
						Main.serverOperations(Username_password, 2);
						System.out.println("1:" + Main.receivedData);
						if (Main.receivedData.equals("0"))// the name that you submit is already taken
						{
							System.out.println(Main.receivedData);
							JOptionPane.showMessageDialog(Register.this,
									Main.registerButton_usernametaken_classRegister);
							dispose();
							new Login();

						}
						if (Main.receivedData.equals("1")) {
							dispose();
							new Login();

						}

					}

				}
			}

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		usernameRegister.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			public void keyReleased(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

					dispose();
					new Login();

				}

			}

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		passwordRegister.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			public void keyReleased(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

					dispose();
					new Login();

				}

			}

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		setIconImage(imageIcon.getImage());

		panelRegister.add(new JLabel(Main.username_classLogin_classRegister));
		panelRegister.add(usernameRegister);
		panelRegister.add(new JLabel(Main.password_classLogin_classRegister));
		panelRegister.add(passwordRegister);
		panelRegister.add(registerButton);

		add(panelRegister);
		setResizable(false);

		setLocationRelativeTo(null);
		setVisible(true);
	}

}

class Settings extends JFrame {
	public JTextField usernameSettings;
	public JTextField passwordSettings;
	public JTextField passwordSettings2;
	public ImageIcon imageIcon;

	public Settings() {

		setTitle(Main.setTitle_classSettings);
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		usernameSettings = new JTextField(16);
		passwordSettings = new JPasswordField(16);
		passwordSettings2 = new JPasswordField(16);
		JPanel settings = new JPanel();
		JButton english_languageButton = new JButton("English");
		JButton turkish_languageButton = new JButton("Turkish");
		JButton change_passwordButton = new JButton(Main.changepasswordButton_classSettings);
		imageIcon = new ImageIcon("res/Images/ius_logo.png");
		JLabel logoLabel = new JLabel(imageIcon);
		JButton save_settingsButton = new JButton(Main.closeButton_classSettings);
		// Language eklenecek
		// turkçe ve ingilizce
		// şifre değişikliği
		// easter egg ekle login tuşuna eğer 5 kere art arda basarsa dünya tahmin etme
		// oyunu açılsın felan
		setIconImage(imageIcon.getImage());

		settings.add(new JLabel(Main.language_classSettings));
		settings.add(english_languageButton);
		settings.add(turkish_languageButton);
		// settings.add(new JLabel(Main.changepassword_classSettings));
		settings.add(new JLabel(Main.username_classSettings));
		settings.add(usernameSettings);
		settings.add(new JLabel(Main.password_classSettings));
		settings.add(passwordSettings);
		settings.add(new JLabel(Main.newpassword_classSettings));
		settings.add(passwordSettings2);
		settings.add(change_passwordButton);

		settings.add(save_settingsButton);
		setResizable(false);
		add(settings);
		setLocationRelativeTo(null);
		setVisible(true);

		save_settingsButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Main.play_Sound(1);

				dispose();
				new Login();

			}
		});
		turkish_languageButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Main.play_Sound(1);

				// login
				Main.username_classLogin_classRegister = "Kullanıcı :";
				Main.password_classLogin_classRegister = "   Sifre :   ";
				Main.usernamepasswordEmpty_classLogin_classRegister = "Kullanıcı veya şifre boş olamaz.Tekrar deneyiniz";
				Main.loginButton_classLogin = "Giris";
				Main.registerButton_classLogin = "Kayıt";
				Main.setTitle_classLogin = "Giris";
				Main.usernameorpasswordWrong_classLogin = "Kullanıcı adı veya şifre hatalı!";

				// register
				Main.setTitle_classRegister = "Kayıt";
				Main.registerButton_usernametaken_classRegister = "The name that you submit is already taken";

				// Settings
				Main.changepasswordButton_classSettings = "Sifre değiştir";
				Main.setTitle_classSettings = "Ayarlar";
				Main.closeButton_classSettings = "Kapat";
				Main.language_classSettings = "      Dil               :";

				Main.changingpasswordFailed_classSettings = "Şifre değişikliği başarısız";
				Main.changingpasswordSuccessfull_classSettings = "Şifre değişikliği başarılı";
				Main.usernameSettingsorpasswordSettingsorpasswordSettings2isempty_classSettings = "Kullanıcı adı veya şifreler boş olamaz";
				Main.username_classSettings = "Kullanıcı adı :";
				Main.password_classSettings = "             Sifre :";
				Main.newpassword_classSettings = "     Yeni sifre :";
				// Notes
				Main.setTitle_classNotes = "Notlar /";
				Main.saveandcloseButton_classNotes = "Kaydet/Kapat";
				Main.notesaved_classNotes = "Notunuz başarıyla kayıt edildi";
			}// 18 tane main var
		});
		english_languageButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Main.play_Sound(1);

				// login
				Main.username_classLogin_classRegister = "Username :";
				Main.password_classLogin_classRegister = "Password :";
				Main.loginButton_classLogin = "Login";
				Main.registerButton_classLogin = "Register";
				Main.setTitle_classLogin = "Login";
				Main.usernameorpasswordWrong_classLogin = "Username or password wrong!";
				Main.usernamepasswordEmpty_classLogin_classRegister = "Username or password cannot be empty. Try again";
				// register
				Main.setTitle_classRegister = "Register";
				Main.registerButton_usernametaken_classRegister = "Kullanıcı adi zaten alınmış!";

				// settings
				Main.changepasswordButton_classSettings = "Change Password";
				Main.setTitle_classSettings = "Settings";
				Main.closeButton_classSettings = "Close";
				Main.language_classSettings = "Language:";
				Main.changingpasswordFailed_classSettings = "Password change failed";
				Main.changingpasswordSuccessfull_classSettings = "Password changed successfull";
				Main.usernameSettingsorpasswordSettingsorpasswordSettings2isempty_classSettings = "Username and passwords can not be empty";
				Main.username_classSettings = "        Username :";
				Main.password_classSettings = "         Password :";
				Main.newpassword_classSettings = "New password :";
				// Notes

				Main.setTitle_classNotes = "Notes /";
				Main.saveandcloseButton_classNotes = "Save/Close";
				Main.notesaved_classNotes = "Your note has been saved successfully";

			}
		});

		change_passwordButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Main.play_Sound(1);

				String Username_password5 = usernameSettings.getText() + ":" + passwordSettings.getText() + ":"
						+ passwordSettings2.getText();

				System.out.println(Username_password5);

				if (usernameSettings.getText().isEmpty() || passwordSettings.getText().isEmpty()
						|| passwordSettings2.getText().isEmpty()) {
					JOptionPane.showMessageDialog(Settings.this,
							Main.usernameSettingsorpasswordSettingsorpasswordSettings2isempty_classSettings);
					dispose();
					new Settings();

				} else {
					Main.serverOperations(Username_password5, 5);
					if (Main.receivedData.equals("0")) {
						System.out.println();
						JOptionPane.showMessageDialog(Settings.this, Main.changingpasswordFailed_classSettings);
						dispose();
						new Settings();

					}

					if (Main.receivedData.equals("1")) {
						JOptionPane.showMessageDialog(Settings.this, Main.changingpasswordSuccessfull_classSettings);

						dispose();
						new Login();

					}

				}

			}
		});

	}
}