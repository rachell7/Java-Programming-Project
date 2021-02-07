import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class Exit {
	static String[] select = { "Game Restart", "Exit", "Cancel" }; // 게임을 재시작할지 종료할지 취소할지 선택할 수 있는 문자열
	static int restart, exit; // 게임을 다시 시작하는지, 종료하는지 판단하는 변수
	static VolumeControl volume = new VolumeControl(); // 버튼을 클릭했을 때 재생하기 위한 변수

	static public void exitOrGameRestart() {
		JLabel lbl = new JLabel("GAME RESTART OR EXIT?     ", javax.swing.SwingConstants.CENTER);
		// 라벨 생성
		Font fnt = new Font("Verdana", Font.PLAIN, 15);
		// 폰트 설정
		lbl.setFont(fnt);
		// 라벨 폰트
		UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("Arial", Font.PLAIN, 15)));
		UIManager.put("OptionPane.Font", new FontUIResource(new Font("Arial", Font.PLAIN, 15)));
		// 버튼과 글씨 폰트 설정

		int selected = JOptionPane.showInternalOptionDialog(null, lbl, "GAME RESTART OR EXIT",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, select, select[0]);
		// 게임 도중 게임을 재시작하거나 종료할 수 있는 옵션 창
		if (selected == 0) { // Game restart 가 클릭되면
			volume.buttonVolume(); // 버튼 효과음
			restart = JOptionPane.showConfirmDialog(null, "Really Game Restart?", "Confirm", JOptionPane.YES_NO_OPTION);
			// 정말 게임을 재시작 할 것인지 묻는 다이얼로그
			if (restart == 0) { // yes 클릭
				Graphic.restartGame(restart); // 게임 모드 선택 화면으로 돌아간다.
			}
		} else if (selected == 1) { // Exit 가 클릭되면
			volume.buttonVolume(); // 버튼 효과음
			exit = JOptionPane.showConfirmDialog(null, "Really Exit?", "Confirm", JOptionPane.YES_NO_OPTION);
			// 정말 게임을 종료할 것인지 묻는 다이얼로그
			if (exit == 0) { // yes 클릭
				System.exit(0); // 프로그램을 종료한다.
			}
		} else if (selected == 2) { // cancel 이 클릭되면
			volume.buttonVolume(); // 버튼 효과음
		} // if...else if
	} // exitOrGameRestart()
} // Exit class