//package security.userDetails;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import database.entities.Todo_User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//public class UserDetailsImpl implements UserDetails {
//
//    private static final  long serialVersionUID = 1L;
//
//    private Long id;
//    private String login;
//    private String email;
//
//    @JsonIgnore
//    private String password;
//
//    private Collection<? extends GrantedAuthority> authorities;
//
//    public UserDetailsImpl(Long id, String login, String email, String password, Collection<? extends GrantedAuthority> authorities) {
//        this.id = id;
//        this.login = login;
//        this.email = email;
//        this.password = password;
//        this.authorities = authorities;
//    }
//
//    public static UserDetailsImpl build(Todo_User user) {
//        List<GrantedAuthority> authorities = user.getRole().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
//                .collect(Collectors.toList());
//
//        return new UserDetailsImpl(
//                user.getId(),
//                user.getLogin(),
//                user.getEmail(),
//                user.getPassword(),
//                authorities
//        );
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    public Long getId() { return id; }
//    public String getLogin() { return login; }
//    public String getEmail() { return email; }
//    public String getPassword() { return password; }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    public boolean isAccountNonExpired() { return true; }
//    public boolean isAccountNonLocked() { return true; }
//    public boolean isCredentialsNonExpired() { return true; }
//    public boolean isEnabled() { return true; }
//
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserDetailsImpl user = (UserDetailsImpl) o;
//        return Objects.equals(id, user.id);
//    }
//}
