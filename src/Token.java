public class Token {
	private int state; // 토큰의 정보를 저장하는 멤버변수

	public Token(int val) {
		this.state = val; // 토큰의 정보를 설정하는 생성자
	}

	public int getState() { // 토큰의 상태를 반환하는 메소드
		return state;
	}
}