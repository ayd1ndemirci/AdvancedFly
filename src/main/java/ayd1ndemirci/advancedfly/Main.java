package ayd1ndemirci.advancedfly;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getLogger().info("AdvancedFly Enabled - https://ayd1ndemirci.github.io");
        getCommand("fly").setExecutor(new FlyCommand());
    }

    @Override
    public void onDisable() {
        this.getLogger().info("AdvancedFly Disabled - https://ayd1ndemirci.github.io");
    }
}
