package be.jrose.villagers2.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import be.jrose.villagers2.aiengine.core.AIEngine;
import be.jrose.villagers2.aiengine.core.AgentMemory;
import be.jrose.villagers2.aiengine.core.Script;
import be.jrose.villagers2.aiengine.core.ScriptContainer;
import be.jrose.villagers2.aiengine.core.SensorScript;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class GenericVillager extends EntityLiving {

	protected String texture;

	protected ScriptContainer sensorScripts;

	protected AIEngine fsmEngine = AIEngine.getInstance();
	protected AgentMemory memory;

	public GenericVillager(World par1World) {
		super(par1World);
		this.texture = "/GenericVillager2.png";
		System.out.println("CONSTRUCTOR CALLED");
		this.memory = new AgentMemory();
		this.setAIMoveSpeed(0.25F);
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
		// fsmActor.update();
	}

	private boolean first = true;

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if (first) {
			SensorScript ss = new SensorScript("test.js");
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
