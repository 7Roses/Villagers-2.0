package be.jrose.scriptEngine.core;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.script.CompiledScript;
import javax.script.ScriptException;

import net.minecraft.world.World;

import be.jrose.helperclasses.FileHelper;
import be.jrose.villagers2.Villagers2;
import be.jrose.villagers2.lib.Reference;
import be.jrose.villagers2.lib.ScriptEngineConstants;

public class ScriptManager {
    private static final Logger log = Logger.getLogger(Reference.MOD_ID);
    private ScriptCache cache;
    
    private ScriptProcessor compilerreference;
    
    public ScriptManager(ScriptCache cache,ScriptProcessor compilerReference)
    {
        this.cache = cache;
        this.compilerreference = compilerReference;
        if(Villagers2.DEBUG){log.setLevel(Level.ALL);}
    }
    
    /** script for loading of all default scripts (delivered with the mod)*/
    public void loadScripts()
    {
        //fill the list with default scripts.
        List<String> files = FileHelper.listFiles(ScriptEngineConstants.DEAFAULT_SCRIPTS_PAHT, ScriptEngineConstants.SCRIPT_EXTENSION);
        List<ScriptContainer<CompiledScript>> scripts = new ArrayList<ScriptContainer<CompiledScript>>();
        //for debug
            
        for(String path:files)
        {
            log.info(path);
            ScriptContainer<CompiledScript> script = new ScriptContainer<CompiledScript>();
            script.setPath(path);
            script = this.compileSingleScript(script);
            if (script.isValid())
            {
               try
               {
                   this.compilerreference.evalScript(script);
                   script.setName((String) this.compilerreference.runScriptFunction("getName"));
                   scripts.add(script);
                   log.info("compiled and added. (name='"+script.getName()+"')");
               } catch (ScriptException | NoSuchMethodException e)
               {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
               }
            }    
        }
        this.cache.addAll(scripts);
        log.info("ScriptManager loaded: "+scripts.size()+" into it's cache");
    }
    
    /** function for loading the scripts in a specific world save file.
     * @param world */
    public void loadWorldScripts(World world)
    {
        
    }
    
    
    /** function for checking all scripts for validity and for recompiling needs*/
    public void checkScripts(/*world variable for specification*/){}
    
    
    
    /** privte function for compilling a single scriptcontainers script.*/
    private ScriptContainer<CompiledScript> compileSingleScript(ScriptContainer<CompiledScript> script)
    {
        try
        {
            script.setScript(compilerreference.compileScript(script.getPath()));
        } catch (FileNotFoundException e)
        {
            // TODO some logging code.
        } catch (ScriptException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return script;
    }    
    /** private function for calling the scriptProcessor and request compilation for certain scripts.*/
    private List<ScriptContainer<CompiledScript>> compileList(List<ScriptContainer<CompiledScript>> scripts)
    {
        for(ScriptContainer<CompiledScript> container:scripts)
        {
            try
            {
                container.setScript(compilerreference.compileScript(container.getPath()));
            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            } catch (ScriptException e)
            {
                e.printStackTrace();
            }
        }
        return scripts;
    }
    
    
    /** private function for adding the compiled scripts to the scriptcache */
    private void addToCache(List<ScriptContainer<CompiledScript>> scripts)
    {
        this.cache.addAll(scripts);
    }
    
    private void removeFromCache(List<ScriptContainer<CompiledScript>> scripts)
    {
        this.cache.removeAll(scripts);
    }
    
    

}
