package asia.ncc.erp.hrm;

import asia.ncc.erp.hrm.NcchrmApp;
import asia.ncc.erp.hrm.config.TestSecurityConfiguration;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Base composite annotation for integration tests.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = { NcchrmApp.class, TestSecurityConfiguration.class })
public @interface IntegrationTest {
}
