package dev.hideko

import org.bukkit.Bukkit
import org.bukkit.Location
import xyz.xenondevs.particle.ParticleBuilder
import xyz.xenondevs.particle.ParticleEffect
import java.awt.Color

class Particle {

    companion object {

        fun drawOutline(loc: Location, color: Color, amount: Int) {
            val world = loc.world ?: return
            val vertices = arrayOf(
                loc.clone().add(0.0, 0.0, 0.0),
                loc.clone().add(1.0, 0.0, 0.0),
                loc.clone().add(0.0, 1.0, 0.0),
                loc.clone().add(1.0, 1.0, 0.0),
                loc.clone().add(0.0, 0.0, 1.0),
                loc.clone().add(1.0, 0.0, 1.0),
                loc.clone().add(0.0, 1.0, 1.0),
                loc.clone().add(1.0, 1.0, 1.0)
            )

            for (i in vertices.indices) {
                for (j in i + 1 until vertices.size) {
                    val start = vertices[i]
                    val end = vertices[j]
                    if (start.distance(end) == 1.0) {
                        drawParticleLine(start, end, world, color, amount)
                    }
                }
            }
        }

        private fun drawParticleLine(start: Location, end: Location, world: org.bukkit.World, color: Color, amount: Int) {
            val steps = 10
            val stepX = (end.x - start.x) / steps
            val stepY = (end.y - start.y) / steps
            val stepZ = (end.z - start.z) / steps
            for (i in 0..steps) {
                val x = start.x + stepX * i
                val y = start.y + stepY * i
                val z = start.z + stepZ * i
                ParticleBuilder(ParticleEffect.REDSTONE, Location(Bukkit.getWorld("world"), x, y, z))
                    .setAmount(amount)
                    .setColor(color)
                    .setSpeed(10f)
                    .display()
            }
        }
    }

}