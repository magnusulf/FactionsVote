package dk.muj.factionsvote;

import org.bukkit.permissions.Permissible;

import com.massivecraft.massivecore.util.PermUtil;

public enum Perm
{
	BASECOMMAND("use"),
	VOTE("vote"),
	CREATE("create"),
	DELETE("delete"),
	LIST("list"), 
	LIST_OTHERS("list.others"),
	VERSION("version"),
	
	// END OF LIST
	;
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public final String node;
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	// When constructing the Enum, take the String of it and add our pluginname in beforehand
	Perm(final String node)
	{
		this.node = "factions.vote." + node;
	}
		
	// -------------------------------------------- //
	// ToString
	// -------------------------------------------- //
	
	@Override
	public String toString()
	{
		return node;
	}
	
	// -------------------------------------------- //
	// HAS
	// -------------------------------------------- //
	
	// Two has-methodes, one only with permissible and with with a boolean informSenderIfNot
	public boolean has(Permissible permissible, boolean informSenderIfNot)
	{
		return PermUtil.has(permissible, this.node, informSenderIfNot);
	}
	
	public boolean has(Permissible permissible)
	{
		return has(permissible, false);
	}
	
}
