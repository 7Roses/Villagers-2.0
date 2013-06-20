package be.ehb.student.jorisderijck.engine.core.ai.village;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.logging.Logger;

import be.ehb.student.jorisderijck.Villagers2Js.lib.Reference;
import be.ehb.student.jorisderijck.engine.binds.IWorldVillageFinder;

import net.minecraft.world.World;

public class VillageManager {

    private static final Logger log = Logger.getLogger(Reference.MOD_ID);

    private HashMap<World, WorldVillageContainer> worldVillages;

    public VillageManager()
    {
        worldVillages = new HashMap<World,WorldVillageContainer>();
    }
    
    public void loadWorldVillages(World world)
    {
        StringBuilder sb = new StringBuilder("saves");
        sb.append(File.separatorChar).append(
                world.getSaveHandler().getWorldDirectoryName());
        sb.append(File.separatorChar).append(
                world.provider.getSaveFolder() == null ? "region"
                        : world.provider.getSaveFolder());
        String loadDir = sb.toString();
        log.info(String.format("current loaddir: %s", loadDir));
        WorldVillageContainer wvc =null;
        try
        {
            wvc = loadWorldFromFile(loadDir);
        } catch (FileNotFoundException e)
        {
            log.warning("could not find any village save file for Villagers2JS, if you just started the world this is normal!");
            wvc = new WorldVillageContainer();
        }
        if (wvc == null)
        {
            wvc = new WorldVillageContainer(); // none existed, so make one.
        }
        worldVillages.put(world, wvc);
    }

    public void unloadWorldVillages(World world)
    {
        saveWorldVillage(world);
    }

    public void saveWorldVillage(World world)
    {
        StringBuilder sb = new StringBuilder("saves");
        sb.append(File.separatorChar).append(
                world.getSaveHandler().getWorldDirectoryName());
        sb.append(File.separatorChar).append(
                world.provider.getSaveFolder() == null ? "region"
                        : world.provider.getSaveFolder());
        String saveDir = sb.toString();
        saveWorldVillagesToFile(saveDir, worldVillages.get(world));
    }

    private void saveWorldVillagesToFile(String saveDir,
            IWorldVillageFinder worldVillageContainer)
    {
        try
        {
            File dir = new File(saveDir);
            dir.mkdirs();
            File file = new File(saveDir + File.separatorChar + "villages.dat");
            if (Reference.DEBUGLOGGING)
            {
                log.info("saving to: " + file.getPath());
                log.info(file.getCanonicalPath());
            }
            OutputStream outputfile = new FileOutputStream(file);
            OutputStream buffer = new BufferedOutputStream(outputfile);
            ObjectOutput output = new ObjectOutputStream(buffer);
            try
            {
                output.writeObject(worldVillageContainer);
            } finally
            {
                output.close();
            }
        } catch (IOException ex)
        {
            log.severe(String.format("Cannot perform output. reason: %s",ex.toString()));
        }
    }

    private WorldVillageContainer loadWorldFromFile(String loadDir) throws FileNotFoundException
    {
        WorldVillageContainer villageContainer = null;
        try
        {
            File file = new File(loadDir + File.separatorChar + "villages.dat");
            InputStream inputfile = new FileInputStream(file);
            InputStream buffer = new BufferedInputStream(inputfile);
            ObjectInput input = new ObjectInputStream(buffer);
            try
            {
                villageContainer = (WorldVillageContainer) input.readObject();
            } finally
            {
                input.close();
            }
        } catch (ClassNotFoundException ex)
        {
            log.severe("Cannot perform input. Class not found." + ex);
        } catch (IOException ex)
        {
            log.severe("Cannot perform input." + ex);
        }
        return villageContainer;
    }

    public IWorldVillageFinder getWorldVillageContainer(World world)
    {
        if (!this.worldVillages.containsKey(world))
        {
            this.worldVillages.put(world, new WorldVillageContainer());
        }
        return this.worldVillages.get(world);
    }
}
