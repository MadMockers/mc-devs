package net.minecraft.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Set;
import java.util.logging.Logger;

public class ServerConfigurationManager extends ServerConfigurationManagerAbstract
{
	private File e;
	private File f;

	public ServerConfigurationManager(DedicatedServer paramDedicatedServer)
	{
		super(paramDedicatedServer);

		this.e = paramDedicatedServer.f("ops.txt");
		this.f = paramDedicatedServer.f("white-list.txt");
		this.d = paramDedicatedServer.a("view-distance", 10);
		this.maxPlayers = paramDedicatedServer.a("max-players", 20);
		setHasWhitelist(paramDedicatedServer.a("white-list", false));

		if (!paramDedicatedServer.H()) {
			getNameBans().setEnabled(true);
			getIPBans().setEnabled(true);
		}

		getNameBans().load();
		getNameBans().save();
		getIPBans().load();
		getIPBans().save();
		t();
		v();
		u();
		w();
	}

	public void setHasWhitelist(boolean paramBoolean)
	{
		super.setHasWhitelist(paramBoolean);
		getServer().a("white-list", Boolean.valueOf(paramBoolean));
		getServer().a();
	}

	public void addOp(String paramString)
	{
		super.addOp(paramString);
		u();
	}

	public void removeOp(String paramString)
	{
		super.removeOp(paramString);
		u();
	}

	public void removeWhitelist(String paramString)
	{
		super.removeWhitelist(paramString);
		w();
	}

	public void addWhitelist(String paramString)
	{
		super.addWhitelist(paramString);
		w();
	}

	public void reloadWhitelist()
	{
		v();
	}

	private void t() {
		try {
			getOPs().clear();
			BufferedReader localBufferedReader = new BufferedReader(new FileReader(this.e));
			String str = "";
			while ((str = localBufferedReader.readLine()) != null) {
				getOPs().add(str.trim().toLowerCase());
			}
			localBufferedReader.close();
		} catch (Exception localException) {
			a.warning("Failed to load operators list: " + localException);
		}
	}

	private void u() {
		try {
			PrintWriter localPrintWriter = new PrintWriter(new FileWriter(this.e, false));
			for (String str : getOPs()) {
				localPrintWriter.println(str);
			}
			localPrintWriter.close();
		} catch (Exception localException) {
			a.warning("Failed to save operators list: " + localException);
		}
	}

	private void v() {
		try {
			getWhitelisted().clear();
			BufferedReader localBufferedReader = new BufferedReader(new FileReader(this.f));
			String str = "";
			while ((str = localBufferedReader.readLine()) != null) {
				getWhitelisted().add(str.trim().toLowerCase());
			}
			localBufferedReader.close();
		} catch (Exception localException) {
			a.warning("Failed to load white-list: " + localException);
		}
	}

	private void w() {
		try {
			PrintWriter localPrintWriter = new PrintWriter(new FileWriter(this.f, false));
			for (String str : getWhitelisted()) {
				localPrintWriter.println(str);
			}
			localPrintWriter.close();
		} catch (Exception localException) {
			a.warning("Failed to save white-list: " + localException);
		}
	}

	public boolean isWhitelisted(String paramString) {
		paramString = paramString.trim().toLowerCase();
		return (!getHasWhitelist()) || (isOp(paramString)) || (getWhitelisted().contains(paramString));
	}

	public DedicatedServer getServer()
	{
		return (DedicatedServer)super.getServer();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ServerConfigurationManager
 * JD-Core Version:		0.6.0
 */