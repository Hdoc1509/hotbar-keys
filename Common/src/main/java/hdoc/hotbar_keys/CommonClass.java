package hdoc.hotbar_keys;

import hdoc.hotbar_keys.platform.Services;

public class CommonClass {

    // NOTE: create usePrevItem KeyMapping
    // NOTE: create useNextItem KeyMapping
    // NOTE: create onClientTick method

    // This method serves as an initialization hook for the mod. The vanilla
    // game has no mechanism to load tooltip listeners so this must be
    // invoked from a mod loader specific project like Forge or Fabric.
    public static void init() {
        if (Services.PLATFORM.isModLoaded(Constants.MOD_ID)) {
            Constants.LOG.info("{} successfully loaded", Constants.MOD_NAME);
        }
    }
}
