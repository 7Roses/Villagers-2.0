package be.ehb.student.jorisderijck.engine.binds;

import be.ehb.student.jorisderijck.Villagers2Js.entity.GenericVillager;

public class SensorProxy implements ISensorProxy {
	
	private GenericVillager agent;
	
	public void setAgent(GenericVillager agent) {
		this.agent = agent;
	}

	/* (non-Javadoc)
     * @see be.ehb.student.jorisderijck.engine.binds.facades.ISensorProxy#age()
     */
	@Override
    public int age()
	{
		return this.agent.getAge();
	}
	
	/* (non-Javadoc)
     * @see be.ehb.student.jorisderijck.engine.binds.facades.ISensorProxy#getPosition()
     */
	@Override
    public Integer[] getPosition()
	{
		Integer[] ret = new Integer[3];
		ret[0] = (int) this.agent.posX;
		ret[1] = (int) this.agent.posY;
		ret[2] = (int) this.agent.posZ;
		return ret;
	}
	
	/* (non-Javadoc)
     * @see be.ehb.student.jorisderijck.engine.binds.facades.ISensorProxy#getWorldTime()
     */
	@Override
    public Long getWorldTime()
	{
		return this.agent.worldObj.getWorldTime();
	}
	
}
