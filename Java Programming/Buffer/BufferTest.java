/*
 * Dateiname: BufferTest.java
 * Erik Autenrieth, 9036029
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Stack;

public class BufferTest {


    @Test
    public void randomSizeIn(){
        Buffer<Packet> buffer = new Buffer<Packet>(1000);
        int size = 0;
        int bytes = 0;
        for (int i = 0; i < 5; i++){                         //Füge 5 Packete mit zufälligen Größen hinzu und überprüft nach jedem
            int random = new Random().nextInt(30);     //hinzufügen, ob Werte stimmen (Packet Anzahl und Gesamt Bytes)
            size++;
            bytes += random;
            Packet p = new HighPriorityPacket(random);
            buffer.insertPacket(p);
            Assertions.assertEquals(size, buffer.getSizePackets());      //Prüft, ob Size stimmt
            Assertions.assertEquals(bytes, buffer.getSizeBytes());       //Prüft, ob Gesamt Bytes stimmen
        }
    }

    @Test
    public void exceptionForPos(){
        Buffer<Packet> buffer = new Buffer<Packet>(1000);
        buffer.insertPacket(new HighPriorityPacket(2));
        Assertions.assertThrows(NoSuchElementException.class, ()->buffer.get(2));      //Schaut nach, ob eine nicht leere Liste eine Fehlermeldung auswirft
    }                                                                                  //Listen Index leer ist.

    @Test
    public void fiveInFiveOut(){                           //Ich wusste nicht, ob ich die Stackklasse verwenden durfte, hab jetzt nicht gelesen, dass es nicht
        Stack<Packet> stack = new Stack<>();               //erlaubt ist. Buffer ist ja im Prinzip ein Stack
        Buffer<Packet> buffer = new Buffer<Packet>(1000);
        for (int i = 0; i < 5; i++){                         //Füge 5 zufällige Packete hinzu
            int random = new Random().nextInt(30);
            Packet p = new HighPriorityPacket(random);
            stack.add(p);                                   //Fügt dem Stack ein Element hinzu
            buffer.insertPacket(p);                         //Fügt dem Buffer ein Element hinzu
            Assertions.assertEquals(stack.get(i), buffer.get(i+1));   //Schaut nach, ob die Elemente in dem jeweiligen Index gleich sind
        }

        for(int i = 0; i <5; i++){
            Assertions.assertEquals(stack.pop(), buffer.removePacket());      //Entferne ein Element nach dem anderen und schau nach, ob diese gleich sind
        }
    }

    @Test
    public void insertFull(){
        Buffer<Packet> buffer = new Buffer<>(9);           //Füge 4 Packete hinzu, wobei Kapazität beim vierten Packet überschritten wird
        buffer.insertPacket(new HighPriorityPacket(3));
        buffer.insertPacket(new HighPriorityPacket(3));
        buffer.insertPacket(new HighPriorityPacket(3));
        Assertions.assertFalse(buffer.insertPacket(new HighPriorityPacket(3)));  //Prüft auf Boolean return, soll False sein, beim letzten Packet
        Assertions.assertEquals(3, buffer.getSizePackets());       //Es sind drei Packete, da dass vierte die Kapazität überschreitet
        Assertions.assertEquals(9, buffer.getSizeBytes());    //Size soll neun sein, da das vierte Paket nicht aufgenommen wird
    }
}