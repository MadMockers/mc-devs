/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ public enum EnumEntitySize
/*		 */ {
/*		 */	 public int a(double paramDouble)
/*		 */	 {
/* 105 */		 double d = paramDouble - (MathHelper.floor(paramDouble) + 0.5D);
/*		 */ 
/* 107 */		 switch (EntitySizes.a[ordinal()]) {
/*		 */		 case 1:
/* 109 */			 if (d < 0.0D ? d < -0.3125D : d < 0.3125D) {
/* 110 */				 return MathHelper.f(paramDouble * 32.0D);
/*		 */			 }
/*		 */ 
/* 113 */			 return MathHelper.floor(paramDouble * 32.0D);
/*		 */		 case 2:
/* 115 */			 if (d < 0.0D ? d < -0.3125D : d < 0.3125D) {
/* 116 */				 return MathHelper.floor(paramDouble * 32.0D);
/*		 */			 }
/*		 */ 
/* 119 */			 return MathHelper.f(paramDouble * 32.0D);
/*		 */		 case 3:
/* 121 */			 if (d > 0.0D) {
/* 122 */				 return MathHelper.floor(paramDouble * 32.0D);
/*		 */			 }
/*		 */ 
/* 125 */			 return MathHelper.f(paramDouble * 32.0D);
/*		 */		 case 4:
/* 127 */			 if (d < 0.0D ? d < -0.1875D : d < 0.1875D) {
/* 128 */				 return MathHelper.f(paramDouble * 32.0D);
/*		 */			 }
/*		 */ 
/* 131 */			 return MathHelper.floor(paramDouble * 32.0D);
/*		 */		 case 5:
/* 133 */			 if (d < 0.0D ? d < -0.1875D : d < 0.1875D) {
/* 134 */				 return MathHelper.floor(paramDouble * 32.0D);
/*		 */			 }
/*		 */ 
/* 137 */			 return MathHelper.f(paramDouble * 32.0D);
/*		 */		 case 6:
/*		 */		 }
/* 140 */		 if (d > 0.0D) {
/* 141 */			 return MathHelper.f(paramDouble * 32.0D);
/*		 */		 }
/*		 */ 
/* 144 */		 return MathHelper.floor(paramDouble * 32.0D);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EnumEntitySize
 * JD-Core Version:		0.6.0
 */