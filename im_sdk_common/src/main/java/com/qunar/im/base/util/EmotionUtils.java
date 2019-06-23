package com.qunar.im.base.util;

import android.content.Context;
import android.text.TextUtils;
import com.qunar.im.base.util.graphics.ImageUtils;
import com.qunar.im.base.view.faceGridView.EmoticionMap;
import com.qunar.im.base.view.faceGridView.EmoticonEntity;
import com.qunar.im.base.view.faceGridView.EmoticonFileUtils;
import com.qunar.im.common.CommonConfig;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EmotionUtils {
    public static File EMOJICON_DIR = new File(CommonConfig.globalContext.getFilesDir(), "emoticon_favorite" + File.separator + DataUtils.getInstance(CommonConfig.globalContext).getPreferences("fullname", ""));
    public static final String FAVORITE_ID = "favorite";
    private static EmoticionMap defaultEmotion;
    private static Map<String, EmoticionMap> extEmotions = new LinkedHashMap();
    private static final Object lockObj = new Object();
    private static Map<String, String> pkgId2Name = new LinkedHashMap();

    public static EmoticionMap getDefaultEmotion(Context context) {
        if (defaultEmotion == null) {
            synchronized (lockObj) {
                if (defaultEmotion == null) {
                    defaultEmotion = new EmoticonFileUtils("emoticons/", "emoticons/DefaultEmoticon.xml").geteMap(context);
                    pkgId2Name.put(defaultEmotion.packgeId, "经典");
                }
            }
        }
        defaultEmotion.showAll = 1;
        return defaultEmotion;
    }

    public static EmoticonEntity getEmoticionByShortCut(String shortCut, String pkgId, boolean checkExt) {
        boolean z = true;
        EmoticonEntity emoticonEntity = null;
        if (TextUtils.isEmpty(pkgId)) {
            if (defaultEmotion != null) {
                emoticonEntity = defaultEmotion.getEntity(shortCut);
                if (emoticonEntity != null) {
                    emoticonEntity.showAll = true;
                }
            }
            if (checkExt && emoticonEntity == null && extEmotions.size() > 0) {
                for (String key : extEmotions.keySet()) {
                    emoticonEntity = ((EmoticionMap) extEmotions.get(key)).getEntity(shortCut);
                    if (emoticonEntity != null) {
                        emoticonEntity.showAll = ((EmoticionMap) extEmotions.get(key)).showAll == 1;
                    }
                }
            }
        } else if (defaultEmotion != null && defaultEmotion.packgeId.equals(pkgId)) {
            emoticonEntity = defaultEmotion.getEntity(shortCut);
            if (emoticonEntity != null) {
                emoticonEntity.showAll = true;
            }
        } else if (checkExt && pkgId2Name.containsKey(pkgId)) {
            EmoticionMap eMap = (EmoticionMap) extEmotions.get(pkgId2Name.get(pkgId));
            if (eMap != null) {
                emoticonEntity = eMap.getEntity(shortCut);
                if (eMap.showAll != 1) {
                    z = false;
                }
                emoticonEntity.showAll = z;
            }
        }
        return emoticonEntity;
    }

    public static boolean isExistsEmoticon(String shortCut, String pkgId, boolean checkExt) {
        if (TextUtils.isEmpty(pkgId)) {
            if (defaultEmotion != null && defaultEmotion.containKey(shortCut)) {
                return true;
            }
            if (checkExt && extEmotions.size() > 0) {
                for (String key : extEmotions.keySet()) {
                    if (((EmoticionMap) extEmotions.get(key)).containKey(shortCut)) {
                        return true;
                    }
                }
            }
            return false;
        } else if (defaultEmotion != null && defaultEmotion.packgeId.equals(pkgId)) {
            return defaultEmotion.containKey(shortCut);
        } else {
            boolean z;
            if (checkExt && pkgId2Name.containsKey(pkgId) && extEmotions.containsKey(pkgId2Name.get(pkgId)) && ((EmoticionMap) extEmotions.get(pkgId2Name.get(pkgId))).containKey(shortCut)) {
                z = true;
            } else {
                z = false;
            }
            return z;
        }
    }

    public static Map<String, EmoticionMap> getExtEmotionsMap(Context context, boolean isForce) {
        if (isForce) {
            extEmotions.clear();
        }
        if (extEmotions.size() == 0) {
            File dir = getExtEmoticonFileDir();
            if (dir.exists()) {
                List<Map<String, String>> list = getEmotions(context);
                for (int i = 0; i < list.size(); i++) {
                    String name = (String) ((Map) list.get(i)).get("name");
                    String pkgId = (String) ((Map) list.get(i)).get("pkgid");
                    String path = (String) ((Map) list.get(i)).get("path");
                    String xmlPath = (String) ((Map) list.get(i)).get("xml");
                    pkgId2Name.put(pkgId, name);
                    EmoticionMap map = new EmoticonFileUtils(path + "/", xmlPath).geteMap();
                    if (map != null) {
                        if ("qq".equals(pkgId) || "yahoo".equals(pkgId)) {
                            map.showAll = 1;
                        }
                        extEmotions.put(name, map);
                    }
                }
            } else {
                dir.mkdirs();
            }
        }
        return extEmotions;
    }

    public static EmoticionMap getFavoriteMap(Context context) {
        File dir = getFavorEmoticonFileDir();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File[] files = dir.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
        EmoticionMap map = new EmoticionMap("0", files.length + 1, 0, 0, "");
        EmoticonEntity entity = new EmoticonEntity();
        entity.id = "favorite";
        map.pusEntity(entity.id, entity);
        for (File f : files) {
            EmoticonEntity tmpEntity = new EmoticonEntity();
            tmpEntity.fileFiexd = f.getAbsolutePath();
            tmpEntity.fileOrg = f.getAbsolutePath();
            map.pusEntity(f.getName(), tmpEntity);
        }
        map.showAll = 0;
        map.packgeId = "favorite";
        return map;
    }

    public static void putEmotions(Context cxt, String name, String pkgId, String dirPath, String xmlPath) {
        String userid = DataUtils.getInstance(CommonConfig.globalContext).getPreferences("fullname", "");
        List<Map<String, String>> obj = DataUtils.getInstance(cxt).loadObject(cxt, "emoticon" + userid);
        List<Map<String, String>> list = new ArrayList();
        if (obj != null) {
            list = obj;
        }
        Map<String, String> entry = new HashMap();
        entry.put("name", name);
        entry.put("pkgid", pkgId);
        entry.put("path", dirPath);
        entry.put("xml", xmlPath);
        list.add(entry);
        DataUtils.getInstance(cxt).saveObject(cxt, list, "emoticon" + userid);
    }

    public static void deleteDir(String pPath) {
        deleteDirWihtFile(new File(pPath));
    }

    public static void deleteDirWihtFile(File dir) {
        if (dir != null && dir.exists() && dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                if (file.isFile()) {
                    file.delete();
                } else if (file.isDirectory()) {
                    deleteDirWihtFile(file);
                }
            }
            dir.delete();
        }
    }

    public static void removeEmotions(Context cxt, String name, String pkgId) {
        String userid = DataUtils.getInstance(CommonConfig.globalContext).getPreferences("fullname", "");
        List<Map<String, String>> obj = DataUtils.getInstance(cxt).loadObject(cxt, "emoticon" + userid);
        List<Map<String, String>> list = new ArrayList();
        if (obj != null) {
            list = obj;
        }
        for (int i = 0; i < list.size(); i++) {
            Map<String, String> entry = (Map) list.get(i);
            if (((String) entry.get("name")).equals(name)) {
                deleteDir((String) entry.get("path"));
                list.remove(i);
            }
        }
        DataUtils.getInstance(cxt).saveObject(cxt, list, "emoticon" + userid);
    }

    public static List<Map<String, String>> getEmotions(Context cxt) {
        List<Map<String, String>> obj = DataUtils.getInstance(cxt).loadObject(cxt, "emoticon" + DataUtils.getInstance(CommonConfig.globalContext).getPreferences("fullname", ""));
        List<Map<String, String>> list = new ArrayList();
        if (obj != null) {
            return obj;
        }
        return list;
    }

    public static void loadSpecialExtEmot(Context cxt, String name, String pkgId, File emtDir) {
        if (emtDir != null && emtDir.exists()) {
            File[] xmlFile = emtDir.listFiles(new FileFilter() {
                public boolean accept(File pathname) {
                    if (pathname.isFile() && pathname.getName().endsWith(".xml")) {
                        return true;
                    }
                    return false;
                }
            });
            if (xmlFile.length > 0) {
                pkgId2Name.put(pkgId, name);
                extEmotions.put(name, new EmoticonFileUtils(emtDir.getPath() + "/", xmlFile[0].getPath()).geteMap());
                putEmotions(cxt, name, pkgId, emtDir.getPath(), xmlFile[0].getPath());
                return;
            }
            emtDir.delete();
        }
    }

    public static String saveImgToFavoriteEmojiconDir(Context context, List<String> filePaths) {
        File dir = getFavorEmoticonFileDir();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (filePaths.size() == 0) {
            return "";
        }
        for (String path : filePaths) {
            File src = new File(path);
            if (src.exists() && src.isFile()) {
                ImageUtils.compressFile(src, 204800, 256, 256, new File(dir, fileToMD5(src)));
            }
        }
        return dir.getAbsolutePath();
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x003b A:{SYNTHETIC, Splitter:B:28:0x003b} */
    private static java.lang.String fileToMD5(java.io.File r9) {
        /*
        r3 = 0;
        r4 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x0048 }
        r4.<init>(r9);	 Catch:{ Exception -> 0x0048 }
        r7 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r0 = new byte[r7];	 Catch:{ Exception -> 0x001f, all -> 0x0045 }
        r7 = "MD5";
        r1 = java.security.MessageDigest.getInstance(r7);	 Catch:{ Exception -> 0x001f, all -> 0x0045 }
        r6 = 0;
    L_0x0011:
        r7 = -1;
        if (r6 == r7) goto L_0x0029;
    L_0x0014:
        r6 = r4.read(r0);	 Catch:{ Exception -> 0x001f, all -> 0x0045 }
        if (r6 <= 0) goto L_0x0011;
    L_0x001a:
        r7 = 0;
        r1.update(r0, r7, r6);	 Catch:{ Exception -> 0x001f, all -> 0x0045 }
        goto L_0x0011;
    L_0x001f:
        r2 = move-exception;
        r3 = r4;
    L_0x0021:
        r7 = "";
        if (r3 == 0) goto L_0x0028;
    L_0x0025:
        r3.close();	 Catch:{ Exception -> 0x0041 }
    L_0x0028:
        return r7;
    L_0x0029:
        r5 = r1.digest();	 Catch:{ Exception -> 0x001f, all -> 0x0045 }
        r7 = convertHashToString(r5);	 Catch:{ Exception -> 0x001f, all -> 0x0045 }
        if (r4 == 0) goto L_0x0036;
    L_0x0033:
        r4.close();	 Catch:{ Exception -> 0x003f }
    L_0x0036:
        r3 = r4;
        goto L_0x0028;
    L_0x0038:
        r7 = move-exception;
    L_0x0039:
        if (r3 == 0) goto L_0x003e;
    L_0x003b:
        r3.close();	 Catch:{ Exception -> 0x0043 }
    L_0x003e:
        throw r7;
    L_0x003f:
        r8 = move-exception;
        goto L_0x0036;
    L_0x0041:
        r8 = move-exception;
        goto L_0x0028;
    L_0x0043:
        r8 = move-exception;
        goto L_0x003e;
    L_0x0045:
        r7 = move-exception;
        r3 = r4;
        goto L_0x0039;
    L_0x0048:
        r2 = move-exception;
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qunar.im.base.util.EmotionUtils.fileToMD5(java.io.File):java.lang.String");
    }

    private static String convertHashToString(byte[] hashBytes) {
        String returnVal = "";
        for (byte b : hashBytes) {
            returnVal = returnVal + Integer.toString((b & 255) + 256, 16).substring(1);
        }
        return returnVal.toLowerCase();
    }

    public static File getFavorEmoticonFileDir() {
        return new File(CommonConfig.globalContext.getFilesDir(), "emoticon_favorite" + File.separator + DataUtils.getInstance(CommonConfig.globalContext).getPreferences("fullname", ""));
    }

    public static File getExtEmoticonFileDir() {
        return new File(CommonConfig.globalContext.getFilesDir(), "qtalk_emoticon" + File.separator + DataUtils.getInstance(CommonConfig.globalContext).getPreferences("fullname", ""));
    }

    public static void clearEmoticonCache() {
        defaultEmotion = null;
        extEmotions.clear();
        pkgId2Name.clear();
    }
}
