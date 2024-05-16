package dev.hideko

import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask

class Task {

    companion object {

        fun late(plugin: JavaPlugin, later: Long, task: (cancel: () -> Unit) -> Unit): BukkitTask? {
            return object : BukkitRunnable() {
                override fun run() {
                    task { cancel() }
                }
            }.runTaskLater(plugin, later)
        }

        fun timer(plugin: JavaPlugin, later: Long, period: Long, task: (cancel: () -> Unit) -> Unit): BukkitTask? {
            return object : BukkitRunnable() {
                override fun run() {
                    task { cancel() }
                }
            }.runTaskTimer(plugin, later, period)
        }

    }

}