

package org.seriale;

import jssc.*;

public class Serial {

    public static void main(String[] args) {
        String[] portNames = null;
        portNames = SerialPortList.getPortNames();
        for (String string : portNames) {
            System.out.println(string);
        }

        if (portNames.length == 0) {
            System.out.println("There are no serial-ports");
        } else {

            SerialPort serialPort = new SerialPort("COM1");
            try {

                serialPort.openPort();
                serialPort.setParams(SerialPort.BAUDRATE_9600,SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                System.out.println("In Attesa");
                Thread.sleep(3000);

                serialPort.setParams(SerialPort.BAUDRATE_9600,SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);

                PortWriter port = new PortWriter(serialPort);
                port.scrivi("Culo");

                serialPort.addEventListener(port, SerialPort.MASK_RXCHAR);

            } catch (Exception e) {
                System.out.println("There are an error on writing string to port т: " + e);
            }
        }
    }
}
