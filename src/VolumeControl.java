public class VolumeControl {
	static Sound play = new Sound(); // ��� ���ǰ� ȿ������ ����ϱ� ���� ����
	static int bgm = 50, sfx = 50; // ��� ����, ȿ������ ����� ���� ��
	int ms = 0; // OK ��ư�� �� �� ���ȴ��� �� ����

	public int bgmvolumeValue(int bgmVolume) {
		bgm = bgmVolume; // ��� ������ ���� ���� ����
		return bgm; // ��ȯ
	} // bgmvolumeValue()

	public int sfxvolumeValue(int sfxVolume) {
		sfx = sfxVolume; // ȿ������ ���� ���� ����
		return sfx; // ��ȯ
	} // sfxvolumeValue()

	public int musicSelect(int cnt) {
		ms = cnt % 3; // OK ��ư�� �� �� ���ȴ����� ���� 3���� ���� ������ ������ ����
		return ms; // ��ȯ
	} // musicSelect()

	public void bgmVolume() {
		if (bgm == 0) { // ���� ���� 0�̸�
			play.stopBackgroundSound(); // ����ǰ� �ִ� ��� ���� ����
		} else if (bgm == 50 && ms == 0) { // ���� ���� 50�̰� ms ���� 0�̸�
			play.stopBackgroundSound(); // ����ǰ� �ִ� ��� ���� ����
			play.volume_playBackgroundSound1(bgm); // ù��° ��������� ���
		} else if (bgm == 50 && ms == 1) { // ���� ���� 50�̰� ms ���� 1�̸�
			play.stopBackgroundSound(); // ����ǰ� �ִ� ��� ���� ����
			play.volume_playBackgroundSound2(bgm); // �ι�° ��������� ���
		} else if (bgm == 50 && ms == 2) { // ���� ���� 50�̰� ms ���� 2�̸�
			play.stopBackgroundSound(); // ����ǰ� �ִ� ��� ���� ����
			play.volume_playBackgroundSound3(bgm); // ����° ��������� ���
		} else if (bgm != 50 && ms == 0) { // ���� ���� 50�� �ƴϰ� ms ���� 0�̸�
			play.stopBackgroundSound(); // ����ǰ� �ִ� ��� ���� ����
			play.volume_playBackgroundSound1(bgm); // ����� ���� ������ ù��° ��������� ���
		} else if (bgm != 50 && ms == 1) { // ���� ���� 50�� �ƴϰ� ms ���� 1�̸�
			play.stopBackgroundSound(); // ����ǰ� �ִ� ��� ���� ����
			play.volume_playBackgroundSound2(bgm); // ����� ���� ������ �ι�° ��������� ���
		} else if (bgm != 50 && ms == 2) { // ���� ���� 50�� �ƴϰ� ms ���� 2�̸�
			play.stopBackgroundSound(); // ����ǰ� �ִ� ��� ���� ����
			play.volume_playBackgroundSound3(bgm); // ����� ���� ������ ����° ��������� ���
		} // if..else if
	} // bgmVolume()

	public void buttonVolume() {
		if (sfx == 50) { // ȿ���� ���� ���� 50�̸�
			play.buttonSpecialEffect(); // ��ư ȿ������ ���
		} else if (sfx == 0) { // ȿ���� ���� ���� 0�̸�
		} else { // ȿ���� ���� ���� 0, 50�� �ƴϸ�
			play.volume_buttonSpecialEffect(sfx); // ����� ������ ��ư ȿ������ ���
		} // if..else
	} // buttonVolume()

	public void startVolume() {
		if (sfx == 50) { // ȿ���� ���� ���� 50�̸�
			play.startSpecialEffect(); // ���� ȿ������ ���
		} else if (sfx == 0) { // ȿ���� ���� ���� 0�̸�
		} else { // ȿ���� ���� ���� 0, 50�� �ƴϸ�
			play.volume_startSpecialEffect(sfx); // ����� ������ ���� ȿ������ ���
		} // if..else
	} // startVolume()

	public void endVolume() {
		if (sfx == 50) { // ȿ���� ���� ���� 50�̸�
			play.endSpecialEffect(); // ���� ȿ������ ���
		} else if (sfx == 0) { // ȿ���� ���� ���� 0�̸�
		} else { // ȿ���� ���� ���� 0, 50�� �ƴϸ�
			play.volume_endSpecialEffect(sfx); // ����� ������ ���� ȿ������ ���
		} // if..else
	} // endVolume()
} // VolumeControl class