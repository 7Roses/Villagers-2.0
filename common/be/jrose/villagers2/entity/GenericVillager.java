package be.jrose.villagers2.entity;

import java.util.List;
import java.util.logging.Logger;

import be.jrose.villagers2.Villagers2;
import be.jrose.villagers2.aiengine.core.AIEngine;
import be.jrose.villagers2.aiengine.core.AgentMemory;
import be.jrose.villagers2.aiengine.core.Script;
import be.jrose.villagers2.aiengine.core.ScriptContainer;
import be.jrose.villagers2.aiengine.core.SensorScript;
import be.jrose.villagers2.inventory.InventoryVillager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class GenericVillager extends EntityLiving {

	protected String texture;

	protected ScriptContainer sensorScripts;

	protected AIEngine fsmEngine = Villagers2.aiEngine;
	protected AgentMemory memory;

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
		this.memory = new AgentMemory();
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
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		/*
		NBTTagList sensorScripts = tag.getTagList("SensorScript");
		this.sensorScripts.readSensorScriptsFromNBT(sensorScripts);
		*/
	}

	public InventoryVillager getInventory(){return this.inventory;}

	
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
		System.out.println("been called !!!");
	}

	@Override
	protected void updateAITick() {
		// TODO Auto-generated method stub
		// super.updateAITick();
	//	System.out.println("been called!");
	//	System.out.println("have : " + this.sensorScripts.getScriptCount()+ "sensorScripts");
		fsmEngine.updateSensors(this);
		fsmEngine.tick(this);
		System.out.println("aimove: "+this.getAIMoveSpeed());
		System.out.println("aipath: "+(this.getNavigator().getPath()==null?"null!!":this.getNavigator().getPath()));
		
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

	public List<Script> getSensorScripts() {
		return this.sensorScripts.getScripts();
	}

	public AgentMemory getMemory() {
		return this.memory;
	}

	public int getGrowingAge() {
		// TODO Auto-generated method stub
		return 0;
	}

}
