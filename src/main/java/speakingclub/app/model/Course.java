package speakingclub.app.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import speakingclub.app.model.enums.CourseDirection;
import speakingclub.app.model.enums.CourseType;

@Entity
@Data
@Table(name = "courses")
@SQLDelete(sql = "UPDATE courses SET is_deleted = TRUE WHERE id = ?")
@Where(clause = "is_deleted = FALSE")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private BigDecimal price;
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    private CourseType courseType;
    @Enumerated(EnumType.STRING)
    private CourseDirection courseDirection;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Module> modules = new HashSet<>();
    @Column(nullable = false)
    private boolean isDeleted = false;
}
