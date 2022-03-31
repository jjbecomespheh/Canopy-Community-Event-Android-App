package com.example.project.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import java.io.ByteArrayOutputStream;

/** Utility class for encoding/decoding images to/from a string */
public abstract class BitmapEncoder {

    /**
     * Encodes a given bitmap image to a Base64 string. It compresses the bitmap to a JPEG using
     * the highest quality compression level.
     *
     * @param bitmap Bitmap to encode.
     * @return Encoded Base64 string representing the image.
     */
    public static String encodeImage(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();

        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    /**
     * Decodes a Base64 string back into the original bitmap image.
     *
     * @param encodedImage Base64 string to decode.
     * @return Original Bitmap object.
     */
    public static Bitmap decodeImage(String encodedImage) {
        byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}