package hdoc.hotbar_keys;

import com.mojang.blaze3d.platform.InputConstants;

import hdoc.hotbar_keys.platform.Services;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

import org.lwjgl.glfw.GLFW;

// This class is part of the common project meaning it is shared between all supported loaders. Code
// written here can only import and access the vanilla codebase, libraries used by vanilla, and
// optionally third party libraries that provide common compatible binaries. This means common code
// can not directly use loader specific concepts such as Forge events however it will be compatible
// with all supported mod loaders.
public class CommonClass {

    public static KeyMapping usePrevItem =
            new KeyMapping(
                    Constants.KEY_PREV_ITEM,
                    // TODO: add KeyConflictContext.IN_GAME
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_V,
                    Constants.KEY_CATEGORY_MOD);

    public static KeyMapping useNextItem =
            new KeyMapping(
                    Constants.KEY_NEXT_ITEM,
                    // TODO: add KeyConflictContext.IN_GAME
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_R,
                    Constants.KEY_CATEGORY_MOD);

    public static void onClientTick(Minecraft client) {
        if (client.player == null || client.gameMode == null) return;

        LocalPlayer player = client.player;

        if (usePrevItem.consumeClick()) {
            int currentSlot = player.getInventory().selected;
            int nextSlot = (currentSlot - 1 + 9) % 9;

            player.getInventory().selected = nextSlot;
            return;
        }

        if (useNextItem.consumeClick()) {
            int currentSlot = player.getInventory().selected;
            int nextSlot = (currentSlot + 1) % 9;

            player.getInventory().selected = nextSlot;
            return;
        }
    }

    // The loader specific projects are able to import and use any code from the common project.
    // This allows you to write the majority of your code here and load it from your loader specific
    // projects. This example has some code that gets invoked by the entry point of the loader
    // specific projects.
    public static void init() {

        // It is common for all supported loaders to provide a similar feature that can not be used
        // directly in the common code. A popular way to get around this is using Java's built-in
        // service loader feature to create your own abstraction layer. You can learn more about
        // this in our provided services class. In this example we have an interface in the common
        // code and use a loader specific implementation to delegate our call to the platform
        // specific approach.
        if (Services.PLATFORM.isModLoaded(Constants.MOD_ID)) {
            Constants.LOG.info("Successfully loaded!");
        }
    }
}
