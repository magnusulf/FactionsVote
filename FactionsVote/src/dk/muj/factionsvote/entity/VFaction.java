package dk.muj.factionsvote.entity;

import java.util.ArrayList;
import java.util.List;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.massivecore.store.Entity;

public class VFaction extends Entity<VFaction>
{

	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
		
		public static VFaction get(Object oid)
		{
			return VFactionColl.get().get(oid);
		}
		
		public static VFaction get(Faction faction)
		{
			return VFactionColl.get().get(faction.getId(), true);
		}
		
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
		
		//The fist string is the name of the election.
		//The second is the description.
		private List<Election> elections = new ArrayList<Election>();
		public List<Election> getElections(){return elections;}
		
		public void AddElection(Election e)
		{
			this.elections.add(e);
		}
		
		public Election GetElection(String name)
		{
			for(Election e: this.elections)
				if(e.getName().equalsIgnoreCase(name))
					return e;
			return null;
		}
		
}
