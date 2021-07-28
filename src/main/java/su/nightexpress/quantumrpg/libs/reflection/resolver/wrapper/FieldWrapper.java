package su.nightexpress.quantumrpg.libs.reflection.resolver.wrapper;

import java.lang.reflect.Field;

public class FieldWrapper<R> extends WrapperAbstract {
  private final Field field;
  
  public FieldWrapper(Field field) {
    this.field = field;
  }
  
  public boolean exists() {
    return (this.field != null);
  }
  
  public String getName() {
    return this.field.getName();
  }
  
  public R get(Object object) {
    try {
      return (R)this.field.get(object);
    } catch (Exception e) {
      throw new RuntimeException(e);
    } 
  }
  
  public R getSilent(Object object) {
    try {
      return (R)this.field.get(object);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public void set(Object object, R value) {
    try {
      this.field.set(object, value);
    } catch (Exception e) {
      throw new RuntimeException(e);
    } 
  }
  
  public void setSilent(Object object, R value) {
    try {
      this.field.set(object, value);
    } catch (Exception exception) {}
  }
  
  public Field getField() {
    return this.field;
  }
  
  public boolean equals(Object object) {
    if (this == object)
      return true; 
    if (object == null || getClass() != object.getClass())
      return false; 
    FieldWrapper<?> that = (FieldWrapper)object;
    if ((this.field != null) ? !this.field.equals(that.field) : (that.field != null))
      return false; 
    return true;
  }
  
  public int hashCode() {
    return (this.field != null) ? this.field.hashCode() : 0;
  }
}
