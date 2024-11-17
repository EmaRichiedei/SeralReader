package org.seriale;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class PortReader implements SerialPortEventListener {
    private SerialPort serialPort;
    private String datiLetti=null;
    public PortReader(SerialPort serialPort) {
        this.serialPort = serialPort;
    }

    /**
     * Evento che rimane in ascolto della Porta COM X fino a che non riceve una stringa di testo.
     * @param event
     */
    @Override
    public void serialEvent(SerialPortEvent event) {
        System.out.println("started");
        if (event.isRXCHAR() && event.getEventValue() > 0) {
            try {
                 datiLetti = serialPort.readString(event.getEventValue());
                //System.out.println("Received response: " + receivedData);

            } catch (SerialPortException ex) {
                System.out.println("Error in receiving string from COM-port: " + ex);
            }
        }

    }

    /**
     * Restituisce quanto letto dalla porta seriale
     * @return
     */
    public String getDatiLetti(){
        return datiLetti;
    }
}