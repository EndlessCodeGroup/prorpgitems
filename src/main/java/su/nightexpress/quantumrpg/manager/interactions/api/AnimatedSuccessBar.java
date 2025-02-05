package su.nightexpress.quantumrpg.manager.interactions.api;

import java.util.function.Function;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import mc.promcteam.engine.manager.api.task.ITask;
import mc.promcteam.engine.utils.StringUT;
import mc.promcteam.engine.utils.random.Rnd;
import su.nightexpress.quantumrpg.QuantumRPG;

public class AnimatedSuccessBar extends ICustomInteraction {
	
	private String title;
	private String barChar;
	private String barFormat;
	private ChatColor barColorNeutral;
	private ChatColor barColorGood;
	private ChatColor barColorBad;
	private int barSize;
	
	private long fillInterval;
	private int fillAmount;
	
	private double chance;
	private int minSuccess;
	
	private int succ;
	private int unsucc;
	
	private Function<Boolean, Void> result;
	
	private AnimatedSuccessBar(@NotNull Builder builder) {
		super(builder.plugin);
		
		this.title = builder.barTitle;
		this.barChar = builder.barChar;
		this.barFormat = builder.barFormat;
		this.barColorNeutral = builder.barColorNeutral;
		this.barColorGood = builder.barColorGood;
		this.barColorBad = builder.barColorBad;
		this.barSize = builder.barSize;
		this.fillInterval = builder.fillInterval;
		this.fillAmount = builder.fillAmount;
		this.chance = builder.chance;
		this.minSuccess = builder.minSuccess;
		this.result = builder.result;
		
		this.succ = 0;
		this.unsucc = 0;
	}
	
	@Override
	protected boolean doAction() {
		new Task().start();
		return true;
	}
	
	private void display() {
		double oneFillSucc = 100D / (double) this.barSize; // 10 succ = 1 fill
		
		StringBuilder barBuilder = new StringBuilder();
		for (int count = 0; count < this.barSize; count++) {
			if (this.succ >= oneFillSucc * count) {
				barBuilder.append(this.barColorGood);
			}
			else if (this.unsucc >= (this.barSize - count) * oneFillSucc) {
				barBuilder.append(this.barColorBad);
			}
			else {
				barBuilder.append(this.barColorNeutral);
			}
			barBuilder.append(this.barChar);
		}
		
		String bar = this.barFormat
				.replace("%failure%", String.valueOf(unsucc))
				.replace("%success%", String.valueOf(succ))
				.replace("%bar%", barBuilder.toString());
		
		boolean isFirst = succ + unsucc == 0;
		player.sendTitle(title, bar, isFirst ? 10 : 0, (int) fillInterval + 20, 40);
	}
	
	class Task extends ITask<QuantumRPG> {

		Task() {
			super(AnimatedSuccessBar.this.plugin, AnimatedSuccessBar.this.fillInterval, true);
		}

		@Override
		public void action() {
			if (player == null || player.isDead()) {
				this.stop();
				return;
			}
			
			display();
			
			if (succ + unsucc >= 100) {
				plugin.getServer().getScheduler().runTask(plugin, () -> result.apply(succ >= minSuccess));
				endAction();
				this.stop();
				return;
			}
			
			if (Rnd.get(true) < chance) succ += fillAmount; else unsucc += fillAmount;
		}
	}
	
	public static class Builder {
		
		private QuantumRPG plugin;
		
		private String barTitle;
		private String barChar;
		private String barFormat;
		private int barSize;
		private ChatColor barColorNeutral;
		private ChatColor barColorGood;
		private ChatColor barColorBad;
		
		private long fillInterval;
		private int fillAmount;
		
		private double chance;
		private int minSuccess;
		
		private Function<Boolean, Void> result;
		
		public Builder(@NotNull QuantumRPG plugin, @NotNull String title, @NotNull String barChar) {
			this.plugin = plugin;
			this.barTitle = StringUT.color(title);
			this.barChar = StringUT.color(barChar);
			this.setBarFormat("%bar%");
			this.setColorNeutral(ChatColor.DARK_GRAY);
			this.setColorSuccess(ChatColor.GREEN);
			this.setColorUnsuccess(ChatColor.RED);
			this.setBarSize(20);
			this.setFillInterval(1);
			this.setFillAmount(1);
			this.setChance(50);
			this.setMinSuccess(50);
			this.setResult(b -> null);
		}
		
		@NotNull
		@Override
		public Builder clone() {
			Builder clone = new Builder(plugin, barTitle, barChar);
			clone.barFormat = barFormat;
			clone.barColorNeutral = barColorNeutral;
			clone.barColorGood = barColorGood;
			clone.barColorBad = barColorBad;
			clone.barSize = barSize;
			clone.fillInterval = fillInterval;
			clone.fillAmount = fillAmount;
			clone.chance = chance;
			clone.minSuccess = minSuccess;
			clone.result = result;
			return clone;
		}
		
		@NotNull
		public AnimatedSuccessBar build() {
			return new AnimatedSuccessBar(this);
		}
		
		@NotNull
		public Builder setBarFormat(@NotNull String format) {
			this.barFormat = StringUT.color(format);
			return this;
		}
		
		@NotNull
		public Builder setColorNeutral(@NotNull ChatColor color) {
			this.barColorNeutral = color;
			return this;
		}
		
		@NotNull
		public Builder setColorSuccess(@NotNull ChatColor color) {
			this.barColorGood = color;
			return this;
		}
		
		@NotNull
		public Builder setColorUnsuccess(@NotNull ChatColor color) {
			this.barColorBad = color;
			return this;
		}
		
		@NotNull
		public Builder setBarSize(int size) {
			this.barSize = size;
			return this;
		}
		
		@NotNull
		public Builder setFillInterval(long interval) {
			this.fillInterval = interval;
			return this;
		}
		
		@NotNull
		public Builder setFillAmount(int amount) {
			this.fillAmount = amount;
			return this;
		}
		
		@NotNull
		public Builder setChance(double chance) {
			this.chance = chance;
			return this;
		}
		
		@NotNull
		public Builder setMinSuccess(int min) {
			this.minSuccess = min;
			return this;
		}
		
		@NotNull
		public Builder setResult(@NotNull Function<Boolean, Void> result) {
			this.result = result;
			return this;
		}
	}
}
