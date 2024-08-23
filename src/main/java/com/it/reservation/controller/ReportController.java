package com.it.reservation.controller;

import java.io.File;
import java.io.FileInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.it.reservation.common.AbstractCommon;
import com.it.reservation.payload.CustomerUserAttr;
import com.it.reservation.service.ReportService;
import com.it.reservation.util.AppConstants;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = AppConstants.PROJECT_VERSION + "/report")
public class ReportController extends AbstractCommon{

    @Autowired
    ReportService reportService;
    
    @GetMapping("reservation/download/excel")
    public ResponseEntity<?> stockDownloadExcel(HttpServletRequest request, @RequestParam("type") String type) throws IOException, Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reservationReport.xlsx");
        CustomerUserAttr userAttr = super.getCustomerUserAttr(request);
        InputStreamResource resource;
        File file;
        try {
            file = reportService.revGenExcel(userAttr, type);
            if (null == file) {
                return ResponseEntity.ok().body(null);
            } else {
                resource = new InputStreamResource(new FileInputStream(file));
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(null != file ? file.length() : 0)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
