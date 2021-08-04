package io.jmix2mvp.petclinic.gql;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphUtils;
import graphql.schema.DataFetchingFieldSelectionSet;
import graphql.schema.SelectedField;
import io.leangen.graphql.execution.ResolutionEnvironment;
import org.springframework.data.mapping.PersistentEntity;
import org.springframework.data.mapping.PersistentProperty;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntityGraphs {
    private final MappingContext<?, ?> mappingContext;

    public EntityGraphs(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") MappingContext<?, ?> mappingContext) {
        this.mappingContext = mappingContext;
    }

    public EntityGraph getEntityGraph(Class<?> entityClass, ResolutionEnvironment env) {
        PersistentEntity<?, ?> persistentEntity = mappingContext.getPersistentEntity(entityClass);
        assert persistentEntity != null;
        List<String> entityGraphProperties = collectEntityGraphProperties(persistentEntity,
                env.dataFetchingEnvironment.getSelectionSet(), null);
        return entityGraphProperties.isEmpty() ? null : EntityGraphUtils.fromAttributePaths(entityGraphProperties.toArray(new String[]{}));
    }

    protected List<String> collectEntityGraphProperties(PersistentEntity<?, ?> persistentEntity, DataFetchingFieldSelectionSet selectionSet, String parentPath) {
        List<String> entityGraphProperties = new ArrayList<>();
        for (SelectedField selectedField : selectionSet.getImmediateFields()) {
            PersistentProperty<?> persistentProperty = persistentEntity.getPersistentProperty(selectedField.getQualifiedName());
            if (persistentProperty != null && persistentProperty.isAssociation()) {
                String entityGraphProperty = parentPath == null ? persistentProperty.getName() :
                        String.format("%s.%s", parentPath, persistentProperty.getName());
                entityGraphProperties.add(entityGraphProperty);
                if (!selectedField.getSelectionSet().getImmediateFields().isEmpty()) {
                    PersistentEntity<?, ?> associationEntity =
                            mappingContext.getPersistentEntity(persistentProperty.getAssociationTargetType());
                    entityGraphProperties.addAll(collectEntityGraphProperties(associationEntity,
                            selectedField.getSelectionSet(), entityGraphProperty));
                }
            }
        }
        return entityGraphProperties;
    }
}
