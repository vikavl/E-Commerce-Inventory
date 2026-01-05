package vikavl.cloud.computing.inventory.mapper;

public interface BaseMapper<D, E> {
    D toDto(E entity);
    E toEntity(D dto);
}
