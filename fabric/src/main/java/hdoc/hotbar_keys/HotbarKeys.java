package hdoc.hotbar_keys;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

public class HotbarKeys implements ModInitializer {

    @Override
    public void onInitialize() {

        // This method is invoked by the Fabric mod loader when it is ready to load your mod. You
        // can access Fabric and Common code in this project.

        // Use Fabric to bootstrap the Common mod.
        Constants.LOG.info("Hello Fabric world!");
        CommonClass.init();

        KeyBindingHelper.registerKeyBinding(CommonClass.usePrevItem);
        KeyBindingHelper.registerKeyBinding(CommonClass.useNextItem);

        ClientTickEvents.END_CLIENT_TICK.register(client -> CommonClass.onClientTick(client));
    }
}
