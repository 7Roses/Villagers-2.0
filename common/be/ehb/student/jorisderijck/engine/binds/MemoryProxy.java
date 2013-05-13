package be.ehb.student.jorisderijck.engine.binds;

import be.ehb.student.jorisderijck.engine.core.ai.memory.Memory;

public class MemoryProxy{

	private Memory memory;
	
	public MemoryProxy(){
	}
	
	public void setMemory(Memory memory) {
		this.memory = memory;
	}

	public void forget(String variableName) {
		this.memory.remove(variableName);
	}

	public void save(String variableName, Object value) {
		this.memory.setObject(variableName, value);
	}

	public Object load(String variableName) {
		return this.memory.getObject(variableName);
	}

	public Boolean has(String variableName) {
		return this.memory.hasKey(variableName);
	}
	
	
}
