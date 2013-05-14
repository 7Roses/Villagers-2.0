package be.ehb.student.jorisderijck.Villagers2Js.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;

public class InventoryVillager extends InventoryBasic implements IInventory {

    public InventoryVillager() {
        super("Villagers Inventory", false, INVENTORY_MAIN_MAX);
    }

    private static final int INVENTORY_MAIN_MAX = 36;

    public boolean consumeItem(int blockId)
    {

        return false;
    }

    public int getSlotForItemAfterSlot(int itemId, int slotId)
    {
        for (int i = slotId+1; i < this.getSizeInventory(); i++)
        {
            if (this.getStackInSlot(i).itemID == itemId) { return i; }
        }
        return -1;

    }

    public int getSlotForItem(int itemId)
    {
        for (int i = 0; i < this.getSizeInventory(); i++)
        {
            if (this.getStackInSlot(i).itemID == itemId) { return i; }
        }
        return -1;
    }

    public int getItemAmountByItemId(int itemId)
    {
        int has = 0;
        int slot = getSlotForItem(itemId);
        while (slot != -1)
        {
            if (getStackInSlot(slot).isStackable())
                has += getStackInSlot(slot).stackSize;
            slot = getSlotForItemAfterSlot(itemId,slot);
        }
        return has;
    }
    
    public boolean hasItemAmount(int itemId, int amount)
	{
        return getItemAmountByItemId(itemId)>=amount?true:false;
	}
}
