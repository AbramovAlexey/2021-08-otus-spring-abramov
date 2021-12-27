package ru.otus.spring.hw14.job.processors;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw14.repository.IdRelationRepository;

@Service
@RequiredArgsConstructor
public class RelationCacheServiceImpl implements RelationCacheService{

    private final IdRelationRepository idRelationRepository;

    @Override
    @Cacheable(cacheNames = "relationCache")
    public String getRelationId(Long sqlId, Class _class) {
        return idRelationRepository.findBySqlIdAndType(sqlId, _class.getName())
                .orElseThrow(() -> new RuntimeException(String.format("Not found relation for %s with sqlId %s", _class.getName(), sqlId)))
                .getMongoId() ;
    }

}
