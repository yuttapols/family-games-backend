package com.it.reservation.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class ImgUtils {

    public static final int BITE_SIZE = 4 * 1024;

//    public static void moveFile(File file, String fileName, String pathType) throws IOException {
//        if (null != file) {
//            Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
//            String outputPathStr = path + File.separator + Constants.PATH_IMAGES.PATH_FOLDER_UPLOAD + File.separator + Constants.PATH_IMAGES.PATH_FOLDER_INPUT + File.separator + pathType
//                    + File.separator + fileName;
//            File fileOut = new File(outputPathStr);
//            file.renameTo(fileOut);
//        }
//    }
//
//    public static void saveFile(MultipartFile file, String fileName, String pathType) throws IOException {
//        Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
//        if (null != file) {
//            String outputPathStr = path + File.separator + Constants.PATH_IMAGES.PATH_FOLDER_UPLOAD + File.separator + Constants.PATH_IMAGES.PATH_FOLDER_INPUT + File.separator + pathType
//                    + File.separator + fileName;
//            try (FileOutputStream fout = new FileOutputStream(outputPathStr)) {
//                fout.write(file.getBytes());
//                fout.close();
//            } catch (Exception e) {
//                // TODO: handle exception
//            }
//        }
//    }
//
//    public static String getPathInput(String pathType) throws IOException {
//
//        return File.separator + Constants.PATH_IMAGES.PATH_FOLDER_UPLOAD + File.separator + Constants.PATH_IMAGES.PATH_FOLDER_INPUT + File.separator + pathType + File.separator;
//    }
//
//    public static String getPathOutput(String pathType) throws IOException {
//
//        return File.separator + Constants.PATH_IMAGES.PATH_FOLDER_UPLOAD + File.separator + Constants.PATH_IMAGES.PATH_FOLDER_OUTPUT + File.separator + pathType + File.separator;
//    }
//
//    public static void deleteFile(String fileName, String pathType) throws IOException {
//        if (StringUtils.isNotBlank(fileName)) {
//            Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
//            String filePath = path + File.separator + Constants.PATH_IMAGES.PATH_FOLDER_UPLOAD + File.separator + Constants.PATH_IMAGES.PATH_FOLDER_INPUT + File.separator + pathType
//                    + File.separator + fileName;
//
//            Path filePaths = Paths.get(filePath);
//            // Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rwxrwxrwx");
//            try {
//                // Files.setPosixFilePermissions(filePaths, permissions);
//                Files.deleteIfExists(filePaths);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
//     public static void copyFile(File original, String fileNameClone, String pathType) throws IOException {
//         if (StringUtils.isNotBlank(fileNameClone)) {
//             Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
// //			String filePathOld = Constants.PATH_FOLDER_MAIN + File.separator + Constants.PATH_FOLDER_UPLOAD + File.separator + pathType
// //					+ File.separator + fileName;
//             String fileCopiedPath = path + File.separator + Constants.PATH_FOLDER_UPLOAD + File.separator + pathType
//                     + File.separator + fileNameClone;
//             File copied = new File(fileCopiedPath);
//             FileCopyUtils.copy(original, copied);
//         }
//     }

    public static String genaratePrefixFile() {

        Calendar today = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
        String date = format1.format(today.getTime());
        Random random = new Random();

        return date + "_" + random.nextInt(99999) + "_";
    }

    public static String genarateFileName() {

        return generateRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 17);
    }

    public static String concatStr(String main, String other) throws UnsupportedEncodingException {

        return main.concat(other);
    }

    public static String generateRandomChars(String candidateChars, int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars
                    .length())));
        }

        return sb.toString();
    }

    public static String subStrFileName(String fileName) {

        if (null != fileName) {
            int lastIndex = fileName.lastIndexOf(".");
            fileName = fileName.substring(lastIndex, fileName.length());
        }

        return fileName;
    }

    public static byte[] compressImage(byte[] data) throws IOException {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[BITE_SIZE];

        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }

        outputStream.close();

        return outputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] data) throws DataFormatException, IOException {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[BITE_SIZE];

        while (!inflater.finished()) {
            int count = inflater.inflate(tmp);
            outputStream.write(tmp, 0, count);
        }

        outputStream.close();

        return outputStream.toByteArray();
    }

    public static String getFileNameImages(MultipartFile file) throws Exception {
        String fileName = null;
        try {
            String preFixNameFile = ImgUtils.genaratePrefixFile();
            String genarateFileName = ImgUtils.genarateFileName().concat(ImgUtils.subStrFileName(file.getOriginalFilename()));
            fileName = ImgUtils.concatStr(preFixNameFile, genarateFileName);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return fileName;
    }

//    public static void saveFileV2(MultipartFile file, String fileName, String pathType) throws IOException {
//
//        if (null != file) {
//            String outputPathStr = File.separator + Constants.PATH_IMAGES.PATH_FOLDER_UPLOAD + File.separator + Constants.PATH_IMAGES.PATH_FOLDER_INPUT + File.separator + pathType
//                    + File.separator + fileName;
//            try (FileOutputStream fout = new FileOutputStream(outputPathStr)) {
//                fout.write(file.getBytes());
//                fout.close();
//            } catch (Exception e) {
//                // TODO: handle exception
//            }
//        }
//    }

    public static void deleteFileV2(String fileName, String path) throws IOException {
        if (StringUtils.isNotBlank(fileName)) {

            String filePath = path.concat(fileName);

            Path filePaths = Paths.get(filePath);
            try {
                Files.deleteIfExists(filePaths);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
