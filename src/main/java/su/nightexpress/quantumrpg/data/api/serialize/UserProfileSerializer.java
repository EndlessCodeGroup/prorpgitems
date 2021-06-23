package su.nightexpress.quantumrpg.data.api.serialize;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.inventory.ItemStack;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import mc.promcteam.engine.utils.CollectionsUT;
import mc.promcteam.engine.utils.ItemUT;
import su.nightexpress.quantumrpg.QuantumRPG;
import su.nightexpress.quantumrpg.api.QuantumAPI;
import su.nightexpress.quantumrpg.data.api.UserEntityNamesMode;
import su.nightexpress.quantumrpg.data.api.UserProfile;
import su.nightexpress.quantumrpg.manager.effects.buffs.SavedBuff;
import su.nightexpress.quantumrpg.modules.EModule;
import su.nightexpress.quantumrpg.modules.list.classes.ClassManager;
import su.nightexpress.quantumrpg.modules.list.classes.api.RPGClass;
import su.nightexpress.quantumrpg.modules.list.classes.api.UserClassData;

public class UserProfileSerializer implements JsonSerializer<UserProfile>, JsonDeserializer<UserProfile> {

	@Override
	public JsonElement serialize(UserProfile src, Type type, JsonSerializationContext contex) {
		
		JsonObject o = new JsonObject();
		o.addProperty("name", src.getIdName());
		o.addProperty("isDefault", src.isDefault());
		o.add("buffDamage", contex.serialize(src.getDamageBuffs()));
		o.add("buffDefense", contex.serialize(src.getDefenseBuffs()));
		o.add("buffStats", contex.serialize(src.getItemStatBuffs()));
		o.add("inventory", contex.serialize(ItemUT.toBase64(src.getInventory())));
		o.addProperty("namesMode", src.getNamesMode().name());
		o.addProperty("hideHelmet", src.isHideHelmet());
		o.add("cData", contex.serialize(src.getClassData()));
		o.addProperty("cCooldown", src.getClassSelectionCooldown());
		
		return o;
	}

	@Override
	public UserProfile deserialize(JsonElement json, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		
		JsonObject j = json.getAsJsonObject();
		String id = j.get("name").getAsString();
		boolean isDefault = j.get("isDefault").getAsBoolean();
		
		Set<SavedBuff> buffDamage = new HashSet<>();
		JsonElement jBuffsElem = j.get("buffDamage");
		JsonArray jBuffs = null;
		if (jBuffsElem != null) {
			jBuffs = jBuffsElem.getAsJsonArray();
			for (JsonElement e : jBuffs) {
				buffDamage.add(context.deserialize(e, SavedBuff.class));
			}
		}
		
		Set<SavedBuff> buffDefense = new HashSet<>();
		jBuffsElem = j.get("buffDefense");
		jBuffs = null;
		if (jBuffsElem != null) {
			jBuffs = jBuffsElem.getAsJsonArray();
			for (JsonElement e : jBuffs) {
				buffDefense.add(context.deserialize(e, SavedBuff.class));
			}
		}
		
		Set<SavedBuff> buffStats = new HashSet<>();
		jBuffsElem = j.get("buffStats");
		jBuffs = null;
		if (jBuffsElem != null) {
			jBuffs = jBuffsElem.getAsJsonArray();
			for (JsonElement e : jBuffs) {
				buffStats.add(context.deserialize(e, SavedBuff.class));
			}
		}
		
		JsonElement eInventory = j.get("inventory");
		ItemStack[] inventory = new ItemStack[41];
		if (eInventory != null) {
			int count = 0;
			for (JsonElement item : eInventory.getAsJsonArray()) {
				inventory[count++] = ItemUT.fromBase64(item.getAsString());
			}
		}
		
		JsonElement eNames = j.get("namesMode");
		String namesModeRaw = eNames != null ? eNames.getAsString() : null;
		UserEntityNamesMode namesMode = namesModeRaw != null ? CollectionsUT.getEnum(namesModeRaw, UserEntityNamesMode.class) : UserEntityNamesMode.DEFAULT;
		
		JsonElement eHideHelmet = j.get("hideHelmet");
		boolean hideHelmet = eHideHelmet != null ? eHideHelmet.getAsBoolean() : false;
		
		UserClassData cData = null;
		JsonElement jData = j.get("cData");
		if (jData != null && QuantumRPG.getInstance().cfg().isModuleEnabled(EModule.CLASSES)) {
			JsonObject jClass = jData.getAsJsonObject();
			cData = context.deserialize(jClass, UserClassData.class);
			
			String clazzId = cData.getClassId();
			ClassManager classManager = QuantumAPI.getModuleManager().getClassManager();
			RPGClass clazz = classManager == null ? null : classManager.getClassById(clazzId);
			if (clazz == null) {
				System.out.println("[QuantumRPG] Player class '" + clazzId + "' no more exists.");
				cData = null;
			}
			else {
				cData.setPlayerClass(clazz);
			}
		}
		
		
		JsonElement jCooldown = j.get("cCooldown");
		long cCooldown = 0L;
		if (jCooldown != null) cCooldown = jCooldown.getAsLong();
		
		return new UserProfile(
			id, 
			isDefault, 
				
			buffDamage, 
			buffDefense, 
			buffStats, 
			
			inventory,
			namesMode == null ? UserEntityNamesMode.DEFAULT : namesMode,
			hideHelmet,
			
			cData, 
			cCooldown
		);
	}
}
