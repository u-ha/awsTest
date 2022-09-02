package uha.awsTest.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uha.awsTest.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "USER_TABLE")
public class User extends BaseTimeEntity {
    @Id @GeneratedValue
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }
    public User update(String name, String picture){
        this.name=name;
        this.picture=picture;

        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
