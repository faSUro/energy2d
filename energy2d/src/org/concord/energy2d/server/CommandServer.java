package org.concord.energy2d.server;

import org.concord.energy2d.commandmanagement.CommandProcessor;
import org.concord.energy2d.system.System2D;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class CommandServer extends Thread {

    private System2D _s2d;
    private DatagramSocket socket;

    public CommandServer(System2D s2d, int port) throws SocketException {
        s2d = s2d;
        socket = new DatagramSocket(port);
    }

    @Override
    public void run() {
        CommandProcessor cmdProcessor = new CommandProcessor(_s2d);

        while (true) {
            try {
                byte[] buffer = new byte[4194304]; //max data: 4MB
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                String command = new String(buffer, 0, request.getLength());
                String answer = cmdProcessor.processCommand(command);
                if (!answer.equals(null)) {
                    InetAddress clientAddress = request.getAddress();
                    int clientPort = request.getPort();

                    byte[] bResult = answer.getBytes();
                    DatagramPacket response = new DatagramPacket(bResult, bResult.length, clientAddress, clientPort);
                    socket.send(response);
                };
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
