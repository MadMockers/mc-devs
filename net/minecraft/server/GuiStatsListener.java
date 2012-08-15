package net.minecraft.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GuiStatsListener
	implements ActionListener
{
	GuiStatsListener(GuiStatsComponent paramGuiStatsComponent)
	{
	}

	public void actionPerformed(ActionEvent paramActionEvent)
	{
		GuiStatsComponent.a(this.a);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.GuiStatsListener
 * JD-Core Version:		0.6.0
 */