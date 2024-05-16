package dev.hideko

import me.confuser.barapi.BarAPI
import org.bukkit.entity.Player

class BossBar {

    companion object {

        fun send(player: Player, message: String) {
            BarAPI.setMessage(player, message)
        }

        fun timed(player: Player, message: String, time: Int) {
            BarAPI.setMessage(player, message, time)
        }

        fun health(player: Player): Float {
            return BarAPI.getHealth(player)
        }

        fun message(player: Player): String {
            return BarAPI.getMessage(player)
        }

        fun remove(player: Player) {
            BarAPI.removeBar(player)
        }

    }

}