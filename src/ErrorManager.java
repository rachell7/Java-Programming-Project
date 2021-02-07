import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorManager {
	static public void positionError(JFrame frame) { // ������ ������ ������ ��ū �ܿ� �ٸ� ���� Ŭ������ �� ��µǴ� ���� �޽���
		JOptionPane.showMessageDialog(frame, "Cannot be placed", "ERROR", JOptionPane.WARNING_MESSAGE);
		// ���� �޽����� ����ϴ� ���̾�α�
	} // positionError()

	static public void skipMsg(JFrame frame, int color) { // ���ʰ� ��ŵ�Ǿ� ���濡�� �Ѿ ��� �̸� �˷��ִ� �޽���
		String strColor = (color == 1) ? "Black" : "White"; // ���� ���ʰ� �������� �˷��ִ� ���ڿ�
		JOptionPane.showMessageDialog(frame, "Turn skip: current Turn is " + strColor + "!", "Information",
				JOptionPane.INFORMATION_MESSAGE);
		// �˸� �޽����� ����ϴ� ���̾�α�
	} // skipMsg()
} // ErrorManager class