/*
 * Dateiname: BufferHelper.java
 * Erik Autenrieth, 9036029
 */

public class BufferHelper {

    private BufferHelper(){} //Verhindert instaziierung

    public static <T extends Packet> void copyBuffer2Array(Buffer<T> buffer, T[] array){
        int i = 0;
        if(array.length < buffer.getSizePackets())     //Ist das Array zuklein soll Fehlermeldung geworfen werden
            throw new IllegalArgumentException();

        for(T item: buffer){        //Nutzung des implementierten Iterators und Zuweisung an jeweilige Stelle
            array[i++] = item;
        }
    }
}