package org.metadevs.buycraftapi.providers;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.logging.Level;
import java.util.logging.Logger;

public class VaultSupport implements IVaultProvider {
    private Permission perms = null;
    private final Logger logger;

    public VaultSupport(Logger logger) {
        this.logger = logger;
    }

    @Override
    public boolean setupPermissions() {
        if (!Bukkit.getPluginManager().isPluginEnabled("Vault")) {
            return false;
        }
        try {
            RegisteredServiceProvider<Permission> rsp = Bukkit.getServer().getServicesManager()
                    .getRegistration(Permission.class);
            if (rsp == null)
                return false;
            perms = rsp.getProvider();
            logger.log(Level.INFO, "Successfully hooked into Vault permissions.");
            return true;
        } catch (Exception e) {
            logger.log(Level.WARNING, "Failed to hook into Vault permissions: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String getPrimaryGroup(OfflinePlayer player) {
        if (perms == null) {
            return "No Vault";
        }
        try {
            return perms.getPrimaryGroup(null, player);
        } catch (Exception e) {
            return "Error";
        }
    }

    @Override
    public boolean isHooked() {
        return perms != null;
    }
}
