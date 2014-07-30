package de.pro_crafting.common;

import org.bukkit.Location;
import org.bukkit.World;

public class Point {
	private int x;
	private int y;
	private int z;

	public Point(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Point(Location from) {
		this.x = from.getBlockX();
		this.y = from.getBlockY();
		this.z = from.getBlockZ();
	}

	public Point(String loc) {
		String[] splited = loc.split(":");
		this.x = Integer.parseInt(splited[0]);
		this.y = Integer.parseInt(splited[1]);
		this.z = Integer.parseInt(splited[2]);
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getZ() {
		return this.z;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
	public Location toLocation(World world) {
		return new Location(world, x, y, z);
	}
	
	public String toString()
	{
		return x+":"+y+":"+z;
	}
	
	public void add(int x, int y, int z) {
		this.x += x;
		this.y += y;
		this.z += z;
	}
	
	public void add(Point value) {
		if (value == null) {
			return;
		}
		add(value.getX(), value.getY(), value.getZ());
	}
	
	public void subtract(int x, int y, int z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
	}
	
	public void subtract(Point value) {
		if (value == null) {
			return;
		}
		add(value.getX(), value.getY(), value.getZ());
	}
	
	public void multiply(int value) {
		this.x *= value;
		this.y *= value;
		this.z *= value;
	}
}