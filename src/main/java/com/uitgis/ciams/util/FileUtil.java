package com.uitgis.ciams.util;

import com.uitgis.ciams.user.dto.CiamsFileDto;
import com.uitgis.ciams.model.CiamsFile;
import com.uitgis.ciams.model.CiamsImage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class FileUtil {
    private static String pathPrefix;
    private static String fileTempPath;

    @Value("${file.path.prefix}")
    public void setPathPrefix(String value) {
        pathPrefix = value;
    }

    @Value("${file.path.temp}")
    public void setFileTempPath(String value) {
        fileTempPath = value;
    }

    public static String getPathPrefix() {
        return pathPrefix;
    }

    public static String getFileTempPath() {
        return fileTempPath;
    }

    public static File getFile(CiamsFile suFile) {
        String filePath = pathPrefix + suFile.getPath() + "/" + suFile.getNewName();
        return new File(filePath);
    }

    public static File getFile(CiamsImage suFile) {
        String filePath = pathPrefix + suFile.getPath() + "/" + suFile.getName();
        return new File(filePath);
    }

    public static List<CiamsFile> tansUploadFiles(String linkId, String user, List<CiamsFileDto.TempFile> files) {
        List<CiamsFile> uploadFiles = new ArrayList<>();

        files.forEach(tempFile -> {
            CiamsFile uploadFile = new CiamsFile();
            BeanUtils.copyProperties(tempFile, uploadFile);

            uploadFile.setLinkId(linkId);
            uploadFile.setCreateUser(user);

            uploadFiles.add(uploadFile);
        });

        return uploadFiles;
    }


    public static List<CiamsFile> saveFiles(List<CiamsFile> files) throws IOException {
        List<CiamsFile> saveFiles = new ArrayList<>();

        String tempDir = pathPrefix + fileTempPath + "/";

        for (CiamsFile file : files) {
            String fileNewName = file.getNewName();

            Path srcPath = Paths.get(tempDir + "/" + fileNewName);
            if (!Files.exists(srcPath)) {
                continue;
            }

            String dstDir = pathPrefix + "/" + file.getTypeCode();
            if (!Files.exists(Paths.get(dstDir))) {
                Files.createDirectories(Paths.get(dstDir));
            }

            Path dstPath = Paths.get(dstDir + "/" + fileNewName);
            Files.move(srcPath, dstPath, StandardCopyOption.REPLACE_EXISTING);
            log.debug("Files move {} -> {}", srcPath, dstPath);

            CiamsFile saveFile = new CiamsFile();
            BeanUtils.copyProperties(file, saveFile);

            saveFile.setPath("/" + file.getTypeCode());

            saveFiles.add(saveFile);
        }

        return saveFiles;
    }


    public static void deleteFiles(List<CiamsFile> deleteFiles) {
        if (ValidUtil.notEmpty(deleteFiles)) {
            deleteFiles.forEach(file -> {
                String filePath = pathPrefix + file.getPath() + "/" + file.getNewName();

                try {
                    Files.deleteIfExists(Paths.get(filePath));
                    log.debug("Attach file deleted. {}", filePath);
                } catch (IOException e) {
                    log.error("Attach file delete error. {}", e.getMessage());
                }
            });
        }
    }

    public static String getBrowser(HttpServletRequest req) {
        String userAgent = req.getHeader("User-Agent");
        if (userAgent.indexOf("Chrome") > -1) {
            return "Chrome";
        } else if (userAgent.indexOf("Opera") > -1) {
            return "Opera";
        } else if (userAgent.indexOf("Safari") > -1) {
            return "Safari";
        } else if (userAgent.indexOf("Firefox") > -1) {
            return "Firefox";
        } else {
            return "MSIE";
        }
    }


    public static String getFileNm(String browser, String fileNm) throws UnsupportedEncodingException {
        String reFileNm = null;

        if (browser.equals("MSIE") || browser.equals("Trident") || browser.equals("Edge")) {
            reFileNm = URLEncoder.encode(fileNm, "UTF-8").replaceAll("\\+", "%20");
        } else {
            if (browser.equals("Chrome")) {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < fileNm.length(); i++) {
                    char c = fileNm.charAt(i);
                    if (c > '~') {
                        sb.append(URLEncoder.encode(Character.toString(c), "UTF-8"));
                    } else {
                        sb.append(c);
                    }
                }
                reFileNm = sb.toString();
            } else {
                reFileNm = new String(fileNm.getBytes("UTF-8"), "ISO-8859-1");
            }
        }

        return reFileNm;
    }

    public static ResponseEntity<?> responseEntityImage(File file) {
        if (file.exists()) {
            try {
                Path imagePath = file.toPath();
                String contentType = Files.probeContentType(imagePath);

                if (contentType == null || !contentType.startsWith("image")) {
                    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Not an image file.");
                }

                InputStreamResource resource = new InputStreamResource(Files.newInputStream(imagePath, StandardOpenOption.READ));

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .body(resource);

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error loading image.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found.");
        }
    }
}
