package priv.nick.cbs.topgun.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import priv.nick.cbs.topgun.domain.SetMenu;

import java.util.List;

/**
 * @since 2024-4-18 16:51
 * @author nicksy
 */
@Repository
@Mapper
public interface SetMenuMapper {
    List<SetMenu> selectAll();
    void insert(SetMenu setMenu);
    SetMenu findById(Long id);
}
