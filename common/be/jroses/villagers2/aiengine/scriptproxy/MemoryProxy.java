package be.jroses.villagers2.aiengine.scriptproxy;

import be.jroses.villagers2.aiengine.core.AgentMemory;
import be.jroses.villagers2.entity.GenericVillager;

public class MemoryProxy {

	private static MemoryProxy instance;
	private AgentMemory memory;
	
	private MemoryProxy(){
	}
	
	public static MemoryProxy getInstance() {
		if (instance == null){
			instance = new MemoryProxy();
		}
		return instance;
	}

	public void setMemory(AgentMemory memory) {
		this.memory = memory;
	}

	public void save(String name,Object obj){
		this.memory.saveVariable(name, obj);
	}
	
	public void register(String name,String type)
	{
		this.memory.saveVariable(name, null);
	}
	public void load(String name){} // wrong return type I know..
}
