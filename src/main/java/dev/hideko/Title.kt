package dev.hideko

import com.cryptomorin.xseries.messages.Titles
import org.bukkit.entity.Player

class Title {

    companion object {

        fun send(player: Player, title: String, subtitle: String) {
            Titles(title, subtitle, 10, 30, 10).send(player)
        }

        fun custom(player: Player, title: String, subtitle: String, fadeIn: Int, stay: Int, fadeOut: Int) {
            Titles(title, subtitle, fadeIn, stay, fadeOut).send(player)
        }

    }

}