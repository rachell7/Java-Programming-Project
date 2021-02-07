import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	static Clip clip; // 파일을 저장하기 위한 클립

	public void playBackgroundSound1() { // 제일 처음 실행되는 배경 음악
		File file = new File("musics/turtle.wav"); // 파일 생성

		try {
			if (clip == null) { // 만약 재생되는 음악이 없으면
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
				clip = AudioSystem.getClip();
				clip.open(audioIn); // 클립 열기

				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				// 음악 파일의 볼륨을 제어할 수 있게
				gainControl.setValue((float) -25); // 볼륨 0~100 에서 50을 기준으로 재생
			}
			if (!clip.isRunning()) { // 만약 재생되는 음악이 없으면
				clip.loop(100); // 100번 반복재생
			}
		} catch (Exception e) { // 예외 처리
			e.printStackTrace();
		} // try..catch
	} // playBackgroundSound1()

	public void volume_playBackgroundSound1(int bgmVolume) { // 제일 처음 실행되는 배경 음악 볼륨 조절 메서드
		float set; // 볼륨 값

		set = (bgmVolume - 100) / 2; // -25를 50으로 정했기 때문에 그것을 기준으로 받아온 볼륨 값을 계산

		File file = new File("musics/turtle.wav"); // 파일 생성

		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
			clip.open(audioIn); // 클립 열기

			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			// 음악 파일의 볼륨을 제어할 수 있게
			gainControl.setValue((float) set); // set값으로 지정된 볼륨 재생
			if (!clip.isRunning()) { // 만약 재생되는 음악이 없으면
				clip.loop(100); // 100번 반복재생
			}
		} catch (Exception e) { // 예외 처리
			e.printStackTrace();
		} // try..catch
	} // volume_playBackgroundSound1()

	public void volume_playBackgroundSound2(int bgmVolume) { // 두번째로 재생되는 배경 음악
		float set; // 볼륨 값

		set = (bgmVolume - 100) / 2; // -25를 50으로 정했기 때문에 그것을 기준으로 받아온 볼륨 값을 계산

		File file = new File("musics/introMusic.wav"); // 파일 생성

		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
			clip.open(audioIn); // 클립 열기

			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			// 음악 파일의 볼륨을 제어할 수 있게
			gainControl.setValue((float) set); // set값으로 지정된 볼륨 재생
			if (!clip.isRunning()) { // 만약 재생되는 음악이 없으면
				clip.loop(100); // 100번 반복재생
			} // if
		} catch (Exception e) { // 예외 처리
			e.printStackTrace();
		} // try..catch
	} // volume_playBackgroundSound2()

	public void volume_playBackgroundSound3(int bgmVolume) { // 세번째로 재생되는 배경 음악
		float set; // 볼륨 값

		set = (bgmVolume - 100) / 2; // -25를 50으로 정했기 때문에 그것을 기준으로 받아온 볼륨 값을 계산

		File file = new File("musics/rainyday.wav"); // 파일 생성

		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
			clip.open(audioIn); // 클립 열기

			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			// 음악 파일의 볼륨을 제어할 수 있게
			gainControl.setValue((float) set); // set값으로 지정된 볼륨 재생
			if (!clip.isRunning()) { // 만약 재생되는 음악이 없으면
				clip.loop(100); // 100번 반복재생
			}
		} catch (Exception e) { // 예외 처리
			e.printStackTrace();
		} // try..catch
	} // volume_playBackgroundSound3()

	public void stopBackgroundSound() { // 배경 음악을 멈춤
		if (clip == null)
			return; // 클립이 없으면 반환
		else { // 클립이 있으면
			clip.stop(); // 클립을 멈추고
			clip.close(); // 클립을 닫음
		} // if..else
	} // stopBackgroundSound()

	public void buttonSpecialEffect() { // 버튼 효과음
		File effect2 = new File("musics/뿅.wav"); // 파일 생성

		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(effect2);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn); // 클립 열기

			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			// 음악 파일의 볼륨을 제어할 수 있게
			gainControl.setValue((float) -25); // 볼륨 0~100 에서 50을 기준으로 재생

			clip.start(); // 클립 재생
		} catch (Exception e) { // 예외 처리
			e.printStackTrace();
		} // try..catch
	} // buttonSpecialEffect()

	public void volume_buttonSpecialEffect(int sfxVolume) { // 버튼 효과음 볼륨 조절 메서드
		float set; // 볼륨 값

		set = (sfxVolume - 100) / 2; // -25를 50으로 정했기 때문에 그것을 기준으로 받아온 볼륨 값을 계산

		File effect2 = new File("musics/뿅.wav"); // 파일 생성

		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(effect2);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn); // 클립 열기

			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			// 음악 파일의 볼륨을 제어할 수 있게
			gainControl.setValue((float) set); // set값으로 지정된 볼륨 재생

			clip.start(); // 클립 재생
		} catch (Exception e) { // 예외 처리
			e.printStackTrace();
		} // try..catch
	} // volume_buttonSpecialEffect()

	public void startSpecialEffect() { // 게임이 시작될 때 효과음
		File effect3 = new File("musics/마리오.wav"); // 파일 생성

		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(effect3);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn); // 클립 열기

			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			// 음악 파일의 볼륨을 제어할 수 있게
			gainControl.setValue((float) -25); // 볼륨 0~100 에서 50을 기준으로 재생

			clip.start(); // 클립 재생
		} catch (Exception e) { // 예외 처리
			e.printStackTrace();
		} // try..catch
	} // startSpecialEffect()

	public void volume_startSpecialEffect(int sfxVolume) { // 게임이 시작될 때 효과음 볼륨 조절 메서드
		float set; // 볼륨 값

		set = (sfxVolume - 100) / 2; // -25를 50으로 정했기 때문에 그것을 기준으로 받아온 볼륨 값을 계산

		File effect3 = new File("musics/마리오.wav"); // 파일 생성

		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(effect3);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn); // 클립 열기

			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			// 음악 파일의 볼륨을 제어할 수 있게
			gainControl.setValue((float) set); // set값으로 지정된 볼륨 재생

			clip.start(); // 클립 재생
		} catch (Exception e) { // 예외 처리
			e.printStackTrace();
		} // try..catch
	} // volume_startSpecialEffect()

	public void endSpecialEffect() { // 게임이 끝날 때 효과음
		File effect4 = new File("musics/예에에.wav"); // 파일 생성

		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(effect4);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn); // 클립 열기

			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			// 음악 파일의 볼륨을 제어할 수 있게
			gainControl.setValue((float) -25); // 볼륨 0~100 에서 50을 기준으로 재생

			clip.start(); // 클립 재생
		} catch (Exception e) { // 예외 처리
			e.printStackTrace();
		} // try..catch
	} // endSpecialEffect()

	public void volume_endSpecialEffect(int sfxVolume) { // 게임이 끝날 때 효과음 볼륨 조절 메서드
		float set; // 볼륨 값

		set = (sfxVolume - 100) / 2; // -25를 50으로 정했기 때문에 그것을 기준으로 받아온 볼륨 값을 계산

		File effect4 = new File("musics/예에에.wav"); // 파일 생성

		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(effect4);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn); // 클립 열기

			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			// 음악 파일의 볼륨을 제어할 수 있게
			gainControl.setValue((float) set); // set값으로 지정된 볼륨 재생

			clip.start(); // 클립 재생
		} catch (Exception e) { // 예외 처리
			e.printStackTrace();
		} // try..catch
	} // volume_endSpecialEffect()
} // Sound class