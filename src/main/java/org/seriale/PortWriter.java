package org.seriale;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class PortWriter implements SerialPortEventListener {
    private SerialPort serialPort;
    private String  daScrivere;
    public PortWriter(SerialPort serialPort) {
        this.serialPort = serialPort;
    }

    public void scrivi(String _text){
        daScrivere = _text;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        System.out.println("started");
        if (event.isRXCHAR() && event.getEventValue() > 0) {
            try {

                serialPort.openPort();
                serialPort.writeBytes(daScrivere.getBytes());
                serialPort.closePort();

            } catch (SerialPortException ex) {
                System.out.println("Error in receiving string from COM-port: " + ex);
            }
        }
    }

}


