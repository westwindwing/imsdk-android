package com.qunar.im.base.view.faceGridView;

import android.content.Context;
import android.util.Xml;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class EmoticonFileUtils {
    private EmoticionMap eMap;
    private String emoticonXml;
    private String facePath;
    private String ns = null;

    public EmoticonFileUtils(String facePath, String emoticonXml) {
        this.facePath = facePath;
        this.emoticonXml = emoticonXml;
    }

    public EmoticionMap geteMap(Context context) {
        try {
            parse(context.getResources().getAssets().open(this.emoticonXml));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.eMap;
    }

    public EmoticionMap geteMap() {
        try {
            parse(new BufferedInputStream(new FileInputStream(new File(this.emoticonXml))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.eMap;
    }

    private void parse(InputStream in) {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", false);
            parser.setInput(in, "utf-8");
            parser.nextTag();
            readRoot(parser);
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
            try {
                in.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            try {
                in.close();
            } catch (IOException e32) {
                e32.printStackTrace();
            }
        } catch (Throwable th) {
            try {
                in.close();
            } catch (IOException e322) {
                e322.printStackTrace();
            }
            throw th;
        }
    }

    private void readRoot(XmlPullParser parser) {
        try {
            parser.require(2, this.ns, "FACESETTING");
            while (parser.next() != 3) {
                if (parser.getEventType() == 2) {
                    readDefaultFace(parser);
                }
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void readDefaultFace(XmlPullParser parser) {
        try {
            parser.require(2, this.ns, "DEFAULTFACE");
            this.eMap = new EmoticionMap(parser.getAttributeValue(this.ns, "version"), Integer.valueOf(parser.getAttributeValue(this.ns, "count")).intValue(), Integer.valueOf(parser.getAttributeValue(this.ns, "showall")).intValue(), Integer.valueOf(parser.getAttributeValue(this.ns, "line")).intValue(), parser.getAttributeValue(this.ns, "package"));
            while (parser.next() != 3) {
                if (parser.getEventType() == 2) {
                    readFaceEntity(parser);
                }
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void readFaceEntity(XmlPullParser parser) {
        try {
            parser.require(2, this.ns, "FACE");
            String shortcut = parser.getAttributeValue(this.ns, "shortcut");
            String id = parser.getAttributeValue(this.ns, "id");
            String tip = parser.getAttributeValue(this.ns, "tip");
            EmoticonEntity entity = new EmoticonEntity();
            entity.id = id;
            entity.shortCut = shortcut;
            entity.multiframe = 1;
            entity.tip = tip;
            while (parser.next() != 3) {
                if (parser.getEventType() == 2) {
                    if (parser.getName().equals("FILE_FIXED")) {
                        entity.fileFiexd = this.facePath + readFileName(parser);
                    } else {
                        entity.fileOrg = this.facePath + readFileOrg(parser);
                    }
                }
            }
            this.eMap.pusEntity(shortcut, entity);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private String readFileName(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(2, this.ns, "FILE_FIXED");
        String result = "";
        if (parser.next() == 4) {
            result = parser.getText();
            parser.nextTag();
        }
        parser.require(3, this.ns, "FILE_FIXED");
        return result;
    }

    private String readFileOrg(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(2, this.ns, "FILE_ORG");
        String result = "";
        if (parser.next() == 4) {
            result = parser.getText();
            parser.nextTag();
        }
        parser.require(3, this.ns, "FILE_ORG");
        return result;
    }
}
