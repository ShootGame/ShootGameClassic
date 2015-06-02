/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.server;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 *
 * @author Aleksander
 */
public abstract class TargetServer extends Server implements ITarget {
    public static final int PORT = 25565;
    public static final int TIMEOUT = 4000;
    
    private final String address;
    private int players = 0, slots = 0;
    private final int port;
    private final boolean publicy;
    
    public TargetServer(String address, String id, String name, boolean publicy) throws NumberFormatException {
        super(id, name);
        String[] split = address.split(":");
        this.address = split[0];
        this.publicy = publicy;
        
        if (split.length > 1) {
            this.port = Integer.parseInt(split[1]);
        } else {
            this.port = PORT;
        }
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public int getPlayers() {
        return this.players;
    }
    
    public int getPort() {
        return this.port;
    }
    
    public String getSetting(String setting) {
        return this.getSetting(setting, null);
    }
    
    public String getSetting(String setting, String def) {
        String type = "minecraft";
        if (this instanceof ArcadeTarget) {
            type = "arcade";
        } else if (this instanceof LobbyTarget) {
            type = "lobby";
        }
        
        return Servers.getConfiguration().getString(type + "." + this.getID() + "." + setting, def);
    }
    
    public int getSlots() {
        return this.slots;
    }
    
    public boolean isOnline() {
        return !(this.players == 0 && this.slots == 0);
    }
    
    public boolean isPublic() {
        return this.publicy;
    }
    
    public void ping() {
        Socket socket = null;
        DataInputStream input = null;
        DataOutputStream output = null;
        ByteArrayOutputStream array = null;
        DataOutputStream data = null;
        
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(this.getAddress(), this.getPort()), TIMEOUT);
            
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            array = new ByteArrayOutputStream();
            data = new DataOutputStream(array);
            
            // handshake - read first -> http://wiki.vg/Server_List_Ping
            data.writeByte(0x00); // packet ID
            this.writeInt(data, 4); // Protocol
            this.writeInt(data, this.getAddress().length());
            data.writeBytes(this.getAddress());
            data.writeShort(this.getPort());
            this.writeInt(data, 1); // for get the status
            
            this.writeInt(output, array.size());
            output.write(array.toByteArray());
            output.writeByte(0x01); // Size of packet
            output.writeByte(0x00);
            
            // response
            this.readInt(input);
            
            int id = this.readInt(input);
            if (id == -1) {
                throw new IOException("Server prematurely ended stream.");
            } else if (id != 0x00) {
                throw new IOException("Server returned invalid packet.");
            }
            
            int length = this.readInt(input);
            if (length == -1) {
                throw new IOException("Server prematurely ended stream.");
            } else if (length == 0) {
                throw new IOException("Server returned unexpected value.");
            }
            
            byte[] bytes = new byte[length];
            input.readFully(bytes);
            this.read(new String(bytes, Charset.forName("UTF-8")));
            
            // close
            data.close();
            array.close();
            output.close();
            input.close();
            socket.close();
        } catch (IOException ex) {
            this.setPlayers(0);
            this.setSlots(0);
        } finally {
            try {
                if (data != null) data.close();
                if (array != null) array.close();
                if (output != null) output.close();
                if (input != null) input.close();
                if (socket != null) socket.close();
            } catch (IOException ex) {
                this.setPlayers(0);
                this.setSlots(0);
            }
        }
    }
    
    public void setPlayers(int players) {
        this.players = players;
    }
    
    public void setSlots(int slots) {
        this.slots = slots;
    }
    
    private int readInt(DataInputStream in) throws IOException {
        int i = 0, j = 0;
        while (true) {
            int k = in.readByte();
            i |= (k & 0x7F) << j++ * 7;
            if (j > 5) {
                throw new RuntimeException("VarInt too big");
            }
            if ((k & 0x80) != 128) {
                break;
            }
        }
        return i;
    }
    
    private void writeInt(DataOutputStream output, int i) throws IOException {
        while (true) {
            if ((i & 0xFFFFFF80) == 0) {
                output.writeByte(i);
                return;
            }
            output.writeByte(i & 0x7F | 0x80);
            i >>>= 7;
        }
    }
}
