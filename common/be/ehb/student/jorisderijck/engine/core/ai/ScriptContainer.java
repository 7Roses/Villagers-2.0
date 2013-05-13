package be.ehb.student.jorisderijck.engine.core.ai;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ScriptContainer {

	private List<Script> scripts;
	
	public ScriptContainer(){
		this.scripts = new ArrayList<Script>();
	}
	
    public  void readSensorScriptsFromNBT(NBTTagList list)
    {
    	this.scripts = new ArrayList<Script>();
    	for (int i = 0;i<list.tagCount();i++)
    	{
    		NBTTagCompound scriptTag = (NBTTagCompound) list.tagAt(i);
    		scripts.add(SensorScript.loadScriptFromNBT(scriptTag));
    	}
    }

    public  void readTickScriptsFromNBT(NBTTagList list)
    {
    	this.scripts = new ArrayList<Script>();
    	for (int i = 0;i<list.tagCount();i++)
    	{
    		NBTTagCompound scriptTag = (NBTTagCompound) list.tagAt(i);
    		scripts.add(SensorScript.loadScriptFromNBT(scriptTag));
    	}
    }
    
    public  NBTBase writeScriptsToNBT(NBTTagList list)
    {
    	for(Script s:scripts)
    	{
    		NBTTagCompound scriptTag = new NBTTagCompound();
    		s.writeEntityToNBT(scriptTag);
    		list.appendTag(scriptTag);
    	}
    	return list;
    }
    
    public int getScriptCount(){return this.scripts.size();}
    public void addScript(Script script)
    {
    	this.scripts.add(script);
    }
    public List<Script> getScripts()
    {
    	return this.scripts;
    }
}
