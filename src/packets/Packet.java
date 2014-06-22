package packets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class Packet implements Serializable {
  
  /**
   * Default Packet
   */
  private static final long serialVersionUID = 1L;
  
  private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
    aInputStream.defaultReadObject();
  }

  private void writeObject(
    ObjectOutputStream aOutputStream
  ) throws IOException {
    aOutputStream.defaultWriteObject();
  }
}