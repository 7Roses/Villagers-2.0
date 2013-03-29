package be.jrose.villagers2.aiengine.scriptproxy;

import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.Vec3;
import be.jrose.villagers2.entity.GenericVillager;


/**
 * class made so uses don't need to use obscrufaded function calls (those can change from version till version)
 * */
public class VillagerProxy {

	
	private static VillagerProxy instance;
	private GenericVillager agent;
	private PathNavigate navigator;
	
	private VillagerProxy(){}
	
	public static VillagerProxy getInstance()
	{
		if (instance == null)
		{
			instance = new VillagerProxy();
		}
		return instance;
	}
	
	/**
	 * set's the agent for wich the proxy will be active.
	 * set's also the most used shortcut references.
	 * */
	public void setAgent(GenericVillager agent)
	{
		this.agent = agent;
		this.navigator = agent.getNavigator();
	}
	
	public boolean setDestination(Integer[] destination)
	{
		//PathEntity path = this.navigator.getPathToXYZ(destination[0], destination[1], destination[2]);
		System.out.println("before try");
		boolean succeed = this.navigator.tryMoveToXYZ(destination[0], destination[1], destination[2], this.agent.getAIMoveSpeed());
		System.out.println("after try with result: "+succeed);
		PathEntity newPath = this.navigator.getPathToXYZ(destination[0], destination[1], destination[2]);
		this.navigator.setPath(newPath, this.agent.getAIMoveSpeed());
		if (succeed)
		{
			System.out.println("Succesfull set desitination");
			System.out.println(this.navigator.getPath());
			System.out.println("x: "+this.navigator.getPath().getFinalPathPoint().xCoord +
					"\ty: "+this.navigator.getPath().getFinalPathPoint().yCoord +
					"\tz: "+this.navigator.getPath().getFinalPathPoint().zCoord);
			
			return true;
		}
		System.out.println("set location to: "+
					"/tx: "+destination[0] +
					"/ty: "+destination[1] + 
					"/tz: "+destination[2]);
		
		 return false;
		//this.navigator.setPath(path, this.agent.getAIMoveSpeed());
	}
	
	public Integer[] getDestination()
	{
		Integer[] ret = {0,0,0};
		if (this.navigator.getPath() != null)
		{
			ret[0] = this.navigator.getPath().getFinalPathPoint().xCoord;
			ret[1] = this.navigator.getPath().getFinalPathPoint().yCoord;
			ret[2] = this.navigator.getPath().getFinalPathPoint().zCoord;
		}
		return ret; 
	}
	
	public PathNavigate getNavigator()
	{
		return this.navigator;
	}
	
}
