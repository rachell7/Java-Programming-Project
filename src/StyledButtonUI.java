import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

public class StyledButtonUI extends BasicButtonUI {
	@Override
	public void installUI(JComponent c) {
		super.installUI(c);// 기본 메서드
		AbstractButton button = (AbstractButton) c;// 기본 버튼
		button.setOpaque(false);// 버튼 보여주기
		button.setBorder(new EmptyBorder(5, 10, 10, 10));// 위쪽 왼쪽 아래 오른쪽
	}// installUI()

	@Override
	public void paint(Graphics g, JComponent c) {
		AbstractButton b = (AbstractButton) c;// 기본 버튼
		paintBackground(g, b, b.getModel().isPressed() ? 2 : 0);// press유무에 따라 버튼의 형태 변형
		super.paint(g, c);// 버튼 즉시 변경
	}// paint()

	private void paintBackground(Graphics g, JComponent c, int yOffset) {
		Dimension size = c.getSize();
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);// 가로 세로 글자 설정
		g.setColor(c.getBackground().darker());// 배경 어둡게 설정
		g.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 15, 15);// 버튼의 형태
		g.setColor(c.getBackground());// 버튼 색상 설정
		g.fillRoundRect(0, yOffset, size.width, size.height + yOffset - 5, 15, 15);// 버튼의 형태
	}// paintBackground()
}// StyledButtonUI class
