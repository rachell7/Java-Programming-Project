import java.awt.Point;

public class Computer {
	private Token myToken; // ��ǻ���� ��ū�� �����ϴ� ����
	private Point myPoint; // ��ǻ�Ͱ� ������ ���� ��ġ�� �����ϴ� ����

	public Computer(int color) { // color�� ���� ������ int�� �������� ���, computer��ü�� ����
		this.myToken = new Token(color);
		myPoint = new Point();
	}

	// dest�� src�� ���ҵ��� ����
	private void copyElement(int[][] dest, int[][] src) {
		// dest�� src���� 8 x 8 �迭�� �����Ƿ� i, j�� 8���� �ݺ�
		for (int i = 0; i < 8; ++i)
			for (int j = 0; j < 8; ++j)
				dest[i][j] = src[i][j];
	}

	// minimax�˰����� Ȱ���Ͽ� myPoint�� ������ ��ġ�� ����
	private int sol(GameBoard currentBoard, int state, int depth, int turnSkip) {
		if (depth > 5 || turnSkip == 2) // ���̰� ������ų� turnSkip�� 2�� �Ǹ� (turnSkip�� 2�� �Ǹ� ������ �����ٴ� �ǹ���)
			return -state * (currentBoard.getCntBlack() - currentBoard.getCntWhite()); // �ڽ��� �������� ����� ������ �� �Ϳ��ٰ� -1�� ����
																						// ����� ���������� ���ͼ�ġ�� ��ȯ

		int ret = -987654321; // ���� ������ �ʱ�ȭ
		GameBoard nextBoard = new GameBoard(); // nextBoard�� GameBoard��ü�� �Ҵ�

		boolean[][] possiblePos = GameManager.findPossiblePosition(currentBoard, state);// ���� ���ʿ��� ���� �� �ִ� ��ġ��
																						// possiblePos�� ����

		// �������� 8 x 8�̹Ƿ� i, j�� 8���� �ݺ�
		for (int i = 0; i < 8; ++i)
			for (int j = 0; j < 8; ++j)
				if (possiblePos[i][j] == true) { // i�� j���� ��ġ ������ ���̸�
					copyElement(nextBoard.board, currentBoard.board); // nextBoard�� board�� ���� �������� ���¸� ����
					Point pos = new Point(j, i); // i,j���� ���� Point��ü ����

					nextBoard.updateBoard(pos, state); // i�� j���� ���� ��ū�� ��ġ������ �� �� �����Ͽ� nextBoard�� board�� ������Ʈ

					int tmpRet = sol(nextBoard, -state, depth + 1, 0); // nextBoard�� ���ʸ� �ٲٸ� ���̸� 1��ŭ ���� ���ȣ��,
					// pos�� ��ġ ������ �� ���� ���� 5���� ���� �� �ִ� �ּ� ������ �ش�ġ�� �����

					// �����ǿ��� ������ �����ϱ� ���� ������ ��ġ�� ��� ū ���� �ο�
					if (i == 0 && j == 0)
						tmpRet += 12 - depth; // depth�� ���ִ� ������ �� ���� ���̿����� ������ ���� �� ���̱� ����
					else if (i == 0 && j == 7)
						tmpRet += 12 - depth;
					else if (i == 7 && j == 0)
						tmpRet += 12 - depth;
					else if (i == 7 && j == 7)
						tmpRet += 12 - depth;

					if (ret < tmpRet) { // tmpRet�� ���� ret���� �� Ŭ ���
						ret = tmpRet; // ret�� ���� tmpRet���� ��ü

						if (depth == 0) { // depth 0�� ���� ��Ȳ���� ��ǻ�Ͱ� ��ū�� �־� �Ǵ� ���ʸ� �ǹ�
							myPoint.x = j; // myPoint�� x�� j���� �־���, (j�� �࿭���� ���� �ǹ�)
							myPoint.y = i; // myPoint�� y�� i���� �־���, (i�� �࿭���� ���� �ǹ�)
						}
					}
				}

		// ��ǻ�Ͱ� ���� �ڸ��� ������ ���
		if (ret == -987654321)
			ret = sol(currentBoard, -state, depth + 1, turnSkip + 1); // ���� �������� �����ϰ� ��������� �ѱ�, �̶� turnSkip��� turnSkip +
																		// 1�� �Ѱ���

		// ��ȯ �޴� �Լ������� ��� �����̹Ƿ� -1�� ���ؼ� ��������
		return -ret;
	}

	// ��ǻ�Ͱ� ������ �ִ� Token�� ��ȯ�ϴ� �޼���
	public Token getMyToken() {
		return myToken;
	}

	// ��ǻ�Ͱ� �� �� �ִ� ������ ���� ã�� ��ȯ
	public Point searchBestPosition(GameBoard currentBoard, int state, int depth, int turnSkip) {
		sol(currentBoard, state, 0, turnSkip); // solȣ�� => myPoint�� ������ ���� �����

		return myPoint; // myPoint��ȯ
	}
}