package be.ehb.student.jorisderijck.Villagers2Js.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryVillager implements IInventory {

	private static final int INVENTORY_MAIN_MAX = 36;
	
	/** An array containing the main inventory of a villager. */
	public ItemStack[] mainInventory = new ItemStack[INVENTORY_MAIN_MAX];
	
	/** An array of the armor currently wearing (not in first release). */
	public ItemStack[] armorInventory = new ItemStack[4];
	
	/**
	 * returns the index of the slot containing a itemstack of the given itemId
	 * @param itemId the item id of the item searching
	 * @return indexOfFoundItemStack OR -1 when none was found.
	 * */
	private int getInventorySlotContainingItem(int itemId)
	{
		for(int i=0;i<this.mainInventory.length;++i)
		{
			if (this.mainInventory[i] != null && this.mainInventory[i].itemID == itemId)
			{
				return i;
			}
		}
		return -1;
	}
	
	public boolean hasItem(int itemId)
	{
		return getInventorySlotContainingItem(itemId)==-1?false:true;
	}

	@Override
	public int getSizeInventory() {
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		if (var1>=0 && var1<INVENTORY_MAIN_MAX)
		{
			return this.mainInventory[var1];
		}
		return null;
	}

	@Override
	public ItemStack decrStackSize(int var1, int var2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getInvName() {
		// TODO Auto-generated method stub
		return "Villagers Inventory";
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void onInventoryChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeChest() {
		// TODO Auto-generated method stub
		
	}

	public void consumeItem(int blockId) {
		// TODO Auto-generated method stub
		
	}

    @Override
    public boolean isInvNameLocalized()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isStackValidForSlot(int i, ItemStack itemstack)
    {
        // TODO Auto-generated method stub
        return false;
    }
	
	
}
