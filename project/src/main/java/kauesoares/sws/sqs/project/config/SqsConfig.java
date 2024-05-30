package kauesoares.sws.sqs.project.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import kauesoares.sws.sqs.project.config.properties.AWSAccessProperties;
import kauesoares.sws.sqs.project.config.properties.AWSSQSProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SqsConfig {

    private final AWSAccessProperties awsAccessProperties;
    private final AWSSQSProperties awsSqsProperties;

    @Bean
    public AmazonSQS amazonSQS() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(
                this.awsAccessProperties.getKey(),
                this.awsAccessProperties.getSecret()
        );
        return AmazonSQSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.fromName(this.awsSqsProperties.getRegion()))
                .build();
    }

}
