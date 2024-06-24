package com.example.butler.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImgUtil {

    @Value("${dir.img.post}")
    private String postImgDir;

    public ResponseEntity<byte[]> getImgStream(String path) throws IOException {
        String imgPath = URLDecoder.decode(Paths.get(postImgDir+path).toString(), StandardCharsets.UTF_8);
        File file = new File(Path.of(imgPath).toString());
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", Files.probeContentType(file.toPath()));
        return new ResponseEntity<>(getImgByteStream(file), header, HttpStatus.OK);
    }
    private byte[] getImgByteStream (File file) throws IOException {
        return Files.readAllBytes(file.toPath());
    }
}
