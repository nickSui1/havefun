package priv.nick.cbs.topgun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import priv.nick.cbs.topgun.model.Client;

@Repository
@Mapper
public interface ClientMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Client row);

    Client selectByPrimaryKey(Long id);

    List<Client> selectAll();

    int updateByPrimaryKey(Client row);

    Client selectByTenantId(Long tenantId);
}