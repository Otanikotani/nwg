package com.dev.controllers;


import com.google.common.primitives.Bytes;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Controller
@RequestMapping("/nwgs")
public class ConverterController {

    public final static byte[] OFFSET_LABEL = "Warcraft III recorded game".getBytes();

    public byte[] convert(byte[] bytes) throws IOException {
        int offset = Bytes.indexOf(bytes, OFFSET_LABEL);
        return Arrays.copyOfRange(bytes, offset, bytes.length);
    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public byte[] handleFileUpload(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
        String name = file.getOriginalFilename();
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                byte[] converted = convert(bytes);
                String w3gName = FilenameUtils.removeExtension(name) + ".w3g";
                response.setHeader("Content-Disposition", "attachment; filename=\"" + w3gName + "\"");
                response.setContentLength(converted.length);
                response.setContentType("application/octet-stream");
                return converted;
            } catch (Exception e) {
                return new byte[0];
            }
        } else {
            return new byte[0];
        }
    }

}
