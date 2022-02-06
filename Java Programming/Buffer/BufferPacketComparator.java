/*
 * Dateiname: BufferPacketComparator.java
 * Erik Autenrieth, 9036029
 */

import java.util.Comparator;

public class BufferPacketComparator implements Comparator<Buffer<Packet>> {

    public int compare(Buffer o1, Buffer o2) {
        return o1.getSizePackets()-o2.getSizePackets();
    }
}