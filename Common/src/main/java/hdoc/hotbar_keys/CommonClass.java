package hdoc.hotbar_keys;

import com.mojang.blaze3d.platform.InputConstants;

import hdoc.hotbar_keys.platform.Services;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

import org.lwjgl.glfw.GLFW;

public class CommonClass {

    public static KeyMapping usePrevItem =
            new KeyMapping(
                    Constants.KEY_PREV_ITEM,
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_V,
                    Constants.KEY_CATEGORY_MOD);

    public static KeyMapping useNextItem =
            new KeyMapping(
                    Constants.KEY_NEXT_ITEM,
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_R,
                    Constants.KEY_CATEGORY_MOD);

    public static void onClientTick(Minecraft client) {
        if (client.player == null || client.gameMode == null) return;

        LocalPlayer player = client.player;

        if (usePrevItem.consumeClick()) {
            int currentSlot = player.getInventory().selected;
            int previousSlot = (currentSlot - 1 + 9) % 9;

            player.getInventory().selected = previousSlot;
            return;
        }

        if (useNextItem.consumeClick()) {
            int currentSlot = player.getInventory().selected;
            int nextSlot = (currentSlot + 1) % 9;

            player.getInventory().selected = nextSlot;
            return;
        }
    }

    // This method serves as an initialization hook for the mod. The vanilla
    // game has no mechanism to load tooltip listeners so this must be
    // invoked from a mod loader specific project like Forge or Fabric.
    public static void init() {
        if (Services.PLATFORM.isModLoaded(Constants.MOD_ID)) {
            Constants.LOG.info("{} successfully loaded", Constants.MOD_NAME);
        }
    }
}
