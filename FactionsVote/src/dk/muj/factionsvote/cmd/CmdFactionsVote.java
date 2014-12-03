package dk.muj.factionsvote.cmd;

import com.massivecraft.massivecore.cmd.HelpCommand;
import com.massivecraft.massivecore.cmd.VersionCommand;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;

import dk.muj.factionsvote.FactionsVote;
import dk.muj.factionsvote.Perm;


public class CmdFactionsVote extends FactionsVoteCommand
{
	CmdFactionsVoteList innerCmdFactionsVoteList = new CmdFactionsVoteList();
	CmdFactionsVoteVote innerCmdFactionsVoteVote = new CmdFactionsVoteVote();
	CmdFactionsVoteCreate innerCmdFactionsVoteCreate = new CmdFactionsVoteCreate();
	CmdFactionsVoteDelete innerCmdFactionsVoteDelete = new CmdFactionsVoteDelete();
	VersionCommand innerVersionCommand = new VersionCommand(FactionsVote.get(), Perm.VERSION.node, "ver", "version");
	
	
	public CmdFactionsVote()
	{
		super.addAliases("vote");
		super.setDesc("Allow factions to vote");
		
		super.addSubCommand(HelpCommand.get());
		super.addSubCommand(innerCmdFactionsVoteList);
		super.addSubCommand(innerCmdFactionsVoteCreate);
		super.addSubCommand(innerCmdFactionsVoteVote);
		super.addSubCommand(innerCmdFactionsVoteDelete);
		super.addSubCommand(innerVersionCommand);
		
		super.addRequirements(ReqHasPerm.get(Perm.BASECOMMAND.node));
	}
}
