package be.jrose.villagers2.aiengine.scriptproxy;

import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.Vec3;
import be.jrose.villagers2.Villagers2;
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
	    System.out.println("SET NAV IS BEEN CALLED !!!!");
	    boolean ret;
		boolean doorallowed = true;
		this.agent.getNavigator().clearPathEntity();
		return this.agent.getNavigator().tryMoveToXYZ((double)destination[0], (double)destination[1],(double)destination[2], 0.3F);
		/*
		PathEntity foundPath = this.agent.worldObj.getEntityPathToXYZ(this.agent, destination[0], destination[1], destination[2], 50, doorallowed, false, false, false);
		if (foundPath !=null)
		{
			System.out.println("my path exists!");	
		} else {
			System.out.println("NO PATH EXISTS???");
		}
		System.out.println(VillagerProxyDebug());
		ret = this.agent.getNavigator().setPath(foundPath, this.agent.getAIMoveSpeed());
		System.out.println(VillagerProxyDebug());
		return ret;*/
		
	}
	
	
	public void tmp()
	{
		
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
	
	
	public String VillagerProxyDebug()
	{
		StringBuilder sb = new StringBuilder("debug info for: "+ this.getClass().getName()+"\n");
		sb.append("this.agent: ").append(this.agent.toString())
			.append("\t (").append(this.agent.getClass().getName());
		sb.append('\n');
		sb.append("this.navigator: ").append(this.navigator.toString());
		sb.append('\n').append('\n');
		sb.append("this.navigator.getPath: ").append(this.navigator.getPath()==null?"NULL":this.navigator.getPath().getFinalPathPoint().toString());
		return sb.toString();
	}
	
	public void buildBlock(int x, int y,int z,int blockId)
	{
		/*if(this.agent.getInventory().hasItem(blockId) || Villagers2.DEBUG)
		{//*/
			this.agent.worldObj.setBlock(x, y, z, blockId);
			if(!Villagers2.DEBUG)
			{
				this.agent.getInventory().consumeItem(blockId);
			}
		//}
	}
}
