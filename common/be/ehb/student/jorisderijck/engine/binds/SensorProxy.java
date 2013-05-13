package be.ehb.student.jorisderijck.engine.binds;

import be.ehb.student.jorisderijck.Villagers2Js.entity.GenericVillager;

public class SensorProxy {

	private static SensorProxy instance;

	private SensorProxy(){}
	
	private GenericVillager agent;
	
	public void setAgent(GenericVillager agent) {
		this.agent = agent;
	}

	public static SensorProxy getInstance() {
		if (instance == null) {
			instance = new SensorProxy();
		}
		return instance;
	}

	public int age()
	{
		return this.agent.getAge();
	}
	
	public Integer[] getPosition()
	{
		Integer[] ret = new Integer[3];
		ret[0] = (int) this.agent.posX;
		ret[1] = (int) this.agent.posY;
		ret[2] = (int) this.agent.posZ;
		return ret;
	}
	
	public Long getWorldTime()
	{
		return this.agent.worldObj.getWorldTime();
	}
	
}
