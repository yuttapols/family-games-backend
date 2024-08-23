package com.it.reservation.service;

import java.io.File;
import java.io.IOException;

import com.it.reservation.payload.CustomerUserAttr;

public interface ReportService {

	public File revGenExcel(CustomerUserAttr userAttr, String type) throws IOException, Exception;
}
