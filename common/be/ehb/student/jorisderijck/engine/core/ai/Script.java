package be.ehb.student.jorisderijck.engine.core.ai;

import net.minecraft.nbt.NBTTagCompound;

public abstract class Script {

	public static byte NEW = 1;
	public static byte RUNNING = 2;
	public static byte SLEEPING = 3;
	public static byte GOTOSLEEP = 4;
	public static byte STOPPING = 5;
	public static byte ERROR = -1;

	
	public static String scriptLocationTag = "ScriptLocation";
	
	protected byte status;
	protected String scriptLocation;

	public abstract void readEntityFromNBT(NBTTagCompound var1);
    public abstract void writeEntityToNBT(NBTTagCompound var1);

    public Script(String scriptLocation)
    {
        this.scriptLocation = scriptLocation;
    }
    
    public String toString(){return this.scriptLocation;}
    public String getScriptLocation()
    {
    	return this.scriptLocation;
    }
}
