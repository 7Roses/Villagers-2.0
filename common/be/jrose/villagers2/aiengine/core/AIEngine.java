package be.jrose.villagers2.aiengine.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.script.ScriptException;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

import be.jrose.scriptEngine.core.ScriptCache;
import be.jrose.scriptEngine.core.ScriptManager;
import be.jrose.scriptEngine.core.ScriptProcessor;
import be.jrose.villagers2.aiengine.scriptproxy.LogProxy;
import be.jrose.villagers2.aiengine.scriptproxy.MemoryProxy;
import be.jrose.villagers2.aiengine.scriptproxy.SensorProxy;
import be.jrose.villagers2.aiengine.scriptproxy.VillagerProxy;
import be.jrose.villagers2.entity.GenericVillager;


import be.jrose.scriptEngine.proxy.LoggingProxy;

public class AIEngine {

	//private static final boolean DEBUGBUILD = true;

	private ScriptProcessor scriptprocessor;
	private ScriptCache cache;
	
	public AIEngine(ScriptCache cache,ScriptProcessor scriptprocessor) {
		HashMap<String,Object> binding = new HashMap<String,Object>();
		binding.put("log", new LogProxy());
		binding.put("Logger",new LoggingProxy());
		binding.put("sensors",SensorProxy.getInstance());
		binding.put("memory", MemoryProxy.getInstance());
		binding.put("actors", VillagerProxy.getInstance()); // used for taking actions!
		this.scriptprocessor = scriptprocessor;
		this.scriptprocessor.setBindings(binding);
		this.cache = cache;
	}

	// find the scripts currently registered for this agent to be run.
	public void updateSensors(GenericVillager agent) {
		// get the iterator for this agents scripts
		Iterator<Script> itr = agent.getSensorScripts().iterator();
		bindProxy(agent);
		while (itr.hasNext()) {
			SensorScript currentScript = (SensorScript) itr.next();
			ArrayList<String> functionsToCall = new ArrayList<String>();
			if (currentScript.status == Script.NEW) {
				functionsToCall.add("onStartSensor");
				currentScript.status = Script.RUNNING;
			}
			if (currentScript.status == Script.STOPPING) {
				functionsToCall.add("onStopSensor");
			}
			if (currentScript.status == Script.RUNNING){
				functionsToCall.add("onUpdateSensor");
			}
			String[] functions = new String[0];
					 functions = functionsToCall.toArray(functions);
			try
            {
                this.scriptprocessor.runScript(cache.getScriptByScriptName(currentScript.getScriptLocation())
                        , functions);
            } catch (NoSuchMethodException e)
            {
                System.err.println("error while executing an script, identifier:"+currentScript.getScriptLocation());
                 e.printStackTrace();
            } catch (ScriptException e)
            {
                System.err.println("error while executing an script, identifier:"+currentScript.getScriptLocation());
                e.printStackTrace();
            }
		}

	}

	/**
	 * sets the variable references of the various proxys to the current AIagent
	 * and then override the privious bindings with it.
	 * */
	private void bindProxy(GenericVillager agent) {
		SensorProxy sensproxy = SensorProxy.getInstance();
		sensproxy.setAgent(agent);
		MemoryProxy memoryproxy = MemoryProxy.getInstance();
		memoryproxy.setMemory(agent.getMemory());
		VillagerProxy.getInstance().setAgent(agent);
	}

	public void tick(GenericVillager genericVillager) {
		// take the top first script name
		// see what kind of status the script is in (SLEEPING,RUNNING,NEW)
		// search if a compiled version exists
		// if it does, run that.
		// else
		// compile, run and save the script version
		// see if the status of the script has been changed
		// (GOTOSLEEP/ENDSCRIPT)
		// if GOTOSLEEP run also the sleep() function forthe script
		// if ENDSCRIPT run the end() function on the script.

		// for both, the run will check wich functions it needs to execute:
		// if SLEEPING, run onAwake() and run onUpdate()
		// if RUNNING just run onUpdate()
		// if NEW run start() and onUpdate()
	}
}
