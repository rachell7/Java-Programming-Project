import java.awt.Point;

public class Computer {
	private Token myToken; // 컴퓨터의 토큰을 저장하는 변수
	private Point myPoint; // 컴퓨터가 토콘을 놓을 위치를 저장하는 변수

	public Computer(int color) { // color에 대한 정보를 int형 변수에다 담고, computer객체를 생성
		this.myToken = new Token(color);
		myPoint = new Point();
	}

	// dest에 src의 원소들을 복사
	private void copyElement(int[][] dest, int[][] src) {
		// dest와 src에는 8 x 8 배열만 들어오므로 i, j를 8까지 반복
		for (int i = 0; i < 8; ++i)
			for (int j = 0; j < 8; ++j)
				dest[i][j] = src[i][j];
	}

	// minimax알고리즘을 활용하여 myPoint에 적절한 위치를 저장
	private int sol(GameBoard currentBoard, int state, int depth, int turnSkip) {
		if (depth > 5 || turnSkip == 2) // 깊이가 깊어지거나 turnSkip이 2가 되면 (turnSkip이 2가 되면 게임이 끝났다는 의미임)
			return -state * (currentBoard.getCntBlack() - currentBoard.getCntWhite()); // 자신의 점수에서 상대의 점수를 뺀 것에다가 -1을 곱해
																						// 상대편 관점에서의 이익수치를 반환

		int ret = -987654321; // 작은 값으로 초기화
		GameBoard nextBoard = new GameBoard(); // nextBoard에 GameBoard객체를 할당

		boolean[][] possiblePos = GameManager.findPossiblePosition(currentBoard, state);// 현재 차례에서 놓을 수 있는 위치를
																						// possiblePos에 저장

		// 게임판은 8 x 8이므로 i, j를 8까지 반복
		for (int i = 0; i < 8; ++i)
			for (int j = 0; j < 8; ++j)
				if (possiblePos[i][j] == true) { // i행 j열이 위치 가능한 곳이면
					copyElement(nextBoard.board, currentBoard.board); // nextBoard의 board에 현제 게임판의 상태를 복사
					Point pos = new Point(j, i); // i,j값을 가진 Point객체 생성

					nextBoard.updateBoard(pos, state); // i행 j열에 현재 토큰을 위치시켰을 때 를 가정하여 nextBoard의 board를 업데이트

					int tmpRet = sol(nextBoard, -state, depth + 1, 0); // nextBoard와 차례를 바꾸며 깊이를 1만큼 더해 재귀호출,
					// pos를 위치 시켰을 때 이후 깊이 5에서 얻을 수 있는 최소 이익의 극대치가 저장됨

					// 게임판에서 구석을 점령하기 위해 구석에 위치할 경우 큰 값을 부여
					if (i == 0 && j == 0)
						tmpRet += 12 - depth; // depth를 빼주는 이유는 더 얕은 깊이에서의 구석이 선택 될 것이기 때문
					else if (i == 0 && j == 7)
						tmpRet += 12 - depth;
					else if (i == 7 && j == 0)
						tmpRet += 12 - depth;
					else if (i == 7 && j == 7)
						tmpRet += 12 - depth;

					if (ret < tmpRet) { // tmpRet의 값이 ret보다 더 클 경우
						ret = tmpRet; // ret의 값을 tmpRet으로 교체

						if (depth == 0) { // depth 0은 실제 상황에서 컴퓨터가 토큰을 둬야 되는 차례를 의미
							myPoint.x = j; // myPoint의 x에 j값을 넣어줌, (j는 행열에서 열을 의미)
							myPoint.y = i; // myPoint의 y에 i값을 넣어줌, (i는 행열에서 행을 의미)
						}
					}
				}

		// 컴퓨터가 놓을 자리가 없었을 경우
		if (ret == -987654321)
			ret = sol(currentBoard, -state, depth + 1, turnSkip + 1); // 현재 게임판을 유지하고 상대턴으로 넘김, 이때 turnSkip대신 turnSkip +
																		// 1을 넘겨줌

		// 반환 받는 함수에서는 상대 차례이므로 -1을 곱해서 전달해줌
		return -ret;
	}

	// 컴퓨터가 가지고 있는 Token을 반환하는 메서드
	public Token getMyToken() {
		return myToken;
	}

	// 컴퓨터가 둘 수 있는 최적의 수를 찾아 반환
	public Point searchBestPosition(GameBoard currentBoard, int state, int depth, int turnSkip) {
		sol(currentBoard, state, 0, turnSkip); // sol호출 => myPoint에 적절한 값이 저장됨

		return myPoint; // myPoint반환
	}
}