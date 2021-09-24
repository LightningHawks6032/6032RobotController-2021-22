package org.firstinspires.ftc.teamcode6032.comms.data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Encoder and decoder for classes into a buffer for sending.
 * @param <T> The type of object to en/decode.
 */
public interface BufferCodec<T> {
    void encode(T data, DataOutputStream buf) throws IOException;
    T decode(DataInputStream buf) throws IOException;
}
