/*/
# Packet format:    *ALWAYS USE BIG-ENDIAN

    command name format: UPPER_CASE, LEN=4;
    param format: `f{encoded float}`|`s{int for length}HelloWorld`|`i{encoded int}`; {f:float,s:string,i:integer}
    id format: encoded random int not 0

    Requests:
        `>{command}{id|0}{nParams}{param}{param}`

    Response:
        `<{command}{id}{nParams}{param}{param}`
/*/

package org.firstinspires.ftc.teamcode6032.comms.data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * A class representing a single command, request, or response object from the app.
 */
public class Packet {
    public static final Codec codec = new Codec();
    private static final Random idRand = new Random();

    /** Id of the request this packet is linked to. */
    public final int requestId;
    /** Parameters for the command */
    public final PacketParam[] params;
    /** If the packet is a request or response. */
    public final Type type;
    /** The command the packet is using. */
    public final Command command;


    /** Construct a new instance of Packet */
    public Packet(Type typeIn, Command commandIn, PacketParam[] paramsIn, int requestIdIn) {
        type = typeIn;
        command = commandIn;
        requestId = requestIdIn;
        params = paramsIn;
    }
    /** Construct a request packet */
    public Packet(Command commandIn, PacketParam[] paramsIn) {
        this(Type.REQUEST,commandIn,paramsIn,idRand.nextInt());
    }
    /** Construct a response packet to request #`requestIdIn`. */
    public Packet(int requestIdIn, Command commandIn, PacketParam[] paramsIn) {
        this(Type.RESPONSE,commandIn,paramsIn,requestIdIn);
    }

    /** Enum for if the packet is a request or response. */
    public enum Type { REQUEST, RESPONSE }

    protected static class Codec implements BufferCodec<Packet> {

        @Override
        public void encode(Packet data, DataOutputStream buf) throws IOException {
            // Encode type.
            if (data.type == null)
                throw new IllegalStateException("Cannot encode Packet without type.");
            else buf.writeChar((int) TypeCharConverter.packetTypeChar(data.type));
            // Encode command
            buf.writeChars(data.command.id);
            // Encode request id
            buf.writeInt(data.requestId);
            // Write int for number of params
            buf.writeInt(data.params.length);
            for (int i = 0; i < data.params.length; i++)
                PacketParam.codec.encode(data.params[i],buf);
        }

        @Override
        public Packet decode(DataInputStream buf) throws IOException {
            // Decode type.
            Type type = TypeCharConverter.packetTypeEnum(buf.readChar());
            if (type == null) throw new IllegalStateException("Cannot decode Packet without type.");
            // Decode command
            byte[] cmdBytesIn = new byte[4];
            //noinspection ResultOfMethodCallIgnored
            buf.read(cmdBytesIn);
            Command command = Command.get(new String(cmdBytesIn, StandardCharsets.US_ASCII));
            // Decode request id
            int requestId = buf.readInt();
            // Decode params
            int nParams = buf.readInt();
            PacketParam[] params = new PacketParam[nParams];
            for (int i = 0; i < nParams; i++)
                params[i] = PacketParam.codec.decode(buf);
            // Construct object and return.
            return new Packet(type,command, params, requestId);
        }
    }
}
