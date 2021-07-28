package su.nightexpress.quantumrpg.nbt;

import org.bukkit.inventory.ItemStack;
import su.nightexpress.quantumrpg.nbt.utils.MinecraftVersion;

import java.lang.reflect.Method;

public enum ReflectionMethod {
    COMPOUND_SET_FLOAT(ClassWrapper.NMS_NBTTAGCOMPOUND.getClazz(), new Class[]{String.class, float.class}, MinecraftVersion.MC1_7_R4, new Since[]{new Since(MinecraftVersion.MC1_7_R4, "setFloat")}),
    COMPOUND_SET_STRING(ClassWrapper.NMS_NBTTAGCOMPOUND.getClazz(), new Class[]{String.class, String.class}, MinecraftVersion.MC1_7_R4, new Since[]{new Since(MinecraftVersion.MC1_7_R4, "setString")}),
    COMPOUND_SET_INT(ClassWrapper.NMS_NBTTAGCOMPOUND.getClazz(), new Class[]{String.class, int.class}, MinecraftVersion.MC1_7_R4, new Since[]{new Since(MinecraftVersion.MC1_7_R4, "setInt")}),
    COMPOUND_SET_BYTEARRAY(ClassWrapper.NMS_NBTTAGCOMPOUND.getClazz(), new Class[]{String.class, byte[].class}, MinecraftVersion.MC1_7_R4, new Since[]{new Since(MinecraftVersion.MC1_7_R4, "setByteArray")}),
    COMPOUND_SET_INTARRAY(ClassWrapper.NMS_NBTTAGCOMPOUND.getClazz(), new Class[]{String.class, int[].class}, MinecraftVersion.MC1_7_R4, new Since[]{new Since(MinecraftVersion.MC1_7_R4, "setIntArray")}),
    COMPOUND_SET_LONG(ClassWrapper.NMS_NBTTAGCOMPOUND.getClazz(), new Class[]{String.class, long.class}, MinecraftVersion.MC1_7_R4, new Since[]{new Since(MinecraftVersion.MC1_7_R4, "setLong")}),
    COMPOUND_SET_SHORT(ClassWrapper.NMS_NBTTAGCOMPOUND.getClazz(), new Class[]{String.class, short.class}, MinecraftVersion.MC1_7_R4, new Since[]{new Since(MinecraftVersion.MC1_7_R4, "setShort")}),
    COMPOUND_SET_BYTE(ClassWrapper.NMS_NBTTAGCOMPOUND.getClazz(), new Class[]{String.class, byte.class}, MinecraftVersion.MC1_7_R4, new Since[]{new Since(MinecraftVersion.MC1_7_R4, "setByte")}),
    COMPOUND_SET_DOUBLE(ClassWrapper.NMS_NBTTAGCOMPOUND.getClazz(), new Class[]{String.class, double.class}, MinecraftVersion.MC1_7_R4, new Since[]{new Since(MinecraftVersion.MC1_7_R4, "setDouble")}),
    COMPOUND_SET_BOOLEAN(ClassWrapper.NMS_NBTTAGCOMPOUND.getClazz(), new Class[]{String.class, boolean.class}, MinecraftVersion.MC1_7_R4, new Since[]{new Since(MinecraftVersion.MC1_7_R4, "setBoolean")}),
    COMPOUND_GET_FLOAT(ClassWrapper.NMS_NBTTAGCOMPOUND.getClazz(), new Class[]{String.class}, MinecraftVersion.MC1_7_R4, new Since[]{new Since(MinecraftVersion.MC1_7_R4, "getFloat")}),
    COMPOUND_GET_STRING(ClassWrapper.NMS_NBTTAGCOMPOUND.getClazz(), new Class[]{String.class}, MinecraftVersion.MC1_7_R4, new Since[]{new Since(MinecraftVersion.MC1_7_R4, "getString")}),
    COMPOUND_GET_INT(ClassWrapper.NMS_NBTTAGCOMPOUND.getClazz(), new Class[]{String.class}, MinecraftVersion.MC1_7_R4, new Since[]{new Since(MinecraftVersion.MC1_7_R4, "getInt")}),
    COMPOUND_GET_BYTEARRAY(ClassWrapper.NMS_NBTTAGCOMPOUND.getClazz(), new Class[]{String.class}, MinecraftVersion.MC1_7_R4, new Since[]{new Since(MinecraftVersion.MC1_7_R4, "getByteArray")}),
    COMPOUND_GET_INTARRAY(ClassWrapper.NMS_NBTTAGCOMPOUND.getClazz(), new Class[]{String.class}, MinecraftVersion.MC1_7_R4, new Since[]{new Since(MinecraftVersion.MC1_7_R4, "getIntArray")}),
    COMPOUND_GET_LONG(ClassWrapper.NMS_NBTTAGCOMPOUND.getClazz(), new Class[]{String.class}, MinecraftVersion.MC1_7_R4, new Since[]{new Since(MinecraftVersion.MC1_7_R4, "getLong")}),
    COMPOUND_GET_SHORT(ClassWrapper.NMS_NBTTAGCOMPOUND.getClazz(), new Class[]{String.class}, MinecraftVersion.MC1_7_R4, new Since[]{new Since(MinecraftVersion.MC1_7_R4, "getShort")}),
    COMPOUND_GET_BYTE(ClassWrapper.NMS_NBTTAGCOMPOUND.getClazz(), new Class[]{String.class}, MinecraftVersion.MC1_7_R4, new Since[]{new Since(MinecraftVersion.MC1_7_R4, "getByte")}),
    COMPOUND_GET_DOUBLE(ClassWrapper.NMS_NBTTAGCOMPOUND.getClazz(), new Class[]{String.class}, MinecraftVersion.MC1_7_R4, new Since[]{new Since(MinecraftVersion.MC1_7_R4, "getDouble")}),
    COMPOUND_GET_BOOLEAN(ClassWrapper.NMS_NBTTAGCOMPOUND.getClazz(), new Class[]{String.class}, MinecraftVersion.MC1_7_R4, new Since[]{new Since(MinecraftVersion.MC1_7_R4, "getBoolean")}),
    COMPOUND_REMOVE_KEY(ClassWrapper.NMS_NBTTAGCOMPOUND.getClazz(), new Class[]{String.class}, MinecraftVersion.MC1_7_R4, new Since[]{new Since(MinecraftVersion.MC1_7_R4, "remove")}),
    COMPOUND_HAS_KEY(ClassWrapper.NMS_NBTTAGCOMPOUND.getClazz(), new Class[]{String.class}, MinecraftVersion.MC1_7_R4, new Since[]{new Since(MinecraftVersion.MC1_7_R4, "hasKey")}),
    COMPOUND_GET_TYPE(ClassWrapper.NMS_NBTTAGCOMPOUND.getClazz(), new Class[]{String.class}, MinecraftVersion.MC1_8_R3, new Since[]{new Since(MinecraftVersion.MC1_8_R3, "b"), new Since(MinecraftVersion.MC1_9_R1, "d")}),
    COMPOUND_GET_KEYS(ClassWrapper.NMS_NBTTAGCOMPOUND.getClazz(), new Class[0], MinecraftVersion.MC1_7_R4, new Since[]{new Since(MinecraftVersion.MC1_7_R4, "c"), new Since(MinecraftVersion.MC1_13_R1, "getKeys")}),
    LISTCOMPOUND_GET_KEYS(ClassWrapper.NMS_NBTTAGCOMPOUND.getClazz(), new Class[0], MinecraftVersion.MC1_7_R4, new Since[]{new Since(MinecraftVersion.MC1_7_R4, "c"), new Since(MinecraftVersion.MC1_13_R1, "getKeys")}),
    ITEMSTACK_SET_TAG(ClassWrapper.NMS_ITEMSTACK.getClazz(), new Class[]{ClassWrapper.NMS_NBTTAGCOMPOUND.getClazz()}, MinecraftVersion.MC1_7_R4, new Since[]{new Since(MinecraftVersion.MC1_7_R4, "setTag")}),
    ITEMSTACK_NMSCOPY(ClassWrapper.CRAFT_ITEMSTACK.getClazz(), new Class[]{ItemStack.class}, MinecraftVersion.MC1_7_R4, new Since[]{new Since(MinecraftVersion.MC1_7_R4, "asNMSCopy")}),
    ITEMSTACK_BUKKITMIRROR(ClassWrapper.CRAFT_ITEMSTACK.getClazz(), new Class[]{ClassWrapper.NMS_ITEMSTACK.getClazz()}, MinecraftVersion.MC1_7_R4, new Since[]{new Since(MinecraftVersion.MC1_7_R4, "asCraftMirror")}),
    PARSE_NBT(ClassWrapper.NMS_MOJANGSONPARSER.getClazz(), new Class[]{String.class}, MinecraftVersion.MC1_7_R4, new Since[]{new Since(MinecraftVersion.MC1_7_R4, "parse")});

    private Since targetVersion;

    private Method method;

    private boolean loaded;

    private boolean compatible;

    ReflectionMethod(Class<?> targetClass, Class[] args, MinecraftVersion addedSince, Since[] methodnames) {
        this.loaded = false;
        this.compatible = false;
        MinecraftVersion server = MinecraftVersion.getVersion();
        if (server.compareTo(addedSince) < 0)
            return;
        this.compatible = true;
        Since target = methodnames[0];
        byte b;
        int i;
        Since[] arrayOfSince;
        for (i = (arrayOfSince = methodnames).length, b = 0; b < i; ) {
            Since s = arrayOfSince[b];
            if (s.version.getVersionId() <= server.getVersionId() && target.version.getVersionId() < s.version.getVersionId())
                target = s;
            b++;
        }
        this.targetVersion = target;
        try {
            (this.method = targetClass.getMethod(this.targetVersion.name, args)).setAccessible(true);
            this.loaded = true;
        } catch (NullPointerException | NoSuchMethodException | SecurityException ex3) {
            ex3.printStackTrace();
        }
    }

    public Object run(Object target, Object... args) {
        try {
            return this.method.invoke(target, args);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean isLoaded() {
        return this.loaded;
    }

    public boolean isCompatible() {
        return this.compatible;
    }

    public static class Since {
        public final MinecraftVersion version;

        public final String name;

        public Since(MinecraftVersion version, String name) {
            this.version = version;
            this.name = name;
        }
    }
}
