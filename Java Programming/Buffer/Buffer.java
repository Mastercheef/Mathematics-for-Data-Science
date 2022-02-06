/*
 * Dateiname: Buffer.java
 * Erik Autenrieth, 9036029
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Buffer <T extends Packet> implements BufferIF<T>, Iterable<T> {

    private class BufferIterator implements Iterator<T>{
        int i = 0;

        public boolean hasNext(){
            return size > i;
        }    //Is true, wenn Größe größer ist als Index i

        @Override
        public T next() {
            return get(i++);
        }  //Holt sich das nächste Element über die Methode der Buffer Klasse
    }

    @Override
    public Iterator<T> iterator() {
        return new BufferIterator();
    }  //Returnt einen neuen BufferIterator

    private class Node{   //Private Klasse zum Speichern von Referenzen auf das nächste Element sowie das Packet
        T p;
        Node next;

        public Node(T p){
            this.p = p;
        }
    }

    private Node head;  //Anfangsknoten
    private int size;   //Anzahl der gespeicherten Packages
    private int capacity;   //Maximale Kapazität in Bytes

    public Buffer(int capacity){
        this.capacity = capacity;
    }      //Konstruktor nimmt maximale Kapazität in Byte als Parameter

    @Override
    public int getSizePackets() {
        return size;
    }        //Anzahl der Packete /size

    @Override
    public int getSizeBytes() {        //Anzahl der Summe aller Bytes von den Packeten
        int bytes = 0;                //Initliasiert mit 0 und iteriert über alle Nodes um die Bytes aufzusummieren
        Node current = this.head;      //Hätte es auch mit dem iterator implementieren können, allerdings kam die Implementation eines
        //Iterators erst später
        while (current != null){
            bytes += current.p.getSize();
            current = current.next;
        }
        return bytes;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }    //Gibt maximale Kapazität wieder in Bytes

    @Override
    public Boolean insertPacket(T p) {         //Fügt Packet hinzu, es gibt drei Fälle, die beachtet werden müssen:
        if (p.getSize()+getSizeBytes() > capacity)   //Buffer voll?
            return false;

        if (head == null){                          //Anfangsknoten definiert?
            head = new Node(p);
            size++;
            return true;
        }

        Node newNode = new Node(p);                 //Ansonsten füge neuen Knoten normal hinzu
        Node current = head;
        while(current.next != null){
            current = current.next;
        }
        current.next = newNode;
        size++;
        return true;
    }

    @Override
    public T removePacket() {               //Drei Bedingungen müssen überprüft werden:
        T item;
        if (head == null)                         //Anfangsknoten leer? Wenn ja dann wirst NoSuchElementException
            throw new NoSuchElementException();
        if(head.next == null) {                   //head.next ist leer? Dann lösche Anfangsknoten
            item = head.p;
            head = null;
            size--;
            return item;
        }

        Node current = head;                    //Ansonsten lösche Knoten wie folgt:
        while(current.next.next != null)        //Iteriere solange, bis über übernächstes Element leer ist
            current = current.next;
        item = current.next.p;                  //speichere übernächstes Element
        current.next = null;                    //lösche übernächstes Element
        return item;
    }

    @Override
    public T get(int pos) {                      //Kopf leer? Dann werfe Fehlermeldung
        if(head == null)
            throw new NoSuchElementException();
        Node current = head;                    //Ansonsten, solange pos über 1 ist, iteriere über Buffer
        while(pos-- > 1)                        //pos wird dabei immer um 1 reduziert
            current = current.next;

        if(current == null)                     //Handelt es sich um ein leeres Node soll Fehlermeldung ausgeworfen wernde
            throw new NoSuchElementException();

        return current.p;                       //Ansonsten gebe das Objekt zurück
    }
    //Last in first out = Stack

}
