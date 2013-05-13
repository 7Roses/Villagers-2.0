package be.ehb.student.jorisderijck.engine.core.script;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import be.ehb.student.jorisderijck.Villagers2Js.lib.Reference;
import be.ehb.student.jorisderijck.engine.binds.facades.LoggingProxy;

public class ScriptProcessor {
    
    private static final Logger log = Logger.getLogger(Reference.MOD_ID + ":ScriptProcessor");

	private static ScriptProcessor instance;
	private ScriptEngineManager manager = new ScriptEngineManager();
	private ScriptEngine scriptEngine;
	private Compilable compileEngine;
	private Invocable invokeEngine;

//	private ScriptCache cache;

	public ScriptProcessor() {
		init();
	}

	public void init()
	{
	    this.scriptEngine = manager.getEngineByName("js");
        setBindings(null);
        if (this.scriptEngine instanceof Compilable) {
            this.compileEngine = (Compilable) this.scriptEngine;
        } else {
            log.warning("no compiler found!!, execution will be a bit slower");
        }
        if (this.scriptEngine instanceof Invocable) {
            this.invokeEngine = (Invocable) this.scriptEngine;
        } else {
            log.severe("error !! no invocable engine found! you sure you have a java SE 1.6 or above?");
        }
	}
	
	@Deprecated
	public static ScriptProcessor getInstance() {
		if (instance == null) {
			ScriptProcessor.instance = new ScriptProcessor();
		}
		return ScriptProcessor.instance;
	}

	public void setBindings(Map<String,Object> aditionalMappings) {
		Bindings binding = this.scriptEngine.createBindings();
		binding.put("console", new LoggingProxy());
		if (aditionalMappings != null)binding.putAll(aditionalMappings);
		this.scriptEngine.setBindings(binding, ScriptContext.GLOBAL_SCOPE);
	}
	
	public Object getBoundObject(String name)
	{
		return this.scriptEngine.getBindings(ScriptContext.GLOBAL_SCOPE).get(name);
	}

	public void evalScript(ScriptContainer<CompiledScript> script) throws ScriptException
	{
	    if (script.getScript() !=null)
	    {
	        script.getScript().eval();
	    }
	    else 
	    {
	        evalFromFile(script.getPath());
	    }
	}
	
	public void runScript(ScriptContainer<CompiledScript> script, String... functions)
			throws ScriptException, NoSuchMethodException {
		evalScript(script);
		for (String s : functions) {
			runScriptFunction(s);
		}

		// this.invokeEngine.invokeFunction("onDebug");
	}

	
	/**
	 * call a specific function from the current evaluated script (be aware if a script before this one's function isn't overwritten it's possible to call this functions still!)
	 * @return the return value given back by the script's function.
	 * */
	public Object runScriptFunction(String functionName)
			throws NoSuchMethodException, ScriptException {
		return this.invokeEngine.invokeFunction(functionName);
	}

	/**
	 * 
	 * */
	@Deprecated
	public CompiledScript compileScript(Reader filestream)
			throws ScriptException {
		return this.compileEngine.compile(filestream);
	}
	
	public CompiledScript compileScript(String pathname) throws FileNotFoundException, ScriptException
	{
	    File scriptfile;
	    FileReader filereader;
	        scriptfile = new File(pathname);
	        if (!scriptfile.isFile() || !scriptfile.canRead())
	        {
	            throw new FileNotFoundException("file isn't there or is't not a valid file.");
	        }
	        try
            {
	            filereader = new FileReader(scriptfile);
	            this.compileEngine.compile(filereader);
	            filereader.close();
            } catch (IOException e)
            {
                log.warning("an error is thrown by compiling: "+pathname);
                log.warning("the error message is: "+e.getMessage());
                e.printStackTrace();
            }
	    return null;
	}

	public void evalFromFile(String pathname)
	{
	    File scriptfile;
        FileReader filereader;
            scriptfile = new File(pathname);
            try
            {
                if (!scriptfile.isFile() || !scriptfile.canRead())
                {
                    throw new FileNotFoundException("file isn't there or is't not a valid file.");
                }                
                filereader = new FileReader(scriptfile);
                this.scriptEngine.eval(filereader);
                filereader.close();
            } catch (IOException e)
            {
                log.warning("an error is thrown by reading the file: "+pathname);
                log.warning("the error message is: "+e.getMessage());
                e.printStackTrace();
            } catch (ScriptException e)
            {
                log.warning("an error is thrown by evaluating a script from file: ");
                log.warning("the script name :"+pathname);
                log.warning("the error message is: "+e.getMessage());
                e.printStackTrace();
            }
	}
	
}
