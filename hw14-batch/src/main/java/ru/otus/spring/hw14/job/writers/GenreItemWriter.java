package ru.otus.spring.hw14.job.writers;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import ru.otus.spring.hw14.model.GenreMongo;
import ru.otus.spring.hw14.model.IdRelation;
import ru.otus.spring.hw14.repository.GenreRepositoryMongo;
import ru.otus.spring.hw14.repository.IdRelationRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreItemWriter implements ItemWriter<GenreMongo> {

    private final GenreRepositoryMongo genreRepositoryMongo;
    private final IdRelationRepository idRelationRepository;

    @Override
    public void write(List<? extends GenreMongo> items) {
        List<IdRelation> idRelations = new ArrayList<>();
        items.forEach(it -> {
            IdRelation idRelation = new IdRelation();
            idRelation.setType(GenreMongo.class.getName());
            idRelation.setSqlId(Long.parseLong(it.getId()));;
            it.setId(null);
            idRelation.setMongoId(genreRepositoryMongo.save(it).getId());
            idRelations.add(idRelation);
        });
        idRelationRepository.saveAll(idRelations);
    }

}
