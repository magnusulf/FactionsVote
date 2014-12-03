package dk.muj.factionsvote.cmd;

import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.util.Txt;

import dk.muj.factionsvote.Perm;
import dk.muj.factionsvote.entity.VFaction;

public class CmdFactionsVoteDelete extends FactionsVoteCommand
{

	public CmdFactionsVoteDelete()
	{
		super.addAliases("d","delete");
		super.addRequiredArg("name");
		
		super.setDesc("Deletes an election");
		
		super.setErrorOnToManyArgs(true);
		
		super.addRequirements(ReqHasPerm.get(Perm.DELETE.node));
	}
	
	@Override
	public void perform()
	{
		if(msenderFaction.isNone()) {msender.sendMessage(Txt.parse("<b>Votes are disabled for default faction")); return;}
		if(!msenderFaction.getPermitted("elec").contains(msender.getRelationTo(msenderFaction)) && !msender.isUsingAdminMode())
		{
			msender.sendMessage(Txt.parse("<b>You cannot delete elections in this faction"));
			return;
		}
		
		String name = super.arg(0);
		VFaction vfaction = VFaction.get(msenderFaction);
		
		if(vfaction.GetElection(name) == null){msender.sendMessage(Txt.parse("<b>Couldn't find election")); return;}
		vfaction.RemoveElection(name);
		
		msender.sendMessage(Txt.parse("<lime>You deleted an election called <aqua>"+name));
		
	}
}