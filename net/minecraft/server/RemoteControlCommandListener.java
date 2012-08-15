package net.minecraft.server;

public class RemoteControlCommandListener
	implements ICommandListener
{
	public static final RemoteControlCommandListener instance = new RemoteControlCommandListener();

	private StringBuffer b = new StringBuffer();

	public void b() {
		this.b.setLength(0);
	}

	public String c() {
		return this.b.toString();
	}

	public String getName() {
		return "Rcon";
	}

	public void sendMessage(String paramString) {
		this.b.append(paramString);
	}

	public boolean b(String paramString) {
		return true;
	}

	public String a(String paramString, Object[] paramArrayOfObject) {
		return LocaleLanguage.a().a(paramString, paramArrayOfObject);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.RemoteControlCommandListener
 * JD-Core Version:		0.6.0
 */