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
		super.installUI(c);// �⺻ �޼���
		AbstractButton button = (AbstractButton) c;// �⺻ ��ư
		button.setOpaque(false);// ��ư �����ֱ�
		button.setBorder(new EmptyBorder(5, 10, 10, 10));// ���� ���� �Ʒ� ������
	}// installUI()

	@Override
	public void paint(Graphics g, JComponent c) {
		AbstractButton b = (AbstractButton) c;// �⺻ ��ư
		paintBackground(g, b, b.getModel().isPressed() ? 2 : 0);// press������ ���� ��ư�� ���� ����
		super.paint(g, c);// ��ư ��� ����
	}// paint()

	private void paintBackground(Graphics g, JComponent c, int yOffset) {
		Dimension size = c.getSize();
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);// ���� ���� ���� ����
		g.setColor(c.getBackground().darker());// ��� ��Ӱ� ����
		g.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 15, 15);// ��ư�� ����
		g.setColor(c.getBackground());// ��ư ���� ����
		g.fillRoundRect(0, yOffset, size.width, size.height + yOffset - 5, 15, 15);// ��ư�� ����
	}// paintBackground()
}// StyledButtonUI class
