import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class Graphic {
	public final Color BUTTONCOLOR = new Color(45, 175, 90);// ��ư�� ����
	public static JFrame mainFrame, winFrame;// �ֿ� frame �� ��� frame
	public static JPanel initPanel, gameSettingPanel;
	private JPanel characterSelectPanel, winPanel, gamePlayPanel;
	private JLabel lblImage, lblPlayer1, lblPlayer2, colorInfo, lblSubTitle, lbl1, lbl2;
	private JButton btnGameStart, btnManual, btnPlay, btnHome1, btnHome2, btnHome3, btnPlayAgain, btnGameExit;
	public static JButton btnNext;
	public static JRadioButton rb1PlayerMode, rb2PlayerMode, rbBlackToken, rbWhiteToken;
	private JRadioButton rbWhale, rbWolf, rbSquirrel, rbShark;// player1�� ĳ����
	private JRadioButton rbWhale2, rbWolf2, rbSquirrel2, rbShark2;// player2�� ĳ����
	public static ButtonGroup bgPlayerMode, bgToken, bgPlayer1, bgPlayer2;// button group
	private ButtonActionListener buttonL;
	private RadioButtonListener RadioButtonL;
	public static GameStartInfo gameInfo;// ���� ���� ����
	private JButton manual_btn;// ���� ������ ���� �ִ� �Ŵ��� ��ư
	private JButton option_btn;// ���� ������ ���� �ִ� �ɼ� ��ư
	private JButton exit_btn;// ���� ������ ���� �ִ� ������ ��ư(�ӽ�)
	private ShowManual sf;// manual �г�
	private ShowOptions option;// option �г�
	private BevelBorder border;// �׵θ� ���� ȿ��
	private ClickListener clickL;
	private GameManager gm;
	private Computer com;// computer
	private boolean[][] posPossible;
	private Board board_panel;
	private VolumeControl volume = new VolumeControl();
	private Sound play = new Sound();
	private TimeStop ts;
	private JLabel scr1, scr2, Character1, Character2;// score board
	private int score_1, score_2, P1, P2;
	private ImageIcon image1, image2;
	private Exit exitOrRestart = new Exit();
	public final Color c = new Color(168, 211, 142);// original color

	Image imgboard;// ������ �̹���
	Image img, img2, img3;// ���ȭ��
	ImageIcon imageB = new ImageIcon("images/b_place.png");// ���� ��ū �̹���
	ImageIcon imageW = new ImageIcon("images/w_place.png");// ��� ��ū �̹���
	ImageIcon imageBS = new ImageIcon("images/B_shadow.png");// ���� ��ū ���� �̹���
	ImageIcon imageWS = new ImageIcon("images/W_shadow.png");// ��� ��ū ���� �̹���

	String[] filePath1 = new String[6];// seal
	String[] filePath2 = new String[6];// wolf
	String[] filePath3 = new String[6];// chipmunk
	String[] filePath4 = new String[6];// monster
	String[] filePath0 = new String[6];// computer
	ImageIcon icon = new ImageIcon("images/OthelloIcon3.png");

	public Graphic() {
		// seal ĳ������ �̹��� ��� �迭
		filePath1[0] = "images/seal_1.png";
		filePath1[1] = "images/seal_2.png";
		filePath1[2] = "images/seal_3.png";
		filePath1[3] = "images/seal_1(1).png";
		filePath1[4] = "images/seal_2(1).png";
		filePath1[5] = "images/seal_3(1).png";

		// wolf ĳ������ �̹��� ��� �迭
		filePath2[0] = "images/wolf_1.png";
		filePath2[1] = "images/wolf_2.png";
		filePath2[2] = "images/wolf_3.png";
		filePath2[3] = "images/wolf_1(1).png";
		filePath2[4] = "images/wolf_2(1).png";
		filePath2[5] = "images/wolf_3(1).png";

		// chipmunk ĳ������ �̹��� ��� �迭
		filePath3[0] = "images/chipmunk_1.png";
		filePath3[1] = "images/chipmunk_2.png";
		filePath3[2] = "images/chipmunk_3.png";
		filePath3[3] = "images/chipmunk_1(1).png";
		filePath3[4] = "images/chipmunk_2(1).png";
		filePath3[5] = "images/chipmunk_3(1).png";

		// monster ĳ������ �̹��� ��� �迭
		filePath4[0] = "images/monster_1.png";
		filePath4[1] = "images/monster_2.png";
		filePath4[2] = "images/monster_3.png";
		filePath4[3] = "images/monster_1(1).png";
		filePath4[4] = "images/monster_2(1).png";
		filePath4[5] = "images/monster_3(1).png";

		// dragon ĳ������ �̹��� ��� �迭
		filePath0[0] = "images/dragon_1.png";
		filePath0[1] = "images/dragon_2.png";
		filePath0[2] = "images/dragon_3.png";
		filePath0[3] = "images/dragon_1(1).png";
		filePath0[4] = "images/dragon_2(1).png";
		filePath0[5] = "images/dragon_3(1).png";

		// �׵θ� ��ü ȿ��
		border = new BevelBorder(BevelBorder.RAISED);
		// thread����
		ts = new TimeStop();

		// frame����
		mainFrame = new JFrame("Othello Game");
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(new Dimension(1000, 700));
		mainFrame.setLocationRelativeTo(null);

		// button������ ����
		buttonL = new ButtonActionListener();// �ʱ� ȭ�� ��ư
		RadioButtonL = new RadioButtonListener();// ���� ��� ȭ�� ��ư
		clickL = new ClickListener();// ���� ȭ�� ��ư

		// ���� ������ ��ư ���� �� ��ȯ
		btnHome1 = getButtonHome();
		btnHome2 = getButtonHome();
		btnHome3 = getButtonHome();

		gameInfo = new GameStartInfo();// ���� ���� ����

		setInitPanel();// �ʱ�ȭ�� �г�

		setGameSettingPanel();// ���� ��� ���� ȭ��

		showInitPage();// �ʱ�ȭ�� �����ֱ�

		mainFrame.setVisible(true);
		play.playBackgroundSound1();// �������
	}

	// �ش�Ǵ� �гη� ȭ�� ����
	private void mySetPanel(JPanel p) {
		p.setPreferredSize(new Dimension(1000, 700));
		p.setLayout(null);
	}

	// ���� �� ���� �� ��ȯ
	private JLabel getLabelLogo() {
		JLabel lblOthelloGame = new JLabel("OTHELLO GAME");
		lblOthelloGame.setForeground(Color.white);
		lblOthelloGame.setBounds(30, 80, 940, 80);
		lblOthelloGame.setFont(new Font("Felix Titling", Font.BOLD, 53));
		lblOthelloGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblOthelloGame.setVisible(true);

		return lblOthelloGame;
	}

	// Home��ư ���� �� ��ȯ
	private JButton getButtonHome() {
		JButton btnHome = new JButton("Home");
		btnHome.setBackground(BUTTONCOLOR);
		btnHome.setForeground(Color.white);
		btnHome.setFont(new Font("Felix Titling", Font.BOLD, 20));
		btnHome.setVerticalAlignment(SwingConstants.CENTER);
		btnHome.setHorizontalAlignment(SwingConstants.CENTER);
		btnHome.setBounds(70, 600, 180, 50);
		btnHome.setVisible(true);
		btnHome.addActionListener(buttonL);
		btnHome.setBorder(border);
		btnHome.setUI(new StyledButtonUI());

		return btnHome;
	}

	// ���α׷� ���� ȭ��(�ʱ�ȭ��)
	private void setInitPanel() {

		// �̹��� ���� �о�� ��
		try {
			img = ImageIO.read(new File("./images/bg1.jpg"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		// �г� ���� �� ����
		initPanel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				g.drawImage(img, 0, 0, 1000, 700, null);
			}
		};

		mySetPanel(initPanel);

		initPanel.add(getLabelLogo());

		// ���� �� ����
		lblImage = new JLabel("", icon, SwingConstants.CENTER);
		lblImage.setVerticalAlignment(SwingConstants.CENTER); // ���η� ��� ����
		lblImage.setHorizontalAlignment(SwingConstants.CENTER); // ���η� ��� ����
		lblImage.setBounds(30, 100, 940, 200);
		lblImage.setVisible(false);
		initPanel.add(lblImage);

		// ������ �� ����
		lblSubTitle = new JLabel("No Pain No Game");
		lblSubTitle.setFont(new Font("Felix Titling", Font.BOLD, 30));
		lblSubTitle.setVerticalAlignment(SwingConstants.CENTER); // ���η� ��� ����
		lblSubTitle.setHorizontalAlignment(SwingConstants.CENTER); // ���η� ���� ����
		lblSubTitle.setBounds(100, 170, 800, 50);
		lblSubTitle.setForeground(Color.white);
		initPanel.add(lblSubTitle);

		// ���� ���� ��ư ����
		btnGameStart = new JButton("Game Start");
		btnGameStart.setBackground(BUTTONCOLOR);
		btnGameStart.setForeground(Color.white);
		btnGameStart.setFont(new Font("Felix Titling", Font.BOLD, 23));
		btnGameStart.setVerticalAlignment(SwingConstants.CENTER);
		btnGameStart.setHorizontalAlignment(SwingConstants.CENTER);
		btnGameStart.setBounds(375, 400, 250, 80);
		btnGameStart.addActionListener(buttonL);
		btnGameStart.setBorder(border);
		btnGameStart.setUI(new StyledButtonUI());
		btnGameStart.setVisible(true);
		initPanel.add(btnGameStart);

		// manual ��ư ����
		btnManual = new JButton("MANUAL");
		btnManual.setBackground(BUTTONCOLOR);
		btnManual.setForeground(Color.white);
		btnManual.setFont(new Font("Felix Titling", Font.BOLD, 23));
		btnManual.setVerticalAlignment(SwingConstants.CENTER);
		btnManual.setHorizontalAlignment(SwingConstants.CENTER);
		btnManual.setBounds(375, 530, 250, 80);
		btnManual.addActionListener(buttonL);
		btnManual.setBorder(border);
		btnManual.setUI(new StyledButtonUI());
		btnManual.setVisible(true);
		initPanel.add(btnManual);

	}

	// ���� ��� ���� �г�
	private void setGameSettingPanel() {
		// ���ȭ�� �̹��� ��� �о����
		try {
			img2 = ImageIO.read(new File("./images/bg2.jpg"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		// �г� ���� �� ����
		gameSettingPanel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				g.drawImage(img2, 0, 0, 1000, 700, null);
				setOpaque(false);
			}

		};

		// ���� ��� ���� �гη� ����
		mySetPanel(gameSettingPanel);
		gameSettingPanel.add(btnHome1);// home��ư �߰�
		gameSettingPanel.add(getLabelLogo());// ���� ���̺� �߰�

		// next��ư ����
		btnNext = new JButton("Next");
		btnNext.setBackground(BUTTONCOLOR);
		btnNext.setForeground(Color.white);
		btnNext.setFont(new Font("Felix Titling", Font.BOLD, 20));
		btnNext.setVerticalAlignment(SwingConstants.CENTER);
		btnNext.setHorizontalAlignment(SwingConstants.CENTER);
		btnNext.setBounds(750, 600, 180, 50);
		btnNext.addActionListener(buttonL);
		btnNext.setBorder(border);
		btnNext.setVisible(false);
		btnNext.setUI(new StyledButtonUI());
		gameSettingPanel.add(btnNext);

		// 1�� �÷��� ��� üũ�ڽ�
		rb1PlayerMode = new JRadioButton("1 Player Mode");
		rb1PlayerMode.setOpaque(false);
		rb1PlayerMode.setForeground(Color.white);
		rb1PlayerMode.setFont(new Font("Felix Titling", Font.BOLD, 20));
		rb1PlayerMode.setVerticalAlignment(SwingConstants.CENTER);
		rb1PlayerMode.setHorizontalAlignment(SwingConstants.CENTER);
		rb1PlayerMode.setBounds(50, 200, 900, 80);
		rb1PlayerMode.setVisible(true);
		rb1PlayerMode.addActionListener(RadioButtonL);
		gameSettingPanel.add(rb1PlayerMode);

		// 2�� �÷��� ��� üũ�ڽ�
		rb2PlayerMode = new JRadioButton("2 Player Mode");
		rb2PlayerMode.setOpaque(false);
		rb2PlayerMode.setForeground(Color.white);
		rb2PlayerMode.setFont(new Font("Felix Titling", Font.BOLD, 20));
		rb2PlayerMode.setVerticalAlignment(SwingConstants.CENTER);
		rb2PlayerMode.setHorizontalAlignment(SwingConstants.CENTER);
		rb2PlayerMode.setBounds(50, 250, 900, 80);
		rb2PlayerMode.setVisible(true);
		rb2PlayerMode.addActionListener(RadioButtonL);
		gameSettingPanel.add(rb2PlayerMode);

		// �÷��� ��� grouping
		bgPlayerMode = new ButtonGroup();
		bgPlayerMode.add(rb1PlayerMode);
		bgPlayerMode.add(rb2PlayerMode);

		// 1�� ����� �� ��ū ���� ���ϴ� üũ�ڽ�(���� ��ū)
		rbBlackToken = new JRadioButton("Black Token");
		rbBlackToken.setOpaque(false);
		rbBlackToken.setForeground(Color.white);
		rbBlackToken.setFont(new Font("Felix Titling", Font.BOLD, 20));
		rbBlackToken.setVerticalAlignment(SwingConstants.CENTER);
		rbBlackToken.setHorizontalAlignment(SwingConstants.CENTER);
		rbBlackToken.setBounds(50, 350, 900, 80);
		rbBlackToken.setVisible(false);
		rbBlackToken.addActionListener(RadioButtonL);
		gameSettingPanel.add(rbBlackToken);

		// 1�� ����� �� ��ū ���� ���ϴ� üũ�ڽ�(��� ��ū)
		rbWhiteToken = new JRadioButton("White Token");
		rbWhiteToken.setOpaque(false);
		rbWhiteToken.setForeground(Color.white);
		rbWhiteToken.setFont(new Font("Felix Titling", Font.BOLD, 20));
		rbWhiteToken.setVerticalAlignment(SwingConstants.CENTER);
		rbWhiteToken.setHorizontalAlignment(SwingConstants.CENTER);
		rbWhiteToken.setBounds(50, 400, 900, 80);
		rbWhiteToken.setVisible(false);
		rbWhiteToken.addActionListener(RadioButtonL);
		gameSettingPanel.add(rbWhiteToken);

		// ���� ��ū�� �� ��ū ���� grouping
		bgToken = new ButtonGroup();
		bgToken.add(rbBlackToken);
		bgToken.add(rbWhiteToken);
	}

	// ĳ���� ���ϴ� �г�(ĳ���� ���ϴ� ȭ��)
	private void setCharacterSelectPanel() {

		// ���ȭ��
		try {
			img3 = ImageIO.read(new File("./images/bg3.jpg"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		// �г� ���� �� ����
		characterSelectPanel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				g.drawImage(img3, 0, 0, 1000, 700, null);
			}
		};

		// �г� ȭ�� ����
		mySetPanel(characterSelectPanel);
		characterSelectPanel.add(btnHome2);// home ��ư
		characterSelectPanel.add(getLabelLogo());// ���� ���̺� �߰�

		String color = null;// ��ū ���� �˷��ִ� ���̺�

		if (gameInfo.getGameMode() == 1) {// 1�� ����� ���
			if (gameInfo.getPlayer1().getMyToken().getState() == 1)// ������� ��ū ������ ���� ��ū�� ���
				color = "Black";
			else// ������� ��ū ������ �� ��ū�� ���
				color = "White";
		} else// 2�� ����� ���
			color = "Black";

		// 1��° �÷��̾�
		lblPlayer1 = new JLabel("Player1 (" + color + ")" + " : ");// ��ū ���� ���� ǥ�����ֱ�
		lblPlayer1.setBounds(150, 170, 500, 30);
		lblPlayer1.setFont(new Font("Felix Titling", Font.BOLD, 20));
		lblPlayer1.setForeground(Color.white);
		lblPlayer1.setVerticalAlignment(SwingConstants.CENTER);
		lblPlayer1.setHorizontalAlignment(SwingConstants.LEFT);
		lblPlayer1.setVisible(true);
		characterSelectPanel.add(lblPlayer1);

		// seal ĳ���� ����
		rbWhale = new JRadioButton(new ImageIcon("images/seal_t.png"));
		rbWhale.setForeground(Color.black);
		rbWhale.setVerticalAlignment(SwingConstants.CENTER);
		rbWhale.setHorizontalAlignment(SwingConstants.CENTER);
		rbWhale.setBounds(200, 220, 119, 120);
		rbWhale.setVisible(true);
		rbWhale.addActionListener(RadioButtonL);
		rbWhale.setOpaque(false);
		characterSelectPanel.add(rbWhale);

		// wolf ĳ���� ����
		rbWolf = new JRadioButton(new ImageIcon("images/wolf_t.png"));
		rbWolf.setForeground(Color.black);
		rbWolf.setVerticalAlignment(SwingConstants.CENTER);
		rbWolf.setHorizontalAlignment(SwingConstants.CENTER);
		rbWolf.setBounds(360, 220, 119, 120);
		rbWolf.setVisible(true);
		rbWolf.addActionListener(RadioButtonL);
		rbWolf.setOpaque(false);
		characterSelectPanel.add(rbWolf);

		// squirrel ĳ���� ����
		rbSquirrel = new JRadioButton(new ImageIcon("images/chipmunk_t.png"));
		rbSquirrel.setForeground(Color.black);
		rbSquirrel.setVerticalAlignment(SwingConstants.CENTER);
		rbSquirrel.setHorizontalAlignment(SwingConstants.CENTER);
		rbSquirrel.setBounds(520, 220, 119, 120);
		rbSquirrel.setVisible(true);
		rbSquirrel.addActionListener(RadioButtonL);
		rbSquirrel.setOpaque(false);
		characterSelectPanel.add(rbSquirrel);

		// monster ĳ���� ����
		rbShark = new JRadioButton(new ImageIcon("images/monster_t.png"));
		rbShark.setForeground(Color.black);
		rbShark.setVerticalAlignment(SwingConstants.CENTER);
		rbShark.setHorizontalAlignment(SwingConstants.CENTER);
		rbShark.setBounds(680, 220, 119, 120);
		rbShark.setVisible(true);
		rbShark.addActionListener(RadioButtonL);
		rbShark.setOpaque(false);
		characterSelectPanel.add(rbShark);

		// ĳ���͵� grouping
		bgPlayer1 = new ButtonGroup();
		bgPlayer1.add(rbWhale);
		bgPlayer1.add(rbWolf);
		bgPlayer1.add(rbSquirrel);
		bgPlayer1.add(rbShark);

		// 2�� ����� ���
		if (gameInfo.getGameMode() == 2) {
			// 2��° �÷��̾�
			lblPlayer2 = new JLabel("Player2 (White) : ");
			lblPlayer2.setBounds(150, 370, 500, 30);
			lblPlayer2.setFont(new Font("Felix Titling", Font.BOLD, 20));
			lblPlayer2.setForeground(Color.white);
			lblPlayer2.setVerticalAlignment(SwingConstants.CENTER);
			lblPlayer2.setHorizontalAlignment(SwingConstants.LEFT);
			lblPlayer2.setVisible(true);
			characterSelectPanel.add(lblPlayer2);

			// seal ĳ���� ����
			rbWhale2 = new JRadioButton(new ImageIcon("images/seal_t.png"));
			rbWhale2.setForeground(Color.black);
			rbWhale2.setVerticalAlignment(SwingConstants.CENTER);
			rbWhale2.setHorizontalAlignment(SwingConstants.CENTER);
			rbWhale2.setBounds(200, 420, 119, 120);
			rbWhale2.setVisible(true);
			rbWhale2.addActionListener(RadioButtonL);
			rbWhale2.setOpaque(false);
			characterSelectPanel.add(rbWhale2);

			// wolf ĳ���� ����
			rbWolf2 = new JRadioButton(new ImageIcon("images/wolf_t.png"));
			rbWolf2.setForeground(Color.black);
			rbWolf2.setVerticalAlignment(SwingConstants.CENTER);
			rbWolf2.setHorizontalAlignment(SwingConstants.CENTER);
			rbWolf2.setBounds(360, 420, 119, 120);
			rbWolf2.setVisible(true);
			rbWolf2.addActionListener(RadioButtonL);
			rbWolf2.setOpaque(false);
			characterSelectPanel.add(rbWolf2);

			// squirrel ĳ���� ����
			rbSquirrel2 = new JRadioButton(new ImageIcon("images/chipmunk_t.png"));
			rbSquirrel2.setForeground(Color.black);
			rbSquirrel2.setVerticalAlignment(SwingConstants.CENTER);
			rbSquirrel2.setHorizontalAlignment(SwingConstants.CENTER);
			rbSquirrel2.setBounds(520, 420, 119, 120);
			rbSquirrel2.setVisible(true);
			rbSquirrel2.addActionListener(RadioButtonL);
			rbSquirrel2.setOpaque(false);
			characterSelectPanel.add(rbSquirrel2);

			// monster ĳ���� ����
			rbShark2 = new JRadioButton(new ImageIcon("images/monster_t.png"));
			rbShark2.setForeground(Color.black);
			rbShark2.setVerticalAlignment(SwingConstants.CENTER);
			rbShark2.setHorizontalAlignment(SwingConstants.CENTER);
			rbShark2.setBounds(680, 420, 119, 120);
			rbShark2.setVisible(true);
			rbShark2.addActionListener(RadioButtonL);
			rbShark2.setOpaque(false);
			characterSelectPanel.add(rbShark2);

			// ĳ���͵� grouping
			bgPlayer2 = new ButtonGroup();
			bgPlayer2.add(rbWhale2);
			bgPlayer2.add(rbWolf2);
			bgPlayer2.add(rbSquirrel2);
			bgPlayer2.add(rbShark2);
		}

		// Play ��ư ����
		btnPlay = new JButton("Play");
		btnPlay.setBackground(BUTTONCOLOR);
		btnPlay.setForeground(Color.white);
		btnPlay.setFont(new Font("Felix Titling", Font.BOLD, 20));
		btnPlay.setVerticalAlignment(SwingConstants.CENTER);
		btnPlay.setHorizontalAlignment(SwingConstants.CENTER);
		btnPlay.setBounds(750, 600, 180, 50);
		btnPlay.addActionListener(buttonL);
		btnPlay.setVisible(false);
		btnPlay.setBorder(border);
		btnPlay.setUI(new StyledButtonUI());
		characterSelectPanel.add(btnPlay);
	}

	// ����ȭ���� ���ȭ��
	class GamePlayPanel extends JPanel {
		private BufferedImage back_img;

		public GamePlayPanel() {
			setPreferredSize(new Dimension(1000, 700));
			setLayout(null);
			setVisible(true);
			try {
				back_img = ImageIO.read(new File("images/back3.png"));

			} catch (IOException ex) {

			}
		}// GamePlayPanel()

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(back_img, 0, 0, 1000, 700, this);
		}// paintComponent()
	}

	// ����ȭ�� �� �������
	private void setGamePlayPanel() {

		// ������ ���ȭ��
		gamePlayPanel = new GamePlayPanel();

		// ���Ӻ����� �̹���
		board_panel = new Board();
		board_panel.addMouseListener(clickL);
		board_panel.setBounds(250, 130, 500, 500);
		board_panel.setVisible(true);
		gamePlayPanel.add(board_panel);

		ImageIcon imgManual = new ImageIcon("images/Manual.png");// manual ��ư �̹���
		ImageIcon imgOption = new ImageIcon("images/option.png");// option ��ư �̹���
		ImageIcon imgExit = new ImageIcon("images/exitdoor.png");// exit ��ư �̹���

		// manual ��ư ����
		manual_btn = new JButton(imgManual);
		manual_btn.setBounds(50, 40, 50, 60);
		manual_btn.setOpaque(false);
		manual_btn.setBorderPainted(false);
		manual_btn.setBackground(new Color(168, 211, 142));
		manual_btn.addActionListener(buttonL);
		gamePlayPanel.add(manual_btn);

		// ���� ������ ��ū ���� ǥ���ϴ� �� ����
		colorInfo = new JLabel("Black Turn");
		colorInfo.setFont(new Font("Centaur", Font.BOLD, 30));
		colorInfo.setForeground(Color.black);
		colorInfo.setBounds(400, 100, 200, 50);// 11.23
		colorInfo.setHorizontalAlignment(SwingConstants.CENTER);
		colorInfo.setVerticalAlignment(SwingConstants.CENTER);
		gamePlayPanel.add(colorInfo);

		// option ��ư ����
		option_btn = new JButton(imgOption);
		option_btn.setBounds(850, 40, 50, 50);
		option_btn.setOpaque(false);
		option_btn.setBorderPainted(false);
		option_btn.setBackground(new Color(168, 211, 142));
		option_btn.addActionListener(buttonL);
		gamePlayPanel.add(option_btn);

		// exit ��ư ����
		exit_btn = new JButton(imgExit);
		exit_btn.setBounds(920, 40, 50, 50);
		exit_btn.setOpaque(false);
		exit_btn.setBorderPainted(false);
		exit_btn.setBackground(new Color(168, 211, 142));
		exit_btn.addActionListener(buttonL);
		gamePlayPanel.add(exit_btn);

		// �÷��̾���� ���� ����
		int p1, p2;
		int t1, t2;
		int mode;
		JLabel Token1;
		JLabel Token2;
		ImageIcon imgtoken1, imgtoken2;
		JLabel ply1, ply2;// player 1,w
		JLabel tt;// ����

		// ���� ���� ��
		tt = new JLabel("OTHELLO GAME");
		tt.setForeground(Color.black);
		tt.setBounds(30, 20, 940, 80);
		tt.setFont(new Font("Felix Titling", Font.BOLD, 40));
		tt.setHorizontalAlignment(SwingConstants.CENTER);
		tt.setVisible(true);
		gamePlayPanel.add(tt);

		// player 1 �� ����
		ply1 = new JLabel("Player 1");
		ply1.setBounds(45, 230, 150, 40);
		ply1.setOpaque(false);
		ply1.setBackground(new Color(204, 238, 253));
		ply1.setForeground(Color.BLACK);
		ply1.setFont(new Font("Centaur", Font.BOLD, 25));
		ply1.setVerticalAlignment(SwingConstants.CENTER);
		ply1.setHorizontalAlignment(SwingConstants.CENTER);
		ply1.setVisible(true);
		gamePlayPanel.add(ply1);

		// player 2 �� ����
		ply2 = new JLabel("Player 2");
		ply2.setBounds(810, 230, 150, 40);
		ply2.setOpaque(false);
		ply2.setBackground(new Color(204, 238, 253));
		ply2.setForeground(Color.black);
		ply2.setFont(new Font("Centaur", Font.BOLD, 25));
		ply2.setVerticalAlignment(SwingConstants.CENTER);
		ply2.setHorizontalAlignment(SwingConstants.CENTER);
		ply2.setVisible(true);
		gamePlayPanel.add(ply2);

		// player 1�� ���� �� ����
		scr1 = new JLabel("2");
		scr1.setBounds(45, 420, 150, 90);
		scr1.setOpaque(false);
		scr1.setForeground(Color.black);
		scr1.setFont(new Font("Centaur", Font.BOLD, 60));
		scr1.setVerticalAlignment(SwingConstants.CENTER);
		scr1.setHorizontalAlignment(SwingConstants.CENTER);
		scr1.setVisible(true);
		gamePlayPanel.add(scr1);

		// player 2�� ���� �� ����
		scr2 = new JLabel("2");
		scr2.setBounds(810, 420, 150, 90);
		scr2.setOpaque(false);
		scr2.setForeground(Color.black);
		scr2.setFont(new Font("Centaur", Font.BOLD, 60));
		scr2.setVerticalAlignment(SwingConstants.CENTER);
		scr2.setHorizontalAlignment(SwingConstants.CENTER);
		scr2.setVisible(true);
		gamePlayPanel.add(scr2);

		mode = gameInfo.getGameMode();// ���� ���� ���
		p1 = gameInfo.getPlayer1().getMyCharacter();// player 1�� ĳ����
		p2 = gameInfo.getPlayer2().getMyCharacter();// player 2�� ĳ����

		t1 = gameInfo.getPlayer1().getMyToken().getState();// player 1�� ��ū ����
		t2 = gameInfo.getPlayer2().getMyToken().getState();// player 2�� ��ū ����

		// 1�� ���
		if (mode == 1) {
			// �� player�� ��ū ����
			if (t1 == 1) {
				// player 1�� ��ū ������ ������
				imgtoken1 = new ImageIcon("images/B.png");
				Token1 = new JLabel("", imgtoken1, SwingConstants.CENTER);
				Token1.setBounds(110, 290, 120, 120);
				Token1.setVisible(true);
				gamePlayPanel.add(Token1);

				// player 2�� ��ū ������ ���
				imgtoken2 = new ImageIcon("images/W.png");
				Token2 = new JLabel("", imgtoken2, SwingConstants.CENTER);
				Token2.setBounds(760, 290, 120, 120);
				Token2.setVisible(true);
				gamePlayPanel.add(Token2);
			} else if (t1 == -1) {
				// player 1�� ��ū ������ ���
				imgtoken1 = new ImageIcon("images/W.png");
				Token1 = new JLabel("", imgtoken1, SwingConstants.CENTER);
				Token1.setBounds(110, 290, 120, 120);
				Token1.setVisible(true);
				gamePlayPanel.add(Token1);

				// player 2�� ��ū ������ ������
				imgtoken2 = new ImageIcon("images/B.png");
				Token2 = new JLabel("", imgtoken2, SwingConstants.CENTER);
				Token2.setBounds(760, 290, 120, 120);
				Token2.setVisible(true);
				gamePlayPanel.add(Token2);
			}

			// �� player �� ĳ����
			if (p1 == 1) {
				// player 1�� ĳ���Ͱ� seal
				image1 = new ImageIcon(filePath1[0]);
				Character1 = new JLabel("", image1, SwingConstants.CENTER);
				Character1.setBounds(10, 290, 120, 120);
				Character1.setVisible(true);
				gamePlayPanel.add(Character1);

			} else if (p1 == 2) {
				// player 1�� ĳ���Ͱ� wolf
				image1 = new ImageIcon(filePath2[0]);
				Character1 = new JLabel("", image1, SwingConstants.CENTER);
				Character1.setBounds(10, 290, 120, 120);
				Character1.setVisible(true);
				gamePlayPanel.add(Character1);
			} else if (p1 == 3) {
				// player 1�� ĳ���Ͱ� squirrel
				image1 = new ImageIcon(filePath3[0]);
				Character1 = new JLabel("", image1, SwingConstants.CENTER);
				Character1.setBounds(10, 290, 120, 120);
				Character1.setVisible(true);
				gamePlayPanel.add(Character1);
			} else if (p1 == 4) {
				// player 1�� ĳ���Ͱ� monster
				image1 = new ImageIcon(filePath4[0]);
				Character1 = new JLabel("", image1, SwingConstants.CENTER);
				Character1.setBounds(10, 290, 120, 120);
				Character1.setVisible(true);
				gamePlayPanel.add(Character1);
			}

			if (p2 == 0) {
				// 1�� ����� �� player 2�� computer ĳ����
				image2 = new ImageIcon(filePath0[0]);
				Character2 = new JLabel("", image2, SwingConstants.CENTER);
				Character2.setBounds(860, 290, 120, 120);
				Character2.setVisible(true);
				gamePlayPanel.add(Character2);
			}

		}
		// 2�� ����� ���
		else {
			// ���� ��ū�� ����
			imgtoken1 = new ImageIcon("images/B.png");
			Token1 = new JLabel("", imgtoken1, SwingConstants.CENTER);
			Token1.setBounds(110, 290, 120, 120);
			Token1.setVisible(true);
			gamePlayPanel.add(Token1);

			// ��� ��ū�� ������
			imgtoken2 = new ImageIcon("images/W.png");
			Token2 = new JLabel("", imgtoken2, SwingConstants.CENTER);
			Token2.setBounds(760, 290, 120, 120);
			Token2.setVisible(true);
			gamePlayPanel.add(Token2);

			// �� player�� ĳ����
			// player 1
			if (p1 == 1) {
				// player 1�� ĳ���Ͱ� seal
				image1 = new ImageIcon(filePath1[0]);
				Character1 = new JLabel("", image1, SwingConstants.CENTER);
				Character1.setBounds(10, 290, 120, 120);
				Character1.setVisible(true);
				gamePlayPanel.add(Character1);
			} else if (p1 == 2) {
				// player 1�� ĳ���Ͱ� wolf
				image1 = new ImageIcon(filePath2[0]);
				Character1 = new JLabel("", image1, SwingConstants.CENTER);
				Character1.setBounds(10, 290, 120, 120);
				Character1.setVisible(true);
				gamePlayPanel.add(Character1);
			} else if (p1 == 3) {
				// player 1�� ĳ���Ͱ� squirrel
				image1 = new ImageIcon(filePath3[0]);
				Character1 = new JLabel("", image1, SwingConstants.CENTER);
				Character1.setBounds(10, 290, 120, 120);
				Character1.setVisible(true);
				gamePlayPanel.add(Character1);
			} else if (p1 == 4) {
				// player 1�� ĳ���Ͱ� monster
				image1 = new ImageIcon(filePath4[0]);
				Character1 = new JLabel("", image1, SwingConstants.CENTER);
				Character1.setBounds(10, 290, 120, 120);
				Character1.setVisible(true);
				gamePlayPanel.add(Character1);
			}

			// player 2
			if (p2 == 1) {
				// player 2�� ĳ���Ͱ� seal
				image2 = new ImageIcon(filePath1[0]);
				Character2 = new JLabel("", image2, SwingConstants.CENTER);
				Character2.setBounds(870, 290, 120, 120);
				Character2.setVisible(true);
				gamePlayPanel.add(Character2);
			} else if (p2 == 2) {
				// player 2�� ĳ���Ͱ� wolf
				image2 = new ImageIcon(filePath2[0]);
				Character2 = new JLabel("", image2, SwingConstants.CENTER);
				Character2.setBounds(870, 290, 120, 120);
				Character2.setVisible(true);
				gamePlayPanel.add(Character2);
			} else if (p2 == 3) {
				// player 2�� ĳ���Ͱ� squirrel
				image2 = new ImageIcon(filePath3[0]);
				Character2 = new JLabel("", image2, SwingConstants.CENTER);
				Character2.setBounds(870, 290, 120, 120);
				Character2.setVisible(true);
				gamePlayPanel.add(Character2);
			} else if (p2 == 4) {
				// player 2�� ĳ���Ͱ� monster
				image2 = new ImageIcon(filePath4[0]);
				Character2 = new JLabel("", image2, SwingConstants.CENTER);
				Character2.setBounds(870, 290, 120, 120);
				Character2.setVisible(true);
				gamePlayPanel.add(Character2);
			}
		}

	}

	// ���� ��� �г�(��� ȭ��)
	private void setWinPanel() {
		// �г� ���� �� ����
		winPanel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				g.drawImage(img, 0, 0, 700, 500, null);
			}

		};
		winPanel.setPreferredSize(new Dimension(700, 500));
		winPanel.setLayout(null);

		// ���� ǥ�� ����
		String msg;

		if (score_1 > score_2)// player 1�� �¸�
			msg = "Player 1";
		else if (score_1 < score_2)// player 2�� �¸�
			msg = "Player 2";
		else// ���º�
			msg = "";

		// ���� ���� �� ���� �� ����
		lbl1 = new JLabel(msg);
		lbl1.setFont(new Font("Felix Titling", Font.BOLD, 30));
		lbl1.setVerticalAlignment(SwingConstants.CENTER);
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setForeground(Color.white);
		lbl1.setBounds(270, 50, 150, 35);
		winPanel.add(lbl1);

		// �¸� ���� ����
		lbl2 = new JLabel("Win!!");
		if (msg == "")// ���º�
			lbl2.setText("Draw!");
		lbl2.setFont(new Font("Felix Titling", Font.BOLD, 70));
		lbl2.setVerticalAlignment(SwingConstants.CENTER);
		lbl2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2.setForeground(Color.white);
		lbl2.setBounds(200, 130, 300, 100);
		winPanel.add(lbl2);

		// play again ��ư ����
		btnPlayAgain = new JButton("PLAY AGAIN");
		btnPlayAgain.setBackground(BUTTONCOLOR);
		btnPlayAgain.setForeground(Color.white);
		btnPlayAgain.setFont(new Font("Felix Titling", Font.BOLD, 20));
		btnPlayAgain.setVerticalAlignment(SwingConstants.CENTER);
		btnPlayAgain.setHorizontalAlignment(SwingConstants.CENTER);
		btnPlayAgain.setBounds(100, 340, 200, 70);
		btnPlayAgain.addActionListener(buttonL);
		btnPlayAgain.setVisible(true);
		btnPlayAgain.setUI(new StyledButtonUI());
		winPanel.add(btnPlayAgain);

		// game exit ��ư ����
		btnGameExit = new JButton("GAME" + " " + "EXIT");
		btnGameExit.setBackground(BUTTONCOLOR);
		btnGameExit.setForeground(Color.white);
		btnGameExit.setFont(new Font("Felix Titling", Font.BOLD, 20));
		btnGameExit.setVerticalAlignment(SwingConstants.CENTER);
		btnGameExit.setHorizontalAlignment(SwingConstants.CENTER);
		btnGameExit.setBounds(400, 340, 200, 70);
		btnGameExit.addActionListener(buttonL);
		btnGameExit.setUI(new StyledButtonUI());
		btnGameExit.setVisible(true);
		winPanel.add(btnGameExit);
	}

	// ������ �ʱ�ȭ�� ����
	private void showInitPage() {
		mainFrame.setContentPane(initPanel);
		mainFrame.pack();
	}

	// ��� ���� ȭ�� �г� ����
	static private void showGameSettingPage() {
		mainFrame.setContentPane(gameSettingPanel);
		mainFrame.pack();
	}

	// player �� ĳ���� ����
	private void showChracterSelectPanel() {
		mainFrame.setContentPane(characterSelectPanel);
		mainFrame.pack();
	}

	// ����ȭ�� �г� ����
	private void showGamePlayPage() {
		mainFrame.setContentPane(gamePlayPanel);
		mainFrame.pack();
	}

	// ���� ��� ǥ���ϴ� ������ ����
	private void showWinPage() {
		colorInfo.setText("Game Over");

		// ���� ��� ǥ�� �г� ������ �����ӿ� �߰�
		setWinPanel();
		winFrame = new JFrame("Game Result");
		winFrame.setResizable(false);
		winFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		winFrame.setVisible(true);
		winFrame.setSize(new Dimension(700, 500));
		winFrame.setLocationRelativeTo(mainFrame);
		winFrame.getContentPane().add(winPanel);
		winFrame.pack();

		// ȿ���� ���
		volume.endVolume();
	}

	// ����ȭ�� �� ���Ӻ����� update
	private void updateGameBoardG() {
		board_panel.removeAll();
		posPossible = GameManager.findPossiblePosition(gm.getGameCurrentBoard(), gm.currentTurnToken.getState());
		board_panel.paintImmediately(0, 0, 500, 500);
	}

	// ���� ������ ��ū ���� update
	private void updateColorInfo(int color) {
		String strColor = (color == 1) ? "Black" : "White";

		colorInfo.setText(strColor + " Turn");
	}

	// player�� ������ ĳ���� update
	private void updateScore() {

		// player 1�� ��ū ������ ������
		if (gameInfo.getPlayer1().getMyToken().getState() == 1) {
			score_1 = gm.getGameCurrentBoard().getCntBlack();// player 1�� ���� == ���� ��ū�� ����
			score_2 = gm.getGameCurrentBoard().getCntWhite();// player 2�� ���� == ��� ��ū�� ����
		}
		// player 1�� ��ū ������ ���
		else {
			score_1 = gm.getGameCurrentBoard().getCntWhite();// player 1�� ���� == ��� ��ū�� ����
			score_2 = gm.getGameCurrentBoard().getCntBlack();// player 2�� ���� == ���� ��ū�� ����
		}
		// ���� �� ���� �� update
		scr1.setText(Integer.toString(score_1));
		scr2.setText(Integer.toString(score_2));
		scr1.paintImmediately(scr1.getVisibleRect());
		scr2.paintImmediately(scr2.getVisibleRect());

		// player�� ĳ����
		P1 = gameInfo.getPlayer1().getMyCharacter();
		P2 = gameInfo.getPlayer2().getMyCharacter();

		int i, j;

		// player 1�� ����
		if (score_1 >= 0 && score_1 < 8) {// 1�ܰ�
			i = 0;
		} else if (score_1 < 16) {// 2�ܰ�
			i = 3;
		} else if (score_1 < 24) {// 3�ܰ�
			i = 1;
		} else if (score_1 < 32) {// 4�ܰ�
			i = 4;
		} else if (score_1 < 40) {// 5�ܰ�
			i = 2;
		} else {// 6�ܰ�
			i = 5;
		}

		// player 2�� ����
		if (score_2 >= 0 && score_2 < 8) {// 1�ܰ�
			j = 0;
		} else if (score_2 < 16) {// 2�ܰ�
			j = 3;
		} else if (score_2 < 24) {// 3�ܰ�
			j = 1;
		} else if (score_2 < 32) {// 4�ܰ�
			j = 4;
		} else if (score_2 < 40) {// 5�ܰ�
			j = 2;
		} else {// 6�ܰ�
			j = 5;
		}

		// ��ȭ �ܰ迡 ���� file��� �о����
		String s1, s2;

		// player 1�� ĳ���� �̹��� ��� �о����
		if (P1 == 1)// seal
			s1 = filePath1[i];
		else if (P1 == 2)// wolf
			s1 = filePath2[i];
		else if (P1 == 3)// squirrel
			s1 = filePath3[i];
		else// monster
			s1 = filePath4[i];

		// player 2�� ĳ���� �̹��� ��� �о����
		if (P2 == 0)// computer
			s2 = filePath0[j];
		else if (P2 == 1)// seal
			s2 = filePath1[j];
		else if (P2 == 2)// wolf
			s2 = filePath2[j];
		else if (P2 == 3)// squirrel
			s2 = filePath3[j];
		else// monster
			s2 = filePath4[j];

		// ĳ���� ����
		Character1.setIcon(new ImageIcon(s1));
		Character2.setIcon(new ImageIcon(s2));
	}

	// ����ȭ�� �� ���Ӻ�����
	class Board extends JPanel {

		public Board() {
			setPreferredSize(new Dimension(500, 500));
			setVisible(true);
			setOpaque(false);
			// ������ �̹��� �о����
			try {
				imgboard = ImageIO.read(new File("images/Board_gg.png"));

			} catch (IOException e) {
				e.printStackTrace();
			}
		}// Board()

		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			g.drawImage(imgboard, 0, 0, 500, 500, null);
			Graphics2D g2 = (Graphics2D) g;

			// ���� �������� ��ū �̹��� �׸���
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (gm.getGameCurrentBoard().board[i][j] == 1) {// ������ ��ū �̹��� ��ġ�ϱ�
						g2.drawImage(imageB.getImage(), 58 * j + 18, 58 * i + 18, null);
					} else if (gm.getGameCurrentBoard().board[i][j] == -1) {// ��� ��ū �̹��� ��ġ�ϱ�
						g2.drawImage(imageW.getImage(), 58 * j + 18, 58 * i + 18, null);
					}
				}
			} // paintComponent()

			// computer�� ������ ����ڰ� ���� �����ǿ��� ��ġ ������ ��ū �̹��� �׸���
			if (!(gameInfo.getPlayer2().getMyCharacter() == 0
					&& gm.currentTurnToken.getState() == gameInfo.getPlayer2().getMyToken().getState())) {
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if (posPossible[i][j] == true) {// ��ū ��ġ ������ ��ġ
							if (gm.currentTurnToken.getState() == 1) {// player�� ��ū ������ �������� ���
								g2.drawImage(imageBS.getImage(), 58 * j + 18, 58 * i + 18, null);// ������ shadow ��ū ��ġ
							} else {// player�� ��ū ������ ����� ���
								g2.drawImage(imageWS.getImage(), 58 * j + 18, 58 * i + 18, null);// ��� shadow ��ū ��ġ
							}
						}
					}
				}
			}
		}
	}

	// ���� �ٽ��ϱ�
	public static void restartGame(int re) {
		// ���� ����� ���� Ȯ��
		re = Exit.restart;

		// ���ο� ���� ���� ���� �� ���� ���� �ʱ�ȭ
		gameInfo = new GameStartInfo();
		bgPlayerMode.clearSelection();
		bgToken.clearSelection();
		rbBlackToken.setVisible(false);
		rbWhiteToken.setVisible(false);
		btnNext.setVisible(false);

		// ���� ����� �� ���
		if (re == 0) {
			showGameSettingPage();// ���� ��� ���� ȭ������ �г� ����
		}
	}

	private class ButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// ���É� button �����ϱ�
			JButton obj = (JButton) e.getSource();

			// ���õ� button�� btnGameStart�� ���
			if (obj == btnGameStart) {
				// ȿ���� ����
				volume.buttonVolume();
				// ���� ��� ���� �гη� ����
				showGameSettingPage();
			}
			// ���õ� button�� Home�� ���
			else if (obj == btnHome1 || obj == btnHome2 || obj == btnHome3) {
				// ȿ���� ����
				volume.buttonVolume();
				// ���� ���� ���� ���� ����
				gameInfo = new GameStartInfo();

				// ���� ����Ǿ� �ִ� ���� �ʱ�ȭ
				rbBlackToken.setVisible(false);
				rbWhiteToken.setVisible(false);
				btnNext.setVisible(false);
				bgToken.clearSelection();
				bgPlayerMode.clearSelection();

				// ���� ȭ������ �̵�
				showInitPage();
			}
			// ���õ� button�� btnNext�� ���
			else if (obj == btnNext) {
				// ȿ���� ����
				volume.buttonVolume();
				// ĳ���� ���� �г� ����
				setCharacterSelectPanel();
				showChracterSelectPanel();
			}
			// ���õ� button�� manual �� ���
			else if (obj == btnManual || obj == manual_btn) {
				// ȿ���� ����
				volume.buttonVolume();
				// manual frame�� �������� ���� ���
				if (sf == null)
					sf = new ShowManual();// ���� ����
				else {// manual frame�� �̹� ������ ���
					sf.dispose();// ���� frame ���ֱ�
					sf = new ShowManual();// ���� ����
				}
			}
			// ���õ� button�� option_btn�� ���
			else if (obj == option_btn) {
				// ȿ���� ����
				volume.buttonVolume();
				// option frame�� ������ ���
				if (option != null)
					option.dispose();// ���� frame ���ֱ�
				option = new ShowOptions();// ���� ����
			}
			// ���õ� button�� exit_btn�� ���
			else if (obj == exit_btn) {
				// ȿ���� ����
				volume.buttonVolume();
				// exit dialog ����
				Exit.exitOrGameRestart();

			}
			// ���õ� button�� btnPlay�� ���
			else if (obj == btnPlay) {
				// ȿ���� ����
				volume.startVolume();

				// ���� ȭ�� �гη� ����
				setGamePlayPanel();
				showGamePlayPage();

				// ���� ���� ����
				gm = new GameManager();
				// ���� �����ǿ��� ��ġ ������ ��ġ������ �� ��������
				posPossible = GameManager.findPossiblePosition(gm.getGameCurrentBoard(),
						gm.currentTurnToken.getState());

				// computer�� ��ū ��������
				com = new Computer(-1 * gameInfo.getPlayer1().getMyToken().getState());

				// 1�� ����̰� player1�� ��ū�� �������� ���
				if (gameInfo.getGameMode() == 1 && gameInfo.getPlayer1().getMyToken().getState() == -1) {
					// computer�� ��ġ�� ��ġ ã��
					Point posIdx = com.searchBestPosition(gm.getGameCurrentBoard(), gm.currentTurnToken.getState(), 0,
							0);

					// ���� ���� ������ ��������
					GameBoard currentBoard = gm.getGameCurrentBoard();
					// ���� ������ update
					currentBoard.updateBoard(posIdx, gm.currentTurnToken.getState());
					// ���� ������ ��ū ���� ����
					gm.setCurrentToken(new Token(-1 * gm.currentTurnToken.getState()));

					// ���� ������ ��ū ���� update
					updateColorInfo(gm.currentTurnToken.getState());
					// ���� �������� �̹��� update
					updateGameBoardG();
					// player�� ���� ������Ʈ
					updateScore();
				}
			}
			// ���õ� button�� btnPlayAgain�� ���
			else if (obj == btnPlayAgain) {
				// ���ο� ���� ���� ���� ����
				gameInfo = new GameStartInfo();

				// ���� ����Ǿ� �ִ� ���� �ʱ�ȭ
				rbBlackToken.setVisible(false);
				rbWhiteToken.setVisible(false);
				btnNext.setVisible(false);
				bgToken.clearSelection();
				bgPlayerMode.clearSelection();

				// ���� ��� ���� �гη� ����
				showGameSettingPage();

				// ���� ��� frame ����
				winFrame.dispose();
			}
			// ���õ� button�� btnGameExit�� ���
			else if (obj == btnGameExit)
				// ���α׷� ����
				System.exit(0);

		}// actionPerformed()
	}// ButtonActionListener class

	private class RadioButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// ���õ� radio ����
			JRadioButton obj = (JRadioButton) e.getSource();

			// ���É� radio ��ư�� 1�� �÷��� ����� ���
			if (obj == rb1PlayerMode) {
				// ��ū �����ϴ� ��ư group �ʱ�ȭ �� �����ֱ�
				rbBlackToken.setSelected(false);
				rbWhiteToken.setSelected(false);
				rbBlackToken.setVisible(true);
				rbWhiteToken.setVisible(true);
				bgToken.clearSelection();
				// ���� �гη� �̵��ϴ� ��ư �������� �ʱ�
				btnNext.setVisible(false);
			}
			// ���õ� radio ��ư�� 2�� ����� ���
			else if (obj == rb2PlayerMode) {
				// ��ū �����ϴ� ��ư group �������� �ʱ�
				rbBlackToken.setVisible(false);
				rbWhiteToken.setVisible(false);
				// ���� �гη� �̵��ϴ� ��ư �����ֱ�
				btnNext.setVisible(true);
				// ���� ���� ������ 2�� ���� �����ϰ� player1�� ��ū ���� �ڵ����� ������ ����
				gameInfo.setGameStartInfo(2, new Token(0));
			}
			// ���õ� radio ��ư�� ������ ��ū�� ���
			else if (obj == rbBlackToken) {
				// ���� �гη� �̵��ϴ� ��ư �����ֱ�
				btnNext.setVisible(true);
				// ���� ���� ������ 1�� ���� �����ϰ� player1�� ��ū ���� ���������� ����
				gameInfo.setGameStartInfo(1, new Token(1));
			}
			// ���õ� radio ��ư�� ��� ��ū�� ���
			else if (obj == rbWhiteToken) {
				// ���� �гη� �̵��ϴ� ��ư �����ֱ�
				btnNext.setVisible(true);
				// ���� ���� ������ 1�� ���� �����ϰ� player1�� ��ū ���� ������� ����
				gameInfo.setGameStartInfo(1, new Token(-1));
			}
			// ���õ� radio ��ư�� player 1�� ĳ���ͷ� seal�� ������ ���
			else if (obj == rbWhale) {

				rbWhale.setIcon(new ImageIcon("images/seal_1.png"));// seal�� �̹����� ���ϰ� ǥ��
				rbWolf.setIcon(new ImageIcon("images/wolf_t.png"));
				rbSquirrel.setIcon(new ImageIcon("images/chipmunk_t.png"));
				rbShark.setIcon(new ImageIcon("images/monster_t.png"));

				// ���� ���� �������� player1�� ĳ���� 1�� ����
				gameInfo.setPlayer1(1);

				// 1�� ����� ���
				if (gameInfo.getGameMode() == 1) {
					gameInfo.setPlayer2(0);// player 2�� ĳ���� computer�� ����
					btnPlay.setVisible(true);// ����ȭ������ �̵��ϴ� ��ư �����ֱ�
				}
				// 2�� ����� ���
				else {
					// player 1�� player 2�� ĳ���� ���ð� ������ �Ϸ�� ���
					if (gameInfo.getPlayer1() != null && gameInfo.getPlayer1().getMyCharacter() != 0
							&& gameInfo.getPlayer2() != null && gameInfo.getPlayer2().getMyCharacter() != 0)
						btnPlay.setVisible(true);// ����ȭ������ �̵��ϴ� ��ư �����ֱ�
				}
			}
			// ���õ� radio ��ư�� player 1�� ĳ���ͷ� wolf�� ������ ���
			else if (obj == rbWolf) {

				rbWhale.setIcon(new ImageIcon("images/seal_t.png"));
				rbWolf.setIcon(new ImageIcon("images/wolf_1.png"));// wolf�� �̹����� ���ϰ� ǥ��
				rbSquirrel.setIcon(new ImageIcon("images/chipmunk_t.png"));
				rbShark.setIcon(new ImageIcon("images/monster_t.png"));

				// ���� ���� �������� player1�� ĳ���� 2�� ����
				gameInfo.setPlayer1(2);

				// 1�� ����� ���
				if (gameInfo.getGameMode() == 1) {
					gameInfo.setPlayer2(0);// player 2�� ĳ���� computer�� ����
					btnPlay.setVisible(true);// ����ȭ������ �̵��ϴ� ��ư �����ֱ�
				}
				// 2�� ����� ���
				else {
					// player 1�� player 2�� ĳ���� ���ð� ������ �Ϸ�� ���
					if (gameInfo.getPlayer1() != null && gameInfo.getPlayer1().getMyCharacter() != 0
							&& gameInfo.getPlayer2() != null && gameInfo.getPlayer2().getMyCharacter() != 0)
						btnPlay.setVisible(true);// ����ȭ������ �̵��ϴ� ��ư �����ֱ�
				}
			}
			// ���õ� radio ��ư�� player 1�� ĳ���ͷ� squirrel�� ������ ���
			else if (obj == rbSquirrel) {

				rbWhale.setIcon(new ImageIcon("images/seal_t.png"));
				rbWolf.setIcon(new ImageIcon("images/wolf_t.png"));
				rbSquirrel.setIcon(new ImageIcon("images/chipmunk_1.png"));// squirrel�� �̹����� ���ϰ� ǥ��
				rbShark.setIcon(new ImageIcon("images/monster_t.png"));

				// ���� ���� �������� player1�� ĳ���� 3�� ����
				gameInfo.setPlayer1(3);

				// 1�� ����� ���
				if (gameInfo.getGameMode() == 1) {
					gameInfo.setPlayer2(0);// player 2�� ĳ���� computer�� ����
					btnPlay.setVisible(true);// ����ȭ������ �̵��ϴ� ��ư �����ֱ�
				}
				// 2�� ����� ���
				else {
					// player 1�� player 2�� ĳ���� ���ð� ������ �Ϸ�� ���
					if (gameInfo.getPlayer1() != null && gameInfo.getPlayer1().getMyCharacter() != 0
							&& gameInfo.getPlayer2() != null && gameInfo.getPlayer2().getMyCharacter() != 0)
						btnPlay.setVisible(true);// ����ȭ������ �̵��ϴ� ��ư �����ֱ�
				}
			}
			// ���õ� radio ��ư�� player 1�� ĳ���ͷ� monster�� ������ ���
			else if (obj == rbShark) {

				rbWhale.setIcon(new ImageIcon("images/seal_t.png"));
				rbWolf.setIcon(new ImageIcon("images/wolf_t.png"));
				rbSquirrel.setIcon(new ImageIcon("images/chipmunk_t.png"));
				rbShark.setIcon(new ImageIcon("images/monster_1.png"));// monster�� �̹����� ���ϰ� ǥ��

				// ���� ���� �������� player1�� ĳ���� 4�� ����
				gameInfo.setPlayer1(4);

				// 1�� ����� ���
				if (gameInfo.getGameMode() == 1) {
					gameInfo.setPlayer2(0);// player 2�� ĳ���� computer�� ����
					btnPlay.setVisible(true);// ����ȭ������ �̵��ϴ� ��ư �����ֱ�
				}
				// 2�� ����� ���
				else {
					// player 1�� player 2�� ĳ���� ���ð� ������ �Ϸ�� ���
					if (gameInfo.getPlayer1() != null && gameInfo.getPlayer1().getMyCharacter() != 0
							&& gameInfo.getPlayer2() != null && gameInfo.getPlayer2().getMyCharacter() != 0)
						btnPlay.setVisible(true);// ����ȭ������ �̵��ϴ� ��ư �����ֱ�
				}
			}
			// ���õ� radio ��ư�� player 2�� ĳ���ͷ� seal�� ������ ���
			else if (obj == rbWhale2) {

				rbWhale2.setIcon(new ImageIcon("images/seal_1.png"));// seal�� �̹����� ���ϰ� ǥ��
				rbWolf2.setIcon(new ImageIcon("images/wolf_t.png"));
				rbSquirrel2.setIcon(new ImageIcon("images/chipmunk_t.png"));
				rbShark2.setIcon(new ImageIcon("images/monster_t.png"));

				// ���� ���� �������� player2�� ĳ���� 1�� ����
				gameInfo.setPlayer2(1);

				// player 1�� player 2�� ĳ���� ���ð� ������ �Ϸ�� ���
				if (gameInfo.getPlayer1() != null && gameInfo.getPlayer1().getMyCharacter() != 0
						&& gameInfo.getPlayer2() != null && gameInfo.getPlayer2().getMyCharacter() != 0)
					btnPlay.setVisible(true);// ����ȭ������ �̵��ϴ� ��ư �����ֱ�
			}
			// ���õ� radio ��ư�� player 2�� ĳ���ͷ� wolf�� ������ ���
			else if (obj == rbWolf2) {

				rbWhale2.setIcon(new ImageIcon("images/seal_t.png"));
				rbWolf2.setIcon(new ImageIcon("images/wolf_1.png"));// wolf�� �̹����� ���ϰ� ǥ��
				rbSquirrel2.setIcon(new ImageIcon("images/chipmunk_t.png"));
				rbShark2.setIcon(new ImageIcon("images/monster_t.png"));

				// ���� ���� �������� player2�� ĳ���� 2�� ����
				gameInfo.setPlayer2(2);

				// player 1�� player 2�� ĳ���� ���ð� ������ �Ϸ�� ���
				if (gameInfo.getPlayer1() != null && gameInfo.getPlayer1().getMyCharacter() != 0
						&& gameInfo.getPlayer2() != null && gameInfo.getPlayer2().getMyCharacter() != 0)
					btnPlay.setVisible(true);// ����ȭ������ �̵��ϴ� ��ư �����ֱ�
			}
			// ���õ� radio ��ư�� player 2�� ĳ���ͷ� squirrel�� ������ ���
			else if (obj == rbSquirrel2) {

				rbWhale2.setIcon(new ImageIcon("images/seal_t.png"));
				rbWolf2.setIcon(new ImageIcon("images/wolf_t.png"));
				rbSquirrel2.setIcon(new ImageIcon("images/chipmunk_1.png"));// squirrel�� �̹����� ���ϰ� ǥ��
				rbShark2.setIcon(new ImageIcon("images/monster_t.png"));

				// ���� ���� �������� player2�� ĳ���� 3�� ����
				gameInfo.setPlayer2(3);

				// player 1�� player 2�� ĳ���� ���ð� ������ �Ϸ�� ���
				if (gameInfo.getPlayer1() != null && gameInfo.getPlayer1().getMyCharacter() != 0
						&& gameInfo.getPlayer2() != null && gameInfo.getPlayer2().getMyCharacter() != 0)
					btnPlay.setVisible(true);// ����ȭ������ �̵��ϴ� ��ư �����ֱ�
			}
			// ���õ� radio ��ư�� player 2�� ĳ���ͷ� monster�� ������ ���
			else if (obj == rbShark2) {

				rbWhale2.setIcon(new ImageIcon("images/seal_t.png"));
				rbWolf2.setIcon(new ImageIcon("images/wolf_t.png"));
				rbSquirrel2.setIcon(new ImageIcon("images/chipmunk_t.png"));
				rbShark2.setIcon(new ImageIcon("images/monster_1.png"));// monster�� �̹����� ���ϰ� ǥ��

				// ���� ���� �������� player2�� ĳ���� 4�� ����
				gameInfo.setPlayer2(4);

				// player 1�� player 2�� ĳ���� ���ð� ������ �Ϸ�� ���
				if (gameInfo.getPlayer1() != null && gameInfo.getPlayer1().getMyCharacter() != 0
						&& gameInfo.getPlayer2() != null && gameInfo.getPlayer2().getMyCharacter() != 0)
					btnPlay.setVisible(true);// ����ȭ������ �̵��ϴ� ��ư �����ֱ�
			}
		}// actionPerformed()

	}// RadioButtonListener class

	private class ClickListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// ��ġ ��������
			Point p = e.getPoint();

			// ȿ���� ����
			volume.buttonVolume();

			// ���� ��尡 1�� �ϰ��
			if (gameInfo.getGameMode() == 1) {
				Token playerToken = gameInfo.getPlayer1().getMyToken();// player1�� ��ū ��������

				// �ڽ��� ������ ���
				if (gm.currentTurnToken.getState() == playerToken.getState()) {
					// ���� ��ġ�� ������ �� ��ġ�� ��ȯ
					Point posIdx = gameInfo.getPlayer1().convertPosToIdx(p);

					// ������ �� ��ġ�� ���
					if (GameManager.isRange(posIdx.x, posIdx.y)) {

						// ���� ������ ȣ���ϱ�
						GameBoard currentBoard = gm.getGameCurrentBoard();

						// ���� �����ǿ��� ��ġ������ ��ġ�� ��������
						posPossible = GameManager.findPossiblePosition(currentBoard, playerToken.getState());

						// ��ġ������ ��ġ�� �ƴ� ���
						if (posPossible[posIdx.y][posIdx.x] == false)
							ErrorManager.positionError(mainFrame);

						// ��ġ������ ��ġ�� ���
						else {

							// skipȽ��
							gm.successiveCntSkip = 0;

							// ���� ������ update
							currentBoard.updateBoard(posIdx, playerToken.getState());

							// ���� player���� ���� �Ѱ��ֱ�
							gm.setCurrentToken(new Token(-1 * (gm.currentTurnToken.getState())));

							// ��ġ������ ��ġ�� ��������
							posPossible = new boolean[8][8];

							// ������ �̹��� update
							updateGameBoardG();
							// ���� update
							updateScore();
							// ���� ������ ��ū ���� update
							updateColorInfo(gm.currentTurnToken.getState());

							// skip �߻��� ���
							if (gm.canSkip()) {
								// skip�� 2ȸ �̻� �߻� �Ǵ� �������� á�� ���
								if (gm.isFull() || gm.successiveCntSkip >= 2) {
									showWinPage();// ���� ���� ó�� ����ߵ�
								}
								// �׿��� ���
								else {
									// ���� ��ū�� ���� ����
									gm.setCurrentToken(new Token(-1 * gm.currentTurnToken.getState()));

									// �������� ���� ��á�� ���
									if (!gm.isFull())
										ErrorManager.skipMsg(mainFrame, gm.currentTurnToken.getState());// skip message
																										// �����ֱ�

									// ���� ������ ��ū ���� update
									updateColorInfo(gm.currentTurnToken.getState());
									// ���� update
									updateScore();
									// ������ �̹��� update
									updateGameBoardG();

									// skip �� �߻��� ���
									if (gm.canSkip())
										showWinPage();// ���� ���� ó�� ����ߵ�
								}
							}
						}
					}
					// ��ġ error message ���
					else {
						ErrorManager.positionError(mainFrame);
					}
				}
			} else { // 2�� ����ϰ��
				int currentColor = gm.currentTurnToken.getState();

				Player currentPlayer;

				// � ��ū�� ���� �÷��̾����� ����
				if (gameInfo.getPlayer1().getMyToken().getState() == currentColor)
					currentPlayer = gameInfo.getPlayer1();
				else
					currentPlayer = gameInfo.getPlayer2();

				// posIdx�� ��ǥ�� �ε����� ��ȯ
				Point posIdx = currentPlayer.convertPosToIdx(p);

				// ���� ������ ȣ���ϱ�
				GameBoard currentBoard = gm.getGameCurrentBoard();

				// ���� �����ǿ��� ��ġ������ ��ġ�� ��������
				posPossible = GameManager.findPossiblePosition(currentBoard, currentColor);

				// ������ �� ��ġ�� ���
				if (GameManager.isRange(posIdx.x, posIdx.y)) {

					// ��ġ������ ��ġ�� �ƴ� ���
					if (posPossible[posIdx.y][posIdx.x] == false)
						ErrorManager.positionError(mainFrame);// error message ����ϱ�
					// ��ġ������ ��ġ�� ���
					else {
						// skip�߻� Ƚ��
						gm.successiveCntSkip = 0;
						// ���� ������ update
						currentBoard.updateBoard(posIdx, currentColor);
						// ���� ��ū�� ���� ����
						gm.setCurrentToken(new Token(-1 * (currentColor)));

						// ���� ������ ��ū ���� update
						updateColorInfo(gm.currentTurnToken.getState());
						// ���� update
						updateScore();
						// ������ �̹��� update
						updateGameBoardG();

						// skip �߻��� ���
						if (gm.canSkip()) {
							// skip�� 2ȸ �̻� �߻� �Ǵ� �������� á�� ���
							if (gm.isFull() || gm.successiveCntSkip >= 2) {
								showWinPage();// ���� ���� ó�� ����ߵ�
							}
							// �׿��� ���
							else {
								// ���� ��ū�� ���� ����
								gm.setCurrentToken(new Token(-1 * gm.currentTurnToken.getState()));

								// skip �� �߻��� ���
								if (gm.canSkip()) {
									showWinPage();// ���� ���� ó�� ����ߵ�
								}
								// �׿��� ���
								else {
									// �������� ���� ��á�� ���
									if (!gm.isFull())
										ErrorManager.skipMsg(mainFrame, gm.currentTurnToken.getState());// skip message
																										// �����ֱ�

									// ���� ������ ��ū ���� update
									updateColorInfo(gm.currentTurnToken.getState());
									// ���� update
									updateScore();
									// ������ �̹��� update
									updateGameBoardG();
								}
							}
						}
					}
				}
				// error message ���
				else {
					ErrorManager.positionError(mainFrame);
				}
			}
		}// mousePressed()

		@Override
		public void mouseReleased(MouseEvent e) {
			// ���� ��尡 1�� ����̰� ��ǻ�� ������ ���
			if (gameInfo.getGameMode() == 1 && gm.currentTurnToken.getState() == com.getMyToken().getState()) {
				// skip�� �߻��� ���
				if (gm.canSkip()) {
					gm.setCurrentToken(new Token(-1 * gm.currentTurnToken.getState()));// ���� ������ ��ū ���� update

					// �������� ���� �ʾ��� ���
					if (!gm.isFull())
						ErrorManager.skipMsg(mainFrame, gm.currentTurnToken.getState());// skip message �����ֱ�

					// ���� ������ ��ū ���� update
					updateColorInfo(gm.currentTurnToken.getState());
					// ������ �̹��� update
					updateGameBoardG();
				}
				// �� ���� ���
				else {
					// skip �߻� Ƚ��
					gm.successiveCntSkip = 0;

					// computer�� ��ġ�� �� �ִ� ������ ��ġ ã��
					Point posIdx = com.searchBestPosition(gm.getGameCurrentBoard(), gm.currentTurnToken.getState(), 0,
							0);

					// thread ������Ű��
					ts.run();

					// ���� ���� ������ ��������
					GameBoard currentBoard = gm.getGameCurrentBoard();

					// ���� ���� ������ update
					currentBoard.updateBoard(posIdx, gm.currentTurnToken.getState());
					// ���� ��ū�� ���� update
					gm.setCurrentToken(new Token(-1 * gm.currentTurnToken.getState()));

					// ���� ������ ��ū ���� update
					updateColorInfo(gm.currentTurnToken.getState());
					// ���� update
					updateScore();
					// ������ �̹��� update
					updateGameBoardG();

					// skip�� �߻��ϴ� ����
					while (gm.canSkip()) {
						// ���� ��ū�� ���� ����
						gm.setCurrentToken(new Token(-1 * gm.currentTurnToken.getState()));

						// skip�� 2ȸ �̻� �߻��� ����̰ų� �������� á�� ���
						if (gm.successiveCntSkip >= 2 || gm.isFull()) {
							showWinPage();// ���� ���� ó�� ����ߵ�
							break;
						}
						// �׿��� ���
						else {
							// �������� �� á�� ���
							if (!gm.isFull()) {
								ErrorManager.skipMsg(mainFrame, gm.currentTurnToken.getState());// skip message �����ֱ�
								// thread ������Ű��
								ts.run();

								// computer�� ��ġ�� �� �ִ� ������ ��ġ ã��
								posIdx = com.searchBestPosition(gm.getGameCurrentBoard(),
										gm.currentTurnToken.getState(), 0, 0);

								// ��ġ ������ ��ġ�� ���
								if (gm.isPossilbe(posIdx)) {
									// ���� ������ ��������
									currentBoard = gm.getGameCurrentBoard();
									// ������ update
									currentBoard.updateBoard(posIdx, gm.currentTurnToken.getState());

									// skipȽ�� �ʱ�ȭ
									gm.successiveCntSkip = 0;
								}

								// ���� ��ū ���� ����
								gm.setCurrentToken(new Token(-1 * gm.currentTurnToken.getState()));

								// ���� ������ ��ū ���� update
								updateColorInfo(gm.currentTurnToken.getState());
								// ���� update
								updateScore();
								// ������ �̹��� update
								updateGameBoardG();
							}
						}
					}
				}
			}
		}// mouseReleased()

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	}// ClickListener class

	// main method
	public static void main(String[] args) {
		Graphic g = new Graphic();
	}
}
