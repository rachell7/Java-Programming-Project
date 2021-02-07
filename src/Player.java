import java.awt.Point;

public class Player {

	private Token myToken; // 토큰의 정보를 저장할 멤버변수
	private int myCharacter; // 캐릭터의 정보를 저장할 멤버변수

	public Player(Token tk) // 토큰을 인자로 받는 생성자
	{
		myToken = tk;
		myCharacter = 0; // 캐릭터는 0 으로 설정하여 미설정
	}

	public Player(int c) // 캐릭터를 인자로 받는 생성자
	{
		myCharacter = c; // 캐릭터 정보 저장
	}

	public Player(Token tk, int c) // 플레이어의 정보를인자로 받는 생성자
	{
		myToken = tk; // 토큰 설정
		myCharacter = c;// 캐릭터 설정
	}

	public Token getMyToken() {
		return myToken;
	} // 토큰의 정보 반환 메소드

	public int getMyCharacter() {
		return myCharacter;
	} // 캐릭터 정보 반환 메소드

	public void setMyChararter(int c) {
		myCharacter = c;
	} // 캐릭터 정보 설정 메소드

	public void setMyToken(Token tk) {
		myToken = tk;
	} // 토큰 정보 설정 메소드

	public Point convertPosToIdx(Point pos) {
		return new Point((pos.x - 11) / 57, (pos.y - 11) / 57); // 좌표를 보드의 인덱스로 전환해주는 메소드
	}
}