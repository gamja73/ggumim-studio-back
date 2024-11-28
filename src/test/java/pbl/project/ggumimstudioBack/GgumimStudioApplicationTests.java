package pbl.project.ggumimstudioBack;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GgumimStudioApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(Keys.secretKeyFor(SignatureAlgorithm.HS512).toString());
    }

}
