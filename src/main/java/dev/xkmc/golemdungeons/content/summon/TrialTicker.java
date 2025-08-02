package dev.xkmc.golemdungeons.content.summon;

public interface TrialTicker {

	void addCost(int cost, long time);

	void stop();

}
