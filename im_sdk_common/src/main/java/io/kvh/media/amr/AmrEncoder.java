package io.kvh.media.amr;

public class AmrEncoder {

    public enum Mode {
        MR475,
        MR515,
        MR59,
        MR67,
        MR74,
        MR795,
        MR102,
        MR122,
        MRDTX,
        N_MODES
    }

    public static native int encode(int i, short[] sArr, byte[] bArr);

    public static native void exit();

    public static native void init(int i);

    public static native void reset();

    static {
        System.loadLibrary("amr-codec");
    }
}
