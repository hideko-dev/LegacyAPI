package dev.hideko

import com.cryptomorin.xseries.messages.ActionBar
import com.cryptomorin.xseries.messages.Titles
import org.bukkit.entity.Player

class Messages {

    companion object {

        fun sendActionBar(player: Player, message: String) {
            ActionBar.sendActionBar(player, message)
        }

        fun sendTitle(player: Player, title: String, subtitle: String) {
            Titles(title, subtitle, 10, 30, 10).send(player)
        }

    }

}