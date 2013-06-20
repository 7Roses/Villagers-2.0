package be.ehb.student.jorisderijck.engine.binds.facades;

import java.util.logging.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.util.Vec3;

import be.ehb.student.jorisderijck.Villagers2Js.entity.GenericVillager;
import be.ehb.student.jorisderijck.Villagers2Js.lib.Reference;
import be.ehb.student.jorisderijck.Villagers2Js.lib.ScriptEngineConstants;

public class LoggingProxy implements IAgentBinder {

	private Logger log = Logger.getLogger(Reference.MOD_ID);
	private static boolean enabled = ScriptEngineConstants.LOGGINGENABLED;

	private GenericVillager agent;
	
	public LoggingProxy()
	{
	    log.info(String.format("Script logging is been %s",enabled?"enabled":"disabled"));
	}
	public void info(Object obj)
	{
		if (enabled) {
		    log.info(String.format("[Script] %s", obj.toString()));
		    if (Minecraft.getMinecraft().theWorld.isRemote && Minecraft.getMinecraft().thePlayer !=null)
		    {
		        EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
		        Vec3 playerpos = Vec3.createVectorHelper(player.posX, player.posY, player.posZ);
		        Vec3 agentpos = Vec3.createVectorHelper(agent.posX,agent.posY, agent.posZ);
		        if (playerpos.distanceTo(agentpos)<=ScriptEngineConstants.LOGGINGDISTANCE)
		        {
		            Minecraft.getMinecraft().thePlayer.addChatMessage(obj.toString());
		        }
		    }
		}
		
	}
	public void debug(Object obj)
	{
	    if (Minecraft.getMinecraft().theWorld.isRemote && enabled)
	        Minecraft.getMinecraft().thePlayer.addChatMessage(obj.toString());
	}
	
    @Override
    public void setAgent(GenericVillager villager)
    {
        agent = villager;
    }

}
