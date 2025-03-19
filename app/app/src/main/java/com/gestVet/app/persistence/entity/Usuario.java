package main.java.com.gestVet.app.persistence.entity;



import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

@Table(name = "usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;
    
    @Column(nullable = false, unique = true, length = 50)
    private String username;
    
    @Column(nullable = false, length = 255)
    private String password;
    
    @Column(nullable = false, length = 20)
    private String rol;
    
    @OneToOne
    @JoinColumn(name = "persona_id", unique = true)
    private Persona persona;
}
