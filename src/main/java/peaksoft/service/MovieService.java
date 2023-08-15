package peaksoft.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import peaksoft.model.Movie;

import java.util.List;
@Service
@Transactional

public class MovieService implements ServiceLayer<Movie>{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Movie save(Movie movie) {
        entityManager.persist(movie);
        return movie;
    }

    @Override
    public Movie findById(int id) {
        return entityManager.find(Movie.class,id);
    }

    @Override
    public List<Movie> findAll() {

        return (List<Movie>) entityManager.createQuery("from Movie ").getResultList();
    }

    @Override
    public Movie update(int id, Movie movie) {

        Movie oldMovie = entityManager.find(Movie.class,id);

        oldMovie.setName(movie.getName());
        oldMovie.setMovieType(movie.getMovieType());
        oldMovie.setCreatedDate(movie.getCreatedDate());
        oldMovie.setCountry(movie.getCountry());
        oldMovie.setLanguage(movie.getLanguage());

        return oldMovie;
    }

    @Override
    public void deleteById(int id) {
        entityManager.remove(entityManager.find(Movie.class,id));

    }
}
