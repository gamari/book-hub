package gamari.app.features.base.constants;

public enum URL {
    ERROR_PAGE("redirect:/error_page"),
    DASHBOARD("redirect:/dashboard");

    private final String url;

    URL(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
