import java.awt.Point;

public class GameManager {
	public Token currentTurnToken; // 현재 차례의 Token을 저장하는 변수
	private GameBoard currentBoard; // 현제 GameBoard를 저장하는 변수
	public int successiveCntSkip; // 연속적으로 발생한 skip횟수를 저장하는 변수
	private GameStartInfo info; // 게임이 시작할 때 정보를 저장하는 변수

	// 생성자
	public GameManager() {
		this.currentBoard = new GameBoard();
		currentBoard.initBoard(); // board를 초기화 시킴
		successiveCntSkip = 0; // 0으로 초기화
		currentTurnToken = new Token(1);
	}

	// info를 설정
	public void setGameInfo(GameStartInfo info) {
		this.info = info;
	}

	// currentTurnToken을 입력으로 받은 Token으로 설정하는 메서드
	public void setCurrentToken(Token current) {
		currentTurnToken = current;
	}

	// x,y 인덱스가 게임판 내부인지 판단하는 메서드
	public static boolean isRange(int x, int y) {
		return x >= 0 && x < 8 && y >= 0 && y < 8;
	}

	public static boolean[][] findPossiblePosition(GameBoard currentBoard, int currentState) {
		boolean[][] possiblePos = new boolean[8][8];

		Point[] dir = new Point[8]; // dir은 방향벡터로 사용될 예정 오른쪽, 위쪽, 왼쪽, 아래쪽, 오른쪽 위 대각, 왼쪽 위 대각, 왼쪽 아래 대각, 오른쪽 아래 대각이
		// 들어갈 예정
		int x = 0;
		int y = 1;

		// 오른쪽, 위쪽, 왼쪽, 아래쪽, 오른쪽 위 대각, 왼쪽 위 대각, 왼쪽 아래 대각, 오른쪽 아래 대각 방향이 순서대로 dir에 저장됨
		for (int k = 0; k < 8; ++k) {
			if (k < 4) { // 오른쪽 부터 아래쪽까지 저장
				int tmp = x;
				x = y;
				y = tmp;

				if (k % 2 == 1) {
					x *= -1;
					y *= -1;
				}
			} else { // 오른쪽 부터 아래쪽 까지의 조합으로 대각 방향을 저장
				x = dir[k - 4].x + dir[(k - 3) % 4].x;
				y = dir[k - 4].y + dir[(k - 3) % 4].y;
			}

			dir[k] = new Point(x, y);
		}

		// 게임판의 모든 위치를 탐색
		for (int i = 0; i < 8; ++i)
			for (int j = 0; j < 8; ++j)
				if (currentBoard.board[i][j] == currentState) { // 현재 토큰과 같은 토큰이 올려져 있는 토큰을 발견하면
					for (int k = 0; k < 8; ++k) { // 모든 방향에 대해 수행
						int step = 1;

						int nextX = j + dir[k].x; // (i,j)에서 dir[k]방향으로 1칸 이동했을때 x위치를 저장
						int nextY = i + dir[k].y; // (i,j)에서 dir[k]방향으로 1칸 이동했을때 y위치를 저장

						// (nextX, nextY)이 범위 이내이고 다른색 토큰이 올려져 있을 때까지 반복
						while (isRange(nextX, nextY) && currentBoard.board[nextY][nextX] == -currentState) {
							step++;

							nextX = j + dir[k].x * step; // (i,j)에서 dir[k]방향으로 step만큼 이동했을때 x위치를 저장
							nextY = i + dir[k].y * step; // (i,j)에서 dir[k]방향으로 step만큼 이동했을때 y위치를 저장
						}

						// (nextX, nextY)가 범위이냉며 step 이상일 경우
						if (isRange(nextX, nextY) && step > 1) {
							if (currentBoard.board[nextY][nextX] == 0) // (nextY, nextX)가 비어있다면 이 자리는 위치 가능한 자리
								possiblePos[nextY][nextX] = true;
						}
					}
				}

		return possiblePos;
	}

	// 입력으로 들어온 위치가 가능한 곳인지를 확인하는 메서드
	public boolean isPossilbe(Point p) {
		boolean[][] possiblePos = GameManager.findPossiblePosition(currentBoard, currentTurnToken.getState()); // findPossiblePosition을
																												// 호출하여
																												// 가능한
																												// 위치
																												// 배열을
																												// 가져옴

		return possiblePos[p.y][p.x];
	}

	// skip을 해야되는지 확인하는 메서드
	public boolean canSkip() {
		boolean[][] possiblePos = GameManager.findPossiblePosition(currentBoard, currentTurnToken.getState()); // findPossiblePosition을
																												// 호출하여
																												// 가능한
																												// 위치
																												// 배열을
																												// 가져옴

		for (int i = 0; i < 8; ++i)
			for (int j = 0; j < 8; ++j)
				if (possiblePos[i][j] == true) // 가능한 곳이 있을 경우 스킵을 하면 안됨
					return false; // false반환

		// 가능한 위치가 하나도 없을 경우
		successiveCntSkip++; // skip이 발생할테니 연속적인 스킵 횟수를 저장하는 변수 값을 1증가
		return true;
	}

	// 게임판이 토큰으로 가득 찼는지 확인하는 메서드
	public boolean isFull() {
		// 게임판을 순회
		for (int i = 0; i < 8; ++i)
			for (int j = 0; j < 8; ++j)
				if (currentBoard.board[i][j] == 0) // 빈칸 발견
					return false; // false 반환

		// 가득 찼음
		return true;
	}

	// currentBoard를 반환하는 메서드
	public GameBoard getGameCurrentBoard() {
		return this.currentBoard;
	}
}