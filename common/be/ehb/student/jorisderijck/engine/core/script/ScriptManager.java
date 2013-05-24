package be.ehb.student.jorisderijck.engine.core.script;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.script.CompiledScript;
import javax.script.ScriptException;

import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;

import be.ehb.student.jorisderijck.Villagers2Js.lib.Reference;
import be.ehb.student.jorisderijck.Villagers2Js.lib.ScriptEngineConstants;
import be.ehb.student.jorisderijck.Villagers2Js.utils.FileHelper;

public class ScriptManager {
    private static final Logger log = Logger.getLogger(Reference.MOD_ID);
    private ScriptCache cache;

    private ScriptProcessor compilerreference;

    public ScriptManager(ScriptCache cache, ScriptProcessor compilerReference) {
        this.cache = cache;
        this.compilerreference = compilerReference;
    }

    /** script for loading of all default scripts (delivered with the mod) */
    public void loadScripts()
    {
        int scriptsLoaded = loadFromDirectory(ScriptEngineConstants.DEAFAULT_SCRIPTS_PAHT);
        log.info("ScriptManager loaded: " + scriptsLoaded + " into it's cache");
    }

    /**
     * function for loading the scripts in a specific world save file.
     * 
     * @param world
     */
    public void loadWorldScripts(World world)
    {
        StringBuilder sb = new StringBuilder("saves");
        sb.append(File.separatorChar).append(world.getSaveHandler().getWorldDirectoryName());
        sb.append(File.separatorChar).append(world.provider.getSaveFolder()==null?"region":world.provider.getSaveFolder());
        String loadDir = sb.toString();
        int scriptsLoaded = loadFromDirectory(loadDir);
        log.info("ScriptManager added: " + scriptsLoaded + " into it's cache");
        log.info(String.format("ScriptManager curently has %s scripts loaded into cache",this.cache.getsize()));
        }

    /**
     * private function used for {@link #loadScripts()} and
     * {@link #loadWorldScripts(World)}
     * */
    private int loadFromDirectory(String rootdirectory)
    {
        // fill the list with default scripts.
        List<String> files = FileHelper.listFiles(rootdirectory,
                ScriptEngineConstants.SCRIPT_EXTENSION);
        List<String> dirs = FileHelper.listDirectories(rootdirectory);
        List<ScriptContainer<CompiledScript>> scripts = new ArrayList<ScriptContainer<CompiledScript>>();
        // for debug
        if (files!=null && files.size() > 0)
        {
            log.info(String.format("found %d valid files and %d directories in the current dir", files.size(),dirs.size()));
            for (String path : files)
            {
                ScriptContainer<CompiledScript> script = new ScriptContainer<CompiledScript>();
                script.setPath(path);
                script = this.compileSingleScript(script);
                if (script.isValid())
                {
                    try
                    {
                        this.compilerreference.evalScript(script);
                        script.setName((String) this.compilerreference
                                .runScriptFunction("getName"));
                        scripts.add(script);
                     
                        log.log(Level.INFO, String.format("compiled and added (name=%s,path=%s)",script.getName(),path));
                    
                    } catch (ScriptException | NoSuchMethodException e)
                    {
                        log.warning("a script has thrown an exception!");
                        log.log(Level.WARNING, "scriptName: {}", path);
                        log.log(Level.WARNING, e.getMessage(), e);
                    }
                }
            }
        }
        this.cache.addAll(scripts);
        int loadedScripts = scripts.size(); // statistic number
        if (dirs !=null && dirs.size()>0){
            for (String dir : dirs)
            {
                loadedScripts += loadFromDirectory(dir); // load scripts from subdirs (recuscive)
            }
        }
        return loadedScripts;
    }

    /** function for checking all scripts for validity and for recompiling needs */
    public void checkScripts(/* world variable for specification */)
    {
        // THIS IS AN PRE DESIGNED EXTENSION POINT (not in the scoop of the project)
    }

    /** private function for compilling a single scriptcontainers script. */
    private ScriptContainer<CompiledScript> compileSingleScript(
            ScriptContainer<CompiledScript> script)
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

    /**
     * private function for calling the scriptProcessor and request compilation
     * for certain scripts.
     */
    @SuppressWarnings("unused") // will come when the command reload is in the mod, or if regular rechecking of scripts works.
    private List<ScriptContainer<CompiledScript>> compileList(
            List<ScriptContainer<CompiledScript>> scripts)
    {
        for (ScriptContainer<CompiledScript> container : scripts)
        {
            try
            {
                container.setScript(compilerreference.compileScript(container
                        .getPath()));
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
    @SuppressWarnings("unused") // will come when the command reload is in the mod, or if regular rechecking of scripts works.
    private void addToCache(List<ScriptContainer<CompiledScript>> scripts)
    {
        this.cache.addAll(scripts);
    }

    @SuppressWarnings("unused") // will come when the command reload is in the mod, or if regular rechecking of scripts works.
    private void removeFromCache(List<ScriptContainer<CompiledScript>> scripts)
    {
        this.cache.removeAll(scripts);
    }

}
