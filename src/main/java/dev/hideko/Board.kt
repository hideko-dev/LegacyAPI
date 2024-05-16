package dev.hideko

import fr.mrmicky.fastboard.FastBoard
import org.bukkit.entity.Player

class Board {

    companion object {

        fun send(player: Player, title: String, titles: List<String>) {
            val board = FastBoard(player)
            board.updateTitle(title)
            board.updateLines(titles)
        }

    }

}