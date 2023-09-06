package gamari.app.features.base.libs;

/**
 * Google Books APIのクエリを作成するクラス。
 * 
 * https://developers.google.com/books/docs/v1/using?hl=ja
 */
public class GoogleBooksQueryBuilder {
    private StringBuilder query;

    public GoogleBooksQueryBuilder() {
        this.query = new StringBuilder();
    }

    public GoogleBooksQueryBuilder addKeyword(String keyword) {
        query.append("?q=intitle:").append(keyword);
        return this;
    }

    public GoogleBooksQueryBuilder addToken(String token) {
        query.append("&key=").append(token);
        return this;
    }

    public GoogleBooksQueryBuilder orderByNewest() {
        query.append("&orderBy=newest");
        return this;
    }

    public GoogleBooksQueryBuilder setMaxResults(int maxResults) {
        query.append("&maxResults=").append(maxResults);
        return this;
    }

    public GoogleBooksQueryBuilder setStartIndex(int startIndex) {
        query.append("&startIndex=").append(startIndex);
        return this;
    }

    public GoogleBooksQueryBuilder setCountry(String country) {
        query.append("&country=").append(country);
        return this;
    }

    public GoogleBooksQueryBuilder setLangRestrict(String langRestrict) {
        query.append("&langRestrict=").append(langRestrict);
        return this;
    }

    public GoogleBooksQueryBuilder orderByRelevance() {
        query.append("&orderBy=relevance");
        return this;
    }

    public String build() {
        return query.toString();
    }
}
