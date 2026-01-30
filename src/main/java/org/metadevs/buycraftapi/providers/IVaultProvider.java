package org.metadevs.buycraftapi.providers;

import org.bukkit.OfflinePlayer;

public interface IVaultProvider {
    boolean setupPermissions();
    String getPrimaryGroup(OfflinePlayer player);
    boolean isHooked();
}
