/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class MathHelper
/*		 */ {
/*	19 */	 private static float[] a = new float[65536];
/*		 */ 
/*		 */	 public static final float sin(float paramFloat)
/*		 */	 {
/*	26 */		 return a[((int)(paramFloat * 10430.378F) & 0xFFFF)];
/*		 */	 }
/*		 */ 
/*		 */	 public static final float cos(float paramFloat) {
/*	30 */		 return a[((int)(paramFloat * 10430.378F + 16384.0F) & 0xFFFF)];
/*		 */	 }
/*		 */ 
/*		 */	 public static final float c(float paramFloat) {
/*	34 */		 return (float)Math.sqrt(paramFloat);
/*		 */	 }
/*		 */ 
/*		 */	 public static final float sqrt(double paramDouble) {
/*	38 */		 return (float)Math.sqrt(paramDouble);
/*		 */	 }
/*		 */ 
/*		 */	 public static int d(float paramFloat) {
/*	42 */		 int i = (int)paramFloat;
/*	43 */		 return paramFloat < i ? i - 1 : i;
/*		 */	 }
/*		 */ 
/*		 */	 public static int floor(double paramDouble)
/*		 */	 {
/*	51 */		 int i = (int)paramDouble;
/*	52 */		 return paramDouble < i ? i - 1 : i;
/*		 */	 }
/*		 */ 
/*		 */	 public static long d(double paramDouble) {
/*	56 */		 long l = ()paramDouble;
/*	57 */		 return paramDouble < l ? l - 1L : l;
/*		 */	 }
/*		 */ 
/*		 */	 public static float abs(float paramFloat)
/*		 */	 {
/*	65 */		 return paramFloat >= 0.0F ? paramFloat : -paramFloat;
/*		 */	 }
/*		 */ 
/*		 */	 public static int a(int paramInt) {
/*	69 */		 return paramInt >= 0 ? paramInt : -paramInt;
/*		 */	 }
/*		 */ 
/*		 */	 public static int f(float paramFloat) {
/*	73 */		 int i = (int)paramFloat;
/*	74 */		 return paramFloat > i ? i + 1 : i;
/*		 */	 }
/*		 */ 
/*		 */	 public static int f(double paramDouble) {
/*	78 */		 int i = (int)paramDouble;
/*	79 */		 return paramDouble > i ? i + 1 : i;
/*		 */	 }
/*		 */ 
/*		 */	 public static int a(int paramInt1, int paramInt2, int paramInt3) {
/*	83 */		 if (paramInt1 < paramInt2) {
/*	84 */			 return paramInt2;
/*		 */		 }
/*	86 */		 if (paramInt1 > paramInt3) {
/*	87 */			 return paramInt3;
/*		 */		 }
/*	89 */		 return paramInt1;
/*		 */	 }
/*		 */ 
/*		 */	 public static double a(double paramDouble1, double paramDouble2)
/*		 */	 {
/* 109 */		 if (paramDouble1 < 0.0D) paramDouble1 = -paramDouble1;
/* 110 */		 if (paramDouble2 < 0.0D) paramDouble2 = -paramDouble2;
/* 111 */		 return paramDouble1 > paramDouble2 ? paramDouble1 : paramDouble2;
/*		 */	 }
/*		 */ 
/*		 */	 public static int a(Random paramRandom, int paramInt1, int paramInt2)
/*		 */	 {
/* 133 */		 if (paramInt1 >= paramInt2) {
/* 134 */			 return paramInt1;
/*		 */		 }
/* 136 */		 return paramRandom.nextInt(paramInt2 - paramInt1 + 1) + paramInt1;
/*		 */	 }
/*		 */ 
/*		 */	 public static double a(long[] paramArrayOfLong) {
/* 140 */		 long l1 = 0L;
/*		 */ 
/* 142 */		 for (long l2 : paramArrayOfLong) {
/* 143 */			 l1 += l2;
/*		 */		 }
/*		 */ 
/* 146 */		 return l1 / paramArrayOfLong.length;
/*		 */	 }
/*		 */ 
/*		 */	 public static float g(float paramFloat) {
/* 150 */		 paramFloat %= 360.0F;
/* 151 */		 if (paramFloat >= 180.0F) paramFloat -= 360.0F;
/* 152 */		 if (paramFloat < -180.0F) paramFloat += 360.0F;
/* 153 */		 return paramFloat;
/*		 */	 }
/*		 */ 
/*		 */	 public static double g(double paramDouble) {
/* 157 */		 paramDouble %= 360.0D;
/* 158 */		 if (paramDouble >= 180.0D) paramDouble -= 360.0D;
/* 159 */		 if (paramDouble < -180.0D) paramDouble += 360.0D;
/* 160 */		 return paramDouble;
/*		 */	 }
/*		 */ 
/*		 */	 static
/*		 */	 {
/*	20 */		 for (int i = 0; i < 65536; i++)
/*	21 */			 a[i] = (float)Math.sin(i * 3.141592653589793D * 2.0D / 65536.0D);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.MathHelper
 * JD-Core Version:		0.6.0
 */