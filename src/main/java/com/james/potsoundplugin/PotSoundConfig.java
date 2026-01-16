package com.james.potsoundplugin;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;

@ConfigGroup("potsound")
public interface PotSoundConfig extends Config {

	@Range(min = 0, max = 100)
	@ConfigItem(keyName = "potVolume", name = "Pot Sound Volume", description = "The volume of the pot sound", position = 1)
	default int potVolume() {
		return 100;
	}

}
