package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet3Chat extends Packet
{
	public static int a = 119;
	public String message;
	private boolean c;

	public Packet3Chat()
	{
		this.c = true;
	}

	public Packet3Chat(String s) {
		this(s, true);
	}

	public Packet3Chat(String s, boolean flag) {
		this.c = true;

		this.message = s;
		this.c = flag;
	}

	public void a(DataInputStream datainputstream) throws IOException {
		this.message = a(datainputstream, a);
	}

	public void a(DataOutputStream dataoutputstream) throws IOException {
		a(this.message, dataoutputstream);
	}

	public void handle(NetHandler nethandler) {
		nethandler.a(this);
	}

	public int a() {
		return 2 + this.message.length() * 2;
	}

	public boolean isServer() {
		return this.c;
	}

	public boolean a_() {
		return !this.message.startsWith("/");
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Packet3Chat
 * JD-Core Version:		0.6.0
 */