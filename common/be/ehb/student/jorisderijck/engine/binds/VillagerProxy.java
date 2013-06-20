package be.ehb.student.jorisderijck.engine.binds;

import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import be.ehb.student.jorisderijck.Villagers2Js.Villagers2Js;
import be.ehb.student.jorisderijck.Villagers2Js.entity.GenericVillager;


/**
 * class made so uses don't need to use obscrufaded function calls (those can change from version till version)
 * */
public class VillagerProxy implements IVillagerProxy {

	
	private GenericVillager agent;
	private PathNavigate navigator;
	
	public VillagerProxy(){}
	
	
	/**
	 * set's the agent for wich the proxy will be active.
	 * set's also the most used shortcut references.
	 * */
	public void setAgent(GenericVillager agent)
	{
		this.agent = agent;
		this.navigator = agent.getNavigator();
	}
	
	/* (non-Javadoc)
     * @see be.ehb.student.jorisderijck.engine.binds.facades.IVillagerProxy#setDestination(java.lang.Integer[])
     */
	@Override
    public boolean setDestination(Integer[] destination)
	{
	    return agent.getNavigator().tryMoveToXYZ(destination[0], destination[1], destination[2], 0.3F);
	}
	
	/* (non-Javadoc)
     * @see be.ehb.student.jorisderijck.engine.binds.facades.IVillagerProxy#getDestination()
     */
	@Override
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
	
	/* (non-Javadoc)
     * @see be.ehb.student.jorisderijck.engine.binds.facades.IVillagerProxy#getNavigator()
     */
	@Override
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
	
	/* (non-Javadoc)
     * @see be.ehb.student.jorisderijck.engine.binds.facades.IVillagerProxy#buildBlock(int, int, int, int)
     */
	@Override
    public void buildBlock(int x, int y,int z,int blockId)
	{
		if(Villagers2Js.DEBUG || this.agent.getInventory().getSlotForItem(blockId)!=-1)
		{
			this.agent.worldObj.setBlock(x, y, z, blockId);
			if(!Villagers2Js.DEBUG)
			{
				this.agent.getInventory().consumeItem(blockId);
			}
		}
	}
}
