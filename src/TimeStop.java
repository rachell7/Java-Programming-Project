public class TimeStop extends Thread {
	public void run() { // 1초 동안 Thread를 중지시켜주는 메서드
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

		}
	}
}