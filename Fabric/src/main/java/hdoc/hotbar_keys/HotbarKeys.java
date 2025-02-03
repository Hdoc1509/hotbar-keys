package hdoc.hotbar_keys;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

public class HotbarKeys implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.
        KeyBindingHelper.registerKeyBinding(CommonClass.usePrevItem);
        KeyBindingHelper.registerKeyBinding(CommonClass.useNextItem);

        CommonClass.init();
    }
}
