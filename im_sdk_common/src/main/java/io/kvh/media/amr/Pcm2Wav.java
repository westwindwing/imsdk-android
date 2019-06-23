package io.kvh.media.amr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Pcm2Wav {
    static final /* synthetic */ boolean $assertionsDisabled = (!Pcm2Wav.class.desiredAssertionStatus());

    public void convertAudioFiles(File src, File target) throws Exception {
        int size;
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(target);
        byte[] buf = new byte[4096];
        int PCMSize = 0;
        for (size = fis.read(buf); size != -1; size = fis.read(buf)) {
            PCMSize += size;
        }
        fis.close();
        WaveHeader header = new WaveHeader();
        header.fileLength = PCMSize + 36;
        header.FmtHdrLeth = 16;
        header.BitsPerSample = (short) 16;
        header.Channels = (short) 1;
        header.FormatTag = (short) 1;
        header.SamplesPerSec = 8000;
        header.BlockAlign = (short) ((header.Channels * header.BitsPerSample) / 8);
        header.AvgBytesPerSec = header.BlockAlign * header.SamplesPerSec;
        header.DataHdrLeth = PCMSize;
        byte[] h = header.getHeader();
        if ($assertionsDisabled || h.length == 44) {
            fos.write(h, 0, h.length);
            fis = new FileInputStream(src);
            for (size = fis.read(buf); size != -1; size = fis.read(buf)) {
                fos.write(buf, 0, size);
            }
            fis.close();
            fos.close();
            return;
        }
        throw new AssertionError();
    }
}
