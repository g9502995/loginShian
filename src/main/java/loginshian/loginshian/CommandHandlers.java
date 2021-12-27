package loginshian.loginshian;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.ParametersAreNonnullByDefault;

public class CommandHandlers implements CommandExecutor{

    @Override
    @ParametersAreNonnullByDefault
//修改密碼程式

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equals("register")) {
            String pwdConcat = String.join("<space>", args[0]);
            sender.sendMessage(pwdConcat);
            if (!(sender instanceof Player)) {
            return false;

        }
         //確認格式
        try {

            if (args.length < 2) {
                sender.sendMessage(ChatColor.GREEN + "請輸入正確的格式");

                return false;

            }
        }catch (Exception e){

        }

       //驗證玩家登入了沒
        if (ConfigReader.isPlayerRegistered(sender.getName())) {
            //驗證舊密碼
            if (ConfigReader.verifyPassword(sender.getName(), pwdConcat)) {
                String a = args[1];
                //修改密碼
                ConfigReader.changePassword(sender.getName(),a);
                sender.sendMessage(ChatColor.RED + "密碼更改成功");
            } else {
                sender.sendMessage(ChatColor.RED + "舊密碼輸入錯誤！");
            }
            return true;

        } else {

            sender.sendMessage(ChatColor.RED + "你還沒註冊！");
            return true;
        }
        }
return false;
    }



}
