import java.awt.Point;

public class GameManager {
	public Token currentTurnToken; // ���� ������ Token�� �����ϴ� ����
	private GameBoard currentBoard; // ���� GameBoard�� �����ϴ� ����
	public int successiveCntSkip; // ���������� �߻��� skipȽ���� �����ϴ� ����
	private GameStartInfo info; // ������ ������ �� ������ �����ϴ� ����

	// ������
	public GameManager() {
		this.currentBoard = new GameBoard();
		currentBoard.initBoard(); // board�� �ʱ�ȭ ��Ŵ
		successiveCntSkip = 0; // 0���� �ʱ�ȭ
		currentTurnToken = new Token(1);
	}

	// info�� ����
	public void setGameInfo(GameStartInfo info) {
		this.info = info;
	}

	// currentTurnToken�� �Է����� ���� Token���� �����ϴ� �޼���
	public void setCurrentToken(Token current) {
		currentTurnToken = current;
	}

	// x,y �ε����� ������ �������� �Ǵ��ϴ� �޼���
	public static boolean isRange(int x, int y) {
		return x >= 0 && x < 8 && y >= 0 && y < 8;
	}

	public static boolean[][] findPossiblePosition(GameBoard currentBoard, int currentState) {
		boolean[][] possiblePos = new boolean[8][8];

		Point[] dir = new Point[8]; // dir�� ���⺤�ͷ� ���� ���� ������, ����, ����, �Ʒ���, ������ �� �밢, ���� �� �밢, ���� �Ʒ� �밢, ������ �Ʒ� �밢��
		// �� ����
		int x = 0;
		int y = 1;

		// ������, ����, ����, �Ʒ���, ������ �� �밢, ���� �� �밢, ���� �Ʒ� �밢, ������ �Ʒ� �밢 ������ ������� dir�� �����
		for (int k = 0; k < 8; ++k) {
			if (k < 4) { // ������ ���� �Ʒ��ʱ��� ����
				int tmp = x;
				x = y;
				y = tmp;

				if (k % 2 == 1) {
					x *= -1;
					y *= -1;
				}
			} else { // ������ ���� �Ʒ��� ������ �������� �밢 ������ ����
				x = dir[k - 4].x + dir[(k - 3) % 4].x;
				y = dir[k - 4].y + dir[(k - 3) % 4].y;
			}

			dir[k] = new Point(x, y);
		}

		// �������� ��� ��ġ�� Ž��
		for (int i = 0; i < 8; ++i)
			for (int j = 0; j < 8; ++j)
				if (currentBoard.board[i][j] == currentState) { // ���� ��ū�� ���� ��ū�� �÷��� �ִ� ��ū�� �߰��ϸ�
					for (int k = 0; k < 8; ++k) { // ��� ���⿡ ���� ����
						int step = 1;

						int nextX = j + dir[k].x; // (i,j)���� dir[k]�������� 1ĭ �̵������� x��ġ�� ����
						int nextY = i + dir[k].y; // (i,j)���� dir[k]�������� 1ĭ �̵������� y��ġ�� ����

						// (nextX, nextY)�� ���� �̳��̰� �ٸ��� ��ū�� �÷��� ���� ������ �ݺ�
						while (isRange(nextX, nextY) && currentBoard.board[nextY][nextX] == -currentState) {
							step++;

							nextX = j + dir[k].x * step; // (i,j)���� dir[k]�������� step��ŭ �̵������� x��ġ�� ����
							nextY = i + dir[k].y * step; // (i,j)���� dir[k]�������� step��ŭ �̵������� y��ġ�� ����
						}

						// (nextX, nextY)�� �����̳ø� step �̻��� ���
						if (isRange(nextX, nextY) && step > 1) {
							if (currentBoard.board[nextY][nextX] == 0) // (nextY, nextX)�� ����ִٸ� �� �ڸ��� ��ġ ������ �ڸ�
								possiblePos[nextY][nextX] = true;
						}
					}
				}

		return possiblePos;
	}

	// �Է����� ���� ��ġ�� ������ �������� Ȯ���ϴ� �޼���
	public boolean isPossilbe(Point p) {
		boolean[][] possiblePos = GameManager.findPossiblePosition(currentBoard, currentTurnToken.getState()); // findPossiblePosition��
																												// ȣ���Ͽ�
																												// ������
																												// ��ġ
																												// �迭��
																												// ������

		return possiblePos[p.y][p.x];
	}

	// skip�� �ؾߵǴ��� Ȯ���ϴ� �޼���
	public boolean canSkip() {
		boolean[][] possiblePos = GameManager.findPossiblePosition(currentBoard, currentTurnToken.getState()); // findPossiblePosition��
																												// ȣ���Ͽ�
																												// ������
																												// ��ġ
																												// �迭��
																												// ������

		for (int i = 0; i < 8; ++i)
			for (int j = 0; j < 8; ++j)
				if (possiblePos[i][j] == true) // ������ ���� ���� ��� ��ŵ�� �ϸ� �ȵ�
					return false; // false��ȯ

		// ������ ��ġ�� �ϳ��� ���� ���
		successiveCntSkip++; // skip�� �߻����״� �������� ��ŵ Ƚ���� �����ϴ� ���� ���� 1����
		return true;
	}

	// �������� ��ū���� ���� á���� Ȯ���ϴ� �޼���
	public boolean isFull() {
		// �������� ��ȸ
		for (int i = 0; i < 8; ++i)
			for (int j = 0; j < 8; ++j)
				if (currentBoard.board[i][j] == 0) // ��ĭ �߰�
					return false; // false ��ȯ

		// ���� á��
		return true;
	}

	// currentBoard�� ��ȯ�ϴ� �޼���
	public GameBoard getGameCurrentBoard() {
		return this.currentBoard;
	}
}