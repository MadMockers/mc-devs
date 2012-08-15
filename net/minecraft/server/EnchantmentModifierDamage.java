/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ final class EnchantmentModifierDamage
/*		 */	 implements EnchantmentModifier
/*		 */ {
/*		 */	 public int a;
/*		 */	 public EntityLiving b;
/*		 */ 
/*		 */	 public void a(Enchantment paramEnchantment, int paramInt)
/*		 */	 {
/* 111 */		 this.a += paramEnchantment.a(paramInt, this.b);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EnchantmentModifierDamage
 * JD-Core Version:		0.6.0
 */