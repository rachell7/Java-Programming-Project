import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class Exit {
	static String[] select = { "Game Restart", "Exit", "Cancel" }; // ������ ��������� �������� ������� ������ �� �ִ� ���ڿ�
	static int restart, exit; // ������ �ٽ� �����ϴ���, �����ϴ��� �Ǵ��ϴ� ����
	static VolumeControl volume = new VolumeControl(); // ��ư�� Ŭ������ �� ����ϱ� ���� ����

	static public void exitOrGameRestart() {
		JLabel lbl = new JLabel("GAME RESTART OR EXIT?     ", javax.swing.SwingConstants.CENTER);
		// �� ����
		Font fnt = new Font("Verdana", Font.PLAIN, 15);
		// ��Ʈ ����
		lbl.setFont(fnt);
		// �� ��Ʈ
		UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("Arial", Font.PLAIN, 15)));
		UIManager.put("OptionPane.Font", new FontUIResource(new Font("Arial", Font.PLAIN, 15)));
		// ��ư�� �۾� ��Ʈ ����

		int selected = JOptionPane.showInternalOptionDialog(null, lbl, "GAME RESTART OR EXIT",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, select, select[0]);
		// ���� ���� ������ ������ϰų� ������ �� �ִ� �ɼ� â
		if (selected == 0) { // Game restart �� Ŭ���Ǹ�
			volume.buttonVolume(); // ��ư ȿ����
			restart = JOptionPane.showConfirmDialog(null, "Really Game Restart?", "Confirm", JOptionPane.YES_NO_OPTION);
			// ���� ������ ����� �� ������ ���� ���̾�α�
			if (restart == 0) { // yes Ŭ��
				Graphic.restartGame(restart); // ���� ��� ���� ȭ������ ���ư���.
			}
		} else if (selected == 1) { // Exit �� Ŭ���Ǹ�
			volume.buttonVolume(); // ��ư ȿ����
			exit = JOptionPane.showConfirmDialog(null, "Really Exit?", "Confirm", JOptionPane.YES_NO_OPTION);
			// ���� ������ ������ ������ ���� ���̾�α�
			if (exit == 0) { // yes Ŭ��
				System.exit(0); // ���α׷��� �����Ѵ�.
			}
		} else if (selected == 2) { // cancel �� Ŭ���Ǹ�
			volume.buttonVolume(); // ��ư ȿ����
		} // if...else if
	} // exitOrGameRestart()
} // Exit class