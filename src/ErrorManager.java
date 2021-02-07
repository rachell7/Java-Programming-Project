import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorManager {
	static public void positionError(JFrame frame) { // 보드판 내에서 섀도우 토큰 외에 다른 곳을 클릭했을 때 출력되는 오류 메시지
		JOptionPane.showMessageDialog(frame, "Cannot be placed", "ERROR", JOptionPane.WARNING_MESSAGE);
		// 오류 메시지를 출력하는 다이얼로그
	} // positionError()

	static public void skipMsg(JFrame frame, int color) { // 차례가 스킵되어 상대방에게 넘어갈 경우 이를 알려주는 메시지
		String strColor = (color == 1) ? "Black" : "White"; // 현재 차례가 누구인지 알려주는 문자열
		JOptionPane.showMessageDialog(frame, "Turn skip: current Turn is " + strColor + "!", "Information",
				JOptionPane.INFORMATION_MESSAGE);
		// 알림 메시지를 출력하는 다이얼로그
	} // skipMsg()
} // ErrorManager class