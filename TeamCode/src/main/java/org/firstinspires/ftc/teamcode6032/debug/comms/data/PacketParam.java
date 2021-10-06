package org.firstinspires.ftc.teamcode6032.debug.comms.data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

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

    /** Create a packet parameter with null type. */
    public PacketParam() { this(null,0,0,null); }
    /** Create a packet parameter with type of int. */
    public PacketParam(int dataIn) { this(Type.INT,dataIn,0,null); }
    /** Create a packet parameter with type of float. */
    public PacketParam(float dataIn) { this(Type.FLOAT,0,dataIn,null); }
    /** Create a packet parameter with type of string. */
    public PacketParam(String dataIn) { this(Type.STRING,0,0,dataIn); }

    /** The type of data the param contains (for encoding). */
    public enum Type { FLOAT, INT, STRING }

    protected static class Codec implements BufferCodec<PacketParam> {

        @Override
        public void encode(PacketParam data, DataOutputStream buf) throws IOException {
            buf.writeByte(TypeCharConverter.paramTypeChar(data.type));
            switch (data.type) {
                case FLOAT:  buf.writeFloat(data.floatData ); break;
                case INT:    buf.writeInt  (data.intData   ); break;
                case STRING: buf.writeUTF  (data.stringData); break;
                default: throw new IllegalStateException("Cannot encode PacketParam without type.");
            }
        }

        @Override
        public PacketParam decode(DataInputStream buf) throws IOException {
            Type type = TypeCharConverter.paramTypeEnum((char)buf.readByte());
            if (type != null) {
                switch (type) {
                    case FLOAT: return new PacketParam(buf.readFloat());
                    case INT: return new PacketParam(buf.readInt());
                    case STRING:
                        // Read string data
                        return new PacketParam(buf.readUTF());
                }
            }
            throw new IllegalArgumentException("Cannot decode PacketParam without valid type.");
        }
    }
}
