/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arduinoserialio;

// CODE FROM: http://silveiraneto.net/2009/03/01/arduino-and-java/
// SPIEGAZIONE: http://en.wikibooks.org/wiki/Serial_Programming/Serial_Java
/**
 *
 * @author Gio
 */
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.io.InputStream;
import java.io.OutputStream;

public class ArduinoSerialIO {

    static InputStream input;
    static OutputStream output;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        char car=' ';

        System.out.println("Using port: " + "COM21");
        CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier(
                "COM16");

        SerialPort port = null;
        try {
            port = (SerialPort) portId.open(
                    "ArduinoSerialIO", // Name of the application asking for the port 
                    10000 // Wait max. 10 sec. to acquire port
                    );
        } catch (Exception e) {
            System.err.println("Port already in use: " + e);
            System.exit(1);
        }
        input = port.getInputStream();
        output = port.getOutputStream();
        port.setSerialPortParams(9600,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);
        while (car!='.') {
            while (input.available() > 0) {
                car = (char) input.read();
                System.out.print(car);
            }
        }
        input.close();
    }
}
