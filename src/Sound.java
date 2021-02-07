import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	static Clip clip; // ������ �����ϱ� ���� Ŭ��

	public void playBackgroundSound1() { // ���� ó�� ����Ǵ� ��� ����
		File file = new File("musics/turtle.wav"); // ���� ����

		try {
			if (clip == null) { // ���� ����Ǵ� ������ ������
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
				clip = AudioSystem.getClip();
				clip.open(audioIn); // Ŭ�� ����

				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				// ���� ������ ������ ������ �� �ְ�
				gainControl.setValue((float) -25); // ���� 0~100 ���� 50�� �������� ���
			}
			if (!clip.isRunning()) { // ���� ����Ǵ� ������ ������
				clip.loop(100); // 100�� �ݺ����
			}
		} catch (Exception e) { // ���� ó��
			e.printStackTrace();
		} // try..catch
	} // playBackgroundSound1()

	public void volume_playBackgroundSound1(int bgmVolume) { // ���� ó�� ����Ǵ� ��� ���� ���� ���� �޼���
		float set; // ���� ��

		set = (bgmVolume - 100) / 2; // -25�� 50���� ���߱� ������ �װ��� �������� �޾ƿ� ���� ���� ���

		File file = new File("musics/turtle.wav"); // ���� ����

		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
			clip.open(audioIn); // Ŭ�� ����

			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			// ���� ������ ������ ������ �� �ְ�
			gainControl.setValue((float) set); // set������ ������ ���� ���
			if (!clip.isRunning()) { // ���� ����Ǵ� ������ ������
				clip.loop(100); // 100�� �ݺ����
			}
		} catch (Exception e) { // ���� ó��
			e.printStackTrace();
		} // try..catch
	} // volume_playBackgroundSound1()

	public void volume_playBackgroundSound2(int bgmVolume) { // �ι�°�� ����Ǵ� ��� ����
		float set; // ���� ��

		set = (bgmVolume - 100) / 2; // -25�� 50���� ���߱� ������ �װ��� �������� �޾ƿ� ���� ���� ���

		File file = new File("musics/introMusic.wav"); // ���� ����

		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
			clip.open(audioIn); // Ŭ�� ����

			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			// ���� ������ ������ ������ �� �ְ�
			gainControl.setValue((float) set); // set������ ������ ���� ���
			if (!clip.isRunning()) { // ���� ����Ǵ� ������ ������
				clip.loop(100); // 100�� �ݺ����
			} // if
		} catch (Exception e) { // ���� ó��
			e.printStackTrace();
		} // try..catch
	} // volume_playBackgroundSound2()

	public void volume_playBackgroundSound3(int bgmVolume) { // ����°�� ����Ǵ� ��� ����
		float set; // ���� ��

		set = (bgmVolume - 100) / 2; // -25�� 50���� ���߱� ������ �װ��� �������� �޾ƿ� ���� ���� ���

		File file = new File("musics/rainyday.wav"); // ���� ����

		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
			clip.open(audioIn); // Ŭ�� ����

			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			// ���� ������ ������ ������ �� �ְ�
			gainControl.setValue((float) set); // set������ ������ ���� ���
			if (!clip.isRunning()) { // ���� ����Ǵ� ������ ������
				clip.loop(100); // 100�� �ݺ����
			}
		} catch (Exception e) { // ���� ó��
			e.printStackTrace();
		} // try..catch
	} // volume_playBackgroundSound3()

	public void stopBackgroundSound() { // ��� ������ ����
		if (clip == null)
			return; // Ŭ���� ������ ��ȯ
		else { // Ŭ���� ������
			clip.stop(); // Ŭ���� ���߰�
			clip.close(); // Ŭ���� ����
		} // if..else
	} // stopBackgroundSound()

	public void buttonSpecialEffect() { // ��ư ȿ����
		File effect2 = new File("musics/��.wav"); // ���� ����

		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(effect2);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn); // Ŭ�� ����

			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			// ���� ������ ������ ������ �� �ְ�
			gainControl.setValue((float) -25); // ���� 0~100 ���� 50�� �������� ���

			clip.start(); // Ŭ�� ���
		} catch (Exception e) { // ���� ó��
			e.printStackTrace();
		} // try..catch
	} // buttonSpecialEffect()

	public void volume_buttonSpecialEffect(int sfxVolume) { // ��ư ȿ���� ���� ���� �޼���
		float set; // ���� ��

		set = (sfxVolume - 100) / 2; // -25�� 50���� ���߱� ������ �װ��� �������� �޾ƿ� ���� ���� ���

		File effect2 = new File("musics/��.wav"); // ���� ����

		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(effect2);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn); // Ŭ�� ����

			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			// ���� ������ ������ ������ �� �ְ�
			gainControl.setValue((float) set); // set������ ������ ���� ���

			clip.start(); // Ŭ�� ���
		} catch (Exception e) { // ���� ó��
			e.printStackTrace();
		} // try..catch
	} // volume_buttonSpecialEffect()

	public void startSpecialEffect() { // ������ ���۵� �� ȿ����
		File effect3 = new File("musics/������.wav"); // ���� ����

		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(effect3);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn); // Ŭ�� ����

			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			// ���� ������ ������ ������ �� �ְ�
			gainControl.setValue((float) -25); // ���� 0~100 ���� 50�� �������� ���

			clip.start(); // Ŭ�� ���
		} catch (Exception e) { // ���� ó��
			e.printStackTrace();
		} // try..catch
	} // startSpecialEffect()

	public void volume_startSpecialEffect(int sfxVolume) { // ������ ���۵� �� ȿ���� ���� ���� �޼���
		float set; // ���� ��

		set = (sfxVolume - 100) / 2; // -25�� 50���� ���߱� ������ �װ��� �������� �޾ƿ� ���� ���� ���

		File effect3 = new File("musics/������.wav"); // ���� ����

		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(effect3);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn); // Ŭ�� ����

			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			// ���� ������ ������ ������ �� �ְ�
			gainControl.setValue((float) set); // set������ ������ ���� ���

			clip.start(); // Ŭ�� ���
		} catch (Exception e) { // ���� ó��
			e.printStackTrace();
		} // try..catch
	} // volume_startSpecialEffect()

	public void endSpecialEffect() { // ������ ���� �� ȿ����
		File effect4 = new File("musics/������.wav"); // ���� ����

		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(effect4);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn); // Ŭ�� ����

			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			// ���� ������ ������ ������ �� �ְ�
			gainControl.setValue((float) -25); // ���� 0~100 ���� 50�� �������� ���

			clip.start(); // Ŭ�� ���
		} catch (Exception e) { // ���� ó��
			e.printStackTrace();
		} // try..catch
	} // endSpecialEffect()

	public void volume_endSpecialEffect(int sfxVolume) { // ������ ���� �� ȿ���� ���� ���� �޼���
		float set; // ���� ��

		set = (sfxVolume - 100) / 2; // -25�� 50���� ���߱� ������ �װ��� �������� �޾ƿ� ���� ���� ���

		File effect4 = new File("musics/������.wav"); // ���� ����

		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(effect4);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn); // Ŭ�� ����

			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			// ���� ������ ������ ������ �� �ְ�
			gainControl.setValue((float) set); // set������ ������ ���� ���

			clip.start(); // Ŭ�� ���
		} catch (Exception e) { // ���� ó��
			e.printStackTrace();
		} // try..catch
	} // volume_endSpecialEffect()
} // Sound class