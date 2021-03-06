/**	
Copyright (c) 2017 David Phung

Building on work by Mathew A. Nelson and Robocode contributors.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package se.lth.cs.etsa02.basicmeleebot;

/**
 * A class to help composing ETSA02 RoboTalk messages.
 * @author DavidPhung
 */
public class MessageWriter {
	
	private String leaderShip;
	private String teamMode;
	private String myPos;
	private String friendPos;
	private String[] enemyPos;
	private int enemyPosCount;
	private String targetEnemy;
	private String targetPos;
	private String moveTo;
	
	/**
	 * Construct a class to help composing a message.
	 */
	public MessageWriter() {
		leaderShip = new String();
		teamMode = new String();
		myPos = new String();
		friendPos = new String();
		enemyPos = new String[10];
		enemyPosCount = 0;
		targetEnemy = new String();
		targetPos = new String();
		moveTo = new String();
	}
	
	/**
	 * Add the leadership line.
	 * @param command
	 */
	public void addLeadership(String command) {
		leaderShip = "leaderShip;" + command;
	}
	
	/**
	 * Add the teamMode line.
	 * @param mode
	 */
	public void addTeamMode(String mode) {
		teamMode = "teamMode;" + mode;
	}
	
	/**
	 * Add the myPos line.
	 * @param x
	 * @param y
	 */
	public void addMyPos(double x, double y) {
		myPos = "myPos;" + x + ";" + y; 
	}
	
	/**
	 * Add the friendPos line.
	 * @param x
	 * @param y
	 */
	public void addFriendPos(double x, double y) {
		friendPos = "friendPos;" + x + ";" + y; 
	}
	
	/**
	 * Add an enemyPos line. Note: we can have multiple lines of this (at most 10).
	 * @param x
	 * @param y
	 */
	public void addEnemyPos(double x, double y) {
		enemyPos[enemyPosCount] = "enemyPos;" + x + ";" + y;
		enemyPosCount++;
	}
	
	/**
	 * Add the targetEnemy line.
	 * @param x
	 * @param y
	 */
	public void addTargetEnemy(String name) {
		targetEnemy = "targetEnemy;" + name;
	}
	
	/**
	 * Add the targetPos line.
	 * @param x
	 * @param y
	 */
	public void addTargetPos(double x, double y) {
		targetPos = "targetPos;" + x + ";" + y; 
	}
	
	/**
	 * Add the moveTo line.
	 * @param x
	 * @param y
	 */
	public void addMoveTo(double x, double y) {
		moveTo = "moveTo;" + x + ";" + y; 
	}
	
	/**
	 * Compose the message from the added lines.
	 * @return the message.
	 */
	public String composeMessage() {
		StringBuilder sb = new StringBuilder();
		addLine(sb, leaderShip);
		addLine(sb, teamMode);
		addLine(sb, myPos);
		addLine(sb, friendPos);
		for (int i = 0; i < enemyPosCount; i++) {
			addLine(sb, enemyPos[i]);
		}
		addLine(sb, targetEnemy);
		addLine(sb, targetPos);
		addLine(sb, moveTo);
		
		if (sb.charAt(sb.length() - 1) == '\n') {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}
	
	/**
	 * Add a line to the message.
	 * @param sb
	 * @param line
	 */
	private void addLine(StringBuilder sb, String line) {
		if (!line.isEmpty()) {
			sb.append(line);
			sb.append('\n');
		}
	}
}