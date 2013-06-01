package be.ehb.student.jorisderijck.engine.core.ai;

import net.minecraft.nbt.NBTTagCompound;

public class SensorScript extends Script {

	public static String referenceCountTag = "ReferenceCount";
	protected byte referenceCount;
	
	public static String statusTag = "ScriptStatus";

	public SensorScript(String scriptLocation)
	{
	    super(scriptLocation);
		this.referenceCount = 1;
		this.status = Script.NEW;
	}
	
	
	public SensorScript() {
	    super("");
		this.referenceCount = 1;
		this.status = Script.NEW;
	}


	@Override
	public void readEntityFromNBT(NBTTagCompound roottag) {
		this.scriptLocation = roottag.getString(Script.scriptLocationTag);
		this.referenceCount = roottag.getByte(SensorScript.referenceCountTag);
		this.status = roottag.getByte(SensorScript.statusTag);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound roottag) {
		roottag.setString(Script.scriptLocationTag, this.scriptLocation);
		roottag.setByte(SensorScript.referenceCountTag, this.referenceCount);
		roottag.setByte(SensorScript.statusTag, this.status);
	}

	public static Script loadScriptFromNBT(NBTTagCompound scriptTag) {
		Script ss = new SensorScript();
		ss.readEntityFromNBT(scriptTag);
		return ss;
	}

}
