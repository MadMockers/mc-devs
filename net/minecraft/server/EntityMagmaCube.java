/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.inventory.ItemStack;
/*		 */ 
/*		 */ public class EntityMagmaCube extends EntitySlime
/*		 */ {
/*		 */	 public EntityMagmaCube(World world)
/*		 */	 {
/*	 6 */		 super(world);
/*	 7 */		 this.texture = "/mob/lava.png";
/*	 8 */		 this.fireProof = true;
/*	 9 */		 this.aG = 0.2F;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canSpawn() {
/*	13 */		 return (this.world.difficulty > 0) && (this.world.b(this.boundingBox)) && (this.world.getCubes(this, this.boundingBox).isEmpty()) && (!this.world.containsLiquid(this.boundingBox));
/*		 */	 }
/*		 */ 
/*		 */	 public int aO() {
/*	17 */		 return getSize() * 3;
/*		 */	 }
/*		 */ 
/*		 */	 public float c(float f) {
/*	21 */		 return 1.0F;
/*		 */	 }
/*		 */ 
/*		 */	 protected String i() {
/*	25 */		 return "flame";
/*		 */	 }
/*		 */ 
/*		 */	 protected EntitySlime j() {
/*	29 */		 return new EntityMagmaCube(this.world);
/*		 */	 }
/*		 */ 
/*		 */	 protected int getLootId() {
/*	33 */		 return Item.MAGMA_CREAM.id;
/*		 */	 }
/*		 */ 
/*		 */	 protected void dropDeathLoot(boolean flag, int i)
/*		 */	 {
/*	38 */		 List loot = new ArrayList();
/*	39 */		 int j = getLootId();
/*		 */ 
/*	41 */		 if ((j > 0) && (getSize() > 1)) {
/*	42 */			 int k = this.random.nextInt(4) - 2;
/*		 */ 
/*	44 */			 if (i > 0) {
/*	45 */				 k += this.random.nextInt(i + 1);
/*		 */			 }
/*		 */ 
/*	48 */			 if (k > 0) {
/*	49 */				 loot.add(new ItemStack(j, k));
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	53 */		 CraftEventFactory.callEntityDeathEvent(this, loot);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isBurning()
/*		 */	 {
/*	58 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 protected int k() {
/*	62 */		 return super.k() * 4;
/*		 */	 }
/*		 */ 
/*		 */	 protected void l() {
/*	66 */		 this.a *= 0.9F;
/*		 */	 }
/*		 */ 
/*		 */	 protected void aZ() {
/*	70 */		 this.motY = (0.42F + getSize() * 0.1F);
/*	71 */		 this.al = true;
/*		 */	 }
/*		 */	 protected void a(float f) {
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean m() {
/*	77 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 protected int n() {
/*	81 */		 return super.n() + 2;
/*		 */	 }
/*		 */ 
/*		 */	 protected String aR() {
/*	85 */		 return "mob.slime";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aS() {
/*	89 */		 return "mob.slime";
/*		 */	 }
/*		 */ 
/*		 */	 protected String o() {
/*	93 */		 return getSize() > 1 ? "mob.magmacube.big" : "mob.magmacube.small";
/*		 */	 }
/*		 */ 
/*		 */	 public boolean J() {
/*	97 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean p() {
/* 101 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityMagmaCube
 * JD-Core Version:		0.6.0
 */