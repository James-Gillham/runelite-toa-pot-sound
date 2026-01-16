package com.james.potsoundplugin;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PotSoundPluginTest {
	public static void main(String[] args) throws Exception {
		ExternalPluginManager.loadBuiltin(PotSoundPlugin.class);
		RuneLite.main(args);
	}
}