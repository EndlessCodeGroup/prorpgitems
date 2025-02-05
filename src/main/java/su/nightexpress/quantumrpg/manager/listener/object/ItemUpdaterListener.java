package su.nightexpress.quantumrpg.manager.listener.object;

import mc.promcteam.engine.manager.IListener;
import mc.promcteam.engine.utils.DataUT;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import su.nightexpress.quantumrpg.QuantumRPG;
import su.nightexpress.quantumrpg.stats.items.ItemStats;
import su.nightexpress.quantumrpg.types.NBTAttribute;

public class ItemUpdaterListener extends IListener<QuantumRPG> {


    public ItemUpdaterListener(@NotNull QuantumRPG plugin) {
        super(plugin);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void click(InventoryClickEvent event) {
        ItemStack item   = event.getCurrentItem();
        ItemStack cursor = event.getCursor();

        update(item);
        update(cursor);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void interact(PlayerInteractEvent event) {
        update(event.getItem());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void drop(PlayerDropItemEvent event) {
        update(event.getItemDrop().getItemStack());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void pickup(EntityPickupItemEvent event) {
        update(event.getItem().getItemStack());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void attack(EntityDamageByEntityEvent event) {
        Entity at      = event.getDamager();
        Entity damaged = event.getEntity();

        if (at instanceof Player) {
            Player player = (Player) at;
            for (ItemStack armor : player.getInventory().getArmorContents()) {
                update(armor);
            }
        }

        if (damaged instanceof Player) {
            Player player = (Player) damaged;
            for (ItemStack armor : player.getInventory().getArmorContents()) {
                update(armor);
            }
        }
    }

    public void update(ItemStack item) {
        if (item == null || item.getType() == Material.AIR) return;

        NamespacedKey key   = NamespacedKey.fromString("rpgpro.fixed_damage");
        boolean       fixed = DataUT.getBooleanData(item, key);
        if (fixed) {
            DataUT.removeData(item, key);
            ItemMeta meta = item.getItemMeta();
            meta.removeItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            item.setItemMeta(meta);
        }

        if (ItemStats.hasDamage(item, NBTAttribute.attackDamage.getNmsName())
                && ItemStats.getDamage(item, NBTAttribute.attackDamage.getNmsName()) <= 0) {
            ItemStats.updateVanillaAttributes(item);
//            DataUT.setData(item, key, true);
        }

    }

}
