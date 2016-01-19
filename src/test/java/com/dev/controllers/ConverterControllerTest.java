package com.dev.controllers;

import com.google.common.io.ByteSource;
import com.google.common.io.Resources;
import com.google.common.primitives.Bytes;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;

import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ConverterControllerTest extends ResourceTest {

    @Autowired
    ConverterController converterController;

    @Test
    public void testUploadingNwgFile() throws Exception {
        String name = "long";
        URL resourceURL = Resources.getResource(name + ".nwg");
        ByteSource replaySourceFile = Resources.asByteSource(resourceURL);
        byte[] bytes = replaySourceFile.read();
        MockMultipartFile file = new MockMultipartFile("file", name + ".nwg", null, bytes);
        MvcResult mvcResult = mockMvc.perform(fileUpload("/nwgs/upload")
                .file(file))
                .andExpect(status().isOk())
                .andReturn();
        byte[] converted = mvcResult.getResponse().getContentAsByteArray();
        assertEquals(0, Bytes.indexOf(converted, ConverterController.OFFSET_LABEL));
    }

    @Override
    protected Object getController() {
        return converterController;
    }
}