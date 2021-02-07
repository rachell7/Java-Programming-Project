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
	public final Color BUTTONCOLOR = new Color(168, 211, 142); // ��ư ���� ����
	static VolumeControl volume = new VolumeControl(); // ȿ������ ����ϱ� ���� ����
	int bgm = 50, sfx = 50, cnt = 0; // ��� ���� ��, ȿ���� ��, OK ��ư�� �� �� ���ȴ��� ����ϴ� ����

	Toolkit toolkit = Toolkit.getDefaultToolkit(); // ������ â�� �̹����� �ٲ� �� �ִ� ��Ŷ
	ImageIcon imageSound1 = new ImageIcon("images/sound.png"); // ���� ���� �����̴� �� ����Ŀ �̹���
	Image img = toolkit.getImage("images/option.png"); // �ɼ� �������� �̹����� ��Ϲ��� ������� �ٲ�
	Dimension size = new Dimension(700, 500); // ������ ������ ����
	OptionPanel panel = new OptionPanel(); // �ɼ� �г� ����

	public ShowOptions() {
		setSize(size); // ������ ������ ����
		setTitle("Option"); // ������ ����
		setIconImage(img); // ������ ������ �̹���
		setLocationRelativeTo(Graphic.mainFrame); // �׷����� ���������� ����� ������ ����
		setResizable(false); // ������ â�� ���Ƿ� ������ �� ����
		setVisible(true); // ������ ���̰� ����
		add(panel); // �г��� ����
	} // ShowOptions()

	public class OptionPanel extends JPanel {

		public void paintComponent(Graphics g) {
			Dimension size = new Dimension(700, 500); // �ɼ�â ��� ������ ����
			ImageIcon image = new ImageIcon("images/option_back.png"); // ��� �̹���
			g.drawImage(image.getImage(), 0, 0, size.width, size.height, null); // �̹��� ������ ����
			setOpaque(false); // �������ϰ�
			super.paintComponent(g); // ���� Ŭ���� ���
		} // OptionPanel class

		JLabel lbl1, lbl2, lblInfo1, lblInfo2; // �� ������ ����
		JSlider slide1, slide2; // �����̴� ������ ����
		JButton btnClose, btnOk; // ��ư ������ ����
		ButtonActionListener buttonL; // ��ư ������ ���� ����

		Font fnt = new Font("Felix Titling", Font.BOLD, 20); // ��Ʈ ����

		public OptionPanel() {

			String info1 = "CONTROL VOLUME AND PRESS DOWN OK."; // ù��° �� ��Ʈ�� ����
			String info2 = "YOU CAN CHANGE BGM BY PRESSING OK."; // �ι�° �� ��Ʈ�� ����

			setSize(size); // �г� ������ ����
			setLayout(null); // ��ġ ������ ����

			buttonL = new ButtonActionListener(); // ����

			lblInfo1 = new JLabel(info1); // ����
			lblInfo1.setFont(fnt); // ��Ʈ ����
			lblInfo1.setVerticalAlignment(SwingConstants.CENTER);
			lblInfo1.setForeground(Color.black); // ����
			lblInfo1.setBounds(120, 20, 600, 50); // ��ġ ����
			add(lblInfo1);

			lblInfo2 = new JLabel(info2); // ����
			lblInfo2.setFont(fnt); // ��Ʈ ����
			lblInfo2.setVerticalAlignment(SwingConstants.CENTER);
			lblInfo2.setForeground(Color.black); // ����
			lblInfo2.setBounds(130, 60, 600, 50); // ��ġ ����
			add(lblInfo2);

			lbl1 = new JLabel("BGM " + volume.bgm, imageSound1, SwingConstants.LEFT); // ����
			lbl1.setFont(fnt); // ��Ʈ ����
			lbl1.setForeground(Color.black); // ����
			lbl1.setBounds(70, 140, 150, 50); // ��ġ ����
			add(lbl1);

			lbl2 = new JLabel(" SFX " + volume.sfx, imageSound1, SwingConstants.LEFT); // ����
			lbl2.setFont(fnt); // ��Ʈ ����
			lbl2.setForeground(Color.black); // ����
			lbl2.setBounds(70, 230, 150, 50); // ��ġ ����
			add(lbl2);

			slide1 = new JSlider(0, 100, volume.bgm); // ����
			slide1.setOpaque(false); // �������ϰ�
			slide1.setBounds(200, 140, 400, 50); // ��ġ ����
			add(slide1);

			slide2 = new JSlider(0, 100, volume.sfx); // ����
			slide2.setOpaque(false); // �������ϰ�
			slide2.setBounds(200, 230, 400, 50); // ��ġ ����
			add(slide2);

			btnOk = new JButton("OK"); // ����
			btnOk.setBackground(BUTTONCOLOR); // ��ư ���� ����
			btnOk.setForeground(Color.black); // ����
			btnOk.setFont(new Font("Felix Titling", Font.BOLD, 20)); // ��Ʈ ����
			btnOk.setVerticalAlignment(SwingConstants.CENTER);
			btnOk.setHorizontalAlignment(SwingConstants.CENTER);
			btnOk.setBounds(100, 330, 200, 80); // ��ġ ����
			btnOk.addActionListener(buttonL); // ��ư �̺�Ʈ
			btnOk.setUI(new StyledButtonUI()); // ��ư ��Ÿ�� ����
			btnOk.setVisible(true); // ��ư ���̰� ����
			this.add(btnOk);

			btnClose = new JButton("CLOSE"); // ����
			btnClose.setBackground(BUTTONCOLOR); // ��ư ���� ����
			btnClose.setForeground(Color.black); // ����
			btnClose.setFont(new Font("Felix Titling", Font.BOLD, 20)); // ��Ʈ ����
			btnClose.setVerticalAlignment(SwingConstants.CENTER);
			btnClose.setHorizontalAlignment(SwingConstants.CENTER);
			btnClose.setBounds(380, 330, 200, 80); // ��ġ ����
			btnClose.addActionListener(buttonL); // ��ư �̺�Ʈ
			btnClose.setUI(new StyledButtonUI()); // ��ư ��Ÿ�� ����
			btnClose.setVisible(true); // ��ư ���̰� ����
			this.add(btnClose);

			slide1.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) { // �����̴� �����̰Բ�
					lbl1.setText("BGM " + slide1.getValue()); // �����̴� ���� ǥ��
				}
			});

			slide2.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) { // �����̴� �����̰Բ�
					lbl2.setText(" SFX " + slide2.getValue()); // �����̴� ���� ǥ��
				}
			});

		} // OptionPanel()

		private class ButtonActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) { // ��ư �̺�Ʈ ó��
				JButton obj = (JButton) e.getSource();
				if (obj == btnClose) { // btnClose�� Ŭ���Ǹ�
					volume.buttonVolume(); // ��ư ȿ���� ���
					dispose(); // â �ݱ�
				} else if (obj == btnOk) { // btnOk�� Ŭ���Ǹ�
					bgm = slide1.getValue(); // �����̴�1 ���� bgm�� ����
					sfx = slide2.getValue(); // �����̴�2 ���� sfx�� ����
					cnt++; // cnt �� ����

					volume.bgmvolumeValue(bgm); // ��� ���� ���� �� ����
					volume.sfxvolumeValue(sfx); // ȿ���� ���� �� ����
					volume.musicSelect(cnt); // cnt �� ����
					volume.buttonVolume(); // ��ư ȿ���� ���
					volume.bgmVolume(); // ��� ���� ���
				} // if else if
			} // actionPerformed()
		} // ButtonActionListener class
	} // OptionPanel class
} // ShowOptions class