package kauesoares.sws.sqs.project.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
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

    private AWSStaticCredentialsProvider awsStaticCredentialsProvider() {
        return new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(
                        this.awsAccessProperties.getKey(),
                        this.awsAccessProperties.getSecret()
                )
        );
    }

    @Bean
    @Primary
    public AmazonSQSAsync amazonSQSAsync() {
        return AmazonSQSAsyncClientBuilder.standard()
                .withRegion(Regions.fromName(this.awsSqsProperties.getRegion()))
                .withCredentials(this.awsStaticCredentialsProvider())
                .build();
    }

}
