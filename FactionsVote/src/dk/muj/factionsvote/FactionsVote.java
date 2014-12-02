package dk.muj.factionsvote;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.MPerm;
import com.massivecraft.massivecore.MassivePlugin;
import com.massivecraft.massivecore.util.MUtil;

import dk.muj.factionsvote.cmd.CmdFactionsVote;
import dk.muj.factionsvote.entity.VFactionColl;

public class FactionsVote extends MassivePlugin
{
	
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	public static MPerm createElection;
	public static MPerm showElections;
	
	private static FactionsVote i;
	public static FactionsVote get() { return i; }
	public FactionsVote() { i = this; }

	@Override
	public void onEnable()
	{
		super.preEnable();
		
		VFactionColl.get().init();
		
		Factions.get().getOuterCmdFactions().addSubCommand(new CmdFactionsVote(), 6);
		createElection = MPerm.getCreative(13100, "elec", "elec", "create election", MUtil.set(Rel.LEADER, Rel.OFFICER), false, true, true);
		showElections = MPerm.getCreative(13200, "listElec", "listElec", "lists elections", MUtil.set(Rel.LEADER, Rel.OFFICER, Rel.MEMBER, Rel.RECRUIT, Rel.ALLY), false, true, true);
		super.postEnable();
	}
}
