package net.minecraft.server;

import java.util.logging.Logger;

public class CommandDispatcher extends CommandHandler
	implements ICommandDispatcher
{
	public CommandDispatcher()
	{
		a(new CommandTime());
		a(new CommandGamemode());
		a(new CommandGamemodeDefault());
		a(new CommandKill());
		a(new CommandToggleDownfall());
		a(new CommandXp());
		a(new CommandTp());
		a(new CommandGive());
		a(new CommandMe());
		a(new CommandSeed());
		a(new CommandHelp());
		a(new CommandDebug());

		if (MinecraftServer.getServer().S()) {
			a(new CommandOp());
			a(new CommandDeop());
			a(new CommandStop());
			a(new CommandSaveAll());
			a(new CommandSaveOff());
			a(new CommandSaveOn());
			a(new CommandBanIp());
			a(new CommandPardonIP());
			a(new CommandBan());
			a(new CommandBanList());
			a(new CommandPardon());
			a(new CommandKick());
			a(new CommandList());
			a(new CommandSay());
			a(new CommandWhitelist());
		} else {
			a(new CommandPublish());
		}

		CommandAbstract.a(this);
	}

	public void a(ICommandListener paramICommandListener, int paramInt, String paramString, Object[] paramArrayOfObject) {
		for (EntityPlayer localEntityPlayer : MinecraftServer.getServer().getServerConfigurationManager().players) {
			if ((localEntityPlayer != paramICommandListener) && (MinecraftServer.getServer().getServerConfigurationManager().isOp(localEntityPlayer.name))) {
				localEntityPlayer.sendMessage("§7§o[" + paramICommandListener.getName() + ": " + localEntityPlayer.a(paramString, paramArrayOfObject) + "]");
			}
		}

		if (paramICommandListener != MinecraftServer.getServer()) {
			MinecraftServer.log.info("[" + paramICommandListener.getName() + ": " + MinecraftServer.getServer().a(paramString, paramArrayOfObject) + "]");
		}

		if ((paramInt & 0x1) != 1)
			paramICommandListener.sendMessage(paramICommandListener.a(paramString, paramArrayOfObject));
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.CommandDispatcher
 * JD-Core Version:		0.6.0
 */