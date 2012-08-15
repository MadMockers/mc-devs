/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.HashMap;
/*		 */ import java.util.HashSet;
/*		 */ import java.util.List;
/*		 */ import java.util.Map;
/*		 */ import java.util.Map.Entry;
/*		 */ import java.util.Set;
/*		 */ 
/*		 */ public class CommandHandler
/*		 */	 implements ICommandHandler
/*		 */ {
/*	 8 */	 private final Map a = new HashMap();
/*	 9 */	 private final Set b = new HashSet();
/*		 */ 
/*		 */	 public void a(ICommandListener paramICommandListener, String paramString) {
/*	12 */		 if (paramString.startsWith("/")) paramString = paramString.substring(1);
/*		 */ 
/*	14 */		 String[] arrayOfString = paramString.split(" ");
/*	15 */		 String str = arrayOfString[0];
/*		 */ 
/*	17 */		 arrayOfString = a(arrayOfString);
/*		 */ 
/*	19 */		 ICommand localICommand = (ICommand)this.a.get(str);
/*		 */		 try
/*		 */		 {
/*	22 */			 if (localICommand == null) {
/*	23 */				 throw new ExceptionUnknownCommand();
/*		 */			 }
/*	25 */			 if (localICommand.b(paramICommandListener))
/*	26 */				 localICommand.b(paramICommandListener, arrayOfString);
/*		 */			 else
/*	28 */				 paramICommandListener.sendMessage("§cYou do not have permission to use this command.");
/*		 */		 }
/*		 */		 catch (ExceptionUsage localExceptionUsage)
/*		 */		 {
/*	32 */			 paramICommandListener.sendMessage("§c" + paramICommandListener.a("commands.generic.usage", new Object[] { paramICommandListener.a(localExceptionUsage.getMessage(), localExceptionUsage.a()) }));
/*		 */		 } catch (CommandException localCommandException) {
/*	34 */			 paramICommandListener.sendMessage("§c" + paramICommandListener.a(localCommandException.getMessage(), localCommandException.a()));
/*		 */		 } catch (Throwable localThrowable) {
/*	36 */			 paramICommandListener.sendMessage("§c" + paramICommandListener.a("commands.generic.exception", new Object[0]));
/*	37 */			 localThrowable.printStackTrace();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public ICommand a(ICommand paramICommand) {
/*	42 */		 List localList = paramICommand.a();
/*		 */ 
/*	44 */		 this.a.put(paramICommand.b(), paramICommand);
/*	45 */		 this.b.add(paramICommand);
/*		 */ 
/*	47 */		 if (localList != null) {
/*	48 */			 for (String str : localList) {
/*	49 */				 ICommand localICommand = (ICommand)this.a.get(str);
/*		 */ 
/*	51 */				 if ((localICommand == null) || (!localICommand.b().equals(str))) {
/*	52 */					 this.a.put(str, paramICommand);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	57 */		 return paramICommand;
/*		 */	 }
/*		 */ 
/*		 */	 private static String[] a(String[] paramArrayOfString) {
/*	61 */		 String[] arrayOfString = new String[paramArrayOfString.length - 1];
/*		 */ 
/*	63 */		 for (int i = 1; i < paramArrayOfString.length; i++) {
/*	64 */			 arrayOfString[(i - 1)] = paramArrayOfString[i];
/*		 */		 }
/*		 */ 
/*	67 */		 return arrayOfString;
/*		 */	 }
/*		 */ 
/*		 */	 public List b(ICommandListener paramICommandListener, String paramString) {
/*	71 */		 String[] arrayOfString = paramString.split(" ", -1);
/*	72 */		 String str = arrayOfString[0];
/*		 */		 Object localObject;
/*	74 */		 if (arrayOfString.length == 1)
/*		 */		 {
/*	76 */			 localObject = new ArrayList();
/*		 */ 
/*	78 */			 for (Map.Entry localEntry : this.a.entrySet()) {
/*	79 */				 if ((CommandAbstract.a(str, (String)localEntry.getKey())) && (((ICommand)localEntry.getValue()).b(paramICommandListener))) {
/*	80 */					 ((List)localObject).add(localEntry.getKey());
/*		 */				 }
/*		 */			 }
/*		 */ 
/*	84 */			 return localObject;
/*	85 */		 }if (arrayOfString.length > 1)
/*		 */		 {
/*	88 */			 localObject = (ICommand)this.a.get(str);
/*		 */ 
/*	90 */			 if (localObject != null) {
/*	91 */				 return ((ICommand)localObject).a(paramICommandListener, a(arrayOfString));
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	95 */		 return (List)null;
/*		 */	 }
/*		 */ 
/*		 */	 public List a(ICommandListener paramICommandListener) {
/*	99 */		 ArrayList localArrayList = new ArrayList();
/*		 */ 
/* 101 */		 for (ICommand localICommand : this.b) {
/* 102 */			 if (localICommand.b(paramICommandListener)) {
/* 103 */				 localArrayList.add(localICommand);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 107 */		 return localArrayList;
/*		 */	 }
/*		 */ 
/*		 */	 public Map a() {
/* 111 */		 return this.a;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CommandHandler
 * JD-Core Version:		0.6.0
 */