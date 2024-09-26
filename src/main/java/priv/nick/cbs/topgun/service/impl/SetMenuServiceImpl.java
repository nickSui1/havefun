package priv.nick.cbs.topgun.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import priv.nick.cbs.topgun.config.exception.DataNotFoundException;
import priv.nick.cbs.topgun.model.SetMenu;
import priv.nick.cbs.topgun.dao.SetMenuMapper;
import priv.nick.cbs.topgun.dto.setmenu.SetMenuAddDTO;
import priv.nick.cbs.topgun.dto.setmenu.SetMenuInfoDTO;
import priv.nick.cbs.topgun.service.SetMenuService;
import priv.nick.cbs.topgun.util.SnowflakeIdWorker;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

/**
 * @author nick.sui
 * @since 2024-08-28
 * 用户接口实现
 */
@Service
public class SetMenuServiceImpl implements SetMenuService {
    private SetMenuMapper setMenuMapper;
    private ApplicationContext applicationContext;
    @Autowired
    public SetMenuServiceImpl(SetMenuMapper setMenuMapper,
                              ApplicationContext applicationContext) {
        this.setMenuMapper = setMenuMapper;
        this.applicationContext = applicationContext;
    }

    @Override
    public List<SetMenu> getSetMenus() {
        List<SetMenu> setMenus = setMenuMapper.selectAll();
        return setMenus;
    }

    @Override
    public Long add(SetMenuAddDTO setMenuAddDTO) {
        ModelMapper modelMapper = new ModelMapper();
        SnowflakeIdWorker snowflakeIdWorker = applicationContext.getBean(SnowflakeIdWorker.class);
        long id = snowflakeIdWorker.nextId();
        SetMenu setMenu = modelMapper.map(setMenuAddDTO, SetMenu.class);
        setMenu.setId(id);
        setMenu.setCreatedBy("admin");
        setMenu.setCreateTime(LocalDateTime.now().toInstant(ZoneOffset.UTC));
        setMenuMapper.insert(setMenu);

        return id;
    }

    @Override
    public SetMenuInfoDTO findById(Long id) {
        SetMenu setMenu = setMenuMapper.findById(id);
        if(setMenu != null){
            ModelMapper modelMapper  = new ModelMapper();
            SetMenuInfoDTO setMenuInfoDTO = modelMapper.map(setMenu, SetMenuInfoDTO.class);
            return setMenuInfoDTO;
        }else{
            throw new DataNotFoundException("Data Not Found");
        }
    }
}
