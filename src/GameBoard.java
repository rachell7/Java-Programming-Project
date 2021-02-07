import java.awt.Point;

public class GameBoard {
	// black 1, white -1
	public int board[][]; // �������� ���¸� �����ϴ� 2���� �迭, blackToken: 1, whiteToken: -1, empty: 0
	private int cntBlack; // �����ǿ��� ������ ��ū�� ���� �����ϴ� ����
	private int cntWhite; // �����ǿ��� ��� ��ū�� ���� �����ϴ� ����

	public GameBoard() { // ������
		board = new int[8][8];

		initBoard();
	}

	public void initBoard() { // �������� �ʱ���·� �ʱ�ȭ ��Ű�� �޼���
		// ������ ��� ���� ��ĭ���� ����
		for (int i = 0; i < 8; ++i)
			for (int j = 0; j < 8; ++j)
				board[i][j] = 0;

		// ������ ��ū 2��, ��� ��ū 2���� �߾ӿ� �ִٴ� ���� �ǹ�
		board[3][3] = -1;
		board[4][4] = -1;
		board[3][4] = 1;
		board[4][3] = 1;

		// �� ��ū�� 2�� �� �����Ƿ� 2�� �ʱ�ȭ
		cntBlack = cntWhite = 2;
	}

	// p�� � �� ��ū�� ��ġ������ �� ����� ��ȭ�� board�� �����Ű�� �޼���
	public void updateBoard(Point p, int currentState) {
		board[p.y][p.x] = currentState; // p�� currentState���� ��ū�� ��ġ��Ŵ, currentState�� 1�̸� black, -1�̸� white

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

		// ��� ���⿡ ���� �ݺ��� ���� 8���� �ݺ�
		for (int k = 0; k < 8; ++k) {
			int step = 1;

			int nextX = p.x + dir[k].x * step; // p���� dir[k]�������� 1��ŭ ���� �� x���� ����
			int nextY = p.y + dir[k].y * step; // p���� dir[k]�������� 1��ŭ ���� �� y���� ����

			// nextX�� nextY�� ������ �����̸� �� ��ġ�� �ٸ� ���� ��ū�� ���� �� ���� �ݺ�
			while (GameManager.isRange(nextX, nextY) && board[nextY][nextX] == -currentState) {
				// step ���� ������Ŵ
				step++;

				nextX = p.x + dir[k].x * step; // p���� dir[k]�������� step��ŭ ���� �� x���� ����
				nextY = p.y + dir[k].y * step; // p���� dir[k]�������� step��ŭ ���� �� y���� ����
			}

			// nextX�� nextY�� �����̳�, (nextX,nextY)�� ��ġ�� ��ū�� ���� ���ʿ� ���� step�� 1���� Ŭ ���
			if (GameManager.isRange(nextX, nextY) && board[nextY][nextX] == currentState && step > 1) {
				Point reverseDir = new Point(-dir[k].x, -dir[k].y); // �� ���� ���� ����

				int prevX = nextX + reverseDir.x; // nextX���� reverseDir[k]�������� 1��ŭ ���� �� x���� ����
				int prevY = nextY + reverseDir.y; // nextY���� reverseDir[k]�������� 1��ŭ ���� �� y���� ����

				// �� �������� �����ϸ鼭 ��ū���� currentState������ �ٲ���
				while (GameManager.isRange(prevX, prevY) && board[prevY][prevX] == -currentState) {
					board[prevY][prevX] = currentState;

					prevX += reverseDir.x;
					prevY += reverseDir.y;
				}

			}
		}

		updateEachCnt(); // cntBlack�� cntWhite�� ������Ʈ
	}

	// ���� ���¸� ���� cntBlack�� cntWhite�� �����ϴ� �޼���
	public void updateEachCnt() {
		cntBlack = 0; // cntBlack, cntWhite�� 0���� �ʱ�ȭ
		cntWhite = 0;

		for (int i = 0; i < 8; ++i)
			for (int j = 0; j < 8; ++j) {
				if (board[i][j] == 1) // ���� ���带 Ž���ϴµ� �װ��� �� ��ū�� ������
					cntBlack++; // cntBlack���� 1��ŭ ����
				else if (board[i][j] == -1) // ���� ���带 Ž���ϴµ� �װ��� ȭ��Ʈ ��ū�� ������
					cntWhite++; // cntWhite���� 1��ŭ ����
			}
	}

	// cntBlack�� ��ȯ�ϴ� �޼���
	public int getCntBlack() {
		return cntBlack;
	}

	// cntWhite�� ��ȯ�ϴ� �޼���
	public int getCntWhite() {
		return cntWhite;
	}

}