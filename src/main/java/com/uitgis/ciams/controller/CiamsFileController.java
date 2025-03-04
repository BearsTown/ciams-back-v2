package com.uitgis.ciams.controller;


import com.uitgis.ciams.dto.CiamsFileDto;
import com.uitgis.ciams.enums.ActionTypeEnum;
import com.uitgis.ciams.enums.MenuEnum;
import com.uitgis.ciams.exception.CustomException;
import com.uitgis.ciams.model.CiamsFile;
import com.uitgis.ciams.service.CiamsCommonService;
import com.uitgis.ciams.service.CiamsFileService;
import com.uitgis.ciams.util.FileUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/file")
@RestController
public class CiamsFileController {

    private final CiamsFileService ciamsFileService;
    private final CiamsCommonService ciamsCommonService;


    @PostMapping("/temp")
    public ResponseEntity<?> uploadTempFiles(@RequestParam List<MultipartFile> files) throws Exception {
        List<CiamsFileDto.TempFile> tempFiles = ciamsFileService.uploadTempFiles(files);

        return ResponseEntity.ok(tempFiles);
    }


    @GetMapping("/list")
    public ResponseEntity<?> search(CiamsFileDto.Search.Params params) {
        CiamsFileDto.Search.Result result = ciamsFileService.search(params);

        return ResponseEntity.ok(result);
    }


    @GetMapping("/down/{id}")
    public ResponseEntity<?> getFileDownload(@PathVariable("id") String id, HttpServletRequest request) {
        String browser = FileUtil.getBrowser(request);

        CiamsFile fileInfo = ciamsFileService.selectById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        File downloadFile = FileUtil.getFile(fileInfo);

        if (downloadFile.exists()) {
            String filePath = downloadFile.getAbsolutePath();
            log.info("Download Path = {}", downloadFile.toURI());

            /**
             * -ToDo : 예외 처리
             */
            try {
                InputStreamResource resource = new InputStreamResource(Files.newInputStream(Paths.get(filePath), StandardOpenOption.READ));

                return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/download; charset=utf-8")).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + FileUtil.getFileNm(browser, fileInfo.getOrgName() + "\"")).body(resource);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("file error.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("file does not exist.");
        }
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<?> viewImage(@PathVariable("id") String id) {
        CiamsFile fileInfo = ciamsFileService.selectById(id);
        File imageFile = FileUtil.getFile(fileInfo);

        return FileUtil.responseEntityImage(imageFile);
    }

    @GetMapping("/pdf/{id}")
    public ResponseEntity<?> getPDFView(@PathVariable("id") String id, HttpServletRequest request) {
        String browser = FileUtil.getBrowser(request);

        CiamsFile fileInfo = ciamsFileService.selectById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        File downloadFile = FileUtil.getFile(fileInfo);

        if (downloadFile.exists()) {
            String filePath = downloadFile.getAbsolutePath();
            log.info("Download Path = {}", downloadFile.toURI());

            /**
             * -ToDo : 예외 처리
             */
            try {
                InputStreamResource resource = new InputStreamResource(Files.newInputStream(Paths.get(filePath), StandardOpenOption.READ));

                return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/pdf; charset=utf-8")).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + FileUtil.getFileNm(browser, fileInfo.getOrgName() + "\"")).body(resource);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("file error.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("file does not exist.");
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable("id") String id) throws Exception {
        try {
            List<String> ids = Arrays.asList(id);

            ciamsCommonService.log(MenuEnum.FILE, ActionTypeEnum.DELETE, id);

            return ResponseEntity.ok(ciamsFileService.deleteFilesByIds(ids));
        } catch (Exception e) {
            throw new CustomException("파일 삭제에 실패했습니다.");
        }
    }
}
