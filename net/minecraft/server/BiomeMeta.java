/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ public class BiomeMeta extends WeightedRandomChoice
/*		 */ {
/*		 */	 public Class b;
/*		 */	 public int c;
/*		 */	 public int d;
/*		 */ 
/*		 */	 public BiomeMeta(Class paramClass, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 172 */		 super(paramInt1);
/* 173 */		 this.b = paramClass;
/* 174 */		 this.c = paramInt2;
/* 175 */		 this.d = paramInt3;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BiomeMeta
 * JD-Core Version:		0.6.0
 */