package be.jrose.villagers2.aiengine.scriptproxy;

import be.jrose.villagers2.aiengine.core.AgentMemory;
import be.jrose.villagers2.entity.GenericVillager;

public class MemoryProxy implements IMemoryProxy {

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

	@Override
	public void forget(String variableName) {
		this.memory.getMemory().remove(variableName);
	}

	@Override
	public void save(String variableName, Object value) {
		this.memory.getMemory().put(variableName, value);
	}

	@Override
	public Object load(String variableName) {
		return this.memory.getMemory().get(variableName);
	}

	@Override
	public Boolean has(String variableName) {
		return this.memory.getMemory().containsKey(variableName);
	}
	
	
}
