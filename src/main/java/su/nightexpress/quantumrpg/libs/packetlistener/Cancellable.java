package su.nightexpress.quantumrpg.libs.packetlistener;

public class Cancellable implements org.bukkit.event.Cancellable {
  private boolean cancelled;
  
  public boolean isCancelled() {
    return this.cancelled;
  }
  
  public void setCancelled(boolean b) {
    this.cancelled = b;
  }
}
