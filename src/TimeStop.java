public class TimeStop extends Thread {
	public void run() { // 1�� ���� Thread�� ���������ִ� �޼���
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

		}
	}
}