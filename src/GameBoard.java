import java.awt.Point;

public class GameBoard {
	// black 1, white -1
	public int board[][]; // 게임판의 상태를 저장하는 2차원 배열, blackToken: 1, whiteToken: -1, empty: 0
	private int cntBlack; // 게임판에서 검은색 토큰의 수를 저장하는 변수
	private int cntWhite; // 게임판에서 흰색 토큰의 수를 저장하는 변수

	public GameBoard() { // 생성자
		board = new int[8][8];

		initBoard();
	}

	public void initBoard() { // 게임판을 초기상태로 초기화 시키는 메서드
		// 게임판 모든 곳을 빈칸으로 만듦
		for (int i = 0; i < 8; ++i)
			for (int j = 0; j < 8; ++j)
				board[i][j] = 0;

		// 검은색 토큰 2개, 흰색 토큰 2개가 중앙에 있다는 것을 의미
		board[3][3] = -1;
		board[4][4] = -1;
		board[3][4] = 1;
		board[4][3] = 1;

		// 각 토큰이 2개 씩 있으므로 2로 초기화
		cntBlack = cntWhite = 2;
	}

	// p에 어떤 색 토큰을 위치시켰을 때 생기는 변화를 board에 적용시키는 메서드
	public void updateBoard(Point p, int currentState) {
		board[p.y][p.x] = currentState; // p에 currentState색의 토큰을 위치시킴, currentState가 1이면 black, -1이면 white

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

		// 모든 방향에 대해 반복을 위해 8까지 반복
		for (int k = 0; k < 8; ++k) {
			int step = 1;

			int nextX = p.x + dir[k].x * step; // p에서 dir[k]방향으로 1만큼 갔을 때 x값을 저장
			int nextY = p.y + dir[k].y * step; // p에서 dir[k]방향으로 1만큼 갔을 때 y값을 저장

			// nextX와 nextY가 게임판 내부이며 그 위치에 다른 색의 토큰이 있을 때 까지 반복
			while (GameManager.isRange(nextX, nextY) && board[nextY][nextX] == -currentState) {
				// step 값을 증가시킴
				step++;

				nextX = p.x + dir[k].x * step; // p에서 dir[k]방향으로 step만큼 갔을 때 x값을 저장
				nextY = p.y + dir[k].y * step; // p에서 dir[k]방향으로 step만큼 갔을 때 y값을 저장
			}

			// nextX와 nextY가 범위이내, (nextX,nextY)에 위치한 토큰이 현재 차례와 같고 step이 1보다 클 경우
			if (GameManager.isRange(nextX, nextY) && board[nextY][nextX] == currentState && step > 1) {
				Point reverseDir = new Point(-dir[k].x, -dir[k].y); // 역 방향 벡터 생성

				int prevX = nextX + reverseDir.x; // nextX에서 reverseDir[k]방향으로 1만큼 갔을 때 x값을 저장
				int prevY = nextY + reverseDir.y; // nextY에서 reverseDir[k]방향으로 1만큼 갔을 때 y값을 저장

				// 역 방향으로 진행하면서 토큰들을 currentState색으로 바꿔줌
				while (GameManager.isRange(prevX, prevY) && board[prevY][prevX] == -currentState) {
					board[prevY][prevX] = currentState;

					prevX += reverseDir.x;
					prevY += reverseDir.y;
				}

			}
		}

		updateEachCnt(); // cntBlack과 cntWhite도 업데이트
	}

	// 보드 상태를 보고 cntBlack과 cntWhite를 갱신하는 메서드
	public void updateEachCnt() {
		cntBlack = 0; // cntBlack, cntWhite를 0으로 초기화
		cntWhite = 0;

		for (int i = 0; i < 8; ++i)
			for (int j = 0; j < 8; ++j) {
				if (board[i][j] == 1) // 게임 보드를 탐색하는데 그곳에 블랙 토큰이 있으면
					cntBlack++; // cntBlack값을 1만큼 증가
				else if (board[i][j] == -1) // 게임 보드를 탐색하는데 그곳에 화이트 토큰이 있으면
					cntWhite++; // cntWhite값을 1만큼 증가
			}
	}

	// cntBlack을 반환하는 메서드
	public int getCntBlack() {
		return cntBlack;
	}

	// cntWhite를 반환하는 메서드
	public int getCntWhite() {
		return cntWhite;
	}

}