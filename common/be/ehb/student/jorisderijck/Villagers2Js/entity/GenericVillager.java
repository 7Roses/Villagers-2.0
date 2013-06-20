package be.ehb.student.jorisderijck.Villagers2Js.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import be.ehb.student.jorisderijck.Villagers2Js.Villagers2Js;
import be.ehb.student.jorisderijck.Villagers2Js.inventory.InventoryVillager;
import be.ehb.student.jorisderijck.Villagers2Js.lib.Reference;
import be.ehb.student.jorisderijck.engine.core.ai.AIEngine;
import be.ehb.student.jorisderijck.engine.core.ai.Script;
import be.ehb.student.jorisderijck.engine.core.ai.ScriptContainer;
import be.ehb.student.jorisderijck.engine.core.ai.SensorScript;
import be.ehb.student.jorisderijck.engine.core.ai.memory.Memory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;

public class GenericVillager extends EntityCreature {

    private static final Logger log = Logger.getLogger(Reference.MOD_ID);
    
	protected String texture;

	protected ScriptContainer sensorScripts;

	protected AIEngine fsmEngine = Villagers2Js.aiEngine;
	protected Memory memory;

	/** 
	 * the inventory used for this villager
	 * if cheater flag is not set you will consume blocks automatical from here when building
	 * */
	protected InventoryVillager inventory;
	
	/** variable which will let you skip certain food checks, block checks,... (for example if you are makeing an adventure map whith the villagers.*/
	protected boolean cheater = false;
	
	public GenericVillager(World par1World) {
		super(par1World);
		
		this.texture = "/GenericVillager2.png";
		System.out.println("CONSTRUCTOR CALLED");
		this.inventory = new InventoryVillager();
		this.getNavigator().setAvoidSun(false);
		this.getNavigator().setAvoidsWater(true);
		this.getNavigator().setEnterDoors(true);
		this.getNavigator().setCanSwim(true);
		//this.setAIMoveSpeed(0.5f);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttag) {
		super.writeEntityToNBT(nbttag);
		
		if (this.getScriptContainer().getScriptCount()>0){
		    List<Script> scripts = this.getScriptContainer().getScripts();
		    String[] stringlist = new String[scripts.size()];
		    for (int i = 0;i< scripts.size();i++)
		    {
		        stringlist[i] = scripts.get(i).getScriptLocation();
		    }
		    nbttag.setTag("Scripts", this.newStringNBTList(stringlist));
		}
		else
		    nbttag.setTag("Scripts", this.newStringNBTList(new String[] {}));
		/*	
		NBTTagList sensorScripts = new NBTTagList();
		sensorScripts.appendTag(this.sensorScripts
				.writeScriptsToNBT(sensorScripts));
		nbttag.setTag("SensorScript", sensorScripts);
		*/
		log.info("write To NBT");
		if (this.memory == null)
		{
		    log.severe("somhow the entity didn't had a memory, creating one now");
		    this.memory = new Memory(this.getPersistentID());
		}
		String world = this.worldObj.getSaveHandler().getWorldDirectoryName();
		Villagers2Js.memorySaveHandler.save(this.memory,
		        new StringBuilder("saves").append(File.separatorChar).append(world).append(File.separatorChar).append("entities").toString());	
	}



    @Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		log.info("read from NBT");
			
		
		NBTTagList nbttaglist = tag.getTagList("Scripts");
		for (int i = 0;i<nbttaglist.tagCount();i++)
		{
		    String s = ((NBTTagString)nbttaglist.tagAt(i)).data;
		    if (s == null || s.length()==0) continue;
		    Script ss = new SensorScript(s);
		    this.getScriptContainer().addScript(ss);
		}//*/
		
		if (this.memory == null)
        {
            log.warning("somhow the entity didn't had a memory, creating one now");
            this.memory = new Memory(this.getPersistentID());
        }
		
		String world = this.worldObj.getSaveHandler().getWorldDirectoryName();
        this.memory = Villagers2Js.memorySaveHandler.load(this.memory,
                new StringBuilder("saves").append(File.separatorChar).append(world).append(File.separatorChar).append("entities").toString());	    
		//	this.memory = Villagers2.instance.memorySaveHandler.load(this.memory, this.worldObj.getSaveHandler().getWorldDirectoryName());
		/*
		NBTTagList sensorScripts = tag.getTagList("SensorScript");
		this.sensorScripts.readSensorScriptsFromNBT(sensorScripts);
		*/
	}

	public InventoryVillager getInventory(){return this.inventory;}

	protected boolean canDespawn()
	{
	    return false;
	}
	
	protected boolean isAIEnabled() {
	
	    return true;
	}

	public int getMaxHealth() {
		return 20;
	}

	public int getAtackStrength(Entity otherentity) {
		return 0;
	}

	@Override
	protected void updateAITick() {
		fsmEngine.updateSensors(this);
		//fsmEngine.tick(this);
	}

	private boolean first = true;

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if (first && this.getScriptContainer().getScriptCount()==0) {
		    Script ss = new SensorScript("testscript");
			this.getScriptContainer().addScript(ss);
			first = false;
		}
	}

	protected void onDeathUpdate()
	{
	    super.onDeathUpdate();
	    if (this.isDead && this.memory!=null)
	    {
	        String world = this.worldObj.getSaveHandler().getWorldDirectoryName();
	        Villagers2Js.memorySaveHandler.remove(this.memory,
	                new StringBuilder("saves").append(File.separatorChar).append(world).append(File.separatorChar).append("entities").toString());
	    }
	}
	
	
	public ScriptContainer getScriptContainer()
	{
        if (this.sensorScripts == null) {
            this.sensorScripts = new ScriptContainer();
        }
        return this.sensorScripts;
	}
	public List<Script> getSensorScripts() {
		return this.getScriptContainer().getScripts();
	}

	public Memory getMemory() {
	    if (this.memory == null)
	    {
	        this.memory = new Memory(this.getPersistentID());
	        // oeps maybe there was already a memory in the dir saved?
	        String world = this.worldObj.getSaveHandler().getWorldDirectoryName();
            this.memory = Villagers2Js.memorySaveHandler.load(this.memory,
                    new StringBuilder("saves").append(File.separatorChar).append(world).append(File.separatorChar).append("entities").toString());
	    }
		return memory;
	}

	public int getGrowingAge() {
		// TODO Auto-generated method stub
		return 0;
	}
	
    private NBTTagList newStringNBTList(String[] strings)
    {
        NBTTagList nbttaglist = new NBTTagList();
        String[] list = strings;
        int i = strings.length;
        for (int j = 0; j < i; ++j)
        {
            String s = list[j];
            nbttaglist.appendTag(new NBTTagString((String)null, s));
        }
        return nbttaglist;
        
    }
}
