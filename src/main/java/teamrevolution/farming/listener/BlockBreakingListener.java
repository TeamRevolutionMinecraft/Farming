package teamrevolution.farming.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import teamrevolution.farming.Farming;
import teamrevolution.farming.enums.Resources;
import teamrevolution.farming.utils.Config;

import java.util.HashMap;

public class BlockBreakingListener implements Listener {
    public BlockBreakingListener(Farming plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private final HashMap<String, Integer> height = Config.loadHeights();



    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (Resources.ORES.getResources().contains(event.getBlock().getType()) || event.getBlock().getType() == Material.STONE) {
            placeBlock(event.getBlock(), Material.COBBLESTONE);
        } else if (event.getBlock().getType() == Material.COBBLESTONE) {
            placeBlock(event.getBlock(), Material.BEDROCK);
        }

        oreSpawnTask(event.getBlock());
    }

    private void placeBlock(Block block, Material material) {
        Bukkit.getScheduler().runTaskLater(Farming.getPlugin(), () -> block.setType(material), 1);
    }
    private void oreSpawnTask(Block block) {
        Bukkit.getScheduler().runTaskLater(Farming.getPlugin(), () -> block.setType(respawnOre(block)), respawnTime());
    }

    private Material respawnOre(Block Block) {
        int yLevel = Block.getY();
        Material block = Material.STONE;

        if (yLevel > height.get("C_min") && yLevel < height.get("C_max")) {
            if(chance(65)) {
                block = Material.COAL_ORE;
            }
        }

        if (yLevel > height.get("I_min") && yLevel < height.get("I_max")) {
            if(chance(50)) {
                block = Material.IRON_ORE;
            }
        }

        if (yLevel > height.get("G_min") && yLevel < height.get("G_max")) {
            if(chance(35)) {
                block = Material.GOLD_ORE;
            }
        }

        if (yLevel > height.get("D_min") && yLevel < height.get("D_max")) {
            if(chance(20)) {
                block = Material.DIAMOND_ORE;
            }
        }
        return block;
    }

    private long respawnTime() {
        int TPS = 20;
        return (int) (Math.random() * 25 * TPS) + 5 * TPS;
    }
    private boolean chance(int base) {
        return ((int) (Math.random() * 100)) < base;
    }
}
