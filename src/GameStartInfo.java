
public class GameStartInfo {

	private int gameMode; // 게임모드를 저장하는 멤버변수
	private Player player1, player2; // 플레이어 1, 2의 정보를 저장하는 멤버변수

	public GameStartInfo() { // 생성자
		player1 = null; // 초기화
		player2 = null;
	} // GameStartInfo()

	public void setGameStartInfo(int pMode, Token pToken) {
		gameMode = pMode; // 게임모드를 pMode로 설정
		player1 = new Player(pToken); // player1 생성
	} // setGameStartInfo()

	public void setPlayer1(int ch) {
		if (gameMode == 1) // 1인모드이면
			player1.setMyChararter(ch); // player1의 캐릭터 설정
		else { // 2인모드이면
			player1 = new Player(new Token(1), ch); // 토큰은 검정색으로 하고 캐릭터 생성
		}
	} // setPlayer1()

	public void setPlayer2(int ch) {
		if (gameMode == 1) // 1인모드이면
			player2 = new Player(new Token(-1 * player1.getMyToken().getState()), 0); // player2의 캐릭터 설정, 토큰은 다른색으로
		else
			player2 = new Player(new Token(-1), ch); // 토큰은 흰색으로 하고 캐릭터 생성
	}// setPlayer2()

	public Player getPlayer1() { // player1의 정보를 가져오는 메소드
		return player1;
	}

	public Player getPlayer2() { // player2의 정보를 가져오는 메소드
		return player2;
	}

	public int getGameMode() { // gameMode를 가져오는 메소드
		return gameMode;
	}
}// GameStartInfo class