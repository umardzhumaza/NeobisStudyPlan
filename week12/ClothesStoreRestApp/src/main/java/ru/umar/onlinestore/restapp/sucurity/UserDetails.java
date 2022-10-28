package RestApp.src.main.java.ru.umar.onlinestore.restapp.sucurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.umar.onlinestore.restapp.models.User;

import java.util.Collection;
import java.util.Collections;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
    private final User user;

    public UserDetails(User user) {this.user = user;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {return Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));}

    @Override
    public String getPassword() {return this.user.getPassword();}

    @Override
    public String getUsername() {return this.user.getUsername();}

    @Override
    public boolean isAccountNonExpired() {return true;}

    @Override
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {return true;}

    @Override
    public boolean isEnabled() {return true;}

    //Нужно чтобы получать данные аутентиицированного пользователя
    public User getUser(){return this.user;}
}
