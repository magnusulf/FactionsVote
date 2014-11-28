package dk.muj.factionsvote.entity;

import com.massivecraft.massivecore.store.Coll;
import com.massivecraft.massivecore.store.MStore;

import dk.muj.factionsvote.Const;
import dk.muj.factionsvote.FactionsVote;

public class VFactionColl extends Coll<VFaction>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static VFactionColl i = new VFactionColl();
	public static VFactionColl get() { return i; }
	private VFactionColl()
	{
		super(Const.COLLECTION_VFACTION, VFaction.class, MStore.getDb(), FactionsVote.get());
	}

}
