package be.jroses.villagers2.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.world.World;

public class GenericVillager extends EntityAgeable {

	public GenericVillager(World par1World) {
		super(par1World);
		this.texture = "/GenericVillager2.png"; ///
		this.moveSpeed = 0.30F;
		this.tasks.taskEntries.clear();
	}

	protected boolean isAIEnabled(){return true;}
	
	@Override
	public int getMaxHealth() {
		return 20;
	}

	public int getAtackStrength(Entity otherentity){
		return 0;
	}
	
	private int firstTime = 0;
	
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if(firstTime<2){
			Minecraft.getMinecraft().thePlayer.sendChatToPlayer("hey I'm Alive");
			firstTime++;
		}
	}

	@Override
	public EntityAgeable createChild(EntityAgeable var1) {
		return null;
	}
	
	
	
}
