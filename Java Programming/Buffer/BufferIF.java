/*
 * Dateiname: BufferIF.java
 * Erik Autenrieth, 9036029
 */

public interface BufferIF<T> {

    public int getSizePackets();   // liefert den aktuellen Füllstand des Puffers ( Anzahl Pakete )

    public int getSizeBytes();    // liefert den aktuellen Füllstand des Puffers ( Anzahl Bytes aller Pakete )

    public int getCapacity();    // liefert die maximale Kapazität des Puffers ( in Bytes oder Paketen , je nach gefordertem Konstruktor )

    public Boolean insertPacket(T p);   // fügt p in den Puffer ein und liefert TRUE wenn p eingefügt werden konnte , FALSE sonst

    public T removePacket();   // löscht ein Paket aus dem Puffer und gibt es zurück oder wirft NoSuchElementException

    public T get(int pos);
}