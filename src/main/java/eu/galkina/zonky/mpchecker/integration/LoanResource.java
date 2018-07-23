package eu.galkina.zonky.mpchecker.integration;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import eu.galkina.zonky.mpchecker.model.Loan;

import static eu.galkina.zonky.mpchecker.util.DateUtil.toISO8601;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

/**
 * Downloads the list of newly added {@link Loan}
 * via the Zonky REST API
 * since the specified LocalDateTime
 */
public class LoanResource {

    private static final String BASE_URL = "https://api.zonky.cz";
    private static final String RESOURCE_LOANS_URL = "/loans/marketplace";
    private static final int DEFAULT_PAGE_SIZE = 20;

    private static final Logger logger = LoggerFactory.getLogger(LoanResource.class);

    private final Client client;
    private final String apiUrl;

    public LoanResource(String apiBaseUrl) {
        this.apiUrl = apiBaseUrl + RESOURCE_LOANS_URL;
        this.client = ClientBuilder.newClient().register(JacksonJsonProvider.class);
    }

    public LoanResource() {
        this.apiUrl = BASE_URL + RESOURCE_LOANS_URL;
        this.client = ClientBuilder.newClient().register(JacksonJsonProvider.class);
    }

    public Set<Loan> sync(LocalDateTime sinceDateTime) {
        WebTarget loansWebTarget = client
                .target(apiUrl)
                .queryParam("datePublished__gt", toISO8601(sinceDateTime));
        Invocation.Builder invocationBuilder
                = loansWebTarget
                .request(MediaType.APPLICATION_JSON)
                .header("X-Order", "datePublished")
                .header("X-Page", 0)
                .header("X-Size", DEFAULT_PAGE_SIZE);
        Response response = invocationBuilder.get();
        Set<Loan> loans = response.readEntity(new GenericType<Set<Loan>>() {});
        loans.addAll(getPagesLoansAsync(response, loansWebTarget));
        return loans;
    }

    /**
     * In case there are more pages, download them asynchronously
     * @param response RS response
     * @param loansWebTarget RS WebTarget for retrieving loans
     * @return The merged list of loans from all pages
     */
    private Set<Loan> getPagesLoansAsync(Response response, WebTarget loansWebTarget) {
        Set<Loan> loanList = new HashSet<>();
        List<Object> xTotalHeaders = response.getHeaders().get("X-Total");
        if (isNotEmpty(xTotalHeaders)) {
            try {
                int total = Integer.parseInt(response.getHeaders().get("X-Total").get(0).toString());
                if (total > DEFAULT_PAGE_SIZE) {
                    List<CompletableFuture<Set<Loan>>> futures =
                            IntStream.rangeClosed(1, total/DEFAULT_PAGE_SIZE)
                                    .boxed()
                                    .map(page -> getLoansPage(loansWebTarget, page))
                                    .collect(Collectors.toList());
                    List<Set<Loan>> futuresResult =
                            futures.stream()
                                    .map(CompletableFuture::join)
                                    .collect(Collectors.toList());
                    futuresResult.forEach(loanList::addAll);
                }
            } catch (NumberFormatException e) {
                logger.error("Unable to parse X-Total response header", e);
            }
        }
        return loanList;
    }

    /**
     * Download the loans with the specified offset and default page size
     * @param loansWebTarget RS WebTarget for retrieving loans
     * @param page Page being loaded
     * @return CompletableFuture of List of Loans
     */
    private CompletableFuture<Set<Loan>> getLoansPage(WebTarget loansWebTarget, int page) {
        return CompletableFuture.supplyAsync(() -> {
            Invocation.Builder invocationBuilder = loansWebTarget
                    .request(MediaType.APPLICATION_JSON)
                    .header("X-Page", page)
                    .header("X-Size", DEFAULT_PAGE_SIZE);
            return invocationBuilder
                    .get(new GenericType<Set<Loan>>() {});
        });
    }
}
