package be.jroses.villagers2.aiengine.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.nbt.NBTTagCompound;

public class AgentMemory {

	private Map<String,Object> memoryBlock;
	
	public AgentMemory(){
		this.memoryBlock = new HashMap<String,Object>();
	}
	
	public void saveVariable(String variableName,Object theValue){this.memoryBlock.put(variableName, theValue);}
	public Object loadVariable(String variableName){return this.memoryBlock.get(variableName);}
	
	
	public void writeEntityToNBT(NBTTagCompound nbttag) {
		Iterator<Entry<String,Object>> itr = this.memoryBlock.entrySet().iterator();
		while(itr.hasNext())
		{
			Entry<String, Object> entry =  itr.next();
			if (entry.getValue() instanceof Byte){nbttag.setByte(entry.getKey(), (Byte)entry.getValue());}
			else if (entry.getValue() instanceof Integer){nbttag.setInteger(entry.getKey(), (Integer)entry.getValue());}
			else if (entry.getValue() instanceof Float){nbttag.setFloat(entry.getKey(), (Float)entry.getValue());}
			
			
			
			
			
			
			itr.remove();
		}
		
	}

	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {
	
	}
	
	

	
	public void setMemory(Map memory){this.memoryBlock = memory;}
	public Map getMemory(){return this.memoryBlock;}
}
