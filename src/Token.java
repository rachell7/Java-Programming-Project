public class Token {
	private int state; // ��ū�� ������ �����ϴ� �������

	public Token(int val) {
		this.state = val; // ��ū�� ������ �����ϴ� ������
	}

	public int getState() { // ��ū�� ���¸� ��ȯ�ϴ� �޼ҵ�
		return state;
	}
}