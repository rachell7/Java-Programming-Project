import java.awt.Point;

public class Player {

	private Token myToken; // ��ū�� ������ ������ �������
	private int myCharacter; // ĳ������ ������ ������ �������

	public Player(Token tk) // ��ū�� ���ڷ� �޴� ������
	{
		myToken = tk;
		myCharacter = 0; // ĳ���ʹ� 0 ���� �����Ͽ� �̼���
	}

	public Player(int c) // ĳ���͸� ���ڷ� �޴� ������
	{
		myCharacter = c; // ĳ���� ���� ����
	}

	public Player(Token tk, int c) // �÷��̾��� ���������ڷ� �޴� ������
	{
		myToken = tk; // ��ū ����
		myCharacter = c;// ĳ���� ����
	}

	public Token getMyToken() {
		return myToken;
	} // ��ū�� ���� ��ȯ �޼ҵ�

	public int getMyCharacter() {
		return myCharacter;
	} // ĳ���� ���� ��ȯ �޼ҵ�

	public void setMyChararter(int c) {
		myCharacter = c;
	} // ĳ���� ���� ���� �޼ҵ�

	public void setMyToken(Token tk) {
		myToken = tk;
	} // ��ū ���� ���� �޼ҵ�

	public Point convertPosToIdx(Point pos) {
		return new Point((pos.x - 11) / 57, (pos.y - 11) / 57); // ��ǥ�� ������ �ε����� ��ȯ���ִ� �޼ҵ�
	}
}