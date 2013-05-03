package be.jrose.scriptEngine.core;

import java.util.HashSet;
import java.util.List;

import javax.script.CompiledScript;

import be.jrose.villagers2.lib.ScriptEngineConstants;

public class ScriptCache {

    private HashSet<ScriptContainer<CompiledScript>> cache;
    
    public ScriptCache()
    {
        this.cache = new HashSet<ScriptContainer<CompiledScript>>(ScriptEngineConstants.INITAL_CACHE_SIZE);
    }

    public void addScript(ScriptContainer<CompiledScript> scriptcontainer)
    {
        this.cache.add(scriptcontainer);
    }
    
    public boolean hasScript(ScriptContainer<CompiledScript> scriptcontainer)
    {
        return this.cache.contains(scriptcontainer);
    }
    
    public void removeScript(ScriptContainer<CompiledScript> scriptcontainer)
    {
        this.cache.remove(scriptcontainer);
    }
    
    public void addAll(List<ScriptContainer<CompiledScript>> scripts)
    {
        this.cache.addAll(scripts);
    }

    public void removeAll(List<ScriptContainer<CompiledScript>> scripts)
    {
        this.cache.removeAll(scripts);
    }
    
    /**
     * function for returning a script with help of the name
     * @param scriptName the scriptname so as it would be saved in the villagers memory
     * */
    public ScriptContainer<CompiledScript> getScriptByScriptName(String scriptName)
    {
        ScriptContainer<CompiledScript> returnvar = null;
        for(ScriptContainer<CompiledScript> scriptcontainer:cache)
        {
            if (scriptcontainer.getName().equalsIgnoreCase(scriptName))
            {
                returnvar = scriptcontainer;
                break;
            }
        }
        return returnvar;
    }
}
