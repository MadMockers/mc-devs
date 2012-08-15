/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.DyeColor;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.entity.Player;
/*		 */ import org.bukkit.entity.Sheep;
/*		 */ import org.bukkit.event.entity.SheepDyeWoolEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class ItemDye extends Item
/*		 */ {
/*	10 */	 public static final String[] a = { "black", "red", "green", "brown", "blue", "purple", "cyan", "silver", "gray", "pink", "lime", "yellow", "lightBlue", "magenta", "orange", "white" };
/*	11 */	 public static final int[] b = { 1973019, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799, 2651799, 4408131, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 15790320 };
/*		 */ 
/*		 */	 public ItemDye(int i) {
/*	14 */		 super(i);
/*	15 */		 a(true);
/*	16 */		 setMaxDurability(0);
/*	17 */		 a(CreativeModeTab.l);
/*		 */	 }
/*		 */ 
/*		 */	 public String c(ItemStack itemstack) {
/*	21 */		 int i = MathHelper.a(itemstack.getData(), 0, 15);
/*		 */ 
/*	23 */		 return super.getName() + "." + a[i];
/*		 */	 }
/*		 */ 
/*		 */	 public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l, float f, float f1, float f2) {
/*	27 */		 if (!entityhuman.e(i, j, k)) {
/*	28 */			 return false;
/*		 */		 }
/*		 */ 
/*	33 */		 if (itemstack.getData() == 15) {
/*	34 */			 int i1 = world.getTypeId(i, j, k);
/*	35 */			 if (i1 == Block.SAPLING.id) {
/*	36 */				 if (!world.isStatic)
/*		 */				 {
/*	38 */					 Player player = (entityhuman instanceof EntityPlayer) ? (Player)entityhuman.getBukkitEntity() : null;
/*	39 */					 ((BlockSapling)Block.SAPLING).grow(world, i, j, k, world.random, true, player, itemstack);
/*		 */				 }
/*		 */ 
/*	44 */				 return true;
/*		 */			 }
/*		 */ 
/*	47 */			 if ((i1 == Block.BROWN_MUSHROOM.id) || (i1 == Block.RED_MUSHROOM.id))
/*		 */			 {
/*	49 */				 if (!world.isStatic) {
/*	50 */					 Player player = (entityhuman instanceof EntityPlayer) ? (Player)entityhuman.getBukkitEntity() : null;
/*	51 */					 ((BlockMushroom)Block.byId[i1]).grow(world, i, j, k, world.random, true, player, itemstack);
/*		 */				 }
/*		 */ 
/*	56 */				 return true;
/*		 */			 }
/*		 */ 
/*	59 */			 if ((i1 == Block.MELON_STEM.id) || (i1 == Block.PUMPKIN_STEM.id)) {
/*	60 */				 if (world.getData(i, j, k) == 7) {
/*	61 */					 return false;
/*		 */				 }
/*		 */ 
/*	64 */				 if (!world.isStatic) {
/*	65 */					 ((BlockStem)Block.byId[i1]).l(world, i, j, k);
/*	66 */					 itemstack.count -= 1;
/*		 */				 }
/*		 */ 
/*	69 */				 return true;
/*		 */			 }
/*		 */ 
/*	72 */			 if (i1 == Block.CROPS.id) {
/*	73 */				 if (world.getData(i, j, k) == 7) {
/*	74 */					 return false;
/*		 */				 }
/*		 */ 
/*	77 */				 if (!world.isStatic) {
/*	78 */					 ((BlockCrops)Block.CROPS).c_(world, i, j, k);
/*	79 */					 itemstack.count -= 1;
/*		 */				 }
/*		 */ 
/*	82 */				 return true;
/*		 */			 }
/*		 */ 
/*	85 */			 if (i1 == Block.COCOA.id) {
/*	86 */				 if (!world.isStatic) {
/*	87 */					 world.setData(i, j, k, 0x8 | BlockDirectional.d(world.getData(i, j, k)));
/*	88 */					 itemstack.count -= 1;
/*		 */				 }
/*		 */ 
/*	91 */				 return true;
/*		 */			 }
/*		 */ 
/*	94 */			 if (i1 == Block.GRASS.id) {
/*	95 */				 if (!world.isStatic) {
/*	96 */					 itemstack.count -= 1;
/*		 */ 
/*	99 */					 for (int j1 = 0; j1 < 128; j1++) {
/* 100 */						 int k1 = i;
/* 101 */						 int l1 = j + 1;
/* 102 */						 int i2 = k;
/*		 */ 
/* 104 */						 int j2 = 0;
/*		 */						 while (true) if (j2 < j1 / 16) {
/* 105 */								 k1 += d.nextInt(3) - 1;
/* 106 */								 l1 += (d.nextInt(3) - 1) * d.nextInt(3) / 2;
/* 107 */								 i2 += d.nextInt(3) - 1;
/* 108 */								 if ((world.getTypeId(k1, l1 - 1, i2) != Block.GRASS.id) || (world.s(k1, l1, i2)))
/*		 */									 break;
/* 104 */								 j2++; continue;
/*		 */							 }
/*		 */							 else
/*		 */							 {
/* 113 */								 if (world.getTypeId(k1, l1, i2) != 0) break;
/* 114 */								 if (d.nextInt(10) != 0) {
/* 115 */									 if (!Block.LONG_GRASS.d(world, k1, l1, i2)) break;
/* 116 */									 world.setTypeIdAndData(k1, l1, i2, Block.LONG_GRASS.id, 1);
/*		 */								 }
/* 118 */								 else if (d.nextInt(3) != 0) {
/* 119 */									 if (!Block.YELLOW_FLOWER.d(world, k1, l1, i2)) break;
/* 120 */									 world.setTypeId(k1, l1, i2, Block.YELLOW_FLOWER.id);
/*		 */								 } else {
/* 122 */									 if (!Block.RED_ROSE.d(world, k1, l1, i2)) break;
/* 123 */									 world.setTypeId(k1, l1, i2, Block.RED_ROSE.id);
/*		 */								 }
/*		 */							 }
/*		 */					 }
/*		 */				 }
/*		 */ 
/* 129 */				 return true;
/*		 */			 }
/* 131 */		 } else if (itemstack.getData() == 3) {
/* 132 */			 int i1 = world.getTypeId(i, j, k);
/* 133 */			 int j1 = world.getData(i, j, k);
/* 134 */			 if ((i1 == Block.LOG.id) && (BlockLog.e(j1) == 3)) {
/* 135 */				 if (l == 0) {
/* 136 */					 return false;
/*		 */				 }
/*		 */ 
/* 139 */				 if (l == 1) {
/* 140 */					 return false;
/*		 */				 }
/*		 */ 
/* 143 */				 if (l == 2) {
/* 144 */					 k--;
/*		 */				 }
/*		 */ 
/* 147 */				 if (l == 3) {
/* 148 */					 k++;
/*		 */				 }
/*		 */ 
/* 151 */				 if (l == 4) {
/* 152 */					 i--;
/*		 */				 }
/*		 */ 
/* 155 */				 if (l == 5) {
/* 156 */					 i++;
/*		 */				 }
/*		 */ 
/* 159 */				 if (world.isEmpty(i, j, k)) {
/* 160 */					 world.setTypeId(i, j, k, Block.COCOA.id);
/* 161 */					 if (world.getTypeId(i, j, k) == Block.COCOA.id) {
/* 162 */						 Block.byId[Block.COCOA.id].postPlace(world, i, j, k, l, f, f1, f2);
/*		 */					 }
/*		 */ 
/* 165 */					 if (!entityhuman.abilities.canInstantlyBuild) {
/* 166 */						 itemstack.count -= 1;
/*		 */					 }
/*		 */				 }
/*		 */ 
/* 170 */				 return true;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 174 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(ItemStack itemstack, EntityLiving entityliving)
/*		 */	 {
/* 179 */		 if ((entityliving instanceof EntitySheep)) {
/* 180 */			 EntitySheep entitysheep = (EntitySheep)entityliving;
/* 181 */			 int i = BlockCloth.e_(itemstack.getData());
/*		 */ 
/* 183 */			 if ((!entitysheep.isSheared()) && (entitysheep.getColor() != i))
/*		 */			 {
/* 185 */				 byte bColor = (byte)i;
/* 186 */				 SheepDyeWoolEvent event = new SheepDyeWoolEvent((Sheep)entitysheep.getBukkitEntity(), DyeColor.getByData(bColor));
/* 187 */				 entitysheep.world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/* 189 */				 if (event.isCancelled()) {
/* 190 */					 return false;
/*		 */				 }
/*		 */ 
/* 193 */				 i = event.getColor().getData();
/*		 */ 
/* 196 */				 entitysheep.setColor(i);
/* 197 */				 itemstack.count -= 1;
/*		 */			 }
/*		 */ 
/* 200 */			 return true;
/*		 */		 }
/* 202 */		 return false;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ItemDye
 * JD-Core Version:		0.6.0
 */