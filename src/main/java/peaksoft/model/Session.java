package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "sessions")

public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String name;
    private LocalDateTime start= LocalDateTime.now();
    private int duration;
    private LocalDateTime finish=LocalDateTime.now().plusHours(duration);

   @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sessions")
   private List<Room> rooms;



   @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "room_id")
   private Room room;


   @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "movie_id")

    private Movie movie;



}
