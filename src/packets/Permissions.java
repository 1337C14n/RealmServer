package packets;

public class Permissions extends NetworkPacket{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7253419213845567272L;
	
	private String playerName;
	private String world;
	private String prefix;
	
	private String[] permissions;
	private String[] groups;
	
	int originatingServer;
	
	public Permissions(String playerName, String world, String[] permissions, String[] groups){
		this.playerName = playerName;
		this.world = world;
		this.permissions = permissions;
		this.groups = groups;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String[] getPermissions() {
		return permissions;
	}

	public void setPermissions(String[] permissions) {
		this.permissions = permissions;
	}

	public String getWorld() {
		return world;
	}

	public void setWorld(String world) {
		this.world = world;
	}

	public int getOriginatingServer() {
		return originatingServer;
	}

	public void setOriginatingServer(int originatingServer) {
		this.originatingServer = originatingServer;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

  public String[] getGroups() {
    return groups;
  }

  public void setGroups(String[] groups) {
    this.groups = groups;
  }

}
