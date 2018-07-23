package eu.galkina.zonky.mpchecker.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import static eu.galkina.zonky.mpchecker.model.JacksonPatterns.DATE_PATTERN;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Loan {

    @JsonProperty
    private String id;

    @JsonProperty
    private String url;

    @JsonProperty
    private String name;

    @JsonProperty
    private String story;

    @JsonProperty("purpose")
    private String purposeCode;

    @JsonProperty
    private List<Photo> photos;

    @JsonProperty
    private String userId;

    @JsonProperty
    private String nickName;

    @JsonProperty
    private Integer termInMonths;

    @JsonProperty
    private Double interestRate;

    @JsonProperty
    private String rating;

    @JsonProperty
    private Boolean topped;

    @JsonProperty
    private Double amount;

    @JsonProperty
    private Double remainingInvestment;

    @JsonProperty
    private Double investmentRate;

    @JsonProperty
    private Boolean covered;

    @JsonProperty
    @JsonFormat(pattern = DATE_PATTERN)
    private Date datePublished;

    @JsonProperty
    private Boolean published;

    @JsonProperty
    @JsonFormat(pattern = DATE_PATTERN)
    private Date deadline;

    @JsonProperty
    private Integer investmentsCount;

    @JsonProperty
    private Integer questionsCount;

    @JsonProperty
    private String region;

    @JsonProperty
    private String mainIncomeType;

    public String getId() {
        return id;
    }

    public Loan setId(String id) {
        this.id = id;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Loan setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getName() {
        return name;
    }

    public Loan setName(String name) {
        this.name = name;
        return this;
    }

    public String getStory() {
        return story;
    }

    public Loan setStory(String story) {
        this.story = story;
        return this;
    }

    public String getPurposeCode() {
        return purposeCode;
    }

    public Loan setPurposeCode(String purposeCode) {
        this.purposeCode = purposeCode;
        return this;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public Loan setPhotos(List<Photo> photos) {
        this.photos = photos;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public Loan setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getNickName() {
        return nickName;
    }

    public Loan setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public Integer getTermInMonths() {
        return termInMonths;
    }

    public Loan setTermInMonths(Integer termInMonths) {
        this.termInMonths = termInMonths;
        return this;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public Loan setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
        return this;
    }

    public String getRating() {
        return rating;
    }

    public Loan setRating(String rating) {
        this.rating = rating;
        return this;
    }

    public Boolean getTopped() {
        return topped;
    }

    public Loan setTopped(Boolean topped) {
        this.topped = topped;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public Loan setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public Double getRemainingInvestment() {
        return remainingInvestment;
    }

    public Loan setRemainingInvestment(Double remainingInvestment) {
        this.remainingInvestment = remainingInvestment;
        return this;
    }

    public Double getInvestmentRate() {
        return investmentRate;
    }

    public Loan setInvestmentRate(Double investmentRate) {
        this.investmentRate = investmentRate;
        return this;
    }

    public Boolean getCovered() {
        return covered;
    }

    public Loan setCovered(Boolean covered) {
        this.covered = covered;
        return this;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public Loan setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
        return this;
    }

    public Boolean getPublished() {
        return published;
    }

    public Loan setPublished(Boolean published) {
        this.published = published;
        return this;
    }

    public Date getDeadline() {
        return deadline;
    }

    public Loan setDeadline(Date deadline) {
        this.deadline = deadline;
        return this;
    }

    public Integer getInvestmentsCount() {
        return investmentsCount;
    }

    public Loan setInvestmentsCount(Integer investmentsCount) {
        this.investmentsCount = investmentsCount;
        return this;
    }

    public Integer getQuestionsCount() {
        return questionsCount;
    }

    public Loan setQuestionsCount(Integer questionsCount) {
        this.questionsCount = questionsCount;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public Loan setRegion(String region) {
        this.region = region;
        return this;
    }

    public String getMainIncomeType() {
        return mainIncomeType;
    }

    public Loan setMainIncomeType(String mainIncomeType) {
        this.mainIncomeType = mainIncomeType;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Objects.equals(id, loan.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("url", url)
                .append("name", name)
                .append("story", story)
                .append("purposeCode", purposeCode)
                .append("photos", photos)
                .append("userId", userId)
                .append("nickName", nickName)
                .append("termInMonths", termInMonths)
                .append("interestRate", interestRate)
                .append("rating", rating)
                .append("topped", topped)
                .append("amount", amount)
                .append("remainingInvestment", remainingInvestment)
                .append("investmentRate", investmentRate)
                .append("covered", covered)
                .append("datePublished", datePublished)
                .append("published", published)
                .append("deadline", deadline)
                .append("investmentsCount", investmentsCount)
                .append("questionsCount", questionsCount)
                .append("region", region)
                .append("mainIncomeType", mainIncomeType)
                .toString();
    }
}
