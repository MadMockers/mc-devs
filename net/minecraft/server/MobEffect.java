/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.PrintStream;
/*		 */ 
/*		 */ public class MobEffect
/*		 */ {
/*		 */	 private int effectId;
/*		 */	 private int duration;
/*		 */	 private int amplification;
/*		 */ 
/*		 */	 public MobEffect(int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	23 */		 this.effectId = paramInt1;
/*	24 */		 this.duration = paramInt2;
/*	25 */		 this.amplification = paramInt3;
/*		 */	 }
/*		 */ 
/*		 */	 public MobEffect(MobEffect paramMobEffect) {
/*	29 */		 this.effectId = paramMobEffect.effectId;
/*	30 */		 this.duration = paramMobEffect.duration;
/*	31 */		 this.amplification = paramMobEffect.amplification;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(MobEffect paramMobEffect) {
/*	35 */		 if (this.effectId != paramMobEffect.effectId) {
/*	36 */			 System.err.println("This method should only be called for matching effects!");
/*		 */		 }
/*	38 */		 if (paramMobEffect.amplification > this.amplification) {
/*	39 */			 this.amplification = paramMobEffect.amplification;
/*	40 */			 this.duration = paramMobEffect.duration;
/*	41 */		 } else if ((paramMobEffect.amplification == this.amplification) && (this.duration < paramMobEffect.duration)) {
/*	42 */			 this.duration = paramMobEffect.duration;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public int getEffectId() {
/*	47 */		 return this.effectId;
/*		 */	 }
/*		 */ 
/*		 */	 public int getDuration() {
/*	51 */		 return this.duration;
/*		 */	 }
/*		 */ 
/*		 */	 public int getAmplifier() {
/*	55 */		 return this.amplification;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean tick(EntityLiving paramEntityLiving)
/*		 */	 {
/*	65 */		 if (this.duration > 0) {
/*	66 */			 if (MobEffectList.byId[this.effectId].a(this.duration, this.amplification)) {
/*	67 */				 b(paramEntityLiving);
/*		 */			 }
/*	69 */			 e();
/*		 */		 }
/*	71 */		 return this.duration > 0;
/*		 */	 }
/*		 */ 
/*		 */	 private int e() {
/*	75 */		 return --this.duration;
/*		 */	 }
/*		 */ 
/*		 */	 public void b(EntityLiving paramEntityLiving) {
/*	79 */		 if (this.duration > 0)
/*	80 */			 MobEffectList.byId[this.effectId].tick(paramEntityLiving, this.amplification);
/*		 */	 }
/*		 */ 
/*		 */	 public String d()
/*		 */	 {
/*	85 */		 return MobEffectList.byId[this.effectId].a();
/*		 */	 }
/*		 */ 
/*		 */	 public int hashCode()
/*		 */	 {
/*	90 */		 return this.effectId;
/*		 */	 }
/*		 */ 
/*		 */	 public String toString()
/*		 */	 {
/*	95 */		 String str = "";
/*	96 */		 if (getAmplifier() > 0)
/*	97 */			 str = d() + " x " + (getAmplifier() + 1) + ", Duration: " + getDuration();
/*		 */		 else {
/*	99 */			 str = d() + ", Duration: " + getDuration();
/*		 */		 }
/* 101 */		 if (MobEffectList.byId[this.effectId].i()) {
/* 102 */			 return "(" + str + ")";
/*		 */		 }
/* 104 */		 return str;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean equals(Object paramObject)
/*		 */	 {
/* 109 */		 if (!(paramObject instanceof MobEffect)) {
/* 110 */			 return false;
/*		 */		 }
/* 112 */		 MobEffect localMobEffect = (MobEffect)paramObject;
/* 113 */		 return (this.effectId == localMobEffect.effectId) && (this.amplification == localMobEffect.amplification) && (this.duration == localMobEffect.duration);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.MobEffect
 * JD-Core Version:		0.6.0
 */