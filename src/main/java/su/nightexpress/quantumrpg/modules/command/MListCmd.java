package su.nightexpress.quantumrpg.modules.command;

import mc.promcteam.engine.modules.IModuleExecutor;
import mc.promcteam.engine.utils.ClickText;
import mc.promcteam.engine.utils.CollectionsUT;
import mc.promcteam.engine.utils.StringUT;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import su.nightexpress.quantumrpg.Perms;
import su.nightexpress.quantumrpg.QuantumRPG;
import su.nightexpress.quantumrpg.modules.ModuleItem;
import su.nightexpress.quantumrpg.modules.api.QModuleDrop;

import java.util.ArrayList;
import java.util.List;

public class MListCmd extends MCmd<QModuleDrop<?>> {

    public MListCmd(@NotNull QModuleDrop<?> m) {
        super(m, new String[]{"list"}, Perms.ADMIN);
    }

    @Override
    @NotNull
    public String usage() {
        return plugin.lang().Module_Cmd_List_Usage.getMsg();
    }

    @Override
    @NotNull
    public String description() {
        return plugin.lang().Module_Cmd_List_Desc.getMsg();
    }

    @Override
    public boolean playersOnly() {
        return false;
    }

    @Override
    public void perform(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
        if (args.length > 2) {
            this.printUsage(sender);
            return;
        }
        int page = 1;
        if (args.length == 2) {
            page = StringUT.getInteger(args[1], 1);
        }

        List<ModuleItem> list  = new ArrayList<>(module.getItems());
        int              pages = CollectionsUT.split(list, 10).size();
        if (page > pages) page = pages;
        if (pages < 1) list = new ArrayList<>();
        else list = CollectionsUT.split(list, 10).get(page - 1);

        int i = 10 * (page - 1) + 1;

        IModuleExecutor<QuantumRPG> executor = module.getExecutor();
        if (executor == null) return;

        for (String line : plugin.lang().Module_Cmd_List_Format_List.asList()) {
            line = line.replace("%module%", module.name());

            if (line.contains("%item%")) {
                for (ModuleItem ge : list) {
                    ClickText ct = new ClickText(line);
                    ct.createPlaceholder("item%", ge.getId());
                    ct.createPlaceholder("%pos%", String.valueOf(i++));

                    ct.createPlaceholder("%button_get%", plugin.lang().Module_Cmd_List_Button_Get_Name.getMsg())
                            .hint(plugin.lang().Module_Cmd_List_Button_Get_Hint.getMsg())
                            .execCmd("/" + executor.labels()[0] + " get" + " " + ge.getId());

                    ct.send(sender);
                }
                continue;
            }
            sender.sendMessage(line
                    .replace("%pages%", String.valueOf(pages))
                    .replace("%page%", String.valueOf(page)));
        }
    }
}
