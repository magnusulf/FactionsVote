package dk.muj.factionsvote.entity;

import java.util.HashMap;
import java.util.Map;

public class Election
{

	//The name/key of the election
	private String name;
	public String getName()					{return name;		}
	public void setName(String name )		{this.name = name;	}
	
	//A more in depth description
	private String desc;
	public String getDesc()					{return desc;		}
	public void setDesc(String desc )		{this.desc = desc;	}
	
	//All the votes. String as ID and boolean as yes/no
	private Map<String, Boolean> votes = new HashMap<>();
	public Map<String, Boolean> getVotes()	{return votes;		}
	
	
	public Election(String name, String description)
	{
		this.name = name;
		this.desc = description;
	}
}
