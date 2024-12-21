package hdoc.hotbar_keys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {
    // NOTE: should match the mod_id in the gradle.properties file
    public static final String MOD_ID = "hotbar-keys";
    public static final String MOD_NAME = "Hotbar Keys";
    public static final String KEY_CATEGORY_MOD = "key.category." + MOD_ID + ".hotbar_key";
    public static final String KEY_PREV_ITEM = "key." + MOD_ID + ".prev_item";
    public static final String KEY_NEXT_ITEM = "key." + MOD_ID + ".next_item";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
}
