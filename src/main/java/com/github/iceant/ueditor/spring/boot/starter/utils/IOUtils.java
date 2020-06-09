package com.github.iceant.ueditor.spring.boot.starter.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtils {
    public static void copyUseStream(InputStream is, OutputStream os, int bufferSize, boolean closeAfterInvoke) {
        byte[] buffer = new byte[bufferSize];
        int length;
        try {
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            os.flush();
        } catch (Exception err) {
            throw new RuntimeException(err);
        } finally {
            if (closeAfterInvoke) {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                    }
                }

                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
    }

    public static void write(byte[] bytes, OutputStream os, int bufferSize, boolean closeAfterInvoke){
        byte[] buffer = new byte[bufferSize];
        int length;
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        try {
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            os.flush();
        } catch (Exception err) {
            throw new RuntimeException(err);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }

            if (closeAfterInvoke) {
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
    }
}
