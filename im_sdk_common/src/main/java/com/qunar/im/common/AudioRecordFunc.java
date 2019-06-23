package com.qunar.im.common;

import android.media.AmrInputStream;
import android.media.AudioRecord;
import android.os.Build.VERSION;
import com.qunar.im.base.common.BackgroundExecutor;
import io.kvh.media.amr.AmrEncoder;
import io.kvh.media.amr.AmrEncoder.Mode;
import io.kvh.media.amr.Pcm2Wav;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class AudioRecordFunc {
    public static final int AUDIO_SAMPLE_RATE = 8000;
    private static final String PCM_FILE_PATH = "PCM.tmp";
    private static final String WAV_FILE_PATH = "WAV.tmp";
    private static final byte[] header = new byte[]{(byte) 35, (byte) 33, (byte) 65, (byte) 77, (byte) 82, (byte) 10};
    private static volatile AudioRecordFunc mInstance;
    File amrFile;
    private AudioRecord audioRecord;
    private int bufferSizeInBytes = 0;
    private int maxAmplitude = 0;
    File rawFile;
    File wavFile;

    private AudioRecordFunc() {
    }

    public static synchronized AudioRecordFunc getInstance() {
        AudioRecordFunc audioRecordFunc;
        synchronized (AudioRecordFunc.class) {
            if (mInstance == null) {
                mInstance = new AudioRecordFunc();
            }
            audioRecordFunc = mInstance;
        }
        return audioRecordFunc;
    }

    public int getMaxAmplitude() {
        return this.maxAmplitude;
    }

    public void prepareFile(String filePath) {
        this.amrFile = new File(filePath);
        this.rawFile = new File(CommonConfig.globalContext.getCacheDir(), "PCM.tmp");
        this.wavFile = new File(CommonConfig.globalContext.getCacheDir(), "WAV.tmp");
        if (this.rawFile.exists()) {
            this.rawFile.delete();
        }
        if (this.wavFile.exists()) {
            this.wavFile.delete();
        }
        try {
            this.amrFile.createNewFile();
            this.rawFile.createNewFile();
            this.wavFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startRecordAndFile(String filePath) {
        prepareFile(filePath);
        creatAudioRecord();
        if (VERSION.SDK_INT >= 16) {
            setNoiseSuppress(this.audioRecord.getAudioSessionId());
        }
        this.audioRecord.startRecording();
        BackgroundExecutor.execute(new Runnable() {
            public void run() {
                AudioRecordFunc.this.writeDateTOFile();
            }
        });
    }

    private void setNoiseSuppress(int sessionId) {
        NoiseSuppressorHelper.setAcousticEchoCanceler(sessionId);
        NoiseSuppressorHelper.setAutomaticGainControl(sessionId);
        NoiseSuppressorHelper.setNoiseSuppressor(sessionId);
    }

    public void cancelRecord() {
        if (this.audioRecord != null) {
            this.audioRecord.stop();
            this.audioRecord.release();
            this.audioRecord = null;
        }
        this.rawFile.delete();
        this.wavFile.delete();
        this.amrFile.delete();
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0073 A:{SYNTHETIC, Splitter:B:24:0x0073} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0084 A:{SYNTHETIC, Splitter:B:31:0x0084} */
    public void stopRecordAndFile() {
        /*
        r6 = this;
        r3 = r6.audioRecord;
        if (r3 == 0) goto L_0x0031;
    L_0x0004:
        r3 = "stopRecord";
        com.qunar.im.base.util.LogUtil.d(r3);
        r3 = r6.audioRecord;
        r3.stop();
        r3 = r6.audioRecord;
        r3.release();
        r1 = 0;
        r3 = android.os.Build.VERSION.SDK_INT;	 Catch:{ IOException -> 0x006d }
        r4 = 24;
        if (r3 < r4) goto L_0x0032;
    L_0x001a:
        r6.pcm2wal();	 Catch:{ IOException -> 0x006d }
        r3 = r6.wavFile;	 Catch:{ IOException -> 0x006d }
        r4 = r6.amrFile;	 Catch:{ IOException -> 0x006d }
        r6.convertAMR(r3, r4);	 Catch:{ IOException -> 0x006d }
    L_0x0024:
        if (r1 == 0) goto L_0x0029;
    L_0x0026:
        r1.close();	 Catch:{ IOException -> 0x0068 }
    L_0x0029:
        r3 = r6.rawFile;
        r3.delete();
    L_0x002e:
        r3 = 0;
        r6.audioRecord = r3;
    L_0x0031:
        return;
    L_0x0032:
        r2 = new java.io.BufferedOutputStream;	 Catch:{ IOException -> 0x006d }
        r3 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x006d }
        r4 = r6.amrFile;	 Catch:{ IOException -> 0x006d }
        r5 = 1;
        r3.<init>(r4, r5);	 Catch:{ IOException -> 0x006d }
        r2.<init>(r3);	 Catch:{ IOException -> 0x006d }
        r3 = 35;
        r2.write(r3);	 Catch:{ IOException -> 0x0095, all -> 0x0092 }
        r3 = 33;
        r2.write(r3);	 Catch:{ IOException -> 0x0095, all -> 0x0092 }
        r3 = 65;
        r2.write(r3);	 Catch:{ IOException -> 0x0095, all -> 0x0092 }
        r3 = 77;
        r2.write(r3);	 Catch:{ IOException -> 0x0095, all -> 0x0092 }
        r3 = 82;
        r2.write(r3);	 Catch:{ IOException -> 0x0095, all -> 0x0092 }
        r3 = 10;
        r2.write(r3);	 Catch:{ IOException -> 0x0095, all -> 0x0092 }
        r3 = r6.rawFile;	 Catch:{ IOException -> 0x0095, all -> 0x0092 }
        r3 = r3.getAbsolutePath();	 Catch:{ IOException -> 0x0095, all -> 0x0092 }
        r6.pcm2amr(r2, r3);	 Catch:{ IOException -> 0x0095, all -> 0x0092 }
        r1 = r2;
        goto L_0x0024;
    L_0x0068:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0029;
    L_0x006d:
        r0 = move-exception;
    L_0x006e:
        r0.printStackTrace();	 Catch:{ all -> 0x0081 }
        if (r1 == 0) goto L_0x0076;
    L_0x0073:
        r1.close();	 Catch:{ IOException -> 0x007c }
    L_0x0076:
        r3 = r6.rawFile;
        r3.delete();
        goto L_0x002e;
    L_0x007c:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0076;
    L_0x0081:
        r3 = move-exception;
    L_0x0082:
        if (r1 == 0) goto L_0x0087;
    L_0x0084:
        r1.close();	 Catch:{ IOException -> 0x008d }
    L_0x0087:
        r4 = r6.rawFile;
        r4.delete();
        throw r3;
    L_0x008d:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0087;
    L_0x0092:
        r3 = move-exception;
        r1 = r2;
        goto L_0x0082;
    L_0x0095:
        r0 = move-exception;
        r1 = r2;
        goto L_0x006e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qunar.im.common.AudioRecordFunc.stopRecordAndFile():void");
    }

    private void creatAudioRecord() {
        this.bufferSizeInBytes = AudioRecord.getMinBufferSize(8000, 16, 2);
        if (this.bufferSizeInBytes % 160 > 0) {
            this.bufferSizeInBytes = ((this.bufferSizeInBytes / 160) + 1) * 160;
        }
        this.audioRecord = new AudioRecord(1, 8000, 16, 2, this.bufferSizeInBytes * 2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x0006 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0059 A:{SYNTHETIC, Splitter:B:24:0x0059} */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0006 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0065 A:{SYNTHETIC, Splitter:B:30:0x0065} */
    private void writeDateTOFile() {
        /*
        r10 = this;
        r9 = 1;
        r6 = r10.bufferSizeInBytes;
        r1 = new short[r6];
        r5 = 0;
    L_0x0006:
        r6 = 3;
        r7 = r10.audioRecord;
        r7 = r7.getRecordingState();
        if (r6 != r7) goto L_0x006e;
    L_0x000f:
        r6 = r10.audioRecord;
        r7 = r10.bufferSizeInBytes;
        r5 = r6.read(r1, r5, r7);
        if (r5 <= 0) goto L_0x004a;
    L_0x0019:
        r6 = 2;
        if (r5 <= r6) goto L_0x002d;
    L_0x001c:
        r6 = 0;
        r6 = r1[r6];
        r6 = r6 & 255;
        r6 = r6 << 8;
        r7 = r1[r9];
        r0 = r6 | r7;
        r6 = java.lang.Math.abs(r0);
        r10.maxAmplitude = r6;
    L_0x002d:
        r3 = 0;
        r4 = new java.io.BufferedOutputStream;	 Catch:{ IOException -> 0x0053 }
        r6 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x0053 }
        r7 = r10.rawFile;	 Catch:{ IOException -> 0x0053 }
        r8 = 1;
        r6.<init>(r7, r8);	 Catch:{ IOException -> 0x0053 }
        r4.<init>(r6);	 Catch:{ IOException -> 0x0053 }
        r6 = r10.short2ByteArray(r1);	 Catch:{ IOException -> 0x0072, all -> 0x006f }
        r4.write(r6);	 Catch:{ IOException -> 0x0072, all -> 0x006f }
        r4.flush();	 Catch:{ IOException -> 0x0072, all -> 0x006f }
        if (r4 == 0) goto L_0x004a;
    L_0x0047:
        r4.close();	 Catch:{ IOException -> 0x004e }
    L_0x004a:
        if (r5 >= 0) goto L_0x0006;
    L_0x004c:
        r5 = 0;
        goto L_0x0006;
    L_0x004e:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x004a;
    L_0x0053:
        r2 = move-exception;
    L_0x0054:
        r2.printStackTrace();	 Catch:{ all -> 0x0062 }
        if (r3 == 0) goto L_0x004a;
    L_0x0059:
        r3.close();	 Catch:{ IOException -> 0x005d }
        goto L_0x004a;
    L_0x005d:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x004a;
    L_0x0062:
        r6 = move-exception;
    L_0x0063:
        if (r3 == 0) goto L_0x0068;
    L_0x0065:
        r3.close();	 Catch:{ IOException -> 0x0069 }
    L_0x0068:
        throw r6;
    L_0x0069:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0068;
    L_0x006e:
        return;
    L_0x006f:
        r6 = move-exception;
        r3 = r4;
        goto L_0x0063;
    L_0x0072:
        r2 = move-exception;
        r3 = r4;
        goto L_0x0054;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qunar.im.common.AudioRecordFunc.writeDateTOFile():void");
    }

    public byte[] short2ByteArray(short[] shorts) {
        byte[] bytes = new byte[(shorts.length * 2)];
        for (int i = 0; i < shorts.length; i++) {
            byte high = (byte) (shorts[i] >> 8);
            bytes[i * 2] = (byte) shorts[i];
            bytes[(i * 2) + 1] = high;
        }
        return bytes;
    }

    private void pcm2wal() {
        try {
            new Pcm2Wav().convertAudioFiles(this.rawFile, this.wavFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pcm2amr(OutputStream out, String wavFileName) throws IOException {
        AmrInputStream aStream = new AmrInputStream(new FileInputStream(wavFileName));
        try {
            byte[] x = new byte[1024];
            while (true) {
                int len = aStream.read(x);
                if (len <= 0) {
                    break;
                }
                out.write(x, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } finally {
            aStream.close();
            out.close();
        }
    }

    private void convertAMR(File inFile, File outFile) {
        try {
            AmrEncoder.init(0);
            List<short[]> armsList = new ArrayList();
            FileInputStream inputStream = new FileInputStream(inFile);
            FileOutputStream outStream = new FileOutputStream(outFile);
            outStream.write(header);
            byte[] buff = new byte[320];
            while (inputStream.read(buff, 0, 320) > 0) {
                short[] shortTemp = new short[160];
                ByteBuffer.wrap(buff).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(shortTemp);
                armsList.add(shortTemp);
            }
            for (int i = 0; i < armsList.size(); i++) {
                byte[] encodedData = new byte[(((short[]) armsList.get(i)).length * 2)];
                int len = AmrEncoder.encode(Mode.MR122.ordinal(), (short[]) armsList.get(i), encodedData);
                if (len > 0) {
                    byte[] tempBuf = new byte[len];
                    System.arraycopy(encodedData, 0, tempBuf, 0, len);
                    outStream.write(tempBuf, 0, len);
                }
            }
            AmrEncoder.exit();
            outStream.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
