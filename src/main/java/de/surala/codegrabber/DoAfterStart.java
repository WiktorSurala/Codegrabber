package de.surala.codegrabber;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class DoAfterStart {

    private static final String URL = "https://api.twitter.com/2/tweets/search/recent?query=from:lis_tomasz";

    @Value("${twitter.bearer.token}")
    private String token;

    @PostConstruct
    public void postConstruct() {
        log.info("Post Construct is called");
        WebClient webClient = WebClient.builder().baseUrl(URL).defaultHeaders(httpHeaders -> httpHeaders.add("Authorization", "Bearer " + token)).build();
        TwitterWrapper twitterWrapper = webClient.get().retrieve().bodyToMono(TwitterWrapper.class).block();
        for (int i = 0; i <= twitterWrapper.getData().length - 1; i++) {
            log.info("########### Message No. {} #############", i);
            log.info(twitterWrapper.getData()[i].getText());
        }
    }
}
