package dk.muj.factionsvote.cmd;

import com.massivecraft.massivecore.cmd.arg.ARBoolean;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.util.Txt;

import dk.muj.factionsvote.Perm;
import dk.muj.factionsvote.entity.Election;
import dk.muj.factionsvote.entity.VFaction;

public class CmdFactionsVoteVote extends FactionsVoteCommand
{

	public CmdFactionsVoteVote()
	{
		super.addAliases("v","vote");
		super.addRequiredArg("name");
		super.addRequiredArg("yes/no");
		
		super.setDesc("Votes in said election");
		
		super.addRequirements(ReqHasPerm.get(Perm.VOTE.node));
	}
	
	@Override
	public void perform()
	{
		String name = super.arg(0);
		Boolean vote = super.arg(1,ARBoolean.get());
		if(null == vote) return;
		VFaction vfaction = VFaction.get(msenderFaction);
		Election election = vfaction.GetElection(name);
		if(null == election)
		{
			msender.sendMessage(Txt.parse("<b>No election with the name <yellow>"+name+" <b>exists"));
			return;
		}
		
		election.getVotes().put(msender.getId(), vote);
		msender.sendMessage(Txt.parse("<lime>You voted <yellow>"+BooleanToYesNo(vote)+"<lime> to <aqua>"+election.getName()));
		
		
	}
	
	private String BooleanToYesNo(Boolean b)
	{
		if(b.booleanValue() == true)
			return "yes";
		else
			return "no";
	}

}
