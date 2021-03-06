package com.eg.instrumental;

import org.junit.Test;

import java.util.Random;

/**
 * Created by bvarner on 12/19/14.
 */
public class AgentTest {

	private static long start = System.currentTimeMillis();

	private static Random r = new Random();

	@Test
	public void gaugeTest() {
		String apiKey = System.getProperty("instrumentalapp.apikey", "");

		if (!apiKey.equals("")) {
			Agent agent = new Agent(apiKey);
			agent.setSynchronous(false);

			for (int i = 1; i < 20; i++) {
				float val = r.nextFloat() * 100;
				agent.gauge("test.gauge", val);
			}

			while (agent.getPending() > 0) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException ie) {}
			}
		}

		// TODO: Assert the number of metrics sent.
	}

	@Test
	public void incrementTest() {
		String apiKey = System.getProperty("instrumentalapp.apikey", "");

		if (!apiKey.equals("")) {
			Agent agent = new Agent(apiKey);
			agent.setSynchronous(false);

			for (int i = 1; i < 20; i++) {
				agent.increment("test.increment");
			}

			while (agent.getPending() > 0) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException ie) {}
			}
		}

		// TODO: Assert the number of metrics sent.
	}

	@Test
	public void noticeTest() {
		String apiKey = System.getProperty("instrumentalapp.apikey", "");

		if (!apiKey.equals("")) {
			Agent agent = new Agent(apiKey);
			agent.setSynchronous(false);

			agent.notice("test.execution", (System.currentTimeMillis() - start) / 1000, start);

			while (agent.getPending() > 0) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException ie) {}
			}
		}
	}
}
