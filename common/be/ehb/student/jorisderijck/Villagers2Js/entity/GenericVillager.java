package be.ehb.student.jorisderijck.Villagers2Js.entity;

import java.io.File;
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
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class GenericVillager extends EntityLiving {

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
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttag) {
		super.writeEntityToNBT(nbttag);
		
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
	protected void entityInit() {
		// TODO Auto-generated method stub
		super.entityInit();
	}

	// still to be thought of functions (for rendering ect..)

	@Override
	protected void updateAITasks() {
		// TODO Auto-generated method stub
		super.updateAITasks(); // this calls theupdateAITick...
	}

	@Override
	protected void updateAITick() {
		// TODO Auto-generated method stub
		// super.updateAITick();
	//	System.out.println("have : " + this.sensorScripts.getScriptCount()+ "sensorScripts");
		fsmEngine.updateSensors(this);
		fsmEngine.tick(this);
		//System.out.println("aimove: "+this.getAIMoveSpeed());
	//	System.out.println("aipath: "+(this.getNavigator().getPath()==null?"null!!":this.getNavigator().getPath()));
		
		// fsmActor.update();
	}

	private boolean first = true;

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if (first) {
		    // System.out.println("filepath: "+(new File("resource/mod/Villagers2Js/test.js")).getAbsolutePath());
			//SensorScript ss = new SensorScript("resource/mod/Villagers2Js/test.js");
		    SensorScript ss = new SensorScript("testscript");
			if (this.sensorScripts == null) {
				this.sensorScripts = new ScriptContainer();
			}
			this.sensorScripts.addScript(ss);
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
	
	public List<Script> getSensorScripts() {
		return this.sensorScripts.getScripts();
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

}
