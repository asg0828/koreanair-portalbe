package com.cdp.portal.common.aws.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * AWS 설정 정보
 */
@Data
@Component
@ConfigurationProperties(prefix = "aws-props")
public class AwsProps {

    //Credentials
    private String dbKeyFirst;
    private String dbKeySecond;
    private String tableauKey;

    //S3
    private String bucketName;
    private String filePath;
    private String previewPath;
    private String viewPath;
    private String photoPath;

    //SageMaker
    private int pageSize;
    private String timeZone;
    private String sagemakerDomainId;
}