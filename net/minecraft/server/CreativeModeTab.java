/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ public class CreativeModeTab
/*		 */ {
/*	 9 */	 public static final CreativeModeTab[] a = new CreativeModeTab[12];
/*	10 */	 public static final CreativeModeTab b = new CreativeModeTab1(0, "buildingBlocks");
/*		 */ 
/*	16 */	 public static final CreativeModeTab c = new CreativeModeTab2(1, "decorations");
/*		 */ 
/*	22 */	 public static final CreativeModeTab d = new CreativeModeTab3(2, "redstone");
/*		 */ 
/*	28 */	 public static final CreativeModeTab e = new CreativeModeTab4(3, "transportation");
/*		 */ 
/*	34 */	 public static final CreativeModeTab f = new CreativeModeTab5(4, "misc");
/*		 */ 
/*	40 */	 public static final CreativeModeTab g = new CreativeModeTab6(5, "search").a("search.png");
/*		 */ 
/*	47 */	 public static final CreativeModeTab h = new CreativeModeTab7(6, "food");
/*		 */ 
/*	53 */	 public static final CreativeModeTab i = new CreativeModeTab8(7, "tools");
/*		 */ 
/*	59 */	 public static final CreativeModeTab j = new CreativeModeTab9(8, "combat");
/*		 */ 
/*	65 */	 public static final CreativeModeTab k = new CreativeModeTab10(9, "brewing");
/*		 */ 
/*	71 */	 public static final CreativeModeTab l = new CreativeModeTab11(10, "materials");
/*		 */ 
/*	77 */	 public static final CreativeModeTab m = new CreativeModeTab12(11, "inventory").a("survival_inv.png").j().h();
/*		 */	 private final int n;
/*		 */	 private final String o;
/*	86 */	 private String p = "list_items.png";
/*	87 */	 private boolean q = true;
/*	88 */	 private boolean r = true;
/*		 */ 
/*		 */	 public CreativeModeTab(int paramInt, String paramString) {
/*	91 */		 this.n = paramInt;
/*	92 */		 this.o = paramString;
/*		 */ 
/*	94 */		 a[paramInt] = this;
/*		 */	 }
/*		 */ 
/*		 */	 public CreativeModeTab a(String paramString)
/*		 */	 {
/* 122 */		 this.p = paramString;
/* 123 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public CreativeModeTab h()
/*		 */	 {
/* 131 */		 this.r = false;
/* 132 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public CreativeModeTab j()
/*		 */	 {
/* 140 */		 this.q = false;
/* 141 */		 return this;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CreativeModeTab
 * JD-Core Version:		0.6.0
 */