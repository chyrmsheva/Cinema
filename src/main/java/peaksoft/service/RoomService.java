package peaksoft.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import peaksoft.model.Room;

import java.util.List;
@Service
@Transactional
public class RoomService implements ServiceLayer<Room>{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Room save(Room room) {
        entityManager.persist(room);
        return room;
    }

    @Override
    public Room findById(int id) {
        return entityManager.find(Room.class,id);
    }

    @Override
    public List<Room> findAll() {
        return (List<Room>) entityManager.createQuery("from Room ").getResultList();
    }

    @Override
    public Room update(int id, Room room) {

        Room oldRoom = entityManager.find(Room.class,id);
        oldRoom.setName(room.getName());
        oldRoom.setRating(room.getRating());
        return oldRoom;
    }

    @Override
    public void deleteById(int id) {
        entityManager.remove(entityManager.find(Room.class,id));

    }
}
