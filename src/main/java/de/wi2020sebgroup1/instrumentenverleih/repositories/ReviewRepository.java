package de.wi2020sebgroup1.instrumentenverleih.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import de.wi2020sebgroup1.instrumentenverleih.entities.Movie;
import de.wi2020sebgroup1.instrumentenverleih.entities.Review;
import de.wi2020sebgroup1.instrumentenverleih.entities.User;

public interface ReviewRepository extends CrudRepository<Review, UUID> {
	
	Optional<List<Review>> findAllByUser(User user);
	
	Optional<List<Review>> findAllByMovie(Movie movie);

}
