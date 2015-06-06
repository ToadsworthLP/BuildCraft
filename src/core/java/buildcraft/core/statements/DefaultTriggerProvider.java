/**
 * Copyright (c) 2011-2015, SpaceToad and the BuildCraft Team
 * http://www.mod-buildcraft.com
 *
 * BuildCraft is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package buildcraft.core.statements;

import java.util.LinkedList;

import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import buildcraft.api.statements.IStatementContainer;
import buildcraft.api.statements.ITriggerExternal;
import buildcraft.api.statements.ITriggerInternal;
import buildcraft.api.statements.ITriggerProvider;
import buildcraft.api.statements.containers.IRedstoneStatementContainer;
import buildcraft.api.tiles.IHasWork;
import buildcraft.core.BuildCraftCore;

public class DefaultTriggerProvider implements ITriggerProvider {
	@Override
	public LinkedList<ITriggerExternal> getExternalTriggers(EnumFacing side, TileEntity tile) {
		LinkedList<ITriggerExternal> res = new LinkedList<ITriggerExternal>();

		if (tile instanceof IInventory && ((IInventory) tile).getSizeInventory() > 0) {
			res.add(BuildCraftCore.triggerEmptyInventory);
			res.add(BuildCraftCore.triggerContainsInventory);
			res.add(BuildCraftCore.triggerSpaceInventory);
			res.add(BuildCraftCore.triggerFullInventory);
			res.add(BuildCraftCore.triggerInventoryBelow25);
			res.add(BuildCraftCore.triggerInventoryBelow50);
			res.add(BuildCraftCore.triggerInventoryBelow75);
		}

		if (tile instanceof IFluidHandler) {
			FluidTankInfo[] tanks = ((IFluidHandler) tile).getTankInfo(side.getOpposite());
			if (tanks != null && tanks.length > 0) {
				res.add(BuildCraftCore.triggerEmptyFluid);
				res.add(BuildCraftCore.triggerContainsFluid);
				res.add(BuildCraftCore.triggerSpaceFluid);
				res.add(BuildCraftCore.triggerFullFluid);
				res.add(BuildCraftCore.triggerFluidContainerBelow25);
				res.add(BuildCraftCore.triggerFluidContainerBelow50);
				res.add(BuildCraftCore.triggerFluidContainerBelow75);
			}
		}

		if (tile instanceof IHasWork) {
			res.add(BuildCraftCore.triggerMachineActive);
			res.add(BuildCraftCore.triggerMachineInactive);
		}

		return res;
	}

	@Override
	public LinkedList<ITriggerInternal> getInternalTriggers(IStatementContainer container) {
		LinkedList<ITriggerInternal> res = new LinkedList<ITriggerInternal>();

		if (container instanceof IRedstoneStatementContainer) {
			res.add(BuildCraftCore.triggerRedstoneActive);
			res.add(BuildCraftCore.triggerRedstoneInactive);
		}

		if (TriggerEnergy.isTriggeringPipe(container.getTile()) || TriggerEnergy.getTriggeringNeighbor(container.getTile()) != null) {
			res.add((ITriggerInternal) BuildCraftCore.triggerEnergyHigh);
			res.add((ITriggerInternal) BuildCraftCore.triggerEnergyLow);
		}

		return res;
	}
}