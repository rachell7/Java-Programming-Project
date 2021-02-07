import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class ShowManual extends JFrame { // ShowManual클래스는 JFrame을 상속받음
	Dimension d = new Dimension(500, 800); // 크기 설정
	public final Color BACKGROUNDCOLOR = new Color(214, 220, 229); // 배경색
	public final Color BUTTONCOLOR = new Color(45, 175, 90); // 버튼의 색
	Image img2;

	private ManualPanel panel = new ManualPanel() { // 프레임에 들어갈 ManualPanel 패널 생성
		public void paintComponent(Graphics g) { // 그리기 메소드

			try {
				img2 = ImageIO.read(new File("./images/manual_back.png")); // 이미지파일 불러오기
			} catch (IOException ex) { // 예외
				ex.printStackTrace();
			}

			g.drawImage(img2, 0, 0, 500, 800, null); // 배경에 그리기
			setOpaque(false); // 겹치기
			super.paintComponent(g);

		}

	};

	public ShowManual() { // ShowManual 생성자
		setSize(d); // 프레임의 크기 설정
		setTitle("Manual");// 프레임의 제목
		setResizable(false);// 크기조절X
		setLocationRelativeTo(Graphic.mainFrame); // 위치설정
		setVisible(true); // 보이기
		setLayout(null); // 배치관리자 X
		add(panel);

	}

	public class ManualPanel extends JPanel { // ManaulPanel 패널
		Dimension d = new Dimension(500, 800);
		JLabel lblRules, lblCapRules, lblTips, lblStrategy, lblMadeBy;
		JTextPane engTxtPaneRules, korTxtPaneRules;
		String strEngRules = "Rules Of Othello";
		String strEngCapRules = "E";
		String strEngTips = "Tips and Strategies";
		String strEngStrategy = "※ ! A disk placed at the vertex of board can't be flipped ! ※";
		String strEngTxtPaneTips = "To place my disk at the vertex, induce the opponent to place the disk in the three spaces attached to the vertex.  (horizontal,vertical,diagonal)\n"
				+ "The disks placed on the edge is safe unless the opponent's disk is placed right next to it by edge.\n"
				+ "Therefore, it is better try to place an edge and then lead the game so that we can place the disk where we want it.\n";

		String strKorRules = "   오델로 게임 방법   ";
		String strKorCapRules = "각";
		String strKorTips = "게임 승리 전략 ";
		String strKorStrategy = "※ ! 보드 꼭지점에 놓인 토큰은 뒤집을 수 없다 ! ※";
		String strKorTxtPaneTips = "보드 꼭지점에 나의 토큰을 놓기 위해서, 상대방이 상대방의 토큰을 꼭지점에 좌,우,대각선에 인접하게 위치한 세 군데에 놓도록 유도하여야 한다.\n"
				+ "모서리에 놓은 토큰은, 상대방이 모서리 양 옆에 토큰을 놓지 않는 이상 뒤집히지 않는다. 그러므로 모서리에 자신의 토큰을 놓을 수 있도록 한 뒤, 상대방의 토큰의 위치를 강요하거나 제약을 줌 으로써 자신의 토큰을 꼭지점에 놓을 수 있도록 이끌어 나간다.\n"
				+ "꼭지점에 자신의 토큰을 놓는것은 수 많은 승리 전략중에 하나일 뿐, 언제나 승리로 이끄는 것은 아니라는 것을 명심하자!";
		JTextPane txtPaneRules, txtPaneTips;
		JButton btnLanguage;
		String strMadeBy = ("Made By 10조 :이수진 이유재 박진우 유경진");
		private boolean bKor; // 한글-영어 전환 boolean
		ButtonListener buttonL; // 버튼리스너
		Image img;
		Font fnt = new Font("Bookman Old Style", Font.PLAIN, 14); //
		Font fnt1 = new Font("맑은고딕", Font.BOLD, 15);
		Font fntTitle = new Font("H수평선B", Font.BOLD, 30); // 제목폰트
		Font fntTxtPane = new Font("Felix Titling", Font.BOLD, 14); // txtPane폰트
		Font fntCap = new Font("Felix Titling", Font.BOLD, 24); // 첫글자 폰트

		public ManualPanel() { // ManualPanel 생성자
			setSize(d); // 패널의 크기 설정
			setLayout(null);
			setBackground(BACKGROUNDCOLOR);

			bKor = true; // boolean true-> 한글

			lblRules = new JLabel(strKorRules); // rule이 적힌 JLabel
			lblRules.setHorizontalAlignment(SwingConstants.CENTER);
			lblRules.setFont(fntTitle);
			lblRules.setForeground(Color.black);
			lblRules.setBounds(100, 10, 300, 50);
			add(lblRules); // 패널에추가

			lblCapRules = new JLabel("각"); // 첫 글자가 적힌 JLabel
			lblCapRules.setHorizontalAlignment(SwingConstants.LEFT);
			lblCapRules.setVerticalAlignment(SwingConstants.TOP);
			lblCapRules.setFont(new Font("맑은고딕", Font.PLAIN, 24));
			lblCapRules.setBounds(50, 63, 400, 300);
			add(lblCapRules);// 패널에추가

			engTxtPaneRules = new JTextPane(); // 영어로 rule이적힌 txtPane
			engTxtPaneRules.setText(
					"   ach player starts with 30 disks at hand.\nOne side of disk is black, the other side is white.\n"
							+ "One player plays their disks white side up and the other player plays their disks black side up. \n"
							+ "The players place their disks alternately with their colors facing up.\n"
							+ "Player must place a disk on the board where exists one or more contiguous opponent disks between the new disk and existing own disk.\n"
							+ "The disks caught between them are flipped over by the color of the player.\r\n"
							+ "If the player does not have a position to place the disk, the turn will be passed to the opponent.\n"
							+ "If the board is full or both players can't place disk anymore, the game ends, and the player wins with more disks on the board.");
			engTxtPaneRules.setBackground(BACKGROUNDCOLOR);
			engTxtPaneRules.setBounds(50, 73, 400, 310);
			engTxtPaneRules.setAlignmentX(SwingConstants.CENTER);
			engTxtPaneRules.setAlignmentY(SwingConstants.CENTER);
			engTxtPaneRules.setFont(fntTxtPane);
			engTxtPaneRules.setOpaque(false); // 배경과 겹치기
			engTxtPaneRules.setEditable(false); // 수정불가
			engTxtPaneRules.setDragEnabled(false); // 드래그 불가
			engTxtPaneRules.setVisible(false); // 숨기기 (한글txtPane 노출)
			add(engTxtPaneRules); // 패널에 추가

			korTxtPaneRules = new JTextPane(); // 한글로 rule이적힌 txtPane
			korTxtPaneRules.setText("     각의 플레이어는 30개의 토큰을 가지고 시작한다.\n토큰의 한 면은 흰색이고, 반대쪽 면은 검정색이다.\n"
					+ "두 명의 플레이어는 각자 흰색 또는 검정색을 자신의 색으로 정한다.\n"
					+ "두 명의 플레이어는 교대로 자신의 색이 보이도록 보드 위에 토큰을 놓으면서 게임을 진행한다.\n"
					+ "플레이어는 자신이 토큰을 놓을 위치와 기존의 자신의 토큰 사이에 하나 이상의 상대방의 토큰이 존재하는 위치에 놓아야한다.\n"
					+ "새롭게 놓은 토큰과 기존의 자신의 토큰 사이에 존재하는 상대방의 토큰은 모두 자신의 색으로 뒤집는다.\n"
					+ "플레이어가 토큰을 놓을 수 있는 위치가 존재하지 않는 경우 상대방에게로 턴이 넘어간다.\n"
					+ "보드가 토큰으로 꽉 차거나, 두 플레이어가 더이상 토큰을 놓을 수 있는 위치가 존재하지 않는 경우 게임이 종료되며,\n보드 위에 놓여진 토큰 중 자신의 색깔이 더 많은 쪽이 승리한다.");
			korTxtPaneRules.setBackground(BACKGROUNDCOLOR);
			korTxtPaneRules.setBounds(50, 73, 400, 310);
			korTxtPaneRules.setAlignmentX(SwingConstants.CENTER);
			korTxtPaneRules.setAlignmentY(SwingConstants.CENTER);
			korTxtPaneRules.setFont(fntTxtPane);
			korTxtPaneRules.setEditable(false); // 수정불가
			korTxtPaneRules.setDragEnabled(false); // 드래그 불가
			korTxtPaneRules.setOpaque(false);// 배경과 겹치기
			add(korTxtPaneRules);// 패널에 추가

			lblTips = new JLabel(strKorTips); // tip이 적힌 JLabel
			lblTips.setHorizontalAlignment(SwingConstants.CENTER);
			lblTips.setFont(fntTitle);
			lblTips.setForeground(Color.black);
			lblTips.setBounds(100, 400, 300, 50);
			add(lblTips);

			lblStrategy = new JLabel(strKorStrategy); // strategy가 적힌 JLabel
			lblStrategy.setHorizontalAlignment(SwingConstants.CENTER);
			lblStrategy.setVerticalAlignment(SwingConstants.CENTER);
			lblStrategy.setFont(fnt1);
			lblStrategy.setForeground(Color.red);
			lblStrategy.setBounds(30, 450, 440, 50);
			add(lblStrategy);

			txtPaneTips = new JTextPane(); // tip이 적힌 txtPane
			txtPaneTips.setText(strKorTxtPaneTips);
			txtPaneTips.setBackground(BACKGROUNDCOLOR);
			txtPaneTips.setBounds(50, 500, 400, 200);
			txtPaneTips.setAlignmentX(SwingConstants.CENTER);
			txtPaneTips.setAlignmentY(SwingConstants.CENTER);
			txtPaneTips.setEditable(false);
			txtPaneTips.setFont(fntTxtPane);
			txtPaneTips.setOpaque(false);
			add(txtPaneTips);

			buttonL = new ButtonListener(); // 버튼에 추가할 버튼리스너

			btnLanguage = new JButton("Eng"); // 한영전환 버튼 -> 초기값은 영어로전환
			btnLanguage.setFont(new Font("Felix Titling", Font.BOLD, 9)); // 폰트 설정
			btnLanguage.setVerticalAlignment(SwingConstants.CENTER);
			btnLanguage.setHorizontalAlignment(SwingConstants.CENTER);
			btnLanguage.setBounds(400, 20, 50, 30);
			btnLanguage.setBackground(BUTTONCOLOR);
			btnLanguage.setForeground(Color.white);
			btnLanguage.setUI(new StyledButtonUI()); // 버튼에 StyledButtonUI 설정
			btnLanguage.addActionListener(buttonL); // ActionListener 추가

			add(btnLanguage); // 패널에 버튼 추가

			lblMadeBy = new JLabel("Made By 10조 : 이수진(19011476),  이유재(19011460),  박진우(1901566),  유경진(18011582)     "); // 개발자
																														// 목록
																														// JLabel
			lblMadeBy.setHorizontalAlignment(SwingConstants.CENTER);
			lblMadeBy.setVerticalAlignment(SwingConstants.BOTTOM);
			lblMadeBy.setBounds(0, 720, 500, 30);
			lblMadeBy.setFont(new Font("맑은고딕", Font.PLAIN, 10));
			// lblMadeBy.setOpaque(false);
			add(lblMadeBy);

		}// ManualPanel 생성자

		private class ButtonListener implements ActionListener { // 버튼리스너

			public void actionPerformed(ActionEvent e) {
				if (bKor) { // bKor == true 이면, 즉 한글 -> 영어 전환
					btnLanguage.setText("Kor"); // 버튼txt Kor 설정
					lblRules.setText(strEngRules); // 영어로 언어 설정
					lblCapRules.setText(strEngCapRules); // 영어로 언어 설정
					korTxtPaneRules.setVisible(false); // 한글 txtPane 숨기기
					engTxtPaneRules.setVisible(true); // 영어 txtPane 보이기
					lblCapRules.setText(strEngCapRules); // 영어로 언어 설정
					lblTips.setText(strEngTips); // 영어로 언어 설정
					lblStrategy.setText(strEngStrategy); // 영어로 언어 설정
					txtPaneTips.setText(strEngTxtPaneTips); // 영어로 언어 설정
					bKor = false; // bKor == false, 영어로설정
				} else { // bKor == false 이면, 즉 영어 -> 한글 전환
					btnLanguage.setText("Eng");
					lblRules.setText(strKorRules);
					lblCapRules.setText(strKorCapRules);
					korTxtPaneRules.setVisible(true);
					engTxtPaneRules.setVisible(false);
					lblTips.setText(strKorTips);
					lblStrategy.setText(strKorStrategy);
					txtPaneTips.setText(strKorTxtPaneTips);
					bKor = true; // 모든 언어 한글로 설정 및 bKor==true
				}

			}

		}
	}// ManualPanel class
}// ShowManual class