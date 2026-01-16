package com.james.potsoundplugin;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.client.audio.AudioPlayer;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@PluginDescriptor(name = "ToA Pot Sound")
public class PotSoundPlugin extends Plugin {
	@Inject
	private PotSoundConfig config;

	@Inject
	private AudioPlayer audioPlayer;

	@Override
	protected void startUp() throws Exception {
		log.info("ToA Pot Sound started!");
	}

	@Override
	protected void shutDown() throws Exception {
		log.info("ToA Pot Sound stopped!");
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked event) {
		if (!config.bankDepositSound()) {
			return;
		}

		String option = event.getMenuOption();

		if (option.startsWith("Deposit") || option.equals("Deposit inventory") || option.equals("Deposit equipment")) {
			playClip(config.soundPath());
		}
	}

	private void playClip(String path) {
		float gain = 20f * (float) Math.log10(config.announcementVolume() / 100f);

		try {
			File file = new File(path);
			if (file.exists()) {
				audioPlayer.play(file, gain);
			} else {
				// Add leading slash for getResourceAsStream if not present
				String resourcePath = path.startsWith("/") ? path : "/" + path;
				InputStream inputStream = getClass().getResourceAsStream(resourcePath);
				if (inputStream != null) {
					audioPlayer.play(inputStream, gain);
				} else {
					log.warn("Sound file not found in filesystem or resources: {}", path);
				}
			}
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
			log.warn("Failed to play sound {}", path, e);
		}
	}

	@Provides
	PotSoundConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(PotSoundConfig.class);
	}
}
