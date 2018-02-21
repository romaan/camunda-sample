package au.com.myapp.mil;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;

@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:config/application-dev.yml")
@SpringBootTest(classes = { Application.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerOrderTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerOrderTest.class);
    private static final String TEST_CUSTOMER_ORDER_KEY = "CustomerOrder";

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    private Map<String, Object> variables;

    @Before
    public void initVars() {
        this.variables = new HashMap<>();
        this.variables.put("outletID", "10019");
        this.variables.put("startTime", "2013-02-12T23:00:00.000Z");
        this.variables.put("endTime", "2015-02-12T23:00:00.000Z");
        this.variables.put("flowRate", "21");
    }

    @Test
    public void startCustomerProcess() throws InterruptedException {
        ProcessInstance processCustomerOrder = runtimeService.startProcessInstanceByKey(TEST_CUSTOMER_ORDER_KEY, variables);
        assertThat(processCustomerOrder).isNotNull();
    }
}
