package be.ehb.student.jorisderijck.engine.binds;

import java.util.logging.Logger;

import net.minecraft.client.Minecraft;

import be.ehb.student.jorisderijck.Villagers2Js.lib.Reference;
import be.ehb.student.jorisderijck.Villagers2Js.lib.ScriptEngineConstants;

public class LoggingProxy {


	
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

}
