/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build;

import au.com.shawware.util.SwAssert;

/**
 * Specifies a single entity creation instruction.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public class Instruction
{
    /** The type of entity to be constructed. */
    private final EntityType mEntityType;
    /** The new entity's level. */
    private final int mLevel;
    /** The new entity's mod type (for a mod). */
    private final ModType mModType;
    /** The new entity's weapon type (for a weapon). */
    private final WeaponType mWeaponType;

    /**
     * Create a new instruction.
     * 
     * @param entityType the type of entity to be constructed
     * @param level the level of entity to be constructed
     * @param subtype the sub-type of the entity to be constructed
     */
    public Instruction(final EntityType entityType, final int level, final Enum<?> subtype)
    {
        SwAssert.notNull(entityType);
        SwAssert.True(Entity.isValidLevel(level), "invalid level: " + level); //$NON-NLS-1$
        SwAssert.notNull(subtype);
        SwAssert.True(((subtype instanceof ModType) || (subtype instanceof WeaponType)), "not a game type"); //$NON-NLS-1$

        mEntityType = entityType;
        mLevel = level;
        if (subtype instanceof WeaponType)
        {
            mModType = null;
            mWeaponType = (WeaponType)subtype;
        }
        else
        {
            mModType = (ModType)subtype;
            mWeaponType = null;
        }
    }

    /**
     * @return The type of entity to be constructed.
     */
    public EntityType getEntityType()
    {
        return mEntityType;
    }

    /**
     * @return The level of the entity to be constructed.
     */
    public int getLevel()
    {
        return mLevel;
    }

    /**
     * @return This instruction's mod type (for a mod instruction only).
     */
    public ModType getModType()
    {
        SwAssert.True(mEntityType.equals(EntityType.MOD), "not a mod instruction"); //$NON-NLS-1$

        return mModType;
    }

    /**
     * @return This instruction's weapon type (for a weapon instruction only).
     */
    public WeaponType getWeaponType()
    {
        SwAssert.True(mEntityType.equals(EntityType.WEAPON), "not a weapon instruction"); //$NON-NLS-1$

        return mWeaponType;
    }

    @Override
    public String toString()
    {
        final StringBuilder s = new StringBuilder();
        s.append(Instruction.class.getSimpleName())
         .append('(')
         .append(mEntityType)
         .append(',')
         .append(' ')
         .append(mLevel)
         .append(',')
         .append(' ');
        if (mEntityType.equals(EntityType.MOD))
        {
            s.append(mModType);
        }
        else
        {
            s.append(mWeaponType);
        }
        s.append(')');
        return s.toString();
    }
}
