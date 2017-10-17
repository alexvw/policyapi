package com.idw.policymanager.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class PMConfig {

	@Value("${s3.bucket}")
	String bucket;

	@Value("${s3.plainidconfig}")
	String plainIdFile;
}
