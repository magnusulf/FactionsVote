package dk.muj.factionsvote.cmd;

import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.util.Txt;

import dk.muj.factionsvote.FactionsVote;
import dk.muj.factionsvote.Perm;
import dk.muj.factionsvote.entity.Election;
import dk.muj.factionsvote.entity.VFaction;

public class CmdFactionsVoteCreate extends FactionsVoteCommand
{

	public CmdFactionsVoteCreate()
	{
		super.addAliases("c","create");
		super.addRequiredArg("name");
		super.addRequiredArg("desc");
		
		super.setDesc("Creates a new election");
		
		super.setErrorOnToManyArgs(false);
		
		super.addRequirements(ReqHasPerm.get(Perm.CREATE.node));
	}
	
	@Override
	public void perform()
	{
		if(!msenderFaction.getPermitted(FactionsVote.createElection).contains(msenderFaction.getRelationTo(msender)));
		{
			msender.sendMessage(Txt.parse("<b>You cannot create elections in this faction"));
		}
		
		String name = super.arg(0);
		String desc = super.argConcatFrom(1);
		if(msenderFaction.isNone()) {msender.sendMessage(Txt.parse("<b>Votes are disabled for default faction")); return;}
		VFaction vfaction = VFaction.get(msenderFaction);
		
		if(vfaction.GetElection(name) != null){msender.sendMessage(Txt.parse("<b>Name is already taken")); return;}
		vfaction.getElections().add(new Election(name, desc));
		
		msender.sendMessage(Txt.parse("<lime>You made an election called <aqua>"+name+"<lime> with the description <yellow>"+desc));
		
	}
}
