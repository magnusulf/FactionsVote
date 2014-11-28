package dk.muj.factionsvote;

import com.massivecraft.factions.Factions;
import com.massivecraft.massivecore.MassivePlugin;

import dk.muj.factionsvote.cmd.CmdFactionsVote;
import dk.muj.factionsvote.entity.VFactionColl;

public class FactionsVote extends MassivePlugin
{
	
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static FactionsVote i;
	public static FactionsVote get() { return i; }
	public FactionsVote() { i = this; }

	@Override
	public void onEnable()
	{
		super.preEnable();
		
		VFactionColl.get().init();
		
		Factions.get().getOuterCmdFactions().addSubCommandAfter(new CmdFactionsVote(), Factions.get().getOuterCmdFactions().getSubCommands().get(6));
		super.postEnable();
	}
}
