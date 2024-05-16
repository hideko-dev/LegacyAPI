package dev.hideko

import com.cryptomorin.xseries.messages.Titles
import org.bukkit.entity.Player

class Title {

    companion object {

        fun send(player: Player, title: String, subtitle: String = "", fadeIn: Int = 10, stay: Int = 30, fadeOut: Int = 10) {
            Titles(title, subtitle, fadeIn, stay, fadeOut).send(player)
        }

    }

}