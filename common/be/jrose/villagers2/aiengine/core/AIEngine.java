package be.jrose.villagers2.aiengine.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import be.jrose.scriptEngine.core.ScriptProcessor;
import be.jrose.villagers2.aiengine.scriptproxy.LogProxy;
import be.jrose.villagers2.aiengine.scriptproxy.MemoryProxy;
import be.jrose.villagers2.aiengine.scriptproxy.SensorProxy;
import be.jrose.villagers2.aiengine.scriptproxy.VillagerProxy;
import be.jrose.villagers2.entity.GenericVillager;


import be.jrose.scriptEngine.proxy.LoggingProxy;

public class AIEngine {

	//private static final boolean DEBUGBUILD = true;

	private static AIEngine instance;
	
	private AIEngine() {
		HashMap<String,Object> binding = new HashMap<String,Object>();
		binding.put("log", new LogProxy());
		binding.put("Logger",new LoggingProxy());
		binding.put("sensors",SensorProxy.getInstance());
		binding.put("memory", MemoryProxy.getInstance());
		binding.put("actors", VillagerProxy.getInstance()); // used for taking actions!
		ScriptProcessor.getInstance().setBindings(binding);
	}

	public static AIEngine getInstance() {
		if (instance == null) {
			instance = new AIEngine();
		}
		return instance;
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
			ScriptProcessor.getInstance().runScripts(currentScript.getScriptLocation(), functions);
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
