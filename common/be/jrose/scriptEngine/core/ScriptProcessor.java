package be.jrose.scriptEngine.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import be.jrose.scriptEngine.proxy.LoggingProxy;

public class ScriptProcessor {

	private static ScriptProcessor instance;
	private ScriptEngineManager manager = new ScriptEngineManager();
	private ScriptEngine scriptEngine;
	private Compilable compileEngine;
	private Invocable invokeEngine;

	private HashMap<String, CompiledScript> scriptcache;

	private ScriptProcessor() {
		this.scriptcache = new HashMap<String, CompiledScript>();
		this.scriptEngine = manager.getEngineByName("js");
		setBindings(null);
		if (this.scriptEngine instanceof Compilable) {
			this.compileEngine = (Compilable) this.scriptEngine;
		}
		if (this.scriptEngine instanceof Invocable) {
			this.invokeEngine = (Invocable) this.scriptEngine;
		}
	}

	public static ScriptProcessor getInstance() {
		if (instance == null) {
			ScriptProcessor.instance = new ScriptProcessor();
		}
		return ScriptProcessor.instance;
	}

	public void setBindings(Map<String,Object> aditionalMappings) {
		Bindings binding = this.scriptEngine.createBindings();
		binding.put("screen", new LoggingProxy());
		if (aditionalMappings != null)binding.putAll(aditionalMappings);
		this.scriptEngine.setBindings(binding, ScriptContext.GLOBAL_SCOPE);
	}
	
	public Object getBoundObject(String name)
	{
		return this.scriptEngine.get(name);
	}

	public void runScripts(String script, String... functions) {
		if (!this.scriptcache.containsKey(script)) {
			try {
				File f = new File(script);
				if (!f.isFile() || !f.canRead())
					return;
				FileReader fr;

				fr = new FileReader(script);

				if (this.compileEngine != null) {
					this.scriptcache.put(script, compileScript(fr));
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (ScriptException e) {
				e.printStackTrace();
			}
		}

		try {
			runScript(script, functions);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void runScript(String scriptname, String... functions)
			throws ScriptException, NoSuchMethodException {
		this.scriptcache.get(scriptname).eval();
		for (String s : functions) {
			runScriptFunction(scriptname, s);
		}

		// this.invokeEngine.invokeFunction("onDebug");
	}

	private void runScriptFunction(String scriptName, String functionName)
			throws NoSuchMethodException, ScriptException {
		this.invokeEngine.invokeFunction(functionName);
	}

	public CompiledScript compileScript(Reader filestream)
			throws ScriptException {
		return this.compileEngine.compile(filestream);
	}

}
