package priv.nick.cbs.topgun.mapper;

import org.apache.ibatis.annotations.Mapper;
import priv.nick.cbs.topgun.domain.SetMenu;

import java.util.List;

/**
 * @since 2024-4-18 16:51
 * @author nicksy
 */
@Mapper
public interface SetMenuMapper {
    int insert(SetMenu setMenu);
    int update(SetMenu setMenu);
    int deleteById(Long id);
    SetMenu selectById(Long id);
    List<SetMenu> selectAll();
}
