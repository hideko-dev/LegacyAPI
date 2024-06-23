package dev.hideko.utility

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.lang.reflect.Field
import java.lang.reflect.Method


class ActionBar {

    fun send(player: Player?, message: String) {
        if (player == null) return
        if (!player.isOnline) return

        var version = Bukkit.getServer().javaClass.getPackage().name
        version = version.substring(version.lastIndexOf(".") + 1)

        val old = version.equals("v1_8_R1", ignoreCase = true) || version.startsWith("v1_7_")

        try {
            val craftPlayerClass = Class.forName("org.bukkit.craftbukkit.$version.entity.CraftPlayer")
            val craftPlayer = craftPlayerClass.cast(player)
            var packet: Any
            val packetPlayOutChatClass = Class.forName("net.minecraft.server.$version.PacketPlayOutChat")
            val packetClass = Class.forName("net.minecraft.server.$version.Packet")
            if (old) {
                val chatSerializerClass = Class.forName("net.minecraft.server.$version.ChatSerializer")
                val iChatBaseComponentClass = Class.forName("net.minecraft.server.$version.IChatBaseComponent")
                val m3: Method = chatSerializerClass.getDeclaredMethod("a", String::class.java)
                val cbc = iChatBaseComponentClass.cast(
                    m3.invoke(
                        chatSerializerClass,
                        "{\"text\": \"$message\"}"
                    )
                )
                packet = packetPlayOutChatClass.getConstructor(
                    *arrayOf(
                        iChatBaseComponentClass,
                        Byte::class.javaPrimitiveType
                    )
                ).newInstance(cbc, 2.toByte())
            } else {
                val chatComponentTextClass = Class.forName("net.minecraft.server.$version.ChatComponentText")
                val iChatBaseComponentClass = Class.forName("net.minecraft.server.$version.IChatBaseComponent")
                try {
                    val chatMessageTypeClass = Class.forName("net.minecraft.server.$version.ChatMessageType")
                    val chatMessageTypes: Array<out Any>? = chatMessageTypeClass.getEnumConstants()
                    var chatMessageType: Any? = null
                    if (chatMessageTypes != null) {
                        for (o in chatMessageTypes) {
                            if (o.toString() == "GAME_INFO") {
                                chatMessageType = o
                            }
                        }
                    }
                    val chatCompontentText = chatComponentTextClass.getConstructor(
                        *arrayOf<Class<*>>(
                            String::class.java
                        )
                    ).newInstance(message)
                    packet =
                        packetPlayOutChatClass.getConstructor(*arrayOf(iChatBaseComponentClass, chatMessageTypeClass))
                            .newInstance(chatCompontentText, chatMessageType)
                } catch (e: ClassNotFoundException) {
                    val chatCompontentText = chatComponentTextClass.getConstructor(
                        *arrayOf<Class<*>>(
                            String::class.java
                        )
                    ).newInstance(message)
                    packet = packetPlayOutChatClass.getConstructor(
                        *arrayOf(
                            iChatBaseComponentClass,
                            Byte::class.javaPrimitiveType
                        )
                    ).newInstance(chatCompontentText, 2.toByte())
                }
            }
            val craftPlayerHandleMethod: Method = craftPlayerClass.getDeclaredMethod("getHandle")
            val craftPlayerHandle: Any = craftPlayerHandleMethod.invoke(craftPlayer)
            val playerConnectionField: Field = craftPlayerHandle.javaClass.getDeclaredField("playerConnection")
            val playerConnection: Any = playerConnectionField.get(craftPlayerHandle)
            val sendPacketMethod: Method = playerConnection.javaClass.getDeclaredMethod("sendPacket", packetClass)
            sendPacketMethod.invoke(playerConnection, packet)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
