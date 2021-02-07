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
	public final Color BUTTONCOLOR = new Color(45, 175, 90);// 버튼의 색상
	public static JFrame mainFrame, winFrame;// 주요 frame 과 결과 frame
	public static JPanel initPanel, gameSettingPanel;
	private JPanel characterSelectPanel, winPanel, gamePlayPanel;
	private JLabel lblImage, lblPlayer1, lblPlayer2, colorInfo, lblSubTitle, lbl1, lbl2;
	private JButton btnGameStart, btnManual, btnPlay, btnHome1, btnHome2, btnHome3, btnPlayAgain, btnGameExit;
	public static JButton btnNext;
	public static JRadioButton rb1PlayerMode, rb2PlayerMode, rbBlackToken, rbWhiteToken;
	private JRadioButton rbWhale, rbWolf, rbSquirrel, rbShark;// player1의 캐릭터
	private JRadioButton rbWhale2, rbWolf2, rbSquirrel2, rbShark2;// player2의 캐릭터
	public static ButtonGroup bgPlayerMode, bgToken, bgPlayer1, bgPlayer2;// button group
	private ButtonActionListener buttonL;
	private RadioButtonListener RadioButtonL;
	public static GameStartInfo gameInfo;// 게임 정보 저장
	private JButton manual_btn;// 게임 페이지 위에 있는 매뉴얼 버튼
	private JButton option_btn;// 게임 페이지 위에 있는 옵션 버튼
	private JButton exit_btn;// 게임 페이지 위에 있는 나가기 버튼(임시)
	private ShowManual sf;// manual 패널
	private ShowOptions option;// option 패널
	private BevelBorder border;// 테두리 보더 효과
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

	Image imgboard;// 보드판 이미지
	Image img, img2, img3;// 배경화면
	ImageIcon imageB = new ImageIcon("images/b_place.png");// 검정 토큰 이미지
	ImageIcon imageW = new ImageIcon("images/w_place.png");// 흰색 토큰 이미지
	ImageIcon imageBS = new ImageIcon("images/B_shadow.png");// 검정 토큰 음영 이미지
	ImageIcon imageWS = new ImageIcon("images/W_shadow.png");// 흰색 토큰 음영 이미지

	String[] filePath1 = new String[6];// seal
	String[] filePath2 = new String[6];// wolf
	String[] filePath3 = new String[6];// chipmunk
	String[] filePath4 = new String[6];// monster
	String[] filePath0 = new String[6];// computer
	ImageIcon icon = new ImageIcon("images/OthelloIcon3.png");

	public Graphic() {
		// seal 캐릭터의 이미지 경로 배열
		filePath1[0] = "images/seal_1.png";
		filePath1[1] = "images/seal_2.png";
		filePath1[2] = "images/seal_3.png";
		filePath1[3] = "images/seal_1(1).png";
		filePath1[4] = "images/seal_2(1).png";
		filePath1[5] = "images/seal_3(1).png";

		// wolf 캐릭터의 이미지 경로 배열
		filePath2[0] = "images/wolf_1.png";
		filePath2[1] = "images/wolf_2.png";
		filePath2[2] = "images/wolf_3.png";
		filePath2[3] = "images/wolf_1(1).png";
		filePath2[4] = "images/wolf_2(1).png";
		filePath2[5] = "images/wolf_3(1).png";

		// chipmunk 캐릭터의 이미지 경로 배열
		filePath3[0] = "images/chipmunk_1.png";
		filePath3[1] = "images/chipmunk_2.png";
		filePath3[2] = "images/chipmunk_3.png";
		filePath3[3] = "images/chipmunk_1(1).png";
		filePath3[4] = "images/chipmunk_2(1).png";
		filePath3[5] = "images/chipmunk_3(1).png";

		// monster 캐릭터의 이미지 경로 배열
		filePath4[0] = "images/monster_1.png";
		filePath4[1] = "images/monster_2.png";
		filePath4[2] = "images/monster_3.png";
		filePath4[3] = "images/monster_1(1).png";
		filePath4[4] = "images/monster_2(1).png";
		filePath4[5] = "images/monster_3(1).png";

		// dragon 캐릭터의 이미지 경로 배열
		filePath0[0] = "images/dragon_1.png";
		filePath0[1] = "images/dragon_2.png";
		filePath0[2] = "images/dragon_3.png";
		filePath0[3] = "images/dragon_1(1).png";
		filePath0[4] = "images/dragon_2(1).png";
		filePath0[5] = "images/dragon_3(1).png";

		// 테두리 입체 효과
		border = new BevelBorder(BevelBorder.RAISED);
		// thread지연
		ts = new TimeStop();

		// frame설정
		mainFrame = new JFrame("Othello Game");
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(new Dimension(1000, 700));
		mainFrame.setLocationRelativeTo(null);

		// button리스너 생성
		buttonL = new ButtonActionListener();// 초기 화면 버튼
		RadioButtonL = new RadioButtonListener();// 게임 모드 화면 버튼
		clickL = new ClickListener();// 게임 화면 버튼

		// 같은 형태의 버튼 생성 및 반환
		btnHome1 = getButtonHome();
		btnHome2 = getButtonHome();
		btnHome3 = getButtonHome();

		gameInfo = new GameStartInfo();// 게임 정보 생성

		setInitPanel();// 초기화면 패널

		setGameSettingPanel();// 게임 모드 선택 화면

		showInitPage();// 초기화면 보여주기

		mainFrame.setVisible(true);
		play.playBackgroundSound1();// 배경음악
	}

	// 해당되는 패널로 화면 설정
	private void mySetPanel(JPanel p) {
		p.setPreferredSize(new Dimension(1000, 700));
		p.setLayout(null);
	}

	// 제목 라벨 생성 및 반환
	private JLabel getLabelLogo() {
		JLabel lblOthelloGame = new JLabel("OTHELLO GAME");
		lblOthelloGame.setForeground(Color.white);
		lblOthelloGame.setBounds(30, 80, 940, 80);
		lblOthelloGame.setFont(new Font("Felix Titling", Font.BOLD, 53));
		lblOthelloGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblOthelloGame.setVisible(true);

		return lblOthelloGame;
	}

	// Home버튼 생성 및 반환
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

	// 프로그램 시작 화면(초기화면)
	private void setInitPanel() {

		// 이미지 파일 읽어온 후
		try {
			img = ImageIO.read(new File("./images/bg1.jpg"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		// 패널 생성 및 설정
		initPanel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				g.drawImage(img, 0, 0, 1000, 700, null);
			}
		};

		mySetPanel(initPanel);

		initPanel.add(getLabelLogo());

		// 제목 라벨 설정
		lblImage = new JLabel("", icon, SwingConstants.CENTER);
		lblImage.setVerticalAlignment(SwingConstants.CENTER); // 가로로 가운데 정렬
		lblImage.setHorizontalAlignment(SwingConstants.CENTER); // 세로로 가운데 정렬
		lblImage.setBounds(30, 100, 940, 200);
		lblImage.setVisible(false);
		initPanel.add(lblImage);

		// 부제목 라벨 설정
		lblSubTitle = new JLabel("No Pain No Game");
		lblSubTitle.setFont(new Font("Felix Titling", Font.BOLD, 30));
		lblSubTitle.setVerticalAlignment(SwingConstants.CENTER); // 가로로 가운데 정렬
		lblSubTitle.setHorizontalAlignment(SwingConstants.CENTER); // 세로로 수평에 정렬
		lblSubTitle.setBounds(100, 170, 800, 50);
		lblSubTitle.setForeground(Color.white);
		initPanel.add(lblSubTitle);

		// 게임 시작 버튼 설정
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

		// manual 버튼 설정
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

	// 게임 모드 선택 패널
	private void setGameSettingPanel() {
		// 배경화면 이미지 경로 읽어오기
		try {
			img2 = ImageIO.read(new File("./images/bg2.jpg"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		// 패널 생성 및 설정
		gameSettingPanel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				g.drawImage(img2, 0, 0, 1000, 700, null);
				setOpaque(false);
			}

		};

		// 게임 모드 선택 패널로 설정
		mySetPanel(gameSettingPanel);
		gameSettingPanel.add(btnHome1);// home버튼 추가
		gameSettingPanel.add(getLabelLogo());// 제목 레이블 추가

		// next버튼 설정
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

		// 1인 플레이 모드 체크박스
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

		// 2인 플레이 모드 체크박스
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

		// 플레이 모드 grouping
		bgPlayerMode = new ButtonGroup();
		bgPlayerMode.add(rb1PlayerMode);
		bgPlayerMode.add(rb2PlayerMode);

		// 1인 모드일 때 토큰 색상 정하는 체크박스(검정 토큰)
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

		// 1인 모드일 때 토큰 색상 정하는 체크박스(흰색 토큰)
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

		// 검정 토큰과 흰 토큰 색상 grouping
		bgToken = new ButtonGroup();
		bgToken.add(rbBlackToken);
		bgToken.add(rbWhiteToken);
	}

	// 캐릭터 정하는 패널(캐릭터 정하는 화면)
	private void setCharacterSelectPanel() {

		// 배경화면
		try {
			img3 = ImageIO.read(new File("./images/bg3.jpg"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		// 패널 생성 및 설정
		characterSelectPanel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				g.drawImage(img3, 0, 0, 1000, 700, null);
			}
		};

		// 패널 화면 설정
		mySetPanel(characterSelectPanel);
		characterSelectPanel.add(btnHome2);// home 버튼
		characterSelectPanel.add(getLabelLogo());// 제목 레이블 추가

		String color = null;// 토큰 색상 알려주는 레이블

		if (gameInfo.getGameMode() == 1) {// 1인 모드일 경우
			if (gameInfo.getPlayer1().getMyToken().getState() == 1)// 사용자의 토큰 색상이 검정 토큰일 경우
				color = "Black";
			else// 사용자의 토큰 색상이 흰 토큰일 경우
				color = "White";
		} else// 2인 모드일 경우
			color = "Black";

		// 1번째 플레이어
		lblPlayer1 = new JLabel("Player1 (" + color + ")" + " : ");// 토큰 색상 옆에 표시해주기
		lblPlayer1.setBounds(150, 170, 500, 30);
		lblPlayer1.setFont(new Font("Felix Titling", Font.BOLD, 20));
		lblPlayer1.setForeground(Color.white);
		lblPlayer1.setVerticalAlignment(SwingConstants.CENTER);
		lblPlayer1.setHorizontalAlignment(SwingConstants.LEFT);
		lblPlayer1.setVisible(true);
		characterSelectPanel.add(lblPlayer1);

		// seal 캐릭터 설정
		rbWhale = new JRadioButton(new ImageIcon("images/seal_t.png"));
		rbWhale.setForeground(Color.black);
		rbWhale.setVerticalAlignment(SwingConstants.CENTER);
		rbWhale.setHorizontalAlignment(SwingConstants.CENTER);
		rbWhale.setBounds(200, 220, 119, 120);
		rbWhale.setVisible(true);
		rbWhale.addActionListener(RadioButtonL);
		rbWhale.setOpaque(false);
		characterSelectPanel.add(rbWhale);

		// wolf 캐릭터 설정
		rbWolf = new JRadioButton(new ImageIcon("images/wolf_t.png"));
		rbWolf.setForeground(Color.black);
		rbWolf.setVerticalAlignment(SwingConstants.CENTER);
		rbWolf.setHorizontalAlignment(SwingConstants.CENTER);
		rbWolf.setBounds(360, 220, 119, 120);
		rbWolf.setVisible(true);
		rbWolf.addActionListener(RadioButtonL);
		rbWolf.setOpaque(false);
		characterSelectPanel.add(rbWolf);

		// squirrel 캐릭터 설정
		rbSquirrel = new JRadioButton(new ImageIcon("images/chipmunk_t.png"));
		rbSquirrel.setForeground(Color.black);
		rbSquirrel.setVerticalAlignment(SwingConstants.CENTER);
		rbSquirrel.setHorizontalAlignment(SwingConstants.CENTER);
		rbSquirrel.setBounds(520, 220, 119, 120);
		rbSquirrel.setVisible(true);
		rbSquirrel.addActionListener(RadioButtonL);
		rbSquirrel.setOpaque(false);
		characterSelectPanel.add(rbSquirrel);

		// monster 캐릭터 설정
		rbShark = new JRadioButton(new ImageIcon("images/monster_t.png"));
		rbShark.setForeground(Color.black);
		rbShark.setVerticalAlignment(SwingConstants.CENTER);
		rbShark.setHorizontalAlignment(SwingConstants.CENTER);
		rbShark.setBounds(680, 220, 119, 120);
		rbShark.setVisible(true);
		rbShark.addActionListener(RadioButtonL);
		rbShark.setOpaque(false);
		characterSelectPanel.add(rbShark);

		// 캐릭터들 grouping
		bgPlayer1 = new ButtonGroup();
		bgPlayer1.add(rbWhale);
		bgPlayer1.add(rbWolf);
		bgPlayer1.add(rbSquirrel);
		bgPlayer1.add(rbShark);

		// 2인 모드일 경우
		if (gameInfo.getGameMode() == 2) {
			// 2번째 플레이어
			lblPlayer2 = new JLabel("Player2 (White) : ");
			lblPlayer2.setBounds(150, 370, 500, 30);
			lblPlayer2.setFont(new Font("Felix Titling", Font.BOLD, 20));
			lblPlayer2.setForeground(Color.white);
			lblPlayer2.setVerticalAlignment(SwingConstants.CENTER);
			lblPlayer2.setHorizontalAlignment(SwingConstants.LEFT);
			lblPlayer2.setVisible(true);
			characterSelectPanel.add(lblPlayer2);

			// seal 캐릭터 설정
			rbWhale2 = new JRadioButton(new ImageIcon("images/seal_t.png"));
			rbWhale2.setForeground(Color.black);
			rbWhale2.setVerticalAlignment(SwingConstants.CENTER);
			rbWhale2.setHorizontalAlignment(SwingConstants.CENTER);
			rbWhale2.setBounds(200, 420, 119, 120);
			rbWhale2.setVisible(true);
			rbWhale2.addActionListener(RadioButtonL);
			rbWhale2.setOpaque(false);
			characterSelectPanel.add(rbWhale2);

			// wolf 캐릭터 설정
			rbWolf2 = new JRadioButton(new ImageIcon("images/wolf_t.png"));
			rbWolf2.setForeground(Color.black);
			rbWolf2.setVerticalAlignment(SwingConstants.CENTER);
			rbWolf2.setHorizontalAlignment(SwingConstants.CENTER);
			rbWolf2.setBounds(360, 420, 119, 120);
			rbWolf2.setVisible(true);
			rbWolf2.addActionListener(RadioButtonL);
			rbWolf2.setOpaque(false);
			characterSelectPanel.add(rbWolf2);

			// squirrel 캐릭터 설정
			rbSquirrel2 = new JRadioButton(new ImageIcon("images/chipmunk_t.png"));
			rbSquirrel2.setForeground(Color.black);
			rbSquirrel2.setVerticalAlignment(SwingConstants.CENTER);
			rbSquirrel2.setHorizontalAlignment(SwingConstants.CENTER);
			rbSquirrel2.setBounds(520, 420, 119, 120);
			rbSquirrel2.setVisible(true);
			rbSquirrel2.addActionListener(RadioButtonL);
			rbSquirrel2.setOpaque(false);
			characterSelectPanel.add(rbSquirrel2);

			// monster 캐릭터 설정
			rbShark2 = new JRadioButton(new ImageIcon("images/monster_t.png"));
			rbShark2.setForeground(Color.black);
			rbShark2.setVerticalAlignment(SwingConstants.CENTER);
			rbShark2.setHorizontalAlignment(SwingConstants.CENTER);
			rbShark2.setBounds(680, 420, 119, 120);
			rbShark2.setVisible(true);
			rbShark2.addActionListener(RadioButtonL);
			rbShark2.setOpaque(false);
			characterSelectPanel.add(rbShark2);

			// 캐릭터들 grouping
			bgPlayer2 = new ButtonGroup();
			bgPlayer2.add(rbWhale2);
			bgPlayer2.add(rbWolf2);
			bgPlayer2.add(rbSquirrel2);
			bgPlayer2.add(rbShark2);
		}

		// Play 버튼 설정
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

	// 게임화면의 배경화면
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

	// 게임화면 내 구성요소
	private void setGamePlayPanel() {

		// 게임의 배경화면
		gamePlayPanel = new GamePlayPanel();

		// 게임보드판 이미지
		board_panel = new Board();
		board_panel.addMouseListener(clickL);
		board_panel.setBounds(250, 130, 500, 500);
		board_panel.setVisible(true);
		gamePlayPanel.add(board_panel);

		ImageIcon imgManual = new ImageIcon("images/Manual.png");// manual 버튼 이미지
		ImageIcon imgOption = new ImageIcon("images/option.png");// option 버튼 이미지
		ImageIcon imgExit = new ImageIcon("images/exitdoor.png");// exit 버튼 이미지

		// manual 버튼 설정
		manual_btn = new JButton(imgManual);
		manual_btn.setBounds(50, 40, 50, 60);
		manual_btn.setOpaque(false);
		manual_btn.setBorderPainted(false);
		manual_btn.setBackground(new Color(168, 211, 142));
		manual_btn.addActionListener(buttonL);
		gamePlayPanel.add(manual_btn);

		// 현재 차례의 토큰 색상 표시하는 라벨 설정
		colorInfo = new JLabel("Black Turn");
		colorInfo.setFont(new Font("Centaur", Font.BOLD, 30));
		colorInfo.setForeground(Color.black);
		colorInfo.setBounds(400, 100, 200, 50);// 11.23
		colorInfo.setHorizontalAlignment(SwingConstants.CENTER);
		colorInfo.setVerticalAlignment(SwingConstants.CENTER);
		gamePlayPanel.add(colorInfo);

		// option 버튼 설정
		option_btn = new JButton(imgOption);
		option_btn.setBounds(850, 40, 50, 50);
		option_btn.setOpaque(false);
		option_btn.setBorderPainted(false);
		option_btn.setBackground(new Color(168, 211, 142));
		option_btn.addActionListener(buttonL);
		gamePlayPanel.add(option_btn);

		// exit 버튼 설정
		exit_btn = new JButton(imgExit);
		exit_btn.setBounds(920, 40, 50, 50);
		exit_btn.setOpaque(false);
		exit_btn.setBorderPainted(false);
		exit_btn.setBackground(new Color(168, 211, 142));
		exit_btn.addActionListener(buttonL);
		gamePlayPanel.add(exit_btn);

		// 플레이어들의 게임 정보
		int p1, p2;
		int t1, t2;
		int mode;
		JLabel Token1;
		JLabel Token2;
		ImageIcon imgtoken1, imgtoken2;
		JLabel ply1, ply2;// player 1,w
		JLabel tt;// 제목

		// 게임 제목 라벨
		tt = new JLabel("OTHELLO GAME");
		tt.setForeground(Color.black);
		tt.setBounds(30, 20, 940, 80);
		tt.setFont(new Font("Felix Titling", Font.BOLD, 40));
		tt.setHorizontalAlignment(SwingConstants.CENTER);
		tt.setVisible(true);
		gamePlayPanel.add(tt);

		// player 1 라벨 설정
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

		// player 2 라벨 설정
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

		// player 1의 점수 라벨 설정
		scr1 = new JLabel("2");
		scr1.setBounds(45, 420, 150, 90);
		scr1.setOpaque(false);
		scr1.setForeground(Color.black);
		scr1.setFont(new Font("Centaur", Font.BOLD, 60));
		scr1.setVerticalAlignment(SwingConstants.CENTER);
		scr1.setHorizontalAlignment(SwingConstants.CENTER);
		scr1.setVisible(true);
		gamePlayPanel.add(scr1);

		// player 2의 점수 라벨 설정
		scr2 = new JLabel("2");
		scr2.setBounds(810, 420, 150, 90);
		scr2.setOpaque(false);
		scr2.setForeground(Color.black);
		scr2.setFont(new Font("Centaur", Font.BOLD, 60));
		scr2.setVerticalAlignment(SwingConstants.CENTER);
		scr2.setHorizontalAlignment(SwingConstants.CENTER);
		scr2.setVisible(true);
		gamePlayPanel.add(scr2);

		mode = gameInfo.getGameMode();// 현재 게임 모드
		p1 = gameInfo.getPlayer1().getMyCharacter();// player 1의 캐릭터
		p2 = gameInfo.getPlayer2().getMyCharacter();// player 2의 캐릭터

		t1 = gameInfo.getPlayer1().getMyToken().getState();// player 1의 토큰 색상
		t2 = gameInfo.getPlayer2().getMyToken().getState();// player 2의 토큰 색상

		// 1인 모드
		if (mode == 1) {
			// 각 player의 토큰 색상
			if (t1 == 1) {
				// player 1의 토큰 색상이 검은색
				imgtoken1 = new ImageIcon("images/B.png");
				Token1 = new JLabel("", imgtoken1, SwingConstants.CENTER);
				Token1.setBounds(110, 290, 120, 120);
				Token1.setVisible(true);
				gamePlayPanel.add(Token1);

				// player 2의 토큰 색상이 흰색
				imgtoken2 = new ImageIcon("images/W.png");
				Token2 = new JLabel("", imgtoken2, SwingConstants.CENTER);
				Token2.setBounds(760, 290, 120, 120);
				Token2.setVisible(true);
				gamePlayPanel.add(Token2);
			} else if (t1 == -1) {
				// player 1의 토큰 색상이 흰색
				imgtoken1 = new ImageIcon("images/W.png");
				Token1 = new JLabel("", imgtoken1, SwingConstants.CENTER);
				Token1.setBounds(110, 290, 120, 120);
				Token1.setVisible(true);
				gamePlayPanel.add(Token1);

				// player 2의 토큰 색상이 검은색
				imgtoken2 = new ImageIcon("images/B.png");
				Token2 = new JLabel("", imgtoken2, SwingConstants.CENTER);
				Token2.setBounds(760, 290, 120, 120);
				Token2.setVisible(true);
				gamePlayPanel.add(Token2);
			}

			// 각 player 의 캐릭터
			if (p1 == 1) {
				// player 1의 캐릭터가 seal
				image1 = new ImageIcon(filePath1[0]);
				Character1 = new JLabel("", image1, SwingConstants.CENTER);
				Character1.setBounds(10, 290, 120, 120);
				Character1.setVisible(true);
				gamePlayPanel.add(Character1);

			} else if (p1 == 2) {
				// player 1의 캐릭터가 wolf
				image1 = new ImageIcon(filePath2[0]);
				Character1 = new JLabel("", image1, SwingConstants.CENTER);
				Character1.setBounds(10, 290, 120, 120);
				Character1.setVisible(true);
				gamePlayPanel.add(Character1);
			} else if (p1 == 3) {
				// player 1의 캐릭터가 squirrel
				image1 = new ImageIcon(filePath3[0]);
				Character1 = new JLabel("", image1, SwingConstants.CENTER);
				Character1.setBounds(10, 290, 120, 120);
				Character1.setVisible(true);
				gamePlayPanel.add(Character1);
			} else if (p1 == 4) {
				// player 1의 캐릭터가 monster
				image1 = new ImageIcon(filePath4[0]);
				Character1 = new JLabel("", image1, SwingConstants.CENTER);
				Character1.setBounds(10, 290, 120, 120);
				Character1.setVisible(true);
				gamePlayPanel.add(Character1);
			}

			if (p2 == 0) {
				// 1인 모드일 때 player 2는 computer 캐릭터
				image2 = new ImageIcon(filePath0[0]);
				Character2 = new JLabel("", image2, SwingConstants.CENTER);
				Character2.setBounds(860, 290, 120, 120);
				Character2.setVisible(true);
				gamePlayPanel.add(Character2);
			}

		}
		// 2인 모드일 경우
		else {
			// 검정 토큰은 왼쪽
			imgtoken1 = new ImageIcon("images/B.png");
			Token1 = new JLabel("", imgtoken1, SwingConstants.CENTER);
			Token1.setBounds(110, 290, 120, 120);
			Token1.setVisible(true);
			gamePlayPanel.add(Token1);

			// 흰색 토큰은 오른쪽
			imgtoken2 = new ImageIcon("images/W.png");
			Token2 = new JLabel("", imgtoken2, SwingConstants.CENTER);
			Token2.setBounds(760, 290, 120, 120);
			Token2.setVisible(true);
			gamePlayPanel.add(Token2);

			// 각 player의 캐릭터
			// player 1
			if (p1 == 1) {
				// player 1의 캐릭터가 seal
				image1 = new ImageIcon(filePath1[0]);
				Character1 = new JLabel("", image1, SwingConstants.CENTER);
				Character1.setBounds(10, 290, 120, 120);
				Character1.setVisible(true);
				gamePlayPanel.add(Character1);
			} else if (p1 == 2) {
				// player 1의 캐릭터가 wolf
				image1 = new ImageIcon(filePath2[0]);
				Character1 = new JLabel("", image1, SwingConstants.CENTER);
				Character1.setBounds(10, 290, 120, 120);
				Character1.setVisible(true);
				gamePlayPanel.add(Character1);
			} else if (p1 == 3) {
				// player 1의 캐릭터가 squirrel
				image1 = new ImageIcon(filePath3[0]);
				Character1 = new JLabel("", image1, SwingConstants.CENTER);
				Character1.setBounds(10, 290, 120, 120);
				Character1.setVisible(true);
				gamePlayPanel.add(Character1);
			} else if (p1 == 4) {
				// player 1의 캐릭터가 monster
				image1 = new ImageIcon(filePath4[0]);
				Character1 = new JLabel("", image1, SwingConstants.CENTER);
				Character1.setBounds(10, 290, 120, 120);
				Character1.setVisible(true);
				gamePlayPanel.add(Character1);
			}

			// player 2
			if (p2 == 1) {
				// player 2의 캐릭터가 seal
				image2 = new ImageIcon(filePath1[0]);
				Character2 = new JLabel("", image2, SwingConstants.CENTER);
				Character2.setBounds(870, 290, 120, 120);
				Character2.setVisible(true);
				gamePlayPanel.add(Character2);
			} else if (p2 == 2) {
				// player 2의 캐릭터가 wolf
				image2 = new ImageIcon(filePath2[0]);
				Character2 = new JLabel("", image2, SwingConstants.CENTER);
				Character2.setBounds(870, 290, 120, 120);
				Character2.setVisible(true);
				gamePlayPanel.add(Character2);
			} else if (p2 == 3) {
				// player 2의 캐릭터가 squirrel
				image2 = new ImageIcon(filePath3[0]);
				Character2 = new JLabel("", image2, SwingConstants.CENTER);
				Character2.setBounds(870, 290, 120, 120);
				Character2.setVisible(true);
				gamePlayPanel.add(Character2);
			} else if (p2 == 4) {
				// player 2의 캐릭터가 monster
				image2 = new ImageIcon(filePath4[0]);
				Character2 = new JLabel("", image2, SwingConstants.CENTER);
				Character2.setBounds(870, 290, 120, 120);
				Character2.setVisible(true);
				gamePlayPanel.add(Character2);
			}
		}

	}

	// 게임 결과 패널(결과 화면)
	private void setWinPanel() {
		// 패널 생성 및 설정
		winPanel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				g.drawImage(img, 0, 0, 700, 500, null);
			}

		};
		winPanel.setPreferredSize(new Dimension(700, 500));
		winPanel.setLayout(null);

		// 승자 표시 문구
		String msg;

		if (score_1 > score_2)// player 1이 승리
			msg = "Player 1";
		else if (score_1 < score_2)// player 2가 승리
			msg = "Player 2";
		else// 무승부
			msg = "";

		// 승자 문구 라벨 생성 및 설정
		lbl1 = new JLabel(msg);
		lbl1.setFont(new Font("Felix Titling", Font.BOLD, 30));
		lbl1.setVerticalAlignment(SwingConstants.CENTER);
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setForeground(Color.white);
		lbl1.setBounds(270, 50, 150, 35);
		winPanel.add(lbl1);

		// 승리 문구 생서
		lbl2 = new JLabel("Win!!");
		if (msg == "")// 무승부
			lbl2.setText("Draw!");
		lbl2.setFont(new Font("Felix Titling", Font.BOLD, 70));
		lbl2.setVerticalAlignment(SwingConstants.CENTER);
		lbl2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2.setForeground(Color.white);
		lbl2.setBounds(200, 130, 300, 100);
		winPanel.add(lbl2);

		// play again 버튼 설정
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

		// game exit 버튼 설정
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

	// 게임의 초기화면 설정
	private void showInitPage() {
		mainFrame.setContentPane(initPanel);
		mainFrame.pack();
	}

	// 모드 선택 화면 패널 설정
	static private void showGameSettingPage() {
		mainFrame.setContentPane(gameSettingPanel);
		mainFrame.pack();
	}

	// player 의 캐릭터 설정
	private void showChracterSelectPanel() {
		mainFrame.setContentPane(characterSelectPanel);
		mainFrame.pack();
	}

	// 게임화면 패널 설정
	private void showGamePlayPage() {
		mainFrame.setContentPane(gamePlayPanel);
		mainFrame.pack();
	}

	// 게임 결과 표시하는 프레임 설정
	private void showWinPage() {
		colorInfo.setText("Game Over");

		// 게임 결과 표시 패널 생성및 프레임에 추가
		setWinPanel();
		winFrame = new JFrame("Game Result");
		winFrame.setResizable(false);
		winFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		winFrame.setVisible(true);
		winFrame.setSize(new Dimension(700, 500));
		winFrame.setLocationRelativeTo(mainFrame);
		winFrame.getContentPane().add(winPanel);
		winFrame.pack();

		// 효과음 재생
		volume.endVolume();
	}

	// 게임화면 내 게임보드판 update
	private void updateGameBoardG() {
		board_panel.removeAll();
		posPossible = GameManager.findPossiblePosition(gm.getGameCurrentBoard(), gm.currentTurnToken.getState());
		board_panel.paintImmediately(0, 0, 500, 500);
	}

	// 현재 차례의 토큰 색상 update
	private void updateColorInfo(int color) {
		String strColor = (color == 1) ? "Black" : "White";

		colorInfo.setText(strColor + " Turn");
	}

	// player의 점수와 캐릭터 update
	private void updateScore() {

		// player 1의 토큰 색상이 검정색
		if (gameInfo.getPlayer1().getMyToken().getState() == 1) {
			score_1 = gm.getGameCurrentBoard().getCntBlack();// player 1의 점수 == 검정 토큰의 개수
			score_2 = gm.getGameCurrentBoard().getCntWhite();// player 2의 점수 == 흰색 토큰의 개수
		}
		// player 1의 토큰 색상이 흰색
		else {
			score_1 = gm.getGameCurrentBoard().getCntWhite();// player 1의 점수 == 흰색 토큰의 개수
			score_2 = gm.getGameCurrentBoard().getCntBlack();// player 2의 점수 == 검정 토큰의 개수
		}
		// 점수 라벨 설정 및 update
		scr1.setText(Integer.toString(score_1));
		scr2.setText(Integer.toString(score_2));
		scr1.paintImmediately(scr1.getVisibleRect());
		scr2.paintImmediately(scr2.getVisibleRect());

		// player의 캐릭터
		P1 = gameInfo.getPlayer1().getMyCharacter();
		P2 = gameInfo.getPlayer2().getMyCharacter();

		int i, j;

		// player 1의 점수
		if (score_1 >= 0 && score_1 < 8) {// 1단계
			i = 0;
		} else if (score_1 < 16) {// 2단계
			i = 3;
		} else if (score_1 < 24) {// 3단계
			i = 1;
		} else if (score_1 < 32) {// 4단계
			i = 4;
		} else if (score_1 < 40) {// 5단계
			i = 2;
		} else {// 6단계
			i = 5;
		}

		// player 2의 점수
		if (score_2 >= 0 && score_2 < 8) {// 1단계
			j = 0;
		} else if (score_2 < 16) {// 2단계
			j = 3;
		} else if (score_2 < 24) {// 3단계
			j = 1;
		} else if (score_2 < 32) {// 4단계
			j = 4;
		} else if (score_2 < 40) {// 5단계
			j = 2;
		} else {// 6단계
			j = 5;
		}

		// 진화 단계에 따른 file경로 읽어오기
		String s1, s2;

		// player 1의 캐릭터 이미지 경로 읽어오기
		if (P1 == 1)// seal
			s1 = filePath1[i];
		else if (P1 == 2)// wolf
			s1 = filePath2[i];
		else if (P1 == 3)// squirrel
			s1 = filePath3[i];
		else// monster
			s1 = filePath4[i];

		// player 2의 캐릭터 이미지 경로 읽어오기
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

		// 캐릭터 설정
		Character1.setIcon(new ImageIcon(s1));
		Character2.setIcon(new ImageIcon(s2));
	}

	// 게임화면 내 게임보드판
	class Board extends JPanel {

		public Board() {
			setPreferredSize(new Dimension(500, 500));
			setVisible(true);
			setOpaque(false);
			// 보드판 이미지 읽어오기
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

			// 현재 보드판의 토큰 이미지 그리기
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (gm.getGameCurrentBoard().board[i][j] == 1) {// 검정색 토큰 이미지 위치하기
						g2.drawImage(imageB.getImage(), 58 * j + 18, 58 * i + 18, null);
					} else if (gm.getGameCurrentBoard().board[i][j] == -1) {// 흰색 토큰 이미지 위치하기
						g2.drawImage(imageW.getImage(), 58 * j + 18, 58 * i + 18, null);
					}
				}
			} // paintComponent()

			// computer를 제외한 사용자가 현재 보드판에서 위치 가능한 토큰 이미지 그리기
			if (!(gameInfo.getPlayer2().getMyCharacter() == 0
					&& gm.currentTurnToken.getState() == gameInfo.getPlayer2().getMyToken().getState())) {
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if (posPossible[i][j] == true) {// 토큰 위치 가능한 위치
							if (gm.currentTurnToken.getState() == 1) {// player의 토큰 색상이 검정색일 경우
								g2.drawImage(imageBS.getImage(), 58 * j + 18, 58 * i + 18, null);// 검정색 shadow 토큰 위치
							} else {// player의 토큰 색상이 흰색일 경우
								g2.drawImage(imageWS.getImage(), 58 * j + 18, 58 * i + 18, null);// 흰색 shadow 토큰 위치
							}
						}
					}
				}
			}
		}
	}

	// 게임 다시하기
	public static void restartGame(int re) {
		// 게임 재시작 여부 확인
		re = Exit.restart;

		// 새로운 게임 정보 생성 및 기존 정보 초기화
		gameInfo = new GameStartInfo();
		bgPlayerMode.clearSelection();
		bgToken.clearSelection();
		rbBlackToken.setVisible(false);
		rbWhiteToken.setVisible(false);
		btnNext.setVisible(false);

		// 게임 재시작 할 경우
		if (re == 0) {
			showGameSettingPage();// 게임 모드 선택 화면으로 패널 설정
		}
	}

	private class ButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// 선택됭 button 접근하기
			JButton obj = (JButton) e.getSource();

			// 선택된 button이 btnGameStart일 경우
			if (obj == btnGameStart) {
				// 효과음 삽입
				volume.buttonVolume();
				// 게임 모드 선택 패널로 설정
				showGameSettingPage();
			}
			// 선택된 button이 Home일 경우
			else if (obj == btnHome1 || obj == btnHome2 || obj == btnHome3) {
				// 효과음 삽입
				volume.buttonVolume();
				// 게임 정보 설정 새로 생성
				gameInfo = new GameStartInfo();

				// 기존 저장되어 있던 정보 초기화
				rbBlackToken.setVisible(false);
				rbWhiteToken.setVisible(false);
				btnNext.setVisible(false);
				bgToken.clearSelection();
				bgPlayerMode.clearSelection();

				// 시작 화면으로 이동
				showInitPage();
			}
			// 선택된 button이 btnNext일 경우
			else if (obj == btnNext) {
				// 효과음 삽입
				volume.buttonVolume();
				// 캐릭터 선택 패널 설정
				setCharacterSelectPanel();
				showChracterSelectPanel();
			}
			// 선택된 button이 manual 일 경우
			else if (obj == btnManual || obj == manual_btn) {
				// 효과음 삽입
				volume.buttonVolume();
				// manual frame이 존재하지 않을 경우
				if (sf == null)
					sf = new ShowManual();// 새로 생성
				else {// manual frame이 이미 존재할 경우
					sf.dispose();// 기존 frame 없애기
					sf = new ShowManual();// 새로 생성
				}
			}
			// 선택된 button이 option_btn일 경우
			else if (obj == option_btn) {
				// 효과음 삽입
				volume.buttonVolume();
				// option frame이 존재할 경우
				if (option != null)
					option.dispose();// 기존 frame 없애기
				option = new ShowOptions();// 새로 생성
			}
			// 선택된 button이 exit_btn일 경우
			else if (obj == exit_btn) {
				// 효과음 삽입
				volume.buttonVolume();
				// exit dialog 생성
				Exit.exitOrGameRestart();

			}
			// 선택된 button이 btnPlay일 경우
			else if (obj == btnPlay) {
				// 효과음 삽입
				volume.startVolume();

				// 게임 화면 패널로 설정
				setGamePlayPanel();
				showGamePlayPage();

				// 게임 정보 생성
				gm = new GameManager();
				// 현재 보드판에서 위치 가능한 위치모음의 판 가져오기
				posPossible = GameManager.findPossiblePosition(gm.getGameCurrentBoard(),
						gm.currentTurnToken.getState());

				// computer의 토큰 가져오기
				com = new Computer(-1 * gameInfo.getPlayer1().getMyToken().getState());

				// 1인 모드이고 player1의 토큰이 검정색일 경우
				if (gameInfo.getGameMode() == 1 && gameInfo.getPlayer1().getMyToken().getState() == -1) {
					// computer가 위치할 위치 찾기
					Point posIdx = com.searchBestPosition(gm.getGameCurrentBoard(), gm.currentTurnToken.getState(), 0,
							0);

					// 현재 게임 보드판 가져오기
					GameBoard currentBoard = gm.getGameCurrentBoard();
					// 게임 보드판 update
					currentBoard.updateBoard(posIdx, gm.currentTurnToken.getState());
					// 현재 차례의 토큰 색상 설정
					gm.setCurrentToken(new Token(-1 * gm.currentTurnToken.getState()));

					// 현재 차례의 토큰 색상 update
					updateColorInfo(gm.currentTurnToken.getState());
					// 게임 보드판의 이미지 update
					updateGameBoardG();
					// player의 점수 업데이트
					updateScore();
				}
			}
			// 선택된 button이 btnPlayAgain일 경우
			else if (obj == btnPlayAgain) {
				// 새로운 게임 정보 설정 생성
				gameInfo = new GameStartInfo();

				// 기존 저장되어 있던 정보 초기화
				rbBlackToken.setVisible(false);
				rbWhiteToken.setVisible(false);
				btnNext.setVisible(false);
				bgToken.clearSelection();
				bgPlayerMode.clearSelection();

				// 게임 모드 선택 패널로 설정
				showGameSettingPage();

				// 게임 결과 frame 삭제
				winFrame.dispose();
			}
			// 선택된 button이 btnGameExit일 경우
			else if (obj == btnGameExit)
				// 프로그램 종료
				System.exit(0);

		}// actionPerformed()
	}// ButtonActionListener class

	private class RadioButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// 선택된 radio 접근
			JRadioButton obj = (JRadioButton) e.getSource();

			// 선택됭 radio 버튼이 1인 플레이 모드일 경우
			if (obj == rb1PlayerMode) {
				// 토큰 선택하는 버튼 group 초기화 및 보여주기
				rbBlackToken.setSelected(false);
				rbWhiteToken.setSelected(false);
				rbBlackToken.setVisible(true);
				rbWhiteToken.setVisible(true);
				bgToken.clearSelection();
				// 다음 패널로 이동하는 버튼 보여주지 않기
				btnNext.setVisible(false);
			}
			// 선택된 radio 버튼이 2인 모드일 경우
			else if (obj == rb2PlayerMode) {
				// 토큰 선택하는 버튼 group 보여주지 않기
				rbBlackToken.setVisible(false);
				rbWhiteToken.setVisible(false);
				// 다음 패널로 이동하는 버튼 보여주기
				btnNext.setVisible(true);
				// 게임 정보 설정에 2인 모드로 설정하고 player1의 토큰 색상 자동으로 검정색 설정
				gameInfo.setGameStartInfo(2, new Token(0));
			}
			// 선택된 radio 버튼이 검정색 토큰일 경우
			else if (obj == rbBlackToken) {
				// 다음 패널로 이동하는 버튼 보여주기
				btnNext.setVisible(true);
				// 게임 정보 설정에 1인 모드로 설정하고 player1의 토큰 색상 검정색으로 설정
				gameInfo.setGameStartInfo(1, new Token(1));
			}
			// 선택된 radio 버튼이 흰색 토큰일 경우
			else if (obj == rbWhiteToken) {
				// 다음 패널로 이동하는 버튼 보여주기
				btnNext.setVisible(true);
				// 게임 정보 설정에 1인 모드로 설정하고 player1의 토큰 색상 흰색으로 설정
				gameInfo.setGameStartInfo(1, new Token(-1));
			}
			// 선택된 radio 버튼이 player 1의 캐릭터로 seal을 선택한 경우
			else if (obj == rbWhale) {

				rbWhale.setIcon(new ImageIcon("images/seal_1.png"));// seal의 이미지만 진하게 표시
				rbWolf.setIcon(new ImageIcon("images/wolf_t.png"));
				rbSquirrel.setIcon(new ImageIcon("images/chipmunk_t.png"));
				rbShark.setIcon(new ImageIcon("images/monster_t.png"));

				// 게임 정보 설정에서 player1의 캐릭터 1로 설정
				gameInfo.setPlayer1(1);

				// 1인 모드일 경우
				if (gameInfo.getGameMode() == 1) {
					gameInfo.setPlayer2(0);// player 2의 캐릭터 computer로 설정
					btnPlay.setVisible(true);// 게임화면으로 이동하는 버튼 보여주기
				}
				// 2인 모드일 경우
				else {
					// player 1과 player 2의 캐릭터 선택과 생성이 완료된 경우
					if (gameInfo.getPlayer1() != null && gameInfo.getPlayer1().getMyCharacter() != 0
							&& gameInfo.getPlayer2() != null && gameInfo.getPlayer2().getMyCharacter() != 0)
						btnPlay.setVisible(true);// 게임화면으로 이동하는 버튼 보여주기
				}
			}
			// 선택된 radio 버튼이 player 1의 캐릭터로 wolf를 선택한 경우
			else if (obj == rbWolf) {

				rbWhale.setIcon(new ImageIcon("images/seal_t.png"));
				rbWolf.setIcon(new ImageIcon("images/wolf_1.png"));// wolf의 이미지만 진하게 표시
				rbSquirrel.setIcon(new ImageIcon("images/chipmunk_t.png"));
				rbShark.setIcon(new ImageIcon("images/monster_t.png"));

				// 게임 정보 설정에서 player1의 캐릭터 2로 설정
				gameInfo.setPlayer1(2);

				// 1인 모드일 경우
				if (gameInfo.getGameMode() == 1) {
					gameInfo.setPlayer2(0);// player 2의 캐릭터 computer로 설정
					btnPlay.setVisible(true);// 게임화면으로 이동하는 버튼 보여주기
				}
				// 2인 모드일 경우
				else {
					// player 1과 player 2의 캐릭터 선택과 생성이 완료된 경우
					if (gameInfo.getPlayer1() != null && gameInfo.getPlayer1().getMyCharacter() != 0
							&& gameInfo.getPlayer2() != null && gameInfo.getPlayer2().getMyCharacter() != 0)
						btnPlay.setVisible(true);// 게임화면으로 이동하는 버튼 보여주기
				}
			}
			// 선택된 radio 버튼이 player 1의 캐릭터로 squirrel을 선택한 경우
			else if (obj == rbSquirrel) {

				rbWhale.setIcon(new ImageIcon("images/seal_t.png"));
				rbWolf.setIcon(new ImageIcon("images/wolf_t.png"));
				rbSquirrel.setIcon(new ImageIcon("images/chipmunk_1.png"));// squirrel의 이미지만 진하게 표시
				rbShark.setIcon(new ImageIcon("images/monster_t.png"));

				// 게임 정보 설정에서 player1의 캐릭터 3로 설정
				gameInfo.setPlayer1(3);

				// 1인 모드일 경우
				if (gameInfo.getGameMode() == 1) {
					gameInfo.setPlayer2(0);// player 2의 캐릭터 computer로 설정
					btnPlay.setVisible(true);// 게임화면으로 이동하는 버튼 보여주기
				}
				// 2인 모드일 경우
				else {
					// player 1과 player 2의 캐릭터 선택과 생성이 완료된 경우
					if (gameInfo.getPlayer1() != null && gameInfo.getPlayer1().getMyCharacter() != 0
							&& gameInfo.getPlayer2() != null && gameInfo.getPlayer2().getMyCharacter() != 0)
						btnPlay.setVisible(true);// 게임화면으로 이동하는 버튼 보여주기
				}
			}
			// 선택된 radio 버튼이 player 1의 캐릭터로 monster을 선택한 경우
			else if (obj == rbShark) {

				rbWhale.setIcon(new ImageIcon("images/seal_t.png"));
				rbWolf.setIcon(new ImageIcon("images/wolf_t.png"));
				rbSquirrel.setIcon(new ImageIcon("images/chipmunk_t.png"));
				rbShark.setIcon(new ImageIcon("images/monster_1.png"));// monster의 이미지만 진하게 표시

				// 게임 정보 설정에서 player1의 캐릭터 4로 설정
				gameInfo.setPlayer1(4);

				// 1인 모드일 경우
				if (gameInfo.getGameMode() == 1) {
					gameInfo.setPlayer2(0);// player 2의 캐릭터 computer로 설정
					btnPlay.setVisible(true);// 게임화면으로 이동하는 버튼 보여주기
				}
				// 2인 모드일 경우
				else {
					// player 1과 player 2의 캐릭터 선택과 생성이 완료된 경우
					if (gameInfo.getPlayer1() != null && gameInfo.getPlayer1().getMyCharacter() != 0
							&& gameInfo.getPlayer2() != null && gameInfo.getPlayer2().getMyCharacter() != 0)
						btnPlay.setVisible(true);// 게임화면으로 이동하는 버튼 보여주기
				}
			}
			// 선택된 radio 버튼이 player 2의 캐릭터로 seal을 선택한 경우
			else if (obj == rbWhale2) {

				rbWhale2.setIcon(new ImageIcon("images/seal_1.png"));// seal의 이미지만 진하게 표시
				rbWolf2.setIcon(new ImageIcon("images/wolf_t.png"));
				rbSquirrel2.setIcon(new ImageIcon("images/chipmunk_t.png"));
				rbShark2.setIcon(new ImageIcon("images/monster_t.png"));

				// 게임 정보 설정에서 player2의 캐릭터 1로 설정
				gameInfo.setPlayer2(1);

				// player 1과 player 2의 캐릭터 선택과 생성이 완료된 경우
				if (gameInfo.getPlayer1() != null && gameInfo.getPlayer1().getMyCharacter() != 0
						&& gameInfo.getPlayer2() != null && gameInfo.getPlayer2().getMyCharacter() != 0)
					btnPlay.setVisible(true);// 게임화면으로 이동하는 버튼 보여주기
			}
			// 선택된 radio 버튼이 player 2의 캐릭터로 wolf을 선택한 경우
			else if (obj == rbWolf2) {

				rbWhale2.setIcon(new ImageIcon("images/seal_t.png"));
				rbWolf2.setIcon(new ImageIcon("images/wolf_1.png"));// wolf의 이미지만 진하게 표시
				rbSquirrel2.setIcon(new ImageIcon("images/chipmunk_t.png"));
				rbShark2.setIcon(new ImageIcon("images/monster_t.png"));

				// 게임 정보 설정에서 player2의 캐릭터 2로 설정
				gameInfo.setPlayer2(2);

				// player 1과 player 2의 캐릭터 선택과 생성이 완료된 경우
				if (gameInfo.getPlayer1() != null && gameInfo.getPlayer1().getMyCharacter() != 0
						&& gameInfo.getPlayer2() != null && gameInfo.getPlayer2().getMyCharacter() != 0)
					btnPlay.setVisible(true);// 게임화면으로 이동하는 버튼 보여주기
			}
			// 선택된 radio 버튼이 player 2의 캐릭터로 squirrel을 선택한 경우
			else if (obj == rbSquirrel2) {

				rbWhale2.setIcon(new ImageIcon("images/seal_t.png"));
				rbWolf2.setIcon(new ImageIcon("images/wolf_t.png"));
				rbSquirrel2.setIcon(new ImageIcon("images/chipmunk_1.png"));// squirrel의 이미지만 진하게 표시
				rbShark2.setIcon(new ImageIcon("images/monster_t.png"));

				// 게임 정보 설정에서 player2의 캐릭터 3로 설정
				gameInfo.setPlayer2(3);

				// player 1과 player 2의 캐릭터 선택과 생성이 완료된 경우
				if (gameInfo.getPlayer1() != null && gameInfo.getPlayer1().getMyCharacter() != 0
						&& gameInfo.getPlayer2() != null && gameInfo.getPlayer2().getMyCharacter() != 0)
					btnPlay.setVisible(true);// 게임화면으로 이동하는 버튼 보여주기
			}
			// 선택된 radio 버튼이 player 2의 캐릭터로 monster을 선택한 경우
			else if (obj == rbShark2) {

				rbWhale2.setIcon(new ImageIcon("images/seal_t.png"));
				rbWolf2.setIcon(new ImageIcon("images/wolf_t.png"));
				rbSquirrel2.setIcon(new ImageIcon("images/chipmunk_t.png"));
				rbShark2.setIcon(new ImageIcon("images/monster_1.png"));// monster의 이미지만 진하게 표시

				// 게임 정보 설정에서 player2의 캐릭터 4로 설정
				gameInfo.setPlayer2(4);

				// player 1과 player 2의 캐릭터 선택과 생성이 완료된 경우
				if (gameInfo.getPlayer1() != null && gameInfo.getPlayer1().getMyCharacter() != 0
						&& gameInfo.getPlayer2() != null && gameInfo.getPlayer2().getMyCharacter() != 0)
					btnPlay.setVisible(true);// 게임화면으로 이동하는 버튼 보여주기
			}
		}// actionPerformed()

	}// RadioButtonListener class

	private class ClickListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// 위치 가져오기
			Point p = e.getPoint();

			// 효과음 삽입
			volume.buttonVolume();

			// 게임 모드가 1인 일경우
			if (gameInfo.getGameMode() == 1) {
				Token playerToken = gameInfo.getPlayer1().getMyToken();// player1의 토큰 가져오기

				// 자신의 차례일 경우
				if (gm.currentTurnToken.getState() == playerToken.getState()) {
					// 현재 위치를 보드판 내 위치로 변환
					Point posIdx = gameInfo.getPlayer1().convertPosToIdx(p);

					// 보드판 내 위치일 경우
					if (GameManager.isRange(posIdx.x, posIdx.y)) {

						// 현재 보드판 호출하기
						GameBoard currentBoard = gm.getGameCurrentBoard();

						// 현재 보드판에서 위치가능한 위치들 가져오기
						posPossible = GameManager.findPossiblePosition(currentBoard, playerToken.getState());

						// 위치가능한 위치가 아닐 경우
						if (posPossible[posIdx.y][posIdx.x] == false)
							ErrorManager.positionError(mainFrame);

						// 위치가능한 위치일 경우
						else {

							// skip횟수
							gm.successiveCntSkip = 0;

							// 현재 보드판 update
							currentBoard.updateBoard(posIdx, playerToken.getState());

							// 다음 player에게 차례 넘겨주기
							gm.setCurrentToken(new Token(-1 * (gm.currentTurnToken.getState())));

							// 위치가능한 위치들 가져오기
							posPossible = new boolean[8][8];

							// 보드판 이미지 update
							updateGameBoardG();
							// 점수 update
							updateScore();
							// 현재 차례의 토큰 색상 update
							updateColorInfo(gm.currentTurnToken.getState());

							// skip 발생할 경우
							if (gm.canSkip()) {
								// skip이 2회 이상 발생 또는 보드판이 찼을 경우
								if (gm.isFull() || gm.successiveCntSkip >= 2) {
									showWinPage();// 게임 끝남 처리 해줘야됨
								}
								// 그외의 경우
								else {
									// 현재 토큰의 색상 설정
									gm.setCurrentToken(new Token(-1 * gm.currentTurnToken.getState()));

									// 보드판이 아직 안찼을 경우
									if (!gm.isFull())
										ErrorManager.skipMsg(mainFrame, gm.currentTurnToken.getState());// skip message
																										// 보여주기

									// 현재 차례의 토큰 색상 update
									updateColorInfo(gm.currentTurnToken.getState());
									// 점수 update
									updateScore();
									// 보드판 이미지 update
									updateGameBoardG();

									// skip 이 발생할 경우
									if (gm.canSkip())
										showWinPage();// 게임 끝남 처리 해줘야됨
								}
							}
						}
					}
					// 위치 error message 출력
					else {
						ErrorManager.positionError(mainFrame);
					}
				}
			} else { // 2인 모드일경우
				int currentColor = gm.currentTurnToken.getState();

				Player currentPlayer;

				// 어떤 토큰을 가진 플레이어인지 결정
				if (gameInfo.getPlayer1().getMyToken().getState() == currentColor)
					currentPlayer = gameInfo.getPlayer1();
				else
					currentPlayer = gameInfo.getPlayer2();

				// posIdx에 좌표를 인덱스로 변환
				Point posIdx = currentPlayer.convertPosToIdx(p);

				// 현재 보드판 호출하기
				GameBoard currentBoard = gm.getGameCurrentBoard();

				// 현재 보드판에서 위치가능한 위치들 가져오기
				posPossible = GameManager.findPossiblePosition(currentBoard, currentColor);

				// 보드판 내 위치일 경우
				if (GameManager.isRange(posIdx.x, posIdx.y)) {

					// 위치가능한 위치가 아닐 경우
					if (posPossible[posIdx.y][posIdx.x] == false)
						ErrorManager.positionError(mainFrame);// error message 출력하기
					// 위치가능한 위치일 경우
					else {
						// skip발생 횟수
						gm.successiveCntSkip = 0;
						// 현재 보드판 update
						currentBoard.updateBoard(posIdx, currentColor);
						// 현재 토큰의 색상 설정
						gm.setCurrentToken(new Token(-1 * (currentColor)));

						// 현재 차례의 토큰 색상 update
						updateColorInfo(gm.currentTurnToken.getState());
						// 점수 update
						updateScore();
						// 보드판 이미지 update
						updateGameBoardG();

						// skip 발생할 경우
						if (gm.canSkip()) {
							// skip이 2회 이상 발생 또는 보드판이 찼을 경우
							if (gm.isFull() || gm.successiveCntSkip >= 2) {
								showWinPage();// 게임 끝남 처리 해줘야됨
							}
							// 그외의 경우
							else {
								// 현재 토큰의 색상 설정
								gm.setCurrentToken(new Token(-1 * gm.currentTurnToken.getState()));

								// skip 이 발생할 경우
								if (gm.canSkip()) {
									showWinPage();// 게임 끝남 처리 해줘야됨
								}
								// 그외의 경우
								else {
									// 보드판이 아직 안찼을 경우
									if (!gm.isFull())
										ErrorManager.skipMsg(mainFrame, gm.currentTurnToken.getState());// skip message
																										// 보여주기

									// 현재 차례의 토큰 색상 update
									updateColorInfo(gm.currentTurnToken.getState());
									// 점수 update
									updateScore();
									// 보드판 이미지 update
									updateGameBoardG();
								}
							}
						}
					}
				}
				// error message 출력
				else {
					ErrorManager.positionError(mainFrame);
				}
			}
		}// mousePressed()

		@Override
		public void mouseReleased(MouseEvent e) {
			// 게임 모드가 1인 모드이고 컴퓨터 차례일 경우
			if (gameInfo.getGameMode() == 1 && gm.currentTurnToken.getState() == com.getMyToken().getState()) {
				// skip이 발생할 경우
				if (gm.canSkip()) {
					gm.setCurrentToken(new Token(-1 * gm.currentTurnToken.getState()));// 현재 차례의 토큰 색상 update

					// 보드판이 차지 않았을 경우
					if (!gm.isFull())
						ErrorManager.skipMsg(mainFrame, gm.currentTurnToken.getState());// skip message 보여주기

					// 현재 차례의 토큰 색상 update
					updateColorInfo(gm.currentTurnToken.getState());
					// 보드판 이미지 update
					updateGameBoardG();
				}
				// 그 외의 경우
				else {
					// skip 발생 횟수
					gm.successiveCntSkip = 0;

					// computer가 위치할 수 있는 최적의 위치 찾기
					Point posIdx = com.searchBestPosition(gm.getGameCurrentBoard(), gm.currentTurnToken.getState(), 0,
							0);

					// thread 지연시키기
					ts.run();

					// 현재 게임 보드판 가져오기
					GameBoard currentBoard = gm.getGameCurrentBoard();

					// 현재 게임 보드판 update
					currentBoard.updateBoard(posIdx, gm.currentTurnToken.getState());
					// 현재 토큰의 색상 update
					gm.setCurrentToken(new Token(-1 * gm.currentTurnToken.getState()));

					// 현재 차례의 토큰 색상 update
					updateColorInfo(gm.currentTurnToken.getState());
					// 점수 update
					updateScore();
					// 보드판 이미지 update
					updateGameBoardG();

					// skip이 발생하는 동안
					while (gm.canSkip()) {
						// 현재 토큰의 색상 설정
						gm.setCurrentToken(new Token(-1 * gm.currentTurnToken.getState()));

						// skip이 2회 이상 발생한 경우이거나 보드판이 찼을 경우
						if (gm.successiveCntSkip >= 2 || gm.isFull()) {
							showWinPage();// 게임 끝남 처리 해줘야됨
							break;
						}
						// 그외의 경우
						else {
							// 보드판이 덜 찼을 경우
							if (!gm.isFull()) {
								ErrorManager.skipMsg(mainFrame, gm.currentTurnToken.getState());// skip message 보여주기
								// thread 지연시키기
								ts.run();

								// computer가 위치할 수 있는 최적의 위치 찾기
								posIdx = com.searchBestPosition(gm.getGameCurrentBoard(),
										gm.currentTurnToken.getState(), 0, 0);

								// 위치 가능한 위치일 경우
								if (gm.isPossilbe(posIdx)) {
									// 현재 보드판 가져오기
									currentBoard = gm.getGameCurrentBoard();
									// 보드판 update
									currentBoard.updateBoard(posIdx, gm.currentTurnToken.getState());

									// skip횟수 초기화
									gm.successiveCntSkip = 0;
								}

								// 현재 토큰 색상 설정
								gm.setCurrentToken(new Token(-1 * gm.currentTurnToken.getState()));

								// 현재 차례의 토큰 색상 update
								updateColorInfo(gm.currentTurnToken.getState());
								// 점수 update
								updateScore();
								// 보드판 이미지 update
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
