package eu.galkina.zonky.mpchecker.integration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import eu.galkina.zonky.mpchecker.model.Loan;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

@Ignore
public class LoanResourceIntTest {

    private LoanResource loanResource;

    @Before
    public void setUp() {
        loanResource = new LoanResource();
    }

    @Test
    public void testLoadLoans() {
        String str = "2018-07-20 23:59:59";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime sinceDate = LocalDateTime.parse(str, formatter);
        Set<Loan> loans = loanResource.sync(sinceDate);
        assertNotNull(loans);
        assertNotSame(loans.size(), 0);
    }

}
