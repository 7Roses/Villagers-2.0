package be.ehb.student.jorisderijck.engine.binds;

import be.ehb.student.jorisderijck.engine.core.ai.memory.Memory;
import be.ehb.student.jorisderijck.engine.scriptutils.communication.Message;

public class MemoryProxy implements IMemoryProxy{

	private Memory memory;
	
	public MemoryProxy(){
	}
	
	public void setMemory(Memory memory) {
		this.memory = memory;
	}

	/* (non-Javadoc)
     * @see be.ehb.student.jorisderijck.engine.binds.facades.IMemoryProxy#forget(java.lang.String)
     */
	@Override
    public void forget(String variableName) {
		this.memory.remove(variableName);
	}

	/* (non-Javadoc)
     * @see be.ehb.student.jorisderijck.engine.binds.facades.IMemoryProxy#save(java.lang.String, java.lang.Object)
     */
	@Override
    public void save(String variableName, Object value) {
		this.memory.setObject(variableName, value);
	}

	/* (non-Javadoc)
     * @see be.ehb.student.jorisderijck.engine.binds.facades.IMemoryProxy#load(java.lang.String)
     */
	@Override
    public Object load(String variableName) {
		return this.memory.getObject(variableName);
	}

	/* (non-Javadoc)
     * @see be.ehb.student.jorisderijck.engine.binds.facades.IMemoryProxy#has(java.lang.String)
     */
	@Override
    public Boolean has(String variableName) {
		return this.memory.hasKey(variableName);
	}

    @Override
    public Message getNextMessage(int time)
    {
            return this.memory.getMessage(time);
    }
	
	
}
