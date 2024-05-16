package dev.hideko

import com.cryptomorin.xseries.messages.ActionBar
import org.bukkit.entity.Player

class ActionBar {

    companion object {

        fun send(player: Player, message: String) {
            ActionBar.sendActionBar(player, message)
        }

    }

}