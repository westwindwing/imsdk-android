package io.kvh.media.amr;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class WaveHeader {
    public int AvgBytesPerSec;
    public short BitsPerSample;
    public short BlockAlign;
    public short Channels;
    public char[] DataHdrID = new char[]{'d', 'a', 't', 'a'};
    public int DataHdrLeth;
    public char[] FmtHdrID = new char[]{'f', 'm', 't', ' '};
    public int FmtHdrLeth;
    public short FormatTag;
    public int SamplesPerSec;
    public final char[] fileID = new char[]{'R', 'I', 'F', 'F'};
    public int fileLength;
    public char[] wavTag = new char[]{'W', 'A', 'V', 'E'};

    public byte[] getHeader() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        WriteChar(bos, this.fileID);
        WriteInt(bos, this.fileLength);
        WriteChar(bos, this.wavTag);
        WriteChar(bos, this.FmtHdrID);
        WriteInt(bos, this.FmtHdrLeth);
        WriteShort(bos, this.FormatTag);
        WriteShort(bos, this.Channels);
        WriteInt(bos, this.SamplesPerSec);
        WriteInt(bos, this.AvgBytesPerSec);
        WriteShort(bos, this.BlockAlign);
        WriteShort(bos, this.BitsPerSample);
        WriteChar(bos, this.DataHdrID);
        WriteInt(bos, this.DataHdrLeth);
        bos.flush();
        byte[] r = bos.toByteArray();
        bos.close();
        return r;
    }

    private void WriteShort(ByteArrayOutputStream bos, int s) throws IOException {
        bos.write(new byte[]{(byte) ((s << 16) >> 24), (byte) ((s << 24) >> 24)});
    }

    private void WriteInt(ByteArrayOutputStream bos, int n) throws IOException {
        bos.write(new byte[]{(byte) (n >> 24), (byte) ((n << 8) >> 24), (byte) ((n << 16) >> 24), (byte) ((n << 24) >> 24)});
    }

    private void WriteChar(ByteArrayOutputStream bos, char[] id) {
        for (char c : id) {
            bos.write(c);
        }
    }
}
