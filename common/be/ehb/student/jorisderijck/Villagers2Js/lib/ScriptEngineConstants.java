package be.ehb.student.jorisderijck.Villagers2Js.lib;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.script.CompiledScript;

import net.minecraft.client.Minecraft;

import be.ehb.student.jorisderijck.engine.core.script.ScriptContainer;

public final class ScriptEngineConstants {

    public final static int INITAL_CACHE_SIZE = 10;
    
    
    
    // the version where basic scripts need to be unpacked and set into .minecraft/resouces/scripts/.
    public final static String DEAFAULT_SCRIPTS_PAHT = "resources" + File.separatorChar + "scripts";
    
    
    // next version testing for finding or loading them from the zipt itself. (or from classloader)
    public final static ArrayList<String> DEFAULT_SCRIPTS = new ArrayList<String>();
    static
    {
        DEFAULT_SCRIPTS.add("scripts/corescripts/startup.js");
        DEFAULT_SCRIPTS.add("scripts/demo/demo.js");
    }
    
    public static final String SCRIPT_EXTENSION = ".js";

    public static final boolean LOGGINGENABLED = true;
    public static final double LOGGINGDISTANCE = 20;
    
}
