package be.jrose.villagers2.aiengine.core;

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
	
	public void writeEntityToNBT(NBTTagCompound nbttag) {
		Iterator<Entry<String,Object>> itr = this.memoryBlock.entrySet().iterator();
		while(itr.hasNext())
		{
			Entry<String, Object> entry =  itr.next();
			if (entry.getValue() instanceof Byte){nbttag.setByte(entry.getKey(), (Byte)entry.getValue());}
			else if (entry.getValue() instanceof Integer){nbttag.setInteger(entry.getKey(), (Integer)entry.getValue());}
			else if (entry.getValue() instanceof Long){nbttag.setLong(entry.getKey(), (Long)entry.getValue());}
			else if (entry.getValue() instanceof Float){nbttag.setFloat(entry.getKey(), (Float)entry.getValue());}
			else if (entry.getValue() instanceof Double){nbttag.setDouble(entry.getKey(), (Double)entry.getValue());}
			else if (entry.getValue() instanceof Boolean){nbttag.setBoolean(entry.getKey(), (Boolean)entry.getValue());}
			
			itr.remove();
		}
		
	}

	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {
	
	}
	
	public void setMemory(Map<String,Object> memory){this.memoryBlock = memory;}
	public Map<String,Object> getMemory(){return this.memoryBlock;}

}
