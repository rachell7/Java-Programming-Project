import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ShowOptions extends JFrame {
	public final Color BUTTONCOLOR = new Color(168, 211, 142); // 버튼 색상 지정
	static VolumeControl volume = new VolumeControl(); // 효과음을 재생하기 위한 변수
	int bgm = 50, sfx = 50, cnt = 0; // 배경 음악 값, 효과음 값, OK 버튼이 몇 번 눌렸는지 계산하는 변수

	Toolkit toolkit = Toolkit.getDefaultToolkit(); // 프레임 창의 이미지를 바꿀 수 있는 툴킷
	ImageIcon imageSound1 = new ImageIcon("images/sound.png"); // 음량 조절 슬라이더 옆 스피커 이미지
	Image img = toolkit.getImage("images/option.png"); // 옵션 프레임의 이미지를 톱니바퀴 모양으로 바꿈
	Dimension size = new Dimension(700, 500); // 프레임 사이즈 설정
	OptionPanel panel = new OptionPanel(); // 옵션 패널 생성

	public ShowOptions() {
		setSize(size); // 프레임 사이즈 설정
		setTitle("Option"); // 프레임 제목
		setIconImage(img); // 프레임 아이콘 이미지
		setLocationRelativeTo(Graphic.mainFrame); // 그래픽의 메인프레임 가운데로 오도록 설정
		setResizable(false); // 프레임 창을 임의로 설정할 수 없음
		setVisible(true); // 프레임 보이게 설정
		add(panel); // 패널을 포함
	} // ShowOptions()

	public class OptionPanel extends JPanel {

		public void paintComponent(Graphics g) {
			Dimension size = new Dimension(700, 500); // 옵션창 배경 사이즈 설정
			ImageIcon image = new ImageIcon("images/option_back.png"); // 배경 이미지
			g.drawImage(image.getImage(), 0, 0, size.width, size.height, null); // 이미지 사이즈 조절
			setOpaque(false); // 불투명하게
			super.paintComponent(g); // 상위 클래스 상속
		} // OptionPanel class

		JLabel lbl1, lbl2, lblInfo1, lblInfo2; // 라벨 변수들 선언
		JSlider slide1, slide2; // 슬라이더 변수들 선언
		JButton btnClose, btnOk; // 버튼 변수들 선언
		ButtonActionListener buttonL; // 버튼 리스너 변수 선언

		Font fnt = new Font("Felix Titling", Font.BOLD, 20); // 폰트 설정

		public OptionPanel() {

			String info1 = "CONTROL VOLUME AND PRESS DOWN OK."; // 첫번째 줄 스트링 선언
			String info2 = "YOU CAN CHANGE BGM BY PRESSING OK."; // 두번째 줄 스트링 선언

			setSize(size); // 패널 사이즈 조정
			setLayout(null); // 배치 관리자 제거

			buttonL = new ButtonActionListener(); // 생성

			lblInfo1 = new JLabel(info1); // 생성
			lblInfo1.setFont(fnt); // 폰트 조정
			lblInfo1.setVerticalAlignment(SwingConstants.CENTER);
			lblInfo1.setForeground(Color.black); // 색상
			lblInfo1.setBounds(120, 20, 600, 50); // 위치 조정
			add(lblInfo1);

			lblInfo2 = new JLabel(info2); // 생성
			lblInfo2.setFont(fnt); // 폰트 조정
			lblInfo2.setVerticalAlignment(SwingConstants.CENTER);
			lblInfo2.setForeground(Color.black); // 색상
			lblInfo2.setBounds(130, 60, 600, 50); // 위치 조정
			add(lblInfo2);

			lbl1 = new JLabel("BGM " + volume.bgm, imageSound1, SwingConstants.LEFT); // 생성
			lbl1.setFont(fnt); // 폰트 조정
			lbl1.setForeground(Color.black); // 색상
			lbl1.setBounds(70, 140, 150, 50); // 위치 조정
			add(lbl1);

			lbl2 = new JLabel(" SFX " + volume.sfx, imageSound1, SwingConstants.LEFT); // 생성
			lbl2.setFont(fnt); // 폰트 조정
			lbl2.setForeground(Color.black); // 색상
			lbl2.setBounds(70, 230, 150, 50); // 위치 조정
			add(lbl2);

			slide1 = new JSlider(0, 100, volume.bgm); // 생성
			slide1.setOpaque(false); // 불투명하게
			slide1.setBounds(200, 140, 400, 50); // 위치 조정
			add(slide1);

			slide2 = new JSlider(0, 100, volume.sfx); // 생성
			slide2.setOpaque(false); // 불투명하게
			slide2.setBounds(200, 230, 400, 50); // 위치 조정
			add(slide2);

			btnOk = new JButton("OK"); // 생성
			btnOk.setBackground(BUTTONCOLOR); // 버튼 색상 설정
			btnOk.setForeground(Color.black); // 색상
			btnOk.setFont(new Font("Felix Titling", Font.BOLD, 20)); // 폰트 조정
			btnOk.setVerticalAlignment(SwingConstants.CENTER);
			btnOk.setHorizontalAlignment(SwingConstants.CENTER);
			btnOk.setBounds(100, 330, 200, 80); // 위치 조정
			btnOk.addActionListener(buttonL); // 버튼 이벤트
			btnOk.setUI(new StyledButtonUI()); // 버튼 스타일 조절
			btnOk.setVisible(true); // 버튼 보이게 설정
			this.add(btnOk);

			btnClose = new JButton("CLOSE"); // 생성
			btnClose.setBackground(BUTTONCOLOR); // 버튼 색상 설정
			btnClose.setForeground(Color.black); // 색상
			btnClose.setFont(new Font("Felix Titling", Font.BOLD, 20)); // 폰트 조정
			btnClose.setVerticalAlignment(SwingConstants.CENTER);
			btnClose.setHorizontalAlignment(SwingConstants.CENTER);
			btnClose.setBounds(380, 330, 200, 80); // 위치 조정
			btnClose.addActionListener(buttonL); // 버튼 이벤트
			btnClose.setUI(new StyledButtonUI()); // 버튼 스타일 조절
			btnClose.setVisible(true); // 버튼 보이게 설정
			this.add(btnClose);

			slide1.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) { // 슬라이더 움직이게끔
					lbl1.setText("BGM " + slide1.getValue()); // 슬라이더 값을 표시
				}
			});

			slide2.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) { // 슬라이더 움직이게끔
					lbl2.setText(" SFX " + slide2.getValue()); // 슬라이더 값을 표시
				}
			});

		} // OptionPanel()

		private class ButtonActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) { // 버튼 이벤트 처리
				JButton obj = (JButton) e.getSource();
				if (obj == btnClose) { // btnClose가 클릭되면
					volume.buttonVolume(); // 버튼 효과음 재생
					dispose(); // 창 닫기
				} else if (obj == btnOk) { // btnOk가 클릭되면
					bgm = slide1.getValue(); // 슬라이더1 값을 bgm에 저장
					sfx = slide2.getValue(); // 슬라이더2 값을 sfx에 저장
					cnt++; // cnt 값 증가

					volume.bgmvolumeValue(bgm); // 배경 음악 볼륨 값 전달
					volume.sfxvolumeValue(sfx); // 효과음 볼륨 값 전달
					volume.musicSelect(cnt); // cnt 값 전달
					volume.buttonVolume(); // 버튼 효과음 재생
					volume.bgmVolume(); // 배경 음악 재생
				} // if else if
			} // actionPerformed()
		} // ButtonActionListener class
	} // OptionPanel class
} // ShowOptions class