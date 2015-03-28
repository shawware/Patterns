/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build;

import java.util.List;

/**
 * Describes the interface for constructing an {@link Inventory}.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public interface IInventoryFactory extends IElement
{
    /**
     * Creates an inventory based on the given list of instructions.
     * 
     * @param instructions the instructions specifying the items to add to the inventory
     * 
     * @return The constructed inventory.
     */
    public Inventory createInventory(final List<Instruction> instructions);
}
