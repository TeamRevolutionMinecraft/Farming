package teamrevolution.farming.enums;

import org.bukkit.Material;
import teamrevolution.farming.utils.Config;

import java.util.List;

public enum Resources {
    ORES(Config.loadMats("ore"));

    private final List<Material> resourceType;
    Resources(List<Material> mats) {
        this.resourceType = mats;
    }

    public List<Material> getResources() {
        return resourceType;
    }
}
