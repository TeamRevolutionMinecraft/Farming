package teamrevolution.farming.utils;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import teamrevolution.farming.Farming;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Config {
    private static final File file = new File(Farming.getPlugin().getDataFolder() + "//config.yml");
    private static final YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);

    public static List<Material> loadMats(String typeOfFarm) {

        ArrayList<Material> mats = new ArrayList<>();

        for(String path : yamlConfiguration.getKeys(false)) {
            if (Objects.equals(yamlConfiguration.get(path + ".type_of_farm"), typeOfFarm)) {
                String matsString = yamlConfiguration.getString(path + ".materials");
                if (matsString == null) {
                    break;
                }

                for (String mat : matsString.split(" ")) {
                    mats.add(Material.getMaterial(mat));
                }
            }
        }
        return mats;
    }
    public static HashMap<String, Integer> loadHeights() {
        HashMap<String, Integer> height = new HashMap<>();
        height.put("D_min", yamlConfiguration.getInt("OreHeight.diamond_min"));
        height.put("D_max", yamlConfiguration.getInt("OreHeight.diamond_max"));

        height.put("G_min", yamlConfiguration.getInt("OreHeight.gold_min"));
        height.put("G_max", yamlConfiguration.getInt("OreHeight.gold_max"));

        height.put("I_min", yamlConfiguration.getInt("OreHeight.iron_min"));
        height.put("I_max", yamlConfiguration.getInt("OreHeight.iron_max"));

        height.put("C_min", yamlConfiguration.getInt("OreHeight.coal_min"));
        height.put("C_max", yamlConfiguration.getInt("OreHeight.coal_max"));

        return height;
    }
}
