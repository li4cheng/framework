package com.my.framework.service;

import com.google.common.net.HttpHeaders;
import com.my.framework.web.rest.errors.BadRequestAlertException;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;

@Service
public class DownloadService {

    public ResponseEntity<Resource> download(String path) {
        File file = new File(path);
        Resource resource = new FileSystemResource(file);
        String fileName = resource.getFilename();
        if (!StringUtils.isNotEmpty(fileName)) {
            throw new BadRequestAlertException("...", "", "");
        }
        String name = new String(fileName.getBytes(), StandardCharsets.ISO_8859_1);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + name + "\"").body(resource);
    }
}
