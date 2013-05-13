package be.ehb.student.jorisderijck.Villagers2Js.utils;

public class Coord {

	
	private int x;
	private int y;
	private int z;
	
	public Coord()
	{
		this.setX(0).setY(0).setZ(0);
	}
	public Coord(int x,int y, int z)
	{
		this.setX(x).setY(y).setZ(z);
	}
	
	public Coord setX(int _x)
	{
		this.x = _x;
		return this;
	}
	public Coord setY(int _y)
	{
		this.y = _y;
		return this;
	}
	public Coord setZ(int _z)
	{
		this.z = _z;
		return this;
	}
	public int getX(){return this.x;}
	public int getY(){return this.y;}
	public int getZ(){return this.z;}
	
	// code for transforming it for saving
	
	// helper code for other format data
	public Integer[] toArray()
	{
		Integer[] ret = {this.x,this.y,this.z};
		return ret;
	}
}
