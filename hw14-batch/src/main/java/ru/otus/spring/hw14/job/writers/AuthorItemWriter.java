package ru.otus.spring.hw14.job.writers;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import ru.otus.spring.hw14.model.AuthorMongo;
import ru.otus.spring.hw14.model.IdRelation;
import ru.otus.spring.hw14.repository.AuthorRepositoryMongo;
import ru.otus.spring.hw14.repository.IdRelationRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorItemWriter implements ItemWriter<AuthorMongo> {

    private final AuthorRepositoryMongo authorRepositoryMongo;
    private final IdRelationRepository idRelationRepository;

    @Override
    public void write(List<? extends AuthorMongo> items) {
        List<IdRelation> idRelations = new ArrayList<>();
        items.forEach(it -> {
            IdRelation idRelation = new IdRelation();
            idRelation.setType(AuthorMongo.class.getName());
            idRelation.setSqlId(Long.parseLong(it.getId()));;
            it.setId(null);
            idRelation.setMongoId(authorRepositoryMongo.save(it).getId());
            idRelations.add(idRelation);
        });
        idRelationRepository.saveAll(idRelations);
    }

}
