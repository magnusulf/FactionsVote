package dk.muj.factionsvote.cmd;

import java.util.ArrayList;
import java.util.List;

import com.massivecraft.factions.cmd.arg.ARFaction;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.massivecore.cmd.arg.ARInteger;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.util.Txt;

import dk.muj.factionsvote.Perm;
import dk.muj.factionsvote.entity.Election;
import dk.muj.factionsvote.entity.VFaction;

public class CmdFactionsVoteList extends FactionsVoteCommand
{

	public CmdFactionsVoteList()
	{
		this.addAliases("l","list");
		this.addOptionalArg("page", "1");
		this.addOptionalArg("faction", "you");
		
		super.setDesc("Lists elections");
		
		this.addRequirements(ReqHasPerm.get(Perm.LIST.node));
	}
	
	@Override
	public void perform()
	{
		Integer page = this.arg(0,ARInteger.get(),1);
		if(null == page) return;
		Faction faction = this.arg(1,ARFaction.get(), msenderFaction);
		if(null == faction) return;
		VFaction vfaction = VFaction.get(faction);
		if(faction.isNone()) {msender.sendMessage(Txt.parse("<b>Votes are disabled for default faction")); return;}
		
		if(!msenderFaction.getPermitted("listElec").contains(msender.getRelationTo(msenderFaction)) && !msender.isUsingAdminMode())
		{
			msender.sendMessage(Txt.parse("<b>You cannot view this factions elections"));
			return;
		}
		
		// Create Messages
		List<String> msgLines = new ArrayList<String>();
		
		List<Election> electionList = vfaction.getElections();
		int listSize = electionList.size();
		
		final int pageHeight = Txt.PAGEHEIGHT_PLAYER;
		
		int pagecount = (listSize / pageHeight) + 1;
		if(page < 1)
			page = 1;
		else if(page > pagecount)
			pagecount = page;
		int startIndex = (page - 1) * pageHeight;
		int endIndex = startIndex + pageHeight;
		if (endIndex > listSize)
			endIndex = listSize;
		
		msgLines.add(Txt.titleize(Txt.parse("Election List for Faction "+faction.getName(msender)+" <gold>"+page+"/"+pagecount)));
		for(Election e: electionList.subList(startIndex, endIndex))
		{
			String result = "<aqua>"+e.getName()+" <yellow>"+e.getDesc()+"<black> | ";
			if(e.getVotes().size() < 1)
				result += "<silver>no votes";
			else
			{
				float yes = 0;
				float no = 0;
				for(Boolean b: e.getVotes().values())
					if (b.booleanValue() == true) yes++;
					else no++;
				float total = yes + no;
				result += "<lime>"+yes/total*100+"% YES  <rose>" + no/total*100+"% NO <pink>"+((int)total)+" votes";
			}
			
			msgLines.add(Txt.parse(result));
		}
		if(msgLines.size() == 1)
			msgLines.add(Txt.parse("<yellow>No elections right now"));		
		
		msender.sendMessage(msgLines);
	}
}
