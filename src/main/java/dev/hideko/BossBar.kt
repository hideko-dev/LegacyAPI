package dev.hideko

import me.confuser.barapi.BarAPI
import org.bukkit.entity.Player

class BossBar {

    fun send(player: Player, message: String) {
        BarAPI.setMessage(player, message)
    }

    fun remove(player: Player) {
        BarAPI.removeBar(player)
    }

}