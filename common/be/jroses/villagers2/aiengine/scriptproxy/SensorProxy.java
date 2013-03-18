package be.jroses.villagers2.aiengine.scriptproxy;

import be.jroses.villagers2.entity.GenericVillager;

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
	
	
}
