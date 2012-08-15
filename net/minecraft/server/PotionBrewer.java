/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.Collection;
/*		 */ import java.util.HashMap;
/*		 */ import java.util.List;
/*		 */ 
/*		 */ public class PotionBrewer
/*		 */ {
/*		 */	 public static final String a;
/*		 */	 public static final String b;
/*		 */	 public static final String c;
/*		 */	 public static final String d;
/*		 */	 public static final String e;
/*		 */	 public static final String f;
/*		 */	 public static final String g;
/*		 */	 public static final String h;
/*		 */	 public static final String i;
/*		 */	 public static final String j;
/*		 */	 public static final String k;
/*	36 */	 private static final HashMap effectDurations = new HashMap();
/*	37 */	 private static final HashMap effectAmplifiers = new HashMap();
/*		 */	 private static final HashMap n;
/*		 */	 private static final String[] appearances;
/*		 */ 
/*		 */	 public static boolean a(int paramInt1, int paramInt2)
/*		 */	 {
/* 156 */		 return (paramInt1 & 1 << paramInt2) != 0;
/*		 */	 }
/*		 */ 
/*		 */	 private static int c(int paramInt1, int paramInt2) {
/* 160 */		 return a(paramInt1, paramInt2) ? 1 : 0;
/*		 */	 }
/*		 */ 
/*		 */	 private static int d(int paramInt1, int paramInt2) {
/* 164 */		 return a(paramInt1, paramInt2) ? 0 : 1;
/*		 */	 }
/*		 */ 
/*		 */	 public static int a(Collection paramCollection)
/*		 */	 {
/* 173 */		 int m = 3694022;
/*		 */ 
/* 175 */		 if ((paramCollection == null) || (paramCollection.isEmpty())) {
/* 176 */			 return m;
/*		 */		 }
/*		 */ 
/* 179 */		 float f1 = 0.0F;
/* 180 */		 float f2 = 0.0F;
/* 181 */		 float f3 = 0.0F;
/* 182 */		 float f4 = 0.0F;
/*		 */ 
/* 184 */		 for (MobEffect localMobEffect : paramCollection) {
/* 185 */			 int i1 = MobEffectList.byId[localMobEffect.getEffectId()].j();
/*		 */ 
/* 187 */			 for (int i2 = 0; i2 <= localMobEffect.getAmplifier(); i2++) {
/* 188 */				 f1 += (i1 >> 16 & 0xFF) / 255.0F;
/* 189 */				 f2 += (i1 >> 8 & 0xFF) / 255.0F;
/* 190 */				 f3 += (i1 >> 0 & 0xFF) / 255.0F;
/* 191 */				 f4 += 1.0F;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 195 */		 f1 = f1 / f4 * 255.0F;
/* 196 */		 f2 = f2 / f4 * 255.0F;
/* 197 */		 f3 = f3 / f4 * 255.0F;
/*		 */ 
/* 199 */		 return (int)f1 << 16 | (int)f2 << 8 | (int)f3;
/*		 */	 }
/*		 */ 
/*		 */	 private static int a(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/* 270 */		 int m = 0;
/* 271 */		 if (paramBoolean1)
/* 272 */			 m = d(paramInt4, paramInt2);
/* 273 */		 else if (paramInt1 != -1) {
/* 274 */			 if ((paramInt1 == 0) && (h(paramInt4) == paramInt2))
/* 275 */				 m = 1;
/* 276 */			 else if ((paramInt1 == 1) && (h(paramInt4) > paramInt2))
/* 277 */				 m = 1;
/* 278 */			 else if ((paramInt1 == 2) && (h(paramInt4) < paramInt2))
/* 279 */				 m = 1;
/*		 */		 }
/*		 */		 else {
/* 282 */			 m = c(paramInt4, paramInt2);
/*		 */		 }
/* 284 */		 if (paramBoolean2) {
/* 285 */			 m *= paramInt3;
/*		 */		 }
/* 287 */		 if (paramBoolean3) {
/* 288 */			 m *= -1;
/*		 */		 }
/* 290 */		 return m;
/*		 */	 }
/*		 */ 
/*		 */	 private static int h(int paramInt) {
/* 294 */		 int m = 0;
/* 295 */		 for (; paramInt > 0; m++) {
/* 296 */			 paramInt &= paramInt - 1;
/*		 */		 }
/* 298 */		 return m;
/*		 */	 }
/*		 */ 
/*		 */	 private static int a(String paramString, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 303 */		 if ((paramInt1 >= paramString.length()) || (paramInt2 < 0) || (paramInt1 >= paramInt2)) {
/* 304 */			 return 0;
/*		 */		 }
/*		 */ 
/* 308 */		 int m = paramString.indexOf('|', paramInt1);
/* 309 */		 if ((m >= 0) && (m < paramInt2)) {
/* 310 */			 i1 = a(paramString, paramInt1, m - 1, paramInt3);
/* 311 */			 if (i1 > 0) {
/* 312 */				 return i1;
/*		 */			 }
/*		 */ 
/* 315 */			 i2 = a(paramString, m + 1, paramInt2, paramInt3);
/* 316 */			 if (i2 > 0) {
/* 317 */				 return i2;
/*		 */			 }
/* 319 */			 return 0;
/*		 */		 }
/*		 */ 
/* 322 */		 int i1 = paramString.indexOf('&', paramInt1);
/* 323 */		 if ((i1 >= 0) && (i1 < paramInt2)) {
/* 324 */			 i2 = a(paramString, paramInt1, i1 - 1, paramInt3);
/* 325 */			 if (i2 <= 0) {
/* 326 */				 return 0;
/*		 */			 }
/*		 */ 
/* 329 */			 i3 = a(paramString, i1 + 1, paramInt2, paramInt3);
/* 330 */			 if (i3 <= 0) {
/* 331 */				 return 0;
/*		 */			 }
/*		 */ 
/* 334 */			 if (i2 > i3) {
/* 335 */				 return i2;
/*		 */			 }
/* 337 */			 return i3;
/*		 */		 }
/*		 */ 
/* 340 */		 int i2 = 0;
/* 341 */		 int i3 = 0;
/* 342 */		 int i4 = 0;
/* 343 */		 boolean bool2 = false;
/* 344 */		 boolean bool3 = false;
/* 345 */		 int i5 = -1;
/* 346 */		 int i6 = 0;
/* 347 */		 int i7 = 0;
/* 348 */		 int i8 = 0;
/*		 */		 boolean bool1;
/* 349 */		 for (int i9 = paramInt1; i9 < paramInt2; i9++)
/*		 */		 {
/* 351 */			 int i10 = paramString.charAt(i9);
/* 352 */			 if ((i10 >= 48) && (i10 <= 57)) {
/* 353 */				 if (i2 != 0) {
/* 354 */					 i7 = i10 - 48;
/* 355 */					 i3 = 1;
/*		 */				 } else {
/* 357 */					 i6 *= 10;
/* 358 */					 i6 += i10 - 48;
/* 359 */					 i4 = 1;
/*		 */				 }
/* 361 */			 } else if (i10 == 42) {
/* 362 */				 i2 = 1;
/* 363 */			 } else if (i10 == 33) {
/* 364 */				 if (i4 != 0) {
/* 365 */					 i8 += a(bool2, i3, bool3, i5, i6, i7, paramInt3);
/* 366 */					 i4 = bool1 = i2 = bool3 = bool2 = 0;
/* 367 */					 i6 = i7 = 0;
/* 368 */					 i5 = -1;
/*		 */				 }
/*		 */ 
/* 371 */				 bool2 = true;
/* 372 */			 } else if (i10 == 45) {
/* 373 */				 if (i4 != 0) {
/* 374 */					 i8 += a(bool2, bool1, bool3, i5, i6, i7, paramInt3);
/* 375 */					 i4 = bool1 = i2 = bool3 = bool2 = 0;
/* 376 */					 i6 = i7 = 0;
/* 377 */					 i5 = -1;
/*		 */				 }
/*		 */ 
/* 380 */				 bool3 = true;
/* 381 */			 } else if ((i10 == 61) || (i10 == 60) || (i10 == 62)) {
/* 382 */				 if (i4 != 0) {
/* 383 */					 i8 += a(bool2, bool1, bool3, i5, i6, i7, paramInt3);
/* 384 */					 i4 = bool1 = i2 = bool3 = bool2 = 0;
/* 385 */					 i6 = i7 = 0;
/* 386 */					 i5 = -1;
/*		 */				 }
/*		 */ 
/* 389 */				 if (i10 == 61)
/* 390 */					 i5 = 0;
/* 391 */				 else if (i10 == 60)
/* 392 */					 i5 = 2;
/* 393 */				 else if (i10 == 62)
/* 394 */					 i5 = 1;
/*		 */			 } else {
/* 396 */				 if ((i10 != 43) || 
/* 397 */					 (i4 == 0)) continue;
/* 398 */				 i8 += a(bool2, bool1, bool3, i5, i6, i7, paramInt3);
/* 399 */				 i4 = bool1 = i2 = bool3 = bool2 = 0;
/* 400 */				 i6 = i7 = 0;
/* 401 */				 i5 = -1;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 405 */		 if (i4 != 0) {
/* 406 */			 i8 += a(bool2, bool1, bool3, i5, i6, i7, paramInt3);
/*		 */		 }
/*		 */ 
/* 409 */		 return i8;
/*		 */	 }
/*		 */ 
/*		 */	 public static List getEffects(int paramInt, boolean paramBoolean)
/*		 */	 {
/* 414 */		 ArrayList localArrayList = null;
/*		 */ 
/* 416 */		 for (MobEffectList localMobEffectList : MobEffectList.byId) {
/* 417 */			 if ((localMobEffectList == null) || ((localMobEffectList.i()) && (!paramBoolean))) {
/*		 */				 continue;
/*		 */			 }
/* 420 */			 String str1 = (String)effectDurations.get(Integer.valueOf(localMobEffectList.getId()));
/* 421 */			 if (str1 == null)
/*		 */			 {
/*		 */				 continue;
/*		 */			 }
/* 425 */			 int i2 = a(str1, 0, str1.length(), paramInt);
/* 426 */			 if (i2 > 0) {
/* 427 */				 int i3 = 0;
/* 428 */				 String str2 = (String)effectAmplifiers.get(Integer.valueOf(localMobEffectList.getId()));
/* 429 */				 if (str2 != null) {
/* 430 */					 i3 = a(str2, 0, str2.length(), paramInt);
/* 431 */					 if (i3 < 0) {
/* 432 */						 i3 = 0;
/*		 */					 }
/*		 */				 }
/*		 */ 
/* 436 */				 if (localMobEffectList.isInstant()) {
/* 437 */					 i2 = 1;
/*		 */				 }
/*		 */				 else {
/* 440 */					 i2 = 1200 * (i2 * 3 + (i2 - 1) * 2);
/* 441 */					 i2 >>= i3;
/* 442 */					 i2 = (int)Math.round(i2 * localMobEffectList.getDurationModifier());
/*		 */ 
/* 444 */					 if ((paramInt & 0x4000) != 0) {
/* 445 */						 i2 = (int)Math.round(i2 * 0.75D + 0.5D);
/*		 */					 }
/*		 */				 }
/*		 */ 
/* 449 */				 if (localArrayList == null) {
/* 450 */					 localArrayList = new ArrayList();
/*		 */				 }
/* 452 */				 localArrayList.add(new MobEffect(localMobEffectList.getId(), i2, i3));
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 456 */		 return localArrayList;
/*		 */	 }
/*		 */ 
/*		 */	 private static int a(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
/*		 */	 {
/* 542 */		 if (paramBoolean3) {
/* 543 */			 if (!a(paramInt1, paramInt2))
/* 544 */				 return 0;
/*		 */		 }
/* 546 */		 else if (paramBoolean1)
/* 547 */			 paramInt1 &= (1 << paramInt2 ^ 0xFFFFFFFF);
/* 548 */		 else if (paramBoolean2) {
/* 549 */			 if ((paramInt1 & 1 << paramInt2) == 0)
/* 550 */				 paramInt1 |= 1 << paramInt2;
/*		 */			 else
/* 552 */				 paramInt1 &= (1 << paramInt2 ^ 0xFFFFFFFF);
/*		 */		 }
/*		 */		 else {
/* 555 */			 paramInt1 |= 1 << paramInt2;
/*		 */		 }
/* 557 */		 return paramInt1;
/*		 */	 }
/*		 */ 
/*		 */	 public static int a(int paramInt, String paramString)
/*		 */	 {
/* 562 */		 int m = 0;
/* 563 */		 int i1 = paramString.length();
/*		 */ 
/* 565 */		 int i2 = 0;
/* 566 */		 boolean bool1 = false;
/* 567 */		 boolean bool2 = false;
/* 568 */		 boolean bool3 = false;
/* 569 */		 int i3 = 0;
/* 570 */		 for (int i4 = m; i4 < i1; i4++)
/*		 */		 {
/* 572 */			 int i5 = paramString.charAt(i4);
/* 573 */			 if ((i5 >= 48) && (i5 <= 57)) {
/* 574 */				 i3 *= 10;
/* 575 */				 i3 += i5 - 48;
/* 576 */				 i2 = 1;
/* 577 */			 } else if (i5 == 33) {
/* 578 */				 if (i2 != 0) {
/* 579 */					 paramInt = a(paramInt, i3, bool2, bool1, bool3);
/* 580 */					 i2 = bool2 = bool1 = bool3 = 0;
/* 581 */					 i3 = 0;
/*		 */				 }
/*		 */ 
/* 584 */				 bool1 = true;
/* 585 */			 } else if (i5 == 45) {
/* 586 */				 if (i2 != 0) {
/* 587 */					 paramInt = a(paramInt, i3, bool2, bool1, bool3);
/* 588 */					 i2 = bool2 = bool1 = bool3 = 0;
/* 589 */					 i3 = 0;
/*		 */				 }
/*		 */ 
/* 592 */				 bool2 = true;
/* 593 */			 } else if (i5 == 43) {
/* 594 */				 if (i2 != 0) {
/* 595 */					 paramInt = a(paramInt, i3, bool2, bool1, bool3);
/* 596 */					 i2 = bool2 = bool1 = bool3 = 0;
/* 597 */					 i3 = 0;
/*		 */				 }
/* 599 */			 } else if (i5 == 38) {
/* 600 */				 if (i2 != 0) {
/* 601 */					 paramInt = a(paramInt, i3, bool2, bool1, bool3);
/* 602 */					 i2 = bool2 = bool1 = bool3 = 0;
/* 603 */					 i3 = 0;
/*		 */				 }
/* 605 */				 bool3 = true;
/*		 */			 }
/*		 */		 }
/* 608 */		 if (i2 != 0) {
/* 609 */			 paramInt = a(paramInt, i3, bool2, bool1, bool3);
/*		 */		 }
/*		 */ 
/* 612 */		 return paramInt & 0x7FFF;
/*		 */	 }
/*		 */ 
/*		 */	 static
/*		 */	 {
/*	43 */		 a = null;
/*		 */ 
/*	64 */		 c = "+0-1-2-3&4-4+13";
/*	65 */		 effectDurations.put(Integer.valueOf(MobEffectList.REGENERATION.getId()), "0 & !1 & !2 & !3 & 0+6");
/*		 */ 
/*	67 */		 b = "-0+1-2-3&4-4+13";
/*	68 */		 effectDurations.put(Integer.valueOf(MobEffectList.FASTER_MOVEMENT.getId()), "!0 & 1 & !2 & !3 & 1+6");
/*		 */ 
/*	70 */		 h = "+0+1-2-3&4-4+13";
/*	71 */		 effectDurations.put(Integer.valueOf(MobEffectList.FIRE_RESISTANCE.getId()), "0 & 1 & !2 & !3 & 0+6");
/*		 */ 
/*	73 */		 f = "+0-1+2-3&4-4+13";
/*	74 */		 effectDurations.put(Integer.valueOf(MobEffectList.HEAL.getId()), "0 & !1 & 2 & !3");
/*		 */ 
/*	76 */		 d = "-0-1+2-3&4-4+13";
/*	77 */		 effectDurations.put(Integer.valueOf(MobEffectList.POISON.getId()), "!0 & !1 & 2 & !3 & 2+6");
/*		 */ 
/*	79 */		 e = "-0+3-4+13";
/*	80 */		 effectDurations.put(Integer.valueOf(MobEffectList.WEAKNESS.getId()), "!0 & !1 & !2 & 3 & 3+6");
/*	81 */		 effectDurations.put(Integer.valueOf(MobEffectList.HARM.getId()), "!0 & !1 & 2 & 3");
/*	82 */		 effectDurations.put(Integer.valueOf(MobEffectList.SLOWER_MOVEMENT.getId()), "!0 & 1 & !2 & 3 & 3+6");
/*		 */ 
/*	84 */		 g = "+0-1-2+3&4-4+13";
/*	85 */		 effectDurations.put(Integer.valueOf(MobEffectList.INCREASE_DAMAGE.getId()), "0 & !1 & !2 & 3 & 3+6");
/*		 */ 
/*	88 */		 j = "+5-6-7";
/*	89 */		 effectAmplifiers.put(Integer.valueOf(MobEffectList.FASTER_MOVEMENT.getId()), "5");
/*	90 */		 effectAmplifiers.put(Integer.valueOf(MobEffectList.FASTER_DIG.getId()), "5");
/*	91 */		 effectAmplifiers.put(Integer.valueOf(MobEffectList.INCREASE_DAMAGE.getId()), "5");
/*	92 */		 effectAmplifiers.put(Integer.valueOf(MobEffectList.REGENERATION.getId()), "5");
/*	93 */		 effectAmplifiers.put(Integer.valueOf(MobEffectList.HARM.getId()), "5");
/*	94 */		 effectAmplifiers.put(Integer.valueOf(MobEffectList.HEAL.getId()), "5");
/*	95 */		 effectAmplifiers.put(Integer.valueOf(MobEffectList.RESISTANCE.getId()), "5");
/*	96 */		 effectAmplifiers.put(Integer.valueOf(MobEffectList.POISON.getId()), "5");
/*		 */ 
/*	99 */		 i = "-5+6-7";
/*		 */ 
/* 103 */		 k = "+14&13-13";
/*		 */ 
/* 202 */		 n = new HashMap();
/*		 */ 
/* 223 */		 appearances = new String[] { "potion.prefix.mundane", "potion.prefix.uninteresting", "potion.prefix.bland", "potion.prefix.clear", "potion.prefix.milky", "potion.prefix.diffuse", "potion.prefix.artless", "potion.prefix.thin", "potion.prefix.awkward", "potion.prefix.flat", "potion.prefix.bulky", "potion.prefix.bungling", "potion.prefix.buttered", "potion.prefix.smooth", "potion.prefix.suave", "potion.prefix.debonair", "potion.prefix.thick", "potion.prefix.elegant", "potion.prefix.fancy", "potion.prefix.charming", "potion.prefix.dashing", "potion.prefix.refined", "potion.prefix.cordial", "potion.prefix.sparkling", "potion.prefix.potent", "potion.prefix.foul", "potion.prefix.odorless", "potion.prefix.rank", "potion.prefix.harsh", "potion.prefix.acrid", "potion.prefix.gross", "potion.prefix.stinky" };
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PotionBrewer
 * JD-Core Version:		0.6.0
 */