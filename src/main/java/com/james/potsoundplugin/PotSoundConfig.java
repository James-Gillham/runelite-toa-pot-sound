package com.james.potsoundplugin;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;

@ConfigGroup("potsound")
public interface PotSoundConfig extends Config {
	@ConfigItem(keyName = "bankDepositSound", name = "Bank Deposit Sound", description = "Play a sound when depositing items into the bank", position = 1)
	default boolean bankDepositSound() {
		return true;
	}

	@Range(min = 0, max = 100)
	@ConfigItem(keyName = "announcementVolume", name = "Announcement Volume", description = "The volume of the sound announcement", position = 2)
	default int announcementVolume() {
		return 100;
	}

	@ConfigItem(keyName = "soundPath", name = "Sound File Path", description = "The path to the .wav file to play (defaults to bundled deposit.wav)", position = 3)
	default String soundPath() {
		return "deposit.wav";
	}
}
