
public class GameStartInfo {

	private int gameMode; // ���Ӹ�带 �����ϴ� �������
	private Player player1, player2; // �÷��̾� 1, 2�� ������ �����ϴ� �������

	public GameStartInfo() { // ������
		player1 = null; // �ʱ�ȭ
		player2 = null;
	} // GameStartInfo()

	public void setGameStartInfo(int pMode, Token pToken) {
		gameMode = pMode; // ���Ӹ�带 pMode�� ����
		player1 = new Player(pToken); // player1 ����
	} // setGameStartInfo()

	public void setPlayer1(int ch) {
		if (gameMode == 1) // 1�θ���̸�
			player1.setMyChararter(ch); // player1�� ĳ���� ����
		else { // 2�θ���̸�
			player1 = new Player(new Token(1), ch); // ��ū�� ���������� �ϰ� ĳ���� ����
		}
	} // setPlayer1()

	public void setPlayer2(int ch) {
		if (gameMode == 1) // 1�θ���̸�
			player2 = new Player(new Token(-1 * player1.getMyToken().getState()), 0); // player2�� ĳ���� ����, ��ū�� �ٸ�������
		else
			player2 = new Player(new Token(-1), ch); // ��ū�� ������� �ϰ� ĳ���� ����
	}// setPlayer2()

	public Player getPlayer1() { // player1�� ������ �������� �޼ҵ�
		return player1;
	}

	public Player getPlayer2() { // player2�� ������ �������� �޼ҵ�
		return player2;
	}

	public int getGameMode() { // gameMode�� �������� �޼ҵ�
		return gameMode;
	}
}// GameStartInfo class