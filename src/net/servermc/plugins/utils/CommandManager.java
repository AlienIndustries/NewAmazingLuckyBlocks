package net.servermc.plugins.utils;

import net.servermc.plugins.utils.WorldList;
import net.servermc.plugins.utils.RandomBlocks;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import net.servermc.plugins.AmazingLuckyBlocks;
import net.servermc.plugins.Listeners.Database;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.enchantments.Enchantment;

public class CommandManager
  implements CommandExecutor
{
  private final AmazingLuckyBlocks plugin;
  
  public CommandManager(AmazingLuckyBlocks instance)
  {
    this.plugin = instance;
  }
  
  public String color(String str)
  {
    return ChatColor.translateAlternateColorCodes('&', str);
  }
  
  public String intColorSet(int Uses){
    String intColor;  
    if(Uses >= 10){
      intColor = "&a";  
    }else if(Uses < 10 || Uses >= 5){
      intColor = "&e";    
    }else if(Uses < 5 || Uses >= 1){
      intColor = "&6";    
    }else{
      intColor = "&4";    
    }
    return intColor;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (cmd.getName().equalsIgnoreCase("alb"))
    {
        if(args.length == 0){
            sender.sendMessage(plugin.name + " " + ChatColor.GREEN + plugin.version);
            sender.sendMessage(color(LangLoader.LangCfg.getString("HelpMessage")));
        }else if(args.length == 1 && args[0].equals("help")){
            
            String line1 = LangLoader.LangCfg.getString("Helpmenu.line1");
            String line2 = LangLoader.LangCfg.getString("Helpmenu.line2");
            String line3 = LangLoader.LangCfg.getString("Helpmenu.line3");
            String line4 = LangLoader.LangCfg.getString("Helpmenu.line4");
            String line5 = LangLoader.LangCfg.getString("Helpmenu.line5");
            String line6 = LangLoader.LangCfg.getString("Helpmenu.line6");
            String line7 = LangLoader.LangCfg.getString("Helpmenu.line7");
            
            List<String> helpmenu = LangLoader.LangCfg.getStringList("Helpmenu");
            helpmenu.add(line1);
            helpmenu.add(line2);
            helpmenu.add(line3);
            helpmenu.add(line4);
            helpmenu.add(line5);
            helpmenu.add(line6);
            helpmenu.add(line7);
            for(int i=0;i<helpmenu.size();i++){
			String text = helpmenu.get(i);
			sender.sendMessage(color(text.replaceAll("%prefix%", AmazingLuckyBlocks.getInstance().prefix)));
            }
        }else if(args.length == 2 && args[0].equals("help")){
            int help_n;
            try{
                help_n = Integer.parseInt(args[1]);
            }catch(IllegalArgumentException e){
                help_n = 1;
            }
            switch(help_n){
                case 1:
                    String line1 = LangLoader.LangCfg.getString("Helpmenu.line1");
                    String line2 = LangLoader.LangCfg.getString("Helpmenu.line2");
                    String line3 = LangLoader.LangCfg.getString("Helpmenu.line3");
                    String line4 = LangLoader.LangCfg.getString("Helpmenu.line4");
                    String line5 = LangLoader.LangCfg.getString("Helpmenu.line5");
                    String line6 = LangLoader.LangCfg.getString("Helpmenu.line6");
                    String line7 = LangLoader.LangCfg.getString("Helpmenu.line7");

                    List<String> helpmenu = LangLoader.LangCfg.getStringList("Helpmenu");
                    helpmenu.add(line1);
                    helpmenu.add(line2);
                    helpmenu.add(line3);
                    helpmenu.add(line4);
                    helpmenu.add(line5);
                    helpmenu.add(line6);
                    helpmenu.add(line7);
                    for(int i=0;i<helpmenu.size();i++){
                            String text = helpmenu.get(i);
                            sender.sendMessage(color(text.replaceAll("%prefix%", AmazingLuckyBlocks.getInstance().prefix)));
                    }
                    break;
                case 2:
                    line1 = LangLoader.LangCfg.getString("Helpmenu2.line1");
                    line2 = LangLoader.LangCfg.getString("Helpmenu2.line2");
                    line3 = LangLoader.LangCfg.getString("Helpmenu2.line3");
                    line4 = LangLoader.LangCfg.getString("Helpmenu2.line4");
                    line5 = LangLoader.LangCfg.getString("Helpmenu2.line5");
                    line6 = LangLoader.LangCfg.getString("Helpmenu2.line6");
                    line7 = LangLoader.LangCfg.getString("Helpmenu2.line7");

                    List<String> helpmenu2 = LangLoader.LangCfg.getStringList("Helpmenu");
                    helpmenu2.add(line1);
                    helpmenu2.add(line2);
                    helpmenu2.add(line3);
                    helpmenu2.add(line4);
                    helpmenu2.add(line5);
                    helpmenu2.add(line6);
                    helpmenu2.add(line7);
                    for(int i=0;i<helpmenu2.size();i++){
                        String text = helpmenu2.get(i);
                        sender.sendMessage(color(text.replaceAll("%prefix%", AmazingLuckyBlocks.getInstance().prefix)));
                    }
                    break;
                default:
                    sender.sendMessage(color(LangLoader.LangCfg.getString("UnknownCommand")));
            }
        }else if((args.length == 1) && (args[0].equalsIgnoreCase("give"))){
            String unknowncommand = color(LangLoader.LangCfg.getString("UnknownCommand"));
            sender.sendMessage(unknowncommand);
            
        }else if ((args.length <= 3) && (args[0].equalsIgnoreCase("give")) && (args[1].equalsIgnoreCase("wands"))){
            String wandsperm = CLBManager.getManager().getConfig().getString("Commands.Give.Wands");
            if (sender.hasPermission(wandsperm)){
//                if(!(sender instanceof Player)){
//                            Bukkit.getConsoleSender().sendMessage(plugin.name+ChatColor.RED+" You can't send this command from the console");
//                            return false;
//                }else{
                    Player p;
                    if(args.length == 3){
                       p = Bukkit.getServer().getPlayer(args[2]);
                       if(p == null){
                           sender.sendMessage(color("&cPlayer " + args[2] + "&c is offline"));
                           return false;
                       }
                    }else{
                        if(!(sender instanceof Player)){
                            Bukkit.getConsoleSender().sendMessage(plugin.name+ChatColor.RED+" You can't send this command from the console");
                            return false;
                        }
                        p = (Player) sender;
                    }
                    

                    List<String> loreList = new ArrayList();
                    
                    //Regen wand

                    ItemStack stack6 = new ItemStack(Material.valueOf("RECORD_8"));
                    ItemMeta meta2 = stack6.getItemMeta();
                    meta2.setDisplayName(color(LangLoader.LangCfg.getString("Wands.Regen.name")));
                    if(CLBManager.getManager().getConfig().getBoolean("Wands.Regen.limited-uses.enable")){
                       int uses = CLBManager.getManager().getConfig().getInt("Wands.Regen.limited-uses.uses");
                       loreList.clear();
                       loreList.add("Uses left:");
                       loreList.add(String.valueOf(uses));
                       meta2.setLore(loreList);
                    }
                    stack6.setItemMeta(meta2);

                    //Invisibility wand

                    ItemStack stack7 = new ItemStack(Material.valueOf("RECORD_7"));
                    ItemMeta meta3 = stack7.getItemMeta();
                    meta3.setDisplayName(color(LangLoader.LangCfg.getString("Wands.Invisibility.name")));
                    if(CLBManager.getManager().getConfig().getBoolean("Wands.Invisibility.limited-uses.enable")){
                       int uses = CLBManager.getManager().getConfig().getInt("Wands.Invisibility.limited-uses.uses");
                       loreList.clear();
                       loreList.add("Uses left:");
                       loreList.add(String.valueOf(uses));
                       meta3.setLore(loreList);
                    }
                    stack7.setItemMeta(meta3);

                    //Tnt wand

                    ItemStack stack21 = new ItemStack(Material.valueOf("RECORD_3"));
                    ItemMeta meta4 = stack21.getItemMeta();
                    meta4.setDisplayName(color(LangLoader.LangCfg.getString("Wands.TNT.name")));
                    if(CLBManager.getManager().getConfig().getBoolean("Wands.TNT.limited-uses.enable")){
                       int uses = CLBManager.getManager().getConfig().getInt("Wands.TNT.limited-uses.uses");
                       loreList.clear();
                       loreList.add("Uses left:");
                       loreList.add(String.valueOf(uses));
                       meta4.setLore(loreList);
                    }
                    stack21.setItemMeta(meta4);

                    //Slime wand

                    ItemStack stack22 = new ItemStack(Material.valueOf("RECORD_4"));
                    ItemMeta meta5 = stack22.getItemMeta();
                    meta5.setDisplayName(color(LangLoader.LangCfg.getString("Wands.Slime.name")));
                    if(CLBManager.getManager().getConfig().getBoolean("Wands.Slime.limited-uses.enable")){
                       int uses = CLBManager.getManager().getConfig().getInt("Wands.Slime.limited-uses.uses");
                       loreList.clear();
                       loreList.add("Uses left:");
                       loreList.add(String.valueOf(uses));
                       meta5.setLore(loreList);
                    }
                    stack22.setItemMeta(meta5);

                    //Dragon-breath wand

                    ItemStack stack23 = new ItemStack(Material.valueOf("RECORD_6"));
                    ItemMeta meta6 = stack23.getItemMeta();
                    meta6.setDisplayName(color(LangLoader.LangCfg.getString("Wands.Dragon-breath.name")));
                    if(CLBManager.getManager().getConfig().getBoolean("Wands.Dragon-breath.limited-uses.enable")){
                       int uses = CLBManager.getManager().getConfig().getInt("Wands.Dragon-breath.limited-uses.uses");
                       loreList.clear();
                       loreList.add("Uses left:");
                       loreList.add(String.valueOf(uses));
                       meta6.setLore(loreList);
                    }
                    stack23.setItemMeta(meta6);

                    //Lightning wand

                    ItemStack stack24 = new ItemStack(Material.valueOf("RECORD_5"));
                    ItemMeta meta7 = stack24.getItemMeta();
                    meta7.setDisplayName(color(LangLoader.LangCfg.getString("Wands.Lightning.name")));
                    if(CLBManager.getManager().getConfig().getBoolean("Wands.Lightning.limited-uses.enable")){
                       int uses = CLBManager.getManager().getConfig().getInt("Wands.Lightning.limited-uses.uses");
                       loreList.clear();
                       loreList.add("Uses left:");
                       loreList.add(String.valueOf(uses));
                       meta7.setLore(loreList);
                    }
                    stack24.setItemMeta(meta7);

                    //Shield wand

                    ItemStack stack29 = new ItemStack(Material.valueOf("RECORD_9"));
                    ItemMeta meta8 = stack29.getItemMeta();
                    meta8.setDisplayName(color(LangLoader.LangCfg.getString("Wands.Shield.name")));
                    if(CLBManager.getManager().getConfig().getBoolean("Wands.Shield.limited-uses.enable")){
                       int uses = CLBManager.getManager().getConfig().getInt("Wands.Shield.limited-uses.uses");
                       loreList.clear();
                       loreList.add("Uses left:");
                       loreList.add(String.valueOf(uses));
                       meta8.setLore(loreList);
                    }
                    stack29.setItemMeta(meta8);

                    p.getInventory().addItem(stack6);
                    p.getInventory().addItem(stack7);
                    p.getInventory().addItem(stack21);
                    p.getInventory().addItem(stack22);
                    p.getInventory().addItem(stack23);
                    p.getInventory().addItem(stack24);
                    p.getInventory().addItem(stack29);

                    String loadwands = color(LangLoader.LangCfg.getString("LoadingWands"));
                    sender.sendMessage (loadwands);
//                }
            }else{
              String noperm = color(LangLoader.LangCfg.getString("NoPermission"));
              sender.sendMessage (noperm);
            }
        }else if ((args[0].equalsIgnoreCase("give")) && (args[1].equalsIgnoreCase("objects"))){
            String objectsperm = CLBManager.getManager().getConfig().getString("Commands.Give.Objects");
            if (sender.hasPermission(objectsperm)){
//                if(!(sender instanceof Player)) {
//                    Bukkit.getConsoleSender().sendMessage(plugin.name+ChatColor.RED+" You can't send this command from the console");
//                    return false;
//                }else{
                    int amount = 1;
                    Player p = null;
                    if(args.length == 2){
                        if(!(sender instanceof Player)){
                            Bukkit.getConsoleSender().sendMessage(plugin.name+ChatColor.RED+" You can't send this command from the console");
                            return false;
                        }
                        p = (Player) sender;
                        amount = 1; 
                    }
                    if(args.length == 4){
                        try{
                            amount = Integer.parseInt(args[2]);
                        }catch(IllegalArgumentException e){
                            amount = 1;
                        }
                        
                        p = Bukkit.getServer().getPlayer(args[3]);
                        if(p == null){
                           sender.sendMessage(color("&cPlayer " + args[3] + "&c is offline"));
                           return false;
                       }
                    }
                    if(args.length == 3){
                        try{
                            amount = Integer.parseInt(args[2]);
                            if(!(sender instanceof Player)){
                                Bukkit.getConsoleSender().sendMessage(plugin.name+ChatColor.RED+" You can't send this command from the console");
                                return false;
                            }
                            p = (Player) sender;
                        }catch(IllegalArgumentException e){
                            amount = 1;
                            p = Bukkit.getServer().getPlayer(args[2]);
                            if(p == null){
                                sender.sendMessage(color("&cPlayer " + args[2] + "&c is offline"));
                            return false;
                            }
                        }
                    }
                    
                    //Dark Hole

                    ItemStack stack24 = new ItemStack(Material.valueOf(CLBManager.getManager().getConfig().getString("Objects.DarkHole.block-material")));
                    ItemMeta meta7 = stack24.getItemMeta();
                    meta7.setDisplayName(color(LangLoader.LangCfg.getString("Objects.DarkHole.name")));
                    stack24.setAmount(amount);
                    stack24.setItemMeta(meta7);

                    //Mini Volcano

                    ItemStack stack25 = new ItemStack(Material.valueOf(CLBManager.getManager().getConfig().getString("Objects.MiniVolcano.block-material")));
                    ItemMeta meta8 = stack25.getItemMeta();
                    meta8.setDisplayName(color(LangLoader.LangCfg.getString("Objects.MiniVolcano.name")));
                    stack25.setAmount(amount);
                    stack25.setItemMeta(meta8);
                    
                    //Ice Bow
                    
                    ItemStack stack26 = new ItemStack(Material.BOW);
                    ItemMeta meta9 = stack26.getItemMeta();
                    meta9.setDisplayName(color(LangLoader.LangCfg.getString("Objects.IceBow.name")));
                    meta9.addEnchant(Enchantment.DURABILITY, 5, true);
                    stack26.setAmount(amount);
                    stack26.setItemMeta(meta9); 
                    
                    
                    
                    p.getInventory().addItem(stack24);
                    p.getInventory().addItem(stack25);
                    p.getInventory().addItem(stack26);

                    String loadobjects = color(LangLoader.LangCfg.getString("LoadingObjects"));
                    sender.sendMessage(loadobjects);
//                    }
            }else{
                String noperm = color(LangLoader.LangCfg.getString("NoPermission"));
                sender.sendMessage (noperm);
            }
        }else if(((args[0].equalsIgnoreCase("give")) && args[1].equalsIgnoreCase("luckyblock"))){
            String luckyperm = CLBManager.getManager().getConfig().getString("Commands.Give.LuckyBlock");
            if (sender.hasPermission(luckyperm)){
//                if(!(sender instanceof Player)){
//                    Bukkit.getConsoleSender().sendMessage(plugin.name+ChatColor.RED+" You can't send this command from the console");
//                    return false;
//                }else{
                    int amount = 1;
                    Player p = null;
                    if(args.length == 2){
                        if(!(sender instanceof Player)){
                            Bukkit.getConsoleSender().sendMessage(plugin.name+ChatColor.RED+" You can't send this command from the console");
                            return false;
                        }
                        p = (Player) sender;
                        amount = 1; 
                    }
                    if(args.length == 4){
                        try{
                            amount = Integer.parseInt(args[2]);
                        }catch(IllegalArgumentException e){
                            amount = 1;
                        }
                        
                        p = Bukkit.getServer().getPlayer(args[3]);
                        if(p == null){
                           sender.sendMessage(color("&cPlayer " + args[3] + "&c is offline"));
                           return false;
                       }
                    }
                    if(args.length == 3){
                        try{
                            amount = Integer.parseInt(args[2]);
                            if(!(sender instanceof Player)){
                                Bukkit.getConsoleSender().sendMessage(plugin.name+ChatColor.RED+" You can't send this command from the console");
                                return false;
                            }
                            p = (Player) sender;
                        }catch(IllegalArgumentException e){
                            amount = 1;
                            p = Bukkit.getServer().getPlayer(args[2]);
                            if(p == null){
                                sender.sendMessage(color("&cPlayer " + args[2] + "&c is offline"));
                            return false;
                            }
                        }
                    }
                ItemStack lucky;
                if(Database.headMode && Database.isDatabaseLoaded){
                    lucky = Database.item;
                }else if(Database.headMode && !Database.isDatabaseLoaded){
                    return false;
                }else{
                    lucky = new ItemStack(Material.valueOf(CLBManager.getManager().getConfig().getString("LuckyBlock.Material")));
                }
                
                List<String> configLore = CLBManager.getManager().getConfig().getStringList("LuckyBlock.Lore");
                List<String> blockLore = new ArrayList();
                for(int i = 0; i < configLore.size(); i++){
                    blockLore.add(color(configLore.get(i)));
                }
                
                
                ItemMeta lucky_meta = lucky.getItemMeta();
                lucky_meta.setDisplayName(color(CLBManager.getManager().getConfig().getString("LuckyBlock.Name")));
                lucky_meta.setLore(blockLore);
                lucky.setItemMeta(lucky_meta);
                lucky.setAmount(amount);
                    
                

                String loadblocks = color(LangLoader.LangCfg.getString("LoadingBlocks"));
                sender.sendMessage(loadblocks);
                p.getInventory().addItem(lucky);
//                }
            }else{
                String noperm = color(LangLoader.LangCfg.getString("NoPermission"));
                sender.sendMessage (noperm);     
            }
        }else if((args.length == 1) && (args[0].equalsIgnoreCase("reload"))){
            String rlperm = CLBManager.getManager().getConfig().getString("Commands.Reload-permission");
            if (sender.hasPermission(rlperm)){
                plugin.reloadConfig();
                File cfile = CLBManager.getManager().cfile;
                //LangLoader.mkdir();
                //LangLoader.MessageFile();
                //LangLoader.loadLang();
                CLBManager.getManager().config = YamlConfiguration.loadConfiguration(cfile);
                CLBManager.getManager().saveConfig();
                LangLoader.getManager().reloadMessages();
                LangLoader.getManager().getMessages();
                plugin.prefix = LangLoader.LangCfg.getString("InGamePrefix");

                WorldList.instance.ReloadAll();
          
                Database db = new Database();
                db.checkHeadMode();
                
                String reload1 = color(LangLoader.LangCfg.getString("Reload.line1"));
                String reload2 = color(LangLoader.LangCfg.getString("Reload.line2"));
                String reload3 = color(LangLoader.LangCfg.getString("Reload.line3"));
                sender.sendMessage(reload1);
                sender.sendMessage(reload2);
                sender.sendMessage(reload3);
                
                String prefix = LangLoader.LangCfg.getString("InGamePrefix");
                
                if(CLBManager.getManager().getConfig().getString("LuckyBlock.Material").equals("SKULL") && !Database.headMode &&
                        (AmazingLuckyBlocks.getInstance().minecraftVersion.equals("1.13") || AmazingLuckyBlocks.getInstance().minecraftVersion.equals("1.14"))){
                    sender.sendMessage(color(prefix + " " + LangLoader.LangCfg.getString("Error-message.1").replaceAll("%version%", plugin.minecraftVersion)));
                    sender.sendMessage(color(prefix + " " + LangLoader.LangCfg.getString("Error-message.2")));
                }
                
            }else{
                String noperm2 = color(LangLoader.LangCfg.getString("NoPermission"));
                sender.sendMessage (noperm2);
            }
            return true;
        }else if(((args.length == 6) || (args.length == 7)) && (args[0].equalsIgnoreCase("randomblock"))){
            String rbperm = CLBManager.getManager().getConfig().getString("Commands.RandomBlocks-permission");
//            if(!(sender instanceof Player)){
//		Bukkit.getConsoleSender().sendMessage(plugin.name+ChatColor.RED+" You can't send this command from the console");
//		return false;
//            }else{
                if(sender.hasPermission(rbperm)){
                    Player player;
                    if(args.length == 6){
                        if(!(sender instanceof Player)){
                            Bukkit.getConsoleSender().sendMessage(plugin.name+ChatColor.RED+" You can't send this command from the console");
                            return false;
                        } 
                        player = (Player) sender; 
                    }else{
                        player = Bukkit.getServer().getPlayer(args[6]);
                        if(player == null){
                           sender.sendMessage(color("&cPlayer " + args[6] + "&c is offline"));
                           return false;
                        }
                    }
                    
                    int radx = Integer.parseInt(args[1]);
                    int rady = Integer.parseInt(args[2]);
                    int radz = Integer.parseInt(args[3]);
                    int blocks = Integer.parseInt(args[4]);
                    boolean floating_blocks;
                    switch (args[5]) {
                    case "true":
                        floating_blocks = true;
                        break;
                    case "false":
                        floating_blocks = false;
                        break;
                    default:
                        floating_blocks = false;
                        break;
                    }
                    
                    
                    String placeblocks = color(LangLoader.LangCfg.getString("PlacingBlocks"));
                    sender.sendMessage(placeblocks);
                    
                    boolean isPlayer = sender instanceof Player;
                    
                    RandomBlocks rb = new RandomBlocks(radx,rady,radz,blocks,floating_blocks,player,isPlayer);
                    rb.generateRandomBlocks();
                }else{
                    String noperm = color(LangLoader.LangCfg.getString("NoPermission"));
                    sender.sendMessage(plugin.name + " " +noperm);
                }
//            }
        }else{
            String unknowncommand = color(LangLoader.LangCfg.getString("UnknownCommand"));
            sender.sendMessage(unknowncommand);
        }
    }
    return false;
  }
}

    