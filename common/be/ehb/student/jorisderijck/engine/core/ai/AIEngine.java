package be.ehb.student.jorisderijck.engine.core.ai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.script.ScriptException;


import be.ehb.student.jorisderijck.Villagers2Js.entity.GenericVillager;
import be.ehb.student.jorisderijck.Villagers2Js.lib.AIEngineConstants;
import be.ehb.student.jorisderijck.Villagers2Js.lib.Reference;
import be.ehb.student.jorisderijck.engine.binds.facades.IAgentBinder;
import be.ehb.student.jorisderijck.engine.binds.facades.LoggingProxy;
import be.ehb.student.jorisderijck.engine.binds.facades.ScriptJobUtils;
import be.ehb.student.jorisderijck.engine.binds.facades.ScriptSelfReflection;
import be.ehb.student.jorisderijck.engine.binds.facades.VillageScriptFacade;
import be.ehb.student.jorisderijck.engine.core.script.ScriptCache;
import be.ehb.student.jorisderijck.engine.core.script.ScriptProcessor;



public class AIEngine {

	//private static final boolean DEBUGBUILD = true;
    private static final Logger log = Logger.getLogger(Reference.MOD_ID);
	private ScriptProcessor scriptprocessor;
	private ScriptCache cache;
	
	public AIEngine(ScriptCache cache,ScriptProcessor scriptprocessor) {
	    ScriptSelfReflection self = new ScriptSelfReflection();
	    LoggingProxy logger = new LoggingProxy();
	    ScriptJobUtils utils = new ScriptJobUtils();
	    VillageScriptFacade villagefinder = new VillageScriptFacade();
	    
		HashMap<String,Object> binding = new HashMap<String,Object>();
		binding.put("Logger",logger);
		binding.put(AIEngineConstants.JS_BIND_SCRIPTSELF,self);
		binding.put("utils", utils);
		binding.put(AIEngineConstants.JS_BIND_VILLAGEFINDER, villagefinder);
		this.scriptprocessor = scriptprocessor;
		this.scriptprocessor.setBindings(binding);
		this.cache = cache;
		log.info("AIEngine has been construct/loaded");
	}

	// find the scripts currently registered for this agent to be run.
	public void updateSensors(GenericVillager agent) {
		// get the iterator for this agents scripts
		Iterator<Script> itr = agent.getSensorScripts().iterator();
		bindProxy(agent);
		while (itr.hasNext()) {
			Script currentScript = (Script) itr.next();
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
			    // debug:
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
	 * and then override the previous bindings with it.
	 * */
	private void bindProxy(GenericVillager agent) {
		IAgentBinder self = (IAgentBinder) this.scriptprocessor.getBoundObject(AIEngineConstants.JS_BIND_SCRIPTSELF);
		self.setAgent(agent);
		IAgentBinder villageHelp = (IAgentBinder) this.scriptprocessor.getBoundObject(AIEngineConstants.JS_BIND_VILLAGEFINDER);
		villageHelp.setAgent(agent);
		LoggingProxy logger = (LoggingProxy) this.scriptprocessor.getBoundObject("Logger");
	        logger.setAgent(agent);
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
