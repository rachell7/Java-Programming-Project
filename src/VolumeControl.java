public class VolumeControl {
	static Sound play = new Sound(); // 배경 음악과 효과음을 재생하기 위한 변수
	static int bgm = 50, sfx = 50; // 배경 음악, 효과음의 변경된 볼륨 값
	int ms = 0; // OK 버튼이 몇 번 눌렸는지 값 저장

	public int bgmvolumeValue(int bgmVolume) {
		bgm = bgmVolume; // 배경 음악의 볼륨 값을 저장
		return bgm; // 반환
	} // bgmvolumeValue()

	public int sfxvolumeValue(int sfxVolume) {
		sfx = sfxVolume; // 효과음의 볼륨 값을 저장
		return sfx; // 반환
	} // sfxvolumeValue()

	public int musicSelect(int cnt) {
		ms = cnt % 3; // OK 버튼이 몇 번 눌렸는지의 값을 3으로 나눈 나머지 값으로 저장
		return ms; // 반환
	} // musicSelect()

	public void bgmVolume() {
		if (bgm == 0) { // 볼륨 값이 0이면
			play.stopBackgroundSound(); // 재생되고 있는 배경 음악 멈춤
		} else if (bgm == 50 && ms == 0) { // 볼륨 값이 50이고 ms 값이 0이면
			play.stopBackgroundSound(); // 재생되고 있는 배경 음악 멈춤
			play.volume_playBackgroundSound1(bgm); // 첫번째 배경음악을 재생
		} else if (bgm == 50 && ms == 1) { // 볼륨 값이 50이고 ms 값이 1이면
			play.stopBackgroundSound(); // 재생되고 있는 배경 음악 멈춤
			play.volume_playBackgroundSound2(bgm); // 두번째 배경음악을 재생
		} else if (bgm == 50 && ms == 2) { // 볼륨 값이 50이고 ms 값이 2이면
			play.stopBackgroundSound(); // 재생되고 있는 배경 음악 멈춤
			play.volume_playBackgroundSound3(bgm); // 세번째 배경음악을 재생
		} else if (bgm != 50 && ms == 0) { // 볼륨 값이 50이 아니고 ms 값이 0이면
			play.stopBackgroundSound(); // 재생되고 있는 배경 음악 멈춤
			play.volume_playBackgroundSound1(bgm); // 변경된 볼륨 값으로 첫번째 배경음악을 재생
		} else if (bgm != 50 && ms == 1) { // 볼륨 값이 50이 아니고 ms 값이 1이면
			play.stopBackgroundSound(); // 재생되고 있는 배경 음악 멈춤
			play.volume_playBackgroundSound2(bgm); // 변경된 볼륨 값으로 두번째 배경음악을 재생
		} else if (bgm != 50 && ms == 2) { // 볼륨 값이 50이 아니고 ms 값이 2이면
			play.stopBackgroundSound(); // 재생되고 있는 배경 음악 멈춤
			play.volume_playBackgroundSound3(bgm); // 변경된 볼륨 값으로 세번째 배경음악을 재생
		} // if..else if
	} // bgmVolume()

	public void buttonVolume() {
		if (sfx == 50) { // 효과음 볼륨 값이 50이면
			play.buttonSpecialEffect(); // 버튼 효과음을 출력
		} else if (sfx == 0) { // 효과음 볼륨 값이 0이면
		} else { // 효과음 볼륨 값이 0, 50이 아니면
			play.volume_buttonSpecialEffect(sfx); // 변경된 값으로 버튼 효과음을 출력
		} // if..else
	} // buttonVolume()

	public void startVolume() {
		if (sfx == 50) { // 효과음 볼륨 값이 50이면
			play.startSpecialEffect(); // 시작 효과음을 출력
		} else if (sfx == 0) { // 효과음 볼륨 값이 0이면
		} else { // 효과음 볼륨 값이 0, 50이 아니면
			play.volume_startSpecialEffect(sfx); // 변경된 값으로 시작 효과음을 출력
		} // if..else
	} // startVolume()

	public void endVolume() {
		if (sfx == 50) { // 효과음 볼륨 값이 50이면
			play.endSpecialEffect(); // 엔딩 효과음을 출력
		} else if (sfx == 0) { // 효과음 볼륨 값이 0이면
		} else { // 효과음 볼륨 값이 0, 50이 아니면
			play.volume_endSpecialEffect(sfx); // 변경된 값으로 엔딩 효과음을 출력
		} // if..else
	} // endVolume()
} // VolumeControl class