/*
 * Dateiname: BufferHelperTest.java
 * Erik Autenrieth, 9036029
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BufferHelperTest {

    @Test
    public void fillTest(){
        Buffer<Packet> buffer = new Buffer<>(100);
        Packet[] array = new Packet[6];                 //Array hat Länge 6, letztes Feld wird nachher nicht gef체llt

        for(int i = 0; i < 5; i++){                      //F체ge dem Buffer 5 Packete hinzu
            Packet packet = new HighPriorityPacket(5);
            buffer.insertPacket(packet);
        }

        BufferHelper.copyBuffer2Array(buffer, array);    //Kopiere Pakete r체ber

        for(int i = 0; i < array.length-1; i++){        //Ersten 5 Felder sollen nicht null sein
            Assertions.assertNotNull(array[i]);
        }

        Assertions.assertNull(array[5]);               //letztes Feld soll leer sein
    }
}