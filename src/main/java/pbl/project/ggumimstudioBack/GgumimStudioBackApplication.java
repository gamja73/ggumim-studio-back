package pbl.project.ggumimstudioBack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import pbl.project.ggumimstudioBack.common.config.YamlPropertySourceFactory;

@SpringBootApplication
@PropertySource(value = "classpath:env.yml", factory = YamlPropertySourceFactory.class)
public class GgumimStudioBackApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(GgumimStudioBackApplication.class, args);
    }
}
