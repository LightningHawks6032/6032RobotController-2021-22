package org.firstinspires.ftc.teamcode6032.comms.data;

import org.firstinspires.ftc.teamcode6032.util.Utf8Len;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class PacketParam {
    public static final Codec codec = new Codec();

    /** The data type of the parameter. */
    public final Type type;
    /** Data of parameter (float) */
    public final float floatData;
    /** Data of parameter (int) */
    public final int intData;
    /** Data of parameter (string) */
    public final String stringData;

    /** General constructor referenced by public constructors. */
    private PacketParam(Type typeIn, int intDataIn, float floatDataIn, String stringDataIn) {
        type = typeIn;
        intData = intDataIn;
        floatData = floatDataIn;
        stringData = stringDataIn;
    }

    /** Create a packet parameter with type of int. */
    public PacketParam(int dataIn) { this(Type.FLOAT,dataIn,0,null); }
    /** Create a packet parameter with type of float. */
    public PacketParam(float dataIn) { this(Type.FLOAT,0,dataIn,null); }
    /** Create a packet parameter with type of string. */
    public PacketParam(String dataIn) { this(Type.FLOAT,0,0,dataIn); }

    /** The type of data the param contains (for encoding). */
    public enum Type { FLOAT, INT, STRING }

    protected static class Codec implements BufferCodec<PacketParam> {

        @Override
        public void encode(PacketParam data, DataOutputStream buf) throws IOException {
            buf.writeChar((int) TypeCharConverter.paramTypeChar(data.type));
            switch (data.type) {
                case FLOAT:  buf.writeFloat(data.floatData ); break;
                case INT:    buf.writeInt  (data.intData   ); break;
                case STRING:
                    buf.writeInt(Utf8Len.calculate(data.stringData));
                    buf.writeUTF(data.stringData);
                    break;
                default: throw new IllegalStateException("Cannot encode PacketParam without type.");
            }
        }

        @Override
        public PacketParam decode(DataInputStream buf) throws IOException {
            Type type = TypeCharConverter.paramTypeEnum(buf.readChar());
            if (type != null) {
                switch (type) {
                    case FLOAT: return new PacketParam(buf.readFloat());
                    case INT: return new PacketParam(buf.readInt());
                    case STRING:
                        // To read a string, first read in the length and then get that many bytes
                        // and parse with utf-8 format.
                        int byteLen = buf.readInt();
                        byte[] dataIn = new byte[byteLen];
                        //noinspection ResultOfMethodCallIgnored
                        buf.read(dataIn);
                        return new PacketParam(new String(dataIn, StandardCharsets.UTF_8));
                }
            }
            throw new IllegalArgumentException("Cannot decode PacketParam without valid type.");
        }
    }
}
