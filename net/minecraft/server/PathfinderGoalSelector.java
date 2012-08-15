/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ 
/*		 */ public class PathfinderGoalSelector
/*		 */ {
/*	22 */	 private List a = new ArrayList();
/*	23 */	 private List b = new ArrayList();
/*		 */	 private final MethodProfiler c;
/*	25 */	 private int d = 0;
/*	26 */	 private int e = 3;
/*		 */ 
/*		 */	 public PathfinderGoalSelector(MethodProfiler paramMethodProfiler) {
/*	29 */		 this.c = paramMethodProfiler;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(int paramInt, PathfinderGoal paramPathfinderGoal) {
/*	33 */		 this.a.add(new PathfinderGoalSelectorItem(this, paramInt, paramPathfinderGoal));
/*		 */	 }
/*		 */ 
/*		 */	 public void a() {
/*	37 */		 ArrayList localArrayList = new ArrayList();
/*		 */		 PathfinderGoalSelectorItem localPathfinderGoalSelectorItem;
/*	39 */		 if (this.d++ % this.e == 0) {
/*	40 */			 for (localIterator = this.a.iterator(); localIterator.hasNext(); ) { localPathfinderGoalSelectorItem = (PathfinderGoalSelectorItem)localIterator.next();
/*	41 */				 boolean bool = this.b.contains(localPathfinderGoalSelectorItem);
/*		 */ 
/*	43 */				 if (bool) {
/*	44 */					 if ((!b(localPathfinderGoalSelectorItem)) || (!a(localPathfinderGoalSelectorItem))) {
/*	45 */						 localPathfinderGoalSelectorItem.a.c();
/*	46 */						 this.b.remove(localPathfinderGoalSelectorItem);
/*		 */					 }
/*		 */				 }
/*		 */ 
/*	50 */				 if ((!b(localPathfinderGoalSelectorItem)) || (!localPathfinderGoalSelectorItem.a.a()))
/*		 */				 {
/*		 */					 continue;
/*		 */				 }
/*	54 */				 localArrayList.add(localPathfinderGoalSelectorItem);
/*	55 */				 this.b.add(localPathfinderGoalSelectorItem); }
/*		 */		 }
/*		 */		 else {
/*	58 */			 localIterator = this.b.iterator();
/*		 */ 
/*	60 */			 while (localIterator.hasNext()) {
/*	61 */				 localPathfinderGoalSelectorItem = (PathfinderGoalSelectorItem)localIterator.next();
/*	62 */				 if (!localPathfinderGoalSelectorItem.a.b()) {
/*	63 */					 localPathfinderGoalSelectorItem.a.c();
/*	64 */					 localIterator.remove();
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	69 */		 this.c.a("goalStart");
/*		 */ 
/*	71 */		 for (Iterator localIterator = localArrayList.iterator(); localIterator.hasNext(); ) { localPathfinderGoalSelectorItem = (PathfinderGoalSelectorItem)localIterator.next();
/*		 */ 
/*	73 */			 this.c.a(localPathfinderGoalSelectorItem.a.getClass().getSimpleName());
/*	74 */			 localPathfinderGoalSelectorItem.a.e();
/*	75 */			 this.c.b();
/*		 */		 }
/*	77 */		 this.c.b();
/*		 */ 
/*	79 */		 this.c.a("goalTick");
/*		 */ 
/*	81 */		 for (localIterator = this.b.iterator(); localIterator.hasNext(); ) { localPathfinderGoalSelectorItem = (PathfinderGoalSelectorItem)localIterator.next();
/*		 */ 
/*	83 */			 this.c.a(localPathfinderGoalSelectorItem.a.getClass().getSimpleName());
/*	84 */			 localPathfinderGoalSelectorItem.a.d();
/*	85 */			 this.c.b();
/*		 */		 }
/*	87 */		 this.c.b();
/*		 */	 }
/*		 */ 
/*		 */	 private boolean a(PathfinderGoalSelectorItem paramPathfinderGoalSelectorItem) {
/*	91 */		 this.c.a("canContinue");
/*	92 */		 boolean bool = paramPathfinderGoalSelectorItem.a.b();
/*	93 */		 this.c.b();
/*	94 */		 return bool;
/*		 */	 }
/*		 */ 
/*		 */	 private boolean b(PathfinderGoalSelectorItem paramPathfinderGoalSelectorItem)
/*		 */	 {
/* 102 */		 this.c.a("canUse");
/*		 */ 
/* 104 */		 for (PathfinderGoalSelectorItem localPathfinderGoalSelectorItem : this.a) {
/* 105 */			 if (localPathfinderGoalSelectorItem == paramPathfinderGoalSelectorItem)
/*		 */				 continue;
/* 107 */			 if (paramPathfinderGoalSelectorItem.b >= localPathfinderGoalSelectorItem.b) {
/* 108 */				 if ((this.b.contains(localPathfinderGoalSelectorItem)) && (!a(paramPathfinderGoalSelectorItem, localPathfinderGoalSelectorItem))) {
/* 109 */					 this.c.b();
/* 110 */					 return false;
/*		 */				 }
/* 112 */			 } else if ((this.b.contains(localPathfinderGoalSelectorItem)) && (!localPathfinderGoalSelectorItem.a.g())) {
/* 113 */				 this.c.b();
/* 114 */				 return false;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 118 */		 this.c.b();
/* 119 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 private boolean a(PathfinderGoalSelectorItem paramPathfinderGoalSelectorItem1, PathfinderGoalSelectorItem paramPathfinderGoalSelectorItem2) {
/* 123 */		 return (paramPathfinderGoalSelectorItem1.a.h() & paramPathfinderGoalSelectorItem2.a.h()) == 0;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PathfinderGoalSelector
 * JD-Core Version:		0.6.0
 */