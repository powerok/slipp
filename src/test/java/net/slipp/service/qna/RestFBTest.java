package net.slipp.service.qna;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.Post;

public class RestFBTest {
    private static Logger logger = LoggerFactory.getLogger(RestFBTest.class);

    private FacebookClient dut;

    @Before
    public void setup() {
        String accessToken = "";
        dut = new DefaultFacebookClient(accessToken);
    }

    @Test
    public void post() throws Exception {
        String message = "그룹에 글쓰기 테스트입니다.";
        FacebookType response = dut.publish("me/feed", FacebookType.class,
                Parameter.with("link", "http://www.slipp.net/questions/87"), Parameter.with("message", message));
        String id = response.getId();
        logger.debug("id : {}", id);
    }

    @Test
    public void groupPost() throws Exception {
        String message = "그룹에 글쓰기 테스트입니다.";
        FacebookType response = dut.publish("387530111326987/feed", FacebookType.class,
                Parameter.with("link", "http://www.slipp.net/questions/87"), Parameter.with("message", message));
        String id = response.getId();
        logger.debug("id : {}", id);
    }

    @Test
    public void fetchPost() throws Exception {
        Connection<Post> myFeed = dut.fetchConnection("me/posts", Post.class);
        for (List<Post> myFeedConnectionPage : myFeed) {
            for (Post post : myFeedConnectionPage) {
                logger.debug("Post: " + post.getMessage());
            }
        }
    }
}
