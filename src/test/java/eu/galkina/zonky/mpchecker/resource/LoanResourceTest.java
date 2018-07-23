package eu.galkina.zonky.mpchecker.resource;

import java.time.LocalDateTime;
import java.util.Set;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import eu.galkina.zonky.mpchecker.integration.LoanResource;
import eu.galkina.zonky.mpchecker.model.Loan;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertNotNull;

public class LoanResourceTest {

    @ClassRule
    public static final WireMockRule WIRE_MOCK_RULE = new WireMockRule(options().dynamicPort().dynamicPort());

    private static LoanResource LOAN_RESOURCE_FIXTURE;

    @Before
    public void setUp() {
        LOAN_RESOURCE_FIXTURE = new LoanResource("http://localhost:" + WIRE_MOCK_RULE.port());
    }

    @Test
    public void testLoadLoans() {
        Set<Loan> loans = LOAN_RESOURCE_FIXTURE.sync(LocalDateTime.of(2018,7,23,18,35, 43));
        assertNotNull(loans);
        assertSame(loans.size(), 20);
    }
}
