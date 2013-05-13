package be.ehb.student.jorisderijck.engine.binds.facades;

import java.util.logging.Logger;

import net.minecraft.client.Minecraft;

import be.ehb.student.jorisderijck.Villagers2Js.entity.GenericVillager;
import be.ehb.student.jorisderijck.Villagers2Js.lib.Reference;
import be.ehb.student.jorisderijck.Villagers2Js.lib.ScriptEngineConstants;

public class LoggingProxy implements IAgentBinder {


	
	private Logger log = Logger.getLogger(Reference.MOD_ID);
	private static boolean enabled = ScriptEngineConstants.LOGGINGENABLED;
	
	public LoggingProxy()
	{
	    log.info(String.format("Script logging is been %s",enabled?"enabled":"disabled"));
	}
	public void info(Object obj)
	{
		if (enabled) log.info(String.format("[Script] %s", obj.toString()));
	}
	public void debug(Object obj)
	{
	    if (Minecraft.getMinecraft().theWorld.isRemote && enabled)
	        Minecraft.getMinecraft().thePlayer.addChatMessage(obj.toString());
	}
	
    @Override
    public void setAgent(GenericVillager villager)
    {
        // not needed here, this is a non agent specific code.
    }

}
