package response;

public class JWTResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String login;
    private String email;

    public JWTResponse(String accessToken, Long id, String login, String email) {
        this.token = accessToken;
        this.id = id;
        this.login = login;
        this.email = email;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Long getId() { return id; }
}
