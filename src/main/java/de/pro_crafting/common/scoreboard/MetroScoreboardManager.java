package de.pro_crafting.common.scoreboard;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class MetroScoreboardManager<T> {
	private Map<T, Scoreboard> scoreboards;
	
	public MetroScoreboardManager() {
		this.scoreboards = new HashMap<T, Scoreboard>();
	}
	
	public int getScore(T key, String name, String objectiveName) {
		Score score = getScoreObject(key, objectiveName, name);
		if (score != null) {
			return score.getScore();
		}
		return 0;
	}
	
	public void setScore(T key, String name, int value, String objectiveName) {
		Score score = getScoreObject(key, objectiveName, name);
		if (score != null) {
			score.setScore(value);
		}
	}
	
	public void changeScoreName(T key, String originalName, String objectiveName, String name) {
		Score oldScore = getScoreObject(key, objectiveName, originalName);
		getScoreObject(key, objectiveName, name).setScore(oldScore.getScore());
		this.getScoreboard(key).resetScores(originalName);
	}
	
	public void removeScore(T key, String name) {
		this.getScoreboard(key).resetScores(name);
	}
	
	public void showObjective(T key, String objectiveName, DisplaySlot slot) {
		Scoreboard board = getScoreboard(key);
		board.clearSlot(slot);
		Objective obj = board.getObjective(objectiveName);
		if (obj != null) {
			board.clearSlot(slot);
			obj.setDisplaySlot(slot);
		}
	}
	
	public void createObjective(T key, String name, DisplaySlot slot, Criteria criteria) {
		Scoreboard board = getScoreboard(key);
		if (board.getObjective(name) == null) {
			board.registerNewObjective(name, criteria.getCriteriaName());
			showObjective(key, name, slot);
		}
	}
	
	public void addPlayer(T key) {
		if (!scoreboards.containsKey(key)) {
			scoreboards.put(key, Bukkit.getScoreboardManager().getNewScoreboard());
		}
	}
	
	private Scoreboard getScoreboard(T key) {
		this.addPlayer(key);
		return scoreboards.get(key);
	}
	
	private Objective getObjective(T key, String name) {
		return this.getScoreboard(key).getObjective(name);
	}
	
	private Score getScoreObject(T key, String objective, String name) {
		Objective obj = getObjective(key, objective);
		if (obj != null) {
			return obj.getScore(name);
		}
		return null;
	}
}
